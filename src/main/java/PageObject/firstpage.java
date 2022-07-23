package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class firstpage {
	
	public WebDriver driver;
	
	By clickstats = By.xpath("//a[@data-hover='Stats']");
	By clickrankings = By.xpath("(//li//div[contains(text(),'Rankings')])[1]");
	By clickplayerrankings = By.xpath("(//li//a[contains(text(),'Players Rankings')])[1]");
	By clickODI = By.xpath("//a[contains(text(),'ODI')]");
	By numONE_ODI = By.xpath("//div[@data-title='ODI Batting Rankings']//div//div[@class='rankings-block__banner--name']");
    By clickAdClose = By.xpath("//button[@class='ott-splash-promo__close js-close']");
	
	@FindBy(xpath = "") WebElement home;
	
   public firstpage(WebDriver driver) 
   {
	   this.driver = driver;
   }
   
   public WebElement clickAdClose() {
	   return driver.findElement(clickAdClose);
   }
   
	public WebElement clickODI() 
	{
		return driver.findElement(clickODI);
		
	}
	
	public WebElement clickrankings()
	{
		return driver.findElement(clickrankings);
	}
	
	public WebElement clickplayerrankings() 
	{
		return driver.findElement(clickplayerrankings );
	}

	public WebElement numONE_ODI() {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(numONE_ODI);
		je.executeScript("arguments[0].scrollIntoView(true);",element);
		return element;
		
	}
}
