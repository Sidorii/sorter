package com.netcracker.trainee.view;

import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.charts.XSSFChartLegend;

public class Chart {

    private XSSFSheet sheet;
    private static final int SPACE = 2;


    public Chart(XSSFSheet sheet) {
        this.sheet = sheet;
    }

    public void buildChart(int fRow, int fColl, int columnCount, CellRangeAddress tableRange) {
        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        int step = tableRange.getLastRow() - tableRange.getFirstRow();
        int chartStarCell = tableRange.getLastRow() + SPACE;

        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0,
                chartStarCell, chartStarCell + step, chartStarCell + 2 * step);

        XSSFChart chart = drawing.createChart(anchor);
        XSSFChartLegend legend = chart.getOrCreateLegend();
        legend.setPosition(LegendPosition.BOTTOM);
        LineChartData chartData = chart.getChartDataFactory().createLineChartData();
        ChartAxis bottomAxis = chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        ChartDataSource<Number> xs =
                DataSources.fromNumericCellRange(sheet,
                        new CellRangeAddress(fRow,
                                fRow, fColl,
                                fColl + columnCount));

        int firstColumn = tableRange.getFirstColumn();
        ChartDataSource<Number> row;

        for (int i = tableRange.getFirstRow(); i < tableRange.getLastRow(); i++) {
            row = DataSources.fromNumericCellRange(sheet,
                    new CellRangeAddress(i, i, firstColumn, tableRange.getLastColumn()));
            chartData.addSeries(xs, row);
        }
        chart.plot(chartData, bottomAxis, leftAxis);
    }
}
