package com.netcracker.trainee.fillers;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class FillerArrayUtilTest {

    int length;
    int tail;
    int[] expected;

    @Before
    public void setUp() {
        tail = 7;
        expected = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        length = expected.length;
    }

    @Test
    public void testSortedArray() throws Exception {
        int[] result = FillerArrayUtil.sortedArray(length);

        assertArrayEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSorterArrayNullNegativeInput() {
        FillerArrayUtil.sortedArray(-1);
    }

    @Test()
    public void testSorterArrayZeroLength() {
        int[] result = FillerArrayUtil.sortedArray(0);
        assertEquals(0, result.length);
    }

    @Test
    public void testPartSortedArray() throws Exception {
        expected = ArrayUtils.addAll(expected, tail);

        int[] result = FillerArrayUtil.partSortedArray(length, tail);

        assertArrayEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPartSortedArrayNegativeLength() {
        FillerArrayUtil.partSortedArray(-1, tail);
    }

    @Test()
    public void testPartSorterArrayZeroLength() {
        int[] result = FillerArrayUtil.partSortedArray(0, tail);
        assertEquals(1, result.length);
    }


    @Test
    public void testFallingSortedArray() throws Exception {
        ArrayUtils.reverse(expected);
        int max = 9;
        int[] result = FillerArrayUtil.fallingSortedArray(max, length);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testFallingSortedArrayMaxLessThanZero() throws Exception {
        int max = -10;
        int[] result = FillerArrayUtil.fallingSortedArray(max, length);

        int[] negativeArray = {-10, -11, -12, -13, -14, -15, -16, -17, -18, -19};
        assertArrayEquals(negativeArray, result);
    }

    @Test()
    public void testFallingSorterArrayZeroLength() {
        int[] result = FillerArrayUtil.fallingSortedArray(5, 0);
        assertEquals(0, result.length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFallingSortedArrayNegativeLength() throws Exception {
        FillerArrayUtil.fallingSortedArray(10, -1);
    }

    @Test
    public void testRandomElementsArray() {
        int[] compared = FillerArrayUtil.randomElementsArray(length);

        for (int i = 0; i < 50_000; i++) {
            int[] result = FillerArrayUtil.randomElementsArray(length);
            if (Arrays.equals(result, compared)) {
                assertTrue(false);
            }
        }
        assertTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRandomElementsArrayNegativeLength() {
        FillerArrayUtil.randomElementsArray(-1);
    }

    @Test()
    public void testRandomElementsArrayZeroLength() {
        int[] result = FillerArrayUtil.randomElementsArray(0);
        assertEquals(0, result.length);
    }
}