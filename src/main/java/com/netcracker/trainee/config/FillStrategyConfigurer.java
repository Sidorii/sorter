package com.netcracker.trainee.config;

import com.netcracker.trainee.fillers.FillStrategy;

import java.util.Set;

/**
 * Interface for configuring {@link Set set of fill strategies} from xml file.
 *
 * @author Ivan Sidorenko
 * @version 1.0
 * @since 1.0
 */
public interface FillStrategyConfigurer {

    /**
     * Configure variety of {@link FillStrategy filling strategies} according to
     * implemented configuration.
     */
    Set<? extends FillStrategy> configure();
}
