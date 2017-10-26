package com.netcracker.trainee.analyzer;

import com.netcracker.trainee.fillers.FillStrategy;
import com.netcracker.trainee.sorters.Sorter;

import java.util.HashSet;
import java.util.Set;

public class Experiment {

    private Set<? extends FillStrategy> fillStrategies;
    private Set<? extends Sorter> sorters;
    private Set<? extends SorterAnalyzer> analyzers;

    public Experiment(Set<? extends FillStrategy> fillStrategies, Set<? extends Sorter> sorters, Set<? extends SorterAnalyzer> analyzers) {
        this.fillStrategies = fillStrategies;
        this.sorters = sorters;
        this.analyzers = analyzers;
    }

    public Set<AnalysisResult> makeExperiment() {

        Set<AnalysisResult> results = new HashSet<>();

        for (FillStrategy f: fillStrategies) {

            for (SorterAnalyzer a : analyzers) {
                a.setFillStrategy(f);
                for (Sorter s : sorters) {
                    results.add(a.makeAnalysis(s));
                }
            }
        }
        return results;
    }
}
