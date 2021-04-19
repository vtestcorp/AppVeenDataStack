Feature: Login to AppCenter Url

Scenario: Successful Login with Valid Credentials
Given User Navigate to AppCenter LogIn Page
 And User enters username and password in AppCenter Url
  |vtest@appveen.com|123123123|
  
 And Verify User Login Successfully in Appcenter Url
 And User enters data in Data Service
