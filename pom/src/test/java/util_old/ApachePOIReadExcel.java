package util_old;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ApachePOIReadExcel {

	public WebDriver driver;
	public WebDriverWait wait;
	String appURL = "https://www.linkedin.com/";

	public static DataFormatter formatter= new DataFormatter();
	private static final String FILE_NAME = "C:\\Users\\irfan\\git\\PageObjectModelProject1\\pom\\testdata\\MyFirstExcel.xlsx";



	//Locators
	private By byEmail = By.name("session_key");
	private By byPassword = By.name("session_password");
	private By bySubmit = By.xpath("//button[@class='sign-in-form__submit-btn']");
	private By byError = By.id("error-for-password");

	@BeforeClass
	public void testSetup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\drivers\\chromedriver.exe");
		/*ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);*/

		//Create a map to store  preferences 
		Map<String, Object> prefs = new HashMap<String, Object>();

		//add key and value to map as follow to switch off browser notification
		//Pass the argument 1 to allow and 2 to block
		prefs.put("profile.default_content_setting_values.notifications", 2);

		prefs.put("profile.default_content_settings.popups", 0);

		//Create an instance of ChromeOptions 
		ChromeOptions options = new ChromeOptions();

		// set ExperimentalOption - prefs 
		options.setExperimentalOption("prefs", prefs);

		//Now Pass ChromeOptions instance to ChromeDriver Constructor to initialize 
		//chrome driver which will switch off this browser notification on the chrome browser
		driver = new ChromeDriver(options);



		//To maximize browser
		driver.manage().window().maximize();
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//To open facebook
		driver.get(appURL);
		wait = new WebDriverWait(driver, 5);
	}

	@AfterClass
	public void tearDown(){
	driver.quit();
	}

	/**
	 * @param File Name
	 * @param Sheet Name
	 * @return
	 */
	public static void getExcelData(String fileName, String sheetName) {
		try {
			FileInputStream excelFile = new FileInputStream(new File(fileName));
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
			XSSFSheet datatypeSheet = workbook.getSheet(sheetName);
			Iterator<Row> rowIterator = datatypeSheet.iterator();

			while (rowIterator.hasNext()) {

				Row currentRow = rowIterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();
					//getCellTypeEnum shown as deprecated for version 3.15
					//getCellTypeEnum ill be renamed to getCellType starting from version 4.0
					if (currentCell.getCellType() == CellType.STRING) {
						System.out.print(currentCell.getStringCellValue() + "--");
					} else if (currentCell.getCellType() == CellType.NUMERIC) {
						System.out.print(currentCell.getNumericCellValue() + "--");
					}

				}
				System.out.println();

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@DataProvider(name="empLogin")
	public Object[][] loginData() throws IOException {
		Object[][] arrayObject = getExcelData();//("D:/sampledoc.xls","Sheet1");
		return arrayObject;
	}

	@Test(dataProvider="empLogin")
	public void VerifyInvalidLogin(String userName, String password) {
		//driver.navigate().to(appURL);
		driver.findElement(byEmail).sendKeys(userName);
		driver.findElement(byPassword).sendKeys("mohammed");
		//wait for element to be visible and perform click
		wait.until(ExpectedConditions.visibilityOfElementLocated(bySubmit));
		driver.findElement(bySubmit).click();

		//Check for error message
		wait.until(ExpectedConditions.presenceOfElementLocated(byError));
		String actualErrorDisplayed = driver.findElement(byError).getText();
		String requiredErrorMessage = "Hmm, that's not the right password. Please try again or request a new one.";
		Assert.assertEquals(requiredErrorMessage, actualErrorDisplayed);

	}


	public static Object[][] getExcelData() throws IOException
	{
		FileInputStream fileInputStream= new FileInputStream(FILE_NAME); //Excel sheet file location get mentioned here
		XSSFWorkbook workbook = new XSSFWorkbook (fileInputStream); //get my workbook 
		XSSFSheet worksheet=workbook.getSheetAt(0);// get my sheet from workbook
		XSSFRow Row=worksheet.getRow(0);     //get my Row which start from 0   

		int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
		int ColNum= Row.getLastCellNum(); // get last ColNum 

		Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array

		for(int i=0; i<RowNum-1; i++) //Loop work for Rows
		{  
			XSSFRow row= worksheet.getRow(i+1);

			for (int j=0; j<ColNum; j++) //Loop work for colNum
			{
				if(row==null)
					Data[i][j]= "";
				else
				{
					XSSFCell cell= row.getCell(j);
					if(cell==null)
						Data[i][j]= ""; //if it get Null value it pass no data 
					else
					{
						String value=formatter.formatCellValue(cell);
						Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
					}
				}
			}
		}

		return Data;
	}







/*	public static void main(String args[]){
		getExcelData(FILE_NAME,"Datatypes in Java");
	}


*/

}
