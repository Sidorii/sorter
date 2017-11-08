package com.netcracker.trainee.view.excel;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;

public class Table {

    private TableHeader tableHeader;
    private TableContent tableContent;
    private int firstRow;


    Table(String sortersCapture, String arraysCapture, List<Long> arraysLength,
          XSSFSheet sheet) {
        this(sortersCapture, arraysCapture, arraysLength, 0, 0, sheet);
    }

    Table(String sortersCapture, String arraysCapture, List<Long> arraysLength,
          int row, int col, XSSFSheet sheet) {

        this.firstRow = row;
        this.tableHeader = new TableHeader(sortersCapture, arraysCapture, arraysLength);

        int resultRow = tableHeader.draw(firstRow, col, sheet);
        this.tableContent = new TableContent(resultRow + 1, col, sheet);
    }

    public void addRow(String sorterName, List<Long> executionTimes) {
        tableContent.addRow(sorterName, executionTimes);
    }

    public CellRangeAddress getTableSize() {
        CellRangeAddress address = tableContent.getTableContentSize();
        address.setFirstRow(firstRow);

        return address;
    }

    public TableHeader getTableHeader() {
        return tableHeader;
    }

    public TableContent getTableContent() {
        return tableContent;
    }
}
