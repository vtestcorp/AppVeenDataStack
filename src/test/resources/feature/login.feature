Feature: Verify Data Service

#@DataService
Scenario: Create Data Service in Author portal
Given User Navigate to LogIn Page
 And User enters username and password
  |vtest@appveen.com|123123123|
 And Verify User Login Successfully
 Then Create new Data Service "users111"
 
 
Scenario: Connect from Author to Appcenter
	Given User gives permission to Data Service
	
 
 Scenario: Verify Data Service in AppCenter portal
Given User Navigate to AppCenter LogIn Page
 And User enters username and password in AppCenter Url
  |vtest@appveen.com|123123123|
  
 And Verify User Login Successfully in Appcenter Url
 And User enters data in Data Service

 