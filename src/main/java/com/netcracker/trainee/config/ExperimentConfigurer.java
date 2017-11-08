package com.netcracker.trainee.config;

import com.netcracker.trainee.analyzer.Experiment;

/**
 * Used for {@link Experiment} configuring.
 *
 * @author Ivan Sidorenko
 * @version 1.0
 * @since 1.0
 */
public interface ExperimentConfigurer {

    /**
     * Create {@link Experiment} from configuration.
     *
     * @return new {@link Experiment} based on configuration witch defines
     * kind of {@link com.netcracker.trainee.fillers.FillStrategy fill strategies},
     * {@link com.netcracker.trainee.sorters.Sorter sorters} and
     * {@link com.netcracker.trainee.analyzer.SorterAnalyzer sorter analysers}
     */
    Experiment createExperiment();
}
