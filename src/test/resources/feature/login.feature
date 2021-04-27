Feature: Verify Data Service 

@Author
Scenario Outline: Create Data Service in Author portal 
	Given User Navigate to LogIn Page 
	And User enters "<username>" and "<password>" in the Author login page
	And Verify User has Logged in Successfully 
	Then Create new Data Service "users"
Examples:
|username|password|
|vtest@appveen.com|123123123|
	
@Connect	
Scenario: Connect from Author to Appcenter 
	Given User gives permission to Data Service 
	
@AppCenter	
Scenario: Verify Data Service in AppCenter portal 
	Given User Navigate to AppCenter LogIn Page 
	And User enters username and password in AppCenter login page
		|vtest@appveen.com|123123123|
	And Verify User has Logged in Successfully in AppCenter Url 
	And User enters data in Data Service 
	
 