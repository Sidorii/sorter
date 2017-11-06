package com.netcracker.trainee.config.impl;

import com.netcracker.trainee.config.FillStrategyConfigurer;
import com.netcracker.trainee.config.xml.entities.XmlFillStrategy;
import com.netcracker.trainee.fillers.FillStrategy;
import com.netcracker.trainee.fillers.Filler;
import org.reflections.Reflections;

import java.util.Set;

/**
 * {@inheritDoc}
 * Class used for configuring {@link FillStrategy} set from annotation config or xml file directly.
 *
 * */
public class StdFillStrategyConfigurer implements FillStrategyConfigurer {


    private String basePackage;

    /**
     * @param basePackage Base package for searching fill strategies that marked by annotations (at least {@link Filler}
     * */
    public StdFillStrategyConfigurer(String basePackage) {
        this.basePackage = basePackage;
    }

    /**
     * {@inheritDoc}
     * Configure multiply of FillStrategies from annotation or xml file directly.
     * Witch way will be chosen depends on {@link XmlFillStrategy} config.
     * If {@link XmlFillStrategy#isAnnotationCfg()} is true - then {@link AnnotationFillStrategyConfigurer} config will be chosen,
     * else - return strategies from xml directly.
     *
     * @see XmlFillStrategy#isAnnotationCfg()
     * @see AnnotationFillStrategyConfigurer
     *
     * @return Return set of {@link FillStrategy fill strategies} by {@link XmlFillStrategy} config.
     * */
    @Override
    public Set<? extends FillStrategy> configure(XmlFillStrategy fillStrategy) {

        if (fillStrategy.isAnnotationCfg()) {
            return new AnnotationFillStrategyConfigurer(basePackage).configure(fillStrategy);
        }
        return fillStrategy.getStrategies();
    }
}
