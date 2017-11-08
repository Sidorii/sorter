package com.netcracker.trainee.view.excel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.awt.*;
import java.util.List;

class TableHeader {

    private String sortersCapture;
    private String arraysCapture;
    private List<Long> arraysLength;

    TableHeader(String sortersCapture,
                String arraysCapture,
                List<Long> arraysLength) {

        this.sortersCapture = sortersCapture;
        this.arraysCapture = arraysCapture;
        this.arraysLength = arraysLength;
    }

    public int draw(int xRow, int xCol, XSSFSheet sheet) {

        XSSFCellStyle headerCellStyle = createCellStyle(sheet.getWorkbook());

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
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(String.valueOf(length));
        }
        return row.getRowNum();
    }


    private XSSFCellStyle createCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle headerCellStyle = workbook.createCellStyle();

        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setWrapText(true);
        headerCellStyle.setBorderLeft(BorderStyle.MEDIUM);
        headerCellStyle.setBorderRight(BorderStyle.MEDIUM);
        headerCellStyle.setBorderBottom(BorderStyle.MEDIUM);
        headerCellStyle.setBorderTop(BorderStyle.MEDIUM);

        return headerCellStyle;
    }

    public List<Long> getArraysLength() {
        return arraysLength;
    }

    public String getArraysCapture() {
        return arraysCapture;
    }

    public String getSortersCapture() {
        return sortersCapture;
    }
}
