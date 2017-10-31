package com.netcracker.trainee;

import com.netcracker.trainee.analyzer.Experiment;
import com.netcracker.trainee.config.ExperimentConfigurer;
import com.netcracker.trainee.config.impl.AutoDetectedSortersConfigurer;
import com.netcracker.trainee.sorters.Sorter;
import com.netcracker.trainee.view.excel.ExcelDrawAPI;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class Runner {

    public static final String BASE_PACKAGE = "com.netcracker.trainee";


    public static void main(String[] args) throws IOException {

        ExperimentConfigurer configurer = new ExperimentConfigurer("sorter.cfg.xml");

        configurer.setSortersConfigurer(new AutoDetectedSortersConfigurer(Sorter.class, BASE_PACKAGE));

        Experiment experiment = configurer.createExperiment();
        ExcelDrawAPI drawAPI = new ExcelDrawAPI("/tmp/MyFirstExcel.xlsx");

        drawAPI.drawResult(experiment.makeExperiment());

        drawAPI.flush();
    }
}
