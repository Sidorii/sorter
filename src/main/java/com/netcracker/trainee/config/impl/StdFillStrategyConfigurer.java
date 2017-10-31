package com.netcracker.trainee.config.impl;

import com.netcracker.trainee.config.FillStrategyConfigurer;
import com.netcracker.trainee.config.xml.entities.XmlFillers;
import com.netcracker.trainee.fillers.FillStrategy;

import java.util.Set;

public class StdFillStrategyConfigurer implements FillStrategyConfigurer {

    private String basePackage;


    public StdFillStrategyConfigurer(String basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public Set<? extends FillStrategy> configure(XmlFillers fillers) {

        if (fillers.isAnnotationCfg()) {
            return new AnnotationFillStrategyConfigurer(basePackage).configure(fillers);
        }
        return fillers.getStrategies();
    }
}
