package pageModules;

import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import base.BaseClass;
import config.Constants;
import helperMethods.DropDown;
import helperMethods.JavascriptClick;
import helperMethods.JsonUtils;
import helperMethods.Property;
import helperMethods.ScrollTypes;
import helperMethods.SwitchWindow;
import helperMethods.WaitTypes;
import pageObjects.Object_AppCenterPage;

public class WorkflowsInAppcenterPage extends BaseClass{
	
	private WebDriver driver;
	private WaitTypes applyWait;
	public DropDown dropdown;
	public ScrollTypes scroll;
	public static String data_Service;
	public Object_AppCenterPage acp;
	
	public WorkflowsInAppcenterPage(WebDriver driver) {
		this.driver = driver;
		this.applyWait = new WaitTypes(driver);
		dropdown=new DropDown(driver);
		scroll=new ScrollTypes(driver);
		acp=new Object_AppCenterPage();
	}
	
	
	public void dataService(String dataService) throws Exception {
		Thread.sleep(1000);
		data_Service=dataService;
		WebElement data=driver.findElement(By.xpath("//div[contains(text(),'"+dataService+"')]"));
		data.click();
	}
	
	
	public void addDataToDataService() throws InterruptedException {
		
		Thread.sleep(1000);
		try {
			applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		}
		catch(Exception e) {
			Thread.sleep(1000);
			JavascriptClick.click(acp.addDataButton);
		}	
		applyExplicitWaitsUntilElementVisible(acp.groupTextBoxes, 30);
		List<WebElement> textBoxes = acp.groupTextBoxes;
		System.out.println(data_Service+"+++++++++++++++++++++");
		String filePath=Constants.testData_Folder + data_Service + Constants.testData_Suffix;
		JSONObject jsonObject = JsonUtils.getJSONObject(filePath);
			
				for(int j=1;j<=textBoxes.size();j++) {
					WebElement textBox = driver.findElement(By.xpath("(//*[contains(@class,'form-control') or @type='checkbox' or @type='file' or contains(@class,'btn btn-link mr-2 p-0') or contains(@class,'searchInput')])[" + j + "]"));
		
				String id1 =textBox.getAttribute("id");
				
				if(textBox.getAttribute("type").equals("text")) {
					if(id1.contains(".")) {
						String [] attributes=id1.trim().split("[^a-zA-Z0-9]+");
					
						JSONObject obj=(JSONObject) jsonObject.get(attributes[0]);
						applyWait.waitForElementToBeClickable(textBox,30).sendKeys((String)obj.get(attributes[1]));
						
					}
					else {
					applyWait.waitForElementToBeClickable(textBox,30).sendKeys(((String)jsonObject.get(id1)).toString());;
				}
				}
				if(textBox.getAttribute("type").equals("number")) {
					applyWait.waitForElementToBeClickable(textBox,30).sendKeys(((Long)jsonObject.get(id1)).toString());;
					}
				}

			applyWait.waitForElementToBeClickable(acp.proceed, 30).click();
			applyWait.waitForElementToBeClickable(acp.comments, 30).sendKeys("Check");;
			applyWait.waitForElementToBeClickable(acp.submit, 30).click();
				
		}
	

	public void logoutFromAppcenter() {
		
		applyWait.waitForElementToBeClickable(acp.userDetails, 30).click();
		applyWait.waitForElementToBeClickable(acp.logout, 30).click();
		
	}

	public void userNavigateToAppCenter() {
		
		SwitchWindow.openNewTab(driver);
		driver.get(app_center_URL);
		driver.manage().window().maximize();
	}

	public void verifyDataAvailableInWorkflowListingPage() {
		
		applyWait.waitForElementToBeClickable(acp.newRecordsTab, 30).click();
		Boolean verify=applyWait.waitForElementToBeClickable(acp.workflowList, 30).isDisplayed();
		if(verify) {
		}
	}

	public void approveTheRecord() throws Exception {
		applyWait.waitForElementToBeClickable(acp.workflowTab, 30).click();
		applyExplicitWaitsUntilElementVisible(acp.respondWorkflows, 30);
		List <WebElement> workflows=acp.respondWorkflows;
		for(WebElement workflow : workflows) {
			applyExplicitWaitsUntilElementVisible(acp.respond1,30);
			Thread.sleep(1000);
			try {
				acp.respond1.click();
			}
			catch(StaleElementReferenceException e) {
				applyExplicitWaitsUntilElementVisible(acp.respond1,30);
				acp.respond1.click();
			}
			Thread.sleep(500);
			applyWait.waitForElementToBeClickable(acp.respond, 30).click();
			applyWait.waitForElementToBeClickable(acp.enterApproveComment, 30).sendKeys(Property.getProperty("approveMessage"));;
			applyWait.waitForElementToBeClickable(acp.approveButton, 30).click();;
		}
	}

	public void verifyDataIsAvailableWithStatusApproved() throws Exception {
//		Thread.sleep(1000);
//		Boolean verify=applyWait.waitForElementToBeClickable(acp.approved, 30).isDisplayed();
		
	}


	public void addDataAndSaveAsDraft() throws Exception {
		
		try {
			applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();
		}
		catch(Exception e) {
			Thread.sleep(1000);
			JavascriptClick.click(acp.addDataButton);
		}
		List<WebElement> textBoxes = acp.textBoxes;
		JSONArray jsonArray = JsonUtils.getJSONArray(Constants.testData_Folder + data_Service + Constants.testData_Suffix);
		for(int i=0;i<jsonArray.size();i++) {
			JSONObject jsonObject=(JSONObject) jsonArray.get(i);
			
			List<WebElement> stepNames=driver.findElements(By.xpath("//div[@class='step-name high-zIndex text-truncate']"));
			for(WebElement stepName : stepNames) {
				stepName.click();
			
				for(int j=1;j<=textBoxes.size();j++) {
				WebElement textBox=driver.findElement(By.xpath("(//input[contains(@class,'form-control')])["+j+"]"));
		
				String id1 =textBox.getAttribute("id");
				if(id1.equals("_id")) {
					applyWait.waitForElementToBeClickable(textBox,30).sendKeys(Property.getProperty("id"));;
				}
				
				else if(textBox.getAttribute("type").equals("text")) {
					if(id1.contains(".")) {
						String [] attributes=id1.trim().split("[^a-zA-Z0-9]+");
					
						JSONObject obj=(JSONObject) jsonObject.get(attributes[0]);
						applyWait.waitForElementToBeClickable(textBox,30).sendKeys((String)obj.get(attributes[1]));
						
					}
					else {
					applyWait.waitForElementToBeClickable(textBox,30).sendKeys(((String)jsonObject.get(id1)).toString());;
				}
				}
				if(textBox.getAttribute("type").equals("number")) {
					applyWait.waitForElementToBeClickable(textBox,30).sendKeys(((Long)jsonObject.get(id1)).toString());;
					}
				
			}
				break;
			}
			applyWait.waitForElementToBeClickable(acp.saveAsDraft, 30).click();
			applyWait.waitForElementToBeClickable(acp.saveAsDraftComments, 30).sendKeys(Property.getProperty("approveMessage"));;
			applyWait.waitForElementToBeClickable(acp.submit, 30).click();
				break;
		}
	}

	public void verifyDataAvailableUnderDraft() {
		applyWait.waitForElementToBeClickable(acp.draftTab, 30).click();
		Boolean verify=applyWait.waitForElementToBeClickable(acp.draftStatus, 30).isDisplayed();
		
	}

	public void updateDraftRecord() throws InterruptedException {
		applyWait.waitForElementToBeClickable(acp.workflowTab, 30).click();
		applyWait.waitForElementToBeClickable(acp.draftTab, 30).click();
		applyWait.waitForElementToBeClickable(acp.viewTab, 30).click();	
		
		List<WebElement> textBoxes = acp.textBoxes;
		JSONArray jsonArray = JsonUtils.getJSONArray(Constants.testData_Folder + data_Service + Constants.testData_Suffix);
		for(int i=0;i<jsonArray.size();i++) {
			JSONObject jsonObject=(JSONObject) jsonArray.get(i);
			
			List<WebElement> stepNames=driver.findElements(By.xpath("//div[@class='step-name high-zIndex text-truncate']"));
			for(WebElement stepName : stepNames) {
				stepName.click();
			
			
				for(int j=1;j<=textBoxes.size();j++) {
				WebElement textBox=driver.findElement(By.xpath("(//input[contains(@class,'form-control')])["+j+"]"));
		
				String textInsideInputBox = textBox.getAttribute("value");
				if(textInsideInputBox.isEmpty())
				{
				
				String id1 =textBox.getAttribute("id");
				
				if(textBox.getAttribute("type").equals("text")) {
					if(id1.contains(".")) {
						String [] attributes=id1.trim().split("[^a-zA-Z0-9]+");
					
						JSONObject obj=(JSONObject) jsonObject.get(attributes[0]);
						applyWait.waitForElementToBeClickable(textBox,30).sendKeys((String)obj.get(attributes[1]));
						
					}
					else {
					applyWait.waitForElementToBeClickable(textBox,30).sendKeys(((String)jsonObject.get(id1)).toString());;
				}
				}
				if(textBox.getAttribute("type").equals("number")) {
					applyWait.waitForElementToBeClickable(textBox,30).sendKeys(((Long)jsonObject.get(id1)).toString());;
					}
				}
			}
				
			}
		break;
	}
		applyWait.waitForElementToBeClickable(acp.proceedButton, 30).click();
		applyWait.waitForElementToBeClickable(acp.saveAsDraftComments, 30).sendKeys(Property.getProperty("updateMessage"));
		applyWait.waitForElementToBeClickable(acp.submit, 30).click();
	}


	public void editDataService() throws Exception {
		applyWait.waitForElementToBeClickable(acp.dataServiceList, 30).click();
		applyWait.waitForElementToBeClickable(acp.edit, 30).click();
		applyWait.waitForElementToBeClickable(acp.firstNameTextBox, 30).clear();;
		applyWait.waitForElementToBeClickable(acp.firstNameTextBox, 30).sendKeys(Property.getProperty("userName"));;
		applyWait.waitForElementToBeClickable(acp.saveAsDraft, 30).click();
		applyWait.waitForElementToBeClickable(acp.saveAsDraftComments, 30).sendKeys(Property.getProperty("editMessage"));;
		applyWait.waitForElementToBeClickable(acp.submit, 30).click();
	}

	public void approveTheRecordFromUpdateRecordTab() {
		
		applyWait.waitForElementToBeClickable(acp.dataService, 30).click();
		applyWait.waitForElementToBeClickable(acp.review, 30).click();
		applyWait.waitForElementToBeClickable(acp.respond1, 30).click();
		applyWait.waitForElementToBeClickable(acp.approve, 30).click();
		applyWait.waitForElementToBeClickable(acp.enterApproveComment, 30).sendKeys(Property.getProperty("approveMessage"));
		applyWait.waitForElementToBeClickable(acp.approveButton, 30).click();
	}

	public void rejectTheRecord() throws Exception {
		

		applyWait.waitForElementToBeClickable(acp.workflowTab, 30).click();
		List <WebElement> workflows=acp.respondWorkflows;
		for(WebElement workflow : workflows) {
			applyExplicitWaitsUntilElementVisible(acp.respond1,30);
			try {
				acp.respond1.click();
			}
			catch(StaleElementReferenceException e) {
				applyExplicitWaitsUntilElementVisible(acp.respond1,30);
				acp.respond1.click();
			}
			applyWait.waitForElementToBeClickable(acp.reject, 30).click();
			applyWait.waitForElementToBeClickable(acp.enterRejectComment, 30).sendKeys(Property.getProperty("rejectMessage"));;
			applyWait.waitForElementToBeClickable(acp.rejectButton, 30).click();;
		}
		
	}

	public void reworkTheRecord() throws Exception {
		
		applyWait.waitForElementToBeClickable(acp.workflowTab, 30).click();
		List <WebElement> workflows=acp.respondWorkflows;
		for(WebElement workflow : workflows) {
			applyExplicitWaitsUntilElementVisible(acp.respond1,30);
			try {
				applyWait.waitForElementToBeClickable(acp.respond1, 30).click();;
			}
			
			catch(StaleElementReferenceException e) {
				applyExplicitWaitsUntilElementVisible(acp.respond1,30);
				acp.respond1.click();
			}
			applyWait.waitForElementToBeClickable(acp.rework1, 30).click();
			applyWait.waitForElementToBeClickable(acp.enterRejectComment, 30).sendKeys(Property.getProperty("reworkMessage"));;
			applyWait.waitForElementToBeClickable(acp.reworkButton, 30).click();;
			
		}
	}
}
