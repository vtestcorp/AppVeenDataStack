package stepdefinitions;

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

	private WebDriver driver;
	private WaitTypes applyWait;
	private ExtentTest test;
	LoginPage loginPage;

	@Given("User Navigate to LogIn Page")
	public void user_Navigate_to_LogIn_Page() throws Exception {
		// test = extent.createTest("TC_01_CreateDS", "Create DS with attributes");

		loginPage = new LoginPage(driver, test);
		loginPage.loginToPage();
	}

	@Given("User enters username and password")
	public void user_enters_UserName_and_Password(DataTable table) throws Exception {
		List<List<String>> userList = table.asLists(String.class);
		for (List<String> e : userList) {
			System.out.println(e.get(0) + "------------");
			System.out.println(e.get(1) + "------------");

			loginPage.enterUserNameAndPassword(e.get(0), e.get(1));
		}


	}

	@Then("Verify User Login Successfully")
	public void message_displayed_Login_Successfully() throws Exception {
		loginPage.verifyListOfDataServices();
	}

	@Then("Create new Data Service {string}")
	public void create_new_Data_Service(String dataService) throws Exception {
		String dataName="C:\\Users\\DELL\\eclipse-workspace\\DataStack\\Test_Data" + "\\" + ""+dataService+".json";
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

}
