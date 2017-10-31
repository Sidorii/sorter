package com.netcracker.trainee.config.xml.entities;

import com.netcracker.trainee.sorters.Sorter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.Set;

@XStreamAlias("sorters")
public class XmlSorters {

    private Set<Sorter> sorters;

    @XStreamAsAttribute
    @XStreamAlias("auto-scan")
    private boolean isAutoScan;

    public Set<Sorter> getSorters() {
        return sorters;
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
}
