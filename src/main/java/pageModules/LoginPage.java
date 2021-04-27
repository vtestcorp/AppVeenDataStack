package pageModules;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.BaseClass;
import config.DefineConstants;
import gherkin.deps.com.google.gson.internal.bind.SqlDateTypeAdapter;
import helperMethods.DropDown;
import helperMethods.JavascriptClick;
import helperMethods.JsonUtils;
import helperMethods.Log;
import helperMethods.Property;
import helperMethods.Screenshots;
import helperMethods.ScrollTypes;
import helperMethods.WaitTypes;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class LoginPage extends BaseClass{
	private WebDriver driver;
	private WaitTypes applyWait;
	private ExtentTest test;
	private JavascriptClick javascriptClick;
	public DropDown dropdown;
	public ScrollTypes scroll;
	String dataServiceName;
	int attributeValue;
	
	public LoginPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.applyWait = new WaitTypes(driver);
		this.test = test;
		dropdown=new DropDown(driver);
		scroll=new ScrollTypes(driver);
	}
//	@FindBy(xpa JsonUtils.getData(DefineConstants.json_FilePath, "emailIDTextBox"))
//	private WebElement emailIDTextBox;
	
	public void loginToPage() throws Exception {

	//	start();
//		this.driver=getDriver();
//		applyWait = new WaitTypes(driver);
//		dropdown=new DropDown(driver);
//		scroll=new ScrollTypes(driver);
		driver.get(DefineConstants.AUTHOR_URL);
		Screenshots.takeScreenshot(driver, "User opened author url ");
		
	}
	
	public void enterUserNameAndPassword(String username, String password) throws Exception {
		
		applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "emailIDTextBox"))), 30).sendKeys(username);
		applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "nextButton"))), 30).click();
		Thread.sleep(2000);
		applyWait.waitforElementToBeDisplayed(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "password"))), 30).sendKeys(password);;
		applyWait.waitforElementToBeDisplayed(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "signInButton"))), 30).click();
		
	}
	
	public void verifyListOfDataServices() throws Exception {
		javascriptClick=new JavascriptClick(driver);
		Thread.sleep(7000);
		applyWait.waitforElementToBeDisplayed(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "listOfDataServices"))), 30);
	if (driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "listOfDataServices"))).isDisplayed()) {
		javascriptClick.highLighterMethod(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "listOfDataServices"))));
		Screenshots.takeScreenshot(driver, "User successfully signed up");
//		test.log(Status.INFO, "User successfully signed up");
		Log.info("User successfully signed up");
		System.out.println("User log in successfully");
	}
	}
	
	public void createNewDataServices(JSONArray jsonArray, String dataService1) throws Exception {
		dataServiceName=dataService1;
		List<WebElement> dataServices=driver.findElements(By.id("serviceManagerCardTitle"));
		System.out.println(dataServices.size());
		for(WebElement dataService : dataServices) {
			Thread.sleep(1000);
			String data=dataService.getText();
			if(data.equalsIgnoreCase(dataServiceName)) {
				System.out.println("Data Service already present  with name of "+dataServiceName);
				WebElement element=driver.findElement(By.xpath("//span[@id='serviceManagerCardTitle' and text()='"+data+"']/parent::div/parent::div/parent::div/following-sibling::div[2]/div/div[@class='toggler']"));
				element.click();
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "delete"))), 30).click();
				Thread.sleep(2000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "delete1"))), 30).click();
				Thread.sleep(5000);
			}
		}
			
		applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "newDataService"))), 30).click();
		applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dataServiceName"))), 30).sendKeys(dataServiceName);;
		applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "createButton"))), 30).click();
		Thread.sleep(10000);
			
		try {
			jsonArray.size();
		}
		catch (Exception e) {
		      System.err.println("Data Service file not found");
		    }
			for (int i = 0; i < jsonArray.size(); i++) {
				
				JSONObject jsonProperties;
				JSONObject attribute = (JSONObject) jsonArray.get(i);
				String attributeName = attribute.get("type").toString();
				String keyName = attribute.get("key").toString();
				
				if(!keyName.equals("_id")) {
					
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "newAttributeButton"))), 30).click();
				
			switch(attributeName) {
			
			case "String" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "abc"))), 30).click();
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "text"))), 30).click();
				requiredAttributes(jsonProperties);

				break;
				
			case "Long Text" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "abc"))), 30).click();
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "longText"))), 30).click();
				requiredAttributes(jsonProperties);
				break;
			
			case "Rich Text" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "abc"))), 30).click();
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "richText"))), 30).click();
				requiredAttributes(jsonProperties);
				break;
				
			case "Secure Text" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "abc"))), 30).click();
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "secureText"))), 30).click();
				requiredAttributes(jsonProperties);
				break;
				
			case "Email" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "abc"))), 30).click();
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "email"))), 30).click();
				requiredAttributes(jsonProperties);
				break;
				
			case "List of values" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "abc"))), 30).click();
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "listOfValue"))), 30).click();
				requiredAttributes(jsonProperties);
				break;
			
			case "Number" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "number"))), 30).click();
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "number1"))), 30).click();
				Thread.sleep(1000);
				requiredAttributes(jsonProperties);
				break;
				
			case "Currency" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "number"))), 30).click();
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "currency"))), 30).click();
				Thread.sleep(3000);
				requiredAttributes(jsonProperties);
				break;
				
			case "Boolean" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "boolean"))), 30).click();
				Thread.sleep(500);
				requiredAttributes(jsonProperties);
				break;
				
				case "Date" : 
					jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "date"))), 30).click();
				Thread.sleep(500);
				requiredAttributes(jsonProperties);
//				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "defaultTimezone"))), 30).sendKeys(jsonProperties.get("defaultTimezone").toString(),Keys.ENTER);
//				JSONArray array=(JSONArray) jsonProperties.get("supportedTimezones");
//				for(int j=0; j<array.size();j++) {
//					System.out.println(array.size()+"-----"+array.get(j).toString());
//				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "supportedTimezones"))), 30).sendKeys(array.get(j).toString(),Keys.ENTER);
//				}
		//		applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "defaultValue"))), 30).sendKeys(attribute.get("Default Value").toString());
			//	applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "description"))), 30).sendKeys(jsonProperties.get("_description").toString());
				break;
				
				
				case "Object" : 
					jsonProperties = (JSONObject) attribute.get("properties");
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
				
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
					Thread.sleep(1000);
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "group"))), 30).click();
					Thread.sleep(500);
					requiredAttributes(jsonProperties);
					JSONArray objectArray=(JSONArray) attribute.get("definition");
					
					for(int k=0;k<objectArray.size();k++) {
						
						JSONObject object = (JSONObject) objectArray.get(k);
						String objectAttributeName = attribute.get("type").toString();
						String objectKey = object.get("key").toString();
					if(k!=0) {
						applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "newAttributeButton1"))), 30).click();
					}
					String attributeTextBox="(//input[@placeholder='Untitled Attribute'])[last()]";
					JSONObject jsonProperty = (JSONObject) object.get("properties");
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(attributeTextBox)), 30).sendKeys(jsonProperty.get("name").toString());
					groupAttributes((String) object.get("type"),object);

					Thread.sleep(1000);
					}
					break;
					
					case "Geojson" : 
						jsonProperties = (JSONObject) attribute.get("properties");
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
				
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
					Thread.sleep(1000);
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "location"))), 30).click();
					Thread.sleep(500);
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "pointOnAMap"))), 30).click();
					Thread.sleep(500);
					requiredAttributes(jsonProperties);
					break;
					
					
					case "Array" : 
						jsonProperties = (JSONObject) attribute.get("properties");
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
				
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
					Thread.sleep(1000);
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "collection"))), 30).click();
					Thread.sleep(500);
					requiredAttributes(jsonProperties);
					
					JSONArray collectionArray=(JSONArray) attribute.get("definition");
					for(int c=0;c<collectionArray.size();c++) {
		//				System.out.println(collectionArray.size()+"===========");
						JSONObject object = (JSONObject) collectionArray.get(c);
						String objectAttributeName = object.get("type").toString();
						collectionTypes(objectAttributeName,object);
						
					}
					
					break;
					
					case "File" : 
						jsonProperties = (JSONObject) attribute.get("properties");
						applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
					
						applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
						Thread.sleep(1000);
						applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "file"))), 30).click();
						Thread.sleep(500);
						requiredAttributes(jsonProperties);
						break;
			
					case "Library" : 
						jsonProperties = (JSONObject) attribute.get("properties");
						applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
					
						applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
						Thread.sleep(1000);
						applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "library"))), 30).click();
						Thread.sleep(500);
						requiredAttributes(jsonProperties);
						break;
					
					case "User" : 
						jsonProperties = (JSONObject) attribute.get("properties");
						applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
						
						applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
						Thread.sleep(1000);
						applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "user"))), 30).click();
						Thread.sleep(3000);
						requiredAttributes(jsonProperties);
						jsonProperties = (JSONObject) attribute.get("properties");
		//				dropdown.selectByVisibleText(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "searchOnField"))), jsonProperties.get("Search On Field").toString());
						JSONArray searchArray=(JSONArray) jsonProperties.get("relatedViewFields");
						
						for (int a = 0; a < searchArray.size(); a++) {
							JSONObject searchObject = (JSONObject) searchArray.get(a);
							String  field=(String) searchObject.get("name");
						Thread.sleep(500);
						dropdown.selectByVisibleText(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "viewField"))), field);
						
						}
						break;
			}
				}
			}
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "submitAndDeploy"))), 30).click();;
		}

	public void createDataService(String dataServicName) throws Exception {
		dataServiceName=dataServicName;
		applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "newDataService"))), 30).click();
		applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dataServiceName"))), 30).sendKeys(dataServiceName);;
		applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "createButton"))), 30).click();
		Thread.sleep(10000);
		applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "saveButton"))), 30).click();
	}

	public void searchDataService() throws Exception {
		Thread.sleep(3000);
		applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "search"))), 30).sendKeys(dataServiceName,Keys.ENTER);;
		Thread.sleep(5000);
		Boolean dataService=applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dataServiceName1"))), 30).isDisplayed();
		String dataServiceActualName=applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dataServiceName1"))), 30).getText();
		if(dataService) {
			System.out.println("Given Data Service searched successfully");
		}
		javascriptClick.highLighterMethod(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dataServiceName1"))));
		Assert.assertEquals(dataServiceName, dataServiceActualName);
	}

	public void deleteDataService() throws Exception {
		List<WebElement> dataServices=driver.findElements(By.id("serviceManagerCardTitle"));
		System.out.println(dataServices.size());
		for(WebElement dataService : dataServices) {
			String data=dataService.getText();
			if(data.equals(dataServiceName)) {
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "toggler"))), 30).click();
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "delete"))), 30).click();
				Thread.sleep(2000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "delete1"))), 30).click();
			}
		}
		
	}

	public void cloneGivenDataService() throws Exception {
		Thread.sleep(10000);
		driver.manage().window().maximize();
		applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "toggler"))), 30).click();
		applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "clone"))), 30).click();
		Thread.sleep(10000);
//		scroll.scrollInToView(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "clone"))));
		scroll.scrollDown();
		applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "clone"))), 30).click();
		Thread.sleep(10000);
		applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "saveButton"))), 30).click();
		
		String service=applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dataServiceName1"))), 30).getText();
//		System.out.println(dataServiceName+"========"+service);
		if(service.contains(dataServiceName)) {
			System.out.println("User can clone given Data Service");
		}
	}
	
	
	public void groupAttributes(String objectAttribute,JSONObject attribute) throws Exception {
		JSONObject jsonProperties;
		switch(objectAttribute) {
		
		
		case "String" : 
			jsonProperties = (JSONObject) attribute.get("properties");
			

			
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
			Thread.sleep(1000);
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "abc"))), 30).click();
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "text"))), 30).click();
			requiredAttributes(jsonProperties);
			
			break;
			
		case "Long Text" : 
			jsonProperties = (JSONObject) attribute.get("properties");
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
			Thread.sleep(1000);
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "abc"))), 30).click();
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "longText"))), 30).click();
			requiredAttributes(jsonProperties);
			break;
		
		case "Rich Text" : 
			jsonProperties = (JSONObject) attribute.get("properties");
			
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
			Thread.sleep(1000);
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "abc"))), 30).click();
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "richText"))), 30).click();
			requiredAttributes(jsonProperties);
			break;
			
		case "Secure Text" : 
			jsonProperties = (JSONObject) attribute.get("properties");
			
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
			Thread.sleep(1000);
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "abc"))), 30).click();
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "secureText"))), 30).click();
			requiredAttributes(jsonProperties);
			break;
			
		case "Email" : 
			jsonProperties = (JSONObject) attribute.get("properties");
//			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
			
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
			Thread.sleep(1000);
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "abc"))), 30).click();
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "email"))), 30).click();
			requiredAttributes(jsonProperties);
			break;
			
		case "List of values" : 
			jsonProperties = (JSONObject) attribute.get("properties");
//			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
			
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
			Thread.sleep(1000);
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "abc"))), 30).click();
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "listOfValue"))), 30).click();
			requiredAttributes(jsonProperties);
			break;
		
		case "Number" : 
			jsonProperties = (JSONObject) attribute.get("properties");
			
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
			Thread.sleep(1000);
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "number"))), 30).click();
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "number1"))), 30).click();
			Thread.sleep(3000);
			requiredAttributes(jsonProperties);
			break;
			
		case "Currency" : 
			jsonProperties = (JSONObject) attribute.get("properties");
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
			Thread.sleep(1000);
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "number"))), 30).click();
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "currency"))), 30).click();
			Thread.sleep(3000);
			requiredAttributes(jsonProperties);
			break;
			
		case "Boolean" : 
			jsonProperties = (JSONObject) attribute.get("properties");
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
			Thread.sleep(1000);
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "boolean"))), 30).click();
			Thread.sleep(500);
			requiredAttributes(jsonProperties);
			break;
			
			case "Date" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
			Thread.sleep(1000);
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "date"))), 30).click();
			Thread.sleep(500);
			requiredAttributes(jsonProperties);
			break;
			
			
			case "Object" : 
				jsonProperties = (JSONObject) attribute.get("properties");
//				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
			
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "group"))), 30).click();
				Thread.sleep(500);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "customLabel"))), 30).sendKeys(jsonProperties.get("label").toString());
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "customError"))), 30).sendKeys(jsonProperties.get("errorMessage").toString());
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "description"))), 30).sendKeys(jsonProperties.get("_description").toString());
				JSONArray objectArray=(JSONArray) attribute.get("definition");
				
				for(int k=0;k<objectArray.size();k++) {
					
					JSONObject object = (JSONObject) objectArray.get(k);
					String objectAttributeName = attribute.get("type").toString();
					String objectKey = object.get("key").toString();
				
				String attributeTextBox="(//input[@placeholder='Untitled Attribute'])[last()]";
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(attributeTextBox)), 30).sendKeys(object.get("key").toString());
				groupAttributes((String) object.get("type"),object);
				attributeValue++;
				}
				break;
				
				case "Geojson" : 
					jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "location"))), 30).click();
				Thread.sleep(500);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "pointOnAMap"))), 30).click();
				Thread.sleep(500);
				requiredAttributes(jsonProperties);
				break;
				
				case "File" : 
					jsonProperties = (JSONObject) attribute.get("properties");
//					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
				
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
					Thread.sleep(1000);
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "file"))), 30).click();
					Thread.sleep(500);
					requiredAttributes(jsonProperties);
					break;
		
				case "Library" : 
					jsonProperties = (JSONObject) attribute.get("properties");
//					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
				
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
					Thread.sleep(1000);
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "library"))), 30).click();
					Thread.sleep(500);
					requiredAttributes(jsonProperties);
					break;
				
				case "User" : 
					jsonProperties = (JSONObject) attribute.get("properties");
//					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "attributeNameTextbox"))), 30).sendKeys(jsonProperties.get("name").toString());
					Thread.sleep(5000);
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
					Thread.sleep(1000);
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "user"))), 30).click();
					Thread.sleep(3000);
					requiredAttributes(jsonProperties);
					break;
		}
	}
		
		public void collectionTypes(String objectAttribute,JSONObject attribute) throws Exception {
			JSONObject jsonProperties;
			switch(objectAttribute) {
			
			
			case "String" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdownCollection"))), 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "abc"))), 30).click();
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "text"))), 30).click();
				requiredAttributes(jsonProperties);
				break;
				
			case "Long Text" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "abc"))), 30).click();
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "longText"))), 30).click();
				requiredAttributes(jsonProperties);
				break;
			
			case "Rich Text" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "abc"))), 30).click();
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "richText"))), 30).click();
				requiredAttributes(jsonProperties);
				break;
				
			case "Secure Text" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "abc"))), 30).click();
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "secureText"))), 30).click();
				requiredAttributes(jsonProperties);
				break;
				
			case "Email" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "abc"))), 30).click();
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "email"))), 30).click();
				requiredAttributes(jsonProperties);
				break;
				
			case "List of values" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "abc"))), 30).click();
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "listOfValue"))), 30).click();
				requiredAttributes(jsonProperties);
				break;
			
			case "Number" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "number"))), 30).click();
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "number1"))), 30).click();
				Thread.sleep(3000);
				requiredAttributes(jsonProperties);
				break;
				
			case "Currency" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "number"))), 30).click();
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "currency"))), 30).click();
				requiredAttributes(jsonProperties);
				break;
				
			case "Boolean" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "boolean"))), 30).click();
				requiredAttributes(jsonProperties);
				break;
				
				case "Date" : 
					jsonProperties = (JSONObject) attribute.get("properties");
					
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "date"))), 30).click();
				Thread.sleep(500);
				requiredAttributes(jsonProperties);
				break;
				
				
				case "Object" : 
					jsonProperties = (JSONObject) attribute.get("properties");
				
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
					Thread.sleep(1000);
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "group"))), 30).click();
					Thread.sleep(1000);
					if(jsonProperties.containsKey("_description")) {
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "description"))), 30).sendKeys(jsonProperties.get("_description").toString());
					}
					JSONArray objectArray=(JSONArray) attribute.get("definition");
					
					for(int k=0;k<objectArray.size();k++) {
						
						JSONObject object = (JSONObject) objectArray.get(k);
						String objectAttributeName = attribute.get("type").toString();
						String objectKey = object.get("key").toString();
						JSONObject object1=(JSONObject) object.get("properties") ;
						if(k!=0){
							applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "newAttributeButton1"))), 30).click();
						}
					
					String attributeTextBox="(//input[@placeholder='Untitled Attribute'])[last()]";
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(attributeTextBox)), 30).sendKeys(object1.get("name").toString());
					groupAttributes((String) object.get("type"),object);
					}
					break;
					
					case "Geojson" : 
						jsonProperties = (JSONObject) attribute.get("properties");
						if(jsonProperties.get("required").toString().equals("true")) {
							applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "required"))), 30).click();
						}
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
					Thread.sleep(1000);
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "location"))), 30).click();
					Thread.sleep(500);
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "pointOnAMap"))), 30).click();
					Thread.sleep(500);
					requiredAttributes(jsonProperties);
					break;
					
					case "File" : 
						jsonProperties = (JSONObject) attribute.get("properties");
					
						applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
						Thread.sleep(1000);
						applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "file"))), 30).click();
						Thread.sleep(500);
						requiredAttributes(jsonProperties);
						break;
			
					case "Library" : 
						jsonProperties = (JSONObject) attribute.get("properties");
					
						applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
						Thread.sleep(1000);
						applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "library"))), 30).click();
						Thread.sleep(500);
						requiredAttributes(jsonProperties);
						break;
					
					case "User" : 
						jsonProperties = (JSONObject) attribute.get("properties");
						Thread.sleep(5000);
						applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "dropdown"))), 30).click();
						Thread.sleep(1000);
						applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "user"))), 30).click();
						Thread.sleep(3000);
						applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "customLabel"))), 30).sendKeys(jsonProperties.get("label").toString());
						applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "customError"))), 30).sendKeys(jsonProperties.get("errorMessage").toString());
						requiredAttributes(jsonProperties);
						break;
			}
		
		

	}
						public void requiredAttributes(JSONObject jsonProperties){
							
							if(jsonProperties.containsKey("required")) {
								if(jsonProperties.get("required").toString().equals("true")) {
									applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "required"))), 30).click();
								}
								}
								
								if(jsonProperties.containsKey("createOnly")) {
								if(jsonProperties.get("createOnly").toString().equals("true")) {
									applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "createOnly"))), 30).click();
								}
								}
								
								if(jsonProperties.containsKey("unique")) {
									if(jsonProperties.get("unique").toString().equals("true")) {
										applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "unique"))), 30).click();
									}
								}
								
								if(jsonProperties.containsKey("label")) {
									applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "customLabel"))), 30).sendKeys(jsonProperties.get("label").toString());								
									}
								
								if(jsonProperties.containsKey("errorMessage")) {
										applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "customError"))), 30).sendKeys(jsonProperties.get("errorMessage").toString());
									}
									
								if(jsonProperties.containsKey("defaultTimezone")) {
									applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "defaultTimezone"))), 30).sendKeys(jsonProperties.get("defaultTimezone").toString(),Keys.ENTER);
									JSONArray array=(JSONArray) jsonProperties.get("supportedTimezones");
									for(int j=0; j<array.size();j++) {
										System.out.println(array.size()+"-----"+array.get(j).toString());
									applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "supportedTimezones"))), 30).sendKeys(array.get(j).toString(),Keys.ENTER);
									}								}
								
								if(jsonProperties.containsKey("currency")) {
									dropdown.selectByVisibleText(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "currencyDropdown"))), jsonProperties.get("currency").toString());
								}
								
								if(jsonProperties.containsKey("minlength")) {
									applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "minValue"))), 30).sendKeys(jsonProperties.get("minlength").toString());
									}
								
								if(jsonProperties.containsKey("maxlength")) {
									applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "maxValue"))), 30).sendKeys(jsonProperties.get("maxlength").toString());
									}
								if(jsonProperties.containsKey("min")) {
									applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "minValue"))), 30).sendKeys(jsonProperties.get("min").toString());
									}
								
								if(jsonProperties.containsKey("max")) {
									applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "maxValueNumber"))), 30).sendKeys(jsonProperties.get("max").toString());
									}
								
								if(jsonProperties.containsKey("tokens")) {
									applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "tokens"))), 30).sendKeys(jsonProperties.get("tokens").toString());
									}
								
								if(jsonProperties.containsKey("values")) {
									applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "values"))), 30).sendKeys(jsonProperties.get("values").toString());
									}
								
								if(jsonProperties.containsKey("pattern")) {
									applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "pattern"))), 30).sendKeys(jsonProperties.get("pattern").toString());									}
								
								
								if(jsonProperties.containsKey("_description")) {
									applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "description"))), 30).sendKeys(jsonProperties.get("_description").toString());
									}
								
								
								
							
						}

						public void connectAuthorToAppcenter(String data_Service) throws Exception {
							Thread.sleep(2000);
							this.driver=getDriver();
							applyWait = new WaitTypes(driver);
							
//							applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_GroupPage_FilePath, "users"))), 30).click();
//							Thread.sleep(2000);
//							String userName=Property.getProperty("userEmail");
//							System.out.println(userName);
//							List<WebElement> userList=driver.findElements(By.xpath(JsonUtils.getData(DefineConstants.json_GroupPage_FilePath, "userList")));
//							System.out.println(userList.size());
//							ArrayList<String> users=new ArrayList<String>();
//							for(WebElement user : userList) {
//								String user1=user.getText();
//								users.add(user1);
//						}
//							if(!users.contains(userName)) {
//								System.out.println(userName+" not contain");
//								Thread.sleep(2000);
//								applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_GroupPage_FilePath, "addUser"))), 30).click();
//								Thread.sleep(1000);
//								applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_GroupPage_FilePath, "userName"))), 30).sendKeys(Property.getProperty("userEmail"));;
//								Thread.sleep(1000);
//								System.out.println(Property.getProperty("userName"));
//								applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_GroupPage_FilePath, "name"))), 30).sendKeys(Property.getProperty("userName"));;
//								Thread.sleep(1000);
//								
//								applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_GroupPage_FilePath, "password"))), 30).sendKeys(Property.getProperty("password"));;
//								applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_GroupPage_FilePath, "confirmPassword"))), 30).sendKeys(Property.getProperty("password"));;
//
//								applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_GroupPage_FilePath, "add"))), 30).click();
//							}
//							else {
//								System.out.println(userName+" user contain");
//							}
							
							applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "groups"))), 30).click();
							Thread.sleep(5000);
							List<WebElement> groupNames=driver.findElements(By.xpath(JsonUtils.getData(DefineConstants.json_GroupPage_FilePath, "groups")));
						String groupName=Property.getProperty("group");
						ArrayList<String> groups=new ArrayList<String>();
							for(WebElement group : groupNames) {
								String group1=group.getText();
								groups.add(group1);
						}
							if(!groups.contains(groupName)) {
								System.out.println(groupName+" group not contain");
								System.out.println("Creating "+groupName+" group");
								applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_GroupPage_FilePath, "newGroup"))), 30).click();
								applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_GroupPage_FilePath, "groupName"))), 30).sendKeys(groupName);;
								applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_GroupPage_FilePath, "createButton"))), 30).click();
								
							}
							else {
								System.out.println(groupName+" group contain");
							}
							Thread.sleep(2000);
							WebElement group=driver.findElement(By.xpath("//div[normalize-space()='"+groupName+"']/parent::div"));
							Thread.sleep(2000);
							try {
							applyWait.waitForElementToBeClickable(group, 30).click();
							}
							catch(StaleElementReferenceException  e) {
								group=driver.findElement(By.xpath("//div[normalize-space()='"+groupName+"']/parent::div"));
								applyWait.waitForElementToBeClickable(group, 30).click();
							}
							Thread.sleep(3000);
							applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_GroupPage_FilePath, "appCenterRoles"))), 30).click();
							Thread.sleep(3000);
							WebElement dsArrow=driver.findElement(By.xpath("//span[normalize-space()='"+data_Service+"']/parent::div/following-sibling::span[2]/child::span"));
							applyWait.waitForElementToBeClickable(dsArrow, 30).click();
							Thread.sleep(3000);
							if(Property.getProperty("skip").equalsIgnoreCase("yes")) {
								applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_GroupPage_FilePath, "skipReviewToggler"))), 30).click();
								}
							if(Property.getProperty("manage").equalsIgnoreCase("yes")) {
								applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_GroupPage_FilePath, "manageToggler"))), 30).click();
							}
							
							if(Property.getProperty("view").equalsIgnoreCase("yes")) {
								applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_GroupPage_FilePath, "viewToggler"))), 30).click();
								}
							
							applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_GroupPage_FilePath, "members"))), 30).click();
							applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_GroupPage_FilePath, "saveDataService"))), 30).click();
							
						}
		
		
}
