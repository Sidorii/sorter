package com.netcracker.trainee.config;

import com.netcracker.trainee.sorters.Sorter;

import java.util.Set;

/**
 * Interface for configuring {@link Set sorters set} from some configuration.
 *
 * @author Ivan Sidorenko
 * @version 1.0
 * @since 1.0
 * */
public interface SortersConfigurer {

    /**
     * Configure {@link Set set} of sorters.
     *
     * @return Method returns {@link Set set} of children {@link Sorter} class.
     *
     * */
    Set<Sorter> configure();
}
