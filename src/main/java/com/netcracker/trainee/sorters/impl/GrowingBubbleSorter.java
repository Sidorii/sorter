package com.netcracker.trainee.sorters.impl;

import com.netcracker.trainee.sorters.Sorter;
import com.netcracker.trainee.sorters.SwapSorter;

/**
 * {@link GrowingBubbleSorter} class is kind of bubble sorting implementation with ascending
 * enumeration of elements while sorting proceeding
 *
 * @author Ivan Sidrenko
 * @version 1.0
 * @see SwapSorter
 * @see Sorter
 * @see FallingBubbleSorter
 * @since 1.0
 */
public class GrowingBubbleSorter extends SwapSorter {

    private static final GrowingBubbleSorter INSTANCE = new GrowingBubbleSorter();

    private GrowingBubbleSorter() {
    }

    public static final GrowingBubbleSorter getInstance() {
        return INSTANCE;
    }


    /**
     * Proceed soring input array by bubble sorting method with ascending enumeration of elements.
     * <p>
     * {@inheritDoc}
     *
     * @param array any size int type array which'll be sorted.
     * @throws IndexOutOfBoundsException if element index is out of bounds when swap between two elements
     *                                   proceeding.
     */
    public void makeSorting(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }
}
