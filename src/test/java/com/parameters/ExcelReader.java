package com.parameters;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    public static String[][] readdata() {
        String[][] data = null;
        String fileName = "src\\test\\resources\\Exceldata\\InputData.xlsx";

        try (FileInputStream fis = new FileInputStream(fileName);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            // ✅ First sheet
            XSSFSheet sheet = workbook.getSheetAt(0);

            int lastRow = sheet.getLastRowNum(); 
            int colCount = sheet.getRow(0).getLastCellNum(); 
            System.out.println("Row Count (excluding header): " + lastRow);

            DataFormatter df = new DataFormatter();
            data = new String[lastRow][colCount]; 

            for (int rowNo = 1; rowNo <= lastRow; rowNo++) { 
                for (int cellNo = 0; cellNo < colCount; cellNo++) {
                    XSSFCell cell = sheet.getRow(rowNo).getCell(cellNo);
                    data[rowNo - 1][cellNo] = df.formatCellValue(cell);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}

