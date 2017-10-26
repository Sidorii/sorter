package com.netcracker.trainee.config;

import com.netcracker.trainee.config.xml.entities.XmlFillers;
import com.netcracker.trainee.fillers.FillStrategy;

import java.util.Properties;
import java.util.Set;

public interface FillStrategyConfigurer {

    Set<? extends FillStrategy> configure(XmlFillers fillers);
}
