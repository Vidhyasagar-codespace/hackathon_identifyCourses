package Utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelUtil {

	public static void writeErrorMsg(String message) throws IOException {
		//Use try-catchS
		String path = System.getProperty("user.dir") + "\\Results\\ErrorMsg.xlsx";
		String writeSheetName = "FormErrorMsg";
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(writeSheetName);
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue(message);
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();
	}
	
	public static void writeCourseDetails(String[] courseDetails) throws IOException {
		//Use try-catchS
		String path = System.getProperty("user.dir") + "\\Results\\CourseDetails.xlsx";
		String writeSheetName = "WebDevCourses";
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(writeSheetName);
		int k=0;
		for(int i=0;i<2;i++)
		{
			Row dataRow = sheet.createRow(i);
			for(int j=0;j<3;j++)
			{
				//sheet.createRow(i).createCell(j).setCellValue(courseDetails[k]);
				dataRow.createCell(j).setCellValue(courseDetails[k]);
				k++;
			}
		}
		
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();
	}
	
	public static void writeCourseLanguages(HashMap<String,String> languages) throws IOException {
		//Use try-catchS
		String path = System.getProperty("user.dir") + "\\Results\\LangauagesList.xlsx";
		String writeSheetName = "CourseLanguages";
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(writeSheetName);
		
		int i=0;
		for(Map.Entry m : languages.entrySet())
		{
			String langName = (String) m.getKey();
			String langCount = (String) m.getValue();
			sheet.createRow(i).createCell(0).setCellValue(langName);
			sheet.getRow(i).createCell(1).setCellValue(langCount);  
			i++;
		}
			
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();
	}

	public static void writeCourseLevels(HashMap<String,String> levels) throws IOException {
		//Use try-catchS
		String path = System.getProperty("user.dir") + "\\Results\\LevelsList.xlsx";
		String writeSheetName = "CourseLevels";
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(writeSheetName);
		
		int i=0;
		for(Map.Entry m : levels.entrySet())
		{
			String levelName = (String) m.getKey();
			String levelCount = (String) m.getValue();
			sheet.createRow(i).createCell(0).setCellValue(levelName);
			sheet.getRow(i).createCell(1).setCellValue(levelCount);  
			i++;
		}
		
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();
	}
}
