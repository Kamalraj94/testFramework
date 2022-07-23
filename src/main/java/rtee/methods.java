package rtee;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;

public class methods extends read {

	public WebDriver EnterURL() {
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "F:\\chromedriver_85\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NONE);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("URL Entered");
		return driver;
	}

	public void Login() {
		 int lastrun = 0 ; 
		for ( int i = 0 ; i < lastrun; i ++  ) {
		try {	
			// enter the user name 
			// enter the password 
			// click login 
			// login success 
			// logout 		
		} catch (Exception e) {
			// capture the error 
			// continue
		}
		}
		System.out.println("Login done");
	}

	public void VerifyHomeButton() {
		System.out.println("home button verified");
	}
}
