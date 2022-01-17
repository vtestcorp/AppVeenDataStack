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
	
	@FindBy(xpath="//*[@class='btn btn-link mr-2 p-0' or @id='_id']")
	public List <WebElement> dateFields;

	@FindBy(xpath="//select[@class='text-primary time-select font-weight-bold'][1]")
	public WebElement monthDropDown;
	
	@FindBy(xpath="//select[@class='text-primary time-select font-weight-bold'][2]")
	public WebElement yearDropDown;
	
	@FindBy(xpath="//select[@class='text-primary time-select'][1]")
	public WebElement hourDropDown;
	
	@FindBy(xpath="//select[@class='text-primary time-select'][2]")
	public WebElement minuteDropDown;
	
	@FindBy(xpath="//select[@class='text-primary time-select'][3]")
	public WebElement secondDropDown;
	
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
	
	@FindBy(xpath="//div[@class='list-item-label d-flex align-items-center text-truncate']")
	public WebElement dataServiceName;
	
	@FindBy(xpath="(//*[contains(@class,'form-control')])[1]")
	public WebElement textBox1;
	
	@FindBy(xpath="//input[@type='text' and contains(@class,'form')]")
	public WebElement idTextBox1;
	
	@FindBy(xpath="//*[contains(@class,'form-control')]")
	public List<WebElement> textBoxes;
	
	@FindBy(xpath="//*[contains(@class,'form-control') or @type='checkbox' or @type='file' or contains(@class,'btn btn-link mr-2 p-0') or contains(@class,'searchInput')]")
	public List<WebElement> groupTextBoxes;
	
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
	
	@FindBy(xpath="//textarea[@placeholder='Type here']")
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
	
	@FindBy(id="_editButton")
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

	@FindBy(xpath="//button[contains(@class,'dropdown-toggle')]")
	public WebElement saveDropDown;

	@FindBy(xpath="//span[normalize-space()='ID']/ancestor::div[@class='ag-header-row']/following-sibling::div//input[contains(@class,'border rounded bg-white w-100 ng')]")
	public WebElement idTab;
	
	@FindBy(xpath="//a[@class='ng-star-inserted']")
	public WebElement record;
	
	@FindBy(xpath="//span[text()='No records to display']")
	public WebElement noRecordDisplay;
	
	@FindBy(xpath="//label[starts-with(@class,'label-width d-flex')]")
	public List<WebElement> attributesOnViewPage;
	
	@FindBy(xpath="((//label[starts-with(@class,'label-width d-flex')])/parent::div/following-sibling::odp-view-separator/descendant::div/child::*[last()])/p | //span[@class='value-width ng-star-inserted']")
	public List<WebElement> attributesOnViewPageForRichText;
	

	@FindBy(xpath="//*[contains(@class,'value-wrapper')]//span[(last() and not(contains(@class, 'mr-2')) and not(contains(@class, 'ml-2')))] | //odp-view-date//div[@class='font-weight-bold value-wrapper'] | //span[text()='Raw location']/ancestor::div[contains(@class,'label-wrapper')]/following-sibling::div | //odp-view-user//a[@class='ng-star-inserted']")
	public List<WebElement> attributesOnViewPageForGroups;
	
	@FindBy(xpath="//*[contains(@class,'value-wrapper')]//span[(last() and not(contains(@class, 'mr-2')) and not(contains(@class, 'ml-2')))] | //odp-view-date//div[@class='font-weight-bold value-wrapper'] | //span[text()='Raw location']/ancestor::div[contains(@class,'label-wrapper')]/following-sibling::div | //odp-view-user//a[@class='ng-star-inserted']")
	public List<WebElement> attributesOnViewPageFor;
	
	@FindBy(xpath="//odp-view-control/div/div/label[starts-with(@class,'label-width')]")
	public List<WebElement> attributesOnViewPageForLocation;
	
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
	
	@FindBy(xpath="//input[@class='searchInput pac-target-input' or @id='_id']")
	public List<WebElement> textBoxesLocation;
	
	@FindBy(id = "doneBtn")
	public WebElement doneButton;
	
	@FindBy(xpath = "//div[starts-with(@class,'footer')]//button[@id='cancelBtn']")
	public WebElement cancelButton;
	
	@FindBy(xpath= "//input[@type='checkbox' or @id='_id' ]")
	public List<WebElement> buttons;
	
	@FindBy(id = "_day")
	public WebElement day;
	
	@FindBy(xpath = "//*[starts-with(@class,'tox-edit-area__iframe') or   @id='_id']")
	public List<WebElement> richtextBoxes;
	
	@FindBy(xpath = "//div[@class='ng-star-inserted']//*[contains(@class,'add-new')]")
	public List<WebElement> addNewButtons;
	
	@FindBy(xpath = "//span[contains(@class,'fas fa-cloud')]")
	public WebElement importfile;
	
	@FindBy(xpath = "//label/input[@id='logoFile']")
	public WebElement uploadFile;
		
	@FindBy(xpath = "//select[contains(@class,'form-control')]")
	public WebElement sheetToReadDropdown;
	
	@FindBy(xpath = "//div[normalize-space()='From the top']//input[contains(@class,'form-control')]")
	public WebElement sheetTopRow;
	
	@FindBy(xpath = "//div[normalize-space()='From the bottom']//input[contains(@class,'form-control')]")
	public WebElement sheetBottomRow;
	
	@FindBy(xpath = "//div[normalize-space()='ID']//input[contains(@class,'colDiv')]")
	public WebElement destination;
	
	@FindBy(xpath = "//div[normalize-space()='STR1004']")
	public WebElement source;
	
	@FindBy(xpath = "//span[normalize-space()='Onboarding Status']/following-sibling::span[starts-with(@class,'badge')]")
	public WebElement onBoardingStatus;
	
	@FindBy(xpath = "//span[@class='slider']")
	public WebElement recoredHeader;
	
	@FindBy(xpath = "//button[normalize-space()='Next' and contains(@class,'btn-min-width')]")
	public WebElement next;
	
	@FindBy(xpath = "//a[normalize-space()='here']")
	public WebElement here;
	
	@FindBy(xpath = "//span[contains(@class,'fa-bell')]")
	public WebElement notification;
	
	@FindBy(xpath = "(//div[@class='content'])[1]")
	public WebElement notification_body;
	
	@FindBy(xpath = "//span[normalize-space()='Click here']//input")
	public WebElement clickHere;
		
	@FindBy(xpath = "//span[starts-with(text(),'Valid Records')]/following-sibling::span//u")
	public WebElement validRecords;

}
