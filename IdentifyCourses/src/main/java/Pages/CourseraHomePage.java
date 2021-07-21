package Pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import base.BaseUI;

public class CourseraHomePage extends BaseUI {

	public CourseraHomePage(WebDriver driver, ExtentTest logger) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		this.logger = logger;
	}
	
	@FindBy(xpath = "//*[@id='__next']/header/div/div/div/div[1]/div[2]/div/button/span")
	public WebElement exploreLink;
	
	@FindBy(xpath = "//*[@id='computer-science~menu-item']/span")
	public WebElement computerScienceLink;
	
	@FindBy(xpath = "//*[@id='__next']/header/div/div/div/div[1]/div[2]/div/div/nav/div/div/div[2]/div[7]/div/section/div/div[2]/div[1]/div[2]/ul/li[5]/a")
	public WebElement webDevLink;
	
	@FindBy(xpath = "//*[@id='enterprise-link']")
	public WebElement enterpriseLink;
	
	public void openExploreMenu() {
		moveCursor(exploreLink);
		//logger.log(Status.PASS,"Explore Menu Opened");
	}
	
	public WebDevCoursesPage openWebDevCourses() throws Exception {
		
		WebDevCoursesPage webdevpage = new WebDevCoursesPage(driver,logger);
		
		waitforElement(computerScienceLink);
		moveCursor(computerScienceLink);
		//logger.log(Status.PASS,"Computer Science Courses Opened");
		
		waitforElement(webDevLink);
		captureScreenShot("WebDevLink");
		elementClick(webDevLink);
		//logger.log(Status.PASS,"Web Development Courses Opened");
		
		PageFactory.initElements(driver, webdevpage);
		return webdevpage;
	
	}
	
	public EnterprisePage goToEnterprisePage() throws IOException {
		EnterprisePage enterprisepage = new EnterprisePage(driver,logger);
		elementClick(enterpriseLink);
		PageFactory.initElements(driver, enterprisepage);
		return enterprisepage;
	}
}
