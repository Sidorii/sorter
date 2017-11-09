package com.netcracker.trainee.config.xml.entities;

import com.netcracker.trainee.fillers.FillStrategy;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@XStreamAlias("strategy")
public class XmlFillStrategy {

    @XStreamAsAttribute
    private String id;

    @XStreamAlias("input")
    private Properties[] properties;
    @XStreamAlias("annotationConfig")
    private boolean isAnnotationCfg;

    private Set<FillStrategy> strategies;

    public XmlFillStrategy(){
        strategies = new HashSet<>();
        properties = new Properties[0];
    }

    public Set<FillStrategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(Set<FillStrategy> strategies) {
        this.strategies = strategies;
    }

    public boolean isAnnotationCfg() {
        return isAnnotationCfg;
    }

    public void setAnnotationCfg(boolean annotationCfg) {
        isAnnotationCfg = annotationCfg;
    }

    public Properties[] getProperties() {
        return properties;
    }

    public void setProperties(Properties[] properties) {
        this.properties = properties;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
