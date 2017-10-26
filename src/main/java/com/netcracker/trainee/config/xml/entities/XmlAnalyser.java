package com.netcracker.trainee.config.xml.entities;

import com.netcracker.trainee.analyzer.SorterAnalyzer;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("analyser")
public class XmlAnalyser {

    @XStreamAlias("class")
    private Class<? extends SorterAnalyzer> clazz;

    public XmlAnalyser(Class<? extends SorterAnalyzer> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends SorterAnalyzer> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends SorterAnalyzer> clazz) {
        this.clazz = clazz;
    }

}
