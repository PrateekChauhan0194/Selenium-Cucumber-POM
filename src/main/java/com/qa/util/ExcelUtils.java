package com.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    private XSSFWorkbook workbook = null;
    private File file = null;
    private FileInputStream fis = null;

    public XSSFWorkbook getWorkbook(String workbookPath) {
        try {
        	file = new File(workbookPath);
        	fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    public XSSFSheet getSheetByName(String workbookPath, String sheetName) {
        return getWorkbook(workbookPath).getSheet(sheetName);
    }

    public XSSFSheet getSheetByIndex(String workbookPath, int sheetIndex) {
        return getWorkbook(workbookPath).getSheetAt(sheetIndex);
    }
    
    public String getCellValueByIndex(Row row, int index) {
		return row.getCell(index).getStringCellValue();
    }
}
