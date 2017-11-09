package com.netcracker.trainee.sorters.impl;

import com.netcracker.trainee.sorters.Sorter;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class SortingTest {


    int[] array;
    int[] sortedArr;
    private Set<Sorter> sorters;

    @Before
    public void setUp() {

        sorters = new HashSet<Sorter>(){
            {
                add(FallingBubbleSorter.getInstance());
                add(GrowingBubbleSorter.getInstance());
                add(InsertSorter.getInstance());
                add(MergeSorter.getInstance());
                add(QuickSorter.getInstance());
                add(SystemSorter.getInstance());
            }
        };

        array = new int[]{1, 5, 3, 90, 3, 5657, 4, -4, 5, 0, 3, 5, -5};

        sortedArr = array.clone();
        Arrays.sort(sortedArr);
    }

    @Test
    public void testDoesSorterSortArray() throws Exception {

        for (Sorter sorter : sorters) {
            int[] result = array.clone();
            sorter.sort(result);
            assertArrayEquals(sorter.getClass().getSimpleName() +
                    " sort method does not sort data", sortedArr, result);
        }
    }

    @Test
    public void testNullOrEmptyInput() {
        int nullArray[] = null;
        int emptyArray[] = {};

        for (Sorter s : sorters) {
            s.sort(nullArray);
        }

        for (Sorter s : sorters) {
            s.sort(emptyArray);
        }
        assertTrue(true);
    }
}