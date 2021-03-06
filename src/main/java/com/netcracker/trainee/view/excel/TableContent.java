package com.netcracker.trainee.view.excel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;

class TableContent {

    private final int fRow;
    private final int fColl;
    private int lColl;

    private int currentRow;
    private final XSSFSheet sheet;
    private final XSSFCellStyle cellStyle;


    TableContent(int fRow, int fColl, XSSFSheet sheet) {
        this.fRow = fRow;
        this.fColl = fColl;
        this.sheet = sheet;

        currentRow = fRow;

        cellStyle = sheet.getWorkbook().createCellStyle();

        cellStyle.setWrapText(true);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
    }



    public void addRow(String sorterName, List<Long> executionTimes){
        int currentColl = fColl;

        XSSFRow row = sheet.createRow(currentRow);
        XSSFCell cell = row.createCell(currentColl);
        cell.setCellType(CellType.STRING);
        cell.setCellValue(sorterName);
        cell.setCellStyle(cellStyle);

        for (Long time : executionTimes) {
            cell = row.createCell(++currentColl);
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(time.intValue());

            cell.setCellStyle(cellStyle);
        }
        currentRow++;
        lColl = currentColl;
    }


    public CellRangeAddress getTableContentSize() {
        return new CellRangeAddress(fRow, currentRow, fColl, lColl);
    }

    public XSSFSheet getSheet() {
        return sheet;
    }
}
