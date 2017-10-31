package com.netcracker.trainee.config;

import com.netcracker.trainee.analyzer.Experiment;
import com.netcracker.trainee.analyzer.SorterAnalyzer;
import com.netcracker.trainee.config.impl.StdFillStrategyConfigurer;
import com.netcracker.trainee.config.xml.XSreamXmlParser;
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
    private SortersConfigurer sortersConfigurer;
    private String xmlPath;

    public ExperimentConfigurer(String xmlPath) {
        this(new XSreamXmlParser(), xmlPath);
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
            XmlFillers fillers = xmlFile.getFillers();
            XmlExperiment experiment = xmlFile.getExperiment();


            fillStrategies = new StdFillStrategyConfigurer(xmlFile.getBasePackage()).configure(fillers);
            sorters = sortersConfigurer.configure();
            analyzers = bindAnalysers(experiment.getAnalyzers());

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


    public void setSortersConfigurer(SortersConfigurer sortersConfigurer) {
        this.sortersConfigurer = sortersConfigurer;
    }

}
