package stepdefinitions;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import helperMethods.WaitTypes;
import io.cucumber.datatable.DataTable;
import pageModules.LoginAppCenter;
import pageModules.LoginPage;

public class LoginToAppCenter extends BaseClass{
//	private WebDriver driver;
	private WaitTypes applyWait;
	private ExtentTest test;
	public LoginAppCenter loginAppCenter;
	
	@Before
	public void initilization() {
		loginAppCenter = new LoginAppCenter(driver, test);
	}
	
	@Given("User navigate to AppCenter loginpage")
	public void user_navigate_to_AppCenter_loginpage() {
		loginAppCenter.loginToAppCenterPage();
	}
	
//	@Given("User navigate to AppCenter login page")
//	public void user_Navigate_to_AppCenter_LogIn_Page() {
//		loginAppCenter.loginToAppCenterPage();
//	    
//	}

	@Given("User enters {string} and {string} in AppCenter login page")
	public void user_enters_username_and_password_in_AppCenter_Url(String userName,String password) throws Exception {
		
			loginAppCenter.enterUserNameAndPassword(userName, password);
		
	}

	@Given("Verify User has Logged in Successfully")
	public void verify_User_Login_Successfully_in_Appcenter_Url() throws Exception {
		//dataStackLogo
		loginAppCenter.verifyUserLoginSuccesfullyToAppCenter();
	    
	}
	
	@Given("Data service {string}")
	public void data_service(String dataService) throws Exception {
		loginAppCenter.dataService(dataService);
	 
	}
	
	
	
	@Then("Add data to the data service for Collection")
	public void add_data_to_the_data_service_for_Collection() throws InterruptedException {
		loginAppCenter.userEnterDataforCollection();
	}


	
	@Then("Add data to the data service")
	public void add_data_to_the_data_service() throws Exception {
		
		loginAppCenter.userEnterData();
	}
	
	@Then("Add data to the data service for Group")
	public void add_data_to_the_data_service_for_Group() throws Exception {
	    loginAppCenter.addDataForGroups();
	}
	
	@Then("Add data to the data service for Location")
	public void add_data_to_the_data_service_for_Location() throws InterruptedException {
		loginAppCenter.userEnterDataInLocationField();
	}


	
	@Then("Add data to the data service for File Type")
	public void add_data_to_the_data_service_for_file() throws Exception {
	    loginAppCenter.addDataForFile();
	}
	
	@Then("Add data to the data service for Library Type")
	public void add_data_to_the_data_service_for_Library_Type() throws Exception {
		loginAppCenter.addDataForGroups();;
	}
	
	@Then("On the listing page it should show the total count of documents as {int}")
	public void on_the_listing_page_it_should_show_the_total_count_of_documents_as(Integer documentCount) throws Exception {
	   
		loginAppCenter.verifyTotalCountOfDocuments(documentCount);
	}
	
	@Then("Remove {int} record from the data service")
	public void remove_record_from_the_data_service(Integer int1) throws Exception {
		loginAppCenter.removeRecords(int1);
	}

	@Then("Remove {int} records from the data service")
	public void remove_records_from_the_data_service(Integer int1) throws Exception {
		loginAppCenter.removeRecords(int1);
	}
	
	@Given("Data service users")
	public void data_service_users() {
	    
	}
	
	
	
//	@Given("User enters data in Data Service")
//	public void user_enters_data_in_Data_Service() throws Exception {
//		loginAppCenter.userEnterData(LoginToAuthorUrl.data_Service);
//	}

}
