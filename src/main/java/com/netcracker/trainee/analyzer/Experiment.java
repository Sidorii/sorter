package com.netcracker.trainee.analyzer;

import com.netcracker.trainee.fillers.FillStrategy;
import com.netcracker.trainee.sorters.Sorter;
import com.netcracker.trainee.view.DrawAPI;

import java.util.HashSet;
import java.util.Set;

public class Experiment {

    private Set<? extends FillStrategy> fillStrategies;
    private Set<? extends Sorter> sorters;
    private SorterAnalyzer analyzer;


    public Experiment(Set<? extends FillStrategy> fillStrategies,
                      Set<? extends Sorter> sorters,
                      SorterAnalyzer analyzer) {

        this.fillStrategies = fillStrategies;
        this.sorters = sorters;
        this.analyzer = analyzer;
    }


    public final Set<AnalysisResult> makeExperiment() {
        Set<AnalysisResult> results = new HashSet<>();

        for (FillStrategy f : fillStrategies) {
            analyzer.setFillStrategy(f);
            for (Sorter s : sorters) {
                AnalysisResult result = analyzer.makeAnalysis(s);
                results.add(result);
            }
        }

        return results;
    }
}
