package test;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class mousehover2 {

	public WebDriver driver;

	@Test
	public void test() throws InterruptedException, AWTException {

		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "F:\\chromedriver_95\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NONE);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  //30 secs
		driver.navigate().to("https://uat-att.siteforge.com/");
		driver.findElement(By.id("username")).sendKeys("test.vl");
		driver.findElement(By.id("password")).sendKeys("PAss!@12");
		driver.findElement(By.name("SUBMIT")).click();
		Thread.sleep(10000);
		WebElement clickExpand = driver.findElement(By.xpath("(//mat-icon[@data-mat-icon-name='Expand'])[1]"));
		clickExpand.click();
		List<WebElement> applicationlist = driver
				.findElements(By.xpath("(//div[@class='highCartDiv'])[3]//span[@class='legendText isInActiveData']"));
		System.out.println("applicationlist size: " + applicationlist.size());

		for (int i = 1; i <= applicationlist.size(); i++) {
			String a = driver.findElement(
					By.xpath("((//div[@class='highCartDiv'])[3]//span[@class='legendText isInActiveData'])[" + i
							+ "]//preceding-sibling::span"))
					.getText();
			driver.findElement(By.xpath(
					"((//div[@class='highCartDiv'])[3]//span[@class='legendText isInActiveData'])["+i+"]//preceding-sibling::span"))
					.click();
			//System.out.println(a);
			Thread.sleep(6000);
			String b = driver.findElement(By.xpath("(//div[@class='heading grid-label ng-star-inserted']//span)[2]"))
					.getText();
			//System.out.println(b);
			assertEquals(a, b);
			driver.navigate().back();
			Thread.sleep(6000);
			driver.findElement(By.xpath("(//mat-icon[@data-mat-icon-name='Expand'])[1]")).click();
			
			
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'COMPOSE')]")));
             
			driver.switchTo().frame("//");
			
			driver.switchTo().frame(0);
			driver.switchTo().parentFrame();
			driver.switchTo().defaultContent();
		}
	}
}