package com.netcracker.trainee.config;

import com.netcracker.trainee.analyzer.Experiment;
import com.netcracker.trainee.analyzer.SorterAnalyzer;
import com.netcracker.trainee.config.impl.StdFillStrategyConfigurer;
import com.netcracker.trainee.config.xml.XStreamXmlParser;
import com.netcracker.trainee.config.xml.XmlParser;
import com.netcracker.trainee.config.xml.entities.*;
import com.netcracker.trainee.fillers.FillStrategy;
import com.netcracker.trainee.sorters.Sorter;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ExperimentConfigurer {

    private XmlParser xmlParser;
    private String xmlPath;

    public ExperimentConfigurer(String xmlPath) {
        this(new XStreamXmlParser(), xmlPath);
    }

    public ExperimentConfigurer(XmlParser parser, String xmlPath) {
        this.xmlPath = xmlPath;
        this.xmlParser = parser;
    }


    public Experiment createExperiment() {
        Set<? extends FillStrategy> fillStrategies;
        Set<? extends Sorter> sorters;
        Set<? extends SorterAnalyzer> analyzers;
        try {
            XmlFile xmlFile = xmlParser.parseXml(xmlPath);
            XmlExperiment experiment = xmlFile.getExperiment();
            XmlFillStrategy xmlFillStrategy = experiment.getFillStrategy();
            sorters = experiment.getSorters().getSorters();

            if (sorters == null || sorters.isEmpty()) {
                throw new IllegalStateException("No such sorters found for experiment. Check your sorters search config.");
            }

            fillStrategies = new StdFillStrategyConfigurer(xmlFile.getBasePackage()).configure(xmlFillStrategy);

            if (fillStrategies == null || fillStrategies.isEmpty()) {
                throw new IllegalStateException("No such fill strategies found for experiment." +
                        " Check your fill strategy search config.");
            }

            analyzers = bindAnalysers(experiment.getAnalyzers());

            if (analyzers == null || analyzers.isEmpty()) {
                throw new IllegalStateException("No such analysers found for experiment." +
                        " Check your analysers search config.");
            }

            return new Experiment(fillStrategies, sorters, analyzers);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Experiment creation failed");
        }
    }


    private Set<? extends SorterAnalyzer> bindAnalysers(Collection<XmlAnalyser> analysers) {

        Set<SorterAnalyzer> result = new HashSet<>();

        analysers.forEach((a) -> {
            try {
                SorterAnalyzer analyzer = a.getClazz().newInstance();
                result.add(analyzer);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("Sorter analyser instantiation from xml failed. " +
                        "Check if class has at least default access, has empty constructor");
            }
        });
        return result;
    }
}
