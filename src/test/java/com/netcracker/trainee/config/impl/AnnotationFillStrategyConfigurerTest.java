package com.netcracker.trainee.config.impl;

import com.netcracker.trainee.config.xml.entities.XmlFillStrategy;
import com.netcracker.trainee.fillers.FillStrategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;
import java.util.Set;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class AnnotationFillStrategyConfigurerTest {

    private AnnotationFillStrategyConfigurer configurer;
    private XmlFillStrategy fillStrategy;

    @Before
    public void setUp() {
        fillStrategy = createMock(XmlFillStrategy.class);
    }

    @Test
    public void testNormalConfiguring() {
        configurer = new AnnotationFillStrategyConfigurer("com.netcracker.trainee.fillers", fillStrategy);

        Properties fillerMethodArguments = new Properties();
        fillerMethodArguments.setProperty("max", "10");
        fillerMethodArguments.setProperty("length", "10");
        fillerMethodArguments.setProperty("tile", "10");

        expect(fillStrategy.getProperties())
                .andReturn(new Properties[]{fillerMethodArguments})
                .anyTimes();

        replay(fillStrategy);

        Set<FillStrategy> result = configurer.configure();
        assertTrue(result.size() > 0);
    }

    @Test
    public void testWithoutPropertiesSet() {
        XmlFillStrategy strategy = new XmlFillStrategy();
        strategy.setAnnotationCfg(true);

        configurer = new AnnotationFillStrategyConfigurer("com.netcracker.trainee.fillers",
                strategy);

        replay(fillStrategy);

        Set<FillStrategy> strategies = configurer.configure();
        assertEquals(0, strategies.size());
    }

    @Test
    public void testNullBasePackage() {
        XmlFillStrategy strategy = new XmlFillStrategy();
        strategy.setAnnotationCfg(true);

        configurer = new AnnotationFillStrategyConfigurer(null,
                strategy);

        replay(fillStrategy);

        assertEquals(0, configurer.configure().size());
    }

    @After
    public void tearDown() {
        verify(fillStrategy);
    }

}