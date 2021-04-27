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
import stepdefinitions.LoginToAuthorUrl;

public class LoginAppCenter  extends BaseClass {
	
	private WebDriver driver;
	private WaitTypes applyWait;
	private ExtentTest test;
	private JavascriptClick javascriptClick;
	public DropDown dropdown;
	public ScrollTypes scroll;
	
	public LoginAppCenter(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.applyWait = new WaitTypes(driver);
		this.test = test;
	}

	public void loginToAppCenterPage() {
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver=new ChromeDriver();
		this.driver=getDriver();
		applyWait = new WaitTypes(driver);
		dropdown=new DropDown(driver);
		scroll=new ScrollTypes(driver);
		
		SwitchWindow.openNewTab(driver);
		driver.get(DefineConstants.APP_CENTER_URL);
		driver.manage().window().maximize();
		
	}

	public void enterUserNameAndPassword(String username, String password) throws InterruptedException {

		applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_AppCenter_FilePath, "username"))), 30).sendKeys(username);
		applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_AppCenter_FilePath, "nextButton"))), 30).click();
		Thread.sleep(2000);
		applyWait.waitforElementToBeDisplayed(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_AppCenter_FilePath, "password"))), 30).sendKeys(password);;
		applyWait.waitforElementToBeDisplayed(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_AppCenter_FilePath, "loginButton"))), 30).click();
		
		
	}

	public void verifyUserLoginSuccesfullyToAppCenter() throws InterruptedException {
		Thread.sleep(5000);
		Boolean status=	applyWait.waitforElementToBeDisplayed(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_AppCenter_FilePath, "dataStackLogo"))), 80).isDisplayed();
		if(status) {
			System.out.println("User log in succesfully to AppCenter Successfully");
		}
		
		

		
	}

	public void userEnterData(String data_Service) throws Exception {
		Thread.sleep(3000);
		WebElement data=driver.findElement(By.xpath("//div[contains(text(),'"+data_Service+"')]"));
		javascriptClick=new JavascriptClick(driver);
	//	javascriptClick.highLighterMethod(data);
		data.click();
		Thread.sleep(1000);
		applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_AppCenter_FilePath, "addDataButton"))), 30).click();	
		Thread.sleep(3000);
		List<WebElement> textBoxes = driver.findElements(By.xpath(JsonUtils.getData(DefineConstants.json_AppCenter_FilePath, "textBoxes")));
		
		JSONArray jsonArray = JsonUtils.getJSONArray("C:\\Users\\DELL\\eclipse-workspace\\DataStack\\testData\\"+data_Service+".data.json");
		for(int i=0;i<jsonArray.size();i++) {
			
			JSONObject jsonObject=(JSONObject) jsonArray.get(i);
			Thread.sleep(1000);
			
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
			applyWait.waitForElementToBeClickable(driver.findElement(By.xpath(JsonUtils.getData(DefineConstants.json_AppCenter_FilePath, "saveAndCreateAnother"))), 30).click();
			Thread.sleep(1000);
	}
	}
	
	
	

}
