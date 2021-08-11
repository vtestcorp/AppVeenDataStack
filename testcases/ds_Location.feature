Feature: This is the Feature file of DS-Number


@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|deepak@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "ds_Location" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "ds_Location" does not exist
	Then Create new data service "ds_Location"
 #Under testData, picks up strings.json create the JSON


Scenario Outline: Assign to Appcenter Group
 	Then Group "String-Group" does not exist
	Then Create new group "String-Group" 
	And Assign appcenter permissions for "ds_Location" dataservice to "<user>"
	
	Examples:
	|user|
	|maker@appveen.com|

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
|maker@appveen.com|123123123|


 #INSERT/UPDATE
Scenario: Add data to data service
	Given Data service "ds_Location"
	Then Add data to the data service for Location
	
	
#	Scenario Outline: Add record to data service
#	Given Data service "ds_Location"
#	Then Add record "<data>" to the Location data service
#	And Expect error "DS-LOCATION Error" on label "DS-LOCATION Label"
#	And Save button is disabled
#Examples:
#|data|
#|{"_id":"DS1002", "dsLocation1002": "", "dsLocation1007": "", "dsLocation1008": ""}|
#
#Scenario Outline: Add record to data service
#	Given Data service "ds_Location"
#	Then Add record "<data>" to the Location data service	
#	And Expect error "ID DS1001 already exists" on save
#	Examples:
#		|data|
#	  |{ "_id": "DS1001", "dsLocation1001": "Satara, Maharashtra, India", "dsLocation1002": "Mumbai, Maharashtra, India", "dsLocation1003": "Kolhapur, Maharashtra, India", "dsLocation1006": "Sangli, Maharashtra, India", "dsLocation1007": "Nanded, Maharashtra, India", "dsLocation1008": "Dhule, Maharashtra, India", "dsLocation1009": "Ratnagiri, Maharashtra, India" }|
#	
#	Scenario Outline: Fetch record from the data service
#	Given Data service "ds_Location"
#	Then Fetch record "<id>" from the data service
#	And Match it to "<data>"
#
#Examples:
#|id|data|
#|DS1001|{"_id": "DS1001","dsLocation1001": "Tamil Nadu, India","dsLocation1002": "Gujarat, India","dsLocation1003": "Bihar, India","dsLocation1006": "Kolkata, West Bengal, India","dsLocation1007": "Jammu and Kashmir", "dsLocation1008": "Himachal Pradesh, India" ,"dsLocation1009": "Bihar, India"}|

#	Scenario Outline: Update record to data service
#	Given Data service "ds_Location"
#	Then Update record "<id>" with "<data>" to the Location 
#Examples:
#|id|data|
#|DS1001|{"dsLocation1001": "Aundh, Pune, Maharashtra, India","dsLocation1002": "Tamil Nadu, India","dsLocation1003": "Jammu and Kashmir","dsLocation1006": "Bihar, India","dsLocation1007": "Patna, Bihar, India","dsLocation1008": "Chandigarh, India" ,"dsLocation1009": "Himachal Pradesh, India"}|

Scenario Outline: Fetch record from the data service
	Given Data service "ds_Location"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>"
Examples:
|id|data|
|DS1001|{"_id": "DS1001","dsLocation1001": 1002,"dsLocation1002": 10,"dsLocation1003": 1004, "dsLocation1004": 1004,"dsLocation1005": 1005,"dsLocation1007": 1007, "dsLocation1008": 1, "dsLocation1010":4,  "dsLocation1013": -5, "dsLocation1014": 6,"dsLocation1015": -3, "dsLocation1018": 3,"dsLocation1020": -4,"dsLocation1021": 3, "dsLocation1022": 1, "dsLocation1023": -1,"dsLocation1024": 2}|

#
#Scenario Outline: Delete record from the data service
#	Given Data service "ds_Location"
#	Then Delete record "<id>" from the data service
#	And deleting from listing page
#Examples:
#|id|
#|DS1001|
