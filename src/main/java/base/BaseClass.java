package base;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
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
	public static ExtentHtmlReporter htmlReporter;
	public static String DownloadFilepath, folder, basefold;
	public static int count;

	public static String testData = System.getProperty("testData");
//	public static String testData = "D:\\users111.json";
	public static String browser = System.getProperty("browser");



	@SuppressWarnings("deprecation")
	public void start() {
//		System.out.println(testData);
//		System.out.println(browser);
		if(browser==null) {
			browser="chrome";
		}
		htmlReporter = config.ExtentReports.createInstance("report/extent.html");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		if (browser.equalsIgnoreCase("chrome")) {
			// defineProperties defineBrowser = new defineProperties(browser);
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
			
		//	driver = new FirefoxDriver();
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("internetexplorer")) {
//			DefineProperties defineBrowser = new DefineProperties(browser);
//			driver = new InternetExplorerDriver(defineBrowser.setIECapability());
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
			driver.manage().window().maximize();
			// driver = new RemoteWebDriver(new URL(url),
			// defineBrowser.SauceLabCapabilities());
		}
		extent.setSystemInfo("Selenium Version", "3");
		extent.setSystemInfo("Environment", "Testing");
	}

	public static WebDriver getDriver() {
		return driver;
	}

	

	@AfterMethod(timeOut = 10000L, alwaysRun = true)
	public void checkResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			atest += "    " + count + "     " + result.getTestClass().getName() + "." + result.getName()
					+ " - Failed\n";
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test Case FAILED", ExtentColor.RED));
			test.fail(result.getThrowable());
			test.addScreenCaptureFromPath(Screenshots.takeScreenshot(driver, result.getMethod().getMethodName()));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			atest += "    " + count + "     " + result.getTestClass().getName() + "." + result.getName()
					+ " - Passed\n";
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		} else {
			atest += "    " + count + "     " + result.getTestClass().getName() + "." + result.getName()
					+ " - Skipped\n";
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.YELLOW));
		}
	}

	@AfterClass
	public void tearDown() throws IOException {
		extent.flush();
//		driver.quit();
	}

	// @AfterSuite
	// public void zip() {
	// try {
	// SendMailForFailedScenarios.SendMail(folder, atest, hypen, space, atest);
	//
	// //zipUtil createZip = new zipUtil("report",
	// ".//report//DetailedReport.zip");
	// slackMessage slackMsg = slackMessage.builder().username("user")
	// .text("Hi,\nExecution status for test cases-\n\n" + hypen + hypen + hypen
	// + "\n"
	// + "| No | " + space + "PackageName.className.TestCaseName" + space + " |
	// status |\n" + hypen + hypen + hypen + "\n" + atest
	// + hypen + hypen + hypen + "\n\n Please check Email for complete report
	// and sample test data, Thanks")
	// .icon_emoji(":zap:").build();
	// slackUtils.sendMessage(slackMsg, System.getProperty("user.dir") +
	// "\\report\\extent.html");
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	@AfterSuite
	public void openReport() {
//		SwitchWindow.openReportTab(driver);
//		driver.get(DefineConstants.PROJECT_PATH + "report" + "/" + "extent.html");
	}
}