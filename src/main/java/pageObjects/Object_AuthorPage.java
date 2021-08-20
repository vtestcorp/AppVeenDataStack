package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;
import config.DefineConstants;
import helperMethods.JsonUtils;

public class Object_AuthorPage extends BaseClass{

	public Object_AuthorPage() {
		PageFactory.initElements(driver, this);
	}
//	WebDriver driver;
	

	
	@FindBy(xpath="//div[normalize-space()='Sign in with your credentials']")
	public WebElement signIn;
	
	@FindBy(xpath="//input[@id='username']")
	public WebElement emailIDTextBox;
	
	@FindBy(xpath="//span[contains(text(),'Login')]")
	public WebElement signInButton;

	@FindBy(xpath="//span[contains(text(),'Next')]")
	public WebElement nextButton;

	@FindBy(xpath="//input[@id='password']")
	public WebElement password;

	@FindBy(xpath="//*[text()='List of Data Services']")
	public WebElement listOfDataServices;

	@FindBy(xpath="//*[text()='New Data Service']")
	public WebElement newDataService;

	@FindBy(xpath="//input[@id='name']")
	public WebElement dataServiceName;

	@FindBy(xpath="(//span[contains(text(),'New Attribute')])[last()]")
	public WebElement newAttributeButton;

	@FindBy(xpath="(//span[contains(text(),'New Attribute')])[last()-1]")
	public WebElement newAttributeButton1;

	@FindBy(xpath="(//span[contains(text(),'New Attribute')]//preceding::input[1])[last()]")
	public WebElement attributeNameTextbox;

	@FindBy(xpath="//button[@id='newServiceModalYes']")
	public WebElement createButton;
	
	@FindBy(xpath="//textarea[@id='description']")
	public WebElement descriptionTextBox;
	
	@FindBy(xpath="//label[@for='reqAttr']//span[@class='checkmark']")
	public WebElement required;
	
	@FindBy(xpath="(//div[starts-with(@class,'readonly')])[last()]")
	public WebElement readOnly;

	@FindBy(xpath="//label[@for='createAttr']//span[@class='checkmark']")
	public WebElement createOnly;

	@FindBy(xpath="//label[@for='UniqAttr']//span[@class='checkmark']")
	public WebElement unique;

	@FindBy(xpath="//div[text()='Custom Label']/following-sibling::div/input")
	public WebElement customLabel;

	@FindBy(xpath="//div[text()='Custom Error']/following-sibling::div/input")
	public WebElement customError;
	
	@FindBy(xpath = "//div[text()='Relates To']/following-sibling::div/input")
	public WebElement relatesTo;

	@FindBy(xpath="//div[text()='Default Value ']/following-sibling::div/input")
	public WebElement defaultValue;

	@FindBy(xpath="//div[contains(text(),'Pattern')]/following-sibling::div/input")
	public WebElement pattern;

	@FindBy(xpath="//div[text()='Min.']/following-sibling::input")
	public WebElement minValue;

	@FindBy(xpath="//div[text()='Max.']/following-sibling::input")
	public WebElement maxValue;

	@FindBy(xpath="//div[text()='Description']/following-sibling::div/textarea")
	public WebElement description;

	@FindBy(xpath="//span[@class='fas fa-angle-down text-accent ng-star-inserted']")
	public WebElement dropdown;
	
	@FindBy(xpath = "//span[@class='odp-references']")
	public WebElement relation; 

	@FindBy(xpath="//span[@class='odp-123']")
	public WebElement number;
	
	@FindBy(xpath="//label[normalize-space()='Number']")
	public WebElement number1;

	@FindBy(xpath="//span[@class='odp-abc']")
	public WebElement abc;

	@FindBy(xpath="//label[normalize-space()='Long Text']")
	public WebElement longText;

	@FindBy(xpath="//label[normalize-space()='Text']")
	public WebElement text;

	@FindBy(xpath="//label[normalize-space()='Rich Text']")
	public WebElement richText;

	@FindBy(xpath="//label[normalize-space()='Email']")
	public WebElement email;

	@FindBy(xpath="//label[normalize-space()='Secure Text']")
	public WebElement secureText;

	@FindBy(xpath="//label[normalize-space()='List of values']")
	public WebElement listOfValue;

	@FindBy(xpath="//div[text()='Max.']/following-sibling::div/input")
	public WebElement maxValueNumber;

	@FindBy(xpath="//span[@class='odp-boolean']")
	public WebElement booleanData;

	@FindBy(xpath="//span[@class='odp-calendar']")
	public WebElement calender;

	@FindBy(xpath="//label[normalize-space()='Date']")
	public WebElement date;
	
	@FindBy(xpath="//label[normalize-space()='Date & Time']")
	public WebElement dateAndTime;
	
	@FindBy(xpath="//div[text()='Default Timezone']/following-sibling::div/*/input")
	public WebElement defaultTimezone;

	@FindBy(xpath="//div[text()='Supported Timezones']/following-sibling::div/*/input")
	public WebElement supportedTimezones;

	@FindBy(xpath="//span[@class='odp-group']")
	public WebElement group;

	@FindBy(xpath="//span[@class='odp-location']")
	public WebElement location;
	
	@FindBy(xpath="//button[normalize-space()='Select Date']")
	public WebElement selectDate;
	
	@FindBy(xpath="//div[@class='calender ng-star-inserted']//select[1]")
	public WebElement monthDropDown;
	
	@FindBy(xpath="//div[@class='calender ng-star-inserted']//select[2]")
	public WebElement yearDropDown;
	
	@FindBy(xpath="//div[@class='time clearfix px-3 py-2 ng-star-inserted']//select[1]")
	public WebElement hourDropDown;
	
	@FindBy(xpath="//div[@class='time clearfix px-3 py-2 ng-star-inserted']//select[2]")
	public WebElement minuteDropDown;
	
	@FindBy(xpath="//div[@class='time clearfix px-3 py-2 ng-star-inserted']//select[3]")
	public WebElement secondDropDown;
	
	@FindBy(xpath="//button[normalize-space()='Done']")
	public WebElement done;
	
	@FindBy(xpath="//label[normalize-space()='Point on a map']")
	public WebElement pointOnAMap;

	@FindBy(xpath="//span[@class='odp-attach']")
	public WebElement file;

	@FindBy(xpath="//span[@class='odp-library']")
	public WebElement library;

	//@FindBy(xpath = "//span[@class='odp-references']")
//	public WebElement relation; 
	
	
	@FindBy(xpath="//div[text()='Linked Library']/following-sibling::div/select")
	public WebElement linkedLibrary;

	@FindBy(xpath = "//div[text()='View Fields']/following-sibling::div/select")
	public WebElement viewFields;

	@FindBy(xpath="//span[@class='fa-user-circle far']")
	public WebElement user;

	@FindBy(xpath="//select[@id='searchOnField']")
	public WebElement searchOnField;
	
	@FindBy(xpath="//select[@formcontrolname='_listInput']")
	public WebElement viewField;

	@FindBy(xpath="//div[contains(text(),'Tokens')]/following-sibling::div/input")
	public WebElement tokens;
	
	@FindBy(xpath = "//button[starts-with(@class,'list-add-button')]")
	public WebElement plusIcon;

	@FindBy(xpath="//div[contains(text(),'Values')]/following-sibling::div/input")
	public WebElement values;
	
	@FindBy(xpath="//select[@formcontrolname='default']")
	public WebElement defaultDropDown;
	

	@FindBy(xpath="//label[normalize-space()='Currency']")
	public WebElement currency;

	@FindBy(xpath="//select[@id='_currency']")
	public WebElement currencyDropdown;

	@FindBy(xpath="//button[@id='sbButtonSave']")
	public WebElement saveButton;

	@FindBy(xpath="//input[@id='searchTerm']")
	public WebElement search;

	@FindBy(xpath="//span[@id='serviceManagerCardTitle']")
	public WebElement dataServiceName1;

	@FindBy(xpath="//div[@class='card-container pt-3']//div[1]//div[3]//div[1]//div[1]")
	public WebElement toggler;
	
	@FindBy(xpath="//div[@class='button-grid bg-light clearfix w-100 active']//span[@class='text'][normalize-space()='Delete']")
	public WebElement delete;

	@FindBy(xpath="//span[@class='text ng-star-inserted'][normalize-space()='Delete']")
	public WebElement delete1;

	@FindBy(xpath="//span[normalize-space()='Clone']")
	public WebElement clone;

	@FindBy(xpath="(//input[@placeholder='Untitled Attribute'])[2]")
	public WebElement newAttribute1;

	@FindBy(xpath="//span[@class='odp-array']")
	public WebElement collection;

	@FindBy(xpath="//div[@class='px-3 schemaProperties-body ng-untouched ng-pristine ng-valid ng-star-inserted']//span[@class='fas fa-angle-down text-accent ng-star-inserted']")
	public WebElement dropdownCollection;

	@FindBy(xpath="//button[@id='sbButtonSaveAndDeploy']")
	public WebElement submitAndDeploy;

	@FindBy(xpath="//span[normalize-space()='Groups']")
	public WebElement groups;
	
	@FindBy(xpath="//div[@class='text-truncate']")
	public WebElement groupNames;
	
	@FindBy(xpath="//span[normalize-space()='Groups']")
	public WebElement groups1;
	
	@FindBy(xpath="(//input[@placeholder='Untitled Attribute'])[last()]")
	public WebElement attributeTextBox;
	
	@FindBy(xpath="//span[normalize-space()='Experience']")
	public WebElement experienceTab;
	
	@FindBy(xpath="//button[normalize-space()='Customize']")
	public WebElement customize;
	
	@FindBy(xpath="//input[@id='stepName']")
	public WebElement stepNameTextBox;
	
	@FindBy(xpath="//div[@id='addStep']")
	public WebElement addStep;
	
	@FindBy(xpath="//div[starts-with(@class,'text-dark')]/span[normalize-space()='Roles']")
	public WebElement roles;
	
	@FindBy(xpath="//span[contains(text(),'Add New')]")
	public WebElement addNew;
	
	@FindBy(xpath="//span[normalize-space()='Name']/following-sibling::input")
	public WebElement roleNameTextBox;
	
	@FindBy(xpath="//div[@class='enable-review d-flex w-100 mb-3 ng-star-inserted']//span[@class='toggler']/parent::label/following-sibling::span")
	public WebElement enabledReviewText;
	
	@FindBy(xpath="//div[@class='enable-review d-flex w-100 mb-3 ng-star-inserted']//span[@class='toggler']")
	public WebElement enabledReviewToggler;
	
	@FindBy(xpath="//div[text()='Create']/parent::div")
	public WebElement createButton1;
	
	@FindBy(xpath="//div[text()='Edit']/parent::div")
	public WebElement editButton;
	
	@FindBy(xpath="//div[text()='Delete']/parent::div")
	public WebElement deleteButton;
	
	@FindBy(xpath="//div[@class='text ng-star-inserted']")
	public WebElement viewTab;
	
	@FindBy(xpath="//div[@class='text'][normalize-space()='Manage']")
	public WebElement manageTab;
	
}
