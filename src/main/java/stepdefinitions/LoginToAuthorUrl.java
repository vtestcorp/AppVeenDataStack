package stepdefinitions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import base.BaseClass;
import helperMethods.JsonUtils;
import helperMethods.WaitTypes;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageModules.LoginPage;

public class LoginToAuthorUrl extends BaseClass {

	public WaitTypes applyWait;
	public LoginPage loginPage ;
	public static String data_Service;
	public static String data_library;

	

	@Before("@Author")
	public void setUp() {
		start();
		loginPage = new LoginPage(driver);
	}
	
	@Before
	public void initilization() {
		loginPage = new LoginPage(driver);
	}
	
	
	@Given("User navigate to Author login page")
	public void user_Navigate_to_LogIn_Page() throws Exception {
		loginPage.loginToPage();
		}

	
	@Given("User enters {string} and {string} in Author login page")
	public void user_enters_UserName_and_Password(String username,String password) throws Exception {
			loginPage.enterUserNameAndPassword(username, password);
	}

	@Then("Verify User has Logged in successfully in Author Url")
	public void message_displayed_Login_Successfully() throws Exception {
		loginPage.verifyListOfDataServices();
	}
	
	@Given("Library {string} exist")
	public void data_Library_exist(String library) throws Exception {
		 loginPage.verifyLibraryExist(library);
	}
	
	@Given("Data service {string} exists")
	public void data_service_exists(String dataService) throws Exception {
		 loginPage.verifyDataServiceExist(dataService);
	}
	
	@Given("Delete {string} data service")
	public void delete_DataService(String dataService) throws Exception {
		 loginPage.deleteDataService(dataService);
	}

	@Then("Remove the data service")
	public void remove_the_data_service() throws Exception {
	      loginPage.deleteGivenDataService();
	}

	@Given("Data service {string} does not exist")
	public void data_service_does_not_exist(String string) {
		loginPage.verifyDataServiceDoesNotExist();
	}
	
	@Given("Create new data service {string} for Relation")
	public void create_DataService_For_Relation(String string) throws Exception {
		loginPage.createDataServiceForRelation(string);
	}
	
	@Then("Create new data service {string}")
	public void create_new_Data_Service(String dataService) throws Exception  {
			data_Service=dataService;
		
		String dataName=path+"\\testData" + "\\" + dataService+".json";
		
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
	@Then("Create new data service {string} for State_Model")
	public void create_DateServiceFor_StateModel(String dataService) throws Exception {
          loginPage.createDataServiceForStateModel(dataService);		

	}
	
	@Then("Add attributes {string} and type {string}")
	public void addAttributeWithType(String attributeName , String attributeType) throws InterruptedException {
       loginPage.addAttributes(attributeName, attributeType);
	}
	
	@Given("Click on Experience tab to create a State Model")
	public void clickOnExperienceTab() throws InterruptedException {
		loginPage.clickOnExperienceTab();
	}
	@Then("Create a State Model for the field {string}")
	public void configureStateModelStatus(String status) throws InterruptedException {
		loginPage.configureStateModel(status);
	}
	
	
	@Then("Create states {string}")
	public void createStateName(String stateName) throws InterruptedException {
       loginPage.createStates(stateName);
	}
	
//	@And("Set Current State {string} and {string} and Save")
//	public void setCurrentState(String fromState, String nextState) throws InterruptedException {
//       loginPage.setStates(fromState,nextState);
//	}
	
	@And("Set Current State with from state to next state")
	public void setCurrentStateFromToNext(DataTable datatable) throws InterruptedException {
       loginPage.setStates(datatable);
	}
	
	@Then("Save and Deploy data service")
	public void saveAndDeploy() throws InterruptedException {
       loginPage.saveAndDeploy();
	}
	
	@Given("Group sampleGroup {string} exists")
	public void group_sampleGroup_exists(String groupName) throws Exception {
		loginPage.verifyGroupExists(groupName);
	    
	}

	@Then("Remove group {string}")
	public void remove_group(String groupName) throws Exception {
	   loginPage.removeGroup(groupName);
	}
	
	@Given("Group sampleGroup {string} does not exists")
	public void group_sampleGroup_does_not_exists(String groupName) throws Exception {
	   loginPage.verifyGroupDoesNotExist(groupName);
	}
	
	@Given("Data service {string}  exists")
	public void data_service_exists1(String dataService) {
	    loginPage.dataServiceExists(dataService);
	}
	
	@Then("Create group {string} and enable role {string} of {string}")
	public void create_group_and_enable_role_of(String groupName, String role, String dataservice) throws Exception {
		loginPage.createGroupAndEnableRole(groupName,role,dataservice);
	  
	}
	@Given("Group {string} exists")
	public void group_exists(String groupName) {
		loginPage.groupExists(groupName);
	}

	@Given("User {string} exists")
	public void user_exists(String userEmail) throws Exception {
	   loginPage.VerifyUserExists(userEmail);
	}
	
	@Given("Add {string} and {string} if not present")
	public void add_if_not_present(String username,String password) throws Exception {
		loginPage.addUser(username,password);
	}

	

	@Then("Add {string} to {string}")
	public void add_to(String userEmail, String group) throws Exception {
	    loginPage.addUserToGroup(userEmail,group);
	}

	@Given("User logged into Author")
	public void user_logged_into_Author() throws Exception {
	    loginPage.logOutFromAuthor();
	}

	@Then("User logs out of Author")
	public void user_logs_out_of_Author() throws Exception {
		loginPage.logsOutOfAuthor();
	}
	
	@And("Create Data Service {string}")
	public void create_new_Data_service(String dataServiceName) throws Exception {
		loginPage.createDataService(dataServiceName);
	}

	@Then("Search given Data Service")
	public void search_given_Data_Service() throws Exception {
		loginPage.searchDataService();
		
		loginPage.logsOutOfAuthor();
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
