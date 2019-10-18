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
		driver.get("https://www.facebook.com");
		//driver.get("https://www.linkedin.com/");
	}
	
	@AfterSuite(alwaysRun=true)
	//Test cleanup
	public void TeardownTest(){
		TestBase.driver.quit();
	}
}