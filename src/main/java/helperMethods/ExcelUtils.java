package helperMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import config.Constants;

public class ExcelUtils {

	public static List<String> getExcelCellValue(String filePath, String columnHeader) throws IOException {
		List<String> cellValues;
//		FileInputStream file = new FileInputStream(Constants.PROJECT_PATH + filePath + ".xlsx");
		FileInputStream file = new FileInputStream(filePath);
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheetAt(0);
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		Row row = sheet.getRow(0);
		int count = 0;
		int nameColumn = 0;
		for (; count < row.getLastCellNum(); count++) {
			if (row.getCell(count).toString().equals(columnHeader)) {
				nameColumn = count;
				break;
			}
		}
		cellValues = new ArrayList<String>();
		for (int i = 1; i < rowCount + 1; i++) {
			String value = sheet.getRow(i).getCell(nameColumn).getStringCellValue();
			cellValues.add(value);
		}
		workbook.close();
		return cellValues;
	}

	public static String getTetsCaseFlag(String inputTestcaseName,String filePath) throws IOException {
		String cellValue=null;
		FileInputStream file = new FileInputStream(filePath);
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheetAt(0);
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		int count = 0;
		int testcasename = 0;
		for (; count < rowCount+1; count++) {
			if (sheet.getRow(count).getCell(0).toString().equals(inputTestcaseName)) {
				testcasename = count;								
			}
		}			
		cellValue = sheet.getRow(testcasename).getCell(1).toString();							
		workbook.close();		
		return cellValue;
	}
	
	public static void readExcel(String filePath,String fileName,String sheetName) throws IOException{

	    File file =    new File(filePath+"\\"+fileName);
	    FileInputStream inputStream = new FileInputStream(file);
	    Workbook book = null;
	    String fileExtensionName = fileName.substring(fileName.indexOf("."));
	    if(fileExtensionName.equals(".xlsx")){
	    	book = new XSSFWorkbook(inputStream);
	    }

	    else if(fileExtensionName.equals(".xls")){
	    	book = new HSSFWorkbook(inputStream);
	    }
	    Sheet sheet = book.getSheet(sheetName);

	    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();

	    for (int i = 0; i < rowCount+1; i++) {

	        Row row = sheet.getRow(i);

	        for (int j = 0; j < row.getLastCellNum(); j++) {

	            System.out.print(row.getCell(j).getStringCellValue()+"|| ");

	        }

	        System.out.println();
	    } 

	    }  

	    //Main function is calling readExcel function to read data from excel file

	   
	
	
}
