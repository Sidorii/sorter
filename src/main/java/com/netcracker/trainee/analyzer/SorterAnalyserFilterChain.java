package com.netcracker.trainee.analyzer;

import com.netcracker.trainee.analyzer.filter.base.Filter;
import com.netcracker.trainee.analyzer.filter.base.FilterChain;
import com.netcracker.trainee.fillers.FillStrategy;
import com.netcracker.trainee.sorters.Sorter;

import java.util.LinkedHashSet;

public class SorterAnalyserFilterChain extends SorterAnalyzer{

    private LinkedHashSet<Filter<AnalysisResult>> filters;
    private RunFilter runFilter = new RunFilter();

    public SorterAnalyserFilterChain(LinkedHashSet<Filter<AnalysisResult>> filters) {
        filters.add(runFilter);
        this.filters = filters;
    }

    public SorterAnalyserFilterChain(FillStrategy strategy, LinkedHashSet<Filter<AnalysisResult>> filters) {
        super(strategy);
        filters.add(runFilter);
        this.filters = filters;
    }


    @Override
    public AnalysisResult makeAnalysis(Sorter sorter) {
        FilterChain<AnalysisResult> filterChain = new FilterChain<>(filters);
        AnalysisResult result = new AnalysisResult();
        runFilter.sorter = sorter;
        filterChain.next(result);
        return result;
    }



    private final class RunFilter implements Filter<AnalysisResult>{

        private Sorter sorter;

        @Override
        public void doFilter(AnalysisResult res, FilterChain<AnalysisResult> filterChain) {
            int[] array = fillStrategy.doFill();
            sorter.sort(array);
            res.setArraySize(array.length)
                    .setSortType(sorter.getClass().getSimpleName())
                    .setFillName(fillStrategy.getName());
        }
    }


//    public static void main(String[] args) {
//
//        int[] arr = {1, 345, 326, 4567, 56758, 34, 132, 5, 658, 34, 213};
//        FillStrategy strategy = () -> arr;
//
//        LinkedHashSet<Filter<AnalysisResult>> filters = new LinkedHashSet<>();
//
//        TestFilter filter = new TestFilter("test 1");
//        TestFilter filter2 = new TestFilter("test 2");
//        TestFilter filter3 = new TestFilter("test 3");
//
//        filters.addAll(Arrays.asList(filter, filter2, filter3));
//
//        FilterSorterAnalyser analyser = new FilterSorterAnalyser(strategy, filters);
//
//        AnalysisResult result = analyser.makeAnalysis(new FallingBubbleSorter());
//
//        System.out.println("Result: " + result);
//    }
//
//    public static class TestFilter implements Filter<AnalysisResult> {
//
//        private String print;
//
//        public TestFilter(String print) {
//            this.print = print;
//        }
//
//        @Override
//        public void doFilter(AnalysisResult result, FilterChain<AnalysisResult> filterChain) {
//            System.out.println(print + " BEFORE");
//            filterChain.next(result);
//            System.out.println(print + "AFTER");
//        }
//    }

}
