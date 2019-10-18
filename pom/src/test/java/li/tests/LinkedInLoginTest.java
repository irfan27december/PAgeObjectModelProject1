package li.tests;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import fb.tests.TestBase;
import li.pages.LinkedInLoginPage;
import utilities.ReadExcel;

public class LinkedInLoginTest extends TestBase{

	@Test(dataProvider = "getExcelData")
	public void VerifyInvalidLogin(String userName, String password){
		LinkedInLoginPage loginPage = PageFactory.initElements(driver, LinkedInLoginPage.class);
		loginPage.loginLinkedInApp(userName, password);
	}
	
	@DataProvider(name="getExcelData")
	public String[][] getExcelData() throws InvalidFormatException, IOException{
	ReadExcel read = new ReadExcel();
	return read.getCellData("testdata/MyFirstExcel.xlsx", "Datatypes in Java");
	}

}
