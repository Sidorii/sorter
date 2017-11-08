package com.netcracker.trainee.config.xml.entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Set;

@XStreamAlias("experiment")
public class XmlExperiment {

    @XStreamAlias("strategy")
    private XmlFillStrategy fillStrategy;
    @XStreamAlias("analyser-filters")
    private Set<XmlFilter> filters;
    private XmlSorters sorters;



    public XmlFillStrategy getFillStrategy() {
        return fillStrategy;
    }

    public void setFillStrategy(XmlFillStrategy fillStrategy) {
        this.fillStrategy = fillStrategy;
    }

    public Set<XmlFilter> getFilters() {
        return filters;
    }

    public XmlSorters getXmlSorters() {
        return sorters;
    }
}
