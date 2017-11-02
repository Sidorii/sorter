package com.netcracker.trainee.config;

import com.netcracker.trainee.config.xml.entities.XmlFillStrategy;
import com.netcracker.trainee.fillers.FillStrategy;

import java.util.Set;

public interface FillStrategyConfigurer {

    Set<? extends FillStrategy> configure(XmlFillStrategy fillStrategy);
}
