Feature: DS STRING RICH TEXT

# Scenarios - DS STRING RICH TEXT 1001 - DS STRING RICH TEXT 1018

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|deepak@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "string_RichText" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "string_RichText" does not exist
	Then Create new data service "string_RichText"
 #Under testData, picks up strings.json create the JSON

Scenario Outline: Assign to Appcenter Group
 Then Group "Rich-text-Group" does not exist
 Then Create new group "Rich-text-Group" 
 And Assign appcenter permissions for "string_RichText" dataservice to "<user>"
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
	Given Data service "string_RichText"
	Then Add data to the stringRichText data service
	
#Scenario Outline: Add record to data service
#	Given Data service "string_RichText"
#	Then Add record "<data>" to the stringRichText data service
#	And Expect error "DS STRING TEXT Error" on label "DS STRING TEXT Label"
#	And Save button is disabled
#Examples:
#|data|
#|{"_id": "STR1002","dsStringRichText1001": "amazon","dsStringRichText1002": "","dsStringRichText1003": "flipkart","dsStringRichText1004": "mintra","dsStringText1005": "starbazar","dsStringRichText1007": "dmart", "dsStringRichText1008": "bigBazar", "dsStringRichText1010": "bigBasket",  "dsStringRichText1013": "online shooping", "dsStringRichText1014": "", "dsStringRichText1015": "",  "dsStringRichText1018": "",  "dsStringRichText1020": "ABCD", "dsStringRichText1021": "season", "dsStringRichText1022": "cathlon", "dsStringRichText1023": "shopper stop", "dsStringRichText1024": "snapdel"}|
#

#Scenario Outline: Add record to data service
#	Given Data service "string_RichText"
#	Then Add record "<data>" to the stringRichText data service	
#	And Expect error "ID STR1001 already exists" on save
#	Examples:
#		|data|
#	|{"_id": "STR1001","dsStringRichText1001": "Mango","dsStringRichText1002": "apple","dsStringRichText1003": "Banana","dsStringRichText1004": "Grapes","dsStringRichText1005": "Pineapple","dsStringRichText1007": "Guava", "dsStringRichText1008": "Apricot", "dsStringRichText1010": "Black Current",  "dsStringRichText1013": "Black Berry", "dsStringRichText1014": "Blue Berry", "dsStringRichText1015": "Custard Apple",  "dsStringRichText1018": "Coconut",  "dsStringRichText1020": "DATE", "dsStringRichText1021": "F", "dsStringRichText1022": "Gooseberry", "dsStringRichText1023": "Jackfruit", "dsStringRichText1024": "Lamon"}|
	
#	
#	Scenario Outline: Fetch record from the data service
#	Given Data service "Design-String"
#	Then Fetch record "<id>" from the data service
#	And Match it to "<data>"
#Examples:
#|id|data|
#|DES1001|{"_id": "DES1001","dsStringRichText1001": "1001","dsStringRichText1002": "a","dsStringRichText1003": "1003","dsStringRichText1004": "1004","dsStringRichText1005": "1005","dsStringRichText1006": null,"dsStringRichText1007": "1007", "dsStringRichText1008": "1008", "dsStringRichText1010": "1010", "dsStringRichText1011": null, "dsStringRichText1013": "1013", "dsStringRichText1014": "1014", "dsStringRichText1015": "1015"}|
#|DES1001|{"_id": "DES1001","dsStringRichText1001": "1001","dsStringRichText1002": "a","dsStringRichText1003": "1003","dsStringRichText1004": "1004","dsStringRichText1005": "1005","dsStringRichText1007": "1007", "dsStringRichText1008": "1008", "dsStringRichText1010": "1010",  "dsStringRichText1013": "1013", "dsStringRichText1014": "1014", "dsStringRichText1015": "1015"}|
#	
#	
#	Scenario Outline: Update record to data service
#	Given Data service "Design-String"
#	Then Update record "<id>" with "<data>" to the data service
#Examples:
#
#|id|data|
#|DES1001|{"dsStringRichText1001": "1111","dsStringRichText1002": "b","dsStringRichText1003": "1333","dsStringRichText1004": "1444","dsStringRichText1005": "1555","dsStringRichText1007": "1777", "dsStringRichText1008": "1888", "dsStringRichText1010": "101010",  "dsStringRichText1013": "101313", "dsStringRichText1014": "101414", "dsStringRichText1015": "101515"}|
#
#Scenario Outline: Fetch record from the data service
#	Given Data service "Design-String"
#	Then Fetch record "<id>" from the data service
#	And Match it to "<data>"
#Examples:
#|id|data|
#|DES1001|{"_id": "DES1001","dsStringRichText1001": "1111","dsStringRichText1002": "a","dsStringRichText1003": "1333","dsStringRichText1004": "1004","dsStringRichText1005": "1555","dsStringRichText1007": "1777", "dsStringRichText1008": "1888", "dsStringRichText1010": "101010",  "dsStringRichText1013": "101313", "dsStringRichText1014": "101414", "dsStringRichText1015": "101515"}|
#	
#Scenario Outline: Delete record from the data service
#	Given Data service "Design-String"
#	Then Delete record "<id>" from the data service
#	And deleting from listing page
#Examples:
#|id|
#|STR1001|
#
#
#
