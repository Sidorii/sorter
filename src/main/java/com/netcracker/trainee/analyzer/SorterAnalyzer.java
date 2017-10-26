package com.netcracker.trainee.analyzer;

import com.netcracker.trainee.fillers.FillStrategy;
import com.netcracker.trainee.sorters.Sorter;

public abstract class SorterAnalyzer {

    protected FillStrategy fillStrategy;

    public SorterAnalyzer() {
    }

    public SorterAnalyzer(FillStrategy strategy) {
        this.fillStrategy = strategy;
    }


    public abstract AnalysisResult makeAnalysis(Sorter sorter);

    public void setFillStrategy(FillStrategy strategy){
        this.fillStrategy = strategy;
    }
}
