Feature: Functions on Strings

# Scenarios - TS.., ..., ..

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|jerry@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "strings" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "strings" does not exist
	Then Create new data service "strings"
# Under testData, picks up strings.json create the JSON

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
|jerry@appveen.com|123123123|

# INSERT/UPDATE
Scenario: Add data to data service
	Given Data service "strings"
	Then Add data to the data service
# Under testData, picks up strings.data.json insert / update the records

Scenario: Add record to data service
	Given Data service "strings"
	Then Add record "<data>" to the data service
Examples:
|data|
|{_id:"123", a:"asdasd"}|

Scenario: Add record to data service
	Given Data service "strings"
	Then Add record "<data>" to the data service
	And Expect error "asdasd" on label "dsStringText1002"
	And Expect error "ajkdhakshd" on label "dsStringText1002"
	And Save button is disabled
Examples:
|data|
|{_id:"123", a:"asdasd"}|

Scenario: Add record to data service
	Given Data service "strings"
	Then Add record "<data>" to the data service
	And Expect error "ID 123 already exists" on save
Examples:
|data|
|{_id:"123", a:"asdasd"}|

Scenario: Add record to data service
	Given Data service "strings"
	Then Add record "<data>" to the data service
	And Expect error "Unique check validation failed for dsStringText1002" on save
Examples:
|data|
|{_id:"123", a:"asdasd"}|

# UPDATE
Scenario: Update record to data service
	Given Data service "strings"
	Then updare record "<id>" with "<data>" to the data service
Examples:
|id|data|
|ID1001|{a:"123123"}|

Scenario: Update record to data service
	Given Data service "strings"
	Then updare record "<id>" with "<data>" to the data service
	And Expect error "asdasd" on label "dsStringText1002"
Examples:
|id|data|
|ID1001|{a:"123123"}|

Scenario: Update record to data service
	Given Data service "strings"
	Then updare record "<id>" with "<data>" to the data service
	And Expect error "Unique check validation failed for dsStringText1002" on save
Examples:
|id|data|
|ID1001|{a:"123123"}|

# GET
Scenario: Fetch record from data service
	Given Data service "strings"
	Then Fetch record "<id>" from the data service
Examples:
|id|
|ID1001|
|ID1001|
# listing page

Scenario: Fetch record from the data service
	Given Data service "strings"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>"
Examples:
|id|data|
|ID1001|{..}|
|ID1002|{..}|
# get and match the data in the view page

Scenario: Fetch record from the data service
	Given Data service "strings"
	Then Fetch record by searching "<label>" with "<searchstring>" from the data service
	And Match it to "<data>"
Examples:
|label|searchstring|data|
|dsStringText1002|sdfsdf|{..}|
|dsStringText1003|asd|{..}|
# Search the listing page, click on first record and match the data

# GET
Scenario: Fetch record from data service
	Given Data service "strings"
	Then Fetch record "<id>" from the data service
	And record must not exist
Examples:
|id|
|ID1001|
|ID1001|
# listing page

# DELETE
Scenario: Delete record from the data service
	Given Data service "strings"
	Then Delete record "<id>" from the data service
	And deleting from listing page
Examples:
|id|
|ID1001|
|ID1001|

# search and delete from listing page
Scenario: Delete record from the data service
	Given Data service "strings"
	Then Delete record by searching "<label>" with "<searchstring>" from the data service
	And deleting from listing page
Examples:
|label|searchstring|
|dsStringText1002|sdfsdf|
|dsStringText1003|asd|

# search and delete from view page
Scenario: Delete record from the data service
	Given Data service "strings"
	Then Delete record by searching "<label>" with "<searchstring>" from the data service
	And deleting from view page
Examples:
|label|searchstring|
|dsStringText1002|sdfsdf|
|dsStringText1003|asd|

Scenario: Delete record from the data service
	Given Data service "strings"
	Then Delete record by searching "<label>" with "<searchstring>" from the data service
	And deleting from view page
	And Expect error "Unique check validation failed for dsStringText1002" on save
Examples:
|label|searchstring|
|dsStringText1002|sdfsdf|
|dsStringText1003|asd|