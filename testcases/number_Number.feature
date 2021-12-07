Feature: DS NUMBER-NUMBER

# Scenarios - DS-NUMBER-NUMBER-1001 - DS-NUMBER-NUMBER-1024

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|test_ui_appadmin@appveen.com	|Veen@99%win|

Scenario: Delete data service
	Given Data service "number_Number" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "number_Number" does not exist
	Then Create new data service "number_Number"

Scenario Outline: Assign to Appcenter Group
 	Then Group "Number-Group" does not exist
	Then Create new group "Number-Group" 
	And Assign appcenter permissions for "number_Number" dataservice to "<user>"
Examples:
|user|
|test_ui_ac_ds_manage@appveen.com|

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
|test_ui_ac_ds_manage@appveen.com |Veen@99%win|

 #INSERT/UPDATE
Scenario: Add data to data service
	Given Data service "number_Number"
	Then Add data to the data service
	
Scenario Outline: Add record to data service
	Given Data service "number_Number"
	Then Add record "<data>" to the data service
	And Expect error "DS-NUMBER-NUMBER Error" on label "DS-NUMBER-NUMBER 1002 label"
	And Save button is disabled
Examples:
|data|
|{"_id": "DS1001","dsNumberNumber1001": 10,"dsNumberNumber1002": "","dsNumberNumber1003": 30, "dsNumberNumber1004": 40,"dsNumberNumber1005": 50,"dsNumberNumber1007": 60, "dsNumberNumber1008": 1, "dsNumberNumber1010":4,  "dsNumberNumber1013": -5, "dsNumberNumber1014": "","dsNumberNumber1015": "", "dsNumberNumber1018": "","dsNumberNumber1020": -4,"dsNumberNumber1021": 3, "dsNumberNumber1022": 1, "dsNumberNumber1023": 23,"dsNumberNumber1024": 24}|

Scenario Outline: Add record to data service
	Given Data service "number_Number"
	Then Add record "<data>" to the data service	
	And Expect error "ID DS1001 already exists." on save
Examples:
|data|
|{"_id": "DS1001","dsNumberNumber1001": 11,"dsNumberNumber1002": 12,"dsNumberNumber1003": 13, "dsNumberNumber1004": 14,"dsNumberNumber1005": 15,"dsNumberNumber1007": 16, "dsNumberNumber1008": 17, "dsNumberNumber1010":18,  "dsNumberNumber1013": 19, "dsNumberNumber1014": 20,"dsNumberNumber1015": 21, "dsNumberNumber1018": 22,"dsNumberNumber1020": 23,"dsNumberNumber1021": 24, "dsNumberNumber1022": 1, "dsNumberNumber1023": 12,"dsNumberNumber1024": 24}|
	  
Scenario Outline: Add record to data service
	Given Data service "number_Number"
	Then Add record "<data>" to the data service		
	And Expect error "Unique check validation failed for dsNumberNumber1002" on save
Examples:
|data|
|{"_id": "DS1002","dsNumberNumber1001": 1,"dsNumberNumber1002": 10,"dsNumberNumber1003": 3, "dsNumberNumber1004": 4,"dsNumberNumber1005": 5,"dsNumberNumber1007": 7, "dsNumberNumber1008": 8, "dsNumberNumber1010":9,  "dsNumberNumber1013": 13, "dsNumberNumber1014": 14,"dsNumberNumber1015": 15, "dsNumberNumber1018": 18,"dsNumberNumber1020": 20,"dsNumberNumber1021": 21, "dsNumberNumber1022": 1, "dsNumberNumber1023": 23,"dsNumberNumber1024": 24}|

Scenario Outline: Fetch record from the data service
	Given Data service "number_Number"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>"
Examples:
|id|data|
|DS1001|{"_id": "DS1001","dsNumberNumber1001": 1001,"dsNumberNumber1002": 10,"dsNumberNumber1003": 1003,"dsNumberNumber1004": 1004,"dsNumberNumber1005": 1005,"dsNumberNumber1007": 0 ,"dsNumberNumber1008": 1, "dsNumberNumber1010": 4, "dsNumberNumber1013": -5,"dsNumberNumber1014": 6,"dsNumberNumber1015": -3, "dsNumberNumber1018": 3,"dsNumberNumber1020": -4,"dsNumberNumber1021": 3, "dsNumberNumber1022": 1, "dsNumberNumber1023": -1,"dsNumberNumber1024": 2}|

Scenario Outline: Update record to data service
	Given Data service "number_Number"
	Then Update record "<id>" with "<data>" to the data service
Examples:
|id|data|
|DS1001|{"dsNumberNumber1001": 1002,"dsNumberNumber1002": 7,"dsNumberNumber1003": 1004,"dsNumberNumber1004": 1005,"dsNumberNumber1005": 1006,"dsNumberNumber1007": 1008 ,"dsNumberNumber1008": 6, "dsNumberNumber1010": 7, "dsNumberNumber1013": 9,"dsNumberNumber1014": 1,"dsNumberNumber1015": 5, "dsNumberNumber1018": 30,"dsNumberNumber1020": 3,"dsNumberNumber1021": 2, "dsNumberNumber1022": 3, "dsNumberNumber1023": 2,"dsNumberNumber1024": 4}|

Scenario Outline: Fetch record from the data service
	Given Data service "number_Number"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>"
Examples:
|id|data|
|DS1001|{"_id": "DS1001","dsNumberNumber1001": 1002,"dsNumberNumber1002": 10,"dsNumberNumber1003": 1004, "dsNumberNumber1004": 1004,"dsNumberNumber1005": 1006,"dsNumberNumber1007": 0, "dsNumberNumber1008": 6, "dsNumberNumber1010":4,  "dsNumberNumber1013": 9, "dsNumberNumber1014": 1,"dsNumberNumber1015": 5, "dsNumberNumber1018": 30,"dsNumberNumber1020": 3,"dsNumberNumber1021": 2, "dsNumberNumber1022": 3, "dsNumberNumber1023": 2,"dsNumberNumber1024": 4}|

Scenario Outline: Delete record from the data service
	Given Data service "number_Number"
	Then Delete record "<id>" from the data service
	And Deleting from listing page
Examples:
|id|
|DS1001|

Scenario: Log out of App Center
	Given User log out from AppCenter
