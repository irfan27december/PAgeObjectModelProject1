package utilities_someissue;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    private static XSSFWorkbook excelWBook;
    private static XSSFSheet excelWSheet;
    private static XSSFRow excelRow;
    
    /*
     * Set the File path, open Excel file
     * @params - Excel Path and Sheet Name
     */
    public static void setExcelFile(String path, String sheetName) throws Exception {
        try {
            // Open the Excel file
            FileInputStream excelFile = new FileInputStream(path);

            // Access the excel data sheet
            excelWBook = new XSSFWorkbook(excelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
        } catch (Exception e) {
            throw (e);
        }
    }

    public static String[][] getTestData(String filePath, String sheetName) throws IOException {
    	FileInputStream excelFile = new FileInputStream(filePath);

        // Access the excel data sheet
        excelWBook = new XSSFWorkbook(excelFile);
        excelWSheet = excelWBook.getSheet(sheetName);
        
        System.out.println("File path "+ filePath+ "   "+ " Sheet name   "+sheetName );
        String[][] testData = null;

        try {
            // Handle numbers and strings
            DataFormatter formatter = new DataFormatter();
            /*XSSFCell[] boundaryCells = findCells(tableName);
            XSSFCell startCell = boundaryCells[0];            
            XSSFCell endCell = boundaryCells[1];            
            int startRow = startCell.getRowIndex() + 1;
            int endRow = endCell.getRowIndex() - 1;
            int startCol = startCell.getColumnIndex() + 1;
            int endCol = endCell.getColumnIndex() - 1;

            testData = new String[endRow - startRow + 1][endCol - startCol + 1];*/
            excelRow = excelWSheet.getRow(0);  //get my Row which start from 0   

    		int RowNum = excelWSheet.getPhysicalNumberOfRows();// count my number of Rows
    		int ColNum= excelRow.getLastCellNum(); // get last ColNum 
            
            System.out.println("Total rows   "+RowNum+ "  Col num  "+ColNum);
            
            for (int i=1; i<(RowNum-1); i++) {
            	//System.out.println("Inside for loop: "+RowNum);
            	excelRow = excelWSheet.getRow(i+1);
            	System.out.println("Inside for loop: "+excelRow);
            	for (int j=0; j<ColNum; j++) {
                    // testData[i-startRow][j-startCol] = ExcelWSheet.getRow(i).getCell(j).getStringCellValue();
                    Cell cell = excelWSheet.getRow(i).getCell(j);
                    testData[i][j] = formatter.formatCellValue(cell);
                    System.out.println("Value is   "+ formatter.formatCellValue(cell));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testData;
    }

    public static XSSFCell[] findCells(String tableName) {
        DataFormatter formatter = new DataFormatter();
        String pos = "begin";
        XSSFCell[] cells = new XSSFCell[2];

        for (Row row : excelWSheet) {
            for (Cell cell : row) {
                // if (tableName.equals(cell.getStringCellValue())) {
                if (tableName.equals(formatter.formatCellValue(cell))) {
                    if (pos.equalsIgnoreCase("begin")) {
                        cells[0] = (XSSFCell) cell;
                        pos = "end";
                    } else {
                        cells[1] = (XSSFCell) cell;
                    }
                }
            }
        }
        return cells;
    }
    
    
	public static void main(String args[]) throws IOException{
		//getExcelData(FILE_NAME,"Datatypes in Java");
		ExcelUtility excelutility = new ExcelUtility();
		excelutility.getTestData(ExcelConstants.File_Name, ExcelConstants.SheetName);
	}



    
}