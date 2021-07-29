Feature: DS NUMBER LIST OF VALUES

# Scenarios - DS NUMBER LIST OF VALUES 1001-DS NUMBER LIST OF VALUES 1020

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|deepak@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "number_ListOfValue" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "number_ListOfValue" does not exist
	Then Create new data service "number_ListOfValue"
 #Under testData, picks up strings.json create the JSON

Scenario Outline: Assign to Appcenter Group
 	Then Group "Number-Group" does not exist
	Then Create new group "Number-Group" 
	And Assign appcenter permissions for "number_ListOfValue" dataservice to "<user>"
	
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
	Given Data service "number_ListOfValue"
	Then Add data to the data service
	
	
	Scenario Outline: Add record to data service
	Given Data service "number_ListOfValue"
	Then Add record "<data>" to the data service
	And Expect error "DS STRING TEXT Error" on label "DS STRING TEXT Label"
	And Save button is disabled
Examples:
|data|
|{"_id":"NUM1002", "dsNumberListOfValues1002":"", "dsNumberListOfValues1014":"","dsNumberListOfValues1015":"","dsNumberListOfValues1017":"","dsNumberListOfValues1018":""}|


Scenario Outline: Add record to data service
	Given Data service "number_ListOfValue"
	Then Add record "<data>" to the data service	
	And Expect error "ID NUM1001 already exists" on save
	Examples:
		|data|
	  |{ "_id" : "NUM1001",,"dsNumberListOfValues1001" : 1,"dsNumberListOfValues1002" : 2,"dsNumberListOfValues1003" : 3,"dsNumberListOfValues1007" : 4,"dsNumberListOfValues1008" : 5,"dsNumberListOfValues1014" : 4,"dsNumberListOfValues1015" : 5}|


Scenario Outline: Add record to data service
	Given Data service "number_ListOfValue"
	Then Add record "<data>" to the data service		
	And Expect error "Unique check validation failed for dsNumberListOfValues1002" on save
Examples:
|data|
|{ "_id" : "NUM1002","dsNumberListOfValues1001" : 10,"dsNumberListOfValues1002" : 3,"dsNumberListOfValues1003" : 3,"dsNumberListOfValues1007" : 7,"dsNumberListOfValues1008" : 8,"dsNumberListOfValues1014" : 14,"dsNumberListOfValues1015" : 15}|
	
	
	
	Scenario Outline: Fetch record from the data service
	Given Data service "number_ListOfValue"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>"
Examples:
|id|data|
|NUM1001|{"_id": "NUM1001","dsNumberListOfValues1001": 1,"dsNumberListOfValues1002": 2,"dsNumberListOfValues1003": 3,"dsNumberListOfValues1004": 3,"dsNumberListOfValues1005": 5,"dsNumberListOfValues1007": 5, "dsNumberListOfValues1008": 4, "dsNumberListOfValues1010": 4,  "dsNumberListOfValues1013": 3, "dsNumberListOfValues1014": 1, "dsNumberListOfValues1015": 5,  "dsNumberListOfValues1016": 3,  "dsNumberListOfValues1020": 4"}|
	
	
	Scenario Outline: Update record to data service
	Given Data service "number_ListOfValue"
	Then Update record "<id>" with "<data>" to the data service
Examples:

|id|data|
|NUM1001|{"dsNumberListOfValues1001": 1,"dsNumberListOfValues1002": 2,"dsNumberListOfValues1003": 3,"dsNumberListOfValues1004": 3,"dsNumberListOfValues1005": 5,"dsNumberListOfValues1007": 5, "dsNumberListOfValues1008": 4, "dsNumberListOfValues1010": 4,  "dsNumberListOfValues1013": 3, "dsNumberListOfValues1014": 1, "dsNumberListOfValues1015": 5,  "dsNumberListOfValues1016": 3,  "dsNumberListOfValues1020": 4, "dsNumberListOfValues1021": 1}|

Scenario Outline: Fetch record from the data service
	Given Data service "number_ListOfValue"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>"
Examples:
|id|data|
|NUM1001|{"_id": "NUM1001","dsNumberListOfValues1001": 1,"dsNumberListOfValues1002": 2,"dsNumberListOfValues1003": 3,"dsNumberListOfValues1004": 3,"dsNumberListOfValues1005": 5,"dsNumberListOfValues1007": 5, "dsNumberListOfValues1008": 4, "dsNumberListOfValues1010": 4,  "dsNumberListOfValues1013": 3, "dsNumberListOfValues1014": 1, "dsNumberListOfValues1015": 5,  "dsNumberListOfValues1016": 3,  "dsNumberListOfValues1020": 4}|
	
Scenario Outline: Delete record from the data service
	Given Data service "number_ListOfValue"
	Then Delete record "<id>" from the data service
	And deleting from listing page
Examples:
|id|
|NUM1001|





