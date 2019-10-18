package li.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedInLoginPage {
	WebDriver driver;
	public WebDriverWait wait;
	public LinkedInLoginPage(WebDriver driver){
		this.driver=driver;
	}

	//@FindBy(By.name("session_key")) WebElement byEmail;
	@FindBy(how=How.ID_OR_NAME, using="session_key") WebElement emailTextBox;
	@FindBy(how = How.ID_OR_NAME, using = "session_password") WebElement passwordTextBox;
	@FindBy(how = How.XPATH, using = "//button[@class='sign-in-form__submit-btn']") WebElement signInButton;
	@FindBy(how = How.ID, using = "error-for-password") WebElement errorMessage;


	public void setEmail(String userName){
		emailTextBox.sendKeys(userName);
	}

	public void setPassword(String password){
		passwordTextBox.sendKeys(password);
	}


	public void clickSignInButton(){
		//wait for element to be visible and perform click
		/*wait.until(ExpectedConditions.visibilityOfElementLocated(signInButton));
		((WebElement) signInButton).click();*/
		
		signInButton.click();
	}

	
	public void loginLinkedInApp(String userName, String password){
		setEmail(userName);
		setPassword(password);
		clickSignInButton();
	}
	
}
