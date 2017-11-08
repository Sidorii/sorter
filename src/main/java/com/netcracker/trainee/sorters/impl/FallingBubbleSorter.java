package com.netcracker.trainee.sorters.impl;

import com.netcracker.trainee.sorters.Sorter;
import com.netcracker.trainee.sorters.SwapSorter;

/**
 * {@link FallingBubbleSorter} class is kind of bubble sorting implementation with descending
 * enumeration of elements while sorting proceeding
 *
 * @author Ivan Sidorenko
 * @version 1.0
 * @see SwapSorter
 * @see Sorter
 * @see GrowingBubbleSorter
 * @since 1.0
 */
public class FallingBubbleSorter extends SwapSorter {

    private static final FallingBubbleSorter INSTANCE = new FallingBubbleSorter();

    public static final FallingBubbleSorter getInstance() {
        return INSTANCE;
    }

    /**
     * Proceed soring input array by bubble sorting method with descending enumeration of elements.
     * <p>
     * {@inheritDoc}
     *
     * @param array any size int type array which'll be sorted.
     * @throws NullPointerException      if input array is null.
     * @throws IndexOutOfBoundsException if element index is out of bounds when swap between two elements
     *                                   proceeding.
     */
    public void sort(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = array.length - 1; j > i; j--) {

                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                }
            }
        }
    }
}
