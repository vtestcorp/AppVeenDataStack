Feature: Login to Author Url

Background: User Log in Author page
 Given User Navigate to LogIn Page
 And User enters username and password
  |deepak_vtest@appveen.com|qwertyabcd|
 And Verify User Login Successfully

@DataService
Scenario: Successful Login with Valid Credentials
 Then Create new Data Service "Test Four"
 
 
 @Search
 Scenario: Verify user able to search given Data service
 And Create Data Service "Service-1"
 Then Search given Data Service 
 
 @Delete
 Scenario: Verify User able to delete given Data service
   And Create Data Service "Service-2"
   Then Delete Given Data Service
   
   
 @Clone
 Scenario: Verify User able to delete given Data service
   And Create Data Service "Service-3"
   Then Clone Given Data Service
 