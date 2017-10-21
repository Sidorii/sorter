package com.netcracker.trainee.sorters.impl;

import com.netcracker.trainee.sorters.SwapSorter;

public class InsertSorter extends SwapSorter {


    public void sort(int[] array) {
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

    @Override
    protected void swap(int[] array, int index1, int index2) {
        int element = array[index1];

        System.arraycopy(array, index2, array, index2 + 1, index1 - index2);

        array[index2] = element;
    }
}


