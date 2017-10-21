package com.netcracker.trainee.sorters;

abstract public class SwapSorter extends Sorter {

    private int buff;

    public abstract void sort(int[] array);

    protected void swap(int[] array, int index1, int index2) {
        buff = array[index1];
        array[index1] = array[index2];
        array[index2] = buff;
    }
}
