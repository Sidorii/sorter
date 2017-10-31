package com.netcracker.trainee.view.excel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.awt.*;
import java.util.List;

class TableHeader {

    private String sortersCapture;
    private String arraysCapture;
    private List<Long> arraysLength;
    private final XSSFCellStyle headerCellStyle;
    private final XSSFWorkbook workbook;


    TableHeader(String sortersCapture,
                String arraysCapture,
                List<Long> arraysLength,
                XSSFWorkbook workbook) {

        this.sortersCapture = sortersCapture;
        this.arraysCapture = arraysCapture;
        this.arraysLength = arraysLength;
        this.workbook = workbook;

        headerCellStyle = workbook.createCellStyle();

        headerCellStyle.setAlignment(HorizontalAlignment.FILL);
        headerCellStyle.setWrapText(true);
        headerCellStyle.setBorderLeft(BorderStyle.THICK);
        headerCellStyle.setBorderRight(BorderStyle.THICK);
        headerCellStyle.setBorderBottom(BorderStyle.THICK);
        headerCellStyle.setBorderTop(BorderStyle.THICK);
        headerCellStyle.setFillBackgroundColor((short) Color.LIGHT_GRAY.getRGB());
    }

    public void draw(int xRow, int xCol, XSSFSheet sheet) {

        if (!sheet.getWorkbook().equals(workbook)) {
            throw new IllegalArgumentException("Workbook does not contain current sheet");
        }

        XSSFRow row = sheet.createRow(xRow);
        XSSFCell cell = row.createCell(xCol);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(sortersCapture);
        sheet.addMergedRegion(new CellRangeAddress(xRow, xRow + 1, xCol, xCol));


        cell = row.createCell(xCol + 1);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(arraysCapture);

        if (cell.getColumnIndex() != arraysLength.size()) {
            sheet.addMergedRegion(new CellRangeAddress(xRow, xRow, cell.getColumnIndex(), arraysLength.size()));
        }


        row = sheet.createRow(xRow + 1);
        int cellIterator = xCol + 1;

        for (Long length : arraysLength) {
            cell = row.createCell(cellIterator++);
            cell.setCellStyle(headerCellStyle);
            cell.setCellValue(String.valueOf(length));
        }
    }
}
