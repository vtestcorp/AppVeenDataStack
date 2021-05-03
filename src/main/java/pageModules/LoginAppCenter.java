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
import pageobjects.Object_AppCenterPage;
import stepdefinitions.LoginToAuthorUrl;

public class LoginAppCenter  extends BaseClass {
	
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
		dropdown=new DropDown(driver);
		scroll=new ScrollTypes(driver);
		acp=new Object_AppCenterPage();
	}

	public void loginToAppCenterPage() {
		
		SwitchWindow.openNewTab(driver);
		driver.get(DefineConstants.APP_CENTER_URL);
		driver.manage().window().maximize();
		
	}

	public void enterUserNameAndPassword(String username, String password) throws InterruptedException {

		applyWait.waitForElementToBeClickable(acp.username, 30).sendKeys(username);
		applyWait.waitForElementToBeClickable(acp.nextButton, 30).click();
		applyWait.waitforElementToBeDisplayed(acp.password, 30).sendKeys(password);;
		applyWait.waitforElementToBeDisplayed(acp.loginButton, 30).click();
		
		
	}

	public void verifyUserLoginSuccesfullyToAppCenter() throws InterruptedException {
		Thread.sleep(2000);
		Boolean status=	applyWait.waitforElementToBeDisplayed(acp.dataStackLogo, 80).isDisplayed();
		if(status) {
			System.out.println("User log in succesfully to AppCenter Successfully");
		}
	}
	
	public void dataService(String dataService) throws Exception {
		Thread.sleep(1000);
		data_Service=dataService;
		WebElement data=driver.findElement(By.xpath("//div[contains(text(),'"+dataService+"')]"));
		javascriptClick=new JavascriptClick(driver);
		data.click();
	}

	public void userEnterData() throws Exception {
		Thread.sleep(1000);
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();	
		List<WebElement> textBoxes = acp.textBoxes;
		
		JSONArray jsonArray = JsonUtils.getJSONArray("C:\\Users\\DELL\\eclipse-workspace\\DataStack\\testData\\"+data_Service+".data.json");
		for(int i=0;i<jsonArray.size();i++) {
			Thread.sleep(1000);
			JSONObject jsonObject=(JSONObject) jsonArray.get(i);
			
				for(int j=1;j<=textBoxes.size();j++) {
				WebElement textBox=driver.findElement(By.xpath("(//input[contains(@class,'form-control')])["+j+"]"));
		
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
				if((jsonArray.size()-1) > i) {
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_AppCenter_FilePath, "saveAndCreateAnother"))), 30).click();
				}
				else {
					applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_AppCenter_FilePath, "save"))), 30).click();
				}
	}
	}

	public void verifyTotalCountOfDocuments(Integer documentCount) throws Exception {
		Thread.sleep(1000);
		String documentList =acp.documentList.getText();
		System.out.println(documentList);
		Integer documentListCount=Integer.parseInt(documentList.trim().split("of")[1].trim());
		System.out.println(documentListCount);
	}

	public void removeRecords(Integer int1) throws Exception {
		Thread.sleep(1000);
		for(int i=0;i<int1;i++) {
			WebElement  checkbox=acp.checkbox;
			checkbox.click();
			acp.delete.click();
			acp.yes.click();
			
		}
		
	}

	
	
	
	

}
