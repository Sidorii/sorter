package com.netcracker.trainee.config.xml.entities;

import com.netcracker.trainee.config.impl.AutoDetectedSortersConfigurer;
import com.netcracker.trainee.sorters.Sorter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.HashSet;
import java.util.Set;

@XStreamAlias("sorters")
public class XmlSorters {

    private Set<Sorter> sorters;
    private String basePackage = "";

    @XStreamAsAttribute
    @XStreamAlias("auto-scan")
    private boolean isAutoScan;


    public Set<Sorter> getSorters() {
        return isAutoScan ? new AutoDetectedSortersConfigurer(Sorter.class, basePackage).configure() : sorters;
    }

    public void setSorters(Set<Sorter> sorters) {
        this.sorters = sorters;
    }

    public boolean isAutoScan() {
        return isAutoScan;
    }

    public void setAutoScan(boolean autoScan) {
        isAutoScan = autoScan;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}
