package base;

import java.net.MalformedURLException;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.List;

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

import config.Constants;
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
//				headless="true";
		if (browser == null) {
			browser = "chrome";
		}
		
		if(headless==null) {
			isHeadLess=false;
		}
		else if(headless.equals("true")){
			isHeadLess=true;
		}
		System.out.println();
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(System.getProperty("os.name").equals("Linux")) {
				System.setProperty("webdriver.chrome.driver","/home/ubuntu/ds-ui-automation/drivers/chromedriver");
//				System.setProperty("webdriver.chrome.driver","/home/ubuntu/ds-ui-automation/drivers/chromedriver");
				options.addArguments("--headless", "--window-size=1296,696", "--no-sandbox", "--disable-gpu", "--disable-dev-shm-usage");
			}
			else {
				WebDriverManager.chromedriver().setup();
				options.addArguments("--start-maximized");
				if(isHeadLess) {
					options.addArguments("--headless","--window-size=1296,696", "--no-sandbox", "--disable-gpu", "--disable-dev-shm-usage");
				}
			}
			
			driver = new ChromeDriver(options);
			
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
			System.err.println("Please pass the correct browser value");
		}
		
		if (url == null) {
			
			url = Constants.bifrostInstance ;
			url=Constants.qaInstance;

		}

		if (url.equalsIgnoreCase(Constants.stagingInstance)) {
			
			author_URL = Constants.stagingInstance + Constants.authorURLSuffix;
			app_center_URL = Constants.stagingInstance + Constants.appcenterURLSuffix;
			
		}

		else if (url.equalsIgnoreCase(Constants.bifrostInstance)) {
			
			author_URL = Constants.bifrostInstance + Constants.authorURLSuffix;
			app_center_URL = Constants.bifrostInstance + Constants.appcenterURLSuffix;
			
		}
		else {
			
			author_URL = url+ Constants.authorURLSuffix;
			app_center_URL = url+ Constants.appcenterURLSuffix;
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}


	public static void applyExplicitWaitsUntilElementVisible(WebElement element, int time)throws MalformedURLException {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));

	}
	
	public static void applyExplicitWaitsUntilElementClickable(WebElement element, int time)throws MalformedURLException {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));

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