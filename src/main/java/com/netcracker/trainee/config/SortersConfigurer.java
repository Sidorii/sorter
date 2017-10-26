package com.netcracker.trainee.config;

import com.netcracker.trainee.sorters.Sorter;

import java.util.Set;

public interface SortersConfigurer {

    Set<Sorter> configure();
}
