Feature: Login to AppCenter Url

Scenario: Successful Login with Valid Credentials
Given User Navigate to AppCenter LogIn Page
 And User enters username and password in AppCenter Url
  |deepak_vtest@appveen.com|qwertyabcd|
  
 And Verify User Login Successfully in Appcenter Url
 Then Create new Data Service 