package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Utils.DateUtils;

import Utils.ExtentReportManager;

import Pages.CourseraHomePage;

public class BaseUI {

	public WebDriver driver;
	public Properties prop;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	public SoftAssert softAssert = new SoftAssert();
	
	public void invokeBrowser(String browserName) {

		try {
			if (browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\Resources\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("Mozilla")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\Resources\\drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// System.out.println(System.getProperty("user.dir"));

		if (prop == null) {
			prop = new Properties();

			// Windows
			try {
				FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\Resources\\Config.properties");
				prop.load(file);
			} catch (Exception e) {
				reportFail(e.getMessage());
				e.printStackTrace();
			}
		}

	}

	/****************** Open URL ***********************/
	public void openURL(String websiteURLKey) {
		try {
			driver.get(prop.getProperty(websiteURLKey));
			reportPass(prop.getProperty(websiteURLKey) + " opened successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}
	
	public void openBrowser() throws IOException {
		String filelocation = System.getProperty("user.dir") + "\\Resources\\Config.properties";
		File file = new File(filelocation);
		FileInputStream fileinput = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fileinput);
		String browser = (String) prop.get("Browser");
		invokeBrowser(browser);
		logger.log(Status.INFO, browser + " browser opened successfully");
	}
	
	public void closeBrowser() {
		driver.close();
		reportPass("Browser Closed Successfully");
	}


	public CourseraHomePage openApplication() throws IOException {
		/*
		 * String filelocation = System.getProperty("user.dir") +
		 * "\\Resources\\Config.properties"; File file = new File(filelocation);
		 * FileInputStream fileinput = new FileInputStream(file); Properties prop = new
		 * Properties(); prop.load(fileinput); String url = (String) prop.get("URL");
		 * //System.out.println(url); //logger.log(Status.INFO, "Opening the WebSite");
		 * driver.get(url);
		 */
		openBrowser();
		openURL("URL");
		captureScreenShot("CourseraHomePage");
		//logger.log(Status.PASS, "Successfully Opened the https: " + url);
		CourseraHomePage homepage = new CourseraHomePage(driver,logger);
		//HomePage homepage = new HomePage(driver, logger);
		PageFactory.initElements(driver, homepage);
		return homepage;
	}
	
	public void moveCursor(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		logger.log(Status.PASS, "Cursor successfully moved to "+element.getText());
	}
	
	/*
	 * Wait for element
	 */
	public void waitforElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/*
	 * Clicking element
	 */
	public void elementClick(WebElement element) {

		try {
			String Elementtext = element.getText();
			if(Elementtext=="") {
				Elementtext = element.getAttribute("value");
			}
			reportPass(Elementtext + " : Element Clicked Successfully");
			element.click();
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	public String switchTab() {

		try {
			Set<String> windowIDs = driver.getWindowHandles();
			Iterator<String> itr = windowIDs.iterator();
			
			String mainPageID = itr.next();
			String newPageID = itr.next();
			
			//Switching to new Tab/Window
			driver.switchTo().window(newPageID);
			logger.log(Status.INFO, "Navigated To "+ driver.switchTo().window(newPageID).getTitle() +"Page Successfully");
			return mainPageID;
		} catch (Exception e) {
			reportFail(e.getMessage());
			return null;
		}
		
	}
	
	public void switchTabBack() {
		Set<String> windowIDs = driver.getWindowHandles();
		Iterator<String> itr = windowIDs.iterator();
		String mainPageID = itr.next();
		driver.switchTo().window(mainPageID);
		logger.log(Status.INFO, "Navigated Back To "+ driver.switchTo().window(mainPageID).getTitle() +"Page Successfully");
	}
	
	/*
	 * Entering test in input box
	 */
	public void enterText(WebElement element, String data) {
		try {
			element.sendKeys(data);
			reportPass(data + " - Entered successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	/*
	 * Selecting drop down value
	 */
	public void SelectElementInList(WebElement element, String Value) {
		boolean flag = false;
		Select select = new Select(element);
		List<WebElement> list = select.getOptions();

		for (WebElement listItem : list) {

			if (listItem.getText().equals(Value)) {
				if (listItem.isEnabled()) {
					flag = true;
					select.selectByVisibleText(Value);
					logger.log(Status.PASS, "Selected the Value : " + Value);
					break;
				} else {
					flag = true;
					reportFail("Value not enabled : " + Value);
				}
			}
		}
		if (flag == false) {
			reportFail("Value not displayed : " + Value);
		}

	}

	/*
	 * getting page title
	 */
	public void getTitle(String expectedTitle) {

		try {
			Assert.assertEquals(driver.getTitle(), expectedTitle);
			reportPass("Actual Title : " + driver.getTitle() + " - equals to Expected Title : " + expectedTitle);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}
	
	/****************** Assertion Functions ***********************/
	public void assertTrue(boolean flag) {
		softAssert.assertTrue(flag);
	}

	public void assertfalse(boolean flag) {
		softAssert.assertFalse(flag);
	}

	public void assertequals(String actual, String expected) {
		try{
			logger.log(Status.INFO, "Assertion : Actual is -" + actual + " And Expacted is - " + expected);
			softAssert.assertEquals(actual, expected);
		}catch(Exception e){
			reportFail(e.getMessage());
		}
		
	}

	/****************** Reporting Functions ***********************/
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShotOnFailure();
		Assert.fail(reportString);
	}

	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	public void afterTest() {
		softAssert.assertAll();
		driver.quit();
	}

	/****************** Capture Screen Shot ***********************/
	public void takeScreenShotOnFailure() {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "/ScreenShots/Fail/" + DateUtils.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "/ScreenShots/Fail/" + DateUtils.getTimeStamp() + ".png");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void captureScreenShot(String fileName) {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "/ScreenShots/" + fileName + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void scrollPage(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	@AfterTest
	public void endReport() {
	report.flush();

	}


}
