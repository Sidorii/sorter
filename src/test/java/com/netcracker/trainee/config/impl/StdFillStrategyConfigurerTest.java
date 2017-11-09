package com.netcracker.trainee.config.impl;

import com.netcracker.trainee.config.FillStrategyConfigurer;
import com.netcracker.trainee.config.xml.entities.XmlFillStrategy;
import com.netcracker.trainee.fillers.FillStrategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static  org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

public class StdFillStrategyConfigurerTest {


    private FillStrategyConfigurer annotationConfigurer;
    private StdFillStrategyConfigurer strategyConfigurer;
    private XmlFillStrategy fillStrategy;

    @Before
    public void setUp() {
        fillStrategy = createMock(XmlFillStrategy.class);

        strategyConfigurer = new StdFillStrategyConfigurer("", fillStrategy);
        annotationConfigurer = createMock(FillStrategyConfigurer.class);
        strategyConfigurer.annotationFillStrategyCfg = annotationConfigurer;
    }

    @Test
    public void testBaseConfig() {
        Set<FillStrategy> fillStrategySet = new HashSet<FillStrategy>(){
            {
                add(createMock(FillStrategy.class));
            }
        };

        expect(fillStrategy.getStrategies()).andReturn(fillStrategySet);
        expect(fillStrategy.isAnnotationCfg()).andReturn(false);

        replay(fillStrategy);

        assertEquals(fillStrategySet, strategyConfigurer.configure());
    }

    @Test
    public void testAnnotationCfg() {

        Set result = createMock(Set.class);

        expect(annotationConfigurer.configure()).andReturn(result);
        expect(fillStrategy.isAnnotationCfg()).andReturn(true);

        replay(result, annotationConfigurer, fillStrategy);

        assertEquals(result, strategyConfigurer.configure());
    }

    @After
    public void tearDown() {
        verify(fillStrategy);
    }
}