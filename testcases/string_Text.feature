Feature: DS STRING TEXT

# Scenarios - DS STRING TEXT 1001-DS STRING TEXT 1024

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|test_ui_appadmin@appveen.com|Veen@99%win|

#Scenario: Stop Data Service
#Given Stop dataservice "string_Text"

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
|test_ui_ac_ds_manage@appveen.com |

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
|test_ui_ac_ds_manage@appveen.com|Veen@99%win|

Scenario: Add data to data service
	Given Data service "string_Text"
	Then Add data to the data service
	
Scenario Outline: Add record to data service
	Given Data service "string_Text"
	Then Add record "<data>" to the data service
	And Expect error "DS STRING TEXT Error" on label "DS STRING TEXT Label"
	And Save button is disabled
Examples:
|data|
|{"_id": "STR1001","dsStringText1001": "amazon","dsStringText1002": "","dsStringText1003": "flipkart","dsStringText1004": "mintra","dsStringText1005": "starbazar","dsStringText1007": "dmart", "dsStringText1008": "bigBazar", "dsStringText1010": "bigBasket",  "dsStringText1013": "online shooping", "dsStringText1014": "", "dsStringText1015": "",  "dsStringText1018": "",  "dsStringText1020": "ABCD", "dsStringText1021": "season", "dsStringText1022": "cathlon", "dsStringText1023": "shopper stop", "dsStringText1024": "snapdel"}|

Scenario Outline: Add record to data service
	Given Data service "string_Text"
	Then Add record "<data>" to the data service	
	And Expect error "ID STR1001 already exists." on save
Examples:
|data|
|{"_id": "STR1001","dsStringText1001": "Mango","dsStringText1002": "apple","dsStringText1003": "Banana","dsStringText1004": "Grapes","dsStringText1005": "Pineapple","dsStringText1007": "Guava", "dsStringText1008": "Apricot", "dsStringText1010": "Black Current",  "dsStringText1013": "Black Berry", "dsStringText1014": "Blue Berry", "dsStringText1015": "Custard Apple",  "dsStringText1018": "Coconut",  "dsStringText1020": "DATE", "dsStringText1021": "F", "dsStringText1022": "Gooseberry", "dsStringText1023": "Jackfruit", "dsStringText1024": "Lamon"}|
	
Scenario Outline: Add record to data service
	Given Data service "string_Text"
	Then Add record "<data>" to the data service		
	And Expect error "Unique check validation failed for dsStringText1002" on save
Examples:
|data|
|{"_id": "STR1002","dsStringText1001": "Rose","dsStringText1002": "shopify","dsStringText1003": "Lotus","dsStringText1004": "Butterfly Pea","dsStringText1005": "Crossandra","dsStringText1007": "Golden Shower", "dsStringText1008": "Forest Ghost", "dsStringText1010": "Marigold",  "dsStringText1013": "Jasmine", "dsStringText1014": "Night Blooming", "dsStringText1015": "Sambac",  "dsStringText1018": "Sunflower",  "dsStringText1020": "XYZ", "dsStringText1021": "M", "dsStringText1022": "Peacock", "dsStringText1023": "Hibiscus", "dsStringText1024": "Daisy"}|
	
Scenario Outline: Fetch record from the data service
	Given Data service "string_Text"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>"
Examples:
|id|data|
|STR1001|{"_id": "STR1001","dsStringText1001": "builder","dsStringText1002": "shopify","dsStringText1003": "amazon","dsStringText1004": "shopperstops","dsStringText1005": "snapdeal","dsStringText1007": "walmart", "dsStringText1008": "pantaloon", "dsStringText1010": "myntra",  "dsStringText1013": "limeraod", "dsStringText1014": "shopclues", "dsStringText1015": "tatacliq",  "dsStringText1018": "paytmmall",  "dsStringText1020": "ABCD", "dsStringText1021": "P", "dsStringText1022": "asksly", "dsStringText1023": "wrangler", "dsStringText1024": "flipkart"}|
	
Scenario Outline: Update record to data service
	Given Data service "string_Text"
	Then Update record "<id>" with "<data>" to the data service
Examples:
|id|data|
|STR1001|{"_id": "STR1001","dsStringText1001": "zerodha","dsStringText1002": "swiggy","dsStringText1003": "builder","dsStringText1004": "dmart","dsStringText1005": "google","dsStringText1007": "yahoo", "dsStringText1008": "amazon", "dsStringText1010": "hp",  "dsStringText1013": "dell", "dsStringText1014": "apple", "dsStringText1015": "lenovo",  "dsStringText1018": "sony",  "dsStringText1020": "MM", "dsStringText1021": "N", "dsStringText1022": "amazon", "dsStringText1023": "vivo", "dsStringText1024": "oppo"}|

Scenario Outline: Fetch record from the data service
	Given Data service "string_Text"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>"
Examples:
|id|data|
|STR1001|{"_id": "STR1001","dsStringText1001": "zerodha","dsStringText1002": "shopify","dsStringText1003": "builder","dsStringText1004": "shopperstops","dsStringText1005": "google","dsStringText1007": "walmart", "dsStringText1008": "amazon", "dsStringText1010": "myntra",  "dsStringText1013": "dell", "dsStringText1014": "apple", "dsStringText1015": "lenovo", "dsStringText1018": "sony", "dsStringText1020": "MM", "dsStringText1021": "N", "dsStringText1022": "amazon", "dsStringText1023": "vivo", "dsStringText1024": "oppo"}|
	
Scenario Outline: Delete record from the data service
	Given Data service "string_Text"
	Then Delete record "<id>" from the data service
	And Deleting from listing page
Examples:
|id|
|STR1001|

Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|test_ui_appadmin@appveen.com|Veen@99%win|

Scenario: Stop Data Service
Given Stop dataservice "string_Text"

Scenario: Log out of Author
	Given User logged into Author
	Then User logs out of Author

Scenario: Log out of App Center
	Given User log out from AppCenter