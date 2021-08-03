Feature: This is the string_Email feature file

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|deepak@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "string_Email" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "string_Email" does not exist
	Then Create new data service "string_Email"
 #Under testData, picks up strings.json create the JSON


Scenario Outline: Assign to Appcenter Group
 	Then Group "String-Group" does not exist
	Then Create new group "String-Group" 
	And Assign appcenter permissions for "string_Email" dataservice to "<user>"
	
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
	Given Data service "string_Email"
	Then Add data to the data service

#Scenario Outline: Add record to data service
#	Given Data service "string_Email"
#	Then Add record "<data>" to the data service
#	And Expect error "DS-string-Email error" on label "DS-string-Email-label"
#	And Save button is disabled
#Examples:
#|data|
#|{"_id":"RC102", "dsStringEmail1002":"", "dsStringEmail1014":"","dsStringEmail1015":"","dsStringEmail1017":"","dsStringEmail1018":""}|

#Scenario Outline: Add record to data service
#	Given Data service "string_Email"
#	Then Add record "<data>" to the data service	
#	And Expect error "ID RC101 already exists" on save
#	Examples:
#		|data|
#	  |{ "_id" : "RC101","dsStringEmail1001" : "abc@gmail.com","dsStringEmail1002" : "xyz@gmail.com","dsStringEmail1003" : "pqr@gamil.com","dsStringEmail1007" : "uvw@gamil.com","dsStringEmail1008" : "mno@gamil.com","dsStringEmail1014" : "hij@gamil.com","dsStringEmail1015" : "lmn@gamil.com"}|
#	  
#
#Scenario Outline: Add record to data service
#	Given Data service "string_Email"
#	Then Add record "<data>" to the data service		
#	And Expect error "Unique check validation failed for dsStringText1002" on save
#Examples:
#|data|
#|{ "_id" : "RC103","dsStringEmail1001" : "abc@gmail.com","dsStringEmail1002" : "ac@gmail.com","dsStringText1003" : "xyz@gamil.com","dsStringEmail1007" : "7","dsStringEmail1008" : "xyz@gamil.com","dsStringEmail1014" : "x@gamil.com","dsStringEmail1015" : "xyz@gamil.com}|
#		  


	
	Scenario Outline: Fetch record from the data service
	Given Data service "string_Email"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>"
Examples:
|id|data|
|RC101|{"_id": "RC101","dsStringEmail1001": "abc@gmail.com","dsStringEmail1002": "ac@gmail.com","dsStringEmail1003": "xyz@gamil.com","dsStringEmail1004": "xyz@gamil.com","dsStringEmail1005": "xyz@gamil.com","dsStringEmail1007": "xyz@gamil.com", "dsStringEmail1008": "xyz@gamil.com",  "dsStringEmail1010": "xyz@gamil.com", "dsStringEmail1013": "xyz@gamil.com", "dsStringEmail1014": "x@gamil.com", "dsStringEmail1015": "xyz@gamil.com", "dsStringEmail1018": "xyz@gamil.com", "dsStringEmail1020": "xyz@gamil.com"}|

	
	Scenario Outline: Update record to data service
	Given Data service "string_Email"
	Then Update record "<id>" with "<data>" to the data service
Examples:

|id|data|
|RC101|{"dsStringEmail1001": "abc@gmail.com","dsStringEmail1002": "atc@gmail.com","dsStringEmail1003": "wxy@gamil.com","dsStringEmail1004": "xyz@gamil.com","dsStringEmail1005": "xyz@gamil.com","dsStringEmail1007": "xy0z@gamil.com", "dsStringEmail1008": "x99@gamil.com","dsStringEmail1010": "xyz@gamil.com", "dsStringEmail1013": "xyz@gamil.com","dsStringEmail1014": "x123y@gamil.com", "dsStringEmail1015": "yz06@gamil.com""dsStringEmail1018": "xyz@gamil.com", "dsStringEmail1020": "xyz@gamil.com"}|

Scenario Outline: Fetch record from the data service
	Given Data service "string_Email"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>"
Examples:
|id|data|
|RC101|{"_id": "RC101","dsStringEmail1001": "abc@gmail.com","dsStringEmail1002": "ac@gmail.com","dsStringEmail1003": "xyz@gamil.com","dsStringEmail1004": "xyz@gamil.com","dsStringEmail1005": "xyz@gamil.com","dsStringEmail1007": "xyz@gamil.com", "dsStringEmail1008": "xyz@gamil.com",  "dsStringEmail1010": "xyz@gamil.com", "dsStringEmail1013": "xyz@gamil.com", "dsStringEmail1014": "x@gamil.com", "dsStringEmail1015": "xyz@gamil.com", "dsStringEmail1018": "xyz@gamil.com", "dsStringEmail1020": "xyz@gamil.com"}|
	
Scenario Outline: Delete record from the data service
	Given Data service "string_Email"
	Then Delete record "<id>" from the data service
	And deleting from listing page
Examples:
|id|
|RC101|
