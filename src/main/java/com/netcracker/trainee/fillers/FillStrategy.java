package com.netcracker.trainee.fillers;

import com.netcracker.trainee.analyzer.SorterAnalyzer;

/**
 * Interface {@link FillStrategy} is used for injecting filling strategy in some classes where it needed.
 * <br>For example: <br>
 * {@link com.netcracker.trainee.analyzer.SorterAnalyzer} class is used this interface for injecting way
 * of initializing array for {@link com.netcracker.trainee.sorters.Sorter} instance. It takes
 * {@link FillStrategy} as constructor-arg parameter as shown below. <br>
 * {@code protected SorterAnalyzer(FillStrategy strategy)}
 *
 * @author Ivan Sidorenko
 * @version 1.0
 * @see SorterAnalyzer
 * @since 1.0
 */
public interface FillStrategy {

    /**
     * Method which fill array by special implemented strategy in every implementer.
     *
     * @return Filled array of int primitives by defined strategy in object implementor.
     */
    int[] doFill();

    /**
     * @return The {@link String} name of filling strategy defined in implementor object.<br>
     *     Default name if another isn't set is <b>no-name</b>
     *
     * */
    default String getName() {
        return "no-name";
    }
}
