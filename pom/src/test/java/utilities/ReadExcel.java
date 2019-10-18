package utilities;


import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import utilities_someissue.ExcelConstants;
public class ReadExcel {
	public String[][] getCellData(String path, String sheetName) throws InvalidFormatException, IOException {
		path = "C:\\Users\\irfan\\git\\PageObjectModelProject1\\pom\\testdata\\MyFirstExcel.xlsx";
		FileInputStream stream = new FileInputStream(path);
		Workbook workbook = WorkbookFactory.create(stream);
		Sheet s = workbook.getSheet("Datatypes in Java");
		int rowcount = s.getLastRowNum();
		int cellcount = s.getRow(0).getLastCellNum();
		String data[][] = new String[rowcount][cellcount];
		for (int i = 1; i <= rowcount; i++) {
			Row r = s.getRow(i);
			for (int j = 0; j < cellcount; j++) {
				Cell c = r.getCell(j);
				try {
					if (c.getCellType() == c.getCellType()) {
						data[i - 1][j] = c.getStringCellValue();
						System.out.println(" Cell value "+data[i - 1][j] );
					} 
					else
					{
						data[i - 1][j] = String.valueOf(c.getNumericCellValue());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}
	
	public static void main(String args[]) throws InvalidFormatException, IOException{
		ReadExcel readexcel = new ReadExcel();
		readexcel.getCellData(ExcelConstants.File_Name,ExcelConstants.SheetName);
	}
}