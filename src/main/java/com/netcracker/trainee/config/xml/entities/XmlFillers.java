package com.netcracker.trainee.config.xml.entities;

import com.netcracker.trainee.fillers.FillStrategy;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Properties;
import java.util.Set;

public class XmlFillers{
    @XStreamAlias("input")
    private Properties properties;
    @XStreamAlias("annotationConfig")
    private boolean isAnnotationCfg;

    private Set<FillStrategy> strategies;

    public Set<FillStrategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(Set<FillStrategy> strategies) {
        this.strategies = strategies;
    }

    public XmlFillers() {
        properties = new Properties();
    }


    public boolean isAnnotationCfg() {
        return isAnnotationCfg;
    }

    public void setAnnotationCfg(boolean annotationCfg) {
        isAnnotationCfg = annotationCfg;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
