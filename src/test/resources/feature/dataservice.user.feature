@DataService
Feature: Validate CRUD operations on data service users 
​
@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|vtest@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "users" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "users" does not exist
	Then Create new data service "users"

Scenario Outline: Delete group
	Given Group sampleGroup "<group>" exists
	Then Remove group "<group>"
	Examples:
		| group |
		| ManageGroup |
		| ReadOnlyGroup |

Scenario Outline: Add group
	Given Group sampleGroup "<group>" does not exists
	And Data service "<dataservice>"  exists
	Then Create group "<group>" and enable role "<role>" of "<dataservice>"
	Examples:
		| group | dataservice | role |
		| ManageGroup | users | Manage |
		| ReadOnlyGroup | users | View |

Scenario Outline: Assign permissions
	Given Data service "<dataservice>"  exists
	And Group "<group>" exists
	And User "<user>" exists
	Then Add "<user>" to "<group>"
	Examples:
		| group | dataservice | user |
		| ManageGroup | users | test_manage@appveen.com |
		| ReadOnlyGroup | users | test_readonly@appveen.com |

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

Scenario: Add data to data service
	Given Data service "users"
	Then Add data to the data service

Scenario: Data listing of data service
	Given Data service "users"
	Then On the listing page it should show the total count of documents as 11

Scenario: Remove data from data service
	Given Data service users
	Then Remove 1 record from the data service

Scenario: Remove data from data service
	Given Data service users
	Then Remove 10 records from the data service

Scenario: Data listing of data service
	Given Data service users
	Then On the listing page it should show the total count of documents as 0