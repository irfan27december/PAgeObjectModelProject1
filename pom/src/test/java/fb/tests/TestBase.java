package fb.tests;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
public class TestBase {
	public static WebDriver driver = null;
	
	@BeforeSuite
	public void initialize() throws IOException{
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		//To maximize browser
		driver.manage().window().maximize();
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//To open facebook
		driver.get("https://www.facebook.com");
	}
	
	@AfterSuite
	//Test cleanup
	public void TeardownTest(){
		TestBase.driver.quit();
	}
}