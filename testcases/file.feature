Feature: DS FILE

# Scenarios - DS FILE 1001 - DS FILE 1013

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|test_appadmin@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "file" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "file" does not exist
	Then Create new data service "file"

Scenario Outline: Assign to Appcenter Group
 	Then Group "File-Group" does not exist
	Then Create new group "File-Group" 
	And Assign appcenter permissions for "file" dataservice to "<user>"
	
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


Scenario: Add data to data service
	Given Data service "file"
	Then Add data to the data service for File Type
	
	
	Scenario Outline: Add record to data service
	Given Data service "file"
	Then Add record "<data>" to the data service for File Type
	And Expect error "DS-FILE-1002 Error" on label "DS-FILE-1002 Label"
	And Save button is disabled
Examples:
|data|
|{"_id" : "DS1004","dsFile1001" : "Date & Time.png","dsFile1002" : "File.txt","dsFile1003" : "Date & Time.png","dsFile1004" : "Date & Time.png","dsFile1006" : "Date & Time.png","dsFile1010" : "Date & Time.png","dsFile1011" : "Date & Time.png","dsFile1012" : "Date & Time.png","dsFile1013" : "Date & Time.png"}|




Scenario Outline: Add record to data service
	Given Data service "file"
	Then Add record "<data>" to the data service for File Type
	And Expect error "ID DS1001 already exists." on save
	Examples:
		|data|
		|{"_id" : "DS1001","dsFile1001" : "Date & Time.png","dsFile1002" : "Date & Time.png","dsFile1003" : "Date & Time.png","dsFile1004" : "Date & Time.png","dsFile1006" : "Date & Time.png","dsFile1010" : "Date & Time.png","dsFile1011" : "Date & Time.png","dsFile1012" : "Date & Time.png","dsFile1013" : "Date & Time.png"}|


	
	
	Scenario Outline: Fetch record from the data service
	Given Data service "file"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>" for File type
Examples:
|id|data|
|DS1001|{ "_id": "DS1001", "dsFile1001": "Date & Time.png", "dsFile1002": "Untitled.png", "dsFile1003": "Date & Time.png", "dsFile1004": "Date & Time.png", "dsFile1006": "Date & Time.png", "dsFile1010": "Date & Time.png", "dsFile1011": "Date & Time.png", "dsFile1012": "Date & Time.png", "dsFile1013": "Date & Time.png" }|
	
	
	Scenario Outline: Update record to data service
	Given Data service "file"
	Then Update record "<id>" with "<data>" to the data service for File Type
Examples:

|id|data|
|DS1001|{"dsFile1001" : "Untitled.png","dsFile1002" : "Untitled.png","dsFile1003" : "Untitled.png","dsFile1004" : "Untitled.png","dsFile1006" : "Untitled.png","dsFile1010" : "Untitled.png","dsFile1011" : "Untitled.png","dsFile1012" : "Untitled.png","dsFile1013" : "Untitled.png"}|

Scenario Outline: Fetch record from the data service
	Given Data service "file"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>" for File type
Examples:
|id|data|
|DS1001|{ "_id": "DS1001", "dsFile1001": "Untitled.png", "dsFile1002": "Untitled.png", "dsFile1003": "Untitled.png", "dsFile1004": "Date & Time.png", "dsFile1006": "Date & Time.png", "dsFile1010": "Untitled.png", "dsFile1011": "Untitled.png", "dsFile1012": "Untitled.png", "dsFile1013": "Untitled.png" }|


Scenario Outline: Delete record from the data service
	Given Data service "file"
	Then Delete record "<id>" from the data service
	And Deleting from listing page
Examples:
|id|
|DS1001|


Scenario: Log out of App Center
	Given User log out from AppCenter


