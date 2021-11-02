Feature: DS STRING TEXT

# Scenarios - DS STRING TEXT 1001-DS STRING TEXT 1024

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|vtest@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "string_Text" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "string_Text" does not exist
	Then Create new data service "string_Text"

Scenario Outline: Assign to Appcenter Group
 	Then Group "String-Group" does not exist
	Then Create new group "String-Group" 
	And Assign appcenter permissions for "string_Text" dataservice to "<user>"
	
	Examples:
	|user|
	|vtest@appveen.com|


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
|vtest@appveen.com|123123123|

Scenario Outline: Add record to data service
	Given Data service "string_Text"
	Then Add record "<data>" to the data service
Examples:
|data|
|{"_id": "STR1002","dsStringText1001": "Rose","dsStringText1002": "shopify","dsStringText1003": "Lotus","dsStringText1004": "Butterfly Pea","dsStringText1005": "Crossandra","dsStringText1007": "Golden Shower", "dsStringText1008": "Forest Ghost", "dsStringText1010": "Marigold",  "dsStringText1013": "Jasmine", "dsStringText1014": "Night Blooming", "dsStringText1015": "Sambac",  "dsStringText1018": "Sunflower",  "dsStringText1020": "XYZ", "dsStringText1021": "M", "dsStringText1022": "Peacock", "dsStringText1023": "Hibiscus", "dsStringText1024": "Daisy"}|


Scenario: Import data to data service
	Given Data service "string_Text"
	Then 	Upload file "Customer" to the import page of data service

Scenario Outline: Provide File settings for importing
	Given User navigates to File setting page
	Then Map "<file_settings>" to the import
	Then User navigate to column mapping page
	Examples:
	|file_settings|
	|{"sheetToRead":"Sheet1","rowsToSkipFromTop": null,"rowsToSkipFromBottom": null,"markFirstRecordAsHeader":"True"}|
	
	
	
	Scenario Outline: Complete column mapping of the file to DS
	Then Map column "<ds_attribute>" to the attributes "<column_names>"
	Examples:
	|ds_attribute|column_names|
	|ID|ID|
	|DS STRING TEXT 1001|DS STRING TEXT 1001|
	|DS STRING TEXT 1002 Label|DS STRING TEXT 1002|
	|DS STRING TEXT 1003|DS STRING TEXT 1003|
	|DS STRING TEXT 1004|DS STRING TEXT 1004|
	|DS STRING TEXT 1005|DS STRING TEXT 1005|
	|DS STRING TEXT 1006|DS STRING TEXT 1006|
	|DS STRING TEXT 1007|DS STRING TEXT 1007|
	|DS STRING TEXT 1008|DS STRING TEXT 1008|
#	|DS STRING TEXT 1009|L|
	|DS STRING TEXT 1010|DS STRING TEXT 1010|
#	|DS STRING TEXT 1011|N|
#	|DS STRING TEXT 1012|O|
	|DS STRING TEXT 1013 Label|DS STRING TEXT 1013|
	|DS STRING TEXT 1014|DS STRING TEXT 1014|
	|DS STRING TEXT 1015 Label|DS STRING TEXT 1015|
#	|DS STRING TEXT 1016|B|
#	|DS STRING TEXT 1017|RR|
	|DS STRING TEXT 1018|DS STRING TEXT 1018|
#	|DS STRING TEXT 1019|Q|
	|DS STRING TEXT 1020|DS STRING TEXT 1020|
	|DS STRING TEXT 1021|DS STRING TEXT 1021|
	|DS STRING TEXT 1022|DS STRING TEXT 1022|
	|DS STRING TEXT 1023|DS STRING TEXT 1023|
	|DS STRING TEXT 1024|DS STRING TEXT 1024|

#Scenario: Column Mapping Complete
#Then User navigate to column mapping page 
#Then User navigate to validating record
#
#Scenario Outline: Complete review of valid records 
#	Given Verify Valid record "<count>" is correct
#	Examples:
#	|count|
#	|9|
#
#	Scenario Outline: Complete review of conflict records
#	Given Select "<conflict_records>" to be updated
#	Examples:
#	|conflict_records|
#	|2|
#	|14|
#	|37|
#	|470|