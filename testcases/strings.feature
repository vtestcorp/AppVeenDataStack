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
	Given Data service "String-text1" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "String-text1" does not exist
	Then Create new data service "String-text1"
 #Under testData, picks up strings.json create the JSON

Scenario: Assign to Appcenter Group
 	Then Group "String-Group" does not exist
	Then Create new group "String-Group" 
	And assign appcenter permissions for "String-text1" dataservice


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




# INSERT/UPDATE
Scenario: Add data to data service
	Given Data service "String-text1"
	Then Add data to the data service



Scenario Outline: Add record to data service
	Given Data service "String-text1"
	Then Add record "<data>" to the data service
Examples:
|data|
|{ "_id" : "STR1002","dsStringText1001" : "1001","dsStringText1002" : "b","dsStringText1003" : "3","dsStringText1007" : "7","dsStringText1008" : "8","dsStringText1014" : "14","dsStringText1015" : "15"}|
|{ "_id" : "STR1003","dsStringText1001" : "11","dsStringText1002" : "c","dsStringText1003" : "13","dsStringText1007" : "17","dsStringText1008" : "118","dsStringText1014" : "114","dsStringText1015" : "115"}|



Scenario Outline: Add record to data service
	Given Data service "String-text1"
	Then Add record "<data>" to the data service
	And Expect error "DS STRING TEXT 1002 Error" on label "DS STRING TEXT 1002 Label"
	And Save button is disabled
Examples:
|data|
|{"_id":"STR1003", "dsStringText1002":"", "dsStringText1003":"1003"}|
#
#
Scenario Outline: Add record to data service
	Given Data service "String-text1"
	Then Add record "<data>" to the data service	
	And Expect error "ID 123 already exists" on save
	Examples:
		|data|
	  |{ "_id" : "STR1002","dsStringText1001" : "1001","dsStringText1002" : "c","dsStringText1003" : "3","dsStringText1007" : "7","dsStringText1008" : "18","dsStringText1014" : "14","dsStringText1015" : "15"}|


Scenario Outline: Add record to data service
	Given Data service "String-text1"
	Then Add record "<data>" to the data service		
	And Expect error "Unique check validation failed for dsStringText1002" on save
Examples:
|data|
|{ "_id" : "STR1004","dsStringText1001" : "1001","dsStringText1002" : "b","dsStringText1003" : "3","dsStringText1007" : "7","dsStringText1008" : "18","dsStringText1014" : "14","dsStringText1015" : "15"}|



 #UPDATE
Scenario Outline: Update record to data service
	Given Data service "String-text1"
	Then Update record "<id>" with "<data>" to the data service
Examples:

|id|data|
|STR1002|{"dsStringText1003":"t"}|



Scenario Outline: Update record to data service
	Given Data service "String-text1"
	Then Update record "<id>" with "<data>" to the data service
	And Expect error "DS STRING TEXT 1014 Error" on label "dsStringText1014"
Examples:
|id|data|
|STR1002|{"dsStringText1014":"", "dsStringText1015":"1015"}|


Scenario Outline: Update record to data service
	Given Data service "String-text1"
	Then Update record "<id>" with "<data>" to the data service
	And Expect error "Unique check validation failed for dsStringText1008" on save
Examples:
|id|data|
|STR1002|{"dsStringText1008":"1008"}|


Scenario Outline: Fetch record from data service
	Given Data service "String-text1"
	Then Fetch record "<id>" from the data service
Examples:
|id|
|STR1001|

Scenario Outline: Fetch record from the data service
	Given Data service "String-text1"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>"
Examples:
|id|data|
|STR1002|{ "_id" : "STR1002","dsStringText1001" : "1001","dsStringText1002" : "b","dsStringText1003" : "t","dsStringText1007" : "7","dsStringText1008" : "8","dsStringText1014" : "14","dsStringText1015" : "1015"}|


Scenario Outline: Fetch record from the data service
	Given Data service "String-text1"
	Then Fetch record by searching "<DS String Text 1001>" with "<DS String Text 1002 label>" from the data service
	And Match it to "<data>"
Examples:
|DS String Text 1001|DS String Text 1002 label|data|
|b|t|{ "_id" : "STR1002","dsStringText1001" : "1001","dsStringText1002" : "b","dsStringText1003" : "t","dsStringText1007" : "7","dsStringText1008" : "8","dsStringText1014" : "14","dsStringText1015" : "1015"}|

# GET
Scenario Outline: Fetch record from data service
	Given Data service "String-text1"
	Then Fetch record "<id>" from the data service
	And Record must not exist
Examples:
|id|
|ID1001|

# DELETE
Scenario Outline: Delete record from the data service
	Given Data service "String-text1"
	Then Delete record "<id>" from the data service
	And deleting from listing page
Examples:
|id|
|STR1001|

# search and delete from listing page
Scenario Outline: Delete record from the data service
	Given Data service "String-text1"
	Then Delete record by searching "<DS String Text 1001>" with "<Ds String Text 1002 label>" from the data service.
	And deleting from listing page
Examples:
|DS String Text 1001|Ds String Text 1002 label|
|a|1003|

# search and delete from view page
Scenario Outline: Delete record from the data service
	Given Data service "String-text1"
	Then Delete record by searching "<DS String Text 1001>" with "<Ds String Text 1002 label>" from the data service
	And deleting from view page
Examples:
|DS String Text 1001|Ds String Text 1002 label|
|a|1003|
