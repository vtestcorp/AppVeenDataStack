package pageModules;

import java.awt.Desktop.Action;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import helperMethods.DropDown;
import helperMethods.JavascriptClick;
import helperMethods.JsonUtils;
import helperMethods.ScrollTypes;
import helperMethods.WaitTypes;
import pageObjects.Object_AppCenterPage;
import pageObjects.Object_AuthorPage;
import pageObjects.Object_GroupPage;

public class Module_DesignTestCases extends BaseClass{
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
		lp=new LoginPage(driver);
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

//		
		try {
			applyWait.waitForElementToBeClickable(ap.groups, 30).click();
		} catch (Exception e1) {
			Thread.sleep(3000);
			handleElementClickException(ap.groups);
		}
//		applyExplicitWaitsUntilElementVisible(gp.group1,10);
		applyExplicitWaitsUntilElementVisible(gp.groups, 10);
		groups=new ArrayList<String>();
		for(WebElement group : gp.groups) {
			String group1=group.getText();
			groups.add(group1);
	}
		
		if(groups.contains(groupName)) {
			WebElement group=driver.findElement(By.xpath("//div[normalize-space()='"+groupName+"']/parent::div"));
			group.click();
			Thread.sleep(500);
			try {
				applyWait.waitForElementToBeClickable(gp.deleteGroup, 30).click();
			} catch (Exception e) {
				handleElementClickException(gp.deleteGroup);
			}
			Thread.sleep(500);
			applyWait.waitForElementToBeClickable(gp.delete, 30).click();

		}
		
	}

	public void createGroup(String groupName) throws Exception {
		applyExplicitWaitsUntilElementVisible(gp.group1,10);
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
		Thread.sleep(1000);
		if(lp.isType)
		{
			applyWait.waitForElementToBeClickable(gp.authorRole, 30).click();
			applyWait.waitForElementToBeClickable(gp.user, 30).click();
			applyWait.waitForElementToBeClickable(gp.blocked, 30).click();
			applyWait.waitForElementToBeClickable(gp.view, 30).click();
		}
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementVisible(gp.appCenterRoles, 10);
		javascriptClick.click(gp.appCenterRoles);
		Thread.sleep(1000);
		applyExplicitWaitsUntilElementVisible(gp.dsArrow, 10);
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
//		lp.VerifyUserExists(user1);
		applyWait.waitForElementToBeClickable(gp.members, 30).click();
		applyWait.waitForElementToBeClickable(gp.addUsers, 30).click();
		applyExplicitWaitsUntilElementVisible(gp.userEmail, 10);
		Thread.sleep(5000);
		
		By ele=By.xpath("//odp-user-list-cell-renderer[normalize-space()='"+userEmail+"']");
		applyWaitForDynamicWebElement(ele,10);
		WebElement user=driver.findElement(ele);
		applyExplicitWaitsUntilElementVisible(user,10);
		try {
		user.click();
		}
		catch(ElementClickInterceptedException e) {
			user=driver.findElement(By.xpath("//odp-user-list-cell-renderer[normalize-space()='"+userEmail+"']"));
			user.click();
		}
		applyWait.waitForElementToBeClickable(gp.done, 30).click();
		applyWait.waitForElementToBeClickable(gp.save, 30).click();
		
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
		Thread.sleep(2000);
		System.out.println(acp.textBoxes.size());
//		applyExplicitWaitsUntilElementVisible(acp.textBoxes, 10);
		applyExplicitWaitsUntilElementVisible(acp.idTextBox1, 10);

		List<WebElement> textBoxes = acp.textBoxes;
		String filePath=path + "\\testData\\" + data_Service + ".data.json";
		JSONObject jsonObject = JsonUtils.fetchJSONObject(string);
		
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

				else {
				for (int j = 1; j <= textBoxes.size(); j++) {
					String jsonValue=null;
					WebElement textBox = driver.findElement(By.xpath("(//*[contains(@class,'form-control')])[" + j + "]"));
					
					if (textBox.isEnabled()) {
						String id1 = textBox.getAttribute("id");

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
									if( jsonObject.get(id1).toString().equals("")){
										applyWait.waitForElementToBeClickable(textBox, 30).click();
									}
									else {
									dropdown.selectByVisibleText(textBox, ( jsonObject.get(id1)).toString());
									}
	
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
										if(JsonPath.read(string, "$."+id1+"").toString().equals("")) {
											applyWait.waitForElementToBeClickable(textBox, 30).click();
										}
										else {
										dropdown.selectByVisibleText(textBox, (JsonPath.read(string, "$."+id1+"").toString()));
										}
	
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
		JSONArray jsonArray = JsonUtils.getJSONArray(path+"\\testData\\string2.json");
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
		Thread.sleep(3000);
		if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
			acp.yes.click();
	    }
		Thread.sleep(2000);
		if(!driver.findElements(By.xpath("//button[@id='addDataBtn']")).isEmpty()){
			applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();	
	    }
		applyExplicitWaitsUntilElementVisible(acp.textBoxesLocation, 20);
		List<WebElement> textBoxes = acp.textBoxesLocation;
		JSONObject jsonObject = JsonUtils.fetchJSONObject(string);
	   	for (int j = 1; j <= textBoxes.size(); j++) {
			WebElement textBox = driver.findElement(By.xpath("(//input[@class='searchInput pac-target-input' or @id='_id'])["+j+"]"));
				if (textBox.isEnabled()) {
					String id1 = textBox.getAttribute("id");
						{		
					  if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")) {
						  if(jsonObject.get(id1)!=null)
						  {
							 applyWait.waitForElementToBeClickable(textBox,30).clear();
						     applyWait.waitForElementToBeClickable(textBox, 30).sendKeys((jsonObject.get(id1)).toString());
//						     applyWait.waitForElementToBeClickable(textBox,30).clear();
						     Thread.sleep(200);
						     textBox.sendKeys(Keys.DOWN);
						     Thread.sleep(200);
						     textBox.sendKeys(Keys.ENTER);
						     
						  }
					  }
					}
				}
		      }
		            applyWait.waitForElementToBeClickable(acp.save, 30).click();
		       }
	
					
	public void addRecordForUser(String string) throws InterruptedException {
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
		JSONObject jsonObject = JsonUtils.fetchJSONObject(string);
	   	for (int j = 1; j <= textBoxes.size(); j++) {
	   		String val =null;
			WebElement textBox = driver.findElement(By.xpath("(//*[contains(@class,'form-control')])["+j+"]"));
				if (textBox.isEnabled()) {
					String id1 = textBox.getAttribute("id");
					val = (String) jsonObject.get(id1);
//					if(id1.equals("_id"))
//					{
//						val = (String) jsonObject.get(id1);
//					}
//					else {
//	                        try {
//								JSONObject json  = (JSONObject) jsonObject.get(id1);
//								val = (String) json.get("_id");
//							} catch (NullPointerException e) {
//								continue;
//							}
		 //		}
					
					if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")||textBox.getAttribute("type").equals("select-one")) {

						   	if (val != null) {

									if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")) {
										
										     applyWait.waitForElementToBeClickable(textBox,30).clear();
			                            	applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(val);
			                            	
			                            	applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(Keys.DOWN);
			                            	Thread.sleep(500);
			                            	applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(Keys.ENTER);
									}
			   					}
			            	}
		            	}
			      
		      }

	            applyWait.waitForElementToBeClickable(acp.save, 30).click();
	      }
	
	
	
				public void addRecordForRichText(String string) throws InterruptedException {
					Thread.sleep(2000);
					if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
						acp.yes.click();
				    }
					Thread.sleep(2000);
					if(!driver.findElements(By.xpath("//button[@id='addDataBtn']")).isEmpty()){
						applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();	
				    }
					List<WebElement> textBoxes = acp.richtextBoxes;
					JSONObject jsonObject = JsonUtils.fetchJSONObject(string);
					for (int j = 1; j <= textBoxes.size(); j++) {
						WebElement textBox = driver.findElement(By.xpath("(//*[starts-with(@class,'tox-edit-area__iframe') or   @id='_id'])["+j+"]"));
						if (textBox.isEnabled()) {
							String val =null;
							String id1 = textBox.getAttribute("id");
							val = (String) jsonObject.get(id1);
							if(id1.equals("id"))
							{
								val = (String) jsonObject.get(id1);
							}
							 else {

			   						 driver.switchTo().frame(textBox);
			   						  WebElement child =driver.findElement(By.xpath("//body"));
			   						 id1 = child.getAttribute("data-id");
			   						 try {
			   						        String value = (String) jsonObject.get(id1);
			   						        applyWait.waitForElementToBeClickable(child, 30).sendKeys(value);
			   					        
			   						 }catch(Exception e) 
			   						 {
			   							 driver.switchTo().defaultContent();
			   							 continue;
			   						 }
			   						 driver.switchTo().defaultContent();
			   					  	 }
			   				   	 }
			   				}
										
							applyWait.waitForElementToBeClickable(acp.save, 30).click();
				}	
		
	

		
	public void addNewRecords() throws Exception {
		
		applyWait.waitForElementToBeClickable(acp.yes, 30).click();
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();	
		List<WebElement> textBoxes = acp.textBoxes;
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
	
	   public void addRecordInBooelan(String string) throws InterruptedException, MalformedURLException {
		   Thread.sleep(2000);
		   if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
				acp.yes.click();
		    }
		    
		      if(!driver.findElements(By.xpath("//button[@id='addDataBtn']")).isEmpty()){
					applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
			    }
		    Thread.sleep(5000);
			List<WebElement> buttons = acp.buttons;
			JSONObject jsonObject = JsonUtils.fetchJSONObject(string);
			for (int j = 1; j <= buttons.size(); j++) {
				WebElement button = driver.findElement(By.xpath("(//input[@type='checkbox' or @id='_id' ])["+j+"]"));
				String id1 = button.getAttribute("id");
				Thread.sleep(1000);
				if(button.isEnabled())
				   {
							
				   if (button.getAttribute("type").equals("text"))
				   	 {
					    String value = (String) jsonObject.get(id1);
					     applyWait.waitForElementToBeClickable(button, 30).sendKeys(value);
				   	 }
				else if (button.getAttribute("type").equals("checkbox")) {
					      WebElement parent = button.findElement(By.xpath("./.."));
					      if(jsonObject.get(id1).equals(true))
					      {
					         applyWait.waitForElementToBeClickable(parent, 30).click();
	              		   }
					      else if(jsonObject.get(id1).equals(false))
					      {
					    	   
					      }
				      }
				   	}
			}
			applyWait.waitForElementToBeClickable(acp.save, 30).click();
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
		Thread.sleep(1000);
		applyWait.waitForElementToBeClickable(acp.edit, 30).click();
		addRecord(jsonFile);
		
	}
	
     public void updateLocationRecord(String id, String jsonFile) throws Exception {
		
		if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
			acp.yes.click();
	    }
		applyWait.waitForElementToBeClickable(acp.idTab, 30).clear();
		applyWait.waitForElementToBeClickable(acp.idTab, 30).sendKeys(id);;
		
		WebElement record=driver.findElement(By.xpath("//a[normalize-space()='"+id+"']"));
		record.click();
		 applyWait.waitForElementToBeClickable(acp.edit, 30).click();
		addRecordForLocation(jsonFile);
     }
     
     public void updateRecordforUser(String id, String jsonFile) throws InterruptedException {
    	 Thread.sleep(2000);
 		if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
 			acp.yes.click();
 	    }
 		applyWait.waitForElementToBeClickable(acp.idTab, 30).clear();
		Thread.sleep(1000);
		applyWait.waitForElementToBeClickable(acp.idTab, 30).sendKeys(id);
		WebElement record=driver.findElement(By.xpath("//a[normalize-space()='"+id+"']"));
		record.click();
		Thread.sleep(1000);
		 applyWait.waitForElementToBeClickable(acp.edit, 30).click();
		Thread.sleep(1000);
		addRecordForUser(jsonFile);
		
	}
	



	public void updateRecords(String id, String attribute) throws InterruptedException {
		
		applyWait.waitForElementToBeClickable(acp.idTab, 30).sendKeys(id);;
		
		WebElement record=driver.findElement(By.xpath("//a[normalize-space()='"+id+"']"));
		record.click();
		applyWait.waitForElementToBeClickable(acp.edit, 30).click();
		WebElement textBox=driver.findElement(By.xpath("//span[normalize-space()='DS STRING TEXT 1008']/parent::label/parent::div/following-sibling::odp-form-control//descendant::input"));
		textBox.clear();;
		textBox.sendKeys(attribute);
		applyWait.waitForElementToBeClickable(acp.save, 30).click();
		
	}
	
	public void updateRecordForBooelan(String id , String jsonFile) throws InterruptedException, MalformedURLException {

        Thread.sleep(2000);
		if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
			acp.yes.click();
	    }
		applyWait.waitForElementToBeClickable(acp.idTab, 30).clear();
		Thread.sleep(1000);
		applyWait.waitForElementToBeClickable(acp.idTab, 30).sendKeys(id);
		WebElement record=driver.findElement(By.xpath("//a[normalize-space()='"+id+"']"));
		record.click();
		Thread.sleep(1000);
		 applyWait.waitForElementToBeClickable(acp.edit, 30).click();
		Thread.sleep(1000);
		addRecordInBooelan(jsonFile);
	}



	public void fetchRecord(String id) throws Exception {
		this.id=id;
		
		if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
			try {
				acp.yes.click();
			} catch (Exception e) {
				Thread.sleep(500);
				acp.yes.click();
			}
	    }
		Thread.sleep(1000);
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
		applyExplicitWaitsUntilElementVisible(acp.dataService, 10);
		WebElement record=driver.findElement(By.xpath("//a[@class='ng-star-inserted']"));
		record.click();
		int q=1;
		applyExplicitWaitsUntilElementVisible(acp.attributesOnViewPage, 10);
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
	System.out.println("Expected List :"  + expectedData );
	if(actualData.equals(expectedData)) {
		System.out.println("Data is matching");
	}
	else {
		
		System.err.println("Data is not matching.Unmatched data are as follows :");
	  MapDifference<String, String> diff = Maps.difference(actualData, expectedData);
	    Map<String, ValueDifference<String>> entriesDiffering = diff.entriesDiffering();
	    System.err.println(entriesDiffering);
	    System.out.println(expectedData);
	    System.out.println(actualData);
	    Assert.assertTrue(actualData.equals(expectedData));
	}
	}
	
   public void matchRecorforBoolen(String jsonFile) throws MalformedURLException {
		LinkedHashMap<String, String> actualData=new LinkedHashMap<>();
		applyExplicitWaitsUntilElementVisible(acp.dataService, 10);
		WebElement record=driver.findElement(By.xpath("//a[@class='ng-star-inserted']"));
		record.click();
		int q=1;
		applyExplicitWaitsUntilElementVisible(acp.attributesOnViewPage, 10);
		for(WebElement attribute : acp.attributesOnViewPage) {
			WebElement t=driver.findElement(By.xpath("((//label[starts-with(@class,'label-width d-flex')])["+q+"]/parent::div/following-sibling::odp-view-separator/descendant::div/child::*)[last()]"));
			String a=attribute.getAttribute("for");
			String w=t.getText();
			if(!w.equals("N.A.")) {
				actualData.put(a, w);
		}
			q++;
		}
		
	LinkedHashMap<String, String> expectedData =(LinkedHashMap<String, String>) JsonUtils.getMapFromJSON(jsonFile);
	System.out.println("Expected List :"  + expectedData );
	if(actualData.equals(expectedData)) {
		System.out.println("Data is matching");
	}
	else {
		
		System.err.println("Data is not matching.Unmatched data are as follows :");
	    MapDifference<String, String> diff = Maps.difference(actualData, expectedData);
	    Map<String, ValueDifference<String>> entriesDiffering = diff.entriesDiffering();
	    System.err.println(entriesDiffering);
	    System.out.println(expectedData);
	    System.out.println(actualData);
	    Assert.assertTrue(actualData.equals(expectedData));
	}
	
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
			 javascriptClick.sendKeys(acp.dsSTRINGTEXT1001, dsStringText1001);
			 javascriptClick.sendKeys(acp.dsSTRINGTEXT1002, dsStringText1002);
			 javascriptClick.click(acp.click_On_Checkbox);
			 javascriptClick.click(acp.click_On_Delete);
			 javascriptClick.click(acp.delete_Record);
  }
	
	
	public void deleteRecordFromViewPage(String dsStringText1001 , String dsStringText1002) throws Exception {
		 applyWait.waitForElementToBeClickable(acp.idTab, 30).clear();
		 javascriptClick.sendKeys(acp.dsSTRINGTEXT1001, dsStringText1001);
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
			 javascriptClick.sendKeys(acp.dsSTRINGTEXT1001, dsStringText1001);
			 javascriptClick.sendKeys(acp.dsSTRINGTEXT1002, dsStringText1002);
			 acp.dsSTRINGTEXT1002.sendKeys(Keys.ENTER);
	}



	public void matchDataCurrency(String jsonFile) throws Exception {

		LinkedHashMap<String, String> actualData=new LinkedHashMap<>();
		applyExplicitWaitsUntilElementVisible(acp.dataService, 10);
		WebElement record=driver.findElement(By.xpath("//a[@class='ng-star-inserted']"));
		record.click();
		applyExplicitWaitsUntilElementVisible(acp.attributesOnViewPage, 10);
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
	System.out.println("Expected List :"  + expectedData );	

	
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
		
		////odp-view-control/div/div/label[starts-with(@class,'label-width')]
		
		//label[@for='dsLocation1003']/parent::div/following-sibling::odp-view-separator/descendant::div[5]
		
		LinkedHashMap<String, String> actualData=new LinkedHashMap<>();
		applyExplicitWaitsUntilElementVisible(acp.dataService, 10);
		WebElement record=driver.findElement(By.xpath("//a[@class='ng-star-inserted']"));
		record.click();
		int q=1;
		applyExplicitWaitsUntilElementVisible(acp.attributesOnViewPageForGroups, 10);
		for(WebElement attribute : acp.attributesOnViewPageForGroups) {
			WebElement value=driver.findElement(By.xpath("((//label[starts-with(@class,'label-width d-flex')])/parent::div/following-sibling::odp-view-separator/descendant::div/child::*[last()])["+q+"]"));
			WebElement key=driver.findElement(By.xpath("((//label[starts-with(@class,'label-width d-flex')])/parent::div/following-sibling::odp-view-separator/descendant::div/child::*[last()])["+q+"]/ancestor::odp-view-separator/preceding-sibling::div//label"));
			String a=key.getAttribute("for");
			String w=value.getText();
			if(!w.equals("N.A.")) {
				actualData.put(a, w);
		}
//			q+=2;;
			q++;
		}
		
	LinkedHashMap<String, String> expectedData =(LinkedHashMap<String, String>) JsonUtils.getMapFromJSON(jsonFile);

	
	if(actualData.equals(expectedData)) {
		System.out.println("Data is matching");
		  System.out.println(actualData);
		    System.out.println(expectedData);
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
		//

	}



	public void addNewRecordForFile(String string) throws Exception {
		Thread.sleep(1000);
		if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
			acp.yes.click();
	    }
		Thread.sleep(2000);
		if(!driver.findElements(By.xpath("//button[@id='addDataBtn']")).isEmpty()){
			applyExplicitWaitsUntilElementVisible(acp.addDataButton,10);
			applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();	
	    }
		applyExplicitWaitsUntilElementVisible(acp.textBox1,10);
		
		List<WebElement> textBoxes = driver.findElements(By.xpath("//input[@class='invisible position-absolute' or @id='_id']"));
		JSONObject jsonObject = JsonUtils.fetchJSONObject(string);
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
			
		if(jsonObject.get(id1)!=null && textBox.isEnabled()) {
			String value=(String) jsonObject.get(id1);
			Thread.sleep(200);
			String absolutePath=new File("files\\"+value).getAbsolutePath();
			textBox.sendKeys(absolutePath);
			Thread.sleep(500);
		}}
		}
		applyWait.waitForElementToBeClickable(acp.save, 30).click();
	}



	public void updateDataForFile(String id, String jsonFile) throws Exception {
		Thread.sleep(1000);
		if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
			acp.yes.click();
	    }
		applyWait.waitForElementToBeClickable(acp.idTab, 30).clear();
		applyWait.waitForElementToBeClickable(acp.idTab, 30).sendKeys(id);;
		applyExplicitWaitsUntilElementVisible(acp.record, 10);
		WebElement record=driver.findElement(By.xpath("//a[normalize-space()='"+id+"']"));
		record.click();
		applyWait.waitForElementToBeClickable(acp.edit, 30).click();
		addNewRecordForFile(jsonFile);
		
	}



	public void matchToRecordForFileType(String jsonFile) throws Exception {
		
		LinkedHashMap<String, String> actualData=new LinkedHashMap<>();
		applyExplicitWaitsUntilElementVisible(acp.dataService, 10);
		WebElement record=driver.findElement(By.xpath("//a[@class='ng-star-inserted']"));
		record.click();
		int q=1;
		applyExplicitWaitsUntilElementVisible(acp.attributesOnViewPage, 10);
		for(WebElement attribute : acp.attributesOnViewPage) {
			WebElement t=driver.findElement(By.xpath("(//label[starts-with(@class,'label-width d-flex')])["+q+"]/parent::div/following-sibling::odp-view-separator/descendant::*[contains(@class,'text') or contains(@class,'value-width')]"));

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
	    Assert.assertTrue(actualData.equals(expectedData));
	}
		
	}



public void addRecordForDate(String jsonFile) throws Exception {
		
		Thread.sleep(1000);
		if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
			acp.yes.click();
	    }
		Thread.sleep(2000);
		if(!driver.findElements(By.xpath("//button[@id='addDataBtn']")).isEmpty()){
			applyExplicitWaitsUntilElementVisible(acp.addDataButton,10);
			applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();	
	    }
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementVisible(acp.textBox1,30);
		List<WebElement> textBoxes = acp.dateFields;
		String path = System.getProperty("user.dir");
		String filePath=path + "\\testData\\" + data_Service + ".data.json";
		JSONObject jsonObject = JsonUtils.fetchJSONObject(jsonFile);
		
		for (int j = 1; j <= textBoxes.size(); j++) {
			WebElement textBox = driver.findElement(By.xpath("(//*[@class='btn btn-link mr-2 p-0' or @id='_id'])[" + j + "]"));
			if (textBox.isEnabled()) {
				String id1="";
				String dateValue="";
				try {
				id1 = textBox.getAttribute("id");
				dateValue=jsonObject.get(id1).toString();
				System.out.println(id1+"-----"+dateValue);
				}
				catch(NullPointerException e) {
					continue;
				}
				
				if(id1.equals("_id")) {
					applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(dateValue.toString());
				}
				else {
					
					if(dateValue.equals("")) {
						WebElement selectDate=driver.findElement(By.id(id1));
						selectDate.click();
						applyExplicitWaitsUntilElementVisible(acp.cancelButton, 20);
						acp.cancelButton.click();
						
					}
					else {
					
					String fullDate[]=dateValue.split("T")[0].split("-");
					String fullTime[]=dateValue.split("T")[1].split(":");
					String date=fullDate[2];
					String month=fullDate[1];
					String year=fullDate[0];
					String hour=fullTime[0];
					String minute=fullTime[1];
					String second1=fullTime[2].replace("Z", "");
					Integer second2=(int)Float.parseFloat(second1);
					String second=second2.toString();
					
					if(second.length()==1) {
						second="0"+second;
					}
					
					String emptyArray[]= {"00","00","00Z"};
					WebElement selectDate=driver.findElement(By.id(id1));
					selectDate.click();
					dropdown.selectByValue(acp.yearDropDown, year);
					dropdown.selectByIndex(acp.monthDropDown, Integer.parseInt(month)-1);
					applyExplicitWaitsUntilElementVisible(acp.day,10);
					WebElement date1=driver.findElement(By.xpath("//span[contains(@class,'disabled')=false and @id='_day']["+date+"]"));
					date1.click();
					
					if(lp.isDateTime) {
						
						dropdown.selectByValue(acp.hourDropDown, hour);
						dropdown.selectByValue(acp.minuteDropDown, minute);
						dropdown.selectByValue(acp.secondDropDown, second);
						
					}
					
					applyWait.waitForElementToBeClickable(acp.doneButton, 30).click();
					Thread.sleep(500);
					}
				}
			}
			}
		applyWait.waitForElementToBeClickable(acp.save, 30).click();
		
	}



public void matchDateData(String jsonFile) throws Exception {

	LinkedHashMap<String, String> actualData=new LinkedHashMap<>();
	applyExplicitWaitsUntilElementVisible(acp.dataService, 10);
	WebElement record=driver.findElement(By.xpath("//a[@class='ng-star-inserted']"));
	record.click();
	int q=1;
	applyExplicitWaitsUntilElementVisible(acp.attributesOnViewPage, 10);
	for(WebElement attribute : acp.attributesOnViewPage) {
		WebElement t=driver.findElement(By.xpath("((//label[starts-with(@class,'label-width d-flex')])["+q+"]/parent::div/following-sibling::odp-view-separator/descendant::*[contains(@class,'value')])[last()]"));
		
		String a=attribute.getAttribute("for");
		String w=t.getText();
		System.out.println(q+"      "+a+"      "+w);
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
    System.out.println(expectedData);
    System.out.println(actualData);
    Assert.assertTrue(actualData.equals(expectedData));
}

	
}



public void updateRecordForDate(String id, String jsonFile) throws Exception {
	Thread.sleep(1000);
	if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
		acp.yes.click();
    }
	applyWait.waitForElementToBeClickable(acp.idTab, 30).clear();
	applyWait.waitForElementToBeClickable(acp.idTab, 30).sendKeys(id);;
	applyExplicitWaitsUntilElementVisible(acp.record, 10);
	WebElement record=driver.findElement(By.xpath("//a[normalize-space()='"+id+"']"));
	record.click();
	applyWait.waitForElementToBeClickable(acp.edit, 30).click();
	addRecordForDate(jsonFile);
	
}



public void matchLocationData(String jsonFile) throws Exception {
	

	
	//odp-view-control/div/div/label[starts-with(@class,'label-width')]
	
	//label[@for='dsLocation1003']/parent::div/following-sibling::odp-view-separator/descendant::div[5]
	
	LinkedHashMap<String, String> actualData=new LinkedHashMap<>();
	applyExplicitWaitsUntilElementVisible(acp.dataService, 10);
	WebElement record=driver.findElement(By.xpath("//a[@class='ng-star-inserted']"));
	record.click();
//	int q=1;
	
	applyExplicitWaitsUntilElementVisible(acp.attributesOnViewPageForLocation, 10);
	System.out.println(acp.attributesOnViewPageForLocation.size());
//	for(WebElement attribute : acp.attributesOnViewPageForLocation) {
	for(int q=1;q<=acp.attributesOnViewPageForLocation.size();q++) {
		WebElement value = null;
		WebElement key = null;
		
		if(q==1) {
			 value=driver.findElement(By.xpath("(//odp-view-control/div/div/label[starts-with(@class,'label-width')])["+q+"]/parent::div/following-sibling::odp-view-separator/descendant::div/span"));
			key=driver.findElement(By.xpath("(//odp-view-control/div/div/label[starts-with(@class,'label-width')])["+q+"]"));
			
		}
		else {
//			WebElement ele=driver.findElement(By.xpath("(//odp-view-control/div/div/label[starts-with(@class,'label-width')])["+q+"]/parent::div/following-sibling::odp-view-separator//span"));
//			if(!ele.getText().equals("N.A.")) {
			 try {
				value=driver.findElement(By.xpath("(//odp-view-control/div/div/label[starts-with(@class,'label-width')])["+q+"]/parent::div/following-sibling::odp-view-separator/descendant::div[5]"));
				 key=driver.findElement(By.xpath("(//odp-view-control/div/div/label[starts-with(@class,'label-width')])["+q+"]"));
			} catch (Exception e) {
				continue;
			}
//		}
		}
	
		
			String a = key.getAttribute("for");
			String w = value.getText();
		
		if(!w.equals("N.A.")) {
			actualData.put(a, w);
	}
//		q++;
	}
	
LinkedHashMap<String, String> expectedData =(LinkedHashMap<String, String>) JsonUtils.getMapFromJSON(jsonFile);


if(actualData.equals(expectedData)) {
	System.out.println("Data is matching");
	  System.out.println(actualData);
	    System.out.println(expectedData);
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
	//


	
}

	}
