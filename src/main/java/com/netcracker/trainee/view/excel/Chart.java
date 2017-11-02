package com.netcracker.trainee.view.excel;

import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.charts.XSSFChartLegend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chart {

    private int seriesCount;
    private ChartDataSource<Long> oX;
    private XSSFDrawing drawing;
    private Map<String, ChartDataSource<Long>> dataSourcesBuffer;


    public Chart(List<Long> title, XSSFSheet sheet) {
        seriesCount = 0;
        dataSourcesBuffer = new HashMap<>();
        drawing = sheet.createDrawingPatriarch();
        oX = DataSources.fromArray(title.toArray(new Long[title.size()]));
    }


    public void addSeries(String name, List<Long> values) {
        ChartDataSource<Long> row = DataSources.fromArray(values.toArray(new Long[values.size()]));
        dataSourcesBuffer.put(name, row);
        seriesCount++;
    }


    public void plot(int row, int col) {
        XSSFClientAnchor anchor = drawing.
                createAnchor(0, 0, 0, 0, col, row, col + seriesCount, row + 2 * seriesCount);

        XSSFChart chart = drawing.createChart(anchor);

        XSSFChartLegend legend = chart.getOrCreateLegend();
        legend.setPosition(LegendPosition.BOTTOM);

        LineChartData chartData = chart.getChartDataFactory().createLineChartData();
        for (Map.Entry<String, ChartDataSource<Long>> ds : dataSourcesBuffer.entrySet()) {
            LineChartSeries series = chartData.addSeries(oX, ds.getValue());
            series.setTitle(ds.getKey());
        }

        ChartAxis bottomAxis = chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        chart.plot(chartData, bottomAxis, leftAxis);
    }
}
