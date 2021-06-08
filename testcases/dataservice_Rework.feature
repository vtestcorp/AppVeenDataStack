Feature: Rework the dataservice in appcenter page 


@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|vtest@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "users" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "users" does not exist
	Then Create new data service "users"

Scenario Outline: Delete group
	Given Group sampleGroup "<group>" exists
	Then Remove group "<group>"
	Examples:
		| group |
		| Employee Reviewer |
		| Employee Maker|

Scenario Outline: Add group
	Given Group sampleGroup "<group>" does not exists
	And Data service "<dataservice>"  exists
	Then Create group "<group>" and enable role "<role>" of "<dataservice>"
	Examples:
		| group | dataservice | role |
		| Employee Reviewer | users | Reviewer |
		| Employee Maker|users|Employee Admin|

Scenario Outline: Assign permissions
	Given Data service "<dataservice>"  exists
	And Group "<group>" exists
	And User "<user>" exists
	Then Add "<user>" to "<group>"
	Examples:
		| group | dataservice | user |
		| Employee Reviewer | users | reviewer@appveen.com |
		| Employee Maker|users|maker@appveen.com|
		| Employee Maker|users|maker1@appveen.com|

Scenario: Log out of Author
	Given User logged into Author
	Then User logs out of Author
	

Scenario Outline: Log into AppCenter as Maker1
	Given User navigate to AppCenter loginpage
	And User enters "<username>" and "<password>" in AppCenter login page
	And Verify User has Logged in Successfully 
Examples:
|username|password|
|maker@appveen.com|123123123|


Scenario: Add data to the dataservice workflow  for Rework
	Given Data Service "users"
	Then Add data to the data service workflow for Rework
	And Verify data is available in the workflow listing page under New Records with status Pending Review
	Then User logs out of AppCenter
	
	
Scenario Outline: Log into AppCenter as Checker1
	Given User navigate to AppCenter login page
	And User enters "<username>" and "<password>" in AppCenter login page
	And Verify User has Logged in Successfully 
Examples:
|username|password|
|reviewer@appveen.com|123123123|


Scenario: Rework the data created 
	Given Data service "users"
	Then Rework the Record created in previous step
#	And Verify data is available in the workflow listing page under New Records with status Approved
#	And Verify data is available in the listing page
#	Then User logs out of AppCenter