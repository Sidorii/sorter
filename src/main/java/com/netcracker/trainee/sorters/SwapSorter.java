package com.netcracker.trainee.sorters;

abstract public class SwapSorter extends Sorter {

    private int buff;

    protected void swap(int[] array, int index1, int index2) {
        buff = array[index1];
        array[index1] = array[index2];
        array[index2] = buff;
    }
}
