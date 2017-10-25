package com.netcracker.trainee.analyzer;

import com.netcracker.trainee.fillers.FillStrategy;
import com.netcracker.trainee.sorters.Sorter;

public class SorterTimeAnalyzer implements SorterAnalyzer{

    private FillStrategy strategy;

    public SorterTimeAnalyzer(FillStrategy fillStrategy) {
        this.strategy = fillStrategy;
    }

    @Override
    public AnalysisResult makeAnalysis(Sorter sorter) {
        int[] array = strategy.doFill();

        long timeExecution = timeAnalyses(sorter, array);

        
        return new AnalysisResult()
                .setExecutionTime(timeExecution)
                .setArraySize(array.length);
    }

    private long timeAnalyses(Sorter sorter, int[] array) {

        long start = System.nanoTime();
        sorter.sort(array);
        long end = System.nanoTime();

        return end - start;
    }

}
