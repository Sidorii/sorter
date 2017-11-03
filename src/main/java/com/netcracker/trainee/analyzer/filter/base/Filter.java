package com.netcracker.trainee.analyzer.filter.base;

public interface Filter<T> {

    void doFilter(T result,FilterChain<T> filterChain);
}
