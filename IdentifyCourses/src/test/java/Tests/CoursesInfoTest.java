package Tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Pages.CampusEnterprisePage;
import Pages.CourseraHomePage;
import Pages.EnterprisePage;
import Pages.WebDevCoursesPage;
import Utils.ReadExcelUtil;
import Utils.WriteExcelUtil;
import base.BaseUI;

public class CoursesInfoTest extends BaseUI {
	CourseraHomePage homepage;
	WebDevCoursesPage webdevcoursepage;
	EnterprisePage enterprisepage;
	CampusEnterprisePage campuspage;
	ReadExcelUtil readExcel = new ReadExcelUtil();
	WriteExcelUtil writeExcel = new WriteExcelUtil();
	
	@Test
	public void getCourse() {
		//BaseUI baseui = new BaseUI();
		 
		try {
			logger = report.createTest("Coursera Test");
			homepage = openApplication();
			homepage.openExploreMenu();
			logger.log(Status.INFO, "Navigated to Web Developpment Courses Page");
			webdevcoursepage = homepage.openWebDevCourses();
			HashMap<String,String> languages = webdevcoursepage.getLanguageList();
			HashMap<String,String> levels = webdevcoursepage.getLevelList();
			String courseDetails[] = webdevcoursepage.selectDesiredCourses();
			WriteExcelUtil.writeCourseLanguages(languages);
			WriteExcelUtil.writeCourseLevels(levels);
			WriteExcelUtil.writeCourseDetails(courseDetails);
		
		} catch (Exception e) {
			//
		}finally {
			closeBrowser();
		}
	}
}
