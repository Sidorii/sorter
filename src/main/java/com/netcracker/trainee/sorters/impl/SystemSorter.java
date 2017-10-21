package com.netcracker.trainee.sorters.impl;

import com.netcracker.trainee.sorters.Sorter;

import java.util.Arrays;

public class SystemSorter extends Sorter {

    @Override
    public void sort(int[] array) {
        Arrays.sort(array);
    }
}
