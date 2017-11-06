package com.netcracker.trainee.sorters;


/**
 * Class {@code {@link Sorter}} is universal abstract interface for sorting algorithms implementation.
 * It has one method
 * {@code void sort(int[])}
 * for sorting array of {@code int} type
 *
 * @author Ivan Sidorenko
 * @since 1.0
 * @version 1.0
 */
abstract public class Sorter {

    /**
     * This method is used for sorting {@code int} type array that passes in method parameter.
     * Sorting algorithm is depending on {@code {@link Sorter}} class implementation.
     *
     * @param array is any size array of int type values.
     * */
    abstract public void sort(int[] array);
}
