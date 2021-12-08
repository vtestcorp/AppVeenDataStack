package pageModules;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.google.common.collect.Maps;
import base.BaseClass;
import config.Constants;
import helperMethods.DropDown;
import helperMethods.JavascriptClick;
import helperMethods.JsonUtils;
import helperMethods.Property;
import helperMethods.ScrollTypes;
import helperMethods.WaitTypes;
import io.cucumber.datatable.DataTable;
import pageObjects.Object_AppCenterPage;
import pageObjects.Object_AuthorPage;
import pageObjects.Object_GroupPage;
import org.assertj.core.api.SoftAssertions;

public class Module_DesignTestCases extends BaseClass{
	private WaitTypes applyWait;
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
	public static List<String> availableStateList;
	
	public Module_DesignTestCases(WebDriver driver) {
		this.driver = driver;
		this.applyWait = new WaitTypes(driver);
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
		applyWait.waitForElementToBeClickable(ap.emailIDTextBox, 30).sendKeys(Constants.User_Email_Id);
		applyWait.waitForElementToBeClickable(ap.nextButton, 30).click();
		applyWait.waitforElementToBeDisplayed(ap.password, 30).sendKeys(Constants.User_Password);;
		applyWait.waitforElementToBeDisplayed(ap.signInButton, 30).click();
		String dataName=Constants.testData_Folder  +dataService + Constants.json_File_Suffix;
		lp.createNewDataServices(JsonUtils.getArrayValues(dataName, "definition"),dataService);
		
	}

	public void groupexist(String groupName) throws Exception {
		

		try {
			applyWait.waitForElementToBeClickable(ap.groups, 30).click();
		} catch (Exception e1) {
			Thread.sleep(3000);
			handleElementClickException(ap.groups);
		}
		applyExplicitWaitsUntilElementVisible(gp.groups, 30);
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
		applyExplicitWaitsUntilElementVisible(gp.group1,30);
		List<WebElement> groupNames=gp.groups;
		ArrayList<String> groups=new ArrayList<String>();
			for(WebElement group : groupNames) {
				String group1=group.getText();
				groups.add(group1);
		}
			if(!groups.contains(groupName)) {
				applyWait.waitForElementToBeClickable(gp.newGroup, 30).click();
				applyWait.waitForElementToBeClickable(gp.groupName, 30).sendKeys(groupName);;
				Thread.sleep(500);
				applyWait.waitForElementToBeClickable(gp.createButton, 30).click();
			}
	}

	public void assignPermission(String dataServiceName,String user1) throws Exception {
		String role="Manage";
		String userEmail=user1;
		
		Thread.sleep(1000);
		if(lp.isUserType)
		{
			applyWait.waitForElementToBeClickable(gp.authorRole, 30).click();
			applyWait.waitForElementToBeClickable(gp.user, 30).click();
			applyWait.waitForElementToBeClickable(gp.blocked, 30).click();
			applyWait.waitForElementToBeClickable(gp.view, 30).click();
		}
		Thread.sleep(3000);
		applyExplicitWaitsUntilElementVisible(gp.appCenterRoles, 30);
		javascriptClick.click(gp.appCenterRoles);
		Thread.sleep(1000);
		
//		if(url.equals(Constants.qaInstance)) {
//		
//		applyExplicitWaitsUntilElementVisible(gp.dsArrow, 30);
//		WebElement dsArrow=driver.findElement(By.xpath("//span[normalize-space()='"+dataServiceName+"']/parent::div/following-sibling::span[2]/child::span"));
//		applyWait.waitForElementToBeClickable(dsArrow, 30).click();
//		
//		if(role.equalsIgnoreCase("SkipReview")) {
//			applyWait.waitForElementToBeClickable(gp.skipReviewToggler, 30).click();
//			}
//		else if(role.equalsIgnoreCase("Manage")) {
//			applyWait.waitForElementToBeClickable(gp.manageToggler, 30).click();
//		}
//		
//		else if(role.equalsIgnoreCase("View")) {
//			applyWait.waitForElementToBeClickable(gp.viewToggler, 30).click();
//			}
//		
//		else {
//			WebElement toggler=driver.findElement(By.xpath("//span[contains(text(),'"+role+"')]/parent::div/following-sibling::span[2]/child::label/child::span[2]"));
//			applyWait.waitForElementToBeClickable(toggler, 30).click();
//			}
//		
//		if(lp.isRelation) {
//			WebElement dsArrow1=driver.findElement(By.xpath("//span[normalize-space()='"+lp.anotherDataService+"']/parent::div/following-sibling::span[2]/child::span"));
//			applyWait.waitForElementToBeClickable(dsArrow1, 30).click();
//			
//			if(role.equalsIgnoreCase("SkipReview")) {
//				applyWait.waitForElementToBeClickable(gp.skipReviewToggler, 30).click();
//				}
//			else if(role.equalsIgnoreCase("Manage")) {
//				applyWait.waitForElementToBeClickable(gp.manageToggler, 30).click();
//			}
//			
//			else if(role.equalsIgnoreCase("View")) {
//				applyWait.waitForElementToBeClickable(gp.viewToggler, 30).click();
//				}
//			
//			else {
//				WebElement toggler=driver.findElement(By.xpath("//span[contains(text(),'"+role+"')]/parent::div/following-sibling::span[2]/child::label/child::span[2]"));
//				applyWait.waitForElementToBeClickable(toggler, 30).click();
//				}
//		}
//		}
		if(url.equals(Constants.bifrostInstance)  ||  url.equals(Constants.qaInstance)){
			By dataService1 = By.xpath("//div[contains(@class,'ds-name')]//span[normalize-space()='"+dataServiceName+"']");
			Actions action =new Actions(driver);
			action.moveToElement(driver.findElement(dataService1));
			applyWaitForDynamicWebElement(dataService1, 30);
			WebElement dataService = driver.findElement(dataService1);
			applyWait.waitForElementToBeClickable(dataService, 30).click();
			By toggler=By.xpath("//span[contains(text(),'"+role+"')]/parent::div/following-sibling::span[2]/child::label/child::span[2]");
			applyWaitForDynamicWebElement(toggler, 30);
			WebElement roleToggler = driver.findElement(toggler);
			applyWait.waitForElementToBeClickable(roleToggler, 30).click();
			
			if(lp.isRelation) {
				By data = By.xpath("//div[contains(@class,'ds-name')]//span[normalize-space()='"+lp.anotherDataService+"']");
				action.moveToElement(driver.findElement(data));
				applyWaitForDynamicWebElement(data, 30);
				WebElement dataServiceRelate = driver.findElement(data);
				applyWait.waitForElementToBeClickable(dataServiceRelate, 30).click();
				applyWaitForDynamicWebElement(toggler, 30);
				WebElement roleToggler1 = driver.findElement(toggler);
				applyWait.waitForElementToBeClickable(roleToggler1, 30).click();
			}
		}
		applyWait.waitForElementToBeClickable(gp.members, 30).click();
		applyWait.waitForElementToBeClickable(gp.addUsers, 30).click();
		applyExplicitWaitsUntilElementVisible(gp.userEmail, 30);
		Thread.sleep(1000);
		
		By ele=By.xpath("//odp-user-list-cell-renderer[normalize-space()='"+userEmail+"']");
		
		applyWaitForDynamicWebElement(ele, 30);
		WebElement user=driver.findElement(ele);
		try {
		user.click();
		}
		catch(ElementClickInterceptedException e) {
			user=driver.findElement(By.xpath("//odp-user-list-cell-renderer[normalize-space()='"+userEmail+"']"));
			user.click();
		}
		applyWait.waitForElementToBeClickable(gp.done, 30).click();
		Thread.sleep(1000);
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
		applyExplicitWaitsUntilElementVisible(acp.idTextBox1, 30);

		List<WebElement> textBoxes = acp.textBoxes;
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
	}
		if(acp.errorMessages.isEmpty()) {
			errorMessage=string+" - Custom Error Expected";
			Assert.assertTrue(false);
//			assertCheck.assertTrue(false);
		}
	}

	public void saveButtonIsDisable() {
		
	Boolean status=	acp.saveButton.isEnabled();
	if(status) {
		Assert.assertTrue(false);
	}
	}

	public void addRecordForRepeatedId() throws Exception {

		applyWait.waitForElementToBeClickable(acp.yes, 30).click();
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();	
		Thread.sleep(2000);
		List<WebElement> textBoxes = acp.textBoxes;
		String path= System.getProperty("user.dir");
		JSONArray jsonArray = JsonUtils.getJSONArray(Constants.testData_Folder + "string2.json");
		for(int i=0;i<jsonArray.size();i++) {
			Thread.sleep(1000);
			JSONObject jsonObject=(JSONObject) jsonArray.get(i);

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
		Thread.sleep(5000);
		applyExplicitWaitsUntilElementVisible(acp.textBoxesLocation, 30);
		List<WebElement> textBoxes = acp.textBoxesLocation;
		JSONObject jsonObject = JsonUtils.fetchJSONObject(string);
	   	for (int j = 1; j <= textBoxes.size(); j++) {
	   		Thread.sleep(1000);
			WebElement textBox = driver.findElement(By.xpath("(//input[@class='searchInput pac-target-input' or @id='_id'])["+j+"]"));
				if (textBox.isEnabled()) {
					String id1 = textBox.getAttribute("id");
						{		
					  if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")) {
						  if(jsonObject.get(id1)!=null)
						  {
							 applyWait.waitForElementToBeClickable(textBox,30).clear();
						     applyWait.waitForElementToBeClickable(textBox, 30).sendKeys((jsonObject.get(id1)).toString());
						     Thread.sleep(500);
						     textBox.sendKeys(Keys.DOWN);
						     Thread.sleep(500);
						     textBox.sendKeys(Keys.ENTER);
						  }
					  }
					}
				}
		      }

	   				Thread.sleep(2000);
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
	
	
				public void addRecordForRichText(String string) throws InterruptedException, MalformedURLException {
					Thread.sleep(2000);
					if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
						acp.yes.click();
				    }
					Thread.sleep(2000);
					if(!driver.findElements(By.xpath("//button[@id='addDataBtn']")).isEmpty()){
						applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();	
				    }
					Thread.sleep(5000);
					List<WebElement> textBoxes = acp.richtextBoxes;
					JSONObject jsonObject = JsonUtils.fetchJSONObject(string);
					for (int j = 1; j <= textBoxes.size(); j++) {
						String val =null;
						WebElement textBox = driver.findElement(By.xpath("(//*[starts-with(@class,'tox-edit-area__iframe') or   @id='_id'])["+j+"]"));
						if (textBox.isEnabled()) {
							String id1 = textBox.getAttribute("id");
						if(id1.equals("_id"))
							{
								val = (String) jsonObject.get(id1);
								applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(val);
							}
						 else {
    		   						 driver.switchTo().frame(textBox);
			   						  WebElement child =driver.findElement(By.xpath("//body"));
			   						 id1 = child.getAttribute("data-id");
			   						 try {
			   							  child.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
			   							    Thread.sleep(500);
			   						        String value = (String) jsonObject.get(id1);
			   						        Thread.sleep(500);
			   						     applyWait.waitForElementToBeClickable(child, 30).sendKeys(value);
			   						       child.sendKeys(Keys.TAB);
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
		JSONArray jsonArray = JsonUtils.getJSONArray(Constants.testData_Folder + "string3.json");
		for(int i=0;i<jsonArray.size();i++) { 
			JSONObject jsonObject=(JSONObject) jsonArray.get(i);

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
	
	   public void addRecordInBoolean(String string) throws InterruptedException, MalformedURLException {
		   Thread.sleep(5000);
		   if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
				acp.yes.click();
		    }
		    
		      if(!driver.findElements(By.xpath("//button[@id='addDataBtn']")).isEmpty()){
					applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
			    }
		    Thread.sleep(2000);
		    try {
				applyExplicitWaitsUntilElementVisible(acp.idTextBox, 5);
			} catch (Exception e1) {
				driver.navigate().refresh();
				 Thread.sleep(3000);
				if(!driver.findElements(By.xpath("//button[@id='addDataBtn']")).isEmpty()){
					applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
				    }
				applyExplicitWaitsUntilElementVisible(acp.idTextBox, 5);
			}
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
					      if(jsonObject.get(id1)!=null) {
					      try {
						} catch (NullPointerException e) {
							continue;
						}
					      if(jsonObject.get(id1).equals(true) && button.getAttribute("class").contains("ng-pristine"))
					      {
					         applyWait.waitForElementToBeClickable(parent, 30).click();
	              		}
					      else if(jsonObject.get(id1).equals(false) && button.getAttribute("class").contains("ng-dirty")){
					    	  applyWait.waitForElementToBeClickable(parent, 30).click();
					      }
				      }
				      }
				   }
			}
			
			applyWait.waitForElementToBeClickable(acp.save, 30).click();
	    }
	   
	   public void mapFileSettingToImport(String jsonfile) throws MalformedURLException {
			JSONObject jsonObject = JsonUtils.fetchJSONObject(jsonfile);
			System.out.println(jsonObject.get("sheetToRead").toString());
			String sheetName= jsonObject.get("sheetToRead").toString();
			if(!(jsonObject.get("rowsToSkipFromTop")==null))
			{
			  	String rowValue = jsonObject.get("rowsToSkipFromTop").toString();
			   	applyWait.waitForElementToBeClickable(acp.sheetTopRow, 30).sendKeys(rowValue);
			}
			if(!(jsonObject.get("rowsToSkipFromBottom")==null))
			{
				String bottomRow = jsonObject.get("rowsToSkipFromBottom").toString();
			   	applyWait.waitForElementToBeClickable(acp.sheetBottomRow, 30).sendKeys(bottomRow);
			}
			
			String header= jsonObject.get("markFirstRecordAsHeader").toString();
			
			applyExplicitWaitsUntilElementVisible(acp.sheetToReadDropdown, 30);
			dropdown.selectByVisibleText(acp.sheetToReadDropdown, sheetName);
		
		}
	   
	   public void userNavigateToColumnMappingPage() throws MalformedURLException, InterruptedException {
		   Thread.sleep(1000);
		   applyWait.waitForElementToBeClickable(acp.next, 30).click();
		  }
	   

		public void userNavigateToValidateRecord() throws InterruptedException {
			 applyWait.waitForElementToBeClickable(acp.here, 30).click();
			 Thread.sleep(1000);
			 applyWait.waitForElementToBeClickable(acp.notification, 30).click();
			 applyWait.waitForElementToBeClickable(acp.notification_body, 30).click();
			 Thread.sleep(500);
		}
	   
	  
	   
	   public void mapColumnToValue(String destination, String source) throws InterruptedException {
		   By src = By.xpath("//div[normalize-space()='"+destination+"']//input[contains(@class,'colDiv')]");
		   Thread.sleep(1000);
		   applyWaitForDynamicWebElement(src, 30);
		   WebElement dest=driver.findElement(src);
	
		   String data = dest.getAttribute("value");
		   System.out.println(dest.getAttribute("value"));
		   System.out.println(source);
		 if(!dest.getAttribute("value").equals(source))
		 {
			 dest.clear();
			 dest.sendKeys(source,Keys.TAB.toString()); 
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

	
   public void updateRecordForRichText(String id, String jsonFile) throws InterruptedException, MalformedURLException {
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
		addRecordForRichText(jsonFile);

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
		addRecordInBoolean(jsonFile);
	}



	public void fetchRecord(String id) throws Exception {
		this.id=id;
		Thread.sleep(1000);
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
		Thread.sleep(500);
		applyWait.waitForElementToBeClickable(acp.idTab, 30).sendKeys(Keys.ENTER);;
	}

	public void recordMustNotExist() throws Exception {
		Thread.sleep(1000);
		if(!driver.findElements(By.xpath("//span[text()='No records to display']")).isEmpty()){
			System.err.println("No record found");
	    }
	}

	public void matchToRecord(String jsonFile) throws Exception {
		
		LinkedHashMap<String, String> actualData=new LinkedHashMap<>();
		applyExplicitWaitsUntilElementVisible(acp.dataService, 30);
		WebElement record=driver.findElement(By.xpath("//a[@class='ng-star-inserted']"));
		record.click();
		Thread.sleep(1000);
		int q=1;
		applyExplicitWaitsUntilElementVisible(acp.attributesOnViewPage, 30);
		for(WebElement attribute : acp.attributesOnViewPage) {
			
			WebElement value=driver.findElement(By.xpath("((//label[starts-with(@class,'label-width d-flex')])/parent::div/following-sibling::odp-view-separator/descendant::div/child::*[last()])["+q+"]"));
			Thread.sleep(1000);
			WebElement key=driver.findElement(By.xpath("((//label[starts-with(@class,'label-width d-flex')])/parent::div/following-sibling::odp-view-separator/descendant::div/child::*[last()])["+q+"]/ancestor::odp-view-separator/preceding-sibling::div//label"));
			
			String a=key.getAttribute("for");
			String w=value.getText();
			if(!w.equals("N.A.")) {
				actualData.put(a, w);
		}
			q++;
		}
		
		matchData(jsonFile, actualData);
	}
	
   public void matchRecordforBoolean(String jsonFile) throws Exception {
		LinkedHashMap<String, String> actualData=new LinkedHashMap<>();
		applyExplicitWaitsUntilElementVisible(acp.dataService, 30);
		WebElement record=driver.findElement(By.xpath("//a[@class='ng-star-inserted']"));
		record.click();
		int q=1;
		applyExplicitWaitsUntilElementVisible(acp.attributesOnViewPage, 30);
		for(WebElement attribute : acp.attributesOnViewPage) {
			WebElement t=driver.findElement(By.xpath("((//label[starts-with(@class,'label-width d-flex')])["+q+"]/parent::div/following-sibling::odp-view-separator/descendant::div/child::*)[last()]"));
			String a=attribute.getAttribute("for");
			String w=t.getText();
			if(!w.equals("N.A.")) {
				actualData.put(a, w);
		}
			q++;
		}
		
		matchData(jsonFile, actualData);

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
		applyExplicitWaitsUntilElementVisible(acp.dataService, 30);
		WebElement record=driver.findElement(By.xpath("//a[@class='ng-star-inserted']"));
		record.click();
		applyExplicitWaitsUntilElementVisible(acp.attributesOnViewPage, 30);
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
		
		matchData(jsonFile, actualData);
	}


	public void matchGroupData(String jsonFile) throws Exception {
		
		LinkedHashMap<String, String> actualData=new LinkedHashMap<>();
		applyExplicitWaitsUntilElementVisible(acp.dataService, 30);
		WebElement record=driver.findElement(By.xpath("//a[@class='ng-star-inserted']"));
		record.click();
		Thread.sleep(1000);
		int q=1;
		applyExplicitWaitsUntilElementVisible(acp.attributesOnViewPageForGroups, 30);
		for(WebElement attribute : acp.attributesOnViewPageForGroups) {
			
			WebElement value=driver.findElement(By.xpath("(//*[contains(@class,'value-wrapper')]//span[(last() and not(contains(@class, 'mr-2')) and not(contains(@class, 'ml-2')))] | //odp-view-date//div[@class='font-weight-bold value-wrapper'] | //span[text()='Raw location']/ancestor::div[contains(@class,'label-wrapper')]/following-sibling::div | //odp-view-user//a[@class='ng-star-inserted'])["+q+"]"));
			WebElement key=driver.findElement(By.xpath("(//*[contains(@class,'value-wrapper')]//span[(last() and not(contains(@class, 'mr-2')) and not(contains(@class, 'ml-2')))] | //odp-view-date//div[@class='font-weight-bold value-wrapper'] | //span[text()='Raw location']/ancestor::div[contains(@class,'label-wrapper')]/following-sibling::div | //odp-view-user//a[@class='ng-star-inserted'])["+q+"]/ancestor::odp-view-separator/preceding-sibling::div//label"));
			
			String a=key.getAttribute("for");
			String w=value.getText();
			if(!w.equals("N.A.")) {
				actualData.put(a, w);
		}
			q++;
		}

		matchData(jsonFile, actualData);
	}

	public void addNewRecordForFile(String string) throws Exception {
		Thread.sleep(1000);
		if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
			acp.yes.click();
	    }
		Thread.sleep(2000);
		if(!driver.findElements(By.xpath("//button[@id='addDataBtn']")).isEmpty()){
			applyExplicitWaitsUntilElementVisible(acp.addDataButton,30);
			applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();	
	    }
		applyExplicitWaitsUntilElementVisible(acp.textBox1,30);
		
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
			String absolutePath=new File("files/"+value).getAbsolutePath();
			textBox.sendKeys(absolutePath);
			Thread.sleep(1000);
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
		applyExplicitWaitsUntilElementVisible(acp.record, 30);
		WebElement record=driver.findElement(By.xpath("//a[normalize-space()='"+id+"']"));
		record.click();
		applyWait.waitForElementToBeClickable(acp.edit, 30).click();
		addNewRecordForFile(jsonFile);
		
	}

	public void matchToRecordForFileType(String jsonFile) throws Exception {
		
		LinkedHashMap<String, String> actualData=new LinkedHashMap<>();
		applyExplicitWaitsUntilElementVisible(acp.dataService, 30);
		WebElement record=driver.findElement(By.xpath("//a[@class='ng-star-inserted']"));
		record.click();
		int q=1;
		applyExplicitWaitsUntilElementVisible(acp.attributesOnViewPage, 30);
		for(WebElement attribute : acp.attributesOnViewPage) {
			WebElement t=driver.findElement(By.xpath("(//label[starts-with(@class,'label-width d-flex')])["+q+"]/parent::div/following-sibling::odp-view-separator/descendant::*[contains(@class,'text') or contains(@class,'value-width')]"));

			String a=attribute.getAttribute("for");
			String w=t.getText();
			
			if(!w.equals("N.A.")) {
				actualData.put(a, w);
		}
			q++;
		}

		
		matchData(jsonFile, actualData);
	}

public void addRecordForDate(String jsonFile) throws Exception {
		
		Thread.sleep(1000);
		if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
			acp.yes.click();
	    }
		Thread.sleep(2000);
		if(!driver.findElements(By.xpath("//button[@id='addDataBtn']")).isEmpty()){
			applyExplicitWaitsUntilElementVisible(acp.addDataButton,30);
			applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();	
	    }
		Thread.sleep(2000);
		applyExplicitWaitsUntilElementVisible(acp.textBox1,30);
		List<WebElement> textBoxes = acp.dateFields;
		JSONObject jsonObject = JsonUtils.fetchJSONObject(jsonFile);
		
		for (int j = 1; j <= textBoxes.size(); j++) {
			WebElement textBox = driver.findElement(By.xpath("(//*[@class='btn btn-link mr-2 p-0' or @id='_id'])[" + j + "]"));
			if (textBox.isEnabled()) {
				String id1="";
				String dateValue="";
				try {
				id1 = textBox.getAttribute("id");
				dateValue=jsonObject.get(id1).toString();
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
						applyExplicitWaitsUntilElementVisible(acp.cancelButton, 30);
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
					applyExplicitWaitsUntilElementVisible(acp.day,30);
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
	applyExplicitWaitsUntilElementVisible(acp.dataService, 30);
	WebElement record=driver.findElement(By.xpath("//a[@class='ng-star-inserted']"));
	record.click();
	int q=1;
	applyExplicitWaitsUntilElementVisible(acp.attributesOnViewPage, 30);
	for(WebElement attribute : acp.attributesOnViewPage) {
		WebElement t=driver.findElement(By.xpath("((//label[starts-with(@class,'label-width d-flex')])["+q+"]/parent::div/following-sibling::odp-view-separator/descendant::*[contains(@class,'value')])[last()]"));
		
		String a=attribute.getAttribute("for");
		String w=t.getText();
		if(!w.equals("N.A.")) {
			actualData.put(a, w);
	}
		q++;
	}

	matchData(jsonFile, actualData);
}

public void updateRecordForDate(String id, String jsonFile) throws Exception {
	Thread.sleep(1000);
	if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
		acp.yes.click();
    }
	applyWait.waitForElementToBeClickable(acp.idTab, 30).clear();
	applyWait.waitForElementToBeClickable(acp.idTab, 30).sendKeys(id);;
	applyExplicitWaitsUntilElementVisible(acp.record, 30);
	WebElement record=driver.findElement(By.xpath("//a[normalize-space()='"+id+"']"));
	record.click();
	applyWait.waitForElementToBeClickable(acp.edit, 30).click();
	addRecordForDate(jsonFile);
	
}

public void matchLocationData(String jsonFile) throws Exception {
	
	LinkedHashMap<String, String> actualData=new LinkedHashMap<>();
	applyExplicitWaitsUntilElementVisible(acp.dataService, 30);
	WebElement record=driver.findElement(By.xpath("//a[@class='ng-star-inserted']"));
	record.click();
	
	applyExplicitWaitsUntilElementVisible(acp.attributesOnViewPageForLocation, 30);
	for(int q=1;q<=acp.attributesOnViewPageForLocation.size();q++) {
		WebElement value = null;
		WebElement key = null;
		
		if(q==1) {
			 value=driver.findElement(By.xpath("(//odp-view-control/div/div/label[starts-with(@class,'label-width')])["+q+"]/parent::div/following-sibling::odp-view-separator/descendant::div/span"));
			key=driver.findElement(By.xpath("(//odp-view-control/div/div/label[starts-with(@class,'label-width')])["+q+"]"));
			
		}
		else {
			 try {
				value=driver.findElement(By.xpath("(//odp-view-control/div/div/label[starts-with(@class,'label-width')])["+q+"]/parent::div/following-sibling::odp-view-separator/descendant::div[5]"));
				 key=driver.findElement(By.xpath("(//odp-view-control/div/div/label[starts-with(@class,'label-width')])["+q+"]"));
			} catch (Exception e) {
				continue;
			}
		}
		String a = key.getAttribute("for");
			String w = value.getText();
		
		if(!w.equals("N.A.")) {
			actualData.put(a, w);
	}
	}
	
	matchData(jsonFile, actualData);
}

public void addRecordForGroup(String string) throws Exception {
	
	Thread.sleep(1000);
	if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
		acp.yes.click();
    }
	Thread.sleep(2000);
	if(!driver.findElements(By.xpath("//button[@id='addDataBtn']")).isEmpty()){
		applyExplicitWaitsUntilElementVisible(acp.addDataButton,30);
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();	
    }
	Thread.sleep(2000);
	applyExplicitWaitsUntilElementVisible(acp.textBox1,30);
	List<WebElement> textBoxes = acp.groupTextBoxes;
	JSONObject jsonObject = JsonUtils.fetchJSONObject(string);

	for (int j = 1; j <= textBoxes.size(); j++) {
		WebElement textBox = driver.findElement(By.xpath("(//*[contains(@class,'form-control') or @type='checkbox' or @type='file' or contains(@class,'btn btn-link mr-2 p-0') or contains(@class,'searchInput')])[" + j + "]"));
		if (textBox.isEnabled()) {
			String id1 = textBox.getAttribute("id");

			String value1 = JsonPath.read(string, "$."+id1+"").toString();
//--------------------------------------------------------String Text------------------------------------------------------------------------------------------------------------------				
			
			if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")||textBox.getAttribute("type").equals("select-one")) {
					if (value1!= null) {


					if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")) {
						
										if(textBox.getAttribute("class").contains("searchInput")) {
											
											String v1 = JsonPath.read(string, "$."+id1+".userInput").toString();
											textBox.clear();
											textBox.sendKeys(v1);
											Thread.sleep(1000);
											textBox.sendKeys(Keys.DOWN);
											textBox.sendKeys(Keys.ENTER);
											
										}
										
										else {
											try {
												textBox.getAttribute("role");
												String value = JsonPath.read(string, "$."+id1+"._id").toString();
												applyWait.waitForElementToBeClickable(textBox, 30).clear();;
												applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value);
												Thread.sleep(500);
												applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(Keys.ENTER);
												
											} catch (Exception e) {
												applyWait.waitForElementToBeClickable(textBox, 30).clear();;
												applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1);
											}
										}
					}

					if (textBox.getAttribute("type").equals("select-one")) {
						if( JsonPath.read(string, "$."+id1).equals("")) {
							textBox.click();
						}
						else {
							
								if( JsonPath.read(string, "$."+id1).equals("String")) {
									dropdown.selectByVisibleText(textBox, JsonPath.read(string, "$."+id1));
								}
								else {
									dropdown.selectByVisibleText(textBox, (JsonPath.read(string, "$."+id1)).toString());
							}
						}
					}
				}
			}
//---------------------------------------------------------Number-----------------------------------------------------------------------------------------------------------------------------------
		
			else if (textBox.getAttribute("type").equals("number") ||textBox.getAttribute("type").equals("select-one")) {
				 String inputText = textBox.getAttribute("value");
			        if( inputText != null ) {
			            for(int i=0; i<inputText.length();i++) { //1234
			                textBox.sendKeys(Keys.BACK_SPACE);
			            }
			        }
				applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1.toString());
			}
			
//-----------------------------------------------------------Email------------------------------------------------------------------------------------------------------------------------				
		
			else if (textBox.getAttribute("type").equals("email")) {
				applyWait.waitForElementToBeClickable(textBox, 30).clear();;
				applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1.toString());
			}
			
//-----------------------------------------------------------Boolean-------------------------------------------------------------------------------------------------------------------------			
		
			else if (textBox.getAttribute("type").equals("checkbox")) {
				
				

			      WebElement parent = textBox.findElement(By.xpath("./.."));
				Boolean status=	JsonPath.read(string, "$."+id1);
				
			      if(status.equals(true) && textBox.getAttribute("class").contains("ng-pristine"))
			      {
			         applyWait.waitForElementToBeClickable(parent, 30).click();
        		}
			      else if(status.equals(false) && textBox.getAttribute("class").contains("ng-dirty")){
			    	  applyWait.waitForElementToBeClickable(parent, 30).click();
			      }
			}
			
//--------------------------------------------------------File--------------------------------------------------------------------------------------------------------------------------------				
		
			else if (textBox.getAttribute("type").equals("file")) {
				Thread.sleep(500);
			String json1=JsonPath.read(string, "$."+id1+".metadata.filename").toString();
				if(json1!=null) {
					String absolutePath=new File("files/"+json1).getAbsolutePath();
					textBox.sendKeys(absolutePath);
					Thread.sleep(500);
				}
			}
//--------------------------------------------------------Location--------------------------------------------------------------------------------------------------------------------------------				
			else if (textBox.getAttribute("type").equals("email")) {
				applyWait.waitForElementToBeClickable(textBox, 30).clear();;
				applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1.toString());
			}
			
			else if (textBox.getAttribute("type").equals("submit")) {
				
				String dateValue=JsonPath.read(string, "$."+id1+".rawData").toString();

				
				String emptyArray[]= {"00","00","00Z"};
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
				
				WebElement selectDate=driver.findElement(By.id(id1));
				selectDate.click();
				dropdown.selectByValue(acp.yearDropDown, year);
				dropdown.selectByIndex(acp.monthDropDown, Integer.parseInt(month)-1);
				applyExplicitWaitsUntilElementVisible(acp.day,30);
				WebElement date1=driver.findElement(By.xpath("//span[contains(@class,'disabled')=false and @id='_day']["+date+"]"));
				date1.click();
				if(lp.isDateTime) {
					dropdown.selectByValue(acp.hourDropDown, hour);
					dropdown.selectByValue(acp.minuteDropDown, minute);
					Thread.sleep(500);
					dropdown.selectByValue(acp.secondDropDown, second);
				}
				
				applyWait.waitForElementToBeClickable(acp.doneButton, 30).click();
				Thread.sleep(500);
			}
		}
	}
	applyWait.waitForElementToBeClickable(acp.save, 30).click();
}

public void updateRecordForGroup(String id2, String jsonFile) throws Exception {
	Thread.sleep(2000);
	if(!driver.findElements(By.xpath("//button[normalize-space()='Yes']")).isEmpty()){
		acp.yes.click();
    }
	applyWait.waitForElementToBeClickable(acp.idTab, 30).clear();
	Thread.sleep(1000);
	applyWait.waitForElementToBeClickable(acp.idTab, 30).sendKeys(id2);
	WebElement record=driver.findElement(By.xpath("//a[normalize-space()='"+id2+"']"));
	record.click();
	Thread.sleep(1000);
	
	applyWait.waitForElementToBeClickable(acp.edit, 30).click();
	Thread.sleep(1000);
	addRecordForGroup(jsonFile);
	
}

public void matchRelationData(String jsonFile) throws Exception {
	LinkedHashMap<String, String> actualData=new LinkedHashMap<>();
	applyExplicitWaitsUntilElementVisible(acp.dataService, 30);
	WebElement record=driver.findElement(By.xpath("//a[@class='ng-star-inserted']"));
	record.click();
	Thread.sleep(1000);
	int q=1;
	applyExplicitWaitsUntilElementVisible(acp.attributesOnViewPage, 30);
	for(WebElement attribute : acp.attributesOnViewPage) {
		WebElement value=driver.findElement(By.xpath("((//label[starts-with(@class,'label-width d-flex')])/parent::div/following-sibling::odp-view-separator/descendant::div/child::*[last()])["+q+"]"));
		WebElement key=driver.findElement(By.xpath("((//label[starts-with(@class,'label-width d-flex')])/parent::div/following-sibling::odp-view-separator/descendant::div/child::*[last()])["+q+"]/ancestor::odp-view-separator/preceding-sibling::div//label"));
		
		String a=key.getAttribute("for");
		String w=value.getText();
		if(!w.equals("N.A.")) {
			actualData.put(a, w);
	}
		q++;
	}

	matchData(jsonFile, actualData);
}

public void matchToRecordToRichText(String jsonFile) throws Exception {
	LinkedHashMap<String, String> actualData=new LinkedHashMap<>();
	applyExplicitWaitsUntilElementVisible(acp.dataService, 30);
	WebElement record=driver.findElement(By.xpath("//a[@class='ng-star-inserted']"));
	record.click();
	Thread.sleep(1000);
	int q=1;
	applyExplicitWaitsUntilElementVisible(acp.attributesOnViewPageForRichText, 30);
	for(WebElement attribute : acp.attributesOnViewPageForRichText) {
		
		WebElement value=driver.findElement(By.xpath("(((//label[starts-with(@class,'label-width d-flex')])/parent::div/following-sibling::odp-view-separator/descendant::div/child::*[last()])/p | //span[@class='value-width ng-star-inserted'])["+q+"]"));
		WebElement key=driver.findElement(By.xpath("(((//label[starts-with(@class,'label-width d-flex')])/parent::div/following-sibling::odp-view-separator/descendant::div/child::*[last()])/p | //span[@class='value-width ng-star-inserted'])["+q+"]/ancestor::odp-view-separator/preceding-sibling::div/child::*[last()]"));
		
		String a=key.getAttribute("for");
		String w=value.getText();
		if(!w.equals("N.A.")) {
			actualData.put(a, w);
	}
		q++;
	}

	matchData(jsonFile, actualData);
	
}

public void matchData(String jsonFile,LinkedHashMap actualData ) throws Exception {

LinkedHashMap<String, String> expectedData =(LinkedHashMap<String, String>) JsonUtils.getMapFromJSON(jsonFile);
if(actualData.equals(expectedData)) {
}
else {
	
  MapDifference<String, String> diff = Maps.difference(actualData, expectedData);
    Map<String, ValueDifference<String>> entriesDiffering = diff.entriesDiffering();
    System.out.println(entriesDiffering);
    System.out.println(diff);
    Assert.assertTrue(actualData.equals(expectedData));
}
}

public void expectErrorOnSave(String errorMessage) throws InterruptedException {
	 Thread.sleep(1000);
	if(errorMessage.contains("ID"))
	{
		By error = By.xpath("//div[@role='alertdialog']");
		applyWaitForDynamicWebElement(error, 30);
		String expectedError = driver.findElement(error).getText();
		Assert.assertEquals(errorMessage, expectedError);
	}
	if(errorMessage.contains("Unique"))
	{
		By error = By.xpath("//div[@role='alertdialog']");
		applyWaitForDynamicWebElement(error, 30);
		String expectedError = driver.findElement(error).getText();
		Assert.assertTrue( expectedError.contains("Unique check validation failed"));  	
	}
 }
	


public void addRecordForstateModel(String data) throws MalformedURLException, InterruptedException {
		applyExplicitWaitsUntilElementVisible(acp.addDataButton,30);
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		applyWait.applyExplicitWaitsUntilElementVisible(acp.groupTextBoxes, 30);
		JSONObject jsonObject = JsonUtils.fetchJSONObject(data);
		for (int j = 1; j <= acp.groupTextBoxes.size(); j++) {
			WebElement textBox = driver.findElement(By.xpath("(//*[contains(@class,'form-control') or @type='checkbox' or @type='file' or contains(@class,'btn btn-link mr-2 p-0') or contains(@class,'searchInput')])["+j+"]"));
			if (textBox.isEnabled()) {
				String id1 = textBox.getAttribute("id");
			   String jsonValue = JsonPath.read(data, "$."+id1+"").toString();
			   

				if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")) {
					applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(jsonValue);	
				}
				else if (textBox.getAttribute("type").equals("number")) {
					applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(jsonValue);
				}
				else if(textBox.getAttribute("type").equals("submit")){
					applyWait.waitForElementToBeClickable(textBox, 30).click();
					String[] part = jsonValue.split("T");
				     String year =  part[0].split("-")[0];
				     String month = part[0].split("-")[1];
				     int i=Integer.parseInt(month)-1;
                     String m = i+"";			    
				     String date = part[0].split("-")[2];
				     String time = part[1]; 
				     String hour = time.split(":")[0];
				     String min = time.split(":")[1];
				     String sec = time.split(":")[2].replace("Z", "");
				     dropdown.selectByValue(acp.yearDropDown,year);
				     Thread.sleep(1000);
				     dropdown.selectByIndex(acp.monthDropDown, i);
				     
				     WebElement date1 = driver.findElement(By.xpath("//span[@id='_day' and not(contains(@class,'disabled'))]//small[normalize-space()='"+date+"']"));
                     date1.click();
                     applyWait.waitForElementToBeClickable(acp.doneButton, 30).click();
                     
				}
			}
		}
		applyWait.waitForElementToBeClickable(acp.save, 30).click();
}

			public void userNextStateAvailable(String nextState) throws InterruptedException {
				applyWait.waitForElementToBeClickable(acp.record, 30).click();
				applyWait.waitForElementToBeClickable(acp.edit, 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(acp.saveDropDown, 30).click();
				String[] st = nextState.split(",");
				List<String> al =  Arrays.asList(st);
				System.out.println("ArrayList is : " +al);
				List<WebElement> stateList = driver.findElements(By.xpath("//button[@class='dropdown-item state-model-option ng-star-inserted']"));
				availableStateList = new ArrayList<>();
				for (int i = 0; i < stateList.size(); i++) {
					String value = stateList.get(i).getText();
					availableStateList.add(value);
				}
				System.out.println("Expected List :  " + availableStateList);
				Assert.assertEquals(availableStateList,  al);
								
				}

			public void verifyInvalidState(String invalidState) {
				String[] invalid = invalidState.split(",");
				List<String> ls = Arrays.asList(invalid);
				ArrayList<String> demo = new ArrayList<>(availableStateList);
				demo.retainAll(ls);
				if(!demo.isEmpty())
				{
					Assert.assertTrue(false);
				}
			}

			public void updateRecordForStateModel(String updateState) throws MalformedURLException, InterruptedException {
				Thread.sleep(5000);
				driver.findElement(By.xpath("//button[contains(@class,'dropdown-item state-model-option')][normalize-space()='"+updateState+"']")).click();
			}

			public void verifyValidRecord(String count) {
				String actual = applyWait.waitforElementToBeDisplayed(acp.validRecords, 30).getText();
				Assert.assertEquals(count, actual);
			}

			public void selectConflictRecords(String conflict_Record) throws InterruptedException {
				driver.findElement(By.xpath("//span[starts-with(text(),'Conflicts')]/following-sibling::span/u")).click();
				Thread.sleep(2000);
				WebElement conflictRecord = driver.findElement(By.xpath("//span[normalize-space()='"+conflict_Record+"']/ancestor::div[@col-id='sNo']/preceding-sibling::div[@col-id='_resolve']//div[contains(@class,'custom-control')]"));
				Thread.sleep(2000);
				applyWait.waitForElementToBeClickable(conflictRecord, 30).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//span[normalize-space()='Done']")).click();
				applyWait.waitForElementToBeClickable(acp.next, 30).click();
				Thread.sleep(1000);
				applyWait.waitForElementToBeClickable(acp.here, 30).click();
				
			    }
			}
