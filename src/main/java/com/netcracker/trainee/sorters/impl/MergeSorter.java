package com.netcracker.trainee.sorters.impl;

import com.netcracker.trainee.sorters.Sorter;

import java.util.Arrays;

/**
 * Class {@link MergeSorter} is implementation of merge sorting method.
 *
 * @author Ivan Sidorenko
 * @version 1.0
 * @see Sorter
 * @since 1.0
 */
public class MergeSorter extends Sorter {

    private static final MergeSorter INSTANCE = new MergeSorter();

    public static final MergeSorter getInstance() {
        return INSTANCE;
    }

    /**
     * Proceeding sorting input array by insert sorting method
     * (Proceeding sorting input array by merge sorting method (recursive sorting method when
     * array divides on a parts until parts does not become 1 element length. As new created
     * subarrays is 1 element length it makes them auto-sorted by default and then this
     * subarrays merging between each other while does not become the input sorted array again).
     * <p>
     * {@inheritDoc}
     *
     * @param array any size int type array which'll be sorted.
     * @throws NullPointerException      if input array is null.
     * @throws IndexOutOfBoundsException if element index is out of bounds when swap between
     *                                   two elements proceeding.
     */
    public void sort(int[] array) {
        processing(array, 0, array.length - 1);
    }

    private void processing(int[] array, int low, int high) {

        if (low < high) {

            int middle = low + (high - low) / 2;
            processing(array, low, middle);
            processing(array, middle + 1, high);
            merge(array, low, middle, high);
        }
    }

    private void merge(int[] array, int low, int mid, int high) {

        int i = low;
        int j = mid + 1;
        int counter = low;

        int helper[] = Arrays.copyOf(array, array.length);

        while (i <= mid && j <= high) {
            if (helper[i] <= helper[j]) {
                array[counter++] = helper[i++];
            } else {
                array[counter++] = helper[j++];
            }
        }

        while (i <= mid) {
            array[counter++] = helper[i++];
        }
    }
}
