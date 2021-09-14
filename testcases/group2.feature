Feature: DS GROUP 

# Scenarios - DS GROUP STRING TEXT - DS GROUP COLLECTION

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|test_appadmin@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "group2" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "group2" does not exist
	Then Create new data service "group2"
 #Under testData, picks up strings.json create the JSON

Scenario Outline: Assign to Appcenter Group
 	Then Group "DS Group" does not exist
	Then Create new group "DS Group" 
	And Assign appcenter permissions for "group2" dataservice to "<user>"
	
	Examples:
	|user|
	|test_ac_ds_manage@appveen.com|
#
#
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
	Given Data service "group2"
	Then Add data to the data service for Group

Scenario Outline: Add record to data service
	Given Data service "group2"
	Then Add record "<data>" to the group	
	And Expect error "ID STR1001 already exists" on save
	Examples:
		|data|
	  |{ "dsGroupLibrary" : { "library" : { "line1" : "Update Library Line 1 ", "line2" : "Update Library Line 2" } }, "_id" : "DS1001", "dsGroupStringText" : { "stringText" : "Update Group Text" }, "dsGroupStringEmail" : { "email" : "xyz@gmail.com" }, "dsGroupStringListOfValues" : { "listOfValues" : "XYZ" }, "dsGroupNumberNumber" : { "number" : 999 }, "dsGroupNumberCurrency" : { "currency" : 11 }, "dsGroupNumberListOfValues" : { "listOfValues" : 999 }, "dsGroupBoolean" : { "boolean" : true }, "dsGroupDate" : { "date" : { "rawData" : "2025-11-27T00:00:00Z" } }, "dsGroupDateTime" : { "dateTime" : { "rawData" : "2030-06-11T08:20:30Z" } }, "dsGroupLocation" : { "location" : { "userInput" : "Solapur, Maharashtra, India" } }, "dsGroupFile" : { "file" : { "metadata" : { "filename" : "Untitled.png" } } }, "dsGroupUser" : { "user" : { "_id" : "deepak@appveen.com" } } }|

	Scenario Outline: Fetch record from the data service
	Given Data service "group2"
	Then Fetch record "<id>" from the data service
	And Match this GROUP data to "<data>"
Examples:
|id|data|
|DS1001|{"_id": "DS1001","dsStringText1001": "1001","dsStringText1002": "a","dsStringText1003": "1003","dsStringText1004": "1004","dsStringText1005": "1005","dsStringText1007": "1007", "dsStringText1008": "1008", "dsStringText1010": "1010",  "dsStringText1013": "1013", "dsStringText1014": "1014", "dsStringText1015": "1015",  "dsStringText1018": "A",  "dsStringText1020": "ABCD", "dsStringText1021": "1021", "dsStringText1022": "1022", "dsStringText1023": "1023", "dsStringText1024": "1024"}|
	

	Scenario Outline: Update record to data service
	Given Data service "group2"
	Then Update record "<id>" with "<data>" to the data service
Examples:

|id|data|
|STR1001|{"dsStringText1001": "1111","dsStringText1002": "b","dsStringText1003": "1333","dsStringText1004": "1444","dsStringText1005": "1555","dsStringText1007": "1777", "dsStringText1008": "1888", "dsStringText1010": "101010",  "dsStringText1013": "101313", "dsStringText1014": "101414", "dsStringText1015": "101515", "dsStringText1018": "101818", "dsStringText1020": "ABCDEFGH", "dsStringText1021": "102121", "dsStringText1022": "102222", "dsStringText1023": "102323", "dsStringText1024": "102424"}|

Scenario Outline: Fetch record from the data service
	Given Data service "group2"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>"
Examples:
|id|data|
|STR1001|{"_id": "STR1001","dsStringText1001": "1111","dsStringText1002": "a","dsStringText1003": "1333","dsStringText1004": "1004","dsStringText1005": "1555","dsStringText1007": "1007", "dsStringText1008": "1888", "dsStringText1010": "1010",  "dsStringText1013": "101313", "dsStringText1014": "101414", "dsStringText1015": "101515", "dsStringText1018": "101818", "dsStringText1020": "ABCDEFGH", "dsStringText1021": "102121", "dsStringText1022": "102222", "dsStringText1023": "102323", "dsStringText1024": "102424"}|
	
Scenario Outline: Delete record from the data service
	Given Data service "group2"
	Then Delete record "<id>" from the data service
	And Deleting from listing page
Examples:
|id|
|STR1001|
