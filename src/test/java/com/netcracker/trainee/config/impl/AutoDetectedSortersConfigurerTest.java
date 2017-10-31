package com.netcracker.trainee.config.impl;

import com.netcracker.trainee.sorters.Sorter;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AutoDetectedSortersConfigurerTest {

    AutoDetectedSortersConfigurer configurer = null;

    @Test
    public void testConfiguration() {
        configurer = new AutoDetectedSortersConfigurer(TestClass.class, "");

        Set<Sorter> result = configurer.configure();

        assertEquals(1, result.size());
        assertTrue(result.toArray()[0].getClass().equals(TestClass2.class));
    }




    public static class TestClass extends Sorter {

        public TestClass() {}

        @Override
        public void sort(int[] array) {
            // some operation going here. In out case is should be sorting
        }

    }


    public static class TestClass2 extends TestClass {

        // another Class for test case


        @Override
        public boolean equals(Object obj) {
            return getClass().equals(obj.getClass());
        }
    }

}