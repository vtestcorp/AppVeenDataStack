Feature: DS DATE

# Scenarios - DS DATE 1001-DS DATE 1017

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|deepak@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "ds_Library" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "ds_Library" does not exist
	Then Create new data service "ds_Library"
 #Under testData, picks up strings.json create the JSON

Scenario Outline: Assign to Appcenter Group
 	Then Group "Library-Group" does not exist
	Then Create new group "Library-Group" 
	And Assign appcenter permissions for "ds_Library" dataservice to "<user>"
	
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


 #INSERT/UPDATE
Scenario: Add data to data service
	Given Data service "ds_Library"
	Then Add data to the data service for Library Type
	
	
#	Scenario Outline: Add record to data service
#	Given Data service "ds_Library"
#	Then Add record "<data>" to the data service
#	And Expect error "DS STRING TEXT Error" on label "DS STRING TEXT Label"
#	And Save button is disabled
#Examples:
#|data|
#|{"_id":"STR1002", "dsStringText1002":"", "dsStringText1014":"","dsStringText1015":"","dsStringText1017":"","dsStringText1018":""}|
#
#
Scenario Outline: Add record to data service
	Given Data service "ds_Library"
	Then Add record "<data>" to the data service	
	And Expect error "ID DS1001 already exists" on save
	Examples:
		|data|
	  |{ "_id" : "DS1001", "dsLibrary1001" : { "line1" : "1001", "line2" : "1002" }, "dsLibrary1002" : { "line1" : null, "line2" : null }, "dsLibrary1003" : { "line1" : "3001", "line2" : "3002" }, "dsLibrary1004" : { "line1" : "4001", "line2" : "4002" }, "dsLibrary1005" : { "line1" : "5001", "line2" : "5002" }, "dsLibrary1006" : { "line1" : "6001", "line2" : "6002" }, "dsLibrary1007" : { "line1" : "7001", "line2" : "7002" }, "dsLibrary1008" : { "line1" : "8001", "line2" : "8002" } }|



	Scenario Outline: Fetch record from the data service
	Given Data service "ds_Library"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>"
Examples:
|id|data|
|DS1001|{ "_id" : "DS1001", "dsLibrary1001" : { "line1" : "Library 1001", "line2" : "Library 1002" }, "dsLibrary1002" : { "line1" : null, "line2" : null }, "dsLibrary1003" : { "line1" : "Library 3001", "line2" : "Library 3002" }, "dsLibrary1004" : { "line1" : "Library 4001", "line2" : "Library 4002" }, "dsLibrary1005" : { "line1" : "Library 5001", "line2" : "Library 5002" }, "dsLibrary1006" : { "line1" : "Library 6001", "line2" : "Library 6002" }, "dsLibrary1007" : { "line1" : "Library 7001", "line2" : "Library 7002" }, "dsLibrary1008" : { "line1" : "Library 8001", "line2" : "Library 8002" } }|
	
	
	Scenario Outline: Update record to data service
	Given Data service "ds_Library"
	Then Update record "<id>" with "<data>" to the data service
Examples:

|id|data|
|DS1001|{ "_id" : "DS1001", "dsLibrary1001" : { "line1" : "Library 11", "line2" : "Library 12" }, "dsLibrary1002" : { "line1" : null, "line2" : null }, "dsLibrary1003" : { "line1" : "Library 31", "line2" : "Library 32" }, "dsLibrary1004" : { "line1" : "Library 41", "line2" : "Library 42" }, "dsLibrary1005" : { "line1" : "Library 51", "line2" : "Library 52" }, "dsLibrary1006" : { "line1" : "Library 61", "line2" : "Library 62" }, "dsLibrary1007" : { "line1" : "Library 71", "line2" : "Library 72" }, "dsLibrary1008" : { "line1" : "Library 81", "line2" : "Library 82" } }|
#
#Scenario Outline: Fetch record from the data service
#	Given Data service "ds_Library"
#	Then Fetch record "<id>" from the data service
#	And Match it to "<data>"
#Examples:
#|id|data|
#|STR1001|{"_id": "STR1001","dsStringText1001": "1111","dsStringText1002": "a","dsStringText1003": "1333","dsStringText1004": "1004","dsStringText1005": "1555","dsStringText1007": "1007", "dsStringText1008": "1888", "dsStringText1010": "1010",  "dsStringText1013": "101313", "dsStringText1014": "101414", "dsStringText1015": "101515", "dsStringText1018": "101818", "dsStringText1020": "ABCDEFGH", "dsStringText1021": "102121", "dsStringText1022": "102222", "dsStringText1023": "102323", "dsStringText1024": "102424"}|
#	
#Scenario Outline: Delete record from the data service
#	Given Data service "ds_Library"
#	Then Delete record "<id>" from the data service
#	And deleting from listing page
#Examples:
#|id|
#|STR1001|
#
#
#
#
#
