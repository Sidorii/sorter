package com.netcracker.trainee.config.impl;

import com.netcracker.trainee.sorters.Sorter;
import com.netcracker.trainee.sorters.SwapSorter;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AutoDetectedSortersConfigurerTest {

    @Test
    public void testConfiguration() {
        AutoDetectedSortersConfigurer configurer =
                new AutoDetectedSortersConfigurer(SwapSorter.class, "com.netcracker.trainee.sorters");

        Set<Sorter> sorters = configurer.configure();

        assertEquals(4, sorters.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullConfig() {
        AutoDetectedSortersConfigurer configurer =
                new AutoDetectedSortersConfigurer(null, null);
        configurer.configure();
    }

    @Test
    public void testEmptyResultIfNothingFound() {
        AutoDetectedSortersConfigurer configurer =
                new AutoDetectedSortersConfigurer(Sorter.class, "not existing package");
        Set<Sorter> result = configurer.configure();

        assertEquals(0,result.size());
    }
}