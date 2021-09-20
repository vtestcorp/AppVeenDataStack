Feature: DS LOCATION

# Scenarios - DS-LOCATION-1001 - DS-LOCATION-1009

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|test_appadmin@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "location" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "location" does not exist
	Then Create new data service "location"
 #Under testData, picks up strings.json create the JSON


Scenario Outline: Assign to Appcenter Group
 	Then Group "String-Group" does not exist
	Then Create new group "String-Group" 
	And Assign appcenter permissions for "location" dataservice to "<user>"
	
	Examples:
	|user|
	|test_ac_ds_manage@appveen.com|

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
|test_ac_ds_manage@appveen.com|123123123|


Scenario: Add data to data service
	Given Data service "location"
	Then Add data to the data service for Location
	
	
#	Scenario Outline: Add record to data service
#	Given Data service "location"
#	Then Add record "<data>" to the Location data service
#	And Expect error "DS-LOCATION 1002 Error" on label "DS-LOCATION 1002 Label"
#	And Save button is disabled
#Examples:
#|data|
#|{"_id":"DS1002", "dsLocation1002": "", "dsLocation1007": "", "dsLocation1008": ""}|

Scenario Outline: Add record to data service
	Given Data service "location"
	Then Add record "<data>" to the Location data service	
	And Expect error "ID DS1001 already exists." on save
	Examples:
		|data|
	  |{ "_id": "DS1001", "dsLocation1001": "Satara, Maharashtra, India", "dsLocation1002": "Mumbai, Maharashtra, India", "dsLocation1003": "Kolhapur, Maharashtra, India", "dsLocation1006": "Sangli, Maharashtra, India", "dsLocation1007": "Nanded, Maharashtra, India", "dsLocation1008": "Dhule, Maharashtra, India", "dsLocation1009": "Ratnagiri, Maharashtra, India" }|
	
	Scenario Outline: Fetch record from the data service
	Given Data service "location"
	Then Fetch record "<id>" from the data service
	And  Match this Location data to "<data>" 

Examples:
|id|data|
|DS1001|{"_id": "DS1001","dsLocation1001": "Pune, Maharashtra, India","dsLocation1002": "Pimpri-Chinchwad, Maharashtra, India","dsLocation1003": "Bangalore, Karnataka, India","dsLocation1006": "Chennai, Tamil Nadu, India","dsLocation1007": "Gujrat, Pakistan", "dsLocation1008": "Satara, Maharashtra, India","dsLocation1009":"Mumbai, Maharashtra, India"}|


	Scenario Outline: Update record to data service
	Given Data service "location"
	Then Update record "<id>" with "<data>" to the Location 
Examples:
|id|data|
|DS1001|{"dsLocation1001": "Aundh, Pune, Maharashtra, India","dsLocation1002": "Tamil Nadu, India","dsLocation1003": "Jammu and Kashmir","dsLocation1006": "Bihar, India","dsLocation1007": "Patna, Bihar, India","dsLocation1008": "Chandigarh, India" ,"dsLocation1009": "Himachal Pradesh, India"}|

Scenario Outline: Fetch record from the data service
	Given Data service "location"
	Then Fetch record "<id>" from the data service
	And Match this Location data to "<data>" 
Examples:
|id|data|
|DS1001|{"_id": "DS1001","dsLocation1001": "Aundh, Pune, Maharashtra, India","dsLocation1002": "Tamil Nadu, India","dsLocation1003": "Jammu and Kashmir","dsLocation1006": "Bihar, India","dsLocation1007": "Patna, Bihar, India", "dsLocation1008": "Chandigarh, India", "dsLocation1009" : "Himachal Pradesh, India"}|

#Scenario Outline: Delete record from the data service
#	Given Data service "location"
#	Then Delete record "<id>" from the data service
#	And Deleting from listing page
#Examples:
#|id|
#|DS1001|
#
#Scenario: Log out of App Center
#	Given User log out from AppCenter
