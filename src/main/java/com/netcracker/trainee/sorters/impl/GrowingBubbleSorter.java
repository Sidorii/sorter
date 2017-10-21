package com.netcracker.trainee.sorters.impl;

import com.netcracker.trainee.sorters.SwapSorter;

public class GrowingBubbleSorter extends SwapSorter {


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
