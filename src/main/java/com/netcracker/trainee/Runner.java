package com.netcracker.trainee;

import com.netcracker.trainee.analyzer.Experiment;
import com.netcracker.trainee.config.ExperimentConfigurer;
import com.netcracker.trainee.config.xml.XmlExperimentConfigurer;
import com.netcracker.trainee.view.excel.ExcelDrawAPI;

import java.io.IOException;

public class Runner {


    //TODO: Comments are required

    public static void main(String[] args) throws IOException {

        ExperimentConfigurer configurer = new XmlExperimentConfigurer("sorter.cfg.xml");

        Experiment experiment = configurer.createExperiment();
        ExcelDrawAPI drawAPI = new ExcelDrawAPI("/tmp/MyFirstExcel.xlsx");

        drawAPI.drawResult(experiment.makeExperiment());

        drawAPI.flush();
    }
}
