package stepdefinitions;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import helperMethods.WaitTypes;
import pageModules.Module_DesignTestCases;
import pageModules.WorkflowsInAppcenterPage;

public class DesignTestCases extends BaseClass{
	
	
	private WaitTypes applyWait;
	private ExtentTest test;
	public Module_DesignTestCases design;
	
	@Before
	public void initilization() {
//		start();
		design = new Module_DesignTestCases(driver, test);
	}

	@Given("Data service should be saved and deployed")
	public void data_service_should_be_saved_and_deployed() throws Exception {
		
		design.dataServiceShouldDeployed();
	   
	}
	@Given("All attributes and properties should be same as user declared")
	public void all_attributes_and_properties_should_be_same_as_user_declared() {
	    
	}
	
	@Given("Record should be displayed in view mode")
	public void record_should_be_displayed_in_view_mode() {
	    
	}

	@Given("Service should be deployed with newlt added attribute")
	public void service_should_be_deployed_with_newlt_added_attribute() {
	    
	}
	@Given("Service should be deployed with newly added attribute")
	public void service_should_be_deployed_with_newly_added_attribute() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("Record should be displayed in view mode with user entered values")
	public void record_should_be_displayed_in_view_mode_with_user_entered_values() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Given("Group {string} does not exist")
	public void group_does_not_exist(String groupName) throws Exception {
	    design.groupexist(groupName);
	}

	@Then("Create new group {string}")
	public void create_new_group(String groupName) throws Exception {
	 design.createGroup(groupName);
	}

	@Then("Assign appcenter permissions for {string} dataservice to {string}")
	public void assign_appcenter_permissions_for_strings_text_dataservice(String dataServiceName,String user) throws Exception {
	    design.assignPermission(dataServiceName,user);
	}
	
	@Then("Add record {string} to the data service")
	public void add_record_to_the_data_service(String string) {
		
		System.out.println(string);
	}

	@Then("^Add record \"(.*?)\" to the data service$")
	public void add_record(String string) throws Exception {
	    design.addRecord(string);
	}
	
	@Then("Add record {string} {string} to the data service")
	public void add_record_to_the_data_service(String string, String string2) throws Exception {
	    design.addNewRecord(string,string2);
	}

	@Then("Expect error {string} on label {string}")
	public void expect_error_on_label(String string, String string2) throws Exception {
	    design.expectError(string,string2);
	}

	@Then("Save button is disabled")
	public void save_button_is_disabled() {
	    design.saveButtonIsDisable();
	}
	
	@Then("Add records {string} {string} {string} {string} {string} {string} {string} {string}")
	public void add_records(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8) throws Exception {
	    design.addRecordForRepeatedId();
	}
	
	@Then("Expect error {string} on save")
	public void expect_error_on_save(String string) {
	    
	}
	
	@Then("Add records to {string} {string} {string} {string} {string} {string} {string} {string}")
	public void add_records_to_And_Expect_error_on_save(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8) throws Exception {
	    design.addNewRecords();
	}
	
	@Then("^Update record \"(.*?)\" with \"(.*?)\" to the data service$")
	public void update_record_with_to_the_data_service(String id, String jsonFile) throws Exception {
	    design.updateRecord(id,jsonFile);
	}
	
	@Then("Update records {string} with {string} to the data service")
	public void update_records_with_to_the_data_service(String id, String attribute) throws Exception {
		design.updateRecords(id,attribute);
	}
	
	@Then("Fetch record {string} from the data service")
	public void fetch_record_from_the_data_service(String id) throws Exception {
	    design.fetchRecord(id);
	}
	
	@Then("Match this Currency data to \"(.*?)\"")
	public void match_this_Currency_data(String jsonFile) throws Exception {
	 design.matchDataCurrency(jsonFile);
	}
	
	@And("Record must not exist")
	public void record_must_not_exist() throws Exception {
		
		design.recordMustNotExist();
	}
	@Then("Match it to \"(.*?)\"")
	public void match_it_to(String jsonFile) throws Exception {
		design.matchToRecord(jsonFile);	
}
	
	@Then("Fetch record by searching {string} with {string} from the data service")
	public void fetch_record_by_searching_with_from_the_data_service(String string, String string2) throws Exception {
		design.fetchRecordBySearchingData(string,string2);
	   
	}
	
	
	@Then("Delete record {string} from the data service")
	public void delete_record_from_the_data_service(String id) {
	  	    design.deleteRecord(id);
	  	   
	}
	
	@Then("Delete record by searching {string} with {string} from the data service.")
	public void delete_record__from_the_data_service(String dsStringText1001 ,String dsStringText1002) throws InterruptedException {
	    design.deleteRecordWithLabel( dsStringText1001 , dsStringText1002);
	}
	


	@Then("Delete record by searching {string} with {string} from the data service")
	public void delete_record_by_searching_with_from_the_data_service(String string, String string2) throws Exception {
		design.deleteRecordFromViewPage(string , string2);
	}
	@Then("deleting from listing page")
	public void deleting_from_listing_page() {
	    
	}
	
	@Then("deleting from view page")
	public void deleting_from_view_page() {
	    
	}

	
	
	

}
