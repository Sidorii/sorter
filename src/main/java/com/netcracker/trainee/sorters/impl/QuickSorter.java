package com.netcracker.trainee.sorters.impl;

import com.netcracker.trainee.sorters.Sorter;
import com.netcracker.trainee.sorters.SwapSorter;

/**
 * {@link QuickSorter} class is quick sorting implementation.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Quicksort">Quicksort wiki</a>
 * @see SwapSorter
 * @see Sorter
 * @see GrowingBubbleSorter
 *
 * @author Ivan Sidorenko
 * @version 1.0
 * @since 1.1
 *
 * */
public class QuickSorter extends SwapSorter{


    @Override
    public void sort(int[] array) {
        processing(array, 0, array.length - 1);
    }

    private void processing(int[] array, int low, int high) {

        int mid = array[low + (high - low) / 2];

        int i = low;
        int j = high;


        while (i <= j) {

            while (array[i] < mid) {
                i++;
            }

            while (array[j] > mid) {
                j--;
            }

            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }

        if (low < j) {
            processing(array, low, j);
        }
        if (i < high) {
            processing(array, i, high);
        }
    }


}
