package rtee;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class read {
	public WebDriver driver;

	@Test
	public void read() throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		FileInputStream fis = new FileInputStream("F://my_framework//src//testcases.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("TC");
		XSSFSheet sheet2 = wb.getSheet("TS");
		int rowcount; 
		int rowcount2;
		int colcount; 
		Method call;
		rowcount = sheet.getLastRowNum(); 
		rowcount2 = sheet2.getLastRowNum();
		methods z = new methods();
		for (int i = 1; i < rowcount + 1; i++) {    
			XSSFCell x = sheet.getRow(i).getCell(2); 
			String y = x.toString(); // i =2
			{
				if (y.equals("Y")) {
					String tc = sheet.getRow(i).getCell(1).toString(); 
					System.out.println("test case name: "+tc);
					for (Row row : sheet2) { 
						for (Cell cell : row) {
							if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
								if (cell.getRichStringCellValue().getString().trim().equals(tc)) {  
							    colcount = cell.getColumnIndex();		
								  for(i=1;i<rowcount2+1;i++) {
									 String method = sheet2.getRow(i).getCell(colcount).toString().trim();
									 call = z.getClass().getMethod(method);
									 call.invoke(z);
								  }
									
								} 
							}
						}
					}
				}
			}
		}
		
	}
}

