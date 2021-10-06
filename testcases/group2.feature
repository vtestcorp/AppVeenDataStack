Feature: DS GROUP 

# Scenarios - DS GROUP STRING TEXT - DS GROUP COLLECTION

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|test_appadmin@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "group2" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "group2" does not exist
	Then Create new data service "group2"

Scenario Outline: Assign to Appcenter Group
 	Then Group "DS Group" does not exist
	Then Create new group "DS Group" 
	And Assign appcenter permissions for "group2" dataservice to "<user>"
	
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
	Given Data service "group2"
	Then Add data to the data service for Group

Scenario Outline: Add record to data service
	Given Data service "group2"
	Then Add record "<data>" to the group	
	And Expect error "ID DS1001 already exists." on save
	Examples:
		|data|
	  |{ "dsGroupLibrary" : { "library" : { "line1" : "Library Line 12 ", "line2" : "Library Line 22" } }, "_id" : "DS1001", "dsGroupStringText" : { "stringText" : "Group Text 1" }, "dsGroupStringEmail" : { "email" : "pqr@gmail.com" }, "dsGroupStringListOfValues" : { "listOfValues" : "XYZ" }, "dsGroupNumberNumber" : { "number" : 888 }, "dsGroupNumberCurrency" : { "currency" : 8 }, "dsGroupNumberListOfValues" : { "listOfValues" : 999 }, "dsGroupBoolean" : { "boolean" : true }, "dsGroupDate" : { "date" : { "rawData" : "2040-12-12T00:00:00Z" } }, "dsGroupDateTime" : { "dateTime" : { "rawData" : "2050-10-20T15:15:15Z" } }, "dsGroupLocation" : { "location" : { "userInput" : "Kolhapur, Maharashtra, India" } }, "dsGroupFile" : { "file" : { "metadata" : { "filename" : "Untitled.png" } } }, "dsGroupUser" : { "user" : { "_id" : "deepak@appveen.com" } } }|

	Scenario Outline: Fetch record from the data service
	Given Data service "group2"
	Then Fetch record "<id>" from the data service
	And Match this GROUP data to "<data>"
Examples:
|id|data|
|DS1001|{"_id":"DS1001", "dsGroupStringText.stringText":"Group Text", "dsGroupStringEmail.email":"abc@gmail.com", "dsGroupStringListOfValues.listOfValues":"ABC", "dsGroupNumberNumber.number":"100", "dsGroupNumberCurrency.currency":"5.00", "dsGroupNumberListOfValues.listOfValues":"123", "dsGroupBoolean.boolean":"Yes", "dsGroupDate.date":"Friday, May 22, 2015 (Etc/Zulu)", "dsGroupDateTime.dateTime":"Thursday, December 12, 2030, 10:10:10 AM (Zulu)", "dsGroupLocation.location":"Pune, Maharashtra, India", "dsGroupFile.file":"Date & Time.png", "dsGroupLibrary.library.line1":"Library Line 1", "dsGroupLibrary.library.line2":"Library Line 2", "dsGroupUser.user":"maker@appveen.com"}|
	

	Scenario Outline: Update record to data service
	Given Data service "group2"
	Then Update record "<id>" with "<data>" to the data service for group
Examples:

|id|data|
|DS1001|{ "dsGroupLibrary" : { "library" : { "line1" : "Update Library Line 1", "line2" : "Update Library Line 2" } }, "dsGroupStringText" : { "stringText" : "Update Group Text" }, "dsGroupStringEmail" : { "email" : "xyz@gmail.com" }, "dsGroupStringListOfValues" : { "listOfValues" : "XYZ" }, "dsGroupNumberNumber" : { "number" : 999 }, "dsGroupNumberCurrency" : { "currency" : 11 }, "dsGroupNumberListOfValues" : { "listOfValues" : 999 }, "dsGroupBoolean" : { "boolean" : true }, "dsGroupDate" : { "date" : { "rawData" : "2025-11-27T00:00:00Z" } }, "dsGroupDateTime" : { "dateTime" : { "rawData" : "2030-06-11T08:20:30Z" } }, "dsGroupLocation" : { "location" : { "userInput" : "Solapur, Maharashtra, India" } }, "dsGroupFile" : { "file" : { "metadata" : { "filename" : "Untitled.png" } } }, "dsGroupUser" : { "user" : { "_id" : "deepak@appveen.com" } } }|

Scenario Outline: Fetch record from the data service
	Given Data service "group2"
	Then Fetch record "<id>" from the data service
	And Match this GROUP data to "<data>"
Examples:
|id|data|
|DS1001|{"_id":"DS1001", "dsGroupStringText.stringText":"Update Group Text", "dsGroupStringEmail.email":"xyz@gmail.com", "dsGroupStringListOfValues.listOfValues":"XYZ", "dsGroupNumberNumber.number":"999", "dsGroupNumberCurrency.currency":"11.00", "dsGroupNumberListOfValues.listOfValues":"999", "dsGroupBoolean.boolean":"No", "dsGroupDate.date":"Thursday, November 27, 2025 (Etc/Zulu)", "dsGroupDateTime.dateTime":"Tuesday, June 11, 2030, 8:20:30 AM (Zulu)", "dsGroupLocation.location":"Solapur, Maharashtra, India", "dsGroupFile.file":"Untitled.png", "dsGroupLibrary.library.line1":"Update Library Line 1", "dsGroupLibrary.library.line2":"Update Library Line 2", "dsGroupUser.user":"deepak@appveen.com"}|
	
Scenario Outline: Delete record from the data service
	Given Data service "group2"
	Then Delete record "<id>" from the data service
	And Deleting from listing page
Examples:
|id|
|DS1001|

Scenario: Log out of App Center
	Given User log out from AppCenter