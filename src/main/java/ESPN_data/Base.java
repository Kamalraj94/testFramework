package ESPN_data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.core.util.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Base {

	public WebDriver driver;
	public Properties prop;
	
	public WebDriver initialize() throws IOException
	{
	
      prop = new Properties();
      FileInputStream fis = new FileInputStream("F:\\my_framework\\src\\main\\java\\ESPN_data\\data.properties");
	  prop.load(fis);
	  String browsername = prop.getProperty("browser");
	  
	  //Chrome
	  if(browsername.equals("chrome"))
	  {
		  System.setProperty("webdriver.chrome.silentOutput", "true");
		  System.setProperty("webdriver.chrome.driver", "F:\\chromedriver_95\\chromedriver.exe");
		  ChromeOptions options = new ChromeOptions();
		  options.setPageLoadStrategy(PageLoadStrategy.NONE);
		  driver=new ChromeDriver();
		  driver.manage().window().maximize(); 
	  }
	  
	  //Firefox
	  else if(browsername.equals("firefox"))
	  {
		  
		  
	  }
	  
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  return driver;
}
	public String testdata() throws IOException {
		FileInputStream fis = new FileInputStream("F://my_framework//src//testdata.xlsx");	
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		String text2 = sheet.getRow(1).getCell(1).toString();
		//driver.findElement(By.xpath("")).sendKeys(text2);
		return text2;
		}
	
	public String extentname() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   String name = (dtf.format(now));  
		   return name;
	}
	
	public String DB_data() throws SQLException {
		
		String data = null;
		String url = "jdbc:mysql://localhost:3306/Odirankings";
		Connection con = DriverManager.getConnection(url,"root","K@malraj94");
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("select * from odi;");
		if(rs.next()) {
		 data = (rs.getString("playername"));
		}
		return data;
	}
	
	public static String screenshot(WebDriver driver) throws IOException {
		TakesScreenshot x = (TakesScreenshot) driver;
		File source = x.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/Screenshots/" + System.currentTimeMillis() + ".png";
		File finalDestination = new File(path);
	    FileHandler.copy(source, finalDestination);
	    return path;	
	}


}