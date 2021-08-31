package pageModules;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.google.gson.JsonObject;

import base.BaseClass;
import helperMethods.DropDown;
import helperMethods.JavascriptClick;
import helperMethods.JsonUtils;
import helperMethods.Log;
import helperMethods.Property;
import helperMethods.Screenshots;
import helperMethods.ScrollTypes;
import helperMethods.WaitTypes;
import junit.framework.Assert;
import pageObjects.Object_AuthorPage;
import pageObjects.Object_GroupPage;

public class LoginPage extends BaseClass{
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
	public static	String anotherDataService;
	public static boolean isRelation;
	public static boolean isDateTime;
	public static boolean isType;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.applyWait = new WaitTypes(driver);
		javascriptClick=new JavascriptClick(driver);
		dropdown=new DropDown(driver);
		scroll=new ScrollTypes(driver);
		ap=new Object_AuthorPage();
		gp=new Object_GroupPage();
	}
	
	
	public void loginToPage() throws Exception {

		driver.get(author_URL);
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
		Log.info("User successfully signed up");
	}
	}
	
	public void verifyDataServiceExist(String dataService) throws InterruptedException {
		
		dataServiceName=dataService;
		applyExplicitWaitsUntilElementVisible(ap.listOfDataService, 20);
		for(WebElement dataService1 : ap.listOfDataService) {
			String data=dataService1.getText();
			if(data.equalsIgnoreCase(dataServiceName)) {
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
				 
				try {
					applyWait.waitForElementToBeClickable(ap.delete1, 30).click();
				} catch (ElementClickInterceptedException e) {
					Thread.sleep(1000);
					applyWait.waitForElementToBeClickable(ap.delete1, 30).click();
				}
			}
	}

	public void verifyDataServiceDoesNotExist() {
		if(flag==false) {
		}
	}
	
	public void createNewDataServices(JSONArray jsonArray, String dataService1) throws Exception {
		dataServiceName=dataService1;
		 Thread.sleep(3000); 
		applyExplicitWaitsUntilElementVisible(ap.dataServiceName1, 10);
		List<WebElement> dataServices=driver.findElements(By.id("serviceManagerCardTitle"));
		data_Services=new ArrayList<String>();
		for(WebElement dataService : dataServices) {
			String data=dataService.getText();
			data_Services.add(data);
		}
		if(flag==false) {
		Actions action=new Actions(driver);
		action.moveToElement(ap.newDataService).perform();
		applyWait.waitForElementToBeClickable(ap.newDataService, 30).click();
		applyWait.waitForElementToBeClickable(ap.dataServiceName, 30).sendKeys(dataServiceName);;
		Thread.sleep(500);
		javascriptClick.click(ap.createButton);
		
		data_Services.add(dataServiceName);
		}
		try {
			jsonArray.size();
		}
		catch (Exception e) {
		    }
			for (int i = 0; i < jsonArray.size(); i++) {
				
				JSONObject jsonProperties;
				JSONObject attribute = (JSONObject) jsonArray.get(i);
				String attributeName = attribute.get("type").toString();
				String keyName = attribute.get("key").toString();
				
				if(!keyName.equals("_id")) {

				applyWait.applyExplicitWaitsUntilElementVisible(ap.newAttributeButton, 30);	
				applyWait.waitForElementToBeClickable(ap.newAttributeButton, 30).click();
				
			switch(attributeName) {
			
			case "String" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(ap.attributeNameTextbox, 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.abc, 30).click();
							
						if(	jsonProperties.containsKey("longText")){
							applyWait.waitForElementToBeClickable(ap.longText, 30).click();
							}
						
						else if(jsonProperties.containsKey("richText")) {
							applyWait.waitForElementToBeClickable(ap.richText, 30).click();
						}
						
						else if(jsonProperties.containsKey("email")) {
							applyWait.waitForElementToBeClickable(ap.email, 30).click();
						}
						else if(jsonProperties.containsKey("enum")) {
							applyWait.waitForElementToBeClickable(ap.listOfValue, 30).click();
						}
						
						else {
							applyWait.waitForElementToBeClickable(ap.text, 30).click();
						}
							requiredAttributes(jsonProperties);

				break;
			
			case "Number" : 
				jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(ap.attributeNameTextbox, 30).sendKeys(jsonProperties.get("name").toString());
				applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.number, 30).click();
				
				if(	jsonProperties.containsKey("currency")){
					applyWait.waitForElementToBeClickable(ap.currency, 30).click();
					}
				
				else if(jsonProperties.containsKey("enum")) {
					applyWait.waitForElementToBeClickable(ap.listOfValue, 30).click();
				}
				
				else {
					applyWait.waitForElementToBeClickable(ap.number1, 30).click();
				}
				
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
				applyWait.waitForElementToBeClickable(ap.calender, 30).click();
				
				if(jsonProperties.get("dateType").equals("date")) {
					applyWait.waitForElementToBeClickable(ap.date, 30).click();
				}
				if(jsonProperties.get("dateType").equals("datetime-local")) {
					isDateTime=true;
					applyWait.waitForElementToBeClickable(ap.dateAndTime, 30).click();
				}
				requiredAttributes(jsonProperties);
				break;
				
				
				case "Object" : 
					jsonProperties = (JSONObject) attribute.get("properties");
					applyWait.waitForElementToBeClickable(ap.attributeNameTextbox, 30).sendKeys(jsonProperties.get("name").toString());
													
					if(jsonProperties.containsKey("password")) {
						
						applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
						applyWait.waitForElementToBeClickable(ap.abc, 30).click();
						applyWait.waitForElementToBeClickable(ap.secureText, 30).click();
						requiredAttributes(jsonProperties);
						
					}
					
                  else if(jsonProperties.containsKey("relatedTo")) {
						
						applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
						applyWait.waitForElementToBeClickable(ap.relation, 30).click();
						requiredAttributes(jsonProperties);
						
						isRelation = true;
					}
                  else if(jsonProperties.containsKey("schema")) {
						
						applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
						applyWait.waitForElementToBeClickable(ap.library, 30).click();
						requiredAttributes(jsonProperties);
						
					}
					
					else {
					
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
					if(jsonProperties.containsKey("createOnly")) {
						if(jsonProperties.get("createOnly").toString().equals("true")) {
							applyWait.waitForElementToBeClickable(ap.createOnly, 30).click();
						}
						if(jsonProperties.containsKey("label")) {
							applyWait.waitForElementToBeClickable(ap.customLabel, 30).sendKeys(jsonProperties.get("label").toString());								
							}
						
						if(jsonProperties.containsKey("errorMessage")) {
								applyWait.waitForElementToBeClickable(ap.customError, 30).sendKeys(jsonProperties.get("errorMessage").toString());
							}
						}
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
					
					case "User" : 
						jsonProperties = (JSONObject) attribute.get("properties");
						applyWait.waitForElementToBeClickable(ap.attributeNameTextbox, 30).sendKeys(jsonProperties.get("name").toString());
						applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
						applyWait.waitForElementToBeClickable(ap.user, 30).click();
						jsonProperties = (JSONObject) attribute.get("properties");
						JSONArray searchArray=(JSONArray) jsonProperties.get("relatedViewFields");
						
						for (int a = 0; a < searchArray.size(); a++) {
							JSONObject searchObject = (JSONObject) searchArray.get(a);
							String  field=(String) searchObject.get("name");
							dropdown.selectByVisibleText(ap.viewField, field);
						}
						isType=true;
						requiredAttributes(jsonProperties);
						break;
			}
				}
			}
			function_ExperienceTab();
			function_RolesTab();
			applyWait.waitForElementToBeClickable(ap.submitAndDeploy, 30).click();;
			Thread.sleep(10000);
			if(isRelation) {
				System.out.println("Starting "+anotherDataService);
				By anotherDataService1=By.xpath("//span[@id='serviceManagerCardTitle' and @title='"+anotherDataService+"']");
				WebElement dsStart=driver.findElement(anotherDataService1);
				WebElement toggler=dsStart.findElement(By.xpath("./ancestor::div[3]/following-sibling::div[2]/div/div[@class='toggler']"));
				toggler.click();
				WebElement startButton=toggler.findElement(By.xpath("./following-sibling::div[6]/span[2]"));
				Thread.sleep(1000);
				if(startButton.getText().equals("Start")) {
				startButton.click();
				Thread.sleep(1000);
				applyWait.waitforElementToBeDisplayed(ap.yes, 10).click();
				}
				
			}
		}







	private void function_ExperienceTab() {
		String path=System.getProperty("user.dir");
		String dataName=path+"\\testData"+ "\\" + ""+dataServiceName+".json";
		JSONArray array =JsonUtils.getArrayValues(dataName, "wizard");
		if(array.size()>0) {
		applyWait.waitForElementToBeClickable(ap.experienceTab, 30).click();
		applyWait.waitForElementToBeClickable(ap.customize, 30).click();
		
		
		for(int i=0;i<array.size();i++) {
			JSONObject object=(JSONObject) array.get(i);
			JSONArray fields=(JSONArray) object.get("fields");
			String fieldName1=(String) object.get("name");
			applyWait.waitForElementToBeClickable(ap.stepNameTextBox, 30).sendKeys(fieldName1);;

			for(int j=0;j<fields.size();j++) {
				String fieldName=((String) fields.get(j)).replace("_", "").toLowerCase();
				List<WebElement> addAttribute=driver.findElements(By.id("addAtribute"));
				for(WebElement add : addAttribute) {
					 WebElement p = add.findElement(By.xpath("./.."));
					 WebElement q=p.findElement(By.xpath("./child::div/child::span"));
					 String text1=q.getText();
					 String[] t=text1.split(" ");
					 StringBuilder builder=new StringBuilder();
					 
					 for(String t1 : t) {
						 builder=builder.append(t1);
					 }
					 
					 if(builder.toString().toLowerCase().contains(fieldName)) {
						 WebElement field1=driver.findElement(By.xpath("//span[normalize-space()='"+text1+"']/parent::div/parent::div/child::span[2]"));
						 field1.click();
					 }
				}
			}
			if(i<array.size()-1) {
			applyWait.waitForElementToBeClickable(ap.addStep, 30).click();
				}
			}
		}
	}



	public void function_RolesTab() throws Exception {
		
		String dataName=path+"\\testData" + "\\" + ""+dataServiceName+".json";
		JSONObject role=(JSONObject) JsonUtils.getJSONObject(dataName).get("role");
		JSONArray roles=(JSONArray) role.get("roles");
	if(roles.size()>3) {
		applyWait.waitForElementToBeClickable(ap.roles, 30).click();
		applyWait.waitForElementToBeClickable(ap.addNew, 30).click();
	}
		
		for (int i = 0; i < roles.size(); i++) {
			if(i>2) {
				
			JSONObject jsonObject= (JSONObject) roles.get(i);
			String roleName=(String) jsonObject.get("name");
			applyWait.waitForElementToBeClickable(ap.roleNameTextBox, 30).sendKeys(roleName);;
			JSONArray operations=(JSONArray) jsonObject.get("operations");
			ArrayList<String> operationList=new ArrayList<String>();
				for (int j = 0; j < operations.size(); j++) {
					JSONObject operation= (JSONObject) operations.get(j);
					String operationName=(String) operation.get("method");
					operationList.add(operationName);
				}
			
				if(operationList.contains("REVIEW")) {
					String input=applyWait.waitForElementToBeClickable(ap.enabledReviewText, 30).getText();
					if(input.equalsIgnoreCase("No")) {
						applyWait.waitForElementToBeClickable(ap.enabledReviewToggler, 30).click();
					}
				}
				applyWait.waitForElementToBeClickable(ap.viewTab, 30).click();
				Thread.sleep(500);
				applyWait.waitForElementToBeClickable(ap.manageTab, 30).click();
				
				if(operationList.contains("POST")) {
					String input=applyWait.waitForElementToBeClickable(ap.createButton1, 30).getAttribute("class");
					if(!input.contains("checked")) {
						applyWait.waitForElementToBeClickable(ap.createButton1, 30).click();
					}
				}
				else {
					String input=applyWait.waitForElementToBeClickable(ap.createButton1, 30).getAttribute("class");
					if(input.contains("checked")) {
						applyWait.waitForElementToBeClickable(ap.createButton1, 30).click();
					}
				}
				
				if(operationList.contains("PUT")) {
					String input=applyWait.waitForElementToBeClickable(ap.editButton, 30).getAttribute("class");
					if(!input.contains("checked")) {
						applyWait.waitForElementToBeClickable(ap.editButton, 30).click();
					}
				}
				else {
					String input=applyWait.waitForElementToBeClickable(ap.editButton, 30).getAttribute("class");
					if(input.contains("checked")) {
						applyWait.waitForElementToBeClickable(ap.editButton, 30).click();
					}
				}
				
				if(operationList.contains("DELETE")) {
					String input=applyWait.waitForElementToBeClickable(ap.deleteButton, 30).getAttribute("class");
					if(!input.contains("checked")) {
						applyWait.waitForElementToBeClickable(ap.deleteButton, 30).click();
					}
				}
				else {
					String input=applyWait.waitForElementToBeClickable(ap.deleteButton, 30).getAttribute("class");
					if(input.contains("checked")) {
						applyWait.waitForElementToBeClickable(ap.deleteButton, 30).click();
					}
				}
				
				if(i < roles.size()-1) {
				applyWait.waitForElementToBeClickable(ap.addNew, 30).click();
				}
			}
		}
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
		
	}

	public void deleteDataService() throws Exception {
		List<WebElement> dataServices=driver.findElements(By.id("serviceManagerCardTitle"));
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
		scroll.scrollDown();
		applyWait.waitForElementToBeClickable(ap.clone, 30).click();
		applyWait.waitForElementToBeClickable(ap.saveButton, 30).click();
		
		String service=applyWait.waitForElementToBeClickable(ap.dataServiceName1, 30).getText();
	}
	
	
	public void groupAttributes(String objectAttribute,JSONObject attribute) throws Exception {
		JSONObject jsonProperties;
		switch(objectAttribute) {
		
		
		case "String" : 
			jsonProperties = (JSONObject) attribute.get("properties");
			applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
			applyWait.waitForElementToBeClickable(ap.abc, 30).click();
			
			if(	jsonProperties.containsKey("longText")){
				applyWait.waitForElementToBeClickable(ap.longText, 30).click();
				}
			
			else if(	jsonProperties.containsKey("richText")) {
				applyWait.waitForElementToBeClickable(ap.richText, 30).click();
			}
			
			else if(	jsonProperties.containsKey("email")) {
				applyWait.waitForElementToBeClickable(ap.email, 30).click();
			}
			else if(	jsonProperties.containsKey("enum")) {
				applyWait.waitForElementToBeClickable(ap.listOfValue, 30).click();
			}
			
			else {
				applyWait.waitForElementToBeClickable(ap.text, 30).click();
			}
				requiredAttributes(jsonProperties);

	break;
			
		
		case "Number" : 
			jsonProperties = (JSONObject) attribute.get("properties");
			
			applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
			applyWait.waitForElementToBeClickable(ap.number, 30).click();
			
			if(	jsonProperties.containsKey("currency")){
				applyWait.waitForElementToBeClickable(ap.currency, 30).click();
				}
			
			else if(jsonProperties.containsKey("enum")) {
				applyWait.waitForElementToBeClickable(ap.listOfValue, 30).click();
			}
			
			else {
				applyWait.waitForElementToBeClickable(ap.number1, 30).click();
			}
			
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
			applyWait.waitForElementToBeClickable(ap.calender, 30).click();
			
			if(jsonProperties.get("dateType").equals("date")) {
				applyWait.waitForElementToBeClickable(ap.date, 30).click();
			}
			if(jsonProperties.get("dateType").equals("datetime-local")) {
				applyWait.waitForElementToBeClickable(ap.dateAndTime, 30).click();
			}
			

			requiredAttributes(jsonProperties);
			break;
			
			
			case "Object" : 
				jsonProperties = (JSONObject) attribute.get("properties");
	   			applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
				applyWait.waitForElementToBeClickable(ap.group, 30).click();
				if(jsonProperties.containsKey("label")) {
					applyWait.waitForElementToBeClickable(ap.customLabel, 30).sendKeys(jsonProperties.get("label").toString());								
					}
				
				if(jsonProperties.containsKey("errorMessage")) {
						applyWait.waitForElementToBeClickable(ap.customError, 30).sendKeys(jsonProperties.get("errorMessage").toString());
					}
				
				if(jsonProperties.containsKey("_description")) {
					applyWait.waitForElementToBeClickable(ap.description, 30).sendKeys(jsonProperties.get("_description").toString());
					}
				
				if(jsonProperties.containsKey("password")) {
					
					applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
					applyWait.waitForElementToBeClickable(ap.abc, 30).click();
					applyWait.waitForElementToBeClickable(ap.secureText, 30).click();
					requiredAttributes(jsonProperties);
					
				}
				 else if(jsonProperties.containsKey("relatedTo")) {
						
						applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
						applyWait.waitForElementToBeClickable(ap.relation, 30).click();
						requiredAttributes(jsonProperties);
						
					}
                  else if(jsonProperties.containsKey("schema")) {
						
						applyWait.waitForElementToBeClickable(ap.dropdown, 30).click();
						applyWait.waitForElementToBeClickable(ap.library, 30).click();
						requiredAttributes(jsonProperties);
						
					}
				else {
				
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
				
				applyWait.waitForElementToBeClickable(ap.dropdownCollection, 30).click();
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
				applyWait.waitForElementToBeClickable(ap.dropdownCollection, 30).click();
				applyWait.waitForElementToBeClickable(ap.booleanData, 30).click();
				requiredAttributes(jsonProperties);
				break;
				
				case "Date" : 
     			jsonProperties = (JSONObject) attribute.get("properties");
				applyWait.waitForElementToBeClickable(ap.dropdownCollection, 30).click();
				applyWait.waitForElementToBeClickable(ap.calender, 30).click();
				requiredAttributes(jsonProperties);
				break;
				
				
				case "Object" : 
					jsonProperties = (JSONObject) attribute.get("properties");
				
                       if(jsonProperties.containsKey("password")) {
						
						applyWait.waitForElementToBeClickable(ap.dropdownCollection, 30).click();
						applyWait.waitForElementToBeClickable(ap.abc, 30).click();
						applyWait.waitForElementToBeClickable(ap.secureText, 30).click();
						requiredAttributes(jsonProperties);
					 	}
                       else if(jsonProperties.containsKey("relatedTo")) {
							
							applyWait.waitForElementToBeClickable(ap.dropdownCollection, 30).click();
							applyWait.waitForElementToBeClickable(ap.relation, 30).click();
							requiredAttributes(jsonProperties);
							
						}
	        		 else if(jsonProperties.containsKey("schema")) {
						
						applyWait.waitForElementToBeClickable(ap.dropdownCollection, 30).click();
						applyWait.waitForElementToBeClickable(ap.library, 30).click();
						requiredAttributes(jsonProperties);
						
					}
	        		 else {
	        			 applyWait.waitForElementToBeClickable(ap.dropdownCollection, 30).click();
	 	        		 applyWait.waitForElementToBeClickable(ap.group, 30).click();
	 	        		 JSONArray objectArray=(JSONArray) attribute.get("definition");
	 	        		 requiredAttributes(jsonProperties);
						for(int k=0;k<objectArray.size();k++) {
							
							JSONObject object = (JSONObject) objectArray.get(k);
							String objectAttributeName = attribute.get("type").toString();
							String objectKey = object.get("key").toString();
							JSONObject object1=(JSONObject) object.get("properties") ;
							if(k!=0){
								applyWait.waitForElementToBeClickable(ap.newAttributeButton1, 30).click();
							}
								
						   applyWait.waitForElementToBeClickable(ap.attributeTextBox, 30).sendKeys(object1.get("name").toString());
						   groupAttributes((String) object.get("type"),object);
						}
	        		 }
	 	        		 
						  
		  
					
						break;

					case "Geojson" : 
						jsonProperties = (JSONObject) attribute.get("properties");
					applyWait.waitForElementToBeClickable(ap.dropdownCollection, 30).click();
					applyWait.waitForElementToBeClickable(ap.location, 30).click();
					applyWait.waitForElementToBeClickable(ap.pointOnAMap, 30).click();
					requiredAttributes(jsonProperties);
					break;
					
					case "File" : 
						jsonProperties = (JSONObject) attribute.get("properties");
					
						applyWait.waitForElementToBeClickable(ap.dropdownCollection, 30).click();
						applyWait.waitForElementToBeClickable(ap.file, 30).click();
						requiredAttributes(jsonProperties);
						break;
			
					
					case "User" : 
						jsonProperties = (JSONObject) attribute.get("properties");
						applyWait.waitForElementToBeClickable(ap.dropdownCollection, 30).click();
						applyWait.waitForElementToBeClickable(ap.user, 30).click();
						requiredAttributes(jsonProperties);
						break;
			}
		
		

}
						public void requiredAttributes(JSONObject jsonProperties) throws Exception{
							
							if(jsonProperties.containsKey("required")) {
								if(jsonProperties.get("required").toString().equals("true")) {
									applyWait.waitForElementToBeClickable(ap.required, 30).click();
								}
								}
							
							if(jsonProperties.containsKey("readonly")) {
								if(jsonProperties.get("readonly").toString().equals("true")) {
									Actions action=new Actions(driver);
									action.moveToElement(ap.readOnly).perform();;
									applyWait.waitForElementToBeClickable(ap.readOnly, 30).click();
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
									
								
								if(jsonProperties.containsKey("supportedTimezones")) {
									
									JSONArray array=(JSONArray) jsonProperties.get("supportedTimezones");
									for(int j=0; j<array.size();j++) {
									applyWait.waitForElementToBeClickable(ap.supportedTimezones, 30).clear();
									applyWait.waitForElementToBeClickable(ap.supportedTimezones, 30).sendKeys(array.get(j).toString());
									Thread.sleep(500);
									applyWait.waitForElementToBeClickable(ap.supportedTimezones, 30).sendKeys(Keys.ENTER);
								
									}
								}
								if(jsonProperties.containsKey("defaultTimezone")) {
									applyWait.waitForElementToBeClickable(ap.defaultTimezone, 30).sendKeys(jsonProperties.get("defaultTimezone").toString(),Keys.ENTER);
								
									}	
																
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
								
								if(jsonProperties.containsKey("hasTokens")) {
									JSONArray array=(JSONArray) jsonProperties.get("hasTokens");
									
									for(int j=0; j<array.size();j++) {
										applyWait.waitForElementToBeClickable(ap.tokens, 30).sendKeys(array.get(j).toString());
										applyWait.waitForElementToBeClickable(ap.plusIcon, 30).click();
									}		
								}

								if(jsonProperties.containsKey("relatedTo")) {
									anotherDataService=jsonProperties.get("relatedTo").toString();
									applyWait.waitForElementToBeClickable(ap.relatesTo, 30).sendKeys(jsonProperties.get("relatedTo").toString());
									By dataService=By.xpath("//button[@role='option']//span[text()='"+anotherDataService+"']");
									applyWaitForDynamicWebElement(dataService, 10);
									driver.findElement(dataService).click();
								}
								
								if(jsonProperties.containsKey("relatedSearchField")) {
									applyWait.waitForElementToBeClickable(ap.searchOnField, 30).sendKeys(jsonProperties.get("relatedSearchField").toString());
								}
								
								if(jsonProperties.containsKey("relatedViewFields")) {
									JSONArray array=(JSONArray) jsonProperties.get("relatedViewFields");
									
									if(jsonProperties.get("_typeChanged").equals("Relation"))
									{
									   for(int j=0; j<array.size();j++) {
										JSONObject object = (JSONObject) array.get(j);
										JSONObject obj = (JSONObject) object.get("properties");
										String value = (String) obj.get("name");
										dropdown.selectByVisibleText(ap.viewFields, value);
									 }
								  }
									else if(jsonProperties.get("_typeChanged").equals("User"))
									{
									   for(int j=0; j<array.size();j++) {
										JSONObject object = (JSONObject) array.get(j);
										String value = (String) object.get("name");
										dropdown.selectByVisibleText(ap.viewFields, value);
									 }
								  }
								}
								
								if(jsonProperties.containsKey("schema")) {
									applyWait.waitForElementToBeClickable(ap.linkedLibrary, 30).sendKeys(jsonProperties.get("schema").toString());
								     Thread.sleep(1000);
								}
								
								if(jsonProperties.containsKey("enum")) {
									JSONArray array=(JSONArray) jsonProperties.get("enum");
									
									for(int j=0; j<array.size();j++) {
										applyWait.waitForElementToBeClickable(ap.values, 30).sendKeys(array.get(j).toString());
										applyWait.waitForElementToBeClickable(ap.plusIcon, 30).click();
									}		
									}
								
								if(jsonProperties.containsKey("values")) {
									applyWait.waitForElementToBeClickable(ap.values, 30).sendKeys(jsonProperties.get("values").toString());
									}
								
								if(jsonProperties.containsKey("default")) {
									
										if(!driver.findElements(By.xpath("//button[normalize-space()='Select Date']")).isEmpty()) {
											applyWait.waitForElementToBeClickable(ap.selectDate, 30).click();
											Thread.sleep(1000);
											scroll.scrollInToView(ap.done);
											Thread.sleep(1000);
											
											String fullDate[]=jsonProperties.get("default").toString().split("T")[0].split("-");
											String fullTime[]=jsonProperties.get("default").toString().split("T")[1].split(":");
											String date=fullDate[2];
											String month=fullDate[1];
											String year=fullDate[0];
											String hour=fullTime[0];
											String minute=fullTime[1];
											String[] second=fullTime[2].trim().split(".");
											
											dropdown.selectByIndex(ap.monthDropDown, Integer.parseInt(month)-1);
											
											dropdown.selectByValue(ap.yearDropDown, year);
											
											WebElement date1=driver.findElement(By.xpath("//span[@class='date float-left text-secondary ng-star-inserted']/small[normalize-space()='"+date+"']"));
											date1.click();
											
											dropdown.selectByValue(ap.hourDropDown, hour);
											dropdown.selectByValue(ap.minuteDropDown, minute);
											applyWait.waitForElementToBeClickable(ap.done, 30).click();
											
										}
										
										else {
//											applyWait.waitForElementToBeClickable(ap.defaultValue, 30).sendKeys(jsonProperties.get("default").toString());
										}
								}
								
								if(jsonProperties.containsKey("pattern")) {
									applyWait.waitForElementToBeClickable(ap.pattern, 30).sendKeys(jsonProperties.get("pattern").toString());									}
								
								
								if(jsonProperties.containsKey("_description")) {
									applyWait.waitForElementToBeClickable(ap.description, 30).sendKeys(jsonProperties.get("_description").toString());
									}
								
								
								if(jsonProperties.containsKey("deleteAction"))
								{
									String allowdeletion_Staus = "";
															
								if(!driver.findElements(By.xpath("//div[contains(normalize-space(),'Allow deletion')]/following-sibling::div//span[@class='text']")).isEmpty()){
										
										 allowdeletion_Staus=ap.allowdeletionStatus.getText();
								    }
									
									if(jsonProperties.get("deleteAction").toString().equals("setnull") && allowdeletion_Staus.equals("No") ) {
										
									   ap.allowdeletionToggler.click();	
									}
									
									else if(jsonProperties.get("deleteAction").toString().equals("restrict") && allowdeletion_Staus.equals("Yes") ) {
										ap.allowdeletionToggler.click();
									}
								}
								
								if(jsonProperties.containsKey("default"))
								{
									String defult_Status = "";
									
									if(!driver.findElements(By.xpath("//div[normalize-space()='Default Value']/following-sibling::div//span[@class='ml-3']")).isEmpty()){
											
											 defult_Status=ap.defaultSatus.getText();
									    }
									if(jsonProperties.get("default").toString().equals("true")  &&  defult_Status.equals("No"))
									{
										ap.defaultToggler.click();
									}
								}
						}

						

						public void verifyGroupExists(String groupName) throws Exception {
							applyWait.waitForElementToBeClickable(ap.groups, 30).click();
							Thread.sleep(2000);
							List<WebElement> groupNames=gp.groups;
						
						groups=new ArrayList<String>();
							for(WebElement group : groupNames) {
								String group1=group.getText();
								groups.add(group1);
						}
							if(groups.contains(groupName)) {
							
						}
						}

						public void removeGroup(String groupName) throws Exception {
							applyExplicitWaitsUntilElementVisible(ap.groupNames, 10);
							if(groups.contains(groupName)) {
								WebElement group=driver.findElement(By.xpath("//div[normalize-space()='"+groupName+"']/parent::div"));
								group.click();
								applyWait.waitForElementToBeClickable(gp.deleteGroup, 30).click();
								Thread.sleep(500);
								applyWait.waitForElementToBeClickable(gp.delete, 30).click();

							}
							
							
						}

						public void verifyGroupDoesNotExist(String groupName) throws Exception {
							applyExplicitWaitsUntilElementVisible(ap.groupNames, 10);
							List<WebElement> groupNames=gp.groups;
							groups=new ArrayList<String>();
								for(WebElement group : groupNames) {
									String group1=group.getText();
									groups.add(group1);
							}
								if(!groups.contains(groupName)) {
							}
							
						}

						public void dataServiceExists(String dataService) {
							
							if(data_Services.contains(dataService)) {
						}
							
						}

						public void createGroupAndEnableRole(String groupName, String role, String dataservice) throws Exception {
							applyExplicitWaitsUntilElementVisible(ap.groupNames, 10);
							List<WebElement> groupNames=gp.groups;
							ArrayList<String> groups=new ArrayList<String>();
								for(WebElement group : groupNames) {
									String group1=group.getText();
									groups.add(group1);
							}
								if(!groups.contains(groupName)) {
									applyWait.waitForElementToBeClickable(gp.newGroup, 30).click();
									applyWait.waitForElementToBeClickable(gp.groupName, 30).sendKeys(groupName);;
									applyWait.waitForElementToBeClickable(gp.createButton, 30).click();
									applyWait.waitForElementToBeClickable(gp.appCenterRoles, 30).click();
									applyExplicitWaitsUntilElementVisible(gp.dsArrow, 10);
									WebElement dsArrow=driver.findElement(By.xpath("//span[normalize-space()='"+dataservice+"']/parent::div/following-sibling::span[2]/child::span"));
									applyWait.waitForElementToBeClickable(dsArrow, 30).click();
									
									if(role.equalsIgnoreCase("SkipReview")) {
										applyWait.waitForElementToBeClickable(gp.skipReviewToggler, 30).click();
										}
									else if(role.equalsIgnoreCase("Manage")) {
										applyWait.waitForElementToBeClickable(gp.manageToggler, 30).click();
									}
									
									else if(role.equalsIgnoreCase("View")) {
										applyWait.waitForElementToBeClickable(gp.viewToggler, 30).click();
										}
									
									else {
										WebElement toggler=driver.findElement(By.xpath("//span[contains(text(),'"+role+"')]/parent::div/following-sibling::span[2]/child::label/child::span[2]"));
										applyWait.waitForElementToBeClickable(toggler, 30).click();
										}
									
									applyWait.waitForElementToBeClickable(gp.saveDataService, 30).click();
								}
							
						}

						public void groupExists(String groupName) {
							if(groups.contains(groupName)) {
							
						}							
						}

						public void VerifyUserExists(String userEmail) throws Exception {
							applyWait.waitForElementToBeClickable(gp.users, 30).click();
							List<WebElement> userList=gp.userList;
							ArrayList<String> users=new ArrayList<String>();
							for(WebElement user : userList) {
								String user1=user.getText();
								users.add(user1);
						}
							if(!users.contains(userEmail)) {
								applyWait.waitForElementToBeClickable(gp.addUser, 30).click();
								applyWait.waitForElementToBeClickable(gp.userName, 30).sendKeys(userEmail);;
								applyWait.waitForElementToBeClickable(gp.password, 30).sendKeys(Property.getProperty("password"));;
								applyWait.waitForElementToBeClickable(gp.confirmPassword, 30).sendKeys(Property.getProperty("password"));;
								applyWait.waitForElementToBeClickable(gp.name, 30).sendKeys(Property.getProperty("userName"));;
								applyWait.waitForElementToBeClickable(gp.add, 30).click();
							}
							
						}

						public void addUserToGroup(String userEmail, String group) throws Exception {
							
							applyWait.waitForElementToBeClickable(ap.groups, 30).click();
							applyExplicitWaitsUntilElementVisible(ap.groupNames, 10);
							WebElement groupName=driver.findElement(By.xpath("//div[normalize-space()='"+group+"']/parent::div"));
							try {
							applyWait.waitForElementToBeClickable(groupName, 30).click();
							}
							catch(StaleElementReferenceException  e) {
								groupName=driver.findElement(By.xpath("//div[normalize-space()='"+group+"']/parent::div"));
								applyWait.waitForElementToBeClickable(groupName, 30).click();
							}
							applyWait.waitForElementToBeClickable(gp.members, 30).click();
							applyWait.waitForElementToBeClickable(gp.addUsers, 30).click();
							applyExplicitWaitsUntilElementVisible(gp.userEmail, 10);
							WebElement user=driver.findElement(By.xpath("//odp-user-list-cell-renderer[normalize-space()='"+userEmail+"']"));
							applyExplicitWaitsUntilElementVisible(user,10);
							try {
							user.click();
							}
							catch(ElementClickInterceptedException e) {
								applyExplicitWaitsUntilElementVisible(gp.userEmail, 10);
								user=driver.findElement(By.xpath("//odp-user-list-cell-renderer[normalize-space()='"+userEmail+"']"));
								user.click();
							}
							applyWait.waitForElementToBeClickable(gp.done, 30).click();
							applyWait.waitForElementToBeClickable(gp.save, 30).click();

							
						}

						public void logOutFromAuthor() throws Exception {
							try {
							applyWait.waitForElementToBeClickable(gp.profileIcon, 30).click();
							}
							catch(ElementClickInterceptedException e) {
								Thread.sleep(2000);
								handleElementClickException(gp.profileIcon);
							}
							applyWait.waitForElementToBeClickable(gp.logout, 30).click();

						}

						public void logsOutOfAuthor() throws Exception {
							applyExplicitWaitsUntilElementVisible(ap.signIn, 10);
							WebElement signInText=ap.signIn;
							Boolean verify=signInText.isDisplayed();
							if(verify) {
							}
						}

						public void navigateToAppcenterLoginPage() {
							
						}
							
						
		
		
}

