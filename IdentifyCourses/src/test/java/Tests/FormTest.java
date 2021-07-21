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

public class FormTest extends BaseUI {
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
			logger = report.createTest("Form Fill Test");
			homepage = openApplication();
			homepage.openExploreMenu();
			logger.log(Status.INFO, "Navigated to Coursera Enterprise Page Successfully");
			enterprisepage = homepage.goToEnterprisePage();
			campuspage = enterprisepage.goToCampusPage();
			logger.log(Status.INFO, "Navigated to Coursera Campus Page Successfully");
			String formValues[] = ReadExcelUtil.readExcelData();
			String errorMsg = campuspage.fillCampusForm(formValues);
			WriteExcelUtil.writeErrorMsg(errorMsg);
		
		} catch (Exception e) {
			//
		}finally {
			closeBrowser();
		}
	}
}
