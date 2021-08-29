Feature: DS STRING LIST OF VALUES

# Scenarios - DS STRING LIST OF VALUES 1001 - DS STRING LIST OF VALUES 1020

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|deepak@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "string_ListOfValue" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "string_ListOfValue" does not exist
	Then Create new data service "string_ListOfValue"
 #Under testData, picks up strings.json create the JSON

Scenario Outline: Assign to Appcenter Group
 	Then Group "String-ListOfValue" does not exist
	Then Create new group "String-ListOfValue" 
	And Assign appcenter permissions for "string_ListOfValue" dataservice to "<user>"
	
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
	Given Data service "string_ListOfValue"
	Then Add data to the data service
	
	
Scenario Outline: Add record to data service
	Given Data service "string_ListOfValue"
	Then Add record "<data>" to the data service
	And Expect error "DS-STRING-LIST-OF-VALUES-1002 error" on label "DS-STRING-LIST-OF-VALUES-1002 label"
	And Save button is disabled
Examples:
|data|
|{"_id": "STR1001","dsStringListOfValues1001": "LIST 1","dsStringListOfValues1002": " ","dsStringListOfValues1003": "LIST 1","dsStringListOfValues1004": "LIST 1","dsStringListOfValues1005": "LIST 1","dsStringListOfValues1007": "LIST 1", "dsStringListOfValues1008": "LIST 1", "dsStringListOfValues1010": "LIST 1",  "dsStringListOfValues1013": "LIST 1", "dsStringListOfValues1014": " ", "dsStringListOfValues1015": " ",  "dsStringListOfValues1018": " ", "dsStringListOfValues1020":"LIST 1"}|


Scenario Outline: Add record to data service
	Given Data service "string_ListOfValue"
	Then Add record "<data>" to the data service	
	And Expect error "ID STR1001 already exists" on save
	Examples:
		|data|
#|{ "_id" : "STR1001","dsStringListOfValues1002" : "LIST 2","dsStringListOfValues1003" : "LIST 1","dsStringListOfValues1007" : "LIST 1","dsStringListOfValues1008" : "LIST 1","dsStringListOfValues1014" : "LIST 1","dsStringListOfValues1015" : "LIST 1"}|
  |{"_id" : "STR1001","dsStringListOfValues1001" : "LIST 1","dsStringListOfValues1002" : "LIST 2", "dsStringListOfValues1003" : "LIST 2","dsStringListOfValues1004" : "LIST 1","dsStringListOfValues1005" : "LIST 2","dsStringListOfValues1007" : "LIST 2","dsStringListOfValues1008" : "LIST 1","dsStringListOfValues1010" : "LIST 1","dsStringListOfValues1013" : "LIST 1","dsStringListOfValues1014" : "LIST 1","dsStringListOfValues1015" : "LIST 1", "dsStringListOfValues1018" : "LIST 2","dsStringListOfValues1020" : "LIST 1"}| 	
    

Scenario Outline: Add record to data service
	Given Data service "string_ListOfValue"
	Then Add record "<data>" to the data service		
	And Expect error "Unique check validation failed for dsStringListOfValues1002" on save
Examples:
|data|
#|{ "_id" : "STR1003","dsStringListOfValues1001" : "LIST 1","dsStringListOfValues1002" : "LIST 1","dsStringListOfValues1003" : "LIST 1","dsStringListOfValues1007" : "LIST 1","dsStringListOfValues1008" : "LIST 1","dsStringListOfValues1014" : "LIST 1","dsStringListOfValues1015" : "LIST 1"}|
|{"_id" : "STR1003","dsStringListOfValues1001" : "LIST 1","dsStringListOfValues1002" : "LIST 1", "dsStringListOfValues1003" : "LIST 1","dsStringListOfValues1004" : "LIST 1","dsStringListOfValues1005" : "LIST 1","dsStringListOfValues1007" : "LIST 1","dsStringListOfValues1008" : "LIST 1","dsStringListOfValues1010" : "LIST 1","dsStringListOfValues1013" : "LIST 1","dsStringListOfValues1014" : "LIST 1","dsStringListOfValues1015" : "LIST 1", "dsStringListOfValues1018" : "LIST 2","dsStringListOfValues1020" : "LIST 1"}| 	

	
	
	Scenario Outline: Fetch record from the data service
	Given Data service "string_ListOfValue"
	Then Fetch record "<id>" from the data service 
	And Match it to "<data>"
Examples:
|id|data|
#|STR1001|{"_id": "STR1001","dsStringListOfValues1001": "LIST 1","dsStringListOfValues1002": "LIST 2","dsStringListOfValues1003": "LIST 1","dsStringListOfValues1004": "LIST 1","dsStringListOfValues1005": "LIST 1","dsStringListOfValues1007": "LIST 1", "dsStringListOfValues1008": "LIST 1", "dsStringListOfValues1010": "LIST 1",  "dsStringListOfValues1013": "LIST 1", "dsStringListOfValues1014": "LIST 1", "dsStringListOfValues1015": "LIST 1",  "dsStringListOfValues1018": "LIST 2", "dsStringListOfValues1020":"LIST 1"}|
|STR1001|{"_id" : "STR1001","dsStringListOfValues1001" : "LIST 1","dsStringListOfValues1002" : "LIST 1", "dsStringListOfValues1003" : "LIST 1","dsStringListOfValues1004" : "LIST 1","dsStringListOfValues1005" : "LIST 1","dsStringListOfValues1007" : "LIST 1","dsStringListOfValues1008" : "LIST 1","dsStringListOfValues1010" : "LIST 1","dsStringListOfValues1013" : "LIST 1","dsStringListOfValues1014" : "LIST 1","dsStringListOfValues1015" : "LIST 1", "dsStringListOfValues1018" : "LIST 2","dsStringListOfValues1020" : "LIST 1"}| 	

	Scenario Outline: Update record to data service
	Given Data service "string_ListOfValue"
	Then Update record "<id>" with "<data>" to the data service
Examples:

|id|data|
#|STR1001|{"dsStringListOfValues1001": "LIST 2","dsStringListOfValues1002": "LIST 2","dsStringListOfValues1003": "LIST 2","dsStringListOfValues1004": "LIST 2","dsStringListOfValues1005": "LIST 2","dsStringListOfValues1007": "LIST 2", "dsStringListOfValues1008": "LIST 2", "dsStringListOfValues1010": "LIST 2",  "dsStringListOfValues1013": "LIST 2", "dsStringText1014": "LIST 2", "dsStringListOfValues1015": "LIST 2",  "dsStringListOfValues1018": "LIST 2"}|
|STR1001|{"_id" : "STR1001","dsStringListOfValues1001" : "LIST 2","dsStringListOfValues1002" : "LIST 2", "dsStringListOfValues1003" : "LIST 2","dsStringListOfValues1004" : "LIST 2","dsStringListOfValues1005" : "LIST 2","dsStringListOfValues1007" : "LIST 1","dsStringListOfValues1008" : "LIST 1","dsStringListOfValues1010" : "LIST 2","dsStringListOfValues1013" : "LIST 2","dsStringListOfValues1014" : "LIST 2","dsStringListOfValues1015" : "LIST 2", "dsStringListOfValues1018" : "LIST 2","dsStringListOfValues1020" : "LIST 2"}| 

Scenario Outline: Fetch record from the data service
	Given Data service "string_ListOfValue"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>"
Examples:
|id|data|
|STR1001|{"_id": "STR1001","dsStringListOfValues1001": "LIST 2","dsStringListOfValues1002": "LIST 1","dsStringListOfValues1003": "LIST 2","dsStringListOfValues1004": "LIST 1","dsStringListOfValues1005": "LIST 2","dsStringListOfValues1007": "LIST 1", "dsStringListOfValues1008": "LIST 1", "dsStringListOfValues1010": "LIST 1",  "dsStringListOfValues1013": "LIST 2", "dsStringListOfValues1014": "LIST 2", "dsStringListOfValues1015": "LIST 2",  "dsStringListOfValues1018": "LIST 2","dsStringListOfValues1020":"LIST 2"}|
#|STR1001|{"_id" : "STR1001","dsStringListOfValues1001" : "LIST 1","dsStringListOfValues1002" : "LIST 1", "dsStringListOfValues1003" : "LIST 1","dsStringListOfValues1004" : "LIST 1","dsStringListOfValues1005" : "LIST 1","dsStringListOfValues1007" : "LIST 1","dsStringListOfValues1008" : "LIST 1","dsStringListOfValues1010" : "LIST 1","dsStringListOfValues1013" : "LIST 1","dsStringListOfValues1014" : "LIST 1","dsStringListOfValues1015" : "LIST 1","dsStringListOfValues1018" : "LIST 2","dsStringListOfValues1020" : "LIST 1"}| 

Scenario Outline: Delete record from the data service
	Given Data service "string_ListOfValue"
	Then Delete record "<id>" from the data service
	And deleting from listing page
Examples:
|id|
|STR1001|

Scenario: Log out of App Center
	Given User log out from AppCenter