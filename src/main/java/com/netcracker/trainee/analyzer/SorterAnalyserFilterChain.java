package com.netcracker.trainee.analyzer;

import com.netcracker.trainee.analyzer.filter.base.Filter;
import com.netcracker.trainee.analyzer.filter.base.FilterChain;
import com.netcracker.trainee.fillers.FillStrategy;
import com.netcracker.trainee.sorters.Sorter;

import java.util.LinkedHashSet;

/**
 * Class extends {@link SorterAnalyzer} abstract class implements
 * {@link SorterAnalyzer#makeAnalysis(Sorter)} make analysis} method
 * with using filter chain as sorting analyser definitely. It uses
 * filters as chain of responsibility pattern units for analysing sorting
 * algorithm.
 *
 * @author Ivan Sidorenko
 * @version 1.0
 * @see Filter
 * @see FilterChain
 * @see FillStrategy
 * @see SorterAnalyzer
 * @see AnalysisResult
 * @see RunFilter
 * @since 1.0
 */
public class SorterAnalyserFilterChain extends SorterAnalyzer {

    private LinkedHashSet<Filter<AnalysisResult>> filters;
    private final RunFilter runFilter = new RunFilter();

    /**
     * Takes linked set of {@link Filter filters} for future applying them
     * during sorting analysing. Add own {@link RunFilter filter} that
     * in that case become last filter in filters chain.
     *
     * @param filters linked set of filters for sorting exploring
     */
    public SorterAnalyserFilterChain(LinkedHashSet<Filter<AnalysisResult>> filters) {
        filters.add(runFilter);
        this.filters = filters;
    }

    /**
     * Takes {@link FillStrategy} for array filling and linked filters set for making sorting analysing
     *
     * @param filters  linked set of filters for sorting exploring
     * @param strategy array initialization strategy
     */
    public SorterAnalyserFilterChain(FillStrategy strategy, LinkedHashSet<Filter<AnalysisResult>> filters) {
        super(strategy);
        filters.add(runFilter);
        this.filters = filters;
    }

    /**
     * Method make analysis for current {@link Sorter} class passed as
     * method argument and return {@link AnalysisResult result} witch
     * save sorting processing description.
     * Current method implementation use {@link FilterChain filter chain}
     * for analysis processing via set of filters witch may put required
     * values in result or note some information, for example.
     *
     * @param sorter Object that encapsulate sorting algorithm implementation.
     *               This is the instance researching in analysis.
     * @return Instance of {@link AnalysisResult} class witch contains
     * fully description of analysis processing depending on filters types
     * that used in analysis.
     * @see RunFilter
     * @see FilterChain
     */
    @Override
    public AnalysisResult makeAnalysis(Sorter sorter) {
        FilterChain<AnalysisResult> filterChain = new FilterChain<>(filters);
        AnalysisResult result = new AnalysisResult();
        runFilter.sorter = sorter;
        filterChain.next(result);
        return result;
    }

    /**
     * This filter is a stub in {@link FilterChain filter chain} and execute last
     * in whole execution chain.<br>
     * This class has private access because it assuming using only in supper class
     * scope and replacing directly calling of sorting on delegating this role to this
     * filter.<br>
     * {@link RunFilter} runs array sorting directly and put the most closed information
     * to execution in {@link AnalysisResult result} object (for example: <b>sorting type
     * name</b>, <b>sorting array length</b> and etc.).
     *
     * @author Ivan Sidorenko
     * @version 1.0
     * @see Filter
     * @see FilterChain
     * @see Sorter
     * @see AnalysisResult
     * @see <a href= "https://en.wikipedia.org/wiki/Chain-of-responsibility_pattern">Chain of responsibility</a>
     * @since 1.0
     */
    private final class RunFilter implements Filter<AnalysisResult> {

        private Sorter sorter;

        /**
         * This method do the most valuable actions for sorting method analysing.
         * It fill array witch sorting the next step.
         * Call sorting method for {@link Sorter} class.
         * Fill {@link AnalysisResult} the most closer information about sorting execution.
         *
         * @param res         Sorting execution result. In the out of method contains filled fields
         *                    about array size witch was sorted, name of {@link Sorter} class
         *                    and name of fill array strategy.
         * @param filterChain is the chain of filters that need for calling the very next filter
         *                    in chain. Each filter has no knowledge about any other filters in
         *                    chain, it can only call the next filter without any knowledge
         *                    what this filters is it.
         * @see Filter
         * @see FilterChain
         * @see AnalysisResult
         * @see FillStrategy
         */
        @Override
        public void doFilter(AnalysisResult res, FilterChain<AnalysisResult> filterChain) {
            int[] array = fillStrategy.doFill();
            sorter.sort(array);
            res.setArraySize(array.length)
                    .setSortType(sorter.getClass().getSimpleName())
                    .setFillName(fillStrategy.getName());
        }
    }
}
