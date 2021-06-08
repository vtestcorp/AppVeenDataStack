package pageModules;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;
import helperMethods.DropDown;
import helperMethods.JavascriptClick;
import helperMethods.JsonUtils;
import helperMethods.ScrollTypes;
import helperMethods.WaitTypes;
import pageobjects.Object_AppCenterPage;

public class WorkflowsInAppcenterPage extends BaseClass{
	
	private WebDriver driver;
	private WaitTypes applyWait;
	private ExtentTest test;
	private JavascriptClick javascriptClick;
	public DropDown dropdown;
	public ScrollTypes scroll;
	public static String data_Service;
	public Object_AppCenterPage acp;
	
	public WorkflowsInAppcenterPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.applyWait = new WaitTypes(driver);
		this.test = test;
		dropdown=new DropDown(driver);
		scroll=new ScrollTypes(driver);
		acp=new Object_AppCenterPage();
	}
	
	
	public void dataService(String dataService) throws Exception {
		Thread.sleep(1000);
		data_Service=dataService;
		WebElement data=driver.findElement(By.xpath("//div[contains(text(),'"+dataService+"')]"));
		javascriptClick=new JavascriptClick(driver);
		data.click();
	}
	
	
	public void addDataToDataService() throws InterruptedException {
		

		Thread.sleep(1000);
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();	
		Thread.sleep(3000);
		List<WebElement> textBoxes = acp.textBoxes;
		String path= System.getProperty("user.dir");
		JSONArray jsonArray = JsonUtils.getJSONArray("C:\\Users\\DELL\\eclipse-workspace\\DataStack"+"\\testData\\"+data_Service+".data.json");
		for(int i=0;i<jsonArray.size();i++) {
			Thread.sleep(1000);
			JSONObject jsonObject=(JSONObject) jsonArray.get(i);
			
			List<WebElement> stepNames=driver.findElements(By.xpath("//div[@class='step-name high-zIndex text-truncate']"));
			for(WebElement stepName : stepNames) {
				stepName.click();
			
			
				for(int j=1;j<=textBoxes.size();j++) {
				WebElement textBox=driver.findElement(By.xpath("(//input[contains(@class,'form-control')])["+j+"]"));
		
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
			if((jsonArray.size()-1) > i) {
			applyWait.waitForElementToBeClickable(acp.proceedAndCreateAnother, 30).click();
				}
				else {
					applyWait.waitForElementToBeClickable(acp.proceed, 30).click();
				}
			applyWait.waitForElementToBeClickable(acp.comments, 30).sendKeys("Check");;
			applyWait.waitForElementToBeClickable(acp.submit, 30).click();
				
		}
	
		

	}


	public void logoutFromAppcenter() {
		
		applyWait.waitForElementToBeClickable(acp.userDetails, 30).click();
		applyWait.waitForElementToBeClickable(acp.logout, 30).click();
		
	}


	public void userNavigateToAppCenter() {
	}


	public void verifyDataAvailableInWorkflowListingPage() {
		
		applyWait.waitForElementToBeClickable(acp.newRecordsTab, 30).click();
		Boolean verify=applyWait.waitForElementToBeClickable(acp.workflowList, 30).isDisplayed();
		if(verify) {
		}
		
	}


	public void approveTheRecord() throws Exception {
		Thread.sleep(2000);
		applyWait.waitForElementToBeClickable(acp.workflowTab, 30).click();
		Thread.sleep(3000);
		List <WebElement> workflows=acp.respondWorkflows;
		int i=1;
		for(WebElement workflow : workflows) {
			applyExplicitWaitsUntilElementVisible(acp.respond1);
			Thread.sleep(1000);
			try {
				acp.respond1.click();
			}
			catch(StaleElementReferenceException e) {
				applyExplicitWaitsUntilElementVisible(acp.respond1);
				WebElement workFlow=driver.findElement(By.xpath("//span[normalize-space()='Respond']"));
				workFlow.click();
			}
			applyWait.waitForElementToBeClickable(acp.approve, 30).click();
			applyWait.waitForElementToBeClickable(acp.enterApproveComment, 30).sendKeys("Approved");;
			applyWait.waitForElementToBeClickable(acp.approveButton, 30).click();;
		}
		
		
		
	}


	public void verifyDataIsAvailableWithStatusApproved() throws Exception {
		Thread.sleep(1000);
		Boolean verify=applyWait.waitForElementToBeClickable(acp.approved, 30).isDisplayed();
		if(verify) {
			System.out.println("Data is available in the workflow listing page under New Records with status Approved");
		}
		else {
			System.out.println("Data is not available in the workflow listing page under New Records with status Approved");
		}
		
		
	}


	public void addDataAndSaveAsDraft() throws Exception {
		
		
		Thread.sleep(1000);
		applyWait.waitForElementToBeClickable(acp.addDataButton, 30).click();	
		Thread.sleep(3000);
		List<WebElement> textBoxes = acp.textBoxes;
		String path= System.getProperty("user.dir");
		JSONArray jsonArray = JsonUtils.getJSONArray(path+"\\testData\\"+data_Service+".data.json");
		for(int i=0;i<jsonArray.size();i++) {
			Thread.sleep(1000);
			JSONObject jsonObject=(JSONObject) jsonArray.get(i);
			
			List<WebElement> stepNames=driver.findElements(By.xpath("//div[@class='step-name high-zIndex text-truncate']"));
			for(WebElement stepName : stepNames) {
				stepName.click();
			
			
				for(int j=1;j<=textBoxes.size();j++) {
				WebElement textBox=driver.findElement(By.xpath("(//input[contains(@class,'form-control')])["+j+"]"));
		
				String id1 =textBox.getAttribute("id");
				if(id1.equals("_id")) {
					applyWait.waitForElementToBeClickable(textBox,30).sendKeys("USE1234");;
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
//			if((jsonArray.size()-1) > i) {
//			applyWait.waitForElementToBeClickable(acp.proceedAndCreateAnother, 30).click();
//				}
//				else {
//					applyWait.waitForElementToBeClickable(acp.proceed, 30).click();
//				}
			applyWait.waitForElementToBeClickable(acp.saveAsDraft, 30).click();
			applyWait.waitForElementToBeClickable(acp.saveAsDraftComments, 30).sendKeys("Maker 1 updated basic details");;
			applyWait.waitForElementToBeClickable(acp.submit, 30).click();
				break;
		}
		
		
	}


	public void verifyDataAvailableUnderDraft() {
		applyWait.waitForElementToBeClickable(acp.draftTab, 30).click();
		Boolean verify=applyWait.waitForElementToBeClickable(acp.draftStatus, 30).isDisplayed();
		if(verify) {
			System.out.println("Data is available in the workflow listing page under Draft with status Draft");
		}
		else {
			System.out.println("Data is not available in the workflow listing page under Draft with status Draft");
		}
	}



	public void updateDraftRecord() throws InterruptedException {
		applyWait.waitForElementToBeClickable(acp.workflowTab, 30).click();
		applyWait.waitForElementToBeClickable(acp.draftTab, 30).click();
		applyWait.waitForElementToBeClickable(acp.viewTab, 30).click();	
		
		Thread.sleep(3000);
		List<WebElement> textBoxes = acp.textBoxes;
		String path= System.getProperty("user.dir");
		JSONArray jsonArray = JsonUtils.getJSONArray(path+"\\testData\\"+data_Service+".data.json");
		for(int i=0;i<jsonArray.size();i++) {
			Thread.sleep(1000);
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
		applyWait.waitForElementToBeClickable(acp.saveAsDraftComments, 30).sendKeys("Updated by Maker 2");
		applyWait.waitForElementToBeClickable(acp.submit, 30).click();
	}


	public void editDataService() throws Exception {
		applyWait.waitForElementToBeClickable(acp.dataServiceList, 30).click();
		Thread.sleep(3000);
		applyWait.waitForElementToBeClickable(acp.edit, 30).click();
		applyWait.waitForElementToBeClickable(acp.firstNameTextBox, 30).clear();;
//		applyWait.waitForElementToBeClickable(acp.idTextBox, 30).sendKeys("USE1232");
		applyWait.waitForElementToBeClickable(acp.firstNameTextBox, 30).sendKeys("Parker");;
		applyWait.waitForElementToBeClickable(acp.saveAsDraft, 30).click();
		applyWait.waitForElementToBeClickable(acp.saveAsDraftComments, 30).sendKeys("Edited by Maker2");;
		applyWait.waitForElementToBeClickable(acp.submit, 30).click();
		
		
		
	}


	public void approveTheRecordFromUpdateRecordTab() {
		
		applyWait.waitForElementToBeClickable(acp.dataService, 30).click();
		applyWait.waitForElementToBeClickable(acp.review, 30).click();
		applyWait.waitForElementToBeClickable(acp.respond1, 30).click();
		applyWait.waitForElementToBeClickable(acp.approve, 30).click();
		applyWait.waitForElementToBeClickable(acp.enterApproveComment, 30).sendKeys("Approved");
		applyWait.waitForElementToBeClickable(acp.approveButton, 30).click();

		
	}


	public void rejectTheRecord() throws Exception {
		

		Thread.sleep(2000);
		applyWait.waitForElementToBeClickable(acp.workflowTab, 30).click();
		Thread.sleep(3000);
		List <WebElement> workflows=acp.respondWorkflows;
		int i=1;
		for(WebElement workflow : workflows) {
			applyExplicitWaitsUntilElementVisible(acp.respond1);
			Thread.sleep(1000);
			try {
				acp.respond1.click();
			}
			catch(StaleElementReferenceException e) {
				applyExplicitWaitsUntilElementVisible(acp.respond1);
				WebElement workFlow=driver.findElement(By.xpath("//span[normalize-space()='Respond']"));
				workFlow.click();
			}
			applyWait.waitForElementToBeClickable(acp.reject, 30).click();
			applyWait.waitForElementToBeClickable(acp.enterRejectComment, 30).sendKeys("Rejected");;
			applyWait.waitForElementToBeClickable(acp.rejectButton, 30).click();;
		}
		
		
		
	
		
	}


	public void reworkTheRecord() throws Exception {
		
		Thread.sleep(2000);
		applyWait.waitForElementToBeClickable(acp.workflowTab, 30).click();
		Thread.sleep(3000);
		List <WebElement> workflows=acp.respondWorkflows;
		int i=1;
		for(WebElement workflow : workflows) {
			applyExplicitWaitsUntilElementVisible(acp.respond1);
			Thread.sleep(1000);
			try {
				applyWait.waitForElementToBeClickable(acp.respond1, 30).click();;
			//	acp.respond1.click();
				
			}
			
			catch(StaleElementReferenceException e) {
				applyExplicitWaitsUntilElementVisible(acp.respond1);
				WebElement workFlow=driver.findElement(By.xpath("//span[normalize-space()='Respond']"));
				workFlow.click();
			}
			Thread.sleep(2000);
			applyWait.waitForElementToBeClickable(acp.rework1, 30).click();
	//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", acp.rework);
			applyWait.waitForElementToBeClickable(acp.enterRejectComment, 30).sendKeys("Send for Rework");;
			applyWait.waitForElementToBeClickable(acp.reworkButton, 30).click();;
			
		}
	}
}
