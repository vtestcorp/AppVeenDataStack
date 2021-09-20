package stepdefinitions;

import java.net.MalformedURLException;


import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;
import helperMethods.WaitTypes;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageModules.Module_DesignTestCases;
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
	@Given("Group {string} does not exist")
	public void group_does_not_exist(String groupName) throws Exception {
//		Thread.sleep(30000);
	    design.groupexist(groupName);
	}

	@Then("Create new group {string}")
	public void create_new_group(String groupName) throws Exception {
	 design.createGroup(groupName);
	}
	
	@Then("assign appcenter permissions for {string} dataservice")
	public void assign_appcenter_permissions_for_dataservice(String string) {
	   
	}

	@Then("Assign appcenter permissions for {string} dataservice to {string}")
	public void assign_appcenter_permissions_for_strings_text_dataservice(String dataServiceName,String user) throws Exception {
	    design.assignPermission(dataServiceName,user);
	}
	
	@Then("^Add record \"(.*?)\" to the data service for Date$")
	public void add_record_to_the_data_service_for_Date(String jsonFile) throws Exception {
		design.addRecordForDate(jsonFile);
	}
	

	@Then("^Add record \"(.*?)\" to the data service$")
	public void add_record(String string) throws Exception {
	    design.addRecord(string);
	}
	
	@Then("Add record {string} {string} to the data service")
	public void add_record_to_the_data_service(String string, String string2) throws Exception {
	    design.addNewRecord(string,string2);
	}
	
	@Then("^Add record \"(.*?)\" to the data service for File Type$")
	public void add_record_to_File_Type(String string) throws Exception {
	    design.addNewRecordForFile(string);
	}
	@Then("^Add record \"(.*?)\" to the boolean data service$")
	public void add_record_to_the_boolean_data_service(String string) throws InterruptedException, MalformedURLException {
	    design.addRecordInBoolean(string);
	}

	@Then("Expect error {string} on label {string}")
	public void expect_error_on_label(String string, String string2) throws Exception {
	    design.expectError(string,string2);
	}
	
	
	
	@Then("^Add record \"(.*?)\" to the group$")
	public void add_record_to_Group(String string) throws Exception {
		design.addRecordForGroup(string);
	}

	@Then("Save button is disabled")
	public void save_button_is_disabled() {
	    design.saveButtonIsDisable();
	}
	
	@Then("^Add records \"(.*?)\"$")
	public void add_records(String string) throws Exception {
	    design.addRecordForRepeatedId();
	}

	@Then("^Add record \"(.*?)\" to the Location data service$")
	public void add_record__toLocation(String string) throws InterruptedException {
		design.addRecordForLocation(string);
	}
	
	
	@Then("^Add record \"(.*?)\" to the user data service$")
	public void add_data_to_theUser(String string) throws InterruptedException {
		  design.addRecordForUser(string);
	}
    
	@Then("^Add record \"(.*?)\" to the stringRichText data service$")
	public void add_data_to_thestringRichText(String string) throws InterruptedException, MalformedURLException {
		  design.addRecordForRichText(string);
	}
	
	@Then("Expect error {string} on save")
	public void expect_error_on_save(String errorMessage) throws InterruptedException {
	    design.expectErrorOnSave(errorMessage);
	}
	
	@Then("^Add records to \"(.*?)\"$")
	public void add_records_to_And_Expect_error_on_save(String json) throws Exception {
	    design.addNewRecords();
	}
	
	@Then("^Update record \"(.*?)\" with \"(.*?)\" to the data service$")
	public void update_record_with_to_the_data_service(String id, String jsonFile) throws Exception {
	    design.updateRecord(id,jsonFile);
	}
	

	@Then("^Update record \"(.*?)\" with \"(.*?)\" to the data service for group$")
	public void update_record_with_to_the_data_service_for_group(String id, String jsonFile) throws Exception {
	    design.updateRecordForGroup(id,jsonFile);
	}
	
	@Then("^Update record \"(.*?)\" with \"(.*?)\" to the stringRichText data service$")
	public void update_record_with_to_the_data_RichTextservice(String id, String jsonFile) throws Exception {
	    design.updateRecordForRichText(id,jsonFile);
	}
	

	@Then("^Update record \"(.*?)\" with \"(.*?)\" to the boolean data service$")
	public void update_record_with__boolean_data_service(String id, String jsonFile) throws InterruptedException, MalformedURLException {
		 design.updateRecordForBooelan(id, jsonFile);
	}

	@Then("^Update record \"(.*?)\" with \"(.*?)\" to the data service for Date Type$")
	public void update_record_with_to_the_data_service_for_Date_Type(String id, String jsonFile) throws Exception {
	    design.updateRecordForDate(id,jsonFile);
	}
	
	@Then("^Update record \"(.*?)\" with \"(.*?)\" to the data service for File Type$")
	public void update_data_to_the_data_service_for_file(String id, String jsonFile) throws Exception {
		design.updateDataForFile(id,jsonFile);
	}
	
	@Then("Update records {string} with {string} to the data service")
	public void update_records_with_to_the_data_service(String id, String attribute) throws Exception {
		design.updateRecords(id,attribute);
	}
	
	
	@Then("^Update record \"(.*?)\" with \"(.*?)\" to the User$")
	public void update_record_with_User_data_service(String id, String jsonFile) throws Exception {
	    design.updateRecordforUser(id, jsonFile);
	}
	
	@Then("^Update record \"(.*?)\" with \"(.*?)\" to the Location$")
	public void update_record_with_dsLocation_data_service(String id, String jsonFile) throws Exception {
	    design.updateLocationRecord(id, jsonFile);
	    
	}
	
	@Then("Fetch record {string} from the data service")
	public void fetch_record_from_the_data_service(String id) throws Exception {
	    design.fetchRecord(id);
	}
	
	@Then("^Match this Currency data to \"(.*?)\"$")
	public void match_this_Currency_data(String jsonFile) throws Exception {
	 design.matchDataCurrency(jsonFile);
	}
	
	@Then("^Match this GROUP data to \"(.*?)\"$")
	public void match_this_GROUP_data(String jsonFile) throws Exception {
	   design.matchGroupData(jsonFile);
	}
	
	@Then("^Match this Library data to \"(.*?)\"$")
	public void match_this_Library_data(String jsonFile) throws Exception {
	   design.matchGroupData(jsonFile);
	}
	
	@Then("^Match it to \"(.*?)\" Date Type$")
	public void match_this_Date_data(String jsonFile) throws Exception {
	   design.matchDateData(jsonFile);
	}
	
	@And("Record must not exist")
	public void record_must_not_exist() throws Exception {
		design.recordMustNotExist();
	}
	@Then("^Match it to \"(.*?)\"$")
	public void match_it_to(String jsonFile) throws Exception {
		design.matchToRecord(jsonFile);	
}
	
	@Then("^Match it to \"(.*?)\" for RichText$")
	public void match_it_to_RichText(String jsonFile) throws Exception {
		design.matchToRecordToRichText(jsonFile);	
}
	
	
	@Then("^Match it to \"(.*?)\" for Relation$")
	public void match_this_Relation_to(String jsonFile) throws Exception {
//		design.matchRelationData(jsonFile);
		design.matchToRecord(jsonFile);	
}
	
	@Then("^Match this Location data to \"(.*?)\"$")
	public void match_this_Location_to(String jsonFile) throws Exception {
		design.matchLocationData(jsonFile);
}
	
	
	@Then("^Match it to \"(.*?)\" for File type$")
	public void match_it_to_for_File_type(String jsonFile) throws Exception {
		design.matchToRecordForFileType(jsonFile);	
}
	@Then("^Match it to \"(.*?)\" boolean$")
	public void match_it_to__boolean(String string) throws MalformedURLException {
		design.matchRecordforBoolean(string);
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
	@Then("Deleting from listing page")
	public void deleting_from_listing_page() {
	    
	}
	
	@Then("deleting from view page")
	public void deleting_from_view_page() {
	    
	}

	@Then("User log out from AppCenter")
	public void user_logs_out_of_AppCenter() {
	    driver.quit();
	}

}
