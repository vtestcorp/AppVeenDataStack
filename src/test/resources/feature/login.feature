Feature: Login to Author Url

Background: User Log in Author page
 Given User Navigate to LogIn Page
 And User enters username and password
  |vtest@appveen.com|123123123|
  
 And Verify User Login Successfully

#@DataService
Scenario: Successful Login with Valid Credentials
 Then Create new Data Service "users"
 
 

 