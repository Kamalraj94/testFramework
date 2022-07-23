package ESPN_data;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.Properties;
import java.util.logging.Logger;

import PageObject.firstpage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.relevantcodes.extentreports.LogStatus;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class Runner extends Base {
	ExtentReports extent;
	ExtentHtmlReporter htmlReporter;
	ExtentTest test;
	String extentnaming = super.extentname();
	
	@BeforeTest
	public void launch() throws IOException {
	htmlReporter = new ExtentHtmlReporter("reports/Report_"+extentnaming+".html");	
	extent = new ExtentReports();
	extent.attachReporter(htmlReporter);	
	Properties prop = new Properties();
    FileInputStream fis = new FileInputStream("F:\\my_framework\\src\\main\\java\\ESPN_data\\data.properties");
	prop.load(fis);
	driver = initialize();
	driver.get(prop.getProperty("url"));
	//System.getProperty("user.dir");
}
	
	@Test
	public void FirstTestCase() throws IOException, AutomationException, SQLException 
	{	
		test = extent.createTest("First Test Case : Number One ODI player Verification");	
		firstpage x = new firstpage(driver);
		Actions actions = new Actions(driver);
		x.clickAdClose().click();
		actions.moveToElement(x.clickrankings()).perform();
		x.clickplayerrankings().click();
		x.clickODI().click();
		String UI = x.numONE_ODI().getText().trim();
		test.info("No 1 player  in ICC server - " + UI);
		String local = testdata().trim();
		test.info("No 1 player in Local server - " + local);
		String DB = DB_data().trim();
		test.info("No 1 player in DB server - " + DB);
		if(UI.equalsIgnoreCase(local) && DB.equalsIgnoreCase(local)) {
			System.out.println("Local server , DB server and Web server value matches");
			test.pass("Virat is still number One in ODI");
		} else {
			test.fail("conditions are not met");
			throw new AutomationException("Conditions are not met");				
		}
		
	}
	
	@Test
	public void SecondTestcase()  throws IOException, AutomationException, SQLException {
		test = extent.createTest("Second Test Case");
		test.pass("Passing this test case");
	}

	@AfterTest
	public void close() throws IOException {
	    Base.screenshot(driver);
		driver.close();
		extent.flush();		
	}

}
