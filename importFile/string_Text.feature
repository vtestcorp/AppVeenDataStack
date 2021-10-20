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

Scenario: Import data to data service
	Given Data service "string_Text"
	Then 	Upload file "Customer" to the import page of data service

Scenario Outline: Provide File settings for importing
	Given User navigates to File setting page
	Then Map "<file_settings>" to the import
	Then User navigate to column mapping page
	Examples:
	|file_settings|
	|{"sheetToRead":"string_Text","rowsToSkipFromTop":"4","rowsToSkipFromBottom":"7","markFirstRecordAsHeader":"True"}|
	
	Scenario Outline: Complete column mapping of the file to DS
	Then Map column "<ds_attribute>" to the attributes "<column_names>"
	Examples:
	|ds_attribute|column_names|
	|ID|STR1004|
	|DS STRING TEXT 1001|Num1001|
	|DS STRING TEXT 1002 Label|c|
	|DS STRING TEXT 1003|Num1003|
	|DS STRING TEXT 1004|Num1004|
	|DS STRING TEXT 1005|Num1005|
	|DS STRING TEXT 1006|Num1006|
	|DS STRING TEXT 1007|Num1007|
	|DS STRING TEXT 1008|Num1008|
#	|DS STRING TEXT 1009|L|
	|DS STRING TEXT 1010|Num1010|
#	|DS STRING TEXT 1011|N|
#	|DS STRING TEXT 1012|O|
	|DS STRING TEXT 1013 Label|Num1013|
	|DS STRING TEXT 1014|Num1014|
	|DS STRING TEXT 1015 Label|Num1015|
#	|DS STRING TEXT 1016|B|
#	|DS STRING TEXT 1017|RR|
	|DS STRING TEXT 1018|Num1018|
#	|DS STRING TEXT 1019|Q|
	|DS STRING TEXT 1020|Num1020|
	|DS STRING TEXT 1021|Num1021|
	|DS STRING TEXT 1022|Num1022|
	|DS STRING TEXT 1023|Num1023|
	|DS STRING TEXT 1024|Num1024|

Scenario: Column Mapping Complete
Then User navigate to column mapping page 
Then User navigate to validating record

Scenario Outline: Complete review of valid records 
	Given Verify Valid record "<count>" is correct
	Examples:
	|count|
	|100|

	Scenario Outline: Complete review of conflict records
	Given Select "<conflict_records>" to be updated
	Examples:
	|conflict_records|
	|11|
	|14|
	|37|
	|470|