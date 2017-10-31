package com.netcracker.trainee.analyzer;

import com.netcracker.trainee.fillers.FillStrategy;
import com.netcracker.trainee.sorters.Sorter;

public class SorterTimeAnalyzer extends SorterAnalyzer {


    public SorterTimeAnalyzer(FillStrategy fillStrategy) {
        super(fillStrategy);
    }

    public SorterTimeAnalyzer() {
        super();
    }

    @Override
    public AnalysisResult makeAnalysis(Sorter sorter) {
        int[] array = fillStrategy.doFill();
        long timeExecution = timeAnalyses(sorter, array);

        return new AnalysisResult()
                .setExecutionTime(timeExecution)
                .setArraySize(array.length)
                .setSortType(sorter.getClass().getSimpleName())
                .setFillName(fillStrategy.getName());
    }

    private long timeAnalyses(Sorter sorter, int[] array) {

        long start = System.nanoTime();
        sorter.sort(array);
        long end = System.nanoTime();

        return end - start;
    }

}
