package com.netcracker.trainee.config.xml.entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("runner-config")
public class XmlFile {

    private String basePackage = "";
    private XmlFillers fillers;
    private XmlExperiment experiment;

    public XmlFillers getFillers() {
        return fillers;
    }

    public void setFillers(XmlFillers fillers) {
        this.fillers = fillers;
    }

    public XmlExperiment getExperiment() {
        return experiment;
    }

    public void setExperiment(XmlExperiment experiment) {
        this.experiment = experiment;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}
