Feature: DS DATE

# Scenarios - DS DATE 1001-DS DATE 1017

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|test_ui_appadmin@appveen.com|Veen@99%win|

Scenario: Delete data service
   	Given Data service "date" exists
    Then Remove the data service

Scenario: Create data service
	Given Data service "date" does not exist
	Then Create new data service "date"
 #Under testData, picks up strings.json create the JSON

Scenario Outline: Assign to Appcenter Group
 	Then Group "Date-Group" does not exist
	Then Create new group "Date-Group" 
	And Assign appcenter permissions for "date" dataservice to "<user>"
	
	Examples:
	|user|
	|test_ui_ac_ds_manage@appveen.com|


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


#INSERT/UPDATE
Scenario: Add data to data service
	Given Data service "date"
	Then Add data to the data service for Date
	
	
	Scenario Outline: Add record to data service
	Given Data service "date"
	Then Add record "<data>" to the data service for Date
	And Expect error "DS DATE 1002 Error" on label "DS DATE 1002Label"
	And Save button is disabled
Examples:
|data|
|{"_id":"DAT1002", "dsDate1002":"", "dsDate1014":"","dsDate1015":"","dsDate1017":"","dsDate1018":""}|


Scenario Outline: Add record to data service
	Given Data service "date"
	Then Add record "<data>" to the data service for Date
	And Expect error "ID DAT1001 already exists." on save
	Examples:
		|data|
	  |{ "_id" : "DAT1001","dsDate1001" : "2031-07-25T00:00:00Z","dsDate1002" : "2029-08-8T00:00:00Z","dsDate1003" : "2031-08-8T00:00:00Z","dsDate1004" : "2031-04-4T00:00:00Z","dsDate1006" : "2040-06-6T00:00:00Z","dsDate1010" : "2020-10-10T00:00:00Z","dsDate1011" : "2031-08-25T00:00:00Z","dsDate1012" : "2035-12-12T00:00:00Z","dsDate1013" : "2031-03-13T00:00:00Z","dsDate1015" : "2021-07-28T00:00:00Z"}|


	Scenario Outline: Fetch record from the data service
	Given Data service "date"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>" Date Type
Examples:
|id|data|
|DAT1001|{"_id": "DAT1001","dsDate1001": "Wednesday, July 28, 2021 (Etc/Zulu)","dsDate1002": "Thursday, July 15, 2021 (Etc/Zulu)","dsDate1003": "Thursday, July 22, 2021 (Etc/Zulu)","dsDate1004": "Thursday, July 22, 2021 (Etc/Zulu)","dsDate1006": "Thursday, July 15, 2021 (Etc/Zulu)", "dsDate1010": "Thursday, July 22, 2021 (Etc/Zulu)", "dsDate1011": "Thursday, July 15, 2021 (Etc/Zulu)","dsDate1012": "Monday, October 27, 2025 (Etc/Zulu)", "dsDate1013": "Tuesday, January 1, 2030 (Etc/Zulu)", "dsDate1014": "Tuesday, January 30, 1990 (Etc/Zulu)", "dsDate1015": "Tuesday, October 20, 2020 (Etc/Zulu)", "dsDate1016": "Sunday, November 21, 2021 (Etc/Zulu)","dsDate1017": "Thursday, December 22, 2022 (Etc/Zulu)"}|
	
	
	Scenario Outline: Update record to data service
	Given Data service "date"
	Then Update record "<id>" with "<data>" to the data service for Date Type
Examples:

|id|data|
|DAT1001|{"dsDate1001" : "2031-08-2T00:00:00Z","dsDate1002" : "2029-08-28T00:00:00Z","dsDate1003" : "2035-07-30T00:00:00Z","dsDate1004" : "2021-04-5T00:00:00Z","dsDate1006" : "2040-08-4T00:00:00Z","dsDate1007" : "2025-1-20T00:00:00Z","dsDate1008" : "2027-12-1T00:00:00Z","dsDate1010" : "2050-12-13T00:00:00Z","dsDate1011" : "2035-06-2T00:00:00Z","dsDate1012" : "2035-12-12T00:00:00Z","dsDate1013" : "2031-03-1T00:00:00Z","dsDate1014" : "2015-01-18T00:00:00Z","dsDate1015" : "2021-07-2T00:00:00Z"}|

Scenario Outline: Fetch record from the data service
	Given Data service "date"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>" Date Type
Examples:
|id|data|
|DAT1001|{"_id": "DAT1001","dsDate1001": "Saturday, August 2, 2031 (Etc/Zulu)","dsDate1002": "Thursday, July 15, 2021 (Etc/Zulu)","dsDate1003": "Monday, July 30, 2035 (Etc/Zulu)","dsDate1004": "Thursday, July 22, 2021 (Etc/Zulu)","dsDate1006": "Thursday, July 15, 2021 (Etc/Zulu)", "dsDate1010": "Tuesday, December 13, 2050 (Etc/Zulu)", "dsDate1011": "Saturday, June 2, 2035 (Etc/Zulu)","dsDate1012": "Wednesday, December 12, 2035 (Etc/Zulu)", "dsDate1013": "Saturday, March 1, 2031 (Etc/Zulu)", "dsDate1014": "Tuesday, January 30, 1990 (Etc/Zulu)", "dsDate1015": "Friday, July 2, 2021 (Etc/Zulu)", "dsDate1016": "Sunday, November 21, 2021 (Etc/Zulu)","dsDate1017": "Thursday, December 22, 2022 (Etc/Zulu)"}|
	
Scenario Outline: Delete record from the data service
	Given Data service "date"
	Then Delete record "<id>" from the data service
	And Deleting from listing page
Examples:
|id|
|DAT1001|

Scenario: Log out of App Center
	Given User log out from AppCenter

