package pageModules;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentTest;
import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import base.BaseClass;
import config.DefineConstants;
import cucumber.api.java.en.Then;
import helperMethods.DropDown;
import helperMethods.JavascriptClick;
import helperMethods.JsonUtils;
import helperMethods.ScrollTypes;
import helperMethods.WaitTypes;
import pageObjects.Object_AppCenterPage;
import pageObjects.Object_AuthorPage;
import pageObjects.Object_GroupPage;

public class Module_DesignTestCases extends BaseClass{
	
//	private WebDriver driver;
	private WaitTypes applyWait;
	private ExtentTest test;
	private JavascriptClick javascriptClick;
	public DropDown dropdown;
	public ScrollTypes scroll;
	public static String data_Service;
	public Object_AppCenterPage acp;
	public Object_AuthorPage ap;
	public Object_GroupPage gp;
	public LoginPage lp;
	public String id;
	public static ArrayList<String> groups;
	
	
	
	public Module_DesignTestCases(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.applyWait = new WaitTypes(driver);
		this.test = test;
		dropdown=new DropDown(driver);
		scroll=new ScrollTypes(driver);
		acp=new Object_AppCenterPage();
		ap=new Object_AuthorPage();
		gp=new Object_GroupPage();
		lp=new LoginPage(driver, test);
		javascriptClick=new JavascriptClick(driver);
	}



	public void dataServiceShouldDeployed() throws Exception {
		String dataService="String";
		
		driver.get(author_URL);
		applyWait.waitForElementToBeClickable(ap.emailIDTextBox, 30).sendKeys(DefineConstants.User_Email_Id);
		applyWait.waitForElementToBeClickable(ap.nextButton, 30).click();
		applyWait.waitforElementToBeDisplayed(ap.password, 30).sendKeys(DefineConstants.User_Password);;
		applyWait.waitforElementToBeDisplayed(ap.signInButton, 30).click();
		String dataName=path+"\\testData" + "\\" + ""+dataService+".json";
		lp.createNewDataServices(JsonUtils.getArrayValues(dataName, "definition"),dataService);
		
	}



	public void groupexist(String groupName) throws Exception {
		
		Thread.sleep(3000);
		applyWait.waitForElementToBeClickable(ap.groups, 30).click();
		applyExplicitWaitsUntilElementVisible(gp.group1);
		List<WebElement> groupNames=gp.groups;
		groups=new ArrayList<String>();
		for(WebElement group : groupNames) {
			String group1=group.getText();
			groups.add(group1);
	}
		
		if(groups.contains(groupName)) {
			WebElement group=driver.findElement(By.xpath("//div[normalize-space()='"+groupName+"']/parent::div"));
			group.click();
			Thread.sleep(500);
			applyWait.waitForElementToBeClickable(gp.deleteGroup, 30).click();
			Thread.sleep(500);
			applyWait.waitForElementToBeClickable(gp.delete, 30).click();

		}
		
		
	}



	



	public void createGroup(String groupName) throws Exception {
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementVisible(gp.group1);
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
				

			}
		
	}



	public void assignPermission(String dataServiceName,String user1) throws Exception {
		String role="Manage";
		String userEmail=user1;
		Thread.sleep(2000);
		applyWait.waitForElementToBeClickable(gp.appCenterRoles, 30).click();
		Thread.sleep(2000);
		WebElement dsArrow=driver.findElement(By.xpath("//span[normalize-space()='"+dataServiceName+"']/parent::div/following-sibling::span[2]/child::span"));
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
		
		applyWait.waitForElementToBeClickable(gp.members, 30).click();
		applyWait.waitForElementToBeClickable(gp.addUsers, 30).click();
		Thread.sleep(2000);
		WebElement user=driver.findElement(By.xpath("//odp-user-list-cell-renderer[normalize-space()='"+userEmail+"']"));
		applyExplicitWaitsUntilElementVisible(user);
		try {
		user.click();
		}
		catch(ElementClickInterceptedException e) {
			user=driver.findElement(By.xpath("//odp-user-list-cell-renderer[normalize-space()='"+userEmail+"']"));
			user.click();
		}
		applyWait.waitForElementToBeClickable(gp.done, 30).click();
		applyWait.waitForElementToBeClickable(gp.save, 30).click();
//		applyWait.waitForElementToBeClickable(gp.saveDataService, 30).click();
		
	}



	public void addRecord(String string) throws Exception {
		Thread.sleep(2000);
		if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
			acp.yes.click();
	    }
		Thread.sleep(2000);
		if(!driver.findElements(By.xpath("//button[@id='addDataBtn']")).isEmpty()){
			applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();	
	    }
		Thread.sleep(1000);
		List<WebElement> textBoxes = acp.textBoxes;
		String filePath=path + "\\testData\\" + data_Service + ".data.json";
		JSONObject jsonObject = JsonUtils.fetchJSONObject(string);
//		for(int i=0;i<jsonArray.size();i++) {
//			Thread.sleep(1000);
//			JSONObject jsonObject=(JSONObject) jsonArray.get(i);
/**
 * Code for Experience tab
 */
		
			List<WebElement> stepNames=driver.findElements(By.xpath("//div[@class='step-name high-zIndex text-truncate']"));
			
				if(stepNames.size()>0) {
					
					for(WebElement stepName : stepNames) {
						stepName.click();
					
					
						for(int j=1;j<=textBoxes.size();j++) {
							
							WebElement textBox=driver.findElement(By.xpath("(//input[contains(@class,'ng-valid ng-star-inserted') or contains(@class,'ng-invalid')])["+j+"]"));
					
							String id1 =textBox.getAttribute("id");
							
							if(textBox.getAttribute("type").equals("text")) {
								if(id1.contains(".")) {
									String [] attributes=id1.trim().split("[^a-zA-Z0-9]+");
								
									JSONObject obj=(JSONObject) jsonObject.get(attributes[0]);
									applyWait.waitForElementToBeClickable(textBox,30).clear();
									applyWait.waitForElementToBeClickable(textBox,30).sendKeys((String)obj.get(attributes[1]));
									
								}
								else {
									applyWait.waitForElementToBeClickable(textBox,30).clear();
								applyWait.waitForElementToBeClickable(textBox,30).sendKeys(((String)jsonObject.get(id1)).toString());;
							}
							}
							if(textBox.getAttribute("type").equals("number")) {
								applyWait.waitForElementToBeClickable(textBox,30).clear();
								applyWait.waitForElementToBeClickable(textBox,30).sendKeys(((Long)jsonObject.get(id1)).toString());;
								}
							
						}
						
					}
				}
			

//				
				else {
				for (int j = 1; j <= textBoxes.size(); j++) {
					String jsonValue=null;
					WebElement textBox = driver.findElement(By.xpath("(//*[contains(@class,'form-control')])[" + j + "]"));
					
					if (textBox.isEnabled()) {
						String id1 = textBox.getAttribute("id");

//						System.out.println(id1+"      "+textBox.getAttribute("type"));
						
//						String value1=JsonUtils.getJsonValue(filePath,id1);

						try {
						jsonValue = JsonPath.read(string, "$."+id1+"").toString();
						}
						catch(PathNotFoundException e) {
							continue;
						}
						if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")||textBox.getAttribute("type").equals("select-one")) {
							if (!jsonValue.equals(null)) {
	
								if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")) {
									Thread.sleep(50);
									applyWait.waitForElementToBeClickable(textBox, 30).click();
									applyWait.waitForElementToBeClickable(textBox, 30).clear();
									applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(jsonValue);
									
										}
	
								if (textBox.getAttribute("type").equals("select-one")) {
									dropdown.selectByVisibleText(textBox, ( jsonObject.get(id1)).toString());
	
								}
							}
	
						}
	
						else if (textBox.getAttribute("type").equals("number") ) {
							
							if (!jsonValue.equals(null)) {
							
								if(JsonPath.read(string, "$."+id1+"").getClass().toString().contains("Double")) {
			
										if (textBox.getAttribute("type").equals("number")) {
											Double value = (Double) JsonPath.read(string, "$."+id1+"");
											Thread.sleep(50);
											applyWait.waitForElementToBeClickable(textBox, 30).click();
											textBox.sendKeys(Keys.CONTROL, Keys.chord("a")); 
											textBox.sendKeys(Keys.BACK_SPACE); 
											applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value.toString());
											
										}
										
										if (textBox.getAttribute("type").equals("select-one")) {
	
											dropdown.selectByVisibleText(textBox, ((Double) jsonObject.get(id1)).toString());
	
										}
									}
								
							
								else if(JsonPath.read(string, "$."+id1+"").getClass().toString().contains("Long")) {
		
										if (textBox.getAttribute("type").equals("number")) {
											Long value = (Long) JsonPath.read(string, "$."+id1+"");
											Thread.sleep(50);
											applyWait.waitForElementToBeClickable(textBox, 30).click();
											textBox.sendKeys(Keys.CONTROL, Keys.chord("a")); 
											textBox.sendKeys(Keys.BACK_SPACE); 
											applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value.toString());
											
										}
										
										if (textBox.getAttribute("type").equals("select-one")) {
											Thread.sleep(50);
											dropdown.selectByVisibleText(textBox, (JsonPath.read(string, "$."+id1+"").toString()));
		
										}
									}
								
								else if(JsonPath.read(string, "$."+id1+"").getClass().toString().contains("Integer")) {
									
									if (textBox.getAttribute("type").equals("number")) {
										Integer value = (Integer) JsonPath.read(string, "$."+id1+"");
										
										 String inputText = textBox.getAttribute("value");
									        if( inputText != null ) {
									            for(int i=0; i<inputText.length();i++) { //1234
									                textBox.sendKeys(Keys.BACK_SPACE);
									            }
									        }
										
									
										applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value.toString());
										
									}
									
									if (textBox.getAttribute("type").equals("select-one")) {
										Thread.sleep(50);
										dropdown.selectByVisibleText(textBox, (JsonPath.read(string, "$."+id1+"").toString()));
	
									}
								}
								
								else if(JsonPath.read(string, "$."+id1+"").equals("")) {
									Thread.sleep(50);
									applyWait.waitForElementToBeClickable(textBox, 30).click();
									applyWait.waitForElementToBeClickable(textBox, 30).clear();
								 }
	                   			}
						   }
							else if(textBox.getAttribute("type").equals("email")) {
								if ( jsonObject.get(id1) != null) {
								applyWait.waitForElementToBeClickable(textBox, 30).clear();
								applyWait.waitForElementToBeClickable(textBox,30).sendKeys(((String)jsonObject.get(id1)).toString());
							}
							}
						  }
						}
				}
				applyWait.waitForElementToBeClickable(acp.save, 30).click();
				}
			
			
//			
//			if((jsonArray.size()-1) > i) {
//			applyWait.waitForElementToBeClickable(acp.proceedAndCreateAnother, 30).click();
//				}
//				else {
//					applyWait.waitForElementToBeClickable(acp.save, 30).click();
//				}
		
//	}
	



	public void addNewRecord(String string, String string2) throws Exception {
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();	
		List<WebElement> textBoxes = acp.textBoxes;
		WebElement textBox=driver.findElement(By.xpath("//span[normalize-space()='DS STRING TEXT 1002 Label']/parent::label/parent::div/following-sibling::odp-form-control//descendant::input"));
		textBox.click();
		WebElement textBox1=driver.findElement(By.xpath("//input[@id='dsStringText1003']"));
		textBox1.click();
	}



	public void expectError(String string, String string2) throws Exception {
		List<WebElement> errors=acp.errorMessages;
		
		for(WebElement error : errors) {
		String errorMessage=error.getText();
		System.err.println(errorMessage);
	}
		if(acp.errorMessages.isEmpty()) {
			System.err.println("Custom Error message are not printing");
			Assert.assertTrue(false);
		}
	}


	public void saveButtonIsDisable() {
		
	Boolean status=	acp.saveButton.isEnabled();
	if(status) {
		System.err.println("Save Button is Enable.");
		Assert.assertTrue(false);
	}
	else {
		System.out.println("Save Button is Disable");
	}
		
	}
	
	


	public void addRecordForRepeatedId() throws Exception {


		applyWait.waitForElementToBeClickable(acp.yes, 30).click();
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();	
		Thread.sleep(2000);
		List<WebElement> textBoxes = acp.textBoxes;
		String path= System.getProperty("user.dir");
		JSONArray jsonArray = JsonUtils.getJSONArray("C:\\Users\\DELL\\eclipse-workspace\\DataStack\\testData\\string2.json");
		for(int i=0;i<jsonArray.size();i++) {
			Thread.sleep(1000);
			JSONObject jsonObject=(JSONObject) jsonArray.get(i);
/**
 * Code for Experience tab
 */
			List<WebElement> stepNames=driver.findElements(By.xpath("//div[@class='step-name high-zIndex text-truncate']"));
			
			if(stepNames.size()>0) {
			for(WebElement stepName : stepNames) {
				stepName.click();
			
			
				for(int j=1;j<=textBoxes.size();j++) {
					
					WebElement textBox=driver.findElement(By.xpath("(//input[contains(@class,'ng-valid ng-star-inserted') or contains(@class,'ng-invalid')])["+j+"]"));
			
					String id1 =textBox.getAttribute("id");
					
					if(textBox.getAttribute("type").equals("text")) {
						if(id1.contains(".")) {
							String [] attributes=id1.trim().split("[^a-zA-Z0-9]+");
						
							JSONObject obj=(JSONObject) jsonObject.get(attributes[0]);
							applyWait.waitForElementToBeClickable(textBox,30).sendKeys((String)obj.get(attributes[1]));
							
						}
						else {
						applyWait.waitForElementToBeClickable(textBox,30).sendKeys(((String)jsonObject.get(id1)).toString());;
					}
					}
					if(textBox.getAttribute("type").equals("number")) {
						applyWait.waitForElementToBeClickable(textBox,30).sendKeys(((Long)jsonObject.get(id1)).toString());;
						}
					
				}
				
			}
			}
			
			else {
				for(int j=1;j<=textBoxes.size();j++) {
					
					WebElement textBox=driver.findElement(By.xpath("(//input[contains(@class,'form')])["+j+"]"));
					if(textBox.isEnabled()) {
					String id1 =textBox.getAttribute("id");
					if(((String)jsonObject.get(id1))!=null) {
						
					
					if(textBox.getAttribute("type").equals("text")) {
						if(id1.contains(".")) {
							String [] attributes=id1.trim().split("[^a-zA-Z0-9]+");
						
							JSONObject obj=(JSONObject) jsonObject.get(attributes[0]);
							applyWait.waitForElementToBeClickable(textBox,30).sendKeys((String)obj.get(attributes[1]));
							
						}
						else {
						applyWait.waitForElementToBeClickable(textBox,30).sendKeys(((String)jsonObject.get(id1)).toString());;
					}
					}
					if(textBox.getAttribute("type").equals("number")) {
						applyWait.waitForElementToBeClickable(textBox,30).sendKeys(((Long)jsonObject.get(id1)).toString());;
						}
					
				}
					}
				}
				
			}
			
			
			if((jsonArray.size()-1) > i) {
			applyWait.waitForElementToBeClickable(acp.proceedAndCreateAnother, 30).click();
				}
				else {
					applyWait.waitForElementToBeClickable(acp.save, 30).click();
				}
		
	      }
		
	}
	
	public void addRecordForLocation(String string) throws InterruptedException {
		Thread.sleep(2000);
		if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
			acp.yes.click();
	    }
		Thread.sleep(2000);
		if(!driver.findElements(By.xpath("//button[@id='addDataBtn']")).isEmpty()){
			applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();	
	    }
		Thread.sleep(1000);
		List<WebElement> textBoxes = acp.textBoxesLocation;
		JSONObject jsonObject = JsonUtils.fetchJSONObject(string);
	   	for (int j = 2; j <= textBoxes.size(); j++) {
			WebElement textBox = driver.findElement(By.xpath("(//input[@class='form-control form-control-sm rounded ng-pristine ng-valid ng-star-inserted ng-touched' or 'searchInput pac-target-input'])["+j+"]"));
				if (textBox.isEnabled()) {
					String id1 = textBox.getAttribute("id");
						{		
					  if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")) {
						  if(jsonObject.get(id1)!=null)
						  {
							 applyWait.waitForElementToBeClickable(textBox,30).clear();
						     applyWait.waitForElementToBeClickable(textBox, 30).sendKeys((jsonObject.get(id1)).toString());
						     Thread.sleep(2000);
						     applyWait.waitForElementToBeClickable(textBox,30).clear();
						     textBox.sendKeys(Keys.DOWN);
						     textBox.sendKeys(Keys.ENTER);
						     
						  }
					  }
					}
				}
		
				      
		           }
		            applyWait.waitForElementToBeClickable(acp.save, 30).click();
		       }
	
					
		
	

		
	public void addNewRecords() throws Exception {
		
		applyWait.waitForElementToBeClickable(acp.yes, 30).click();
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();	
		List<WebElement> textBoxes = acp.textBoxes;
//		String path= System.getProperty("user.dir");
		JSONArray jsonArray = JsonUtils.getJSONArray(path +"\\testData\\string3.json");
		for(int i=0;i<jsonArray.size();i++) {
			JSONObject jsonObject=(JSONObject) jsonArray.get(i);
/**
 * Code for Experience tab
 */
			List<WebElement> stepNames=driver.findElements(By.xpath("//div[@class='step-name high-zIndex text-truncate']"));
			
			if(stepNames.size()>0) {
			for(WebElement stepName : stepNames) {
				stepName.click();
			
			
				for(int j=1;j<=textBoxes.size();j++) {
					
					WebElement textBox=driver.findElement(By.xpath("(//input[contains(@class,'ng-valid ng-star-inserted') or contains(@class,'ng-invalid')])["+j+"]"));
			
					String id1 =textBox.getAttribute("id");
					
					if(textBox.getAttribute("type").equals("text")) {
						if(id1.contains(".")) {
							String [] attributes=id1.trim().split("[^a-zA-Z0-9]+");
						
							JSONObject obj=(JSONObject) jsonObject.get(attributes[0]);
							applyWait.waitForElementToBeClickable(textBox,30).sendKeys((String)obj.get(attributes[1]));
							
						}
						else {
						applyWait.waitForElementToBeClickable(textBox,30).sendKeys(((String)jsonObject.get(id1)).toString());;
					}
					}
					if(textBox.getAttribute("type").equals("number")) {
						applyWait.waitForElementToBeClickable(textBox,30).sendKeys(((Long)jsonObject.get(id1)).toString());;
						}
					
				}
				
			}
			}
			
			else {
				for(int j=1;j<=textBoxes.size();j++) {
					
					WebElement textBox=driver.findElement(By.xpath("(//input[contains(@class,'form')])["+j+"]"));
					if(textBox.isEnabled()) {
					String id1 =textBox.getAttribute("id");
					if(((String)jsonObject.get(id1))!=null) {
						
					
					if(textBox.getAttribute("type").equals("text")) {
						if(id1.contains(".")) {
							String [] attributes=id1.trim().split("[^a-zA-Z0-9]+");
						
							JSONObject obj=(JSONObject) jsonObject.get(attributes[0]);
							applyWait.waitForElementToBeClickable(textBox,30).sendKeys((String)obj.get(attributes[1]));
							
						}
						else {
						applyWait.waitForElementToBeClickable(textBox,30).sendKeys(((String)jsonObject.get(id1)).toString());;
					}
					}
					if(textBox.getAttribute("type").equals("number")) {
						applyWait.waitForElementToBeClickable(textBox,30).sendKeys(((Long)jsonObject.get(id1)).toString());;
						}
				}}
				}
			}
			if((jsonArray.size()-1) > i) {
			applyWait.waitForElementToBeClickable(acp.proceedAndCreateAnother, 30).click();
				}
				else {
					applyWait.waitForElementToBeClickable(acp.save, 30).click();
				}
	}
	
	
		
	
	
		
	
		
	}



	public void updateRecord(String id, String jsonFile) throws Exception {
		
		Thread.sleep(1000);
		if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
			acp.yes.click();
	    }
		applyWait.waitForElementToBeClickable(acp.idTab, 30).clear();
		Thread.sleep(500);
		applyWait.waitForElementToBeClickable(acp.idTab, 30).sendKeys(id);;
		
		WebElement record=driver.findElement(By.xpath("//a[normalize-space()='"+id+"']"));
		record.click();
		applyWait.waitForElementToBeClickable(acp.edit, 30).click();
		Thread.sleep(1000);
		addRecord(jsonFile);
		
	}
	
     public void updateLocationRecord(String id, String jsonFile) throws Exception {
		
		Thread.sleep(2000);
		if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
			acp.yes.click();
	    }
		applyWait.waitForElementToBeClickable(acp.idTab, 30).clear();
		Thread.sleep(1000);
		applyWait.waitForElementToBeClickable(acp.idTab, 30).sendKeys(id);;
		
		WebElement record=driver.findElement(By.xpath("//a[normalize-space()='"+id+"']"));
		record.click();
		Thread.sleep(1000);
		 applyWait.waitForElementToBeClickable(acp.edit, 30).click();
		Thread.sleep(1000);
		addRecordForLocation(jsonFile);
     }
	



	public void updateRecords(String id, String attribute) throws InterruptedException {
		
		applyWait.waitForElementToBeClickable(acp.idTab, 30).sendKeys(id);;
		
		WebElement record=driver.findElement(By.xpath("//a[normalize-space()='"+id+"']"));
		record.click();
		Thread.sleep(2000);
		applyWait.waitForElementToBeClickable(acp.edit, 30).click();
		Thread.sleep(2000);
		WebElement textBox=driver.findElement(By.xpath("//span[normalize-space()='DS STRING TEXT 1008']/parent::label/parent::div/following-sibling::odp-form-control//descendant::input"));
		textBox.clear();;
		textBox.sendKeys(attribute);
		applyWait.waitForElementToBeClickable(acp.save, 30).click();
		
	}



	public void fetchRecord(String id) throws Exception {
		Thread.sleep(1000);
		this.id=id;
		
		if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
			acp.yes.click();
	    }
		applyWait.waitForElementToBeClickable(acp.idTab, 30).clear();
		applyWait.waitForElementToBeClickable(acp.idTab, 30).sendKeys(id);;
		
	}



	public void recordMustNotExist() throws Exception {
		Thread.sleep(1000);
		if(!driver.findElements(By.xpath("//span[text()='No records to display']")).isEmpty()){
			System.err.println("No record found");
	    }
	}



	public void matchToRecord(String jsonFile) throws Exception {
		LinkedHashMap<String, String> actualData=new LinkedHashMap<>();
		Thread.sleep(2000);
		WebElement record=driver.findElement(By.xpath("//a[@class='ng-star-inserted']"));
		record.click();
		Thread.sleep(2000);
		int q=1;
		for(WebElement attribute : acp.attributesOnViewPage) {
			WebElement t=driver.findElement(By.xpath("(//label[starts-with(@class,'label-width d-flex')])["+q+"]/parent::div/following-sibling::odp-view-separator/descendant::div/child::*"));
			String a=attribute.getAttribute("for");
			String w=t.getText();
			if(!w.equals("N.A.")) {
				actualData.put(a, w);
		}
			q++;
		}

		
	LinkedHashMap<String, String> expectedData =(LinkedHashMap<String, String>) JsonUtils.getMapFromJSON(jsonFile);
		
	if(actualData.equals(expectedData)) {
		System.out.println("Data is matching");
	}
	else {
		
		System.err.println("Data is not matching.Unmatched data are as follows :");
	  MapDifference<String, String> diff = Maps.difference(actualData, expectedData);
	    Map<String, ValueDifference<String>> entriesDiffering = diff.entriesDiffering();
	    System.err.println(entriesDiffering);
	    System.out.println(actualData);
		System.out.println(expectedData);
	    Assert.assertTrue(actualData.equals(expectedData));
	}
	System.out.println(actualData);
	System.out.println(expectedData);
	}
	public void deleteRecord(String id) {
		if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
			acp.yes.click();
	    }
		applyWait.waitForElementToBeClickable(acp.idTab, 30).clear();;
		  applyWait.waitForElementToBeClickable(acp.idTab, 30).sendKeys(id,Keys.ENTER);
		  applyWait.waitForElementToBeClickable(acp.searchID, 30).click();
		  applyWait.waitForElementToBeClickable(acp.deleteBtn, 30).click();
		  applyWait.waitForElementToBeClickable(acp.deleteRecord, 30).click();
	  }
	
	public void deleteRecordWithLabel(String dsStringText1001,String dsStringText1002) throws InterruptedException {
		if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
			acp.yes.click();
	    }
		
			 applyWait.waitForElementToBeClickable(acp.idTab, 30).clear();
			 Thread.sleep(2000);
			 javascriptClick.sendKeys(acp.dsSTRINGTEXT1001, dsStringText1001);
			 Thread.sleep(2000);
			 javascriptClick.sendKeys(acp.dsSTRINGTEXT1002, dsStringText1002);
			 javascriptClick.click(acp.click_On_Checkbox);
			 javascriptClick.click(acp.click_On_Delete);
			 javascriptClick.click(acp.delete_Record);
  }
	
	
	public void deleteRecordFromViewPage(String dsStringText1001 , String dsStringText1002) throws Exception {
		 applyWait.waitForElementToBeClickable(acp.idTab, 30).clear();
		 javascriptClick.sendKeys(acp.dsSTRINGTEXT1001, dsStringText1001);
		 Thread.sleep(2000);
		 javascriptClick.sendKeys(acp.dsSTRINGTEXT1002, dsStringText1002);

		 WebElement record=driver.findElement(By.xpath("//a[@class='ng-star-inserted']"));
			record.click();
			applyWait.waitForElementToBeClickable(acp.delete_ViewRecord, 30).click();
			applyWait.waitForElementToBeClickable(acp.delete_Record, 30).click();
	}



	public void enterUserNameAndPassword(String username, String password) {
		applyWait.waitForElementToBeClickable(ap.emailIDTextBox, 30).sendKeys(username);
		applyWait.waitForElementToBeClickable(ap.nextButton, 30).click();
		applyWait.waitforElementToBeDisplayed(ap.password, 30).sendKeys(password);;
		applyWait.waitforElementToBeDisplayed(ap.signInButton, 30).click();
		
	}



	public void fetchRecordBySearchingData(String dsStringText1001, String dsStringText1002) throws Exception {
		
		if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
			acp.yes.click();
	    }
		
			 applyWait.waitForElementToBeClickable(acp.idTab, 30).clear();
			 Thread.sleep(2000);
			 javascriptClick.sendKeys(acp.dsSTRINGTEXT1001, dsStringText1001);
			 Thread.sleep(2000);
			 javascriptClick.sendKeys(acp.dsSTRINGTEXT1002, dsStringText1002);
			 acp.dsSTRINGTEXT1002.sendKeys(Keys.ENTER);
		
	}



	public void matchDataCurrency(String jsonFile) throws Exception {
		

		LinkedHashMap<String, String> actualData=new LinkedHashMap<>();
		Thread.sleep(2000);
		WebElement record=driver.findElement(By.xpath("//a[@class='ng-star-inserted']"));
		record.click();
		Thread.sleep(2000);
		int q=1;
		for(WebElement attribute : acp.attributesOnViewPage) {
			WebElement t=driver.findElement(By.xpath("(//label[starts-with(@class,'label-width d-flex')])["+q+"]/parent::div/following-sibling::odp-view-separator/descendant::div/child::*[last()]"));
			String a=attribute.getAttribute("for");
			String w=t.getText();
			if(!w.equals("N.A.")) {
				actualData.put(a, w);
		}
			q++;
		}

		
	LinkedHashMap<String, String> expectedData =(LinkedHashMap<String, String>) JsonUtils.getMapFromJSON(jsonFile);
	

	
	if(actualData.equals(expectedData)) {
		System.out.println("Data is matching");
	}
	else {
		
		System.err.println("Data is not matching.Unmatched data are as follows :");
	  MapDifference<String, String> diff = Maps.difference(actualData, expectedData);
	    Map<String, ValueDifference<String>> entriesDiffering = diff.entriesDiffering();
	    System.err.println(entriesDiffering);
	    System.out.println(actualData);
	    System.out.println(expectedData);
	    Assert.assertTrue(actualData.equals(expectedData));
	}
	
	
		
	}



	public void matchGroupData(String jsonFile) throws Exception {
		
		LinkedHashMap<String, String> actualData=new LinkedHashMap<>();
		Thread.sleep(2000);
		WebElement record=driver.findElement(By.xpath("//a[@class='ng-star-inserted']"));
		record.click();
		Thread.sleep(2000);
		int q=1;
		for(WebElement attribute : acp.attributesOnViewPageForGroups) {
			WebElement t=driver.findElement(By.xpath("(//label[starts-with(@class,'label-width d-flex')])["+q+"]/parent::div/following-sibling::odp-view-separator/descendant::div/child::*[last()]"));
			String a=attribute.getAttribute("for");
			String w=t.getText();
			if(!w.equals("N.A.")) {
				actualData.put(a, w);
		}
			q+=2;;
		}
		
	LinkedHashMap<String, String> expectedData =(LinkedHashMap<String, String>) JsonUtils.getMapFromJSON(jsonFile);
	

	
	if(actualData.equals(expectedData)) {
		System.out.println("Data is matching");
	}
	else {
		
		System.err.println("Data is not matching.Unmatched data are as follows :");
	  MapDifference<String, String> diff = Maps.difference(actualData, expectedData);
	    Map<String, ValueDifference<String>> entriesDiffering = diff.entriesDiffering();
	    System.err.println(entriesDiffering);
	    Assert.assertTrue(actualData.equals(expectedData));
	}
		
	}



	public void addNewRecordForFile(String string) throws Exception {
		Thread.sleep(2000);
		if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
			acp.yes.click();
	    }
		if(!driver.findElements(By.xpath("//button[@id='addDataBtn']")).isEmpty()){
			applyExplicitWaitsUntilElementVisible(acp.addDataButton);
			applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();	
	    }
		
		applyExplicitWaitsUntilElementVisible(acp.textBox1);
		List<WebElement> textBoxes = driver.findElements(By.xpath("//input[@class='invisible position-absolute' or @id='_id']"));
		System.out.println(textBoxes.size());
		JSONObject jsonObject = JsonUtils.fetchJSONObject(string);
		System.out.println(string);
		for(int  i=1;i<=textBoxes.size();i++) {
		
			WebElement textBox = driver.findElement(By.xpath("(//input[@class='invisible position-absolute' or @id='_id'])["+i+"]"));
				String id1 = textBox.getAttribute("id");
		if(id1.equals("_id")) {
			
			String value=(String) jsonObject.get(id1);
			if(value!=null) {
			textBox.sendKeys(value);
			}
		}
		else {
			
//		JSONObject json=(JSONObject) jsonObject.get(id1);
		if(jsonObject.get(id1)!=null && textBox.isEnabled()) {
//			JSONObject value2=(JSONObject) json.get("metadata");
			String value=(String) jsonObject.get(id1);
			System.out.println(value);
			Thread.sleep(200);
//			textBox.clear();
			textBox.sendKeys(value);
			Thread.sleep(1000);
		}}
		}
		applyWait.waitForElementToBeClickable(acp.save, 30).click();
	}



	public void updateDataForFile(String id, String jsonFile) throws Exception {
		Thread.sleep(2000);
		if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
			acp.yes.click();
	    }
		applyWait.waitForElementToBeClickable(acp.idTab, 30).clear();
		Thread.sleep(1000);
		applyWait.waitForElementToBeClickable(acp.idTab, 30).sendKeys(id);;
		
		WebElement record=driver.findElement(By.xpath("//a[normalize-space()='"+id+"']"));
		record.click();
		applyWait.waitForElementToBeClickable(acp.edit, 30).click();
		Thread.sleep(1000);
		addNewRecordForFile(jsonFile);
		
	}

	}
