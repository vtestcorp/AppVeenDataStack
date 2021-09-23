Feature: DS LIBRARY

# Scenarios - DS LIBRARY 1001-DS LIBRARY 1008

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|test_appadmin@appveen.com|123123123|

Scenario: Create Library
#	Given Create "Address" Library

Scenario: Delete data service
	Given Data service "library" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "library" does not exist
	Then Create new data service "library"
 #Under testData, picks up strings.json create the JSON

Scenario Outline: Assign to Appcenter Group
 	Then Group "Library-Group" does not exist
	Then Create new group "Library-Group" 
	And Assign appcenter permissions for "library" dataservice to "<user>"
	
	Examples:
	|user|
	|test_ac_ds_manage@appveen.com|


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
|test_ac_ds_manage@appveen.com|123123123|


 #INSERT/UPDATE
Scenario: Add data to data service
	Given Data service "library"
	Then Add data to the data service for Library Type
	
	

Scenario Outline: Add record to data service
	Given Data service "library"
	Then Add record "<data>" to the data service	
	And Expect error "ID DS1001 already exists" on save
	Examples:
		|data|
	  |{ "_id" : "DS1001", "dsLibrary1001" : { "line1" : "1001", "line2" : "1002" }, "dsLibrary1002" : { "line1" : null, "line2" : null }, "dsLibrary1003" : { "line1" : "3001", "line2" : "3002" }, "dsLibrary1004" : { "line1" : "4001", "line2" : "4002" }, "dsLibrary1005" : { "line1" : "5001", "line2" : "5002" }, "dsLibrary1006" : { "line1" : "6001", "line2" : "6002" }, "dsLibrary1007" : { "line1" : "7001", "line2" : "7002" }, "dsLibrary1008" : { "line1" : "8001", "line2" : "8002" } }|



	Scenario Outline: Fetch record from the data service
	Given Data service "library"
	Then Fetch record "<id>" from the data service
	And Match this Library data to "<data>"
Examples:
|id|data|
|DS1001|{"_id":"DS1001", "dsLibrary1001.line1":"Library 1001", "dsLibrary1001.line2":"Library 1002", "dsLibrary1003.line1":"Library 3001", "dsLibrary1003.line2":"Library 3002", "dsLibrary1005.line1":"Library 5001", "dsLibrary1005.line2":"Library 5002", "dsLibrary1006.line1":"Library 6001", "dsLibrary1006.line2":"Library 6002", "dsLibrary1007.line1":"Library 7001", "dsLibrary1007.line2":"Library 7002", "dsLibrary1008.line1":"Library 8001", "dsLibrary1008.line2":"Library 8002"}|
	
	
	Scenario Outline: Update record to data service
	Given Data service "library"
	Then Update record "<id>" with "<data>" to the data service
Examples:

|id|data|
|DS1001|{ "_id" : "DS1001", "dsLibrary1001" : { "line1" : "Library 11", "line2" : "Library 12" }, "dsLibrary1003" : { "line1" : "Library 31", "line2" : "Library 32" }, "dsLibrary1004" : { "line1" : "Library 41", "line2" : "Library 42" }, "dsLibrary1005" : { "line1" : "Library 51", "line2" : "Library 52" }, "dsLibrary1006" : { "line1" : "Library 61", "line2" : "Library 62" }, "dsLibrary1007" : { "line1" : "Library 71", "line2" : "Library 72" }, "dsLibrary1008" : { "line1" : "Library 81", "line2" : "Library 82" } }|

Scenario Outline: Fetch record from the data service
	Given Data service "library"
	Then Fetch record "<id>" from the data service
	And Match this Library data to "<data>"
Examples:
|id|data|
|DS1001|{"_id":"DS1001", "dsLibrary1001.line1":"Library 11", "dsLibrary1001.line2":"Library 12", "dsLibrary1003.line1":"Library 31", "dsLibrary1003.line2":"Library 32", "dsLibrary1005.line1":"Library 51", "dsLibrary1005.line2":"Library 52", "dsLibrary1006.line1":"Library 61", "dsLibrary1006.line2":"Library 62", "dsLibrary1007.line1":"Library 71", "dsLibrary1007.line2":"Library 72", "dsLibrary1008.line1":"Library 81", "dsLibrary1008.line2":"Library 82"}|

Scenario Outline: Delete record from the data service
	Given Data service "library"
	Then Delete record "<id>" from the data service
	And Deleting from listing page
Examples:
|id|
|DS1001|

Scenario: Log out of App Center
	Given User log out from AppCenter

