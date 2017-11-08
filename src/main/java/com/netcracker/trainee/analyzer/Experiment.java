package com.netcracker.trainee.analyzer;

import com.netcracker.trainee.fillers.FillStrategy;
import com.netcracker.trainee.sorters.Sorter;

import java.util.HashSet;
import java.util.Set;


/**
 * Class that represent experiment on sorting algorithms. It means that
 * {@link Experiment} class encapsulate behavior of sorter analyser,
 * set of fill array strategies, sorting algorithms and consistently
 * call them with purpose to get {@link AnalysisResult analysys results}
 * of sorting method execution in differs conditions.
 *
 * @author Ivan Sidorenko
 * @version 1.0
 * @see FillStrategy
 * @see SorterAnalyzer
 * @see AnalysisResult
 * @see Sorter
 * @since 1.0
 */
public class Experiment {

    private Set<? extends FillStrategy> fillStrategies;
    private Set<? extends Sorter> sorters;
    private SorterAnalyzer analyzer;


    /**
     * Single constructor for for experiment configuring.
     *
     * @param analyzer       analyser that make analysis for set of sorters that
     *                       sorts arrays filled by different fill strategies
     * @param fillStrategies set strategies of filling arrays for different sorters
     * @param sorters        different sorting algorithms encapsulated in this set of classes
     */
    public Experiment(Set<? extends FillStrategy> fillStrategies,
                      Set<? extends Sorter> sorters,
                      SorterAnalyzer analyzer) {

        this.fillStrategies = fillStrategies;
        this.sorters = sorters;
        this.analyzer = analyzer;
    }

    /**
     * Method that encapsulate all experiment logic.
     * It make analysis by {@link SorterAnalyzer} class for different sorting
     * algorithms under different conditions such as input arrays that defined
     * fill strategies, arrays size and other conditions defined in
     * {@link Experiment#Experiment(Set, Set, SorterAnalyzer)}  constructor
     *
     * @return Set of grouped {@link AnalysisResult} objects for every analysis made.
     */
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
