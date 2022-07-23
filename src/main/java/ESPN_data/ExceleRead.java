package ESPN_data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExceleRead {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("F://my_framework//src//testdata.xlsx");	
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		String text2 = sheet.getRow(1).getCell(1).toString();
		System.out.println(text2);
			
	}

}
