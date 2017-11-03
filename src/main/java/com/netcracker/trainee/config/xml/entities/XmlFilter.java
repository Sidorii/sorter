package com.netcracker.trainee.config.xml.entities;

import com.netcracker.trainee.analyzer.AnalysisResult;
import com.netcracker.trainee.analyzer.filter.base.Filter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("filter")
public class XmlFilter {


    @XStreamAlias("class-name")
    @XStreamAsAttribute
    private Class<? extends Filter<AnalysisResult>> clazz;

    public XmlFilter(Class<? extends Filter<AnalysisResult>> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends Filter<AnalysisResult>> getClazz() {
        return clazz;
    }
}
