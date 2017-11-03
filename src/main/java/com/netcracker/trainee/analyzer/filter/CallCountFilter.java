package com.netcracker.trainee.analyzer.filter;

import com.netcracker.trainee.analyzer.AnalysisResult;
import com.netcracker.trainee.analyzer.filter.base.Filter;
import com.netcracker.trainee.analyzer.filter.base.FilterChain;

public class CallCountFilter implements Filter<AnalysisResult> {

    private int count;

    @Override
    public void doFilter(AnalysisResult result, FilterChain<AnalysisResult> filterChain) {
        System.out.println(++count);
        filterChain.next(result);
    }
}
