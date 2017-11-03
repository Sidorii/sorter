package com.netcracker.trainee.analyzer.filter;

import com.netcracker.trainee.analyzer.AnalysisResult;
import com.netcracker.trainee.analyzer.filter.base.Filter;
import com.netcracker.trainee.analyzer.filter.base.FilterChain;

public class LogFilter implements Filter<AnalysisResult> {


    @Override
    public void doFilter(AnalysisResult res, FilterChain<AnalysisResult> filterChain) {

        filterChain.next(res);
        System.out.println("Sorter invocation [ " + res.getSortType() +
                " ] with fill strategy [ " + res.getFillName() + " ] for array size = [ " + res.getArraySize() + " ]");
    }
}
