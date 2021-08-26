Feature: This is the string_Email feature file

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|deepak@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "boolean" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "boolean" does not exist
	Then Create new data service "boolean"
 #Under testData, picks up strings.json create the JSON


Scenario Outline: Assign to Appcenter Group
 	Then Group "boolean-Group" does not exist
	Then Create new group "boolean-Group" 
	And Assign appcenter permissions for "boolean" dataservice to "<user>"
	
	Examples:
	|user|
	|maker@appveen.com|

Scenario: Log out of Author
	Given User logged into Author
	Then User logs out of Author
	

#@AppCenter
Scenario Outline: Log into AppCenter
	Given User navigate to AppCenter login page
	And User enters "<username>" and "<password>" in AppCenter login page
	And Verify User has Logged in Successfully 
Examples:
|username|password|
|maker@appveen.com|123123123|


 #INSERT/UPDATE
Scenario: Add data to data service
	Given Data service "boolean"
	Then Add data to the data service for boolean

Scenario Outline: Add record to data service
	Given Data service "boolean"
	Then Add record "<data>" to the boolean data service	
	And Expect error "DS-BOOLEAN Error" on label "DS-BOOLEAN-1002 Label"
	And Save button is disabled
Examples:
|data|
|{"_id" : "DS1003", "dsBoolean1001" : false, "dsBoolean1002" : , "dsBoolean1003" : true, "dsBoolean1004" : false, "dsBoolean1005" : true, "dsBoolean1006" : true, "dsBoolean1008" : false, "dsBoolean1010" : true, "dsBoolean1011" : , "dsBoolean1012" : ,"dsBoolean1013" : false, "dsBoolean1014" : true, "dsBoolean1015" : false}|

#Scenario Outline: Add record to data service
#	Given Data service "boolean"
#	Then Add record "<data>" to the boolean data service	
#	And Expect error "ID DS1003 already exists" on save
#	Examples:
#		|data|
#	  |{"_id" : "DS1003", "dsBoolean1001" : false, "dsBoolean1002" : true, "dsBoolean1003" : false, "dsBoolean1004" : false, "dsBoolean1005" : true, "dsBoolean1006" : true, "dsBoolean1008" : false, "dsBoolean1010" : true, "dsBoolean1011" : false, "dsBoolean1012" : true,"dsBoolean1013" : false, "dsBoolean1014" : false, "dsBoolean1015" : false}|
	  

#	Scenario Outline: Fetch record from the data service
#	Given Data service "boolean"
#	Then Fetch record "<id>" from the data service
#	And Match it to "<data>" boolean
#Examples:
#|id|data|
#|DS1003|{"_id" : "DS1003", "dsBoolean1001" : "Yes", "dsBoolean1002" : "Yes", "dsBoolean1003" : "Yes", "dsBoolean1004" : "No", "dsBoolean1005" : "No", "dsBoolean1006" : "No", "dsBoolean1008" : "No", "dsBoolean1010" : "No", "dsBoolean1011" : "Yes", "dsBoolean1012" : "No","dsBoolean1013" : "Yes", "dsBoolean1014" : "Yes", "dsBoolean1015" : "Yes"}|
#
#	Scenario Outline: Update record to data service
#	Given Data service "boolean"
#	Then Update record "<id>" with "<data>" to the boolean data service
#Examples:
#|id|data|
#|DS1003|{"_id" : "DS1003", "dsBoolean1001" : false, "dsBoolean1002" : true, "dsBoolean1003" : false, "dsBoolean1004" : false, "dsBoolean1005" : false, "dsBoolean1006" : true, "dsBoolean1008" : true, "dsBoolean1010" : true, "dsBoolean1011" : false, "dsBoolean1012" : false,"dsBoolean1013" : true, "dsBoolean1014" : true, "dsBoolean1015" : false}|
#
#Scenario Outline: Fetch record from the data service
#	Given Data service "boolean"
#	Then Fetch record "<id>" from the data service
#	And Match it to "<data>" boolean
#Examples:
#|id|data|
#|DS1003|{"_id" : "DS1003", "dsBoolean1001" : "Yes", "dsBoolean1002" : "Yes", "dsBoolean1003" : "Yes", "dsBoolean1004" : "No", "dsBoolean1005" : "No", "dsBoolean1006" : "No", "dsBoolean1008" : "No", "dsBoolean1010" : "No", "dsBoolean1011" : "Yes", "dsBoolean1012" : "No","dsBoolean1013" : "Yes", "dsBoolean1014" : "Yes", "dsBoolean1015" : "Yes"}|

#Scenario Outline: Delete record from the data service
#	Given Data service "boolean"
#	Then Delete record "<id>" from the data service
#	And deleting from listing page
#Examples:
#|id|
#|DS1003|
#
