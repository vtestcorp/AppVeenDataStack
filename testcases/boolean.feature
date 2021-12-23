Feature: DS-Boolean

# Scenarios - DS-BOOLEAN-1001 - DS-BOOLEAN-1015

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|test_ui_appadmin@appveen.com | Veen@99%win |

#Scenario: Stop Data Service
#Given Stop dataservice "boolean"

Scenario: Delete data service
	Given Data service "boolean" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "boolean" does not exist
	Then Create new data service "boolean"

Scenario Outline: Assign to Appcenter Group
 	Then Group "boolean-Group" does not exist
	Then Create new group "boolean-Group" 
	And Assign appcenter permissions for "boolean" dataservice to "<user>"
	
	Examples:
	|user|
	|test_ui_ac_ds_manage@appveen.com |

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
|test_ui_ac_ds_manage@appveen.com   |Veen@99%win|


Scenario: Add data to data service
	Given Data service "boolean"
	Then Add data to the data service for boolean


Scenario Outline: Add record to data service
	Given Data service "boolean"
	Then Add record "<data>" to the boolean data service	
	And Expect error "ID DS1001 already exists." on save
	Examples:
		|data|
	  |{"_id" : "DS1001", "dsBoolean1001" : false, "dsBoolean1002" : true, "dsBoolean1003" : false, "dsBoolean1004" : false, "dsBoolean1005" : true, "dsBoolean1006" : true, "dsBoolean1008" : false, "dsBoolean1010" : true, "dsBoolean1011" : false, "dsBoolean1012" : true,"dsBoolean1013" : false, "dsBoolean1014" : false, "dsBoolean1015" : false}|
	  

	Scenario Outline: Fetch record from the data service
	Given Data service "boolean"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>" boolean
Examples:
|id|data|
|DS1001|{"_id" : "DS1001", "dsBoolean1001" : "Yes", "dsBoolean1002" : "Yes", "dsBoolean1003" : "Yes", "dsBoolean1004" : "No", "dsBoolean1005" : "No", "dsBoolean1006" : "No", "dsBoolean1008" : "No", "dsBoolean1010" : "No", "dsBoolean1011" : "Yes", "dsBoolean1012" : "No","dsBoolean1013" : "Yes", "dsBoolean1014" : "Yes", "dsBoolean1015" : "Yes"}|

	Scenario Outline: Update record to data service
	Given Data service "boolean"
	Then Update record "<id>" with "<data>" to the boolean data service
Examples:
|id|data|
|DS1001|{"dsBoolean1001" : false, "dsBoolean1002" : true, "dsBoolean1003" : false, "dsBoolean1004" : false, "dsBoolean1005" : false, "dsBoolean1006" : true, "dsBoolean1008" : true, "dsBoolean1010" : true, "dsBoolean1011" : false, "dsBoolean1012" : false,"dsBoolean1013" : true, "dsBoolean1014" : true, "dsBoolean1015" : false}|

Scenario Outline: Fetch record from the data service
	Given Data service "boolean"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>" boolean
Examples:
|id|data|
|DS1001|{"_id" : "DS1001", "dsBoolean1001" : "Yes", "dsBoolean1002" : "Yes", "dsBoolean1003" : "Yes", "dsBoolean1004" : "No", "dsBoolean1005" : "No", "dsBoolean1006" : "No", "dsBoolean1008" : "No", "dsBoolean1010" : "Yes", "dsBoolean1011" : "Yes", "dsBoolean1012" : "No","dsBoolean1013" : "No", "dsBoolean1014" : "Yes", "dsBoolean1015" : "Yes"}|

Scenario Outline: Delete record from the data service
	Given Data service "boolean"
	Then Delete record "<id>" from the data service
	And Deleting from listing page
Examples:
|id|
|DS1001|

Scenario Outline: Log into Author
	Given User navigate to Author login page again 
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|test_ui_appadmin@appveen.com | Veen@99%win |

Scenario: Stop Data Service
Given Stop dataservice "boolean"

Scenario: Log out of App Center
Given User log out from AppCenter


