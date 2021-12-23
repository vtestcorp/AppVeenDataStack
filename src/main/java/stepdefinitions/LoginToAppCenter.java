package stepdefinitions;

import java.net.MalformedURLException;

import com.aventstack.extentreports.ExtentTest;
import base.BaseClass;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageModules.LoginAppCenter;

public class LoginToAppCenter extends BaseClass{
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
	

	@Given("User enters {string} and {string} in AppCenter login page")
	public void user_enters_username_and_password_in_AppCenter_Url(String userName,String password) throws Exception {
			loginAppCenter.enterUserNameAndPassword(userName, password);
	}

	@Given("Verify User has Logged in Successfully")
	public void verify_User_Login_Successfully_in_Appcenter_Url() throws Exception {
		loginAppCenter.verifyUserLoginSuccesfullyToAppCenter();
	}
	
	@Given("Data service {string}")
	public void data_service(String dataService) throws Exception {
		loginAppCenter.dataService(dataService);
	}
	
	
	
	@Then("Upload file {string} to the import page of data service")
	public void import_data_service(String file) throws Exception {
		loginAppCenter.uploadDatafile(file);
	}
	
//	@Then("Map {string} to the import")
//	public void map_Filesetting(String file) throws Exception {
//		loginAppCenter.uploadDatafile(file);
//	}
	
	@Then("Add data to the data service for Collection")
	public void add_data_to_the_data_service_for_Collection() throws Exception {
		loginAppCenter.userEnterDataforCollection();
	}
    
	@Then("Add data to the data service for boolean")
	public void add_data_to_the_data_service_for_boolean() throws InterruptedException, MalformedURLException {
		loginAppCenter.userEnterDataforBoolean();
	}

	@Then("Add data to the data service for Date")
	public void add_data_to_the_data_service_for_Date() throws Exception {
	    loginAppCenter.addDataForDate();
	}
	

	@Then("Add data to the data service")
	public void add_data_to_the_data_service() throws Exception {
		loginAppCenter.userEnterData();
	}
	
	@Then("Add data to the stateModel data service")
	public void add_data_to_the_stateModel_data_service() throws Exception {
		loginAppCenter.userEnterDataForStateModel();
	}
	
	@And("Verify the State is {string}")
	public void verifyState(String text) throws Exception {
		loginAppCenter.verifyState(text);
	}
	
	@Then("Add data to the data service for Relation")
	public void add_data_to_the_data_service_for_Relation() throws Exception {
		loginAppCenter.addDataForRelation();
	}
	
	@Then("^Add data to the stringRichText data service$")
	public void add_data_to_the_richTextdata_service() throws Exception {
		loginAppCenter.userEnterDataForRichText();
	}
	
	@Then("Add data to the data service for Group")
	public void add_data_to_the_data_service_for_Group() throws Exception {
	    loginAppCenter.addDataForGroups();
	}
	
	@Then("Add data to the data service for Location")
	public void add_data_to_the_data_service_for_Location() throws Exception {
		loginAppCenter.userEnterDataInLocationField();
	}
	
	@Then("^Add data to the data service for user$")
	public void add_data_to_the_data_service_for_user() throws InterruptedException, MalformedURLException {
	   loginAppCenter.userEnterDataInUserField();
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
	public void data_service_users(Scenario s) {
	    
	}
	
}
