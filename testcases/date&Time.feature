Feature: DS DATE & TIME

# Scenarios - DS DATE & TIME 1001-DS DATE & TIME 1017

@Author
Scenario Outline: Log into Author
	Given User navigate to Author login page
	And User enters "<username>" and "<password>" in Author login page
	And Verify User has Logged in successfully in Author Url
Examples:
|username|password|
|test_appadmin@appveen.com|123123123|

Scenario: Delete data service
	Given Data service "date&Time" exists
	Then Remove the data service

Scenario: Create data service
	Given Data service "date&Time" does not exist
	Then Create new data service "date&Time"
 #Under testData, picks up strings.json create the JSON

Scenario Outline: Assign to Appcenter Group
 	Then Group "Date & Time-Group" does not exist
	Then Create new group "Date & Time-Group" 
	And Assign appcenter permissions for "date&Time" dataservice to "<user>"
	
	Examples:
	|user|
	|test_ac_ds_manage@appveen.com|


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
|test_ac_ds_manage@appveen.com|123123123|


 #INSERT/UPDATE
Scenario: Add data to data service
	Given Data service "date&Time"
	Then Add data to the data service for Date
	
	Scenario Outline: Add record to data service
	Given Data service "date&Time"
	Then Add record "<data>" to the data service for Date
	And Expect error "DS DATE & TIME 1002 Error" on label "DS DATE & TIME 1002 Label"
	And Save button is disabled
Examples:
|data|
|{"_id":"DAT1002", "dsDateTime1002":"", "dsDateTime1014":"","dsDateTime1015":"","dsDateTime1017":"","dsDateTime1018":""}|

	Scenario Outline: Add record to data service
	Given Data service "date&Time"
	Then Add record "<data>" to the data service for Date
	And Expect error "ID DAT1001 already exists." on save
	Examples:
		|data|
	  |{ "_id" : "DAT1001","dsDateTime1001" : "2031-07-25T01:10:25Z","dsDateTime1002" : "2029-08-8T5:05:00Z","dsDateTime1003" : "2031-08-8T08:50:24Z","dsDateTime1004" : "2031-04-4T12:25:10Z","dsDateTime1006" : "2040-06-6T21:05:25Z","dsDateTime1010" : "2020-10-10T2:15:20Z","dsDateTime1011" : "2031-08-25T10:30:20Z","dsDateTime1012" : "2035-12-12T10:45:58Z","dsDateTime1013" : "2031-03-13T3:54:21Z","dsDateTime1015" : "2021-07-28T15:15:20Z"}|


	Scenario Outline: Fetch record from the data service
	Given Data service "date&Time"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>" Date Type
Examples:
|id|data|
|DAT1001|{"_id": "DAT1001","dsDateTime1001": "Monday, August 2, 2021, 10:20:30 AM (Zulu)","dsDateTime1002": "Wednesday, February 2, 2022, 2:02:02 AM (Etc/Zulu)","dsDateTime1003": "Friday, March 3, 2023, 3:03:33 AM (Etc/Zulu)","dsDateTime1006": "Saturday, June 6, 2026, 6:06:06 AM (Etc/Zulu)", "dsDateTime1010": "Wednesday, November 23, 2011, 12:11:22 PM (Etc/Zulu)", "dsDateTime1011": "Tuesday, November 11, 2031, 11:11:11 AM (Etc/Zulu)","dsDateTime1012": "Sunday, December 12, 2032, 6:01:53 PM (Etc/Zulu)", "dsDateTime1013": "Saturday, January 30, 2021, 11:31:44 PM (Etc/Zulu)","dsDateTime1014":"Monday, January 29, 1990, 11:31:31 PM (Etc/Zulu)", "dsDateTime1015": "Sunday, December 20, 2054, 7:56:36 PM (Etc/Zulu)", "dsDateTime1016": "Saturday, August 28, 2021, 6:05:15 PM (Etc/Zulu)","dsDateTime1017": "Saturday, August 7, 2021, 6:05:20 PM (Etc/Zulu)"}|

	
	
	Scenario Outline: UpDate record to data service
	Given Data service "date&Time"
	Then Update record "<id>" with "<data>" to the data service for Date Type
Examples:

|id|data|
|DAT1001|{"dsDateTime1001" : "2031-08-2T05:25:15Z","dsDateTime1002" : "2029-08-28T10:19:25Z","dsDateTime1003" : "2035-07-30T15:25:15Z","dsDateTime1004" : "2021-04-5T11:10:25Z","dsDateTime1006" : "2040-08-4T10:25:55Z","dsDateTime1007" : "2025-1-20T10:10:25Z","dsDateTime1008" : "2027-12-1T09:10:10Z","dsDateTime1010" : "2050-12-13T11:11:11Z","dsDateTime1011" : "2035-06-2T12:12:18Z","dsDateTime1012" : "2035-12-12T2:10:50Z","dsDateTime1013" : "2031-03-1T2:40:20Z","dsDateTime1015" : "2021-07-2T10:00:00Z","dsDateTime1016" : "2031-27-12T10:50:00Z","dsDateTime1017" : "2027-10-23T10:50:10Z"}|

Scenario Outline: Fetch record from the data service
	Given Data service "date&Time"
	Then Fetch record "<id>" from the data service
	And Match it to "<data>" Date Type
Examples:
|id|data|
|DAT1001|{"_id": "DAT1001","dsDateTime1001": "Saturday, August 2, 2031, 5:25:15 AM (Zulu)","dsDateTime1002": "Wednesday, February 2, 2022, 2:02:02 AM (Etc/Zulu)","dsDateTime1003": "Monday, July 30, 2035, 3:25:15 PM (Etc/Zulu)","dsDateTime1006": "Saturday, June 6, 2026, 6:06:06 AM (Etc/Zulu)", "dsDateTime1010": "Tuesday, December 13, 2050, 11:11:11 AM (Etc/Zulu)", "dsDateTime1011": "Saturday, June 2, 2035, 12:12:18 PM (Etc/Zulu)","dsDateTime1012": "Wednesday, December 12, 2035, 6:10:50 PM (Etc/Zulu)", "dsDateTime1013": "Saturday, March 1, 2031, 11:40:20 PM (Etc/Zulu)","dsDateTime1014":"Monday, January 29, 1990, 11:31:31 PM (Etc/Zulu)","dsDateTime1015": "Friday, July 2, 2021, 10:00:00 AM (Etc/Zulu)", "dsDateTime1016": "Sunday, January 12, 2031, 10:50:00 AM (Etc/Zulu)","dsDateTime1017": "Saturday, October 23, 2027, 10:50:10 AM (Etc/Zulu)"}|
	

Scenario Outline: Delete record from the data service
	Given Data service "date&Time"
	Then Delete record "<id>" from the data service
	And Deleting from listing page
Examples:
|id|
|DAT1001|

Scenario: Log out of App Center
	Given User log out from AppCenter

