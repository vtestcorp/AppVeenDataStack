package pageobjects;

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

@FindBy(xpath="//span[text()='New Group']")
public WebElement newGroup;

@FindBy(xpath="//input[@id='name']")
public WebElement groupName;

@FindBy(xpath="//button[normalize-space()='Create']")
public WebElement createButton;

@FindBy(xpath="//span[normalize-space()='App Center Roles']")
public WebElement appCenterRoles;

@FindBy(xpath="//span[normalize-space()='Skip Review']/parent::div/following-sibling::span[2]/child::label/child::span[2]")
public WebElement skipReviewToggler;

@FindBy(xpath="//span[normalize-space()='Manage']/parent::div/following-sibling::span[2]/child::label/child::span[2]")
public WebElement manageToggler;

@FindBy(xpath="//span[normalize-space()='View']/parent::div/following-sibling::span[2]/child::label/child::span[2]")
public WebElement viewToggler;

@FindBy(xpath="//button[normalize-space()='Save']")
public WebElement saveDataService;

@FindBy(xpath="//span[normalize-space()='Members']")
public WebElement members;

@FindBy(xpath="//span[normalize-space()='Users']")
public WebElement users;

@FindBy(xpath="//odp-user-list-cell-renderer[@class='ng-star-inserted']")
public List<WebElement> userList;

@FindBy(xpath="//span[text()='Add User']")
public WebElement addUser;

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

@FindBy(xpath="//button[normalize-space()='Done']")
public WebElement done;

@FindBy(xpath="//div[starts-with(@class,'profile-icon')]")
public WebElement profileIcon;

@FindBy(xpath="//button[normalize-space()='Logout']")
public WebElement logout;

@FindBy(xpath="//button[normalize-space()='Save']")
public WebElement save;



}
