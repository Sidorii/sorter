package com.netcracker.trainee.analyzer;

public class AdvancedAnalysisResult extends AnalysisResult{

    private long swapCount;
    private String fillName;
    private String sortType;

    public long getSwapCount() {
        return swapCount;
    }

    public AdvancedAnalysisResult setSwapCount(long swapCount) {
        this.swapCount = swapCount;
        return this;
    }

    @Override
    public AdvancedAnalysisResult setExecutionTime(long executionTime) {
        return (AdvancedAnalysisResult) super.setExecutionTime(executionTime);
    }

    @Override
    public AdvancedAnalysisResult setArraySize(long arraySize) {
        return (AdvancedAnalysisResult) super.setArraySize(arraySize);
    }

    public AdvancedAnalysisResult setFillName(String fillName) {
        this.fillName = fillName;
        return this;
    }

    public AdvancedAnalysisResult setSortType(String sortType) {
        this.sortType = sortType;
        return this;
    }

    public String toString() {
        return super.toString() + "\nSorter type: " + sortType + "\nFiller name: " + fillName + "\n==============\n";
    }

}
