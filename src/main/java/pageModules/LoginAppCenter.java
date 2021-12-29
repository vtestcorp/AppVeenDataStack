package pageModules;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.aventstack.extentreports.ExtentTest;
import base.BaseClass;
import config.Constants;
import helperMethods.DropDown;
import helperMethods.JavascriptClick;
import helperMethods.JsonUtils;
import helperMethods.Property;
import helperMethods.ScrollTypes;
import helperMethods.SwitchWindow;
import helperMethods.WaitTypes;
import pageObjects.Object_AppCenterPage;

public class LoginAppCenter extends BaseClass {

	private WebDriver driver;
	private WaitTypes applyWait;
	private DropDown dropdown;
	private ScrollTypes scroll;
	public static String data_Service;
	private Object_AppCenterPage acp;
	private LoginPage lp;

	public LoginAppCenter(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.applyWait = new WaitTypes(driver);
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
		acp = new Object_AppCenterPage();
		lp=new LoginPage(driver);
	}

	public void loginToAppCenterPage() {
		
		SwitchWindow.openNewTab(driver);
		driver.get(app_center_URL);
		driver.manage().window().maximize();
		
	}

	public void enterUserNameAndPassword(String username, String password) throws InterruptedException {

		applyWait.waitForElementToBeClickable(acp.username, 30).sendKeys(username);
		applyWait.waitForElementToBeClickable(acp.nextButton, 30).click();
		applyWait.waitforElementToBeDisplayed(acp.password, 30).sendKeys(password);
		applyWait.waitforElementToBeDisplayed(acp.loginButton, 30).click();

	}

	public void verifyUserLoginSuccesfullyToAppCenter() throws InterruptedException {
		
		Boolean status = applyWait.waitforElementToBeDisplayed(acp.dataStackLogo, 80).isDisplayed();
	}

	public void dataService(String dataService) throws Exception {

		data_Service=dataService;
		Actions action=new Actions(driver);
		By ds = null;
		try {
			ds=By.xpath("//div[normalize-space()='"+dataService+"' and contains(@class,'text-truncate')]");
			applyWaitForDynamicWebElement(ds, 10);
			WebElement data = driver.findElement(ds);
			action.moveToElement(data).perform();
			data.click();
		} catch (Exception e) {
			Thread.sleep(5000);
			driver.navigate().refresh();
			Thread.sleep(2000);
					try {
						WebElement data = driver.findElement(ds);
						action.moveToElement(data).perform();
						data.click();
					} catch (Exception e1) {
						Thread.sleep(10000);
						driver.navigate().refresh();
						Thread.sleep(2000);
						
						try {
						WebElement data = driver.findElement(ds);
						action.moveToElement(data).perform();
						data.click();
						}
						catch(Exception e2) {
							
							Thread.sleep(15000);
							driver.navigate().refresh();
							Thread.sleep(2000);
							
							try {
							WebElement data = driver.findElement(ds);
							action.moveToElement(data).perform();
							data.click();
							}
							catch(Exception e3) {
								
								Thread.sleep(15000);
								driver.navigate().refresh();
								Thread.sleep(2000);
								WebElement data = driver.findElement(ds);
								action.moveToElement(data).perform();
								data.click();
								
						}
					}
				}
			}
		}
	
	public void uploadDatafile(String file) throws MalformedURLException, InterruptedException {
		String filePath=Constants.file_Folder + file+ Constants.excelFile_Suffix;
		applyExplicitWaitsUntilElementVisible(acp.importfile,10);
		Actions action = new Actions(driver);
		action.moveToElement(acp.importfile);
		applyWait.waitForElementToBeClickable(acp.importfile, 30).click();
		Thread.sleep(2000);
		applyWait.waitForElementToBeClickable(acp.uploadFile, 30).sendKeys(filePath);

	}

	public void userEnterData() throws Exception {
		Thread.sleep(2000);
		try {
			applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		}
		catch(Exception e) {
			Thread.sleep(1000);
			JavascriptClick.click(acp.addDataButton);
		}
		applyExplicitWaitsUntilElementVisible(acp.textBox1,10);
		List<WebElement> textBoxes = acp.textBoxes;
		String filePath=Constants.testData_Folder + data_Service + Constants.testData_Suffix;
		JSONObject jsonObject = JsonUtils.getJSONObject(filePath);
		
		List<WebElement> stepNames = driver
				.findElements(By.xpath("//div[@class='step-name high-zIndex text-truncate']"));

		if (stepNames.size() > 0) {
			for (WebElement stepName : stepNames) {
				stepName.click();

				for (int j = 1; j <= textBoxes.size(); j++) {

					WebElement textBox = driver
							.findElement(By.xpath("(//*[contains(@class,'form-control')])[" + j + "]"));

					String id1 = textBox.getAttribute("id");
					if (textBox.getAttribute("type").equals("text") || textBox.getAttribute("type") == null) {
						if (id1.contains(".")) {
							String[] attributes = id1.trim().split("[^a-zA-Z0-9]+");

							JSONObject obj = (JSONObject) jsonObject.get(attributes[0]);
							applyWait.waitForElementToBeClickable(textBox, 30).sendKeys((String) obj.get(attributes[1]));

						} else {
							applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(((String) jsonObject.get(id1)).toString());
							
						}
					}
					if (textBox.getAttribute("type").equals("number")) {
						applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(((Long) jsonObject.get(id1)).toString());
						
					}
				}
			}
		}

		else {
			for (int j = 1; j <= textBoxes.size(); j++) {
				WebElement textBox = driver.findElement(By.xpath("(//*[contains(@class,'form-control')])[" + j + "]"));
				if (textBox.isEnabled()) {
					String id1 = textBox.getAttribute("id");
					String value1=JsonUtils.getJsonValue(filePath,id1);
				
					
					if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")||textBox.getAttribute("type").equals("select-one")) {
							if (jsonObject.get(id1) != null) {


							if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")) {
								applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1);
							}

							if (textBox.getAttribute("type").equals("select-one")) {
								if( JsonUtils.getJsonValue(filePath,id1).equals("")) {
									textBox.click();
								}
								else {
									
										if(jsonObject.get(id1).getClass().toString().contains("Long")) {
											dropdown.selectByVisibleText(textBox, jsonObject.get(id1).toString());
										}
										else {
											dropdown.selectByVisibleText(textBox, (JsonUtils.getJsonValue(filePath,id1)).toString());
										}
								}
							}
						}
					}

					else if (textBox.getAttribute("type").equals("number") ||textBox.getAttribute("type").equals("select-one")) {
						if (jsonObject.get(id1) != null) {
							if(jsonObject.get(id1).getClass().toString().contains("Double")) {
								
		
									if (textBox.getAttribute("type").equals("number")) {
										Double value = (Double) jsonObject.get(id1);
										applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value.toString());
									}
									
									if (textBox.getAttribute("type").equals("select-one")) {

										dropdown.selectByVisibleText(textBox, ((Double) jsonObject.get(id1)).toString());
									}
								}
						
						else if(jsonObject.get(id1).getClass().toString().contains("Long")) {
							if (jsonObject.get(id1) != null) {

								if (textBox.getAttribute("type").equals("number")) {
									Long value = (Long) jsonObject.get(id1);
									applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value.toString());
									
								}
								
								if (textBox.getAttribute("type").equals("select-one")) {

									dropdown.selectByVisibleText(textBox, ((Long) jsonObject.get(id1)).toString());

								}
							}
							}
							
						else if(jsonObject.get(id1).getClass().toString().contains("Integer")) {
							if ( jsonObject.get(id1) != null) {

								if (textBox.getAttribute("type").equals("number")) {
									Integer value = (Integer) jsonObject.get(id1);
									applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value.toString());
									
								}
								
								if (textBox.getAttribute("type").equals("select-one")) {

									dropdown.selectByVisibleText(textBox, ((Integer) jsonObject.get(id1)).toString());
								}
							}
							}
							
						else {
							dropdown.selectByVisibleText(textBox, (JsonUtils.getJsonValue(filePath,id1).toString()));
						}
					}
					}
					
					else if (textBox.getAttribute("type").equals("email")) {
						applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1.toString());
					}
				}
			}
		}
		
		applyExplicitWaitsUntilElementClickable(acp.save,30);
		Thread.sleep(1000);
		JavascriptClick.click(acp.save);
	}


	public void userEnterDataInLocationField() throws InterruptedException, Exception {
		Thread.sleep(1500);
		try {
			applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		}
		catch(Exception e) {
			Thread.sleep(1000);
			JavascriptClick.click(acp.addDataButton);
		}
		Thread.sleep(5000);
		applyExplicitWaitsUntilElementVisible(acp.textBoxesLocation, 30);
		List<WebElement> textBoxes = acp.textBoxesLocation;
		
		String filePath=Constants.testData_Folder + data_Service + Constants.testData_Suffix;
		JSONObject jsonObject = JsonUtils.getJSONObject(filePath);
		
		
		for (int j = 1; j <= textBoxes.size(); j++) {
			Thread.sleep(1000);
			WebElement textBox = driver.findElement(By.xpath("(//input[@class='searchInput pac-target-input' or @id='_id'])["+j+"]"));
			if (textBox.isEnabled()) {
				String id1 = textBox.getAttribute("id");
				String v1 =  jsonObject.get(id1).toString();
				String v2= null;
				if(!id1.equals("_id"))
				{
					JSONObject value1= JsonUtils.fetchJSONObject(v1);
					 v2 = (String) value1.get("userInput");
				}
				
				
				if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")) {
					if(id1.equals("_id"))
					{
						applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(v1);
					}
						else {
     							textBox.sendKeys(v2);
								Thread.sleep(500);
								textBox.sendKeys(Keys.DOWN);
								Thread.sleep(500);
								textBox.sendKeys(Keys.ENTER);
        			}
				}
			}
		}
				applyExplicitWaitsUntilElementClickable(acp.save,30);
				Thread.sleep(2000);
				JavascriptClick.click(acp.save);
	}
				
	public void userEnterDataInUserField() throws InterruptedException, MalformedURLException {
		Thread.sleep(2000);
		try {
			applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		}
		catch(Exception e) {
			Thread.sleep(1000);
			JavascriptClick.click(acp.addDataButton);
		}
		Thread.sleep(2000);
		List<WebElement> textBoxes = acp.textBoxes;
		
		String filePath=Constants.testData_Folder + data_Service + Constants.testData_Suffix;
		JSONObject jsonObject = JsonUtils.getJSONObject(filePath);
	
		for (int j = 1; j <= textBoxes.size(); j++) {
			String val =null;
		WebElement textBox = driver.findElement(By.xpath("(//*[contains(@class,'form-control')])["+j+"]"));
			if (textBox.isEnabled()) {
				String id1 = textBox.getAttribute("id");
				if(id1.equals("_id"))
				{
					val = (String) jsonObject.get(id1);
				}
				else {
                        try {
							JSONObject json  = (JSONObject) jsonObject.get(id1);
							val = (String) json.get("_id");
						} catch (NullPointerException e) {
							continue;
						}
				}
				
				if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")||textBox.getAttribute("type").equals("select-one")) {

					   	if (val != null) {

								if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")) {
										applyWait.waitForElementToBeClickable(textBox,30).clear();	
										applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(val);
		                            	Thread.sleep(200);
		                            	applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(Keys.DOWN);
		                            	Thread.sleep(500);
		                            	applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(Keys.ENTER);
								}
		   					}
		            	}
	            	}
		     }
		
		applyExplicitWaitsUntilElementClickable(acp.save,30);
		Thread.sleep(1000);
		JavascriptClick.click(acp.save);
	}
					
			public void userEnterDataforBoolean() throws InterruptedException, MalformedURLException {
				Thread.sleep(1500);
				try {
					applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
				}
				catch(Exception e) {
					Thread.sleep(1000);
					JavascriptClick.click(acp.addDataButton);
				}
				Thread.sleep(1000);
				List<WebElement> buttons = acp.buttons;
				String filePath=Constants.testData_Folder + data_Service + Constants.testData_Suffix;
				JSONObject jsonObject = JsonUtils.getJSONObject(filePath);
				for (int j = 1; j <= buttons.size(); j++) {
					WebElement button = driver.findElement(By.xpath("(//input[@type='checkbox' or @id='_id' ])["+j+"]"));
					String id1 = button.getAttribute("id");
					if (button.getAttribute("type").equals("text"))
					   	 {
						     String value = (String) jsonObject.get(id1);
						     applyWait.waitForElementToBeClickable(button, 30).sendKeys(value);
					   	 }
					else if (button.getAttribute("type").equals("checkbox")) {
						      WebElement parent = button.findElement(By.xpath("./.."));
						      try {
							Boolean status=	(Boolean) jsonObject.get(id1);
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
//			     	
				applyExplicitWaitsUntilElementClickable(acp.save,30);
				Thread.sleep(1000);
				JavascriptClick.click(acp.save);
				}
			

			public void userEnterDataForRichText() throws InterruptedException, MalformedURLException {
				Thread.sleep(2000);
				try {
					applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
				}
				catch(Exception e) {
					Thread.sleep(1000);
					JavascriptClick.click(acp.addDataButton);
				}
				Thread.sleep(5000);
				List<WebElement> textBoxes = acp.richtextBoxes;
				String filePath=Constants.testData_Folder + data_Service + Constants.testData_Suffix;
				JSONObject jsonObject = JsonUtils.getJSONObject(filePath);
				for (int j = 1; j <= textBoxes.size(); j++) {
					WebElement textBox = driver.findElement(By.xpath("(//*[starts-with(@class,'tox-edit-area__iframe') or   @id='_id'])["+j+"]"));
					if (textBox.isEnabled()) {
						String id1 = " ";
						if(j==1)
						{
							 id1 = textBox.getAttribute("id");
							 String value = (String) jsonObject.get(id1);
				             applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value);
							 
						}else {

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
				
				Thread.sleep(2000);
				applyWait.waitForElementToBeClickable(acp.save, 30).click();
			
			}
	
	public void workflow() {
		applyWait.waitForElementToBeClickable(acp.username, 30).sendKeys(Property.getProperty("reviewerEmail"));
		applyWait.waitForElementToBeClickable(acp.nextButton, 30).click();
		applyWait.waitforElementToBeDisplayed(acp.password, 30).sendKeys(Property.getProperty("password"));
		applyWait.waitforElementToBeDisplayed(acp.login, 30).click();
		applyWait.waitforElementToBeDisplayed(acp.workflowTab, 30).click();
		for (WebElement workflow : acp.workflowsId) {
			workflow.click();
			applyWait.waitforElementToBeDisplayed(acp.respond, 30).click();
			applyWait.waitforElementToBeDisplayed(acp.approve, 30).click();
			applyWait.waitforElementToBeDisplayed(acp.enterApproveComment, 30).sendKeys(Property.getProperty("approveMessage"));
			applyWait.waitforElementToBeDisplayed(acp.approve, 30).click();

		}

	}

	public void verifyTotalCountOfDocuments(Integer documentCount) throws Exception {
		applyExplicitWaitsUntilElementVisible(acp.documentList,30);
		String documentList = acp.documentList.getText();
		Integer documentListCount = Integer.parseInt(documentList.trim().split("of")[1].trim());
	}

	public void removeRecords(Integer int1) throws Exception {
		Thread.sleep(1000);
		for (int i = 0; i < int1; i++) {
			WebElement checkbox = acp.checkbox;
			applyExplicitWaitsUntilElementVisible(acp.checkbox,30);
			checkbox.click();
			acp.delete.click();
			acp.yes.click();
		}
	}

	public void addDataForFile() throws Exception {
		Thread.sleep(1500);
		try {
			applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		}
		catch(Exception e) {
			Thread.sleep(1000);
			JavascriptClick.click(acp.addDataButton);
		}
		applyExplicitWaitsUntilElementVisible(acp.textBox1,30);
		List<WebElement> textBoxes = driver.findElements(By.xpath("//input[@class='invisible position-absolute' or @id='_id']"));
		String filePath=Constants.testData_Folder + data_Service + Constants.testData_Suffix;

		JSONObject jsonObject = JsonUtils.getJSONObject(filePath);
		
		for(int  i=1;i<=textBoxes.size();i++) {
		
			WebElement textBox = driver.findElement(By.xpath("(//input[@class='invisible position-absolute' or @id='_id'])["+i+"]"));
				String id1 = textBox.getAttribute("id");
		if(id1.equals("_id")) {
			String value=(String) jsonObject.get(id1);
			textBox.sendKeys(value);
		}
		else {
		JSONObject json=(JSONObject) jsonObject.get(id1);
		if(json!=null) {
			JSONObject value2=(JSONObject) json.get("metadata");
			String value=(String) value2.get("filename");
			String absolutePath=new File("files/"+value).getAbsolutePath();
//			applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(absolutePath);;
			textBox.sendKeys(absolutePath);
			Thread.sleep(1500);
		}}
		}
		
		
		applyExplicitWaitsUntilElementClickable(acp.save,30);
		Thread.sleep(1000);
		JavascriptClick.click(acp.save);
	}
	
	public void userEnterDataforCollection() throws Exception {
		Thread.sleep(1500);
		try {
			applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		}
		catch(Exception e) {
			Thread.sleep(1000);
			JavascriptClick.click(acp.addDataButton);
		}
		JSONObject jsonObject = JsonUtils.getJSONObject(Constants.testData_Folder + data_Service + Constants.testData_Suffix);
		applyExplicitWaitsUntilElementVisible(acp.addNewButtons, 30);
		List<WebElement> addNew = acp.addNewButtons;
		System.out.println(addNew.size());
			for (int j = 1; j <= addNew.size(); j++) {
				WebElement textBox = driver.findElement(By.xpath("(//div[@class='ng-star-inserted']//*[contains(@class,'add-new')])[" +j+ "]"));
				if (textBox.isEnabled()) {
					
					String id1 = textBox.getAttribute("id");
					JSONArray collectionArray=(JSONArray) jsonObject.get(id1);
					try {
						collectionArray.size();
					}
					catch(NullPointerException e) {
						continue;
					}
					for(int i=0;i<collectionArray.size();i++)
					{
						textBox.click();
    					Thread.sleep(500);
						WebElement textBox1 = driver.findElement(By.xpath("//input[contains(@id,'"+id1+"."+i+"')]"));
						String idValue=textBox1.getAttribute("id");   
						String qw=idValue.split("[.]")[0]+"["+idValue.split("[.]")[1]+"]";
						String value = JsonUtils.getJsonValue(Constants.testData_Folder + data_Service + Constants.testData_Suffix,qw);
						System.out.println(value);
						
						if(textBox1.getAttribute("type").equals("text") || textBox1.getAttribute("type").equals("number")) {
							if(textBox1.getAttribute("class").contains("searchInput")) {
								value = JsonUtils.getJsonValue(Constants.testData_Folder + data_Service + Constants.testData_Suffix,qw+".userInput");
								applyWait.waitForElementToBeClickable(textBox1, 30).sendKeys(value);
								applyWait.waitForElementToBeClickable(textBox1, 30).sendKeys(Keys.TAB);
								applyWait.waitForElementToBeClickable(textBox1, 30).sendKeys(Keys.ENTER);
							}
							else {
								applyWait.waitForElementToBeClickable(textBox1, 30).sendKeys(value);
							}
						}
						else if(textBox1.getAttribute("type").equals("checkbox")) {
							if(value.equals("true")) {
								WebElement parentElement =textBox1.findElement(By.xpath("../.."));
								applyWait.waitForElementToBeClickable(parentElement, 30).click();
							}

						}

					}
			}

		}
			
			applyExplicitWaitsUntilElementClickable(acp.save,30);
			Thread.sleep(1000);
			JavascriptClick.click(acp.save);
	}

	

	public void addDataForGroups() throws MalformedURLException, Exception {
		Thread.sleep(1500);
		try {
			applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		}
		catch(Exception e) {
			Thread.sleep(1000);
			JavascriptClick.click(acp.addDataButton);
		}
		applyExplicitWaitsUntilElementVisible(acp.textBox1,30);
		List<WebElement> textBoxes = acp.groupTextBoxes;
		String filePath=Constants.testData_Folder + data_Service + Constants.testData_Suffix;
		JSONObject jsonObject = JsonUtils.getJSONObject(filePath);

		for (int j = 1; j <= textBoxes.size(); j++) {
			WebElement textBox = driver.findElement(By.xpath("(//*[contains(@class,'form-control') or @type='checkbox' or @type='file' or contains(@class,'btn btn-link mr-2 p-0') or contains(@class,'searchInput')])[" + j + "]"));
			if (textBox.isEnabled()) {
				String id1 = textBox.getAttribute("id");

				String value1=JsonUtils.getJsonValue(filePath,id1);
				
//------------------------------------------------------------String Text------------------------------------------------------------------------				
				
				if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")||textBox.getAttribute("type").equals("select-one")) {
						if (value1!= null) {


						if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")) {
							
											if(textBox.getAttribute("class").contains("searchInput")) {
												
												String v1 =  JsonUtils.getJsonValue(filePath, id1+".userInput");
												textBox.sendKeys(v1);
												Thread.sleep(1000);
												textBox.sendKeys(Keys.DOWN);
												textBox.sendKeys(Keys.ENTER);
												
											}
											
											else {
												try {
													textBox.getAttribute("role");
													String value=JsonUtils.getJsonValue(filePath, id1+"._id");
													applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value);
													Thread.sleep(500);
													applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(Keys.ENTER);
													
												} catch (Exception e) {
													applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1);
												}
											}
						}

						if (textBox.getAttribute("type").equals("select-one")) {
							if( JsonUtils.getJsonValue(filePath,id1).equals("")) {
								textBox.click();
							}
							else {
								
									if( JsonUtils.getJsonValue(filePath,id1).equals("String")) {
										dropdown.selectByVisibleText(textBox, JsonUtils.getJsonValue(filePath,id1));
									}
									else {
										dropdown.selectByVisibleText(textBox, (JsonUtils.getJsonValue(filePath,id1)).toString());
								}
							}
						}
					}
				}
//------------------------------------------------------------Number-----------------------------------------------------------------------------
			
				else if (textBox.getAttribute("type").equals("number") ||textBox.getAttribute("type").equals("select-one")) {
					applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1.toString());
				}
				
//------------------------------------------------------------Email------------------------------------------------------------------------------				
			
				else if (textBox.getAttribute("type").equals("email")) {
					applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1.toString());
				}
				
//------------------------------------------------------------Boolean----------------------------------------------------------------------------
			
				else if (textBox.getAttribute("type").equals("checkbox")) {

				      WebElement parent = textBox.findElement(By.xpath("./.."));
				      String v1 =  JsonUtils.getJsonValue(filePath, id1);
					Boolean status=	Boolean.parseBoolean(v1);
					
				      if(status.equals(true) && textBox.getAttribute("class").contains("ng-pristine"))
				      {
				         applyWait.waitForElementToBeClickable(parent, 30).click();
            		}
				      else if(status.equals(false) && textBox.getAttribute("class").contains("ng-dirty")){
				    	  applyWait.waitForElementToBeClickable(parent, 30).click();
				      }
				}
				
//------------------------------------------------------------File-------------------------------------------------------------------------------
			
				else if (textBox.getAttribute("type").equals("file")) {
					Thread.sleep(500);
				String json1=JsonUtils.getJsonValue(filePath, id1+".metadata.filename");
					if(json1!=null) {
						String absolutePath=new File("files/"+json1).getAbsolutePath();
						textBox.sendKeys(absolutePath);
						Thread.sleep(500);
					}
				}
				
//------------------------------------------------------------Location----------------------------------------------------------------------------				
			
				else if (textBox.getAttribute("type").equals("email")) {
					applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1.toString());
				}
				
				else if (textBox.getAttribute("type").equals("submit")) {
					
					String dateValue=JsonUtils.getJsonValue(filePath, id1+".rawData");
					
					String emptyArray[]= {"00","00","00Z"};
					String fullDate[]=dateValue.split("T")[0].split("-");
					String fullTime[]=dateValue.split("T")[1].split(":");
					String date=fullDate[2];
					String month=fullDate[1];//    30
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
	
		applyExplicitWaitsUntilElementClickable(acp.save,30);
		Thread.sleep(1000);
		JavascriptClick.click(acp.save);
	}

	public void addDataForDate() throws Exception {
		Thread.sleep(1500);
		try {
			applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		}
		catch(Exception e) {
			Thread.sleep(1000);
			JavascriptClick.click(acp.addDataButton);
		}
		applyExplicitWaitsUntilElementVisible(acp.textBox1,30);
		List<WebElement> textBoxes = acp.dateFields;
		String filePath=Constants.testData_Folder + data_Service + Constants.testData_Suffix;

		JSONObject jsonObject = JsonUtils.getJSONObject(filePath);
		
		for (int j = 1; j <= textBoxes.size(); j++) {
			WebElement textBox = driver.findElement(By.xpath("(//*[@class='btn btn-link mr-2 p-0' or @id='_id'])[" + j + "]"));
			if (textBox.isEnabled()) {
				String id1 = textBox.getAttribute("id");

				if(id1.equals("_id")) {
					String value1=(String) jsonObject.get(id1);
					applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1.toString());
				}
				else {
					String dateValue;
					try {
					JSONObject json=(JSONObject) jsonObject.get(id1);
					dateValue=json.get("rawData").toString();
					}
					catch(Exception e) {
						continue;
					}
					
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
					
//					applyWait.waitForElementToBeClickable(acp.doneButton, 30).click();
					JavascriptClick.click(acp.doneButton);
					Thread.sleep(500);
					
				}
			}
			}
		applyExplicitWaitsUntilElementClickable(acp.save,30);
		Thread.sleep(1000);
		JavascriptClick.click(acp.save);
	}

	public void addDataForRelation() throws Exception {
		Thread.sleep(1500);
		try {
			applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		}
		catch(Exception e) {
			Thread.sleep(1000);
			JavascriptClick.click(acp.addDataButton);
		}
		applyExplicitWaitsUntilElementVisible(acp.textBox1,30);
		List<WebElement> textBoxes = acp.textBoxes;
		String filePath=Constants.testData_Folder + data_Service + Constants.testData_Suffix;
		JSONObject jsonObject = JsonUtils.getJSONObject(filePath);
		
		for (int j = 1; j <= textBoxes.size(); j++) {
			WebElement textBox = driver.findElement(By.xpath("(//*[contains(@class,'form-control')])[" + j + "]"));
			if (textBox.isEnabled()) {
				String id1 = textBox.getAttribute("id");
				String value1="";
				JSONObject json = null;
				if(id1.equals("_id")) {
					applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(jsonObject.get(id1).toString());
					continue;
				}
				else {
					 json=(JSONObject) jsonObject.get(id1);
					value1=json.get("_id").toString();
				}
				
				if (textBox.getAttribute("type").equals("select-one")) {
						if (json.get("_id") != null) {


						if (textBox.getAttribute("type").equals("select-one")) {
							if( value1.equals("")) {
								textBox.click();
							}
							else {
									if(json.get("_id").getClass().toString().contains("Long")) {
										dropdown.selectByVisibleText(textBox, json.get(id1).toString());
									}
									else {
										dropdown.selectByVisibleText(textBox, value1);
									}
							}
						}
					}
				}

				else if (textBox.getAttribute("type").equals("number") ||textBox.getAttribute("type").equals("select-one")) {
						if(json.get("_id").getClass().toString().contains("Double")) {
							if (json.get("_id") != null) {
	
								if (textBox.getAttribute("type").equals("number")) {
									Double value = (Double) json.get("_id");
									applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value.toString());
									
								}
								
								if (textBox.getAttribute("type").equals("select-one")) {

									dropdown.selectByVisibleText(textBox, ((Double) json.get("_id")).toString());

								}
							}
						}
					
					else if(json.get(id1).getClass().toString().contains("Long")) {
						if (json.get(id1) != null) {

							if (textBox.getAttribute("type").equals("number")) {
								Long value = (Long) json.get("_id");
								applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1.toString());
								
							}
						}
						}
						
					else if(jsonObject.get(id1).getClass().toString().contains("Integer")) {
						if ( jsonObject.get(id1) != null) {

							if (textBox.getAttribute("type").equals("number")) {
								Integer value = (Integer) json.get(id1);
								applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1.toString());
								
							}
							
							if (textBox.getAttribute("type").equals("select-one")) {

								dropdown.selectByVisibleText(textBox, ((Integer) json.get("_id")).toString());

							}
						}
						}
						
					else {
						dropdown.selectByVisibleText(textBox, value1);
					}
				}
				
				else if (textBox.getAttribute("type").equals("email")) {
					applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1.toString());
				}
			}
		}
		
		applyExplicitWaitsUntilElementClickable(acp.save,30);
		Thread.sleep(1000);
		JavascriptClick.click(acp.save);
	}

	public void userEnterDataForStateModel() throws MalformedURLException, InterruptedException {
		try {
			applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		}
		catch(Exception e) {
			Thread.sleep(1000);
			JavascriptClick.click(acp.addDataButton);
		}
		applyWait.applyExplicitWaitsUntilElementVisible(acp.groupTextBoxes, 30);
		List<WebElement> textBoxes = acp.groupTextBoxes;
		String filePath=Constants.testData_Folder + data_Service + Constants.testData_Suffix;
		JSONObject jsonObject = JsonUtils.getJSONObject(filePath);

		for (int j = 1; j <= textBoxes.size(); j++) {
			WebElement textBox = driver.findElement(By.xpath("(//*[contains(@class,'form-control') or @type='checkbox' or @type='file' or contains(@class,'btn btn-link mr-2 p-0') or contains(@class,'searchInput')])[" + j + "]"));
			if (textBox.isEnabled()) {
				String id1 = textBox.getAttribute("id");
				String value1=JsonUtils.getJsonValue(filePath,id1);
				String type = textBox.getAttribute("type");
				
				if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")) {
					applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1);	
				}
				if (textBox.getAttribute("type").equals("number")) {
					applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(((Long) jsonObject.get(id1)).toString());
					
				}
				if(textBox.getAttribute("type").equals("submit")){
					applyWait.waitForElementToBeClickable(textBox, 30).click();
					JSONObject js = (JSONObject) jsonObject.get(id1);
					 String value = (String) js.get("rawData");
					 String[] part = value.split("T");
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
		
		applyExplicitWaitsUntilElementClickable(acp.save,30);
		Thread.sleep(1000);
		JavascriptClick.click(acp.save);
	}

	public void verifyState(String currentState) throws MalformedURLException {
		applyWait.waitForElementToBeClickable(acp.record, 30).click();
		applyExplicitWaitsUntilElementVisible(acp.onBoardingStatus,30);
		WebElement expected = acp.onBoardingStatus;
		String value = expected.getText();
		Assert.assertEquals(value, currentState);
	}
}
