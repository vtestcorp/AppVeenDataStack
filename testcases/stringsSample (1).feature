Feature: Functions on Strings

# Scenarios - TS.., ..., ..

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|deepak@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "Sample1" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "Sample1" does not exist
	Then Create new data service "Sample1"
 #Under testData, picks up strings.json create the JSON

Scenario Outline: Assign to Appcenter Group
 	Then Group "String-Group" does not exist
	Then Create new group "String-Group" 
	And Assign appcenter permissions for "Sample1" dataservice to "<user>"
	
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


 #INSERT/UPDATE
Scenario: Add data to data service
	Given Data service "Sample1"
	Then Add data to the data service for file
	
#	Scenario Outline: Fetch record from the data service
#	Given Data service "Design-String"
#	Then Fetch record "<id>" from the data service
#	And Match it to "<data>"
#Examples:
#|id|data|
#|DES1001|{"_id": "DES1001","dsStringText1001": "1001","dsStringText1002": "a","dsStringText1003": "1003","dsStringText1004": "1004","dsStringText1005": "1005","dsStringText1006": null,"dsStringText1007": "1007", "dsStringText1008": "1008", "dsStringText1010": "1010", "dsStringText1011": null, "dsStringText1013": "1013", "dsStringText1014": "1014", "dsStringText1015": "1015"}|
#|DES1001|{"_id": "DES1001","dsStringText1001": "1001","dsStringText1002": "a","dsStringText1003": "1003","dsStringText1004": "1004","dsStringText1005": "1005","dsStringText1007": "1007", "dsStringText1008": "1008", "dsStringText1010": "1010",  "dsStringText1013": "1013", "dsStringText1014": "1014", "dsStringText1015": "1015"}|
#	
#	
#	Scenario Outline: Update record to data service
#	Given Data service "Design-String"
#	Then Update record "<id>" with "<data>" to the data service
#Examples:
#
#|id|data|
#|DES1001|{"dsStringText1001": "1111","dsStringText1002": "b","dsStringText1003": "1333","dsStringText1004": "1444","dsStringText1005": "1555","dsStringText1007": "1777", "dsStringText1008": "1888", "dsStringText1010": "101010",  "dsStringText1013": "101313", "dsStringText1014": "101414", "dsStringText1015": "101515"}|
#
#Scenario Outline: Fetch record from the data service
#	Given Data service "Design-String"
#	Then Fetch record "<id>" from the data service
#	And Match it to "<data>"
#Examples:
#|id|data|
#|DES1001|{"_id": "DES1001","dsStringText1001": "1111","dsStringText1002": "a","dsStringText1003": "1333","dsStringText1004": "1004","dsStringText1005": "1555","dsStringText1007": "1777", "dsStringText1008": "1888", "dsStringText1010": "101010",  "dsStringText1013": "101313", "dsStringText1014": "101414", "dsStringText1015": "101515"}|
#	
#Scenario Outline: Delete record from the data service
#	Given Data service "Design-String"
#	Then Delete record "<id>" from the data service
#	And deleting from listing page
#Examples:
#|id|
#|STR1001|


