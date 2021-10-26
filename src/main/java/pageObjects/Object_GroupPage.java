package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class Object_GroupPage extends BaseClass{
	
	public Object_GroupPage() {
		PageFactory.initElements(driver, this);
	}


@FindBy(xpath="//*[@class='text-truncate']")
public List<WebElement> groups;

@FindBy(xpath="//*[@class='text-truncate']")
public WebElement group1;

@FindBy(xpath="//span[text()='New Group']")
public WebElement newGroup;

@FindBy(xpath="//input[@id='name']")
public WebElement groupName;

@FindBy(xpath="//button[normalize-space()='Create']")
public WebElement createButton;

@FindBy(xpath="//span[normalize-space()='App Center Roles']")
public WebElement appCenterRoles;

@FindBy(xpath="//div[@class='d-flex flex-column justify-content-center py-3']//div[1]//div[1]//span[2]//span[1]")
public WebElement dsArrow;

@FindBy(xpath="//span[contains(text(),'Skip Review')]/parent::div/following-sibling::span[2]/child::label/child::span[2]")
public WebElement skipReviewToggler;

@FindBy(xpath="//span[contains(text(),'Manage')]/parent::div/following-sibling::span[2]/child::label/child::span[2]")
public WebElement manageToggler;

@FindBy(xpath="//span[contains(text(),'View')]/parent::div/following-sibling::span[2]/child::label/child::span[2]")
public WebElement viewToggler;

@FindBy(xpath="//button[normalize-space()='Save']")
public WebElement saveDataService;

@FindBy(xpath="//span[normalize-space()='Members']")
public WebElement members;

@FindBy(xpath="//span[normalize-space()='Users']")
public WebElement users;

@FindBy(xpath="//button[@id='importUser']")
public WebElement importUsers;

@FindBy(xpath="//div[@col-id='username']//odp-user-list-cell-renderer[@class='ng-star-inserted']")
public List<WebElement> userList;

@FindBy(xpath="//span[text()='Add User']")
public WebElement addUser;

@FindBy(xpath="//button[normalize-space()='Cancel']")
public WebElement cancel;


@FindBy(xpath="//input[@placeholder='Enter Username']")
public WebElement userName;

@FindBy(xpath="//input[@placeholder='Enter Name']")
public WebElement name;

@FindBy(xpath="//input[@placeholder='Password']")
public WebElement password;

@FindBy(xpath="//input[@placeholder='Confirm Password']")
public WebElement confirmPassword;

@FindBy(xpath="//button[normalize-space()='Add']")
public WebElement add;

@FindBy(xpath="//span[normalize-space()='(You)']")
public WebElement you;

@FindBy(xpath="//span[normalize-space()='Delete Group']")
public WebElement deleteGroup;

@FindBy(xpath="//span[normalize-space()='Delete']")
public WebElement delete;

@FindBy(xpath="//span[normalize-space()='Add User(s)']")
public WebElement addUsers;

@FindBy(xpath="//ngb-modal-window[@role='dialog']//div[@name='center']//div[@role='rowgroup']//div[1]//div[1]")
public WebElement userEmail;


@FindBy(xpath="//button[normalize-space()='Done']")
public WebElement done;

@FindBy(xpath="//div[starts-with(@class,'profile-icon')]")
public WebElement profileIcon;

@FindBy(xpath="//button[normalize-space()='Logout']")
public WebElement logout;

@FindBy(xpath="//button[normalize-space()='Save']")
public WebElement save;

@FindBy(xpath = "//span[normalize-space()='Author Roles']")
public WebElement authorRole;


@FindBy(xpath = "//div[@class='d-flex py-2 w-100 pl-3 hover justify-content-between ng-star-inserted']/span[normalize-space()='Users']")
public WebElement user;

@FindBy(xpath = "(//div[@class='label']/following-sibling::div//div[text()='Blocked'])[1]")
public WebElement blocked;

@FindBy(xpath = "(//div[@class='rounded-dropdown ng-star-inserted']//div[normalize-space()='View'])[2]")
public WebElement view;

}
