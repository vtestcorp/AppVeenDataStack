Feature: Login to Author Url

Background: User Log in Author page
 Given User Navigate to LogIn Page
 And User enters username and password
  |deepak_vtest@appveen.com|qwertyabcd|
  
 And Verify User Login Successfully

#@DataService
Scenario: Successful Login with Valid Credentials
 Then Create new Data Service "users"
 
 

 