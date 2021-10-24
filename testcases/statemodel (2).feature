Feature: DS STATE MODEL

# Scenarios - 

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|vtest@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "stringStateModel" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "stringStateModel" does not exist
	Then Create new data service "stringStateModel" for State_Model 
	

Scenario Outline: Add Attributes to data service
Given Add attributes "<attributeName>" and type "<attributeType>"

#And Save button is not clicked	
Examples:
	|attributeName|attributeType|
	|Name|Text|
	|DOB|Date|
	|Salary|Number|
	|Onboarding Status|Text|

Scenario: Configure State model
Given Click on Experience tab to create a State Model 
Then Create a State Model for the field "Onboarding Status"

Scenario Outline: Create State for State Model
Then Create states "<state_name>"
Examples:
|state_name|
|Open|
|In Progress|
|Rejected|
|Deffered|
|Config Issue|
|Cannot Reproduce|
|Working as Expected|
|Ready for QA|
|Re-open|
|Ready for Release|


Scenario: Create State for State Model
And Set Current State with from state to next state
|Open|In Progress,Rejected,Deffered,Config Issue,Cannot Reproduce,Working as Expected|
|In Progress|Ready for QA,Open|
|Ready for QA|Ready for Release,Re-open|
|Re-open|In Progress|
|Ready for Release|Closed|
|Rejected|Open|
|Deffered|Open|
Then Save and Deploy data service

Scenario Outline: Assign to Appcenter Group
	Then Group "stringStateModel" does not exist
	Then Create new group "stringStateModel" 
	And Assign appcenter permissions for "stringStateModel" dataservice to "<user>"
	
	Examples:
	|user|
	|vtest@appveen.com|


Scenario: Log out of Author
	Given User logged into Author
	Then User logs out of Author

@AppCenter
Scenario Outline: Log into AppCenter
	Given User navigate to AppCenter login page
	And User enters "<username>" and "<password>" in AppCenter login page
	And Verify User has Logged in Successfully 
Examples:
|username|password|
|vtest@appveen.com|123123123|


 #INSERT/UPDATE
Scenario Outline: Add data to data service
	Given Data service "stringStateModel"
	Then Add data to the stateModel data service
	And Verify the State is "<Current State>"
	Examples:
	|Current State|
	|Open|
	
Scenario Outline: Edit record to data service and verify available next states
	Given Data service "stringStateModel"
	Then Add record "<data>" to stateModel data service
	And Verify "<Next State>" is available
	And Verify "<Invalid State>" is not available
	And update "<Update State>" and save the record
Examples:
|data|Next State|Invalid State|Update State|
|{"_id": "STR1002","name": "Shyam","dob": "2000-05-21T10:20:30Z", "salary": 400000,"onboardingStatus": "Rejected"}|In Progress,Rejected,Deffered,Config Issue,Cannot Reproduce,Working as Expected|Closed,Ready for QA|Deffered|


Scenario Outline: Fetch record from the data service
	Given Data service "stringStateModel"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>" Date Type
Examples:
|id|data|
|STR1001|{"_id": "STR1001","name": "Ram","dob": "Thursday, November 30, 1995 (Zulu)", "salary": 100000}|

Scenario: Log out of App Center
	Given User log out from AppCenter