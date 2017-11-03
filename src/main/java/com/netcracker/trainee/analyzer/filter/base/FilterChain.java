package com.netcracker.trainee.analyzer.filter.base;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class FilterChain<T> {

    private LinkedHashSet<Filter<T>> filters;
    protected Iterator<Filter<T>> iterator;

    public FilterChain(Collection<Filter<T>> filters) {
        this.filters = new LinkedHashSet<>(filters);
        iterator = filters.iterator();
    }

    protected void addFilter(Filter<T> filter) {
        if (filter != null) {
            filters.add(filter);
        }
    }

    protected boolean removeFilter(Filter<T> filter) {
        if (filters.contains(filter)) {
            filters.remove(filter);
            return true;
        }
        return false;
    }

    protected LinkedHashSet<Filter<T>> getFilters() {
        return filters;
    }

    public void next(T result) {
        if (iterator.hasNext()) {
            iterator.next().doFilter(result,this);
        }
    }

    protected boolean hasNext() {
        return iterator.hasNext();
    }
}
