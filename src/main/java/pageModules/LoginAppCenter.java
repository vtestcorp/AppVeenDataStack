package pageModules;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.jayway.jsonpath.JsonPath;

import base.BaseClass;
import helperMethods.DropDown;
import helperMethods.JavascriptClick;
import helperMethods.JsonUtils;
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

	public LoginAppCenter(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.applyWait = new WaitTypes(driver);
		this.test = test;
		dropdown = new DropDown(driver);
		scroll = new ScrollTypes(driver);
		acp = new Object_AppCenterPage();
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
<<<<<<< HEAD
		data_Service = dataService;
		try {
			WebElement data = driver.findElement(By.xpath("//div[contains(text(),'" + dataService + "')]"));
=======
		data_Service=dataService;
//		Thread.sleep(2000);
		try {
			By ds=By.xpath("//div[contains(text(),'" + dataService + "')]");
			applyWaitForDynamicWebElement(ds, 5);
			WebElement data = driver.findElement(ds);
>>>>>>> deepak
			data.click();
		} catch (Exception e) {
			Thread.sleep(10000);
			driver.navigate().refresh();
<<<<<<< HEAD
			Thread.sleep(4000);
=======
			Thread.sleep(3000);
>>>>>>> deepak
					try {
						WebElement data = driver.findElement(By.xpath("//div[contains(text(),'" + dataService + "')]"));
						data.click();
					} catch (Exception e1) {
						Thread.sleep(10000);
						driver.navigate().refresh();
<<<<<<< HEAD
						Thread.sleep(4000);
=======
						Thread.sleep(3000);
>>>>>>> deepak
						WebElement data = driver.findElement(By.xpath("//div[contains(text(),'" + dataService + "')]"));
						data.click();
					}
			
			
		}
<<<<<<< HEAD
		
=======
>>>>>>> deepak
	}
//		int data = driver.findElements(By.xpath("//div[contains(text(),'" + dataService + "')]")).size();
//		while(data==1)
//		{
//			Thread.sleep(5000);
//			driver.navigate().refresh();
//		    data = driver.findElements(By.xpath("//div[contains(text(),'" + dataService + "')]")).size();
//		}
//		WebElement data1 = driver.findElement(By.xpath("//div[contains(text(),'" + dataService + "')]"));
//	    data1.click();
//	}

	public void userEnterData() throws Exception {
		applyExplicitWaitsUntilElementVisible(acp.addDataButton,10);
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		applyExplicitWaitsUntilElementVisible(acp.textBox1,10);
		List<WebElement> textBoxes = acp.textBoxes;
		String path = System.getProperty("user.dir");
		String filePath=path + "\\testData\\" + data_Service + ".data.json";
		JSONObject jsonObject = JsonUtils.getJSONObject(filePath);

		/**
		 * Code for Experience tab
		 */
		List<WebElement> stepNames = driver
				.findElements(By.xpath("//div[@class='step-name high-zIndex text-truncate']"));

		if (stepNames.size() > 0) {
			for (WebElement stepName : stepNames) {
				System.out.println(stepName.getText());
				stepName.click();

				for (int j = 1; j <= textBoxes.size(); j++) {

					WebElement textBox = driver
							.findElement(By.xpath("(//*[contains(@class,'form-control')])[" + j + "]"));

					String id1 = textBox.getAttribute("id");
					System.out.println(textBox.getAttribute("type"));
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
							if(jsonObject.get(id1).getClass().toString().contains("Double")) {
								if (jsonObject.get(id1) != null) {
		
									if (textBox.getAttribute("type").equals("number")) {
										Double value = (Double) jsonObject.get(id1);
										applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value.toString());
										
									}
									
									if (textBox.getAttribute("type").equals("select-one")) {

										dropdown.selectByVisibleText(textBox, ((Double) jsonObject.get(id1)).toString());

									}
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
					
					else if (textBox.getAttribute("type").equals("email")) {
						applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1.toString());
					}
				}
			}
		}
		applyWait.waitForElementToBeClickable(acp.save, 30).click();
	}


	public void userEnterDataInLocationField() throws InterruptedException {
		Thread.sleep(2000);
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		Thread.sleep(3000);
		List<WebElement> textBoxes = acp.textBoxesLocation;
		
		String filePath=path + "\\testData\\" + data_Service + ".data.json";
		JSONObject jsonObject = JsonUtils.getJSONObject(filePath);
		
		
		for (int j = 2; j <= textBoxes.size(); j++) {
		WebElement textBox = driver.findElement(By.xpath("(//input[@class='form-control form-control-sm rounded ng-pristine ng-valid ng-star-inserted ng-touched' or 'searchInput pac-target-input'])["+j+"]"));
			if (textBox.isEnabled()) {
				String id1 = textBox.getAttribute("id");
				String v1 =  jsonObject.get(id1).toString();
				System.out.println(v1);
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
<<<<<<< HEAD
     							textBox.sendKeys(v2);
								Thread.sleep(2000);
=======
								textBox.sendKeys(v2);
>>>>>>> deepak
								textBox.sendKeys(Keys.DOWN);
								textBox.sendKeys(Keys.ENTER);
        			}
				}
			}
			
		}
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
				//applyExplicitWaitsUntilElementVisible(acp.buttons, 10);
				Thread.sleep(5000);
				List<WebElement> buttons = acp.buttons;
				String filePath=path + "\\testData\\" + data_Service + ".data.json";
				JSONObject jsonObject = JsonUtils.getJSONObject(filePath);
				for (int j = 1; j <= buttons.size(); j++) {
					WebElement button = driver.findElement(By.xpath("(//input[@type='checkbox' or @id='_id' ])["+j+"]"));
					String id1 = button.getAttribute("id");
					Thread.sleep(1000);
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
					      }
					   	}
				}
			
		

	
	public void workflow() {
		applyWait.waitForElementToBeClickable(acp.username, 30).sendKeys("reviewer@appveen.com");
		applyWait.waitForElementToBeClickable(acp.nextButton, 30).click();
		applyWait.waitforElementToBeDisplayed(acp.password, 30).sendKeys("123123123");
		applyWait.waitforElementToBeDisplayed(acp.login, 30).click();
		applyWait.waitforElementToBeDisplayed(acp.workflowTab, 30).click();
		for (WebElement workflow : acp.workflowsId) {
			workflow.click();
			applyWait.waitforElementToBeDisplayed(acp.respond, 30).click();
			applyWait.waitforElementToBeDisplayed(acp.approve, 30).click();
			applyWait.waitforElementToBeDisplayed(acp.enterApproveComment, 30).sendKeys("Approved");
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

		List<WebElement> addNew = driver.findElements(By.xpath("//span[text()='Add new']"));
		int k=1, m=2;
			for (int j = 1; j <= addNew.size(); j++) {
				WebElement textBox = driver.findElement(By.xpath("(//span[text()='Add new'] )[" +j+ "]"));
				if (textBox.isEnabled()) {
					
					String id1 = "collection1001";
					for(int i=1;i<4;i++)
					{
						textBox.click();
<<<<<<< HEAD
						Thread.sleep(8000);
=======
>>>>>>> deepak
						WebElement textBox1 = driver.findElement(By.xpath("(//input[contains(@id,'collection')])["+i+"]"));
						String value = JsonUtils.getJsonValue(path + "\\testData\\" + data_Service + ".data.json", id1);	
						applyWait.waitForElementToBeClickable(textBox1, 30).sendKeys(value);
					}
					
					k++;
			}

		}

		applyWait.waitForElementToBeClickable(acp.save, 30).click();
	}

	public void addDataForLibrary() {
		
	}

	public void addDataForGroups() throws MalformedURLException {
		
		applyExplicitWaitsUntilElementVisible(acp.addDataButton,20);
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		applyExplicitWaitsUntilElementVisible(acp.textBox1,20);
		List<WebElement> textBoxes = acp.textBoxes;
		String path = System.getProperty("user.dir");
		String filePath=path + "\\testData\\" + data_Service + ".data.json";
		JSONObject jsonObject = JsonUtils.getJSONObject(filePath);
		

		for (int j = 1; j <= textBoxes.size(); j++) {
			WebElement textBox = driver.findElement(By.xpath("(//*[contains(@class,'form-control')])[" + j + "]"));
			if (textBox.isEnabled()) {
				String id1 = textBox.getAttribute("id");

				String value1=JsonUtils.getJsonValue(filePath,id1);
				
				if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")||textBox.getAttribute("type").equals("select-one")) {
						if (value1!= null) {


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
						if(jsonObject.get(id1).getClass().toString().contains("Double")) {
							if (value1!= null) {
	
								if (textBox.getAttribute("type").equals("number")) {
									Double value = (Double) jsonObject.get(id1);
									applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value.toString());
									
								}
								
								if (textBox.getAttribute("type").equals("select-one")) {

									dropdown.selectByVisibleText(textBox, ((Double) jsonObject.get(id1)).toString());

								}
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
				
				else if (textBox.getAttribute("type").equals("email")) {
					applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1.toString());
				}
			}
		}
	
		applyWait.waitForElementToBeClickable(acp.save, 30).click();
	}

	public void addDataForDate() throws Exception {
		
		applyExplicitWaitsUntilElementVisible(acp.addDataButton,10);
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		applyExplicitWaitsUntilElementVisible(acp.textBox1,10);
		List<WebElement> textBoxes = acp.dateFields;
		String path = System.getProperty("user.dir");
		String filePath=path + "\\testData\\" + data_Service + ".data.json";
		JSONObject jsonObject = JsonUtils.getJSONObject(filePath);
		
		for (int j = 1; j <= textBoxes.size(); j++) {
			WebElement textBox = driver.findElement(By.xpath("(//*[@class='btn btn-link mr-2 p-0' or @id='_id'])[" + j + "]"));
			if (textBox.isEnabled()) {
				String id1 = textBox.getAttribute("id");

				String value1=JsonUtils.getJsonValue(filePath,id1);
				
				if(id1.equals("_id")) {
					applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value1.toString());
				}
				else {
					
					JSONObject json=(JSONObject) jsonObject.get(id1);
					String dateValue=json.get("rawData").toString();
					String fullDate[]=dateValue.split("T")[0].split("-");
					String fullTime[]=dateValue.split("T")[1].split(":");
					String date=fullDate[2];
					String month=fullDate[1];
					String year=fullDate[0];
					String hour=fullTime[0];
					String minute=fullTime[1];
					String[] second=fullTime[2].trim().split(".");
					
					WebElement selectDate=driver.findElement(By.id(id1));
					selectDate.click();
					dropdown.selectByValue(acp.yearDropDown, year);
					dropdown.selectByIndex(acp.monthDropDown, Integer.parseInt(month)-1);
					applyExplicitWaitsUntilElementVisible(acp.day,10);
					WebElement date1=driver.findElement(By.xpath("//span[contains(@class,'disabled')=false and @id='_day']["+date+"]"));
					date1.click();
					applyWait.waitForElementToBeClickable(acp.doneButton, 30).click();
					Thread.sleep(500);
					
				}
			}
			}
		applyWait.waitForElementToBeClickable(acp.save, 30).click();
	}
	
	
}

