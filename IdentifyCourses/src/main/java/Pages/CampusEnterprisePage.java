package Pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;

import base.BaseUI;

public class CampusEnterprisePage extends BaseUI{

	public CampusEnterprisePage(WebDriver driver,ExtentTest logger) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		this.logger=logger;
	}
	
	@FindBy(xpath = "//*[@id='form']/div/h2")
	public WebElement scrollToForm;

	@FindBy(xpath = "//*[@id='FirstName']")
	public WebElement firstName;
	
	@FindBy(xpath = "//*[@id='LastName']")
	public WebElement lastName;
	
	@FindBy(xpath = "//*[@id='C4C_Job_Title__c']")
	public WebElement jobFn;
	
	@FindBy(xpath = "//*[@id='Title']")
	public WebElement jobTitle;

	@FindBy(xpath = "//*[@id='Email']")
	public WebElement eMail;
	
	@FindBy(xpath = "//*[@id='Phone']")
	public WebElement phNo;
	
	@FindBy(xpath = "//*[@id='Company']")
	public WebElement institutionName;
	
	@FindBy(xpath = "//*[@id='Institution_Type__c']")
	public WebElement institutionType;
	
	@FindBy(xpath = "//*[@id='Primary_Discipline__c']")
	public WebElement primaryDiscipline;
	
	@FindBy(xpath = "//*[@id='Country']")
	public WebElement country;
	
	@FindBy(xpath = "//*[@id='State']")
	public WebElement state;
	
	@FindBy(xpath = "//*[@class='mktoButton']")
	public WebElement submitBtn;
	
	@FindBy(xpath = "//*[@id='ValidMsgEmail']")
	public WebElement errorMsgElt;
	
	public void enterFirstName(String fName) {
		enterText(firstName, fName);
		//Chandler
	}
	
	public void enterLastName(String lName) {
		enterText(lastName, lName);
		//Bing
	}
	
	public void enterjobFn(String jobfunction) {
		SelectElementInList(jobFn, jobfunction);
		//Other
	}
	
	public void enterjobTitle(String jTitle) {
		enterText(jobTitle, jTitle);
		//ABC
	}
	
	public void enterEmail(String mail) {
		enterText(eMail, mail);
		//xyz
	}
	
	public void enterPhNo(String phone) {
		enterText(phNo, phone);
		//+919856988569
	}
	
	public void enterInstitutionName(String iName) {
		enterText(institutionName, iName);
		//SSN College Of Engineering
	}
	
	public void enterInstitutionType(String iType) {
		SelectElementInList(institutionType, iType);
		//Private University
	}
	
	public void enterDiscipline(String discipline) {
		SelectElementInList(primaryDiscipline, discipline);
		//Computer Science
	}
	
	public void enterCountry(String countryName) {
		SelectElementInList(country, countryName);
		//India
	}
	
	public void enterState(String stateName) {
		SelectElementInList(state, stateName);
		//Tamil Nadu
	}
	
	public void clickSubmitBtn() {
		submitBtn.click();
	}
	
	public String getErrorMsg() {
		String errorMsg = errorMsgElt.getText();
		System.out.println(errorMsg);
		return errorMsg;
	}
	
	public String fillCampusForm(String[] exceldata) throws Exception {
		
		captureScreenShot("CourseraCampusPage");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", scrollToForm);
		captureScreenShot("CourseraCampusPageForm");
		//String txt = scrollToForm.getText();
		//System.out.println(txt);
		
		enterFirstName(exceldata[0]);
		enterLastName(exceldata[1]);
		enterjobFn(exceldata[2]);
		enterjobTitle(exceldata[3]);
		enterEmail(exceldata[4]);
		enterPhNo(exceldata[5]);
		enterInstitutionName(exceldata[6]);
		enterInstitutionType(exceldata[7]);
		enterDiscipline(exceldata[8]);
		enterCountry(exceldata[9]);
		enterState(exceldata[10]);
		captureScreenShot("CourseraCampusPageFilledForm");
		clickSubmitBtn();
		String errorMessage = getErrorMsg();
		
		Thread.sleep(1000);
		captureScreenShot("CourseraCampusPageErrorForm");
		
		driver.close();
		
		switchTabBack();
		
		return errorMessage;
	}
	
}
