package com.netcracker.trainee;

import com.netcracker.trainee.analyzer.Experiment;
import com.netcracker.trainee.config.ExperimentConfigurer;
import com.netcracker.trainee.config.impl.AutoDetectedSortersConfigurer;
import com.netcracker.trainee.sorters.Sorter;

import java.io.IOException;

public class Runner {

    public static final String BASE_PACKAGE = "com.netcracker.trainee";


    public static void main(String[] args) throws IOException {

        ExperimentConfigurer configurer = new ExperimentConfigurer("sorter.cfg.xml");

        configurer.setSortersConfigurer(new AutoDetectedSortersConfigurer(Sorter.class, BASE_PACKAGE));

        Experiment experiment = configurer.createExperiment();

        experiment.makeExperiment().forEach(System.out::println);
    }
}
