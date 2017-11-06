package com.netcracker.trainee.config;

import com.netcracker.trainee.analyzer.Experiment;

/**
 * Interface for {@link Experiment} configuring.
 *
 * @author Ivan Sidorenko
 * @since 1.0
 * @version 1.0
 * */
public interface ExperimentConfigurer {

    /**
     * Create {@link Experiment} from configuration.
     *
     * @return {@link Experiment} instance with parameters that configured during method invocation.
     *
     * */
    Experiment createExperiment();
}
