package com.netcracker.trainee.sorters.impl;

import com.netcracker.trainee.sorters.Sorter;
import com.netcracker.trainee.sorters.SwapSorter;

/**
 * {@link GrowingBubbleSorter} class is kind of bubble sorting implementation with ascending enumeration of elements while sorting proceeding
 *
 * @see SwapSorter
 * @see Sorter
 * @see FallingBubbleSorter
 *
 * @author Ivan Sidrenko
 * @version 1.0
 * @since 1.1
 *
 * */
public class GrowingBubbleSorter extends SwapSorter {


    /**
     * Proceed soring input array by bubble sorting method with ascending enumeration of elements.
     *
     * {@inheritDoc}
     * @param array any size int type array which'll be sorted.
     *
     * @throws NullPointerException if input array is null.
     * @throws IndexOutOfBoundsException if element index is out of bounds when swap between two elements proceeding.
     * */
    public void sort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }
}
