Feature: DS RELATION

# Scenarios - DS RELATION 1001-DS RELATION 1020

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|test_ui_appadmin@appveen.com	|Veen@99%win|

Scenario: Delete Relation Data Service
	Given Delete "relation" data service
	
	 Scenario: Delete data service
	Given Data service "sample" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "sample" does not exist
	Then Create new data service "sample"

Scenario: Delete data service
	Given Data service "relation" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "relation" does not exist
	Then Create new data service "relation"

Scenario Outline: Assign to Appcenter Group
 	Then Group "Relation-Group" does not exist
	Then Create new group "Relation-Group" 
	And Assign appcenter permissions for "relation" dataservice to "<user>"
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
|test_ui_ac_ds_manage@appveen.com |Veen@99%win|

Scenario Outline: Add record to data service
	Given Data service "sample"
	Then Add record "<data>" to the data service
Examples:
|data|
|{"_id": "SAM1001","string1": "Facebbok","number1": 5555,"email": "pqr@gmail.com","string2": "Zomato"}|
|{"_id": "SAM1002","string1": "Swiggy","number1": 3333,"email": "xyz@gmail.com","string2": "Samco"}|
|{"_id": "SAM1003","string1": "Zerodha","number1": 4444,"email": "pqr@gmail.com","string2": "Zomato"}|
|{"_id": "SAM1004","string1": "Swiggy","number1": 5555,"email": "pqr@gmail.com","string2": "Zomato"}|
|{"_id": "SAM1005","string1": "Myntra","number1": 1234,"email": "ycm@gmail.com","string2": "Upstox"}|
	
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
|{"_id": "REL1002","dsRelation1001": "SAM1003","dsRelation1002": "","dsRelation1003": "SAM1002","dsRelation1004": "SAM1003","dsRelation1005": "SAM1002","dsRelation1007": "SAM1001", "dsRelation1008": "SAM1003", "dsRelation1010": "SAM1002",  "dsRelation1013": "SAM1003", "dsRelation1014": "", "dsRelation1015": "", "dsRelation1016": "SAM1003",  "dsRelation1018": "SAM1001", "dsRelation1019": "SAM1002", "dsRelation1020": "SAM1001"}|

Scenario Outline: Add record to data service
	Given Data service "relation"
	Then Add record "<data>" to the data service	
	And Expect error "ID REL1001 already exists." on save
Examples:
|data|
|{"_id": "REL1001","dsRelation1001": "SAM1003","dsRelation1002": "SAM1002","dsRelation1003": "SAM1001","dsRelation1004": "SAM1003","dsRelation1005": "SAM1002","dsRelation1007": "SAM1001", "dsRelation1008": "SAM1003", "dsRelation1010": "SAM1002",  "dsRelation1013": "SAM1003", "dsRelation1014": "SAM1002", "dsRelation1015": "SAM1001", "dsRelation1016": "SAM1003",  "dsRelation1018": "SAM1002", "dsRelation1019": "SAM1004", "dsRelation1020": "SAM1005"}|	

Scenario Outline: Add record to data service
	Given Data service "relation"
	Then Add record "<data>" to the data service		
	And Expect error "Unique check validation failed for dsRelation1002" on save
Examples:
|data|
|{"_id": "REL1003","dsRelation1001": "SAM1006","dsRelation1002": "SAM1003","dsRelation1003": "SAM1002","dsRelation1004": "SAM1003","dsRelation1005": "SAM1002","dsRelation1007": "SAM1001", "dsRelation1008": "SAM1003", "dsRelation1010": "SAM1002",  "dsRelation1013": "SAM1003", "dsRelation1014": "SAM1002", "dsRelation1015": "SAM1001", "dsRelation1016": "SAM1003",  "dsRelation1018": "SAM1006", "dsRelation1019": "SAM1005", "dsRelation1020": "SAM1003"}|	
	
Scenario Outline: Fetch record from the data service
	Given Data service "relation"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>"
Examples:
|id|data|
|REL1001|{"_id": "REL1001","dsRelation1001": "SAM1003","dsRelation1002": "SAM1003","dsRelation1003": "SAM1004","dsRelation1004": "SAM1002","dsRelation1005": "SAM1002","dsRelation1007": "SAM1002", "dsRelation1008": "SAM1001", "dsRelation1010": "SAM1001",  "dsRelation1013": "SAM1002", "dsRelation1014": "SAM1003", "dsRelation1015": "SAM1002",  "dsRelation1016": "SAM1001",  "dsRelation1018": "SAM1004", "dsRelation1019": "SAM1001"}|
	
Scenario Outline: Update record to data service
	Given Data service "relation"
	Then Update record "<id>" with "<data>" to the data service
Examples:
|id|data|
|REL1001|{"dsRelation1001": "SAM1002","dsRelation1002": "SAM1002","dsRelation1003": "SAM1005","dsRelation1004": "SAM1001","dsRelation1005": "SAM1003","dsRelation1007": "SAM1001", "dsRelation1008": "SAM1002", "dsRelation1010": "SAM1003",  "dsRelation1013": "SAM1001", "dsRelation1014": "SAM1001", "dsRelation1015": "SAM1001",  "dsRelation1016": "SAM1002",  "dsRelation1018": "SAM1002", "dsRelation1019": "SAM1001", "dsRelation1020": "STR1007"}|

Scenario Outline: Fetch record from the data service
	Given Data service "relation"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>"
Examples:
|id|data|
|REL1001|{"_id": "REL1001","dsRelation1001": "SAM1002","dsRelation1002": "SAM1003","dsRelation1003": "SAM1005","dsRelation1004": "SAM1002","dsRelation1005": "SAM1003","dsRelation1007": "SAM1002", "dsRelation1008": "SAM1002", "dsRelation1010": "SAM1001",  "dsRelation1013": "SAM1001", "dsRelation1014": "SAM1001", "dsRelation1015": "SAM1001",  "dsRelation1016": "SAM1002",  "dsRelation1018": "SAM1002", "dsRelation1019": "SAM1001"}|
	
Scenario Outline: Delete record from the data service
	Given Data service "relation"
	Then Delete record "<id>" from the data service
	And Deleting from listing page
Examples:
|id|
|REL1001|

Scenario: Log out of App Center
	Given User log out from AppCenter
