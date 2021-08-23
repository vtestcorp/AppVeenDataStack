package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class Object_AppCenterPage extends BaseClass{
	
	public Object_AppCenterPage() {
		PageFactory.initElements(driver, this);
	}
	
	

	@FindBy(xpath="//input[@id='username']")
	public WebElement username;

	@FindBy(xpath="//span[normalize-space()='Next']")
	public WebElement nextButton;

	@FindBy(xpath="//input[@placeholder='Password']")
	public WebElement password;

	@FindBy(xpath="//button[@type='submit']")
	public WebElement loginButton;
	
	@FindBy(xpath="//span[normalize-space()='Login']")
	public WebElement login;

	@FindBy(xpath="//button[@id='addDataBtn']")
	public WebElement addDataButton;

	@FindBy(xpath="(//div[starts-with(@class,'logo')])[1]")
	public WebElement dataStackLogo;


	
	@FindBy(xpath="//*[contains(@class,'form-control')]")
	public List<WebElement> textBoxes;
	
	@FindBy(xpath="//div[starts-with(@class,'d-flex flex-column align')]")
	public List<WebElement> errorMessages;

	@FindBy(xpath="//span[normalize-space()='Save & create another']")
	public WebElement saveAndCreateAnother;
	
	@FindBy(xpath="//button[@id='saveAndCreateBtn']")
	public WebElement proceedAndCreateAnother;
	
	@FindBy(xpath="//span[normalize-space()='Proceed']")
	public WebElement proceed;
	
	@FindBy(xpath="//textarea[@id='comments']")
	public WebElement comments;
	
	@FindBy(xpath="//button[normalize-space()='Submit']")
	public WebElement submit;
	
	@FindBy(xpath="//div[starts-with(@class,'user-details')]")
	public WebElement userDetails;
	
	@FindBy(xpath="//span[normalize-space()='Logout']")
	public WebElement logout;

	@FindBy(xpath="//span[normalize-space()='Save']")
	public WebElement save;

	@FindBy(xpath="//label[contains(text(),'Showing')]")
	public WebElement documentList;

	@FindBy(xpath="//div/div[starts-with(@class,'round-check')]")
	public WebElement checkbox;

	@FindBy(xpath="//*[@id='deleteRecordBtn']/span[2]")
	public WebElement delete;

	@FindBy(xpath="//button[normalize-space()='Yes']")
	public WebElement yes;
	
	@FindBy(xpath="//span[starts-with(@class,'odp-workflow')]")
	public WebElement workflowTab;
	
	@FindBy(xpath="//a[@class='text-primary hover font-bold ng-star-inserted']")
	public List<WebElement> workflowsId;
	
	@FindBy(xpath="//button[normalize-space()='Respond']")
	public WebElement respond;
	
	@FindBy(xpath="//span[normalize-space()='Approve']")
	public WebElement approve;
	
	@FindBy(xpath="//span[normalize-space()='Approved']")
	public WebElement approved;
	
	@FindBy(xpath="//span[normalize-space()='Respond']")
	public WebElement respond1;
	
	
	@FindBy(xpath="//span[normalize-space()='Reject']")
	public WebElement reject;
	
	@FindBy(xpath="//button[normalize-space()='Reject']")
	public WebElement rejectButton;
			
	@FindBy(xpath="//span[normalize-space()='Rework']")
	public WebElement rework;
	
	@FindBy(xpath="//button[normalize-space()='Rework']")
	public WebElement reworkButton;
	
	@FindBy(xpath="//button[starts-with(@class,'btn respond-btn rework')]/span")
	public WebElement rework1;
	
	
	@FindBy(xpath="//textarea[@placeholder='Enter comments to justify your response. Attach document if necessary']")
	public WebElement enterApproveComment;
	
	@FindBy(xpath="//textarea[@placeholder='Comments are required to justify your response. Attach document if necessary.']")
	public WebElement enterRejectComment;
	
	
	@FindBy(xpath="//span[@class='font-bold text-dark ml-2 ng-star-inserted']")
	public WebElement workflowList;
	
	@FindBy(xpath="//span[normalize-space()='Respond']")
	public List<WebElement> respondWorkflows;
	
	@FindBy(xpath="//button[normalize-space()='Approve']")
	public WebElement approveButton;
	
	@FindBy(xpath="//span[@class='text-muted ml-2 ng-star-inserted']")
	public WebElement approveWorkFlowList;
	
	@FindBy(xpath="//span[normalize-space()='Save as draft']")
	public WebElement saveAsDraft;
	
	@FindBy(xpath="//textarea[starts-with(@class,'form-control')]")
	public WebElement saveAsDraftComments;
	
	@FindBy(xpath="//span[normalize-space()='Draft']")
	public WebElement draftTab;
	
	@FindBy(xpath="//span[@class='text-muted ml-2 ng-star-inserted']")
	public WebElement draftStatus;
	
	@FindBy(xpath="//span[normalize-space()='View']")
	public WebElement viewTab;
	
	@FindBy(xpath="//button[normalize-space()='Proceed']")
	public WebElement proceedButton;
	
	@FindBy(xpath="//span[normalize-space()='New Records']")
	public WebElement newRecordsTab;
	
	@FindBy(xpath="//a[starts-with(@class,'ng')]")
	public WebElement dataServiceList;
	
	@FindBy(xpath="//span[normalize-space()='Edit']")
	public WebElement edit;
	
	@FindBy(xpath="//input[@id='_id']")
	public WebElement idTextBox;
	
	@FindBy(xpath="//input[@id='customerName.firstName']")
	public WebElement firstNameTextBox;
	
	@FindBy(xpath="//a[@class='ng-star-inserted']")
	public WebElement dataService;
	
	@FindBy(xpath="//button[normalize-space()='Review']")
	public WebElement review;
	
	@FindBy(xpath="//button[@id='saveBtn']")
	public WebElement saveButton;
	
//	@FindBy(xpath="//span[normalize-space()='ID']/ancestor::div[@class='ag-header-row']/following-sibling::div//input[contains(@class,'border rounded bg-white w-100 ng-pristine')]")
//	public WebElement idTab;
	@FindBy(xpath="//span[normalize-space()='ID']/ancestor::div[@class='ag-header-row']/following-sibling::div//input[contains(@class,'border rounded bg-white w-100 ng')]")
	public WebElement idTab;
	
	@FindBy(xpath="//span[text()='No records to display']")
	public WebElement noRecordDisplay;
	
	@FindBy(xpath="//label[starts-with(@class,'label-width d-flex')]")
	public List<WebElement> attributesOnViewPage;
	
	@FindBy(xpath="(//label[starts-with(@class,'label-width d-flex')])/parent::div/following-sibling::odp-view-separator/descendant::div/child::*[last()]")
	public List<WebElement> attributesOnViewPageForGroups;
	
	@FindBy(xpath = "//button[@class='btn btn-link px-3 ng-star-inserted']/parent::div")
	public WebElement deleteButton;
	
	@FindBy(xpath = "//a[@class='ng-star-inserted']")
	public WebElement searchID;
	
	@FindBy(xpath = "//span[text()='Delete']")
	public WebElement deleteBtn;
	
	@FindBy(xpath = "//button[text()='Yes']")
	public WebElement deleteRecord;
	
	
	@FindBy(xpath = "(//input[@class='border rounded bg-white w-100 ng-untouched ng-pristine ng-valid ng-star-inserted'])[2]")
	public WebElement dsSTRINGTEXT1001;
	
	
	@FindBy(xpath = "(//input[@class='border rounded bg-white w-100 ng-untouched ng-pristine ng-valid ng-star-inserted'])[3]")
	public WebElement dsSTRINGTEXT1002;
	
	@FindBy(xpath = "//div[starts-with(@class,'ag-row')]/div[@col-id='_checkbox']")
	public WebElement click_On_Checkbox;
	
	@FindBy(xpath = "//button[@id='deleteRecordBtn']")
	public WebElement click_On_Delete;
	
	@FindBy(xpath = "//button[text()='Yes']")
	public WebElement delete_Record;

	@FindBy(xpath = "//a[@class='ng-star-inserted']")
	public WebElement str1001;
	
	@FindBy(xpath = "//span[text()='Delete']")
	public WebElement delete_ViewRecord;
	
	
	@FindBy(xpath="//input[@class='form-control form-control-sm rounded ng-pristine ng-valid ng-star-inserted ng-touched' or 'searchInput pac-target-input']")
	public List<WebElement> textBoxesLocation;
	
	
	
	@FindBy(xpath= "//input[@type='checkbox' or @id='_id' ]")
	public List<WebElement> buttons;
	
	

}
