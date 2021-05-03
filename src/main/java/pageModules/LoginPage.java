package pageModules;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import base.BaseClass;
import config.DefineConstants;
import helperMethods.DropDown;
import helperMethods.JavascriptClick;
import helperMethods.JsonUtils;
import helperMethods.Log;
import helperMethods.Property;
import helperMethods.Screenshots;
import helperMethods.ScrollTypes;
import helperMethods.WaitTypes;
import junit.framework.Assert;
import pageobjects.Object_AuthorPage;
import pageobjects.Object_GroupPage;

public class LoginPage extends BaseClass{
//	private WebDriver driver;
	private WaitTypes applyWait;
	private ExtentTest test;
	private JavascriptClick javascriptClick;
	public DropDown dropdown;
	public ScrollTypes scroll;
	public Object_AuthorPage author;
	public String dataServiceName;
	public boolean flag;
	public WebElement data_serviceToggler;
	public static ArrayList<String> groups;
	public static ArrayList<String> data_Services;
	public Object_AuthorPage ap;
	public Object_GroupPage gp;
	
	public LoginPage(WebDriver driver, ExtentTest test) {
		PageFactory.initElements(driver, this);
		this.applyWait = new WaitTypes(driver);
		this.test = test;
		dropdown=new DropDown(driver);
		scroll=new ScrollTypes(driver);
		ap=new Object_AuthorPage();
		gp=new Object_GroupPage();
	}
	
	



	
	public void loginToPage() throws Exception {

		driver.get(DefineConstants.AUTHOR_URL);
		Screenshots.takeScreenshot(driver, "User opened author url ");
		
	}
	
	public void enterUserNameAndPassword(String username, String password) throws Exception {
		
		applyWait.waitForElementToBeClickable(ap.emailIDTextBox, 30).sendKeys(username);
		applyWait.waitForElementToBeClickable(ap.nextButton, 30).click();
		applyWait.waitforElementToBeDisplayed(ap.password, 30).sendKeys(password);;
		applyWait.waitforElementToBeDisplayed(ap.signInButton, 30).click();
		
	}
	
	public void verifyListOfDataServices() throws Exception {
		javascriptClick=new JavascriptClick(driver);
		applyWait.waitforElementToBeDisplayed(ap.listOfDataServices, 30);
	if (ap.listOfDataServices.isDisplayed()) {
		javascriptClick.highLighterMethod(ap.listOfDataServices);
		Screenshots.takeScreenshot(driver, "User successfully signed up");
//		test.log(Status.INFO, "User successfully signed up");
		Log.info("User successfully signed up");
		System.out.println("User log in successfully");
	}
	}
	
	public void verifyDataServiceExist(String dataService) throws InterruptedException {
		
		dataServiceName=dataService;
		List<WebElement> dataServices=driver.findElements(By.id("serviceManagerCardTitle"));
		System.out.println(dataServices.size());
		for(WebElement dataService1 : dataServices) {
			String data=dataService1.getText();
			if(data.equalsIgnoreCase(dataServiceName)) {
				System.out.println("Data Service already present  with name of "+dataServiceName);
				 data_serviceToggler=driver.findElement(By.xpath("//span[@id='serviceManagerCardTitle' and text()='"+data+"']/parent::div/parent::div/parent::div/following-sibling::div[2]/div/div[@class='toggler']"));
				flag=true;
				break;
			}
		}
	}

	public void deleteGivenDataService() throws Exception {
		if(flag==true) {
				data_serviceToggler.click();
				applyWait.waitForElementToBeClickable(ap.delete, 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(ap.delete1, 30).click();
			}
		
	}

	public void verifyDataServiceDoesNotExist() {
		if(flag==false) {
			System.out.println("Data service doesn't exist");
		}
	}
	
	public void createNewDataServices(JSONArray jsonArray, String dataService1) throws Exception {
		dataServiceName=dataService1;
		Thread.sleep(1000);
		List<WebElement> dataServices=driver.findElements(By.id("serviceManagerCardTitle"));
		data_Services=new ArrayList<String>();
		for(WebElement dataService : dataServices) {
			Thread.sleep(2000);
			String data=dataService.getText();
			data_Services.add(data);
		}
		if(flag==false) {
			
		applyWait.waitForElementToBeClickable(ap.newDataService, 30).click();
		applyWait.waitForElementToBeClickable(ap.dataServiceName, 30).sendKeys(dataServiceName);;
		applyWait.waitForElementToBeClickable(ap.createButton, 30).click();
		data_Services.add(dataServiceName);
		}
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
					
				applyWait.waitForElementToBeClickable(ap.newAttributeButton, 30).click();
				
			switch(attributeName) {
			
			case "String" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(ap.attributeNameTextbox, 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.abc, 30).click();
				applyWait.waitForElementToBeClickable(ap.text, 30).click();
				requiredAttributes(jsonProperties);

				break;
				
			case "Long Text" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(ap.attributeNameTextbox, 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.abc, 30).click();
				applyWait.waitForElementToBeClickable(ap.longText, 30).click();
				requiredAttributes(jsonProperties);
				break;
			
			case "Rich Text" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(ap.attributeNameTextbox, 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.abc, 30).click();
				applyWait.waitForElementToBeClickable(ap.richText, 30).click();
				requiredAttributes(jsonProperties);
				break;
				
			case "Secure Text" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(ap.attributeNameTextbox, 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.abc, 30).click();
				applyWait.waitForElementToBeClickable(ap.secureText, 30).click();
				requiredAttributes(jsonProperties);
				break;
				
			case "Email" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(ap.attributeNameTextbox, 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.abc, 30).click();
				applyWait.waitForElementToBeClickable(ap.email, 30).click();
				requiredAttributes(jsonProperties);
				break;
				
			case "List of values" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(ap.attributeNameTextbox, 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.abc, 30).click();
				applyWait.waitForElementToBeClickable(ap.listOfValue, 30).click();
				requiredAttributes(jsonProperties);
				break;
			
			case "Number" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(ap.attributeNameTextbox, 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.number, 30).click();
				applyWait.waitForElementToBeClickable(ap.number1, 30).click();
				requiredAttributes(jsonProperties);
				break;
				
			case "Currency" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(ap.attributeNameTextbox, 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.number, 30).click();
				applyWait.waitForElementToBeClickable(ap.currency, 30).click();
				requiredAttributes(jsonProperties);
				break;
				
			case "Boolean" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(ap.attributeNameTextbox, 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.booleanData, 30).click();
				requiredAttributes(jsonProperties);
				break;
				
				case "Date" : 
					jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(ap.attributeNameTextbox, 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(ap.date, 30).click();
				requiredAttributes(jsonProperties);
				break;
				
				
				case "Object" : 
					jsonProperties = (JSONObject) attribute.get("properties");
					applyWait.waitForElementToBeClickable(ap.attributeNameTextbox, 30).sendKeys(jsonProperties.get("name").toString());
				
					applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
					applyWait.waitForElementToBeClickable(ap.group, 30).click();
					requiredAttributes(jsonProperties);
					JSONArray objectArray=(JSONArray) attribute.get("definition");
					
					for(int k=0;k<objectArray.size();k++) {
						
						JSONObject object = (JSONObject) objectArray.get(k);
						String objectAttributeName = attribute.get("type").toString();
						String objectKey = object.get("key").toString();
					if(k!=0) {
						applyWait.waitForElementToBeClickable(ap.newAttributeButton1, 30).click();
					}
					JSONObject jsonProperty = (JSONObject) object.get("properties");
					applyWait.waitForElementToBeClickable(ap.attributeTextBox, 30).sendKeys(jsonProperty.get("name").toString());
					groupAttributes((String) object.get("type"),object);

					}
					break;
					
					case "Geojson" : 
						jsonProperties = (JSONObject) attribute.get("properties");
					applyWait.waitForElementToBeClickable(ap.attributeNameTextbox, 30).sendKeys(jsonProperties.get("name").toString());
				
					applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
					applyWait.waitForElementToBeClickable(ap.location, 30).click();
					applyWait.waitForElementToBeClickable(ap.pointOnAMap, 30).click();
					requiredAttributes(jsonProperties);
					break;
					
					
					case "Array" : 
						jsonProperties = (JSONObject) attribute.get("properties");
					applyWait.waitForElementToBeClickable(ap.attributeNameTextbox, 30).sendKeys(jsonProperties.get("name").toString());
				
					applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
					applyWait.waitForElementToBeClickable(ap.collection, 30).click();
					requiredAttributes(jsonProperties);
					
					JSONArray collectionArray=(JSONArray) attribute.get("definition");
					for(int c=0;c<collectionArray.size();c++) {
						JSONObject object = (JSONObject) collectionArray.get(c);
						String objectAttributeName = object.get("type").toString();
						collectionTypes(objectAttributeName,object);
						
					}
					
					break;
					
					case "File" : 
						jsonProperties = (JSONObject) attribute.get("properties");
						applyWait.waitForElementToBeClickable(ap.attributeNameTextbox, 30).sendKeys(jsonProperties.get("name").toString());
					
						applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
						applyWait.waitForElementToBeClickable(ap.file, 30).click();
						requiredAttributes(jsonProperties);
						break;
			
					case "Library" : 
						jsonProperties = (JSONObject) attribute.get("properties");
						applyWait.waitForElementToBeClickable(ap.attributeNameTextbox, 30).sendKeys(jsonProperties.get("name").toString());
					
						applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
						applyWait.waitForElementToBeClickable(ap.library, 30).click();
						requiredAttributes(jsonProperties);
						break;
					
					case "User" : 
						jsonProperties = (JSONObject) attribute.get("properties");
						applyWait.waitForElementToBeClickable(ap.attributeNameTextbox, 30).sendKeys(jsonProperties.get("name").toString());
						
						applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
						applyWait.waitForElementToBeClickable(ap.user, 30).click();
						requiredAttributes(jsonProperties);
						jsonProperties = (JSONObject) attribute.get("properties");
						JSONArray searchArray=(JSONArray) jsonProperties.get("relatedViewFields");
						
						for (int a = 0; a < searchArray.size(); a++) {
							JSONObject searchObject = (JSONObject) searchArray.get(a);
							String  field=(String) searchObject.get("name");
						dropdown.selectByVisibleText(ap.viewField, field);
						
						}
						break;
			}
				}
			}
			applyWait.waitForElementToBeClickable(ap.submitAndDeploy, 30).click();;
		}

	public void createDataService(String dataServicName) throws Exception {
		dataServiceName=dataServicName;
		applyWait.waitForElementToBeClickable(ap.newDataService, 30).click();
		applyWait.waitForElementToBeClickable(ap.dataServiceName, 30).sendKeys(dataServiceName);;
		applyWait.waitForElementToBeClickable(ap.createButton, 30).click();
		applyWait.waitForElementToBeClickable(ap.saveButton, 30).click();
	}

	public void searchDataService() throws Exception {
		applyWait.waitForElementToBeClickable(ap.search, 30).sendKeys(dataServiceName,Keys.ENTER);;
		Boolean dataService=applyWait.waitForElementToBeClickable(ap.dataServiceName1, 30).isDisplayed();
		String dataServiceActualName=applyWait.waitForElementToBeClickable(ap.dataServiceName1, 30).getText();
		if(dataService) {
			System.out.println("Given Data Service searched successfully");
		}
		javascriptClick.highLighterMethod(ap.dataServiceName1);
		Assert.assertEquals(dataServiceName, dataServiceActualName);
	}

	public void deleteDataService() throws Exception {
		List<WebElement> dataServices=driver.findElements(By.id("serviceManagerCardTitle"));
		System.out.println(dataServices.size());
		for(WebElement dataService : dataServices) {
			String data=dataService.getText();
			if(data.equals(dataServiceName)) {
				applyWait.waitForElementToBeClickable(ap.toggler, 30).click();
				applyWait.waitForElementToBeClickable(ap.delete, 30).click();
				applyWait.waitForElementToBeClickable(ap.delete1, 30).click();
			}
		}
		
	}

	public void cloneGivenDataService() throws Exception {
		driver.manage().window().maximize();
		applyWait.waitForElementToBeClickable(ap.toggler, 30).click();
		applyWait.waitForElementToBeClickable(ap.clone, 30).click();
//		scroll.scrollInToView(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_FilePath, "clone"))));
		scroll.scrollDown();
		applyWait.waitForElementToBeClickable(ap.clone, 30).click();
		applyWait.waitForElementToBeClickable(ap.saveButton, 30).click();
		
		String service=applyWait.waitForElementToBeClickable(ap.dataServiceName1, 30).getText();
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
			

			
			applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
			applyWait.waitForElementToBeClickable(ap.abc, 30).click();
			applyWait.waitForElementToBeClickable(ap.text, 30).click();
			requiredAttributes(jsonProperties);
			
			break;
			
		case "Long Text" : 
			jsonProperties = (JSONObject) attribute.get("properties");
			applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
			applyWait.waitForElementToBeClickable(ap.abc, 30).click();
			applyWait.waitForElementToBeClickable(ap.longText, 30).click();
			requiredAttributes(jsonProperties);
			break;
		
		case "Rich Text" : 
			jsonProperties = (JSONObject) attribute.get("properties");
			
			applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
			applyWait.waitForElementToBeClickable(ap.abc, 30).click();
			applyWait.waitForElementToBeClickable(ap.richText, 30).click();
			requiredAttributes(jsonProperties);
			break;
			
		case "Secure Text" : 
			jsonProperties = (JSONObject) attribute.get("properties");
			
			applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
			applyWait.waitForElementToBeClickable(ap.abc, 30).click();
			applyWait.waitForElementToBeClickable(ap.secureText, 30).click();
			requiredAttributes(jsonProperties);
			break;
			
		case "Email" : 
			jsonProperties = (JSONObject) attribute.get("properties");
			
			applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
			applyWait.waitForElementToBeClickable(ap.abc, 30).click();
			applyWait.waitForElementToBeClickable(ap.email, 30).click();
			requiredAttributes(jsonProperties);
			break;
			
		case "List of values" : 
			jsonProperties = (JSONObject) attribute.get("properties");
			
			applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
			applyWait.waitForElementToBeClickable(ap.abc, 30).click();
			applyWait.waitForElementToBeClickable(ap.listOfValue, 30).click();
			requiredAttributes(jsonProperties);
			break;
		
		case "Number" : 
			jsonProperties = (JSONObject) attribute.get("properties");
			
			applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
			applyWait.waitForElementToBeClickable(ap.number, 30).click();
			applyWait.waitForElementToBeClickable(ap.number1, 30).click();
			requiredAttributes(jsonProperties);
			break;
			
		case "Currency" : 
			jsonProperties = (JSONObject) attribute.get("properties");
			applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
			applyWait.waitForElementToBeClickable(ap.number, 30).click();
			applyWait.waitForElementToBeClickable(ap.currency, 30).click();
			requiredAttributes(jsonProperties);
			break;
			
		case "Boolean" : 
			jsonProperties = (JSONObject) attribute.get("properties");
			applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
			applyWait.waitForElementToBeClickable(ap.booleanData, 30).click();
			requiredAttributes(jsonProperties);
			break;
			
			case "Date" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				
			applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
			applyWait.waitForElementToBeClickable(ap.date, 30).click();
			requiredAttributes(jsonProperties);
			break;
			
			
			case "Object" : 
				jsonProperties = (JSONObject) attribute.get("properties");
			
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.group, 30).click();
				applyWait.waitForElementToBeClickable(ap.customLabel, 30).sendKeys(jsonProperties.get("label").toString());
				applyWait.waitForElementToBeClickable(ap.customError, 30).sendKeys(jsonProperties.get("errorMessage").toString());
				applyWait.waitForElementToBeClickable(ap.description, 30).sendKeys(jsonProperties.get("_description").toString());
				JSONArray objectArray=(JSONArray) attribute.get("definition");
				
				for(int k=0;k<objectArray.size();k++) {
					
					JSONObject object = (JSONObject) objectArray.get(k);
					String objectAttributeName = attribute.get("type").toString();
					String objectKey = object.get("key").toString();
				
			//	String attributeTextBox="(//input[@placeholder='Untitled Attribute'])[last()]";
				applyWait.waitForElementToBeClickable(ap.attributeTextBox, 30).sendKeys(object.get("key").toString());
				groupAttributes((String) object.get("type"),object);
				}
				break;
				
				case "Geojson" : 
					jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.location, 30).click();
				applyWait.waitForElementToBeClickable(ap.pointOnAMap, 30).click();
				requiredAttributes(jsonProperties);
				break;
				
				case "File" : 
					jsonProperties = (JSONObject) attribute.get("properties");
				
					applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
					applyWait.waitForElementToBeClickable(ap.file, 30).click();
					requiredAttributes(jsonProperties);
					break;
		
				case "Library" : 
					jsonProperties = (JSONObject) attribute.get("properties");
				
					applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
					applyWait.waitForElementToBeClickable(ap.library, 30).click();
					requiredAttributes(jsonProperties);
					break;
				
				case "User" : 
					jsonProperties = (JSONObject) attribute.get("properties");
					applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
					applyWait.waitForElementToBeClickable(ap.user, 30).click();
					requiredAttributes(jsonProperties);
					break;
		}
	}
		
		public void collectionTypes(String objectAttribute,JSONObject attribute) throws Exception {
			JSONObject jsonProperties;
			switch(objectAttribute) {
			
			
			case "String" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(ap.dropdownCollection, 30).click();
				applyWait.waitForElementToBeClickable(ap.abc, 30).click();
				applyWait.waitForElementToBeClickable(ap.text, 30).click();
				requiredAttributes(jsonProperties);
				break;
				
			case "Long Text" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.abc, 30).click();
				applyWait.waitForElementToBeClickable(ap.longText, 30).click();
				requiredAttributes(jsonProperties);
				break;
			
			case "Rich Text" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.abc, 30).click();
				applyWait.waitForElementToBeClickable(ap.richText, 30).click();
				requiredAttributes(jsonProperties);
				break;
				
			case "Secure Text" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.abc, 30).click();
				applyWait.waitForElementToBeClickable(ap.secureText, 30).click();
				requiredAttributes(jsonProperties);
				break;
				
			case "Email" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.abc, 30).click();
				applyWait.waitForElementToBeClickable(ap.email, 30).click();
				requiredAttributes(jsonProperties);
				break;
				
			case "List of values" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.abc, 30).click();
				applyWait.waitForElementToBeClickable(ap.listOfValue, 30).click();
				requiredAttributes(jsonProperties);
				break;
			
			case "Number" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.number, 30).click();
				applyWait.waitForElementToBeClickable(ap.number1, 30).click();
				requiredAttributes(jsonProperties);
				break;
				
			case "Currency" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.number, 30).click();
				applyWait.waitForElementToBeClickable(ap.currency, 30).click();
				requiredAttributes(jsonProperties);
				break;
				
			case "Boolean" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.booleanData, 30).click();
				requiredAttributes(jsonProperties);
				break;
				
				case "Date" : 
					jsonProperties = (JSONObject) attribute.get("properties");
					
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.date, 30).click();
				requiredAttributes(jsonProperties);
				break;
				
				
				case "Object" : 
					jsonProperties = (JSONObject) attribute.get("properties");
				
					applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
					applyWait.waitForElementToBeClickable(ap.group, 30).click();
					if(jsonProperties.containsKey("_description")) {
					applyWait.waitForElementToBeClickable(ap.description, 30).sendKeys(jsonProperties.get("_description").toString());
					}
					JSONArray objectArray=(JSONArray) attribute.get("definition");
					
					for(int k=0;k<objectArray.size();k++) {
						
						JSONObject object = (JSONObject) objectArray.get(k);
						String objectAttributeName = attribute.get("type").toString();
						String objectKey = object.get("key").toString();
						JSONObject object1=(JSONObject) object.get("properties") ;
						if(k!=0){
							applyWait.waitForElementToBeClickable(ap.newAttributeButton1, 30).click();
						}
					
					String attributeTextBox="(//input[@placeholder='Untitled Attribute'])[last()]";
					applyWait.waitForElementToBeClickable(ap.attributeTextBox, 30).sendKeys(object1.get("name").toString());
					groupAttributes((String) object.get("type"),object);
					}
					break;
					
					case "Geojson" : 
						jsonProperties = (JSONObject) attribute.get("properties");
						if(jsonProperties.get("required").toString().equals("true")) {
							applyWait.waitForElementToBeClickable(ap.required, 30).click();
						}
					applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
					applyWait.waitForElementToBeClickable(ap.location, 30).click();
					applyWait.waitForElementToBeClickable(ap.pointOnAMap, 30).click();
					requiredAttributes(jsonProperties);
					break;
					
					case "File" : 
						jsonProperties = (JSONObject) attribute.get("properties");
					
						applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
						applyWait.waitForElementToBeClickable(ap.file, 30).click();
						requiredAttributes(jsonProperties);
						break;
			
					case "Library" : 
						jsonProperties = (JSONObject) attribute.get("properties");
					
						applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
						applyWait.waitForElementToBeClickable(ap.library, 30).click();
						requiredAttributes(jsonProperties);
						break;
					
					case "User" : 
						jsonProperties = (JSONObject) attribute.get("properties");
						applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
						applyWait.waitForElementToBeClickable(ap.user, 30).click();
						applyWait.waitForElementToBeClickable(ap.customLabel, 30).sendKeys(jsonProperties.get("label").toString());
						applyWait.waitForElementToBeClickable(ap.customError, 30).sendKeys(jsonProperties.get("errorMessage").toString());
						requiredAttributes(jsonProperties);
						break;
			}
		
		

	}
						public void requiredAttributes(JSONObject jsonProperties){
							
							if(jsonProperties.containsKey("required")) {
								if(jsonProperties.get("required").toString().equals("true")) {
									applyWait.waitForElementToBeClickable(ap.required, 30).click();
								}
								}
								
								if(jsonProperties.containsKey("createOnly")) {
								if(jsonProperties.get("createOnly").toString().equals("true")) {
									applyWait.waitForElementToBeClickable(ap.createOnly, 30).click();
								}
								}
								
								if(jsonProperties.containsKey("unique")) {
									if(jsonProperties.get("unique").toString().equals("true")) {
										applyWait.waitForElementToBeClickable(ap.unique, 30).click();
									}
								}
								
								if(jsonProperties.containsKey("label")) {
									applyWait.waitForElementToBeClickable(ap.customLabel, 30).sendKeys(jsonProperties.get("label").toString());								
									}
								
								if(jsonProperties.containsKey("errorMessage")) {
										applyWait.waitForElementToBeClickable(ap.customError, 30).sendKeys(jsonProperties.get("errorMessage").toString());
									}
									
								if(jsonProperties.containsKey("defaultTimezone")) {
									applyWait.waitForElementToBeClickable(ap.defaultTimezone, 30).sendKeys(jsonProperties.get("defaultTimezone").toString(),Keys.ENTER);
									JSONArray array=(JSONArray) jsonProperties.get("supportedTimezones");
									for(int j=0; j<array.size();j++) {
										System.out.println(array.size()+"-----"+array.get(j).toString());
									applyWait.waitForElementToBeClickable(ap.supportedTimezones, 30).sendKeys(array.get(j).toString(),Keys.ENTER);
									}								}
								
								if(jsonProperties.containsKey("currency")) {
									dropdown.selectByVisibleText(ap.currencyDropdown, jsonProperties.get("currency").toString());
								}
								
								if(jsonProperties.containsKey("minlength")) {
									applyWait.waitForElementToBeClickable(ap.minValue, 30).sendKeys(jsonProperties.get("minlength").toString());
									}
								
								if(jsonProperties.containsKey("maxlength")) {
									applyWait.waitForElementToBeClickable(ap.maxValue, 30).sendKeys(jsonProperties.get("maxlength").toString());
									}
								if(jsonProperties.containsKey("min")) {
									applyWait.waitForElementToBeClickable(ap.minValue, 30).sendKeys(jsonProperties.get("min").toString());
									}
								
								if(jsonProperties.containsKey("max")) {
									applyWait.waitForElementToBeClickable(ap.maxValueNumber, 30).sendKeys(jsonProperties.get("max").toString());
									}
								
								if(jsonProperties.containsKey("tokens")) {
									applyWait.waitForElementToBeClickable(ap.tokens, 30).sendKeys(jsonProperties.get("tokens").toString());
									}
								
								if(jsonProperties.containsKey("values")) {
									applyWait.waitForElementToBeClickable(ap.values, 30).sendKeys(jsonProperties.get("values").toString());
									}
								
								if(jsonProperties.containsKey("pattern")) {
									applyWait.waitForElementToBeClickable(ap.pattern, 30).sendKeys(jsonProperties.get("pattern").toString());									}
								
								
								if(jsonProperties.containsKey("_description")) {
									applyWait.waitForElementToBeClickable(ap.description, 30).sendKeys(jsonProperties.get("_description").toString());
									}
								
								
								
							
						}

						

						public void verifyGroupExists(String groupName) throws Exception {
							
							applyWait.waitForElementToBeClickable(ap.groups, 30).click();
							Thread.sleep(1000);
							List<WebElement> groupNames=gp.groups;
						
						groups=new ArrayList<String>();
							for(WebElement group : groupNames) {
								String group1=group.getText();
								groups.add(group1);
						}
							if(groups.contains(groupName)) {
								System.out.println(groupName+" group exists");
							
						}
						}

						public void removeGroup(String groupName) throws Exception {
							Thread.sleep(1000);
							if(groups.contains(groupName)) {
								WebElement group=driver.findElement(By.xpath("//div[normalize-space()='"+groupName+"']/parent::div"));
								group.click();
								applyWait.waitForElementToBeClickable(gp.deleteGroup, 30).click();
								Thread.sleep(500);
								applyWait.waitForElementToBeClickable(gp.delete, 30).click();

							}
							
							
						}

						public void verifyGroupDoesNotExist(String groupName) throws Exception {
							Thread.sleep(1000);
							List<WebElement> groupNames=gp.groups;
							groups=new ArrayList<String>();
								for(WebElement group : groupNames) {
									String group1=group.getText();
									groups.add(group1);
							}
								if(!groups.contains(groupName)) {
									System.out.println(groupName+" group does not exists");
							}
							
						}

						public void dataServiceExists(String dataService) {
							
							if(data_Services.contains(dataService)) {
								System.out.println(dataService+" Data service exists");
						}
							
						}

						public void createGroupAndEnableRole(String groupName, String role, String dataservice) throws Exception {
							Thread.sleep(1000);
							List<WebElement> groupNames=gp.groups;
							ArrayList<String> groups=new ArrayList<String>();
								for(WebElement group : groupNames) {
									String group1=group.getText();
									groups.add(group1);
							}
								if(!groups.contains(groupName)) {
									System.out.println(groupName+" group not contain");
									System.out.println("Creating "+groupName+" group");
									applyWait.waitForElementToBeClickable(gp.newGroup, 30).click();
									applyWait.waitForElementToBeClickable(gp.groupName, 30).sendKeys(groupName);;
									applyWait.waitForElementToBeClickable(gp.createButton, 30).click();
									
//								
									applyWait.waitForElementToBeClickable(gp.appCenterRoles, 30).click();
									Thread.sleep(2000);
									WebElement dsArrow=driver.findElement(By.xpath("//span[normalize-space()='"+dataservice+"']/parent::div/following-sibling::span[2]/child::span"));
									applyWait.waitForElementToBeClickable(dsArrow, 30).click();
									
									if(role.equalsIgnoreCase("SkipReview")) {
										applyWait.waitForElementToBeClickable(gp.skipReviewToggler, 30).click();
										}
									if(role.equalsIgnoreCase("Manage")) {
										applyWait.waitForElementToBeClickable(gp.manageToggler, 30).click();
									}
									
									if(role.equalsIgnoreCase("View")) {
										applyWait.waitForElementToBeClickable(gp.viewToggler, 30).click();
										}
									
									applyWait.waitForElementToBeClickable(gp.saveDataService, 30).click();
								}
							
						}

						public void groupExists(String groupName) {
							if(groups.contains(groupName)) {
								System.out.println(groupName+" group exists");
							
						}							
						}

						public void VerifyUserExists(String userEmail) throws Exception {
							applyWait.waitForElementToBeClickable(gp.users, 30).click();
							Thread.sleep(1000);
							List<WebElement> userList=gp.userList;
							ArrayList<String> users=new ArrayList<String>();
							for(WebElement user : userList) {
								String user1=user.getText();
								users.add(user1);
						}
							if(!users.contains(userEmail)) {
								System.out.println(userEmail+" not contain");
								applyWait.waitForElementToBeClickable(gp.addUser, 30).click();
								applyWait.waitForElementToBeClickable(gp.userName, 30).sendKeys(userEmail);;
								applyWait.waitForElementToBeClickable(gp.password, 30).sendKeys(Property.getProperty("password"));;
								applyWait.waitForElementToBeClickable(gp.confirmPassword, 30).sendKeys(Property.getProperty("password"));;
								Thread.sleep(1000);
								applyWait.waitForElementToBeClickable(gp.name, 30).sendKeys("Test");;
								
								

								applyWait.waitForElementToBeClickable(gp.add, 30).click();
							}
							else {
								System.out.println(userEmail+" user contain");
							}
							
						}

						public void addUserToGroup(String userEmail, String group) throws Exception {
							
							applyWait.waitForElementToBeClickable(ap.groups, 30).click();
							Thread.sleep(1000);
							WebElement groupName=driver.findElement(By.xpath("//div[normalize-space()='"+group+"']/parent::div"));
							try {
							applyWait.waitForElementToBeClickable(groupName, 30).click();
							}
							catch(StaleElementReferenceException  e) {
								groupName=driver.findElement(By.xpath("//div[normalize-space()='"+group+"']/parent::div"));
								applyWait.waitForElementToBeClickable(groupName, 30).click();
							}
							Thread.sleep(1000);
							applyWait.waitForElementToBeClickable(gp.members, 30).click();
							applyWait.waitForElementToBeClickable(gp.addUsers, 30).click();
							Thread.sleep(1000);
							WebElement user=driver.findElement(By.xpath("//odp-user-list-cell-renderer[normalize-space()='"+userEmail+"']"));
							user.click();
							applyWait.waitForElementToBeClickable(gp.you, 30).click();
							applyWait.waitForElementToBeClickable(gp.done, 30).click();
							applyWait.waitForElementToBeClickable(gp.save, 30).click();

							
						}

						public void logOutFromAuthor() {
							applyWait.waitForElementToBeClickable(gp.profileIcon, 30).click();
							applyWait.waitForElementToBeClickable(gp.logout, 30).click();

							

						}

						public void logsOutOfAuthor() throws Exception {
							Thread.sleep(1000);
							WebElement signInText=ap.signIn;
							System.out.println(signInText.getText());
							Boolean verify=signInText.isDisplayed();
							if(verify) {
								System.out.println("User logs out of Author");
							}
						}

						public void navigateToAppcenterLoginPage() {
							
							
							
							
						}
							
						
		
		
}

