Feature: FileImport

#Scenario: Import data to data service
#	Given Data service "string_Text"
#	Then Upload file "<filename>" to the import page of data service
#	Examples:
#
#
Scenario Outline: Provide File settings for importing
#	Given User navigates to File setting page
#	Then map "<file_settings>" to the import
#	Examples:
#	|file_settings|
#	{"sheetToRead":"sheetname","rowsToSkipFromTop":"4","rowsToSkipFromBottom":"25","markFirstRecordAsHeader":"True"}
#//OR
    Then map "<file_settings>" to the correct "<values>" before import
    Examples:
    |file_settings|values|
    |sheetToRead|sheetname|
    |rowsToSkipFromTop|4|
    |rowsToSkipFromBottom|25|
    |markFirstRecordAsHeader|True|

Scenario Outline: Complete column mapping of the file to DS
	Given User navigate to column mapping page
	Then Map column "<column_names>" to the attributes "<ds_attribute>"
	Examples:
	|ds_attribute|column_names|
	|ID|STR1004|
	|DS STRING TEXT 1001|DD|
	|DS STRING TEXT 1002|c|
	|DS STRING TEXT 1003|D|
	|DS STRING TEXT 1004|W|
	|DS STRING TEXT 1005|R|
	|DS STRING TEXT 1006|H|
	|DS STRING TEXT 1007|J|
	|DS STRING TEXT 1008|K|
	|DS STRING TEXT 1009|L|
	|DS STRING TEXT 1010|M|
	|DS STRING TEXT 1011|N|
	|DS STRING TEXT 1012|O|
	|DS STRING TEXT 1013|P|
	|DS STRING TEXT 1014|XX|
	|DS STRING TEXT 1015|NN|
	|DS STRING TEXT 1016|B|
	|DS STRING TEXT 1017|RR|
	|DS STRING TEXT 1018|I|
	|DS STRING TEXT 1019|Q|
	|DS STRING TEXT 1020|Y|
	|DS STRING TEXT 1021|U|
	|DS STRING TEXT 1022|X|
	|DS STRING TEXT 1023|Z|
	|DS STRING TEXT 1024|T|

Scenario Outline: Complete review of valid records 
	Given upload is ready for review
	Then Verify Valid record "<count>" is correct
	Examples:
	|count|
	|100|

Scenario Outline: Complete review of conflict records
	Given upload is ready for review
	Then select "<conflict_records>" to be updated
	Examples:
	|conflict_records|
	|11|
	|14|
	|37|
	|470|

#	//OR 

#	Examples:
#	|conflict_records|
#	|update all| or |ignore all|




