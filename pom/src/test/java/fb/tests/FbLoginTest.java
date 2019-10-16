package fb.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import fb.pages.FbHomePage;
import fb.pages.FbLoginPage;
public class FbLoginTest extends TestBase{
	@Test
	public void init() throws Exception{
		//driver.get("https://www.facebook.com");
		FbLoginPage loginpage = PageFactory.initElements(driver, FbLoginPage.class);
		loginpage.setEmail("irfan27dec@yhaoo.co.in");
		loginpage.setPassword("MIA@27dec");
		loginpage.clickOnLoginButton();
		FbHomePage homepage = PageFactory.initElements(driver, FbHomePage.class);
		driver.navigate().refresh();
		homepage.clickOnProfileDropdown();
		//homepage.verifyLoggedInUserNameText();
		homepage.clickOnLogoutLink();
	}
}
