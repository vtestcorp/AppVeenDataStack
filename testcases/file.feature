Feature: DS FILE

# Scenarios - DS FILE 1001 - DS FILE 1013

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|deepak@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "file" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "file" does not exist
	Then Create new data service "file"
 #Under testData, picks up strings.json create the JSON

Scenario Outline: Assign to Appcenter Group
 	Then Group "File-Group" does not exist
	Then Create new group "File-Group" 
	And Assign appcenter permissions for "file" dataservice to "<user>"
	
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


Scenario: Add data to data service
	Given Data service "file"
	Then Add data to the data service for File Type
	
	
	Scenario Outline: Add record to data service
	Given Data service "file"
	Then Add record "<data>" to the data service for File Type
	And Expect error "" on label ""
	And Save button is disabled
Examples:
|data|

|{"_id" : "DS1004","dsFile1001" : "Date & Time.png","dsFile1002" : "File.txt","dsFile1003" : "Date & Time.png","dsFile1004" : "Date & Time.png","dsFile1006" : "Date & Time.png","dsFile1010" : "Date & Time.png","dsFile1011" : "Date & Time.png","dsFile1012" : "Date & Time.png","dsFile1013" : "Date & Time.png"}|




Scenario Outline: Add record to data service
	Given Data service "file"
	Then Add record "<data>" to the data service for File Type
	And Expect error "ID NUM1001 already exists" on save
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
	And deleting from listing page
Examples:
|id|
|DS1001|


Scenario: Log out of App Center
	Given User log out from AppCenter


