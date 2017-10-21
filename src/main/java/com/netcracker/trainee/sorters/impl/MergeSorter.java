package com.netcracker.trainee.sorters.impl;

import com.netcracker.trainee.sorters.Sorter;

import java.util.Arrays;

public class MergeSorter extends Sorter {

    public void sort(int[] array) {
        processing(array,0,array.length-1);
    }

    private void processing(int[] array, int low, int high) {

        if (low < high) {

            int middle = low + (high - low) / 2;
            processing(array, low, middle);
            processing(array, middle + 1, high);
            merge(array,low,middle,high);
        }
    }

    private void merge(int[] array, int low, int mid, int high) {

        int i = low;
        int j = mid+1;
        int counter = low;

        int helper[] = Arrays.copyOf(array, array.length);

        while (i <= mid && j <= high) {
            if (helper[i] <= helper[j]) {
                array[counter++] = helper[i++];
            }else {
                array[counter++] = helper[j++];
            }
        }

        while (i <= mid) {
            array[counter++] = helper[i++];
        }
    }

}
