Feature: DS NUMBER CURRENCY

# Scenarios - DS NUMBER CURRENCY 1001 - DS NUMBER CURRENCY 1024

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|test_appadmin@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "number_Currency" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "number_Currency" does not exist
	Then Create new data service "number_Currency"
 #Under testData, picks up strings.json create the JSON

Scenario Outline: Assign to Appcenter Group
 	Then Group "Currency-Group" does not exist
	Then Create new group "Currency-Group" 
	And Assign appcenter permissions for "number_Currency" dataservice to "<user>"
	
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
	Given Data service "number_Currency"
	Then Add data to the data service
	
	
	Scenario Outline: Add record to data service
	Given Data service "number_Currency"
	Then Add record "<data>" to the data service
	And Expect error "DS NUMBER CURRENCY 1002 ERROR" on label "DS NUMBER CURRENCY 1002 LABEL"
	And Save button is disabled
Examples:
|data|
|{"_id": "NUM1001","dsNumberCurrency1001": 11,"dsNumberCurrency1002":"" ,"dsNumberCurrency1003": 13.10,"dsNumberCurrency1004": 14,"dsNumberCurrency1005": 15.5,"dsNumberCurrency1007": 17, "dsNumberCurrency1008": 18, "dsNumberCurrency1010": 18,  "dsNumberCurrency1013": 13.3, "dsNumberCurrency1014":""}|

Scenario Outline: Add record to data service
	Given Data service "number_Currency"
	Then Add record "<data>" to the data service	
	And Expect error "ID NUM1001 already exists" on save
	Examples:
		|data|
    |{"_id": "NUM1001","dsNumberCurrency1001": 1,"dsNumberCurrency1002": 2,"dsNumberCurrency1003": 3,"dsNumberCurrency1004": 4,"dsNumberCurrency1005": 5,"dsNumberCurrency1007": 7, "dsNumberCurrency1008": 8, "dsNumberCurrency1010": 10,  "dsNumberCurrency1013": 13.3, "dsNumberCurrency1014": 19.5, "dsNumberCurrency1015": 15,  "dsNumberCurrency1018": 2.5,  "dsNumberCurrency1020": 20.4, "dsNumberCurrency1021": 12.5 "dsNumberCurrency1022": 17, "dsNumberCurrency1023": 4.00, "dsNumberCurrency1024": 4}|


Scenario Outline: Add record to data service
	Given Data service "number_Currency"
	Then Add record "<data>" to the data service		
	And Expect error "Unique check validation failed for dsNumberCurrency1002" on save
Examples:
|data|
|{"_id": "NUM1002","dsNumberCurrency1001": 10,"dsNumberCurrency1002": 3,"dsNumberCurrency1003": 4,"dsNumberCurrency1004": 4,"dsNumberCurrency1005": 15.5,"dsNumberCurrency1007": 7, "dsNumberCurrency1008": 8, "dsNumberCurrency1010": 10,  "dsNumberCurrency1013": 13, "dsNumberCurrency1014": 14, "dsNumberCurrency1015": 15.00,  "dsNumberCurrency1018": 18,  "dsNumberCurrency1020": 4.00, "dsNumberCurrency1021": 12 "dsNumberCurrency1022": 22, "dsNumberCurrency1023": 0.5, "dsNumberCurrency1024": 5}|

	
	
	Scenario Outline: Fetch record from the data service
	Given Data service "number_Currency"
	Then Fetch record "<id>" from the data service
	And Match this Currency data to "<data>"
Examples:
|id|data|
|NUM1001|{"_id": "NUM1001","dsNumberCurrency1001": 3.00,"dsNumberCurrency1002": 3.00,"dsNumberCurrency1003": 5.00,"dsNumberCurrency1004": 2.00,"dsNumberCurrency1005": 3.00,"dsNumberCurrency1007": 4.50, "dsNumberCurrency1008": 6.22, "dsNumberCurrency1010": 5.55,  "dsNumberCurrency1013": 8.00, "dsNumberCurrency1014": 7.00, "dsNumberCurrency1015": 3.00,  "dsNumberCurrency1018": 2.00,  "dsNumberCurrency1020": 5.00, "dsNumberCurrency1021": 2.00, "dsNumberCurrency1022": 3.00, "dsNumberCurrency1023": 5.00, "dsNumberCurrency1024": 5.00}|
	
	
	Scenario Outline: Update record to data service
	Given Data service "number_Currency"
	Then Update record "<id>" with "<data>" to the data service
Examples:

|id|data|
|NUM1001|{"dsNumberCurrency1001": 1,"dsNumberCurrency1002": 2,"dsNumberCurrency1003": 3,"dsNumberCurrency1004": 3,"dsNumberCurrency1005": 5,"dsNumberCurrency1007": 5, "dsNumberCurrency1008": 4, "dsNumberCurrency1010": 4,  "dsNumberCurrency1013": 3, "dsNumberCurrency1014": 1, "dsNumberCurrency1015": 5,  "dsNumberCurrency1016": 3,  "dsNumberCurrency1020": 4, "dsNumberCurrency1021": 1, "dsNumberCurrency1022": 5, "dsNumberCurrency1023": 4, "dsNumberCurrency1024": 4}|

Scenario Outline: Fetch record from the data service
	Given Data service "number_Currency"
	Then Fetch record "<id>" from the data service
	And Match this Currency data to "<data>"
Examples:
|id|data|
|NUM1001|{"_id": "NUM1001","dsNumberCurrency1001": 1.00,"dsNumberCurrency1002": 3.00,"dsNumberCurrency1003": 3.00,"dsNumberCurrency1004": 2.00,"dsNumberCurrency1005": 5.00,"dsNumberCurrency1007": 4.50, "dsNumberCurrency1008": 4.00, "dsNumberCurrency1010": 5.55,  "dsNumberCurrency1013": 3.00, "dsNumberCurrency1014": 1.00, "dsNumberCurrency1015": 5.00,  "dsNumberCurrency1018": 2.00,  "dsNumberCurrency1020": 4.00, "dsNumberCurrency1021": 1.00, "dsNumberCurrency1022": 5.00, "dsNumberCurrency1023": 4.00, "dsNumberCurrency1024": 4.00}|
	
Scenario Outline: Delete record from the data service
	Given Data service "number_Currency"
	Then Delete record "<id>" from the data service
	And Deleting from listing page
Examples:
|id|
|NUM1001|


Scenario: Log out of App Center
	Given User log out from AppCenter


