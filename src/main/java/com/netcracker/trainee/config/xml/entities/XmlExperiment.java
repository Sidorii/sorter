package com.netcracker.trainee.config.xml.entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.Set;

@XStreamAlias("experiment")
public class XmlExperiment {

    @XStreamAlias("strategy")
    private XmlFillStrategy fillStrategy;
    private Set<XmlAnalyser> analyzers;
    private XmlSorters sorters;



    public XmlFillStrategy getFillStrategy() {
        return fillStrategy;
    }

    public void setFillStrategy(XmlFillStrategy fillStrategy) {
        this.fillStrategy = fillStrategy;
    }

    public Set<XmlAnalyser> getAnalyzers() {
        return analyzers;
    }

    public void setAnalyzers(Set<XmlAnalyser> analyzers) {
        this.analyzers = analyzers;
    }

    public XmlSorters getSorters() {
        return sorters;
    }

    public void setSorters(XmlSorters sorters) {
        this.sorters = sorters;
    }
}
