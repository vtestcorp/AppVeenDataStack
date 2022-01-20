Feature: Maker Checker 

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|test_ui_appadmin@appveen.com|Veen@99%win|

Scenario: Delete data service
	Given Data service "makerchecker-1" exists
#	Then Remove the data service
#
#Scenario: Create data service
#	Given Data service "makerchecker-1" does not exist
#	Then Create new data service "makerchecker-1"
#
#Scenario Outline: Add Maker Checker steps
#	Given Add step name "<step name>" and "<no of approvals>"
#	
#	Examples:
#	|step name|no of approvals|
#	|Production|3|
#	|Quality|2|
# |Pdi|1|
#
#Scenario Outline: Delete group
#	Given Group sampleGroup "<group>" exists
#	Then Remove group "<group>"
#	Examples:
#		| group |
#		| MakerChecker|
#		| Production |
#		| Quality|
#		|Pdi|
#
#Scenario Outline: Add group
#	Given Group sampleGroup "<group>" does not exists
#	And Data service "<dataservice>"  exists
#	Then Create group "<group>" and enable role "<role>" of "<dataservice>"
#	Examples:
#		| group | dataservice | role |
#		| MakerChecker | makerchecker-1 | Manage |
#		| Production|makerchecker-1|Production|
#		| Quality|makerchecker-1|Quality|
#		| Pdi|makerchecker-1|Pdi|
#		
#Scenario Outline: Assign permissions
#	Given Data service "<dataservice>"  exists
#	And Group "<group>" exists
#	And User "<user>" exists
#	Then Add "<user>" to "<group>"
#	Examples:
#		| group | dataservice | user |
#		| MakerChecker | makerchecker-1 | test_ui_ac_ds_manage@appveen.com |
#		| Production|makerchecker-1|reviewer1@appveen.com|
#		| Production|makerchecker-1|reviewer2@appveen.com|
#		| Production|makerchecker-1|reviewer3@appveen.com|
#		| Quality|makerchecker-1|reviewer_quality_1@appveen.com|
#		| Quality|makerchecker-1|reviewer_quality_2@appveen.com|
#		| Pdi|makerchecker-1|reviewer_pdi@appveen.com|
#
#Scenario: Log out of Author
#	Given User logged into Author
#	Then User logs out of Author
#	
#
#Scenario Outline: Log into AppCenter as Maker1
#	Given User navigate to AppCenter loginpage
#	And User enters "<username>" and "<password>" in AppCenter login page
#	And Verify User has Logged in Successfully 
#Examples:
#|username|password|
#|test_ui_ac_ds_manage@appveen.com|Veen@99%win|
#
#
#Scenario: Add data to the dataservice workflow for Approval
#	Given Data Service "makerchecker-1"
#	Then Add data to the data service workflow for Approval
#	And Verify data is available in the workflow listing page under New Records with status Pending Review
#	Then User logs out of AppCenter
#	
#Scenario Outline: Add record to data service
#	Given Data service "makerchecker-1"
#	Then Add record "<data>" to the data service
#	Examples:
#|data|
#|{"_id" : "MAK1003","name" : "Mark","mobileNo" : 8888877777,"address" : "India"}|
#|{"_id" : "MAK1004","name" : "John","mobileNo" : 7777777777,"address" : "Mumbai"}|
#
#
#Scenario Outline: Log into AppCenter as Checker1
#	Given User navigate to AppCenter login page
#	And User enters "<username>" and "<password>" in AppCenter login page
#	And Verify User has Logged in Successfully 
#Examples:
#|username|password|
#|reviewer1@appveen.com|Veen@99%win|
#
#Scenario: Approve the data created 
#	Given Data Service "makerchecker"
#	Then Approve the Record created in previous step
#	And Verify data is available in the workflow listing page under New Records with status Approved
#	And Verify data is available in the listing page
#	Then User logs out of AppCenter