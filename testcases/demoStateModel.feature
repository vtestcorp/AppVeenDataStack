Feature: DS STATE MODEL

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|test_ui@appveen.com|Test@123|

Scenario: Delete data service
	Given Data service "stateModel" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "stateModel" does not exist
	Then Create new data service "stateModel" for State_Model 
	
Scenario Outline: Add Attributes to data service
Given Add attributes "<attributeName>" and type "<attributeType>"
Examples:
	|attributeName|attributeType|
	|Number|ListOfValues|
	|String|ListOfValues|
	
	
Scenario Outline: Create State for State Model
Given Create states "<state_name>"
Examples:
|state_name|
|10|
|598.78|
|344.67|
|1289778.33|
|23423422.76|
|2342342|
|Meghana|
|Parvathy|
|Jugnu|

	
Scenario: Click On Experience tab
And Select the attribute 'Number' from drop down
And Verify all the list of values 
And Verify Next State column is empty


Scenario Outline: Configure the state model
And Set Current State with from state to next state
|10|1289778.33,23423422.76|
|598.78|10,598.78,344.67|
|344.67||
|1289778.33|2342342|
|23423422.76||
|2342342||
|76788|1289778.33,23423422.76|

Scenario Outline: Add new state
Given Add next state "76788"
And Set Current State with from state to next state
|76788|1289778.33,23423422.76|
Then Save and Deploy data service


Scenario Outline: Assign to Appcenter Group
	Then Group "stringStateModel" does not exist
	Then Create new group "stringStateModel" 
	And Assign appcenter permissions for "stringStateModel" dataservice to "<user>"
	Examples:
	|user|
	|demo@appveen.com|


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
|demo@appveen.com|Demo@123|

 #INSERT/UPDATE
Scenario Outline: Add data to data service
	Given Data service "stringStateModel"
	Then Add data to the stateModel data service
	And Verify the State is "<Current State>"
	Examples:
	|Current State|
	|10|


Scenario Outline: Edit record to data service and verify available next states
Given Data service "stringStateModel"
And Add data to the data service
And Verify "<Next State>" is available
And update "<Update State>" and save the record
Examples:
|Next State|Update State|
|1289778.33,23423422.76|1289778.33|


Scenario: Log out of App Center
Given User log out from AppCenter

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|test_ui@appveen.com|Test@123|

Scenario: Edit data service
	Given Edit Data service "stateModel" 
  And add new values to the "Number" attribute 
   |323|
   |567|
  And add new values to the "String" attribute
  |Harshitha|
  |Sushmitha|
  
Scenario: Click On Experience tab
And Select the attribute 'Number' from drop down
And Verify all the list of values 
And Verify Next State column is empty

Scenario Outline: Configure the state model
And Set Current State with from state to next state
|10|1289778.33,23423422.76|
|598.78|10,598.78,344.67|
|344.67||
|1289778.33|2342342|
|23423422.76||
|2342342||
|76788||
|323||
|567|10|
Then Save and Deploy data service
  