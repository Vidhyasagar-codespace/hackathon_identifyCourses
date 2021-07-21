package Pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.BaseUI;

public class WebDevCoursesPage extends BaseUI {

	public WebDevCoursesPage(WebDriver driver, ExtentTest logger) {
		this.driver=driver;
		this.logger=logger;
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//*[@id='react-select-2--value']/div[1]")
	public WebElement languageList;
	
	@FindBy(xpath = "//*[@id='react-select-2--option-9']/div/button[2]")
	public WebElement showAllBtn;
	
	@FindBy(xpath = "//*[@id='main']/div/div[2]/div/div[2]/div[1]/div/div[3]/div/div[3]/div[3]/a")
	public WebElement closeLangWindow;
	
	@FindBy(xpath = "//*[@id='react-select-3--value']/div[1]")
	public WebElement levelList;
	
	@FindBy(xpath = "//*[@id='main']/div/div[2]/div/div[2]/div[1]/div/div[3]/div/div[3]/div[2]/div/div")
	public List<WebElement> languagesAvailable;
	
	@FindBy(xpath = "//main/div//div[2]/div/div/div/button/label/input")
	public List<WebElement> levelsAvailable;
	
	@FindBy(xpath = "//*[@id='react-select-2--option-0']/div/button/label/input")
	public WebElement englishBtn;
	
	@FindBy(xpath = "//*[@id='react-select-3--option-0']/div/button/label/input")
	public WebElement beginnerBtn;
	
	@FindBy(xpath = "//*[@id='__next']/div/div[1]/div/div[1]/div/div[1]/div/div/div/div/div/div/ul/li[1]/div/div/div/div/div/div/div[1]/a")
	public WebElement course1;
	
	@FindBy(xpath = "//*[@id='__next']/div/div[1]/div/div[1]/div/div[1]/div/div/div/div/div/div/ul/li[2]/div/div/div/div/div/div/div[1]/a")
	public WebElement course2;
	
	@FindBy(xpath = "//*[@id='__next']/div/div[1]/div/div[1]/div/div[1]/div/div/div/div/div/div/ul/li[1]/div/div/div/div/div/div/div[4]/div[1]/div[1]/div/span[1]")
	public WebElement rating1;
	
	@FindBy(xpath = "//*[@id='__next']/div/div[1]/div/div[1]/div/div[1]/div/div/div/div/div/div/ul/li[2]/div/div/div/div/div/div/div[4]/div[1]/div[1]/div/span[1]")
	public WebElement rating2;
	
	public HashMap<String, String> getLanguageList() throws Exception {
		
		captureScreenShot("WebDevCourses");
		elementClick(languageList);
		elementClick(showAllBtn);
		captureScreenShot("AvailableLanguages");

		HashMap<String,String> languages =new HashMap<String,String>();
		
		for(int i=1;i<=21;i++)
		{
			for(int j=1;j<=2;j++)
			{
				languages.put(driver.findElement(By.xpath("//*[@id='main']/div/div[2]/div/div[2]/div[1]/div/div[3]/div/div[3]/div[2]/div["+j+"]/div["+i+"]/button/label/input")).getAttribute("value"), driver.findElement(By.xpath("//*[@id='main']/div/div[2]/div/div[2]/div[1]/div/div[3]/div/div[3]/div[2]/div["+j+"]/div["+i+"]/button/div")).getText());
			}
		}
		
		System.out.println("Total Languages Available : "+languagesAvailable.size());
		System.out.println("**************************************************************");
		
		  
		
		closeLangWindow.click();
		logger.log(Status.INFO, "Available Languages Window Closed Successfully");
		
		return languages;
	}
	
	public HashMap<String, String> getLevelList() throws Exception {
		
		elementClick(levelList);
		captureScreenShot("LevelAvailable");

		HashMap<String,String> levels =new HashMap<String,String>();
		
		System.out.println("Total Levels Available : "+levelsAvailable.size());
		System.out.println("**************************************************************");
		
		for(int i=1;i<=4;i++)
		{
			levels.put(driver.findElement(By.xpath("//*[@id='react-select-3--option-"+(i-1)+"']/div/button/label/input")).getAttribute("value"), driver.findElement(By.xpath("//*[@id='react-select-3--option-"+(i-1)+"']/div/button/div")).getText());
		}
		
		//To close
		levelList.click();
		
		return levels;
	}

	public String getCourseName(WebElement course) {
//		System.out.println("**************************************************************");
//		System.out.println(course1.getText());
//		System.out.println(course2.getText());
		return course.getText();
	}
	
	public String getCourseRating(WebElement courseRating) {
//		System.out.println("**************************************************************");
//		System.out.println(rating1.getText());
//		System.out.println(rating2.getText());
		return courseRating.getText();
	}
	
	public String getLearningHours() {
		WebElement learningHours = driver.findElement(By.xpath("//*[@id='main']/div/div[2]/div/div/div/div[2]/div/div[2]/div[5]/div[2]/div/span"));
//		System.out.println(learningHours.getText());
		scrollPage(learningHours);
		return learningHours.getText();
	}
	
	public String[] selectDesiredCourses() throws Exception {
		
		elementClick(languageList);
		elementClick(englishBtn);
		elementClick(levelList);
		elementClick(beginnerBtn);
		
		
		String courseName1 = getCourseName(course1);
		String courseName2 = getCourseName(course2);

		String courseRating1 = getCourseRating(rating1);
		String courseRating2 = getCourseRating(rating2);
		
		waitforElement(course1);
		captureScreenShot("CoursesAfterSort");
		elementClick(course1);
		switchTab();
		captureScreenShot("CourseOnePage");
	
		String courseDuration1 = getLearningHours();
		driver.close();
		
		switchTabBack();
		
		elementClick(course2);
		switchTab();
		captureScreenShot("CourseTwoPage");
		String courseDuration2 = getLearningHours();
		driver.close();
		switchTabBack();
		
		String[] courseDetails = new String [] {courseName1,courseRating1,courseDuration1,courseName2,courseRating2,courseDuration2};

		return courseDetails;
	}
	
}
