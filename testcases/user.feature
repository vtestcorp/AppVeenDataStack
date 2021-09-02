Feature: DS-USER

# Scenarios - DS-USER-1001 -DS-USER-1019

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|deepak@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "user" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "user" does not exist
	Then Create new data service "user"
 #Under testData, picks up strings.json create the JSON


Scenario Outline: Assign to Appcenter Group
 	Then Group "User-Group" does not exist
	Then Create new group "User-Group" 
	And Assign appcenter permissions for "user" dataservice to "<user>"
	
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
	Given Data service "user"
	Then Add data to the data service for user
	
	
	Scenario Outline: Add record to data service
	Given Data service "user"
	Then Add record "<data>" to the user data service
	And Expect error "DS-user 1002 Error" on label "DS-user 1002 Label"
	And Save button is disabled
Examples:
|data|
|{"_id":"DS1001", "dsUser1001": "deepak@appveen.com", "dsUser1002": "", "dsUser1003": "suchita@appveen.com", "dsUser1004": "test_readonly@appveen.com", "dsUser1005": "test_manage@appveen.com", "dsUser1006": "suchita@appveen.com", "dsUser1007": "", "dsUser1008": "", "dsUser1009": "test_readonly@appveen.com","dsUser1010": "suchita@appveen.com", "dsUser1011": "deepak@appveen.com", "dsUser1012": "test_manage@appveen.com", "dsUser1013": "deepak@appveen.com", "dsUser1014": "", "dsUser1015": "", "dsUser1016": "test_readonly@appveen.com", "dsUser1017": "suchita@appveen.com", "dsUser1018": "deepak@appveen.com", "dsUser1019": "test_manage@appveen.com"}|


 Scenario Outline: Add record to data service
	Given Data service "user"
	Then Add record "<data>" to the user data service
	And Expect error "ID DS1001 already exists" on save
	Examples:
		|data|
	|{"_id":"DS1001", "dsUser1001": "suchita@appveen.com", "dsUser1002": "deepak@appveen.com", "dsUser1003": "test_manage@appveen.com", "dsUser1004": "test_readonly@appveen.com", "dsUser1005": "test_manage@appveen.com", "dsUser1006": "deepak@appveen.com", "dsUser1007": "suchita@appveen.com", "dsUser1008": "maker@appveen.com", "dsUser1009": "test_readonly@appveen.com","dsUser1010": "reviewer@appveen.com", "dsUser1011": "deepak@appveen.com", "dsUser1012": "test_manage@appveen.com", "dsUser1013": "suchita@appveen.com", "dsUser1014": "deepak@appveen.com", "dsUser1015": "test_manage@appveen.com", "dsUser1016": "maker@appveen.com", "dsUser1017": "deepak@appveen.com", "dsUser1018": "reviewer@appveen.com", "dsUser1019": "test_manage@appveen.com"}|
	
	
	Scenario Outline: Add record to data service
	Given Data service "user"
	Then Add record "<data>" to the user data service
	And Expect error "Unique check validation failed for dsUser1002" on save
Examples:
|data|
|{"_id":"DS1002", "dsUser1001": "suchita@appveen.com", "dsUser1002": "deepak@appveen.com", "dsUser1003": "test_manage@appveen.com", "dsUser1004": "test_readonly@appveen.com", "dsUser1005": "test_manage@appveen.com", "dsUser1006": "deepak@appveen.com", "dsUser1007": "suchita@appveen.com", "dsUser1008": "maker@appveen.com", "dsUser1009": "test_readonly@appveen.com","dsUser1010": "reviewer@appveen.com", "dsUser1011": "deepak@appveen.com", "dsUser1012": "test_manage@appveen.com", "dsUser1013": "suchita@appveen.com", "dsUser1014": "deepak@appveen.com", "dsUser1015": "test_manage@appveen.com", "dsUser1016": "maker@appveen.com", "dsUser1017": "deepak@appveen.com", "dsUser1018": "reviewer@appveen.com", "dsUser1019": "test_manage@appveen.com"}|

	
	Scenario Outline: Fetch record from the data service
	Given Data service "user"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>"

Examples:
|id|data|
|DS1001|{"_id":"DS1001", "dsUser1001": "test_readonly@appveen.com", "dsUser1002": "deepak@appveen.com", "dsUser1003": "test_manage@appveen", "dsUser1004": "test_readonly@appveen.com", "dsUser1005": "test_manage@appveen.com", "dsUser1006": "deepak@appveen.com", "dsUser1007": "suchita@appveen.com", "dsUser1008": "maker@appveen.com", "dsUser1009": "test_readonly@appveen.com","dsUser1010": "reviewer@appveen.com", "dsUser1011": "deepak@appveen.com", "dsUser1012": "test_manage@appveen.com", "dsUser1013": "suchita@appveen.com", "dsUser1014": "deepak@appveen.com", "dsUser1015": "test_manage@appveen.com", "dsUser1016": "maker@appveen.com", "dsUser1017": "deepak@appveen.com", "dsUser1018": "reviewer@appveen.com", "dsUser1019": "test_manage@appveen"}|



	Scenario Outline: Update record to data service
	Given Data service "user"
	Then Update record "<id>" with "<data>" to the User
Examples:
|id|data|
|DS1001|{"_id":"DS1001", "dsUser1001": "deepak@appveen.com", "dsUser1002": "maker@appveen.com", "dsUser1003": "suchita@appveen.com", "dsUser1004": "reviewer@appveen.com", "dsUser1005": "maker@appveen.com", "dsUser1006": "test_manage@appveen.com", "dsUser1007": "test_readonly@appveen.com", "dsUser1008": "deepak@appveen.com", "dsUser1009": "suchita@appveen.com","dsUser1010": "maker@appveen.com", "dsUser1011": "test_manage@appveen.com", "dsUser1012": "suchita@appveen.com", "dsUser1013": "deepak@appveen.com", "dsUser1014": "test_manage@appveen.com", "dsUser1015": "maker@appveen.com", "dsUser1016": "test_readonly@appveen.com", "dsUser1017": "suchita@appveen.com", "dsUser1018": "test_manage@appveen@.com", "dsUser1019": "maker@appveen.com"}|


Scenario Outline: Fetch record from the data service
	Given Data service "user"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>"
Examples:
|id|data|
|DS1001|{"_id":"DS1001", "dsUser1001": "deepak@appveen.com", "dsUser1002": "maker@appveen.com", "dsUser1003": "suchita@appveen", "dsUser1004": "reviewer@appveen.com", "dsUser1005": "maker@appveen.com", "dsUser1006": "test_manage@appveen.com", "dsUser1007": "test_readonly@appveen.com", "dsUser1008": "deepak@appveen.com", "dsUser1009": "suchita@appveen.com","dsUser1010": "maker@appveen.com", "dsUser1011": "test_manage@appveen.com", "dsUser1012": "suchita@appveen.com", "dsUser1013": "deepak@appveen.com", "dsUser1014": "test_manage@appveen.com", "dsUser1015": "maker@appveen.com", "dsUser1016": "test_readonly@appveen.com", "dsUser1017": "suchita@appveen.com", "dsUser1018": "test_manage@appveen.com", "dsUser1019": "maker@appveen.com"}|


Scenario Outline: Delete record from the data service
	Given Data service "user"
	Then Delete record "<id>" from the data service
	And Deleting from listing page
Examples:
|id|
|DS1001|
