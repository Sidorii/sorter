package com.netcracker.trainee.config.xml;

import com.netcracker.trainee.analyzer.AnalysisResult;
import com.netcracker.trainee.analyzer.Experiment;
import com.netcracker.trainee.analyzer.SorterAnalyserFilterChain;
import com.netcracker.trainee.analyzer.SorterAnalyzer;
import com.netcracker.trainee.analyzer.filter.base.Filter;
import com.netcracker.trainee.config.ExperimentConfigurer;
import com.netcracker.trainee.config.impl.StdFillStrategyConfigurer;
import com.netcracker.trainee.config.xml.entities.*;
import com.netcracker.trainee.fillers.FillStrategy;
import com.netcracker.trainee.sorters.Sorter;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class XmlExperimentConfigurer implements ExperimentConfigurer {

    private XmlParser xmlParser;
    private String xmlPath;

    public XmlExperimentConfigurer(String xmlPath) {
        this(new XStreamXmlParser(), xmlPath);
    }

    public XmlExperimentConfigurer(XmlParser parser, String xmlPath) {
        this.xmlPath = xmlPath;
        this.xmlParser = parser;
    }


    public Experiment createExperiment() {
        Set<? extends FillStrategy> fillStrategies;
        Set<? extends Sorter> sorters;
        SorterAnalyzer analyzer;
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

            analyzer = buildSorterAnalyser(experiment.getFilters());


            return new Experiment(fillStrategies, sorters, analyzer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Experiment creation failed");
        }
    }


    private SorterAnalyzer buildSorterAnalyser(Collection<XmlFilter> analysers) {

        LinkedHashSet<Filter<AnalysisResult>> result = new LinkedHashSet<>();

        analysers.forEach((a) -> {
            try {
                Filter<AnalysisResult> filter = a.getClazz().newInstance();
                result.add(filter);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("SorterAnalyser's filter instantiation from xml failed. " +
                        "Check if class has at least default access, and has empty constructor");
            }
        });
        return new SorterAnalyserFilterChain(result);
    }
}
