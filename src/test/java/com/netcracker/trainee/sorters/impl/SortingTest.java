package com.netcracker.trainee.sorters.impl;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class SortingTest {


    int[] array;
    int[] sortedArr;
    FallingBubbleSorter fallingBubbleSorter;
    GrowingBubbleSorter growingBubbleSorter;
    MergeSorter mergeSorter;
    QuickSorter quickSorter;

    @Before
    public void setUp() {

        array = new int[]{1, 5, 3, 90, 3, 5657, 4, -4, 5, 0, 3, 5, -5};

        sortedArr = array.clone();
        Arrays.sort(sortedArr);
    }


    @Test
    public void testFallingBubbleSorting() throws Exception {

        fallingBubbleSorter = new FallingBubbleSorter();
        fallingBubbleSorter.sort(array);

        assertArrayEquals("Falling bubble sort method does not sort data", sortedArr, array);
    }

    @Test
    public void testGrowingBubbleSorting() throws Exception {

        growingBubbleSorter = new GrowingBubbleSorter();
        growingBubbleSorter.sort(array);

        assertArrayEquals("Growing bubble sort method does not sort data", sortedArr, array);
    }

    @Test
    public void testMergeSorting() throws Exception {

        mergeSorter = new MergeSorter();
        mergeSorter.sort(array);

        assertArrayEquals("Merge sort method does not sort data", sortedArr, array);
    }

    @Test
    public void testQuickSorting() {
        quickSorter = new QuickSorter();
        quickSorter.sort(array);
        assertArrayEquals(sortedArr, array);
    }
}