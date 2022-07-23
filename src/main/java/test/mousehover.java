package test;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class mousehover {
	public WebDriver driver;

	@Test
	public void test() throws InterruptedException, AWTException {

		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "F:\\chromedriver_95\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NONE);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().to("https://uat-att.siteforge.com/");
		driver.findElement(By.id("username")).sendKeys("test.vl");
		driver.findElement(By.id("password")).sendKeys("PAss!@12");
		driver.findElement(By.name("SUBMIT")).click();

		// original list
		List<String> originallist = new ArrayList();
		originallist.add("Capture SOW");
		originallist.add("Capture MACRO RF");
		originallist.add("Capture CRAN RF");
		originallist.add("Capture PNR");
		originallist.add("Acknowledge CNTP");
		originallist.add("Capture Quiet Zone ID");
		originallist.add("Capture Postcon");
		originallist.add("Capture MACRO RF Postcon");
		originallist.add("Capture CRAN RF Postcon");
		originallist.add("Capture Quiet Zone Postcon");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Thread.sleep(10000);
		//wait.until(ExpectedConditions
			//	.visibilityOfElementLocated(By.xpath("(//mat-icon[@data-mat-icon-name='Expand'])[1]")));
		driver.findElement(By.xpath("(//mat-icon[@data-mat-icon-name='Expand'])[1]")).click();
		List<String> naData = new ArrayList();
		List<WebElement> applicationlist = driver
				.findElements(By.xpath("(//div[@class='highCartDiv'])[3]//span[@class='legendText isInActiveData']"));
		System.out.println("applicationlist size: " + applicationlist.size());
		String a = null;
		String b = null;
		for (int i = 0; i < originallist.size(); i++) {
			a = originallist.get(i);
			for (int j = 1; j <= applicationlist.size(); j++) {
				WebElement el = driver.findElement(By.xpath("((//div[@class='highCartDiv'])[3]//span[@class='legendText isInActiveData'])["+j+"]"));
				Coordinates c = ((RemoteWebElement) el).getCoordinates();
				((RemoteWebDriver) driver).getMouse().mouseMove(c);
				b = driver.findElement(By.xpath("//div[@class='tippy-content']")).getText();
				//System.out.println(b);
				if (a.contains(b)) {
					break;
				} else if (j == applicationlist.size()) {
					System.out.println(a);
					naData.add(a);
				}
			}
		}
	/*	WebElement el = driver.findElement(By.xpath("(//a[contains(text(),'Create an account')])[4]"));
		Coordinates c = ((RemoteWebElement) el).getCoordinates();
		System.out.println(c);
		((RemoteWebDriver) driver).getMouse().mouseMove(c);
		 * Robot robot = new Robot(); robot.mouseMove(-10,-10);
		 */
	}

}
