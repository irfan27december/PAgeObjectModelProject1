package fb.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FbHomePage {
	WebDriver driver;
	public FbHomePage(WebDriver driver){
		this.driver=driver;
	}
	//Using FindBy for locating elements
	@FindBy(how=How.XPATH, using="//div[@id='userNavigationLabel']") WebElement profileDropdown;
	//@FindBy(how=How.XPATH, using="//text()[.='Log Out']/ancestor::span[1]") WebElement logoutLink;
	@FindBy(how=How.XPATH, using="//span[contains(text(),'Log Out')]") WebElement logoutLink;
	@FindBy(how=How.XPATH, using="//div[@id='userNavigationLabel']") WebElement userNavigationLink;



	// Defining all the user actions (Methods) that can be performed in the Facebook home page
	// This method to click on Profile Dropdown
	public void clickOnProfileDropdown(){
		profileDropdown.click();
	}


	// This method to click on User Navigation menu/dropdown
	public void clickUserNavigationLink(){
		userNavigationLink.click();
	}

	// This method to click on Logout link
	public void clickOnLogoutLink(){		
		logoutLink.click();
	}
}