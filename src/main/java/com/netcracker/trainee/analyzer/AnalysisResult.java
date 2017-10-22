package com.netcracker.trainee.analyzer;

public class AnalysisResult {

    private long executionTime;
    private long arraySize;


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
        return "Execute time: " + executionTime + " nanoseconds,\nArray size: " + arraySize;
    }
}

