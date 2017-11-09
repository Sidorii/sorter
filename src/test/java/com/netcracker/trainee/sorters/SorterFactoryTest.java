package com.netcracker.trainee.sorters;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class SorterFactoryTest {


    @Test(expected = NoSuchElementException.class)
    public void testPutNull() {
        SorterFactory.getSorter(null);
    }
}