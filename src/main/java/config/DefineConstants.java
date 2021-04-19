package config;

public class DefineConstants {
	// Test data file Path
	public static final String Path_TestData = "";

	// Application URL
	public static final String AUTHOR_URL = "https://staging.appveen.com/author";
	public static final String APP_CENTER_URL = "https://staging.appveen.com/appcenter";
	
	public static final String User_Email_Id="deepak_vtest@appveen.com";
	
	public static final String User_Password="123123123";

//	 http://p5beta.plumb5.com/p5-newdesign/manage-campaigns.html
	
	// Explicit Wait Time
	public static final int explicitWait_10 = 10;
	
	public static final int explicitWait_20 = 20;
	
	public static final int explicitWait_30 = 30;

	public static final int explicitWait_60 = 60;

	public static final String PROJECT_PATH = System.getProperty("user.dir")+"/";

	public static final String PROJECT_OS = System.getProperty("os.name");

	public static final String TestData_Folder = "C:\\Users\\DELL\\eclipse-workspace\\DataStack\\Test_Data";
	
	public static final String Locator_Folder = "C:\\Users\\DELL\\eclipse-workspace\\DataStack\\locator";
	
	public static final String Authentication_TestData_Folder = "Authentication";
	
	public static final String Set_One_TestData_Folder = "Set_One";

	public static final String Success_Message = "Service Created.";
	
	public static final String App_Center_Success_Message = "Saved.";
		
	// Authentication Json File
	public static final String Authentication = TestData_Folder + "//" + Authentication_TestData_Folder + "//" + "Login.json";
	//Set One
	public static final String TC_01_CreateDS = TestData_Folder + "//" + Set_One_TestData_Folder + "//" + "TC_01_CreateDS.json";
	
	public static final String json_FilePath = Locator_Folder + "\\" + "Locator.json";
	
	public static final String json_AppCenter_FilePath = Locator_Folder + "\\" + "appCenterLocator.json";
	
//	public static final String TC_Create_New_Data_Service = TestData_Folder +  "//" + "dataServiceCredentials.json";
	
	public static final String TC_Create_New_Data_Service = TestData_Folder +  "//" + "data.json";

}