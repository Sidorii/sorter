package com.netcracker.trainee.config;

import com.netcracker.trainee.config.xml.entities.XmlFillStrategy;
import com.netcracker.trainee.fillers.FillStrategy;

import java.util.Set;

/**
 * Interface for configuring {@link Set set of fill strategies} from xml file.
 *
 * @author Ivan Sidorenko
 * @since 1.0
 * @version 1.0
 * */
public interface FillStrategyConfigurer {

    /**
     * Configure variety of {@link FillStrategy filling strategies} from {@link XmlFillStrategy} instance.
     *
     * @param fillStrategy POJO class which contains information for configuring set of {@link FillStrategy
     * fill strategies} from xml file.
     * */
    Set<? extends FillStrategy> configure(XmlFillStrategy fillStrategy);
}
