package com.netcracker.trainee.config.impl;

import com.netcracker.trainee.config.FillStrategyConfigurer;
import com.netcracker.trainee.config.xml.entities.XmlFillStrategy;
import com.netcracker.trainee.fillers.Arg;
import com.netcracker.trainee.fillers.FillStrategy;
import com.netcracker.trainee.fillers.Filler;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * {@inheritDoc}
 * Class used to configuration multiply {@link FillStrategy} from annotations.<br>
 * Annotation used for config:<br>
 * <ul>
 *      <li>{@link Filler} - mark methods that used as array fillers.</li>
 *      <li>{@link Arg} - sets values in method arguments from xml file</li>
 * </ul>
 *
 * @see Arg
 * @see Filler
 * @see FillStrategyConfigurer
 * @see StdFillStrategyConfigurer
 * @see XmlFillStrategy
 *
 */
public class AnnotationFillStrategyConfigurer implements FillStrategyConfigurer {

    private String basePackage;
    private XmlFillStrategy fillers;

    /**
     * @param basePackage Base package for searching fill strategies that marked by annotations
     *                    (at least {@link Filler}
     * */
    public AnnotationFillStrategyConfigurer(String basePackage,XmlFillStrategy fillers) {
        this.basePackage = basePackage;
        this.fillers = fillers;
    }

    /**
     * {@inheritDoc}
     * Use {@link Reflections reflection} for searching fillers in
     * {@link AnnotationFillStrategyConfigurer#basePackage}
     *
     * @return Return set of {@link FillStrategy fill strategies} that builds on methods which
     * marked by {@link Filler} annotation.
     *
     * */
    @Override
    public Set<FillStrategy> configure() {
        Set<FillStrategy> strategies = new HashSet<>();
        FillStrategy strategy;

        Reflections reflection = new Reflections(basePackage, new MethodAnnotationsScanner());
        Set<Method> methods = reflection.getMethodsAnnotatedWith(Filler.class);

        for (Method m : methods) {
            Filler filler = m.getAnnotation(Filler.class);
            Parameter[] parameters = m.getParameters();

            for (Properties p : fillers.getProperties()) {
                strategy = new FillStrategy() {

                    @Override
                    public String getName() {
                        return filler.name();
                    }

                    @Override
                    public int[] doFill() {
                        try {
                            Object[] arguments = mapInvocationArguments(parameters, p);
                            return (int[]) m.invoke(null, arguments);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException("Method invocation failed. Reason:" + e.getMessage());
                        }
                    }
                };
                strategies.add(strategy);
            }
        }
        return strategies;
    }


    private Object[] mapInvocationArguments(Parameter[] parameters, Properties properties) {
        List<Integer> arguments = new ArrayList<>();

        for (Parameter p : parameters) {
            Arg arg = p.getAnnotation(Arg.class);
            if (arg != null) {
                String prop = properties.getProperty(arg.value());
                if (prop == null) {
                    throw new RuntimeException("Property value " + arg.value() +
                            " for annotation @Arg is not defined.");
                }
                arguments.add(Integer.parseInt(prop));
            }
        }
        return arguments.toArray();
    }
}
