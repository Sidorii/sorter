package com.netcracker.trainee.view.excel;

import com.netcracker.trainee.analyzer.AnalysisResult;
import com.netcracker.trainee.view.Chart;
import com.netcracker.trainee.view.DrawAPI;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ExcelDrawAPI implements DrawAPI {

    private XSSFWorkbook workbook;
    private String fileName;


    public ExcelDrawAPI(String fileName) {
        workbook = new XSSFWorkbook();
        this.fileName = fileName;
    }


    @Override
    public void drawResult(Set<AnalysisResult> results) {

        Map<String, List<AnalysisResult>> res = results.stream()
                .collect(Collectors.groupingBy(AnalysisResult::getFillName));

        for (Map.Entry<String, List<AnalysisResult>> entry : res.entrySet()) {

            XSSFSheet sheet = workbook.createSheet(entry.getKey());
            sheet.setColumnWidth(0, 4000);
            sheet.setColumnWidth(1, 3000);

            Table table = new Table(2, 0, sheet);

            List<Long> arraysLength =
                    entry.getValue()
                            .stream()
                            .map(AnalysisResult::getArraySize)
                            .distinct()
                            .collect(Collectors.toList());

            Map<String, List<Long>> rows = new HashMap<>();

            for (AnalysisResult r: entry.getValue()) {
                List<Long> executionTimes = rows.computeIfAbsent(r.getSortType(), k -> new ArrayList<>(arraysLength));
                int index = arraysLength.indexOf(r.getArraySize());

                if (index == -1) {
                    throw new RuntimeException("Wrong array size in experiment");
                }
                executionTimes.set(index, r.getExecutionTime());
            }


            for (Map.Entry<String, List<Long>> row : rows.entrySet()) {
                table.addRow(row.getKey(), row.getValue());
            }

            TableHeader tableHeader = new TableHeader("Sorters types", "Arrays length",
                    arraysLength, workbook);

            tableHeader.draw(0, 0, sheet);
            Chart chart = new Chart(sheet);
            chart.buildChart(0,0,
                    arraysLength.size(),table.getTableSize());
        }
    }


    @Override
    public void flush() {
        try (FileOutputStream output = new FileOutputStream(fileName)) {
            workbook.write(output);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
