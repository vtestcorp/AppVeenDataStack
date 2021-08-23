package base;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import config.DefineConstants;
import config.DefineProperties;
import helperMethods.Screenshots;
import helperMethods.SwitchWindow;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static String atest = "";
	public String space = "", hypen = "";
	int invalidLinksCount = 0;
	int invalidImageCount = 0;
	public static WebDriver driver;
	public ExtentTest test;
	public static ExtentReports extent;
	public static String DownloadFilepath, folder, basefold;
	public static int count;
	
	public static  String author_URL;
	public static  String app_center_URL ;

	public static String testData = System.getProperty("testData");
	public static String browser = System.getProperty("browser");
	public static String url = System.getProperty("url");
	public static String path = System.getProperty("user.dir");



	@SuppressWarnings("deprecation")
	public void start() {
		if(browser==null) {
			browser="chrome";
		}
//		htmlReporter = config.ExtentReports.createInstance("report/extent.html");

//		extent = new ExtentReports();
//		extent.attachReporter(htmlReporter);
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("window-size=1280,1024");
			String path = System.getProperty("user.dir");
			DownloadFilepath = path + "\\Test_Data\\Download";
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
	        DesiredCapabilities capabilities = DesiredCapabilities.firefox();  
	        capabilities.setCapability("marionette",true);  
	        driver= new FirefoxDriver(capabilities); 
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("internetexplorer")) {
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
			driver.manage().window().maximize();
		}
		else if (browser.equalsIgnoreCase("safari")) {
			driver=new SafariDriver();
			driver.manage().window().maximize();
		}
		else {
			System.out.println("Please pass the correct browser value");
		}
		
		if(url==null) {
			url="https://bifrost.ds.appveen.com";
		}
		
		if(url.equalsIgnoreCase("https://staging.appveen.com")) {
			author_URL = "https://staging.appveen.com/author";
			app_center_URL = "https://staging.appveen.com/appcenter";
		}
		
		else if(url.equalsIgnoreCase("https://bifrost.ds.appveen.com")) {
			author_URL = "https://bifrost.ds.appveen.com/author";
			app_center_URL = "https://bifrost.ds.appveen.com/appcenter";
			
		}
//		extent.setSystemInfo("Selenium Version", "3");
//		extent.setSystemInfo("Environment", "Testing");
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void applyExplicitWaitsUntilElementVisible(WebElement element,int time) throws MalformedURLException {
		
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	

	}
	
	public void applyWaitForDynamicWebElement(By locator,int time) {
		
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void applyExplicitWaitsUntilElementVisible(List<WebElement> element, int time) {
	WebDriverWait wait = new WebDriverWait(driver, time);
	wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	
	@AfterClass
	public void tearDown() throws IOException {
		extent.flush();
//		driver.quit();
	}

	

	@AfterSuite
	public void openReport() {
//		SwitchWindow.openReportTab(driver);
//		driver.get(DefineConstants.PROJECT_PATH + "report" + "/" + "extent.html");
	}
}