package base;

import java.net.MalformedURLException;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.List;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	public static ExtentSparkReporter report = null;
	public static ExtentReports extent = null;
	public static ExtentTest test = null;
	public static String DownloadFilepath, folder, basefold;
	public static int count;
	public static String author_URL;
	public static String app_center_URL;
	public static String errorMessage = "";
	public static String featureName ="";
	public static String testData = System.getProperty("testData");
	public static String browser = System.getProperty("browser");
	public static String headless = System.getProperty("headless");
	public static String url = System.getProperty("url");
	public static String path = System.getProperty("user.dir");
	public boolean isHeadLess;

	@SuppressWarnings("deprecation")
	public void start() {

		if (browser == null) {
			browser = "chrome";
		}
		if(headless==null) {
			isHeadLess=false;
		}
		else if(headless.equals("true")){
			isHeadLess=true;
		}
		
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("window-size=1280,1024");
			if(isHeadLess) {
			options.addArguments("--headless");
			}

			String path = System.getProperty("user.dir");
			DownloadFilepath = path + "\\Test_Data\\Download";
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);
			driver.manage().window().maximize();
			
		} else if (browser.equalsIgnoreCase("internetExplorer")) {
			
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			
		} else if (browser.equalsIgnoreCase("safari")) {
			
			driver = new SafariDriver();
			driver.manage().window().maximize();
			
		} 
		 else if (browser.equalsIgnoreCase("headless")) {
			 
			  driver=new HtmlUnitDriver(true);
				driver.manage().window().maximize();
				System.out.println("Running in headless mode");
			}
		
		else {
			System.out.println("Please pass the correct browser value");
		}
		
		if (url == null) {
			url = "https://bifrost.ds.appveen.com";
			url="https://qa.ds.appveen.com";

		}

		if (url.equalsIgnoreCase("https://staging.appveen.com")) {
			author_URL = "https://staging.appveen.com/author";
			app_center_URL = "https://staging.appveen.com/appcenter";
		}

		else if (url.equalsIgnoreCase("https://bifrost.ds.appveen.com")) {
			author_URL = "https://bifrost.ds.appveen.com/author";
			app_center_URL = "https://bifrost.ds.appveen.com/appcenter";
		}
		else {
			author_URL = url+"/author";
			app_center_URL = url+"/appcenter";
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}


	public static void applyExplicitWaitsUntilElementVisible(WebElement element, int time)throws MalformedURLException {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void applyWaitForDynamicWebElement(By locator, int time) {

		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void applyExplicitWaitsUntilElementVisible(List<WebElement> element, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public void handleElementClickException(WebElement element) {

		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

		}
		javascriptExecutor.executeScript("arguments[0].click()", element);

	}
	
	public boolean isWebElementDisplayed(WebElement element) {
		boolean status;
		try {
			 status =element.isDisplayed();
		}
		catch(NoSuchElementException e) {
			 status =false;
		}
		
		return status;
	}

}