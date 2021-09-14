Feature: DS RELATION

# Scenarios - DS STRING TEXT 1001-DS STRING TEXT 1024

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|test_appadmin@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "relation" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "relation" does not exist
	Then Create new data service "relation"
 #Under testData, picks up strings.json create the JSON

Scenario Outline: Assign to Appcenter Group
 	Then Group "Relation-Group" does not exist
	Then Create new group "Relation-Group" 
	And Assign appcenter permissions for "relation" dataservice to "<user>"
	
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


# INSERT/UPDATE
Scenario: Add data to data service
	Given Data service "sample"
	Then Add data to the data service
	
#	Scenario Outline: Add record to data service
#	Given Data service "sample"
#	Then Add record "<data>" to the data service
#	Examples:
#|data|
#|{"_id": "STR1005","string1": "Myntra","number1": 1234,"email": "ycm@gmail.com","string2": "Upstox"}|
#|{"_id": "STR1006","string1": "Zerodha","number1": 4321,"email": "xyz@gmail.com","string2": "Samco"}|
#|{"_id": "STR1007","string1": "Swiggy","number1": 5555,"email": "pqr@gmail.com","string2": "Zomato"}|
#	
	Scenario: Add data to data service
	Given Data service "relation"
	Then Add data to the data service for Relation
	
	Scenario Outline: Add record to data service
	Given Data service "relation"
	Then Add record "<data>" to the data service
	And Expect error "DS RELATION 1002 ERROR" on label "DS RELATION 1002 LABEL"
	And Save button is disabled
Examples:
|data|
|{"_id": "REL1002","dsRelation1001": "SAM1003","dsRelation1002": "","dsRelation1003": "Flipkart","dsRelation1004": "SAM1003","dsRelation1005": "SAM1002","dsRelation1007": "SAM1001", "dsRelation1008": "SAM1003", "dsRelation1010": "SAM1002",  "dsRelation1013": "SAM1003", "dsRelation1014": "", "dsRelation1015": "", "dsRelation1016": "SAM1003",  "dsRelation1018": "Flipkart", "dsRelation1019": "Sam", "dsRelation1020": "xyz@gmail.com"}|


Scenario Outline: Add record to data service
	Given Data service "relation"
	Then Add record "<data>" to the data service	
	And Expect error "ID REL1001 already exists" on save
	Examples:
		|data|
|{"_id": "REL1001","dsRelation1001": "SAM1003","dsRelation1002": "5555","dsRelation1003": "Flipkart","dsRelation1004": "SAM1003","dsRelation1005": "SAM1002","dsRelation1007": "SAM1001", "dsRelation1008": "SAM1003", "dsRelation1010": "SAM1002",  "dsRelation1013": "SAM1003", "dsRelation1014": "SAM1002", "dsRelation1015": "SAM1001", "dsRelation1016": "SAM1003",  "dsRelation1018": "Flipkart", "dsRelation1019": "Sam", "dsRelation1020": "xyz@gmail.com"}|	


Scenario Outline: Add record to data service
	Given Data service "relation"
	Then Add record "<data>" to the data service		
	And Expect error "Unique check validation failed for dsRelation1002" on save
Examples:
|data|
|{"_id": "REL1003","dsRelation1001": "SAM1003","dsRelation1002": 5555,"dsRelation1003": "Flipkart","dsRelation1004": "SAM1003","dsRelation1005": "SAM1002","dsRelation1007": "SAM1001", "dsRelation1008": "SAM1003", "dsRelation1010": "SAM1002",  "dsRelation1013": "SAM1003", "dsRelation1014": "SAM1002", "dsRelation1015": "SAM1001", "dsRelation1016": "SAM1003",  "dsRelation1018": "Flipkart", "dsRelation1019": "Sam", "dsRelation1020": "xyz@gmail.com"}|	
	
	Scenario Outline: Fetch record from the data service
	Given Data service "relation"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>"
Examples:
|id|data|
|REL1001|{"_id": "REL1001","dsRelation1001": "SAM1003","dsRelation1002": "5555","dsRelation1003": "Flipkart","dsRelation1004": "SAM1002","dsRelation1005": "SAM1002","dsRelation1007": "SAM1002", "dsRelation1008": "SAM1001", "dsRelation1010": "SAM1001",  "dsRelation1013": "SAM1002", "dsRelation1014": "SAM1003", "dsRelation1015": "SAM1002",  "dsRelation1016": "SAM1001",  "dsRelation1018": "SAM1004", "dsRelation1019": "Flipkart", "dsRelation1020": "ycm@gmail.com"}|
	
	
	Scenario Outline: Update record to data service
	Given Data service "relation"
	Then Update record "<id>" with "<data>" to the data service
Examples:

|id|data|
|REL1001|{"dsRelation1001": "SAM1002","dsRelation1002": "1234","dsRelation1003": "Zerodha","dsRelation1004": "SAM1001","dsRelation1005": "SAM1003","dsRelation1007": "SAM1001", "dsRelation1008": "SAM1002", "dsRelation1010": "SAM1003",  "dsRelation1013": "SAM1001", "dsRelation1014": "SAM1001", "dsRelation1015": "SAM1001",  "dsRelation1016": "SAM1002",  "dsRelation1018": "SAM1002", "dsRelation1019": "Flipkart", "dsRelation1020": "STR1007"}|


Scenario Outline: Fetch record from the data service
	Given Data service "relation"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>"
Examples:
|id|data|
|REL1001|{"dsRelation1001": "SAM1002","dsRelation1002": "1234","dsRelation1003": "Zerodha","dsRelation1004": "SAM1001","dsRelation1005": "SAM1003","dsRelation1007": "SAM1001", "dsRelation1008": "SAM1002", "dsRelation1010": "SAM1003",  "dsRelation1013": "SAM1001", "dsRelation1014": "SAM1001", "dsRelation1015": "SAM1001",  "dsRelation1016": "SAM1002",  "dsRelation1018": "SAM1002", "dsRelation1019": "Flipkart", "dsRelation1020": "STR1007"}|
	
#Scenario Outline: Delete record from the data service
#	Given Data service "relation"
#	Then Delete record "<id>" from the data service
#	And deleting from listing page
#Examples:
#|id|
#|STR1001|
#
#Scenario: Log out of App Center
#	Given User log out from AppCenter
#
