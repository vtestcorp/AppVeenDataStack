package stepdefinitions;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import helperMethods.WaitTypes;
import pageModules.LoginAppCenter;
import pageModules.WorkflowsInAppcenterPage;

public class AppcenterWorkflow extends BaseClass{
	
	private WaitTypes applyWait;
	private ExtentTest test;
	public WorkflowsInAppcenterPage workflow;
	
	@Before
	public void initilization() {
		workflow = new WorkflowsInAppcenterPage(driver, test);
	}
	
	
	@Given("Data Service {string}")
	public void data_Service(String string) throws Exception {
	  workflow.dataService(string);
	}
	
	
	@Then("Add data to the data service workflow  for Approval")
	public void add_data_to_the_data_service_workflow_for_Approval() throws Exception {
		workflow.addDataToDataService();
	}
	
	@Then("Verify data is available in the workflow listing page under New Records with status Pending Review")
	public void verify_data_is_available_in_the_workflow_listing_page_under_New_Records_with_status_Pending_Review() {
	   workflow.verifyDataAvailableInWorkflowListingPage();
	}
	
	@Then("User logs out of AppCenter")
	public void user_logs_out_of_AppCenter() {
		
		workflow.logoutFromAppcenter();
	}

	@Given("User navigate to AppCenter login page")
	public void user_navigate_to_AppCenter_login_page() {
		
		workflow.userNavigateToAppCenter();
	}

	@Then("Approve the Record created in previous step")
	public void approve_the_Record_created_in_previous_step() throws Exception {
		workflow.approveTheRecord();
	    
	}

	@Then("Verify data is available in the workflow listing page under New Records with status Approved")
	public void verify_data_is_available_in_the_workflow_listing_page_under_New_Records_with_status_Approved() throws Exception {
	    workflow.verifyDataIsAvailableWithStatusApproved();
	}
	
	@Then("Add data to the data service and save as draft")
	public void add_data_to_the_data_service_and_save_as_draft() throws Exception {
	    workflow.addDataAndSaveAsDraft();
	}

	@Then("Verify data is available in the workflow listing page under Draft with status Draft")
	public void verify_data_is_available_in_the_workflow_listing_page_under_Draft_with_status_Draft() {
		workflow.verifyDataAvailableUnderDraft();;
	  
	}
	
	@Then("Update the above draft record and Proceed")
	public void update_the_above_draft_record_and_Proceed() throws Exception {
	    workflow.updateDraftRecord();
	}
	@Then("Add data to the data service and save as drafts")
	public void add_data_to_the_data_service_and_save_as_drafts() {
	    workflow.editDataService();
	}
	
	@Then("Approve the Record updated in previous step from Update Record tab")
	public void approve_the_Record_updated_in_previous_step_from_Update_Record_tab() {
	    workflow.approveTheRecordFromUpdateRecordTab();
	}

}
