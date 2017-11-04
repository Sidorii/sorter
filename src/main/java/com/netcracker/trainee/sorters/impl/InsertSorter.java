package com.netcracker.trainee.sorters.impl;

import com.netcracker.trainee.sorters.SwapSorter;

/**
 * Class {@link InsertSorter} is implementation of insert sorting method.
 *
 * @see SwapSorter
 *
 * @author Ivan Sidorenko
 * @since 1.1
 * @version 1.0
 *
 * */
public class InsertSorter extends SwapSorter {

    /**
     * Proceeding sorting input array by insert sorting method (when current element puts on it ordered place in
     * sorted sequence behind current element is).
     *
     * {@inheritDoc}
     * @param array any size int type array which'll be sorted.
     *
     * @throws NullPointerException if input array is null.
     * @throws IndexOutOfBoundsException if element index is out of bounds when swap between two elements proceeding.
     * */
    public void sort(int[] array) {
        if (array == null) {
            throw new NullPointerException("Input array for sort method in InsertSort instance is null.");
        }

        int j;

        for (int i = 1; i < array.length; i++) {
            j = 0;
            while (j < i) {
                if (array[i] < array[j]) {
                    swap(array, i, j);
                    break;
                }
                j++;
            }
        }
    }

     /**
     * Sets element with index index1 in array on position index2 and move all farther elements start with index2
      * position (inclusive) to index1 position (exclusive).
     *
     * @param array array of ints which'll be modified.
     * @param index1 index of element which will be inserted on index2 position.
     * @param index2 final position of element by index1 after method proceeding.
     *
     * @throws NullPointerException will be thrown if input array is null.
     * @throws IndexOutOfBoundsException will be thrown if index1 or index2 is out of array bounds.
     * */
    @Override
    protected void swap(int[] array, int index1, int index2) {
        if (array == null) {
            throw new NullPointerException("Input array for swap method is null.");
        }

        checkIndexInBounds(array, index1);
        checkIndexInBounds(array, index2);

        int element = array[index1];

        System.arraycopy(array, index2, array, index2 + 1, index1 - index2);

        array[index2] = element;
    }
}


