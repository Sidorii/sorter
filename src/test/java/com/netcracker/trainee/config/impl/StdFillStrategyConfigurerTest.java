package com.netcracker.trainee.config.impl;

import com.netcracker.trainee.config.xml.entities.XmlFillStrategy;
import com.netcracker.trainee.fillers.FillStrategy;
import com.netcracker.trainee.fillers.FillerArrayUtil;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;


public class StdFillStrategyConfigurerTest {


    @Test
    public void testEmptyConfig() {

        StdFillStrategyConfigurer configurer = new StdFillStrategyConfigurer("");

        XmlFillStrategy fillers = new XmlFillStrategy();
        Set<? extends FillStrategy> result = configurer.configure(fillers);

        assertArrayEquals(result.toArray(), new FillStrategy[0]);
    }



    @Test
    public void testBasePackageCfgZone() {

        StdFillStrategyConfigurer configurer1 = new StdFillStrategyConfigurer("com.netcracker.trainee.fillers");
        XmlFillStrategy fillers = new XmlFillStrategy();
        fillers.setAnnotationCfg(true);

        Set<? extends FillStrategy> result = configurer1.configure(fillers);

        assertEquals(4, result.size());
    }


    @Test
    public void testAnnotationOrBaseCfgCase() {

        StdFillStrategyConfigurer configurer = new StdFillStrategyConfigurer("");

        XmlFillStrategy fillers = new XmlFillStrategy();
        fillers.setAnnotationCfg(false);

        Set<FillStrategy> strategies = new HashSet<FillStrategy>(){

            {
                add(() -> FillerArrayUtil.sortedArray(10));
                add(() -> new int[]{1,2,3,-1,-134});
            }
        };

        fillers.setStrategies(strategies);

        Set<? extends FillStrategy> result = configurer.configure(fillers);

        assertArrayEquals(strategies.toArray(), result.toArray());
        fillers.setAnnotationCfg(true);
        result = configurer.configure(fillers);

        assertEquals(4, result.size());
    }

}