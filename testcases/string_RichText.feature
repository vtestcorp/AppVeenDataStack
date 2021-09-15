Feature: DS STRING-RICH-TEXT

# Scenarios - DS STRING RICH TEXT 1001 - DS STRING RICH TEXT 1018

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|test_appadmin@appveen.com|123123123|

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
	|test_ac_ds_manage@appveen.com|

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
|test_ac_ds_manage@appveen.com|123123123|


 #INSERT/UPDATE
Scenario: Add data to data service
	Given Data service "string_RichText"
	Then Add data to the stringRichText data service
	
	
Scenario Outline: Add record to data service
	Given Data service "string_RichText"
	Then Add record "<data>" to the stringRichText data service
	And Expect error "DS-STRING-RICH-TEXT 1002 error" on label "DS-STRING-RICH-TEXT 1002 label"
	And Save button is disabled
Examples:
|data|
|{"_id": "STR1002","dsStringRichText1001": "amazon","dsStringRichText1002": "","dsStringRichText1003": "flipkart","dsStringRichText1004": "mintra","dsStringText1005": "StarBazaar","dsStringRichText1007": "dmart", "dsStringRichText1008": "bigBazar", "dsStringRichText1010": "bigBasket", "dsStringRichText1011": "Mango", "dsStringRichText1013": "online shooping", "dsStringRichText1014": " ", "dsStringRichText1015": " ",  "dsStringRichText1018": "Hello"}|


Scenario Outline: Add record to data service
	Given Data service "string_RichText"
	Then Add record "<data>" to the stringRichText data service	
	And Expect error "ID STR1001 already exists." on save
	Examples:
		|data|
	|{"_id": "STR1001","dsStringRichText1001": "Mango","dsStringRichText1002": "apple","dsStringRichText1003": "Banana","dsStringRichText1004": "Grapes","dsStringRichText1005": "Pineapple","dsStringRichText1007": "Guava", "dsStringRichText1008": "Apricot", "dsStringRichText1010": "Black Current","dsStringRichText1011": "Mango", "dsStringRichText1013": "Black Berry", "dsStringRichText1014": "Blue Berry", "dsStringRichText1015": "Custard Apple",  "dsStringRichText1018": "Coconut"}|
	

Scenario Outline: Add record to data service
	Given Data service "string_RichText"
	Then Add record "<data>" to the stringRichText data service		
	And Expect error "Unique check validation failed for dsStringText1002" on save
Examples:
|data|
|{"_id": "STR1003","dsStringRichText1001": "Mango","dsStringRichText1002": "Hello","dsStringRichText1003": "Banana","dsStringRichText1004": "Grapes","dsStringRichText1005": "Pineapple","dsStringRichText1007": "Guava", "dsStringRichText1008": "Apricot", "dsStringRichText1010": "Black Current",  "dsStringRichText1013": "Black Berry", "dsStringRichText1014": "Blue Berry", "dsStringRichText1015": "Custard Apple",  "dsStringRichText1018": "Coconut"}|

	
	Scenario Outline: Fetch record from the data service
	Given Data service "string_RichText"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>" for RichText
Examples:
|id|data|
|STR1001|{"_id": "STR1001","dsStringRichText1001": "Flipkart","dsStringRichText1002": "Hello","dsStringRichText1003": "Amazon","dsStringRichText1004": "BigBazar","dsStringRichText1005":"BigBasket","dsStringRichText1007": "Dmart", "dsStringRichText1008": "StarBazaar", "dsStringRichText1010": "Walmart","dsStringRichText1013":"Pantaloon","dsStringRichText1014":"Reliance", "dsStringRichText1015":"Dcathlon","dsStringRichText1018" : "Seasons"}|

	Scenario Outline: Update record to data service
	Given Data service "string_RichText"
	Then Update record "<id>" with "<data>" to the stringRichText data service
Examples:

|id|data|
|STR1001|{"_id": "STR1001","dsStringRichText1001": "Amazon","dsStringRichText1002": "Season","dsStringRichText1003": "Shopify","dsStringRichText1004": "Mintra","dsStringRichText1005": "Dmart","dsStringRichText1007": "bigBazar", "dsStringRichText1008": "Swiggy", "dsStringRichText1010": "BigBasket",  "dsStringRichText1013": "Walmart", "dsStringRichText1014": "Pantaloon", "dsStringRichText1015": "Star Bazar",  "dsStringRichText1018" : "Dcathlon"}|

Scenario Outline: Fetch record from the data service
	Given Data service "string_RichText"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>" for RichText
Examples:
|id|data|
|STR1001|{"_id": "STR1001","dsStringRichText1001": "Amazon","dsStringRichText1002": "Hello","dsStringRichText1003": "Shopify","dsStringRichText1004": "BigBazar","dsStringRichText1005": "Dmart","dsStringRichText1007": "Dmart", "dsStringRichText1008": "Swiggy", "dsStringRichText1010": "Walmart",  "dsStringRichText1013": "Walmart", "dsStringRichText1014": "Pantaloon", "dsStringRichText1015": "Star Bazar",  "dsStringRichText1018" : "Dcathlon"}|

	
Scenario Outline: Delete record from the data service
	Given Data service "string_RichText"
	Then Delete record "<id>" from the data service
	And Deleting from listing page
Examples:
|id|
|STR1001|

Scenario: Log out of App Center
Given User log out from AppCenter

