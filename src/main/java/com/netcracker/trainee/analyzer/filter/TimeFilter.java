package com.netcracker.trainee.analyzer.filter;

import com.netcracker.trainee.analyzer.AnalysisResult;
import com.netcracker.trainee.analyzer.filter.base.Filter;
import com.netcracker.trainee.analyzer.filter.base.FilterChain;

public class TimeFilter implements Filter<AnalysisResult> {

    public TimeFilter() {}

    @Override
    public void doFilter(AnalysisResult result, FilterChain<AnalysisResult> filterChain) {
        long start = System.nanoTime();
        filterChain.next(result);
        long end = System.nanoTime();

        result.setExecutionTime(end - start);
    }
}
