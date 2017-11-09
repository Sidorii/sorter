package com.netcracker.trainee.sorters;

import com.netcracker.trainee.utils.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.assertFalse;

public class SwapSorterTest {

    private Set<Class<? extends SwapSorter>> subclasses;

    @Before
    public void setUp() {
        subclasses = ReflectionUtils.findSubclassesForClass(SwapSorter.class,
                "com.netcracker.trainee.sorters", false);
    }

    @Test
    public void testCheckIndexArrayInBounds() {
        int[] array = new int[10];
        int index = 4;

        for (Class<? extends SwapSorter> subclass : subclasses) {
            SwapSorter swapSorter = SorterFactory.getSorter(subclass);
            swapSorter.checkIndexInBounds(array, index);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCheckIndexOutOfBoundsCase() {
        int[] array = new int[10];
        int index = 11;

        for (Class<? extends SwapSorter> subclass : subclasses) {
            SwapSorter swapSorter = SorterFactory.getSorter(subclass);
            swapSorter.checkIndexInBounds(array, index);
        }
    }

    @Test
    public void testSwapElementsArray() {
        int[] array = {2, 4, 6, 8, 10, 2, 4, 6, 8};

        for (Class<? extends SwapSorter> subclass : subclasses) {
            SwapSorter swapSorter = SorterFactory.getSorter(subclass);
            int[] result = array.clone();
            swapSorter.swap(result, 0, 8);

            assertFalse("Wrong swap result for " + swapSorter.getClass().getSimpleName(),
                    Arrays.equals(result, array));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testSwapElementsInNullArray() {
        int[] nullArray = null;

        for (Class<? extends SwapSorter> subclass : subclasses) {
            SwapSorter swapSorter = SorterFactory.getSorter(subclass);
            swapSorter.swap(nullArray, 0, 8);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSwpElementsInEmptyArray() {
        int[] emptyArray = new int[0];

        for (Class<? extends SwapSorter> subclass : subclasses) {
            SwapSorter swapSorter = SorterFactory.getSorter(subclass);
            swapSorter.swap(emptyArray, 0, 8);
        }
    }
}