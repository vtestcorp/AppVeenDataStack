Feature: This is the Feature file of DS-Collection

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|deepak@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "collection" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "collection" does not exist
	Then Create new data service "collection"
 #Under testData, picks up strings.json create the JSON


Scenario Outline: Assign to Appcenter Group
	Then Create new group "Collection-Group" 
	And Assign appcenter permissions for "collection" dataservice to "<user>"
	Examples:
	|user|
	|maker@appveen.com|

Scenario: Log out of Author
	Given User logged into Author
	Then User logs out of Author
	
#
#@AppCenter
Scenario Outline: Log into AppCenter
	Given User navigate to AppCenter login page
	And User enters "<username>" and "<password>" in AppCenter login page
	And Verify User has Logged in Successfully 
Examples:
|username|password|
|maker@appveen.com|123123123|


# INSERT/UPDATE
Scenario: Add data to data service
	Given Data service "collection"
	Then Add data to the data service for Collection
	
	
#	Scenario Outline: Add record to data service
#	Given Data service "number_Number"
#	Then Add record "<data>" to the data service
#	And Expect error "DS-NUMBER-NUMBER error" on label "DS-NUMBER-NUMBER label"
#	And Save button is disabled
#Examples:
#|data|
#|{"_id":"DS1002", "dsNumberNumber1002": "", "dsNumberNumber1014": "","dsNumberNumber1015": "","dsNumberNumber1018": "" }|
#
#Scenario Outline: Add record to data service
#	Given Data service "number_Number"
#	Then Add record "<data>" to the data service	
#	And Expect error "ID DS1001 already exists" on save
#	Examples:
#		|data|
#	  |{ "_id" : "DS1001","dsNumberNumber1001" : 11,"dsNumberNumber1002" : 	12,"dsNumberNumber1003" : 13,"dsNumberNumber1007" : 14,"dsNumberNumber1008" : 18,"dsNumberNumber1014" : 17,"dsNumberNumber1015" : 15}|
#
#Scenario Outline: Add record to data service
#	Given Data service "number_Number"
#	Then Add record "<data>" to the data service		
#	And Expect error "Unique check validation failed for dsNumberNumber1002" on save
#Examples:
#|data|
#|{ "_id" : "DS1003","dsNumberNumber1001" : 1001,"dsNumberNumber1002" : 10,"dsNumberNumber1003" : 3,"dsNumberNumber1007" : 7,"dsNumberNumber1008" : 8,"dsNumberNumber1014" : 4,"dsNumberNumber1015" : 15}|
#
#
#	
#	Scenario Outline: Fetch record from the data service
#	Given Data service "number_Number"
#	Then Fetch record "<id>" from the data service
#	And Match it to "<data>"
#
#Examples:
#|id|data|
#|DS1001|{"_id": "DS1001","dsNumberNumber1001": 1001,"dsNumberNumber1002": 10,"dsNumberNumber1003": 1003,"dsNumberNumber1004": 1004,"dsNumberNumber1005": 1005,"dsNumberNumber1007": 0 ,"dsNumberNumber1008": 1, "dsNumberNumber1010": 4, "dsNumberNumber1013": -5,"dsNumberNumber1014": 6,"dsNumberNumber1015": -3, "dsNumberNumber1018": 3,"dsNumberNumber1020": -4,"dsNumberNumber1021": 3, "dsNumberNumber1022": 1, "dsNumberNumber1023": -1,"dsNumberNumber1024": 2}|
#
#	
#
#	Scenario Outline: Update record to data service
#	Given Data service "number_Number"
#	Then Update record "<id>" with "<data>" to the data service
#Examples:
#
#|id|data|
#|DS1001|{"dsNumberNumber1001": 1002,"dsNumberNumber1002": 7,"dsNumberNumber1003": 4,"dsNumberNumber1004": 1,"dsNumberNumber1005": 13,"dsNumberNumber1007": 3 ,"dsNumberNumber1008": 6, "dsNumberNumber1010": 7, "dsNumberNumber1013": 9,"dsNumberNumber1014": 1,"dsNumberNumber1015": -1, "dsNumberNumber1018": 3,"dsNumberNumber1020": -3,"dsNumberNumber1021": 2, "dsNumberNumber1022": 3, "dsNumberNumber1023": 2,"dsNumberNumber1024": 4}|
#
#Scenario Outline: Fetch record from the data service
#	Given Data service "number_Number"
#	Then Fetch record "<id>" from the data service
#	And Match it to "<data>"
#Examples:
#|id|data|
#|DS1001|{"_id": "DS1001","dsNumberNumber1001": 1001,"dsNumberNumber1002": 10,"dsNumberNumber1003": 1003, "dsNumberNumber1004": 1004,"dsNumberNumber1005": 1005,"dsNumberNumber1007": 0, "dsNumberNumber1008": 1, "dsNumberNumber1010":4,  "dsNumberNumber1013": -5, "dsNumberNumber1014": 6,"dsNumberNumber1015": -3, "dsNumberNumber1018": 3,"dsNumberNumber1020": -4,"dsNumberNumber1021": 3, "dsNumberNumber1022": 1, "dsNumberNumber1023": -1,"dsNumberNumber1024": 2}|
#
#
#Scenario Outline: Delete record from the data service
#	Given Data service "number_Number"
#	Then Delete record "<id>" from the data service
#	And Deleting from listing page
#Examples:
#|id|
#|DS1001|
