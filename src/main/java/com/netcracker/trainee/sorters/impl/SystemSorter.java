package com.netcracker.trainee.sorters.impl;

import com.netcracker.trainee.sorters.Sorter;

import java.util.Arrays;

/**
 * Class {@link SystemSorter} is implementation of Java system sorting method.
 *
 * @author Ivan Sidorenko
 * @version 1.0
 * @see Arrays#sort(int[])
 * @see Sorter
 * @since 1.0
 */
public class SystemSorter extends Sorter {

    private static final SystemSorter INSTANCE = new SystemSorter();

    private SystemSorter() {
    }

    public static final SystemSorter getInstance() {
        return INSTANCE;
    }

    /**
     * Provide <b>array</b> sorting by {@code Arrays.sort(int[])} method.
     *
     * @see Arrays#sort(int[])
     */
    @Override
    public void makeSorting(int[] array) {
        Arrays.sort(array);
    }
}
