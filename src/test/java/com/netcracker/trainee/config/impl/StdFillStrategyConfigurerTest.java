package com.netcracker.trainee.config.impl;

import com.netcracker.trainee.config.xml.entities.XmlFillStrategy;
import com.netcracker.trainee.fillers.FillStrategy;
import com.netcracker.trainee.fillers.FillerArrayUtil;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class StdFillStrategyConfigurerTest {


    @Test
    public void testEmptyConfig() {

        XmlFillStrategy fillers = new XmlFillStrategy();
        StdFillStrategyConfigurer configurer = new StdFillStrategyConfigurer("", fillers);

        Set<? extends FillStrategy> result = configurer.configure();

        assertArrayEquals(result.toArray(), new FillStrategy[0]);
    }


    @Test
    public void testBasePackageCfgZone() {

        XmlFillStrategy fillers = new XmlFillStrategy();
        fillers.setAnnotationCfg(true);

        StdFillStrategyConfigurer configurer1 =
                new StdFillStrategyConfigurer("com.netcracker.trainee.fillers", fillers);

        Set<? extends FillStrategy> result = configurer1.configure();

        assertEquals(4, result.size());
    }


    @Test
    public void testAnnotationOrBaseCfgCase() {

        XmlFillStrategy fillers = new XmlFillStrategy();
        fillers.setAnnotationCfg(false);

        StdFillStrategyConfigurer configurer = new StdFillStrategyConfigurer("", fillers);

        Set<FillStrategy> strategies = new HashSet<FillStrategy>() {
            {
                add(() -> FillerArrayUtil.sortedArray(10));
                add(() -> new int[]{1, 2, 3, -1, -134});
            }
        };

        fillers.setStrategies(strategies);

        Set<? extends FillStrategy> result = configurer.configure();

        assertArrayEquals(strategies.toArray(), result.toArray());
        fillers.setAnnotationCfg(true);
        result = configurer.configure();

        assertEquals(4, result.size());
    }

}