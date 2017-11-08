package com.netcracker.trainee.config.xml;

import com.netcracker.trainee.analyzer.AnalysisResult;
import com.netcracker.trainee.analyzer.Experiment;
import com.netcracker.trainee.analyzer.SorterAnalyserFilterChain;
import com.netcracker.trainee.analyzer.SorterAnalyzer;
import com.netcracker.trainee.analyzer.filter.base.Filter;
import com.netcracker.trainee.config.ExperimentConfigurer;
import com.netcracker.trainee.config.impl.StdFillStrategyConfigurer;
import com.netcracker.trainee.config.xml.entities.XmlFile;
import com.netcracker.trainee.config.xml.entities.XmlFilter;
import com.netcracker.trainee.fillers.FillStrategy;
import com.netcracker.trainee.sorters.Sorter;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * {@inheritDoc}
 * Child of {@link ExperimentConfigurer} class. Configure {@link Experiment} based on xml file.
 *
 * @author Ivan Sidorenko
 * @version 1.0
 * @see XmlParser
 * @see Experiment
 * @see SorterAnalyzer
 * @since 1.0
 */
public class XmlExperimentConfigurer implements ExperimentConfigurer {

    private XmlParser xmlParser;
    private String xmlPath;

    public XmlExperimentConfigurer(String xmlPath) {
        this(new XStreamXmlParser(), xmlPath);
    }

    /**
     * Takes {@link XmlParser} as xml file parser witch exists within <b>xmlPath</b>
     */
    public XmlExperimentConfigurer(XmlParser parser, String xmlPath) {
        this.xmlPath = xmlPath;
        this.xmlParser = parser;
    }

    /**
     * {@inheritDoc}
     * Create {@link Experiment} based on xml file.
     *
     * @throws IllegalStateException if sorters or fill strategies not found in
     *                               xml configuration file (or even does not described strategy of creation
     *                               these entities that are required for {@link Experiment} configuring).

     * @throws RuntimeException      if xml config file not found.
     * @see AnalysisResult
     * @see Experiment
     * @see FillStrategy
     * @see com.netcracker.trainee.config.SortersConfigurer
     * @see StdFillStrategyConfigurer
     * @see XmlFile
     * @see XmlParser
     */
    public Experiment createExperiment() {
        Set<? extends FillStrategy> fillStrategies;
        Set<? extends Sorter> sorters;
        SorterAnalyzer analyzer;
        XmlFile xmlFile;

        try {
            xmlFile = xmlParser.parseXml(xmlPath);
            sorters = xmlFile.getExperiment().getXmlSorters().getSorters();

            if (sorters == null || sorters.isEmpty()) {
                throw new IllegalStateException("No such sorters found for experiment. " +
                        "Check your sorters search config.");
            }

            fillStrategies = new StdFillStrategyConfigurer(xmlFile.getBasePackage(),
                    xmlFile.getExperiment().getFillStrategy())
                    .configure();

            if (fillStrategies == null || fillStrategies.isEmpty()) {
                throw new IllegalStateException("No such fill strategies found for experiment. " +
                        "Check your fill strategy search config.");
            }

            analyzer = buildSorterAnalyser(xmlFile.getExperiment().getFilters());
            return new Experiment(fillStrategies, sorters, analyzer);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Experiment creation failed", e);
        }
    }


    private SorterAnalyzer buildSorterAnalyser(Collection<XmlFilter> analysers) {

        LinkedHashSet<Filter<AnalysisResult>> result = new LinkedHashSet<>();

        analysers.forEach((a) -> {
            try {
                Filter<AnalysisResult> filter = a.getClazz().newInstance();
                result.add(filter);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("SorterAnalyser's filter instantiation from xml failed. " +
                        "Check if class has at least default access, and has empty constructor", e);
            }
        });
        return new SorterAnalyserFilterChain(result);
    }
}
