package com.netcracker.trainee.sorters.impl;

import com.netcracker.trainee.sorters.Sorter;

import java.util.Arrays;

/**
 * Class {@link SystemSorter} is implementation of Java system sorting method.
 *
 * @see Arrays#sort(int[])
 * @see Sorter
 *
 * @author Ivan Sidorenko
 * @since 1.0
 * @version 1.0
 *
 * */
public class SystemSorter extends Sorter {

    /**
     * Provide <b>array</b> sorting by {@code Arrays.sort(int[])} method.
     *
     * @see Arrays#sort(int[])
     *
     * */
    @Override
    public void sort(int[] array) {
        Arrays.sort(array);
    }
}
