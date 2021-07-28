package pageModules;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import base.BaseClass;
import config.DefineConstants;
import helperMethods.DropDown;
import helperMethods.JavascriptClick;
import helperMethods.JsonUtils;
import helperMethods.ScrollTypes;
import helperMethods.SwitchWindow;
import helperMethods.WaitTypes;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.Object_AppCenterPage;
import stepdefinitions.LoginToAuthorUrl;

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
		;
		applyWait.waitforElementToBeDisplayed(acp.loginButton, 30).click();

	}

	public void verifyUserLoginSuccesfullyToAppCenter() throws InterruptedException {
		Thread.sleep(2000);
		Boolean status = applyWait.waitforElementToBeDisplayed(acp.dataStackLogo, 80).isDisplayed();
		if (status) {
		}
	}

	public void dataService(String dataService) throws Exception {
		Thread.sleep(2000);
		data_Service = dataService;
		Thread.sleep(3000);
		WebElement data = driver.findElement(By.xpath("//div[contains(text(),'" + dataService + "')]"));
		javascriptClick = new JavascriptClick(driver);
		data.click();
	}

	public void userEnterData() throws Exception {
		Thread.sleep(2000);
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		Thread.sleep(3000);
		List<WebElement> textBoxes = acp.textBoxes;
		System.out.println(textBoxes.size());
		String path = System.getProperty("user.dir");
		JSONObject jsonObject = JsonUtils.getJSONObject(path + "\\testData\\" + data_Service + ".data.json");

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
							applyWait.waitForElementToBeClickable(textBox, 30)
									.sendKeys((String) obj.get(attributes[1]));

						} else {
							applyWait.waitForElementToBeClickable(textBox, 30)
									.sendKeys(((String) jsonObject.get(id1)).toString());
							;
						}
					}
					if (textBox.getAttribute("type").equals("number")) {
						applyWait.waitForElementToBeClickable(textBox, 30)
								.sendKeys(((Long) jsonObject.get(id1)).toString());
						;
					}

				}

			}
		}

		else {
			for (int j = 1; j <= textBoxes.size(); j++) {
				WebElement textBox = driver.findElement(By.xpath("(//*[contains(@class,'form-control')])[" + j + "]"));
				if (textBox.isEnabled()) {
					String id1 = textBox.getAttribute("id");

					if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")||textBox.getAttribute("type").equals("select-one")) {
						if ((String) jsonObject.get(id1) != null) {

							if (textBox.getAttribute("type").equals("text")|| textBox.getAttribute("type").equals("textarea")) {
								if (id1.contains(".")) {
									String[] attributes = id1.trim().split("[^a-zA-Z0-9]+");

									JSONObject obj = (JSONObject) jsonObject.get(attributes[0]);
									applyWait.waitForElementToBeClickable(textBox, 30).sendKeys((String) obj.get(attributes[1]));

								} else {
									applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(((String) jsonObject.get(id1)).toString());
									
								}
							}

							if (textBox.getAttribute("type").equals("select-one")) {
								if( jsonObject.get(id1).equals("")) {
									textBox.click();
								}
								else {

								dropdown.selectByVisibleText(textBox, ((String) jsonObject.get(id1)).toString());
								
								}

							}
						}

					}

					else if (textBox.getAttribute("type").equals("number") ||textBox.getAttribute("type").equals("select-one")) {
						System.out.println(jsonObject.get(id1).getClass());
						
							if(jsonObject.get(id1).getClass().toString().contains("Double")) {
								if ((Double) jsonObject.get(id1) != null) {
		
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
							if ((Long) jsonObject.get(id1) != null) {

								if (textBox.getAttribute("type").equals("number")) {
									Long value = (Long) jsonObject.get(id1);
									applyWait.waitForElementToBeClickable(textBox, 30).sendKeys(value.toString());
									
								}
								
								if (textBox.getAttribute("type").equals("select-one")) {

									dropdown.selectByVisibleText(textBox, ((Long) jsonObject.get(id1)).toString());

								}
							  }
						}
					}
						else if(textBox.getAttribute("type").equals("email")) {
							System.out.println(jsonObject.get(id1).toString() + " : Email value");
							applyWait.waitForElementToBeClickable(textBox,30).sendKeys(((String)jsonObject.get(id1)).toString());
							
					}
				}
			}

		}

//			if((jsonArray.size()-1) > i) {
//			applyWait.waitForElementToBeClickable(acp.proceedAndCreateAnother, 30).click();
//				}
//				else {
		applyWait.waitForElementToBeClickable(acp.save, 30).click();
	}
//				applyWait.waitForElementToBeClickable(acp.comments, 30).sendKeys("Check");;
//				applyWait.waitForElementToBeClickable(acp.submit, 30).click();
//				applyWait.waitForElementToBeClickable(acp.userDetails, 30).click();
//				applyWait.waitForElementToBeClickable(acp.logout, 30).click();
//				
//				workflow();

//		}
//	}

	public void workflow() {
		applyWait.waitForElementToBeClickable(acp.username, 30).sendKeys("reviewer@appveen.com");
		applyWait.waitForElementToBeClickable(acp.nextButton, 30).click();
		applyWait.waitforElementToBeDisplayed(acp.password, 30).sendKeys("123123123");
		;
		applyWait.waitforElementToBeDisplayed(acp.login, 30).click();
		applyWait.waitforElementToBeDisplayed(acp.workflowTab, 30).click();
		for (WebElement workflow : acp.workflowsId) {
			workflow.click();
			applyWait.waitforElementToBeDisplayed(acp.respond, 30).click();
			applyWait.waitforElementToBeDisplayed(acp.approve, 30).click();
			applyWait.waitforElementToBeDisplayed(acp.enterApproveComment, 30).sendKeys("Approved");
			;
			applyWait.waitforElementToBeDisplayed(acp.approve, 30).click();

		}

	}

	public void verifyTotalCountOfDocuments(Integer documentCount) throws Exception {
		applyExplicitWaitsUntilElementVisible(acp.documentList);
		String documentList = acp.documentList.getText();
		Integer documentListCount = Integer.parseInt(documentList.trim().split("of")[1].trim());
	}

	public void removeRecords(Integer int1) throws Exception {
		Thread.sleep(1000);
		for (int i = 0; i < int1; i++) {
			WebElement checkbox = acp.checkbox;
			applyExplicitWaitsUntilElementVisible(acp.checkbox);
			checkbox.click();
			Thread.sleep(2000);
			acp.delete.click();
			acp.yes.click();

		}

	}

	

}
