package stepdefinitions;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;

import base.BaseClass;
//import cucumber.api.java.Before;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
import helperMethods.WaitTypes;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import listeners.ExtentReportListener;
import pageModules.LoginAppCenter;
import pageModules.WorkflowsInAppcenterPage;

public class AppcenterWorkflow extends BaseClass{
	
	private WaitTypes applyWait;
	private ExtentTest test;
	public WorkflowsInAppcenterPage workflow;
	public ExtentReportListener test1;
	public ExtentReports extent;
	public ExtentTest logInfo=null;
	
	@Before
	public void initilization() {
		workflow = new WorkflowsInAppcenterPage(driver, test);
		test1=new ExtentReportListener();
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
	public void user_logs_out_of_AppCenter() throws IOException, ClassNotFoundException {
		ExtentReportListener.test = extent.createTest(Feature.class, "DS GROUP");
		ExtentReportListener.test=ExtentReportListener.test.createNode(Scenario.class, "Log out AppCenter");						
		logInfo=ExtentReportListener.test.createNode(new GherkinKeyword("Given"), "Successful log out AppCenter page");
		workflow.logoutFromAppcenter();
		logInfo.addScreenCaptureFromPath(ExtentReportListener.captureScreenShot(driver));
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
	public void add_data_to_the_data_service_and_save_as_drafts() throws Exception {
	    workflow.editDataService();
	}
	
	@Then("Approve the Record updated in previous step from Update Record tab")
	public void approve_the_Record_updated_in_previous_step_from_Update_Record_tab() {
	    workflow.approveTheRecordFromUpdateRecordTab();
	}
	
	@Then("Add data to the data service workflow for Rejection")
	public void add_data_to_the_data_service_workflow_for_Rejection() throws Exception {
		workflow.addDataToDataService();
	}
	
	@Then("Reject the Record created in previous step")
	public void reject_the_Record_created_in_previous_step() throws Exception {
	   workflow.rejectTheRecord();
	}
	
	@Then("Add data to the data service workflow for Rework")
	public void add_data_to_the_data_service_workflow_for_Rework() throws Exception {
	   workflow.addDataToDataService();
	}

	@Then("Rework the Record created in previous step")
	public void rework_the_Record_created_in_previous_step() throws Exception {
	    workflow.reworkTheRecord();
	}
}
