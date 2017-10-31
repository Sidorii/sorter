package com.netcracker.trainee.fillers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Properties;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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


    @Test
    public void testPartSortedArray() throws Exception {
        expected = ArrayUtils.addAll(expected, tail);

        int[] result = FillerArrayUtil.partSortedArray(length, tail);

        assertArrayEquals(expected, result);
    }


    @Test
    public void testFallingSortedArray() throws Exception {
        ArrayUtils.reverse(expected);

        int[] result = FillerArrayUtil.fallingSortedArray(length);

        assertArrayEquals(expected,result);
    }
}