Feature: DS GROUP 

# Scenarios - DS GROUP STRING TEXT - DS GROUP COLLECTION

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|deepak@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "ds_Group-2" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "ds_Group-2" does not exist
	Then Create new data service "ds_Group-2"
 #Under testData, picks up strings.json create the JSON

Scenario Outline: Assign to Appcenter Group
 	Then Group "String-Group" does not exist
	Then Create new group "String-Group" 
	And Assign appcenter permissions for "ds_Group-2" dataservice to "<user>"
	
	Examples:
	|user|
	|maker@appveen.com|
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
|maker@appveen.com|123123123|


 #INSERT/UPDATE
Scenario: Add data to data service
	Given Data service "ds_Group-2"
	Then Add data to the data service
#	
#	
#	Scenario Outline: Add record to data service
#	Given Data service "ds_Group-2"
#	Then Add record "<data>" to the data service
#	And Expect error "DS STRING TEXT Error" on label "DS STRING TEXT Label"
#	And Save button is disabled
#Examples:
#|data|
#|{"_id":"STR1002", "dsStringText1002":"", "dsStringText1014":"","dsStringText1015":"","dsStringText1017":"","dsStringText1018":""}|
#
#
#Scenario Outline: Add record to data service
#	Given Data service "ds_Group-2"
#	Then Add record "<data>" to the data service	
#	And Expect error "ID STR1001 already exists" on save
#	Examples:
#		|data|
#	  |{ "_id" : "STR1001","dsStringText1001" : "1001","dsStringText1002" : "c","dsStringText1003" : "3","dsStringText1007" : "7","dsStringText1008" : "18","dsStringText1014" : "14","dsStringText1015" : "15"}|
#
#
#Scenario Outline: Add record to data service
#	Given Data service "ds_Group-2"
#	Then Add record "<data>" to the data service		
#	And Expect error "Unique check validation failed for dsStringText1002" on save
#Examples:
#|data|
#|{ "_id" : "STR1003","dsStringText1001" : "1001","dsStringText1002" : "a","dsStringText1003" : "3","dsStringText1007" : "7","dsStringText1008" : "18","dsStringText1014" : "14","dsStringText1015" : "15"}|
#	
#	
#	
#	Scenario Outline: Fetch record from the data service
#	Given Data service "ds_Group-2"
#	Then Fetch record "<id>" from the data service
#	And Match it to "<data>"
#Examples:
#|id|data|
#|STR1001|{"_id": "STR1001","dsStringText1001": "1001","dsStringText1002": "a","dsStringText1003": "1003","dsStringText1004": "1004","dsStringText1005": "1005","dsStringText1007": "1007", "dsStringText1008": "1008", "dsStringText1010": "1010",  "dsStringText1013": "1013", "dsStringText1014": "1014", "dsStringText1015": "1015",  "dsStringText1018": "A",  "dsStringText1020": "ABCD", "dsStringText1021": "1021", "dsStringText1022": "1022", "dsStringText1023": "1023", "dsStringText1024": "1024"}|
#	
#	
#	Scenario Outline: Update record to data service
#	Given Data service "ds_Group-2"
#	Then Update record "<id>" with "<data>" to the data service
#Examples:
#
#|id|data|
#|STR1001|{"dsStringText1001": "1111","dsStringText1002": "b","dsStringText1003": "1333","dsStringText1004": "1444","dsStringText1005": "1555","dsStringText1007": "1777", "dsStringText1008": "1888", "dsStringText1010": "101010",  "dsStringText1013": "101313", "dsStringText1014": "101414", "dsStringText1015": "101515", "dsStringText1018": "101818", "dsStringText1020": "ABCDEFGH", "dsStringText1021": "102121", "dsStringText1022": "102222", "dsStringText1023": "102323", "dsStringText1024": "102424"}|
#
#Scenario Outline: Fetch record from the data service
#	Given Data service "ds_Group-2"
#	Then Fetch record "<id>" from the data service
#	And Match it to "<data>"
#Examples:
#|id|data|
#|STR1001|{"_id": "STR1001","dsStringText1001": "1111","dsStringText1002": "a","dsStringText1003": "1333","dsStringText1004": "1004","dsStringText1005": "1555","dsStringText1007": "1007", "dsStringText1008": "1888", "dsStringText1010": "1010",  "dsStringText1013": "101313", "dsStringText1014": "101414", "dsStringText1015": "101515", "dsStringText1018": "101818", "dsStringText1020": "ABCDEFGH", "dsStringText1021": "102121", "dsStringText1022": "102222", "dsStringText1023": "102323", "dsStringText1024": "102424"}|
#	
#Scenario Outline: Delete record from the data service
#	Given Data service "ds_Group-2"
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
