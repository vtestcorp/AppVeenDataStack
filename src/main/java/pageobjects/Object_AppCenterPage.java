package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class Object_AppCenterPage extends BaseClass{
	
	public Object_AppCenterPage() {
		PageFactory.initElements(driver, this);
	}
	
	

	@FindBy(xpath="//input[@id='username']")
	public WebElement username;

	@FindBy(xpath="//span[normalize-space()='Next']")
	public WebElement nextButton;

	@FindBy(xpath="//input[@placeholder='Password']")
	public WebElement password;

	@FindBy(xpath="//button[@type='submit']")
	public WebElement loginButton;

	@FindBy(xpath="//button[@id='addDataBtn']")
	public WebElement addDataButton;

	@FindBy(xpath="(//div[starts-with(@class,'logo')])[1]")
	public WebElement dataStackLogo;

	@FindBy(xpath="//input[contains(@class,'form-control')]")
	public List<WebElement> textBoxes;

	@FindBy(xpath="//span[normalize-space()='Save & create another']")
	public WebElement saveAndCreateAnother;

	@FindBy(xpath="//span[normalize-space()='Save']")
	public WebElement save;

	@FindBy(xpath="//label[contains(text(),'Showing')]")
	public WebElement documentList;

	@FindBy(xpath="//div/div[starts-with(@class,'round-check')]")
	public WebElement checkbox;

	@FindBy(xpath="//*[@id='deleteRecordBtn']/span[2]")
	public WebElement delete;

	@FindBy(xpath="//button[normalize-space()='Yes']")
	public WebElement yes;
	
	
	

}
