package com.netcracker.trainee.config.xml.entities;

import java.util.Set;

public class XmlFillStrategy  {

    private boolean enableFillersStrategy;
    private Set<XmlFillers> fillers;


    public Set<XmlFillers> getFillers() {
        return fillers;
    }

    public void setFillers(Set<XmlFillers> fillers) {
        this.fillers = fillers;
    }

    public boolean isEnableFillersStrategy() {
        return enableFillersStrategy;
    }

    public void setEnableFillersStrategy(boolean enableFillersStrategy) {
        this.enableFillersStrategy = enableFillersStrategy;
    }
}
