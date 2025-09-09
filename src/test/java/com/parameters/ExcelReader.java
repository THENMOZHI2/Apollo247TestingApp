
package com.parameters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
public static String[][] readdata() {
		
		String[][] data = null;
		String FileName ="src\\test\\resources\\Exceldata\\InputData.xlsx";
		try {
			FileInputStream fis = new FileInputStream(FileName);
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				XSSFSheet sheet = workbook.getSheet("Sheet1");
				
				int rowCount = sheet.getLastRowNum();
				int colCount = sheet.getRow(0).getLastCellNum();
				System.out.println("Row Count "+rowCount);
				
				DataFormatter df = new DataFormatter();
				
				XSSFCell cell;
				data = new String[rowCount][colCount];
				
				for(int rowNo=1;rowNo<=rowCount;rowNo++) {
					int cellCount = sheet.getRow(rowNo).getLastCellNum();
					
					for(int cellNo=0;cellNo<cellCount;cellNo++) {
						cell=sheet.getRow(rowNo).getCell(cellNo);
						data[rowNo-1][cellNo] = df.formatCellValue(cell);
					}
				}
				
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
}