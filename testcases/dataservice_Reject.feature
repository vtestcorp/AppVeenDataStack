Feature: Reject the dataservice in appcenter page 


@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|test_ui_appadmin@appveen.com|Veen@99%win|

Scenario: Delete data service
	Given Data service "makerchecker" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "makerchecker" does not exist
	Then Create new data service "makerchecker"

Scenario Outline: Delete group
	Given Group sampleGroup "<group>" exists
	Then Remove group "<group>"
	Examples:
		| group |
		| MakerChecker|
		| Production |
#		| Quality|

Scenario Outline: Add group
	Given Group sampleGroup "<group>" does not exists
	And Data service "<dataservice>"  exists
	Then Create group "<group>" and enable role "<role>" of "<dataservice>"
	Examples:
		| group | dataservice | role |
		| MakerChecker | makerchecker | Manage |
		| Production|makerchecker|Production|
#		| Quality|makerchecker|Quality|
		
Scenario Outline: Assign permissions
	Given Data service "<dataservice>"  exists
	And Group "<group>" exists
#	And User "<user>" exists
	Then Add "<user>" to "<group>"
	Examples:
		| group | dataservice | user |
		| MakerChecker | makerchecker | test_ui_ac_ds_manage@appveen.com |
		| Production|makerchecker|reviewer1@appveen.com|
#			| Quality|makerchecker|reviewer2@appveen.com|
#			| Quality|makerchecker|reviewer3@appveen.com|

Scenario: Log out of Author
	Given User logged into Author
	Then User logs out of Author


Scenario Outline: Log into AppCenter as Maker1
	Given User navigate to AppCenter loginpage
	And User enters "<username>" and "<password>" in AppCenter login page
	And Verify User has Logged in Successfully 
Examples:
|username|password|
|test_ui_ac_ds_manage@appveen.com|Veen@99%win|


Scenario: Add data to the dataservice workflow  for Approval
	Given Data Service "makerchecker"
	Then Add data to the data service workflow for Approval
	And Verify data is available in the workflow listing page under New Records with status Pending Review
	Then User logs out of AppCenter
	

Scenario Outline: Log into AppCenter as Checker1
	Given User navigate to AppCenter login page
	And User enters "<username>" and "<password>" in AppCenter login page
	And Verify User has Logged in Successfully 
Examples:
|username|password|
|reviewer1@appveen.com|Veen@99%win|


Scenario: Reject the data created 
	Given Data service "makerchecker"
	Then Reject the Record created in previous step
#	And Verify data is available in the workflow listing page under New Records with status Approved
#	And Verify data is available in the listing page
#	Then User logs out of AppCenter