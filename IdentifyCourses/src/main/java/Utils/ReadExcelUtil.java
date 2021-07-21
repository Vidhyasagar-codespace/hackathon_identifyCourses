package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.io.IOException;

public class ReadExcelUtil {
	
	public static String[] formData = new String[11];

	public static String[] readExcelData() throws IOException { // Do not change the method signature
		// Implement code to read data from excel file. Store the values in
		// 'formData' array. Return the array. */
		String path = System.getProperty("user.dir") + "\\Resources\\ReadFormDetails.xlsx";
		String sheetName = "FormDetails";
		File fileName = new File(path);
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheet(sheetName);
		XSSFRow row = ws.getRow(1);
		for (int i = 0; i < 11; i++) {
			XSSFCell cell = row.getCell(i);
			DataFormatter formatter = new DataFormatter();
			String value = formatter.formatCellValue(cell);
			formData[i] = value;
			//formData[i] = String.valueOf(ws.getRow(1).getCell(i));
		}
		
		wb.close();
		fis.close();
		
		return formData;
	}

}
