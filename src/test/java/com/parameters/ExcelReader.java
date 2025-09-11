package com.parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    public static String[][] readdata() {
        String fileName = "src\\test\\resources\\Exceldata\\InputData.xlsx";
        List<String[]> rows = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(fileName);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException("Excel header row is missing");
            }

            int lastRow = sheet.getLastRowNum();
            int colCount = headerRow.getLastCellNum();
            DataFormatter df = new DataFormatter();

            for (int r = 1; r <= lastRow; r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;

                String[] rowData = new String[colCount];
                boolean hasNonEmpty = false;

                for (int c = 0; c < colCount; c++) {
                    Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String val = df.formatCellValue(cell).trim();
                    rowData[c] = val;
                    if (!val.isEmpty()) hasNonEmpty = true;
                }

                if (hasNonEmpty) rows.add(rowData);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to read Excel file: " + e.getMessage(), e);
        }

        // convert list -> array and debug-print
        String[][] data = new String[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            data[i] = rows.get(i);
        }

        System.out.println("ExcelReader: loaded rows = " + data.length);
        for (int i = 0; i < data.length; i++) {
            System.out.print("Row " + i + ": ");
            for (int j = 0; j < data[i].length; j++) {
                System.out.print("[" + j + "]=" + data[i][j] + "  ");
            }
            System.out.println();
        }

        return data;
    }
}
