package stepdefinitions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.BaseClass;
import config.DefineConstants;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import helperMethods.JsonUtils;
import helperMethods.Screenshots;
import helperMethods.WaitTypes;
import io.cucumber.datatable.DataTable;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageModules.LoginPage;

public class LoginToAuthorUrl extends BaseClass {

//	public WebDriver driver;
	public WaitTypes applyWait;
//	public ExtentTest test;
	public LoginPage loginPage ;
	public static String data_Service;

	@Before("@Author")
	public void initilization() {
		start();
	}
	
	@Given("User Navigate to LogIn Page")
	public void user_Navigate_to_LogIn_Page() throws Exception {
		// test = extent.createTest("TC_01_CreateDS", "Create DS with attributes");

		loginPage = new LoginPage(driver, test);
		loginPage.loginToPage();
	}

	@Given("User enters {string} and {string} in the Author login page")
	public void user_enters_UserName_and_Password(String username,String password) throws Exception {


			loginPage.enterUserNameAndPassword(username, password);
	}

	@Then("Verify User has Logged in Successfully")
	public void message_displayed_Login_Successfully() throws Exception {
		loginPage.verifyListOfDataServices();
	}

	@Then("Create new Data Service {string}")
	public void create_new_Data_Service(String dataService) throws Exception  {
//		if(!dataService.equals(null)) {
			data_Service=dataService;
//		}
		
		String dataName="C:\\Users\\DELL\\eclipse-workspace\\DataStack\\testData" + "\\" + ""+dataService+".json";
		System.out.println(dataName);
		
		try {
			FileReader reader = new FileReader(dataName);
		}
		catch(FileNotFoundException file) {
			try {
				FileReader reader = new FileReader(testData);
				dataName=testData;
			}
			catch(Exception file1) {
				System.err.println("Data Service file not found");
				
			}
		}
		
		loginPage.createNewDataServices(JsonUtils.getArrayValues(dataName, "definition"),dataService);
		
	}

	@And("Create Data Service {string}")
	public void create_new_Data_service(String dataServiceName) throws Exception {
		loginPage.createDataService(dataServiceName);
	}

	@Then("Search given Data Service")
	public void search_given_Data_Service() throws Exception {
		loginPage.searchDataService();
	}

	@Then("Delete Given Data Service")
	public void delete_Given_Data_Service() throws Exception {
		Thread.sleep(3000);
		loginPage.deleteDataService();
		
	}
	
	@Then("Clone Given Data Service")
	public void clone_Given_Data_Service() throws Exception {
		loginPage.cloneGivenDataService();
		
	}
	
	
	
	@Given("User gives permission to Data Service")
	public void user_gives_permission_to_Data_Service() throws Exception {
		loginPage = new LoginPage(driver, test);
	  loginPage.connectAuthorToAppcenter(data_Service);
	}

}
