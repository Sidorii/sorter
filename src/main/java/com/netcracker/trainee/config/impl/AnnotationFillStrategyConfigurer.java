package com.netcracker.trainee.config.impl;

import com.netcracker.trainee.config.FillStrategyConfigurer;
import com.netcracker.trainee.config.xml.entities.XmlFillers;
import com.netcracker.trainee.fillers.Arg;
import com.netcracker.trainee.fillers.FillStrategy;
import com.netcracker.trainee.fillers.Filler;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;


public class AnnotationFillStrategyConfigurer implements FillStrategyConfigurer {

    private String basePackage;

    public AnnotationFillStrategyConfigurer(String basePackage) {
        this.basePackage = basePackage;
    }


    @Override
    public Set<FillStrategy> configure(XmlFillers fillers) {
        Set<FillStrategy> strategies = new HashSet<>();
        FillStrategy strategy;


        Reflections reflection = new Reflections(basePackage,new MethodAnnotationsScanner());
        Set<Method> methods = reflection.getMethodsAnnotatedWith(Filler.class);


        for (Method m : methods) {
            Filler filler = m.getAnnotation(Filler.class);
            Parameter[] parameters = m.getParameters();
            strategy = new FillStrategy() {

                @Override
                public String getName() {
                    return filler.name();
                }

                @Override
                public int[] doFill() {
                    try {
                        Object[] arguments = mapInvocationArguments(parameters,fillers);
                        return (int[]) m.invoke(null, arguments);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException("Method invocation failed. Reason:" + e.getMessage());
                    }
                }
            };

            strategies.add(strategy);
        }

        return strategies;
    }



    private Object[] mapInvocationArguments(Parameter[] parameters, XmlFillers fillers) {
        List<Integer> arguments = new ArrayList<>();

        for (Parameter p : parameters) {
            Arg arg = p.getAnnotation(Arg.class);
            if (arg != null) {
                arguments.add(Integer.parseInt((String)fillers.getProperties().get(arg.value())));
            }

        }

        return arguments.toArray();
    }
}
