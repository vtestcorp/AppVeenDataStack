Feature: DS GROUP

# Scenarios - DS GROUP 1001-DS GROUP 1006

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|deepak@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "ds_Group" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "ds_Group" does not exist
	Then Create new data service "ds_Group"
 #Under testData, picks up strings.json create the JSON

Scenario Outline: Assign to Appcenter Group
 	Then Group "DS Group" does not exist
	Then Create new group "DS Group" 
	And Assign appcenter permissions for "ds_Group" dataservice to "<user>"
	
	Examples:
	|user|
	|maker@appveen.com|


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
|maker@appveen.com|123123123|


# INSERT/UPDATE
Scenario: Add data to data service
	Given Data service "ds_Group"
	Then Add data to the data service for Group
	
	

Scenario Outline: Add record to data service
	Given Data service "ds_Group"
	Then Add record "<data>" to the data service	
	And Expect error "ID STR1001 already exists" on save
	Examples:
		|data|
	  |{  "_id" : "DS1001", "dsGroup1001" : { "dsString" : "String 1111" }, "dsGroup1002" : { "dsString" : "String 122" }, "dsGroup1003" : { "dsString" : "String 1333" }, "dsGroup1004" : { "dsString" : "String 1444" }, "dsGroup1005" : { "dsString" : "String 1555" }, "dsGroup1006" : { "dsString" : "String 1666" }}|
#		|{  "_id" : "DS1003", "dsGroup1001" : { "dsString" : "String 1111" }, "dsGroup1002" : { "dsString" : "String 122" }, "dsGroup1003" : { "dsString" : "String 1333" }, "dsGroup1004" : { "dsString" : "String 1444" }, "dsGroup1005" : { "dsString" : "String 1555" }, "dsGroup1006" : { "dsString" : "String 1666" }}|
	
	
	Scenario Outline: Fetch record from the data service
	Given Data service "ds_Group"
	Then Fetch record "<id>" from the data service
	And Match this GROUP data to "<data>"
Examples:
|id|data|
|DS1001|{"_id":"DS1001", "dsGroup1001.dsString":"String 1001", "dsGroup1002.dsString":"String 1002", "dsGroup1003.dsString":"String 1003", "dsGroup1004.dsString":"String 1004", "dsGroup1005.dsString":"String 1005", "dsGroup1006.dsString":"String 1006"}|
	
	
	Scenario Outline: Update record to data service
	Given Data service "ds_Group"
	Then Update record "<id>" with "<data>" to the data service
Examples:

|id|data|
|DS1001|{  "dsGroup1001" : { "dsString" : "String 1111" }, "dsGroup1002" : { "dsString" : "String 2222" }, "dsGroup1003" : { "dsString" : "String 3333" }, "dsGroup1004" : { "dsString" : "String 4444" }, "dsGroup1005" : { "dsString" : "String 5555" }, "dsGroup1006" : { "dsString" : "String 6666" }}|


Scenario Outline: Fetch record from the data service
	Given Data service "ds_Group"
	Then Fetch record "<id>" from the data service
	And Match this GROUP data to "<data>"
Examples:
|id|data|
|DS1001|{"_id":"DS1001", "dsGroup1001.dsString":"String 1111", "dsGroup1002.dsString":"String 2222", "dsGroup1003.dsString":"String 3333", "dsGroup1004.dsString":"String 4444", "dsGroup1005.dsString":"String 5555", "dsGroup1006.dsString":"String 6666"}|
#	
#Scenario Outline: Delete record from the data service
#	Given Data service "ds_Group"
#	Then Delete record "<id>" from the data service
#	And deleting from listing page
#Examples:
#|id|
#|STR1001|
#

#Scenario: Log out of App Center
#	Given User log out from AppCenter

#
#
#
#
