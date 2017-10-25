package com.netcracker.trainee.analyzer;

public class AdvancedAnalysisResult extends AnalysisResult{

    private long swapCount;


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
}
