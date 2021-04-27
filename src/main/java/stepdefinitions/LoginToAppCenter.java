package stepdefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import cucumber.api.java.en.Given;
import helperMethods.WaitTypes;
import io.cucumber.datatable.DataTable;
import pageModules.LoginAppCenter;

public class LoginToAppCenter {
	private WebDriver driver;
	private WaitTypes applyWait;
	private ExtentTest test;
	LoginAppCenter loginAppCenter;
	
	
	@Given("User Navigate to AppCenter LogIn Page")
	public void user_Navigate_to_AppCenter_LogIn_Page() {
		loginAppCenter = new LoginAppCenter(driver, test);
		loginAppCenter.loginToAppCenterPage();
	    
	}

	@Given("User enters username and password in AppCenter login page")
	public void user_enters_username_and_password_in_AppCenter_Url(DataTable dataTable) throws Exception {
		
		List<List<String>> userList = dataTable.asLists(String.class);
		for (List<String> e : userList) {
			System.out.println(e.get(0) + "------------");
			System.out.println(e.get(1) + "------------");
			
			loginAppCenter.enterUserNameAndPassword(e.get(0), e.get(1));
		}
	}

	@Given("Verify User has Logged in Successfully in AppCenter Url")
	public void verify_User_Login_Successfully_in_Appcenter_Url() throws Exception {
		//dataStackLogo
		loginAppCenter.verifyUserLoginSuccesfullyToAppCenter();
	    
	}
	
	@Given("User enters data in Data Service")
	public void user_enters_data_in_Data_Service() throws Exception {
		loginAppCenter.userEnterData(LoginToAuthorUrl.data_Service);
	}

}
