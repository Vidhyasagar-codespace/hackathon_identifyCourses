package Pages;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.ExtentTest;

import Pages.CourseraHomePage;
import base.BaseUI;

public class EnterprisePage extends BaseUI{

	public EnterprisePage(WebDriver driver, ExtentTest logger) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		this.logger=logger;
	}
	
	@FindBy(xpath = "//*[@id='menu-item-9140']/a")
	public WebElement productsLink;
	
	@FindBy(xpath = "//*[@id='menu-item-4901']/a")
	public WebElement forCampusLink;
	
	public CampusEnterprisePage goToCampusPage() throws IOException {
		
		CampusEnterprisePage campuspage = new CampusEnterprisePage(driver,logger);
		
		captureScreenShot("CourseraEnterprisePage");
		moveCursor(productsLink);
		waitforElement(forCampusLink);
		elementClick(forCampusLink);
		
		String mainPageID = switchTab();
		
		//switchTabBack(mainPageID);
		
		PageFactory.initElements(driver, campuspage);
		return campuspage;
	}

}
