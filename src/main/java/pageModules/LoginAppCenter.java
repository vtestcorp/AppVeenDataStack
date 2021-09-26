package pageModules;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.jayway.jsonpath.JsonPath;

import base.BaseClass;
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
	private ExtentTest test;
	private JavascriptClick javascriptClick;
	public DropDown dropdown;
	public ScrollTypes scroll;
	public static String data_Service;
	public Object_AppCenterPage acp;
	public LoginPage lp;

	public LoginAppCenter(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.applyWait = new WaitTypes(driver);
		this.test = test;
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
		if (status) {
		}
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
			Thread.sleep(10000);
			driver.navigate().refresh();
			Thread.sleep(3000);
					try {
						WebElement data = driver.findElement(ds);
						action.moveToElement(data).perform();
						data.click();
					} catch (Exception e1) {
						Thread.sleep(20000);
						driver.navigate().refresh();
						Thread.sleep(3000);
							try {
									WebElement data = driver.findElement(ds);
									action.moveToElement(data).perform();
									data.click();
								} catch (Exception e2) {
									Thread.sleep(20000);
									driver.navigate().refresh();
									Thread.sleep(3000);
									WebElement data = driver.findElement(ds);
									action.moveToElement(data).perform();
									data.click();
							}
					}
			
			
		}
	
			
		}
	
	public void importDatafile() throws MalformedURLException, InterruptedException {
		applyExplicitWaitsUntilElementVisible(acp.importfile,10);
		applyWait.waitForElementToBeClickable(acp.importfile, 30).click();
		Thread.sleep(2000);
		applyWait.waitForElementToBeClickable(acp.uploadFile, 30).sendKeys("C:\\Users\\Lenovo\\Documents\\Customer.xlsx");
		

	}

	public void userEnterData() throws Exception {
		applyExplicitWaitsUntilElementVisible(acp.addDataButton,10);
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		applyExplicitWaitsUntilElementVisible(acp.textBox1,10);
		List<WebElement> textBoxes = acp.textBoxes;
		String filePath=path + "\\testData\\" + data_Service + ".data.json";
		JSONObject jsonObject = JsonUtils.getJSONObject(filePath);

		/**
		 * Code for Experience tab
		 */
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
		applyWait.waitForElementToBeClickable(acp.save, 30).click();
	}


	public void userEnterDataInLocationField() throws InterruptedException, Exception {
		applyExplicitWaitsUntilElementVisible(acp.addDataButton, 20);
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		Thread.sleep(5000);
		applyExplicitWaitsUntilElementVisible(acp.textBoxesLocation, 20);
		List<WebElement> textBoxes = acp.textBoxesLocation;
		
		String filePath=path + "\\testData\\" + data_Service + ".data.json";
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
		Thread.sleep(2000);
				applyWait.waitForElementToBeClickable(acp.save, 30).click();
					
	}
				
	public void userEnterDataInUserField() throws InterruptedException {
		Thread.sleep(2000);
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		Thread.sleep(3000);
		List<WebElement> textBoxes = acp.textBoxes;
		
		String filePath=path + "\\testData\\" + data_Service + ".data.json";
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
				
					
			public void userEnterDataforBoolean() throws InterruptedException {
				
				applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
				Thread.sleep(1000);
			//	applyExplicitWaitsUntilElementVisible(acp.buttons, 10);
				List<WebElement> buttons = acp.buttons;
				String filePath=path + "\\testData\\" + data_Service + ".data.json";
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
			     	applyWait.waitForElementToBeClickable(acp.save, 30).click();
				}
			

			public void userEnterDataForRichText() throws InterruptedException {
				applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
				Thread.sleep(5000);
			//	applyExplicitWaitsUntilElementVisible(acp.richtextBoxes, 10);
				List<WebElement> textBoxes = acp.richtextBoxes;
				String filePath=path + "\\testData\\" + data_Service + ".data.json";
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
		applyExplicitWaitsUntilElementVisible(acp.documentList,10);
		String documentList = acp.documentList.getText();
		Integer documentListCount = Integer.parseInt(documentList.trim().split("of")[1].trim());
	}

	public void removeRecords(Integer int1) throws Exception {
		Thread.sleep(1000);
		for (int i = 0; i < int1; i++) {
			WebElement checkbox = acp.checkbox;
			applyExplicitWaitsUntilElementVisible(acp.checkbox,10);
			checkbox.click();
			acp.delete.click();
			acp.yes.click();

		}

	}

	public void addDataForFile() throws Exception {
		
		applyExplicitWaitsUntilElementVisible(acp.addDataButton,10);
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		applyExplicitWaitsUntilElementVisible(acp.textBox1,10);
		List<WebElement> textBoxes = driver.findElements(By.xpath("//input[@class='invisible position-absolute' or @id='_id']"));
		String path = System.getProperty("user.dir");
		String filePath=path + "\\testData\\" + data_Service + ".data.json";
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
			String absolutePath=new File("files\\"+value).getAbsolutePath();
			textBox.sendKeys(absolutePath);
			Thread.sleep(1000);
		}}
		}
		
		applyWait.waitForElementToBeClickable(acp.save, 30).click();
	}

	
	public void userEnterDataforCollection() throws InterruptedException {
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		String path = System.getProperty("user.dir");
		JSONObject jsonObject = JsonUtils.getJSONObject(path + "\\testData\\" + data_Service + ".data.json");
         Thread.sleep(5000);
		List<WebElement> addNew = driver.findElements(By.xpath("//*[@id='_id' or text()='Add new']"));
		
		int k=1, m=2;
			for (int j = 1; j <= addNew.size(); j++) {
				String val = null;
				WebElement textBox = driver.findElement(By.xpath("(//*[@id='_id' or text()='Add new'])["+j+"]"));
				if (textBox.isEnabled()) {
					String id1 = textBox.getAttribute("id");
					if(id1.equals("_id"))
					{
						 val = (String) jsonObject.get(id1);
						 applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(val);
					}
					else {
					 id1 = "user"+k;
					// JSONArray arr=jsonObject.get(id1);
					for(int i=0;i<3;i++)
					{
						textBox.click();
    					Thread.sleep(2000);
						WebElement textBox1 = driver.findElement(By.xpath("//input[@id='"+id1+"."+i+"']"));
						String newId=id1+"["+i+"]";
						String value = JsonUtils.getJsonValue(path + "\\testData\\" + data_Service + ".data.json", newId);	
						applyWait.waitForElementToBeClickable(textBox1, 30).sendKeys(value);
						Thread.sleep(1000);
					}
					
					k++;
			}

		}
	}
		applyWait.waitForElementToBeClickable(acp.save, 30).click();
	}

	public void addDataForLibrary() {
		
	}

	public void addDataForGroups() throws MalformedURLException, Exception {
		
		applyExplicitWaitsUntilElementVisible(acp.addDataButton,20);
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		applyExplicitWaitsUntilElementVisible(acp.textBox1,20);
		List<WebElement> textBoxes = acp.groupTextBoxes;
		String filePath=path + "\\testData\\" + data_Service + ".data.json";
		JSONObject jsonObject = JsonUtils.getJSONObject(filePath);

		for (int j = 1; j <= textBoxes.size(); j++) {
			WebElement textBox = driver.findElement(By.xpath("(//*[contains(@class,'form-control') or @type='checkbox' or @type='file' or contains(@class,'btn btn-link mr-2 p-0') or contains(@class,'searchInput')])[" + j + "]"));
			if (textBox.isEnabled()) {
				String id1 = textBox.getAttribute("id");

				String value1=JsonUtils.getJsonValue(filePath,id1);
				
//--------------------------------------------------------String Text------------------------------------------------------------------------------------------------------------------				
				
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
//---------------------------------------------------------Number-----------------------------------------------------------------------------------------------------------------------------------
			
				else if (textBox.getAttribute("type").equals("number") ||textBox.getAttribute("type").equals("select-one")) {
					applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1.toString());
				}
				
				
				
//-----------------------------------------------------------Email------------------------------------------------------------------------------------------------------------------------				
			
				else if (textBox.getAttribute("type").equals("email")) {
					applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1.toString());
				}
				
//-----------------------------------------------------------Boolean-------------------------------------------------------------------------------------------------------------------------			
			
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
				
//--------------------------------------------------------File--------------------------------------------------------------------------------------------------------------------------------				
			
				else if (textBox.getAttribute("type").equals("file")) {
					Thread.sleep(500);
				String json1=JsonUtils.getJsonValue(filePath, id1+".metadata.filename");
					if(json1!=null) {
						String absolutePath=new File("files\\"+json1).getAbsolutePath();
						textBox.sendKeys(absolutePath);
						Thread.sleep(500);
					}
				}
//--------------------------------------------------------Location--------------------------------------------------------------------------------------------------------------------------------				
			
				else if (textBox.getAttribute("type").equals("email")) {
					applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1.toString());
				}
				
				else if (textBox.getAttribute("type").equals("submit")) {
					
					String dateValue=JsonUtils.getJsonValue(filePath, id1+".rawData");
					
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
					applyExplicitWaitsUntilElementVisible(acp.day,10);
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

	public void addDataForDate() throws Exception {
		
		applyExplicitWaitsUntilElementVisible(acp.addDataButton,10);
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		applyExplicitWaitsUntilElementVisible(acp.textBox1,20);
		List<WebElement> textBoxes = acp.dateFields;
		String path = System.getProperty("user.dir");
		String filePath=path + "\\testData\\" + data_Service + ".data.json";
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
					applyExplicitWaitsUntilElementVisible(acp.day,10);
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

	public void addDataForRelation() throws Exception {
		applyExplicitWaitsUntilElementVisible(acp.addDataButton,10);
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		applyExplicitWaitsUntilElementVisible(acp.textBox1,10);
		List<WebElement> textBoxes = acp.textBoxes;
		String filePath=path + "\\testData\\" + data_Service + ".data.json";
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
	applyWait.waitForElementToBeClickable(acp.save, 30).click();
	}
	
	
}

