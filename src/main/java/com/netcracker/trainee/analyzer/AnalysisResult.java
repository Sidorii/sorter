package com.netcracker.trainee.analyzer;

public class AnalysisResult {

    private long executionTime;
    private long arraySize;
    private long swapCount;
    private String fillName;
    private String sortType;


    protected AnalysisResult() {}


    public long getSwapCount() {
        return swapCount;
    }

    public AnalysisResult setSwapCount(long swapCount) {
        this.swapCount = swapCount;
        return this;
    }


    public AnalysisResult setFillName(String fillName) {
        this.fillName = fillName;
        return this;
    }


    public String getFillName() {
        return fillName;
    }


    public AnalysisResult setSortType(String sortType) {
        this.sortType = sortType;
        return this;
    }

    public String getSortType() {
        return sortType;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public AnalysisResult setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
        return this;
    }

    public long getArraySize() {
        return arraySize;
    }

    public AnalysisResult setArraySize(long arraySize) {
        this.arraySize = arraySize;
        return this;
    }


    @Override
    public String toString() {
        return "Execute time: " + executionTime + " nanoseconds,\nArray size: " + arraySize +
                "\nSorter type: " + sortType + "\nFiller name: " + fillName + "\n==============\n";
    }
}

