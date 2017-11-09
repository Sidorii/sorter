package com.netcracker.trainee.sorters;


/**
 * Class {@link Sorter} is universal abstract interface for sorting algorithms implementation.
 * It has one method {@code void sort(int[])} for sorting array of {@code int} type
 * Notice: if you create subclass from this class, you must add new instance in
 * {@link SorterFactory} factory class.
 *
 * @author Ivan Sidorenko
 * @version 1.0
 * @since 1.0
 */
abstract public class Sorter {

    /**
     * This method is used for sorting {@code int} type array that passes in method parameter.
     * Sorting algorithm is depending on {@link Sorter} class implementation.
     *
     * @param array is any size array of int type values.
     */
    public void sort(int[] array) {
        if (array != null && array.length != 0) {
            makeSorting(array);
        }
    }

    protected abstract void makeSorting(int[] array);
}
