package com.netcracker.trainee.config.xml.entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("runner-config")
public class XmlFile {


    private String basePackage = "";
    private XmlFillStrategy[] fillers;
    private XmlExperiment experiment;
    private boolean isStrategyFound;

    public XmlFillStrategy[] getFillers() {
        return fillers;
    }

    public void setFillers(XmlFillStrategy[] fillers) {
        this.fillers = fillers;
    }

    public XmlExperiment getExperiment() {
        if (isStrategyFound) {
            return experiment;
        }
        findFillStrategyById(experiment.getFillStrategy().getId());
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


    private void findFillStrategyById(String id) {

        for (XmlFillStrategy s: fillers) {
            if (s.getId().equals(id)) {
                experiment.setFillStrategy(s);
                isStrategyFound = true;
                break;
            }
        }
        if (!isStrategyFound) {
            throw new RuntimeException("No such fill strategy by id = " + id + " found");
        }
    }
}
