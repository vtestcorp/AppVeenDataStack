Feature:ODP Author Application

Scenario Outline:TC-DS-STRING-TEXT-1001-01

	Given Access ODP Author Application
  And Enter "<username>" and "<password>" in Author application and login to application.
	And Click on create service icon
	And Enter "<name>" in name DS text box and "<description>" in Description box and click on create button
  And Click on + New Attibute and add "<data>"
  Then Click on Save and Deploy button
         Examples:
         |username          |password |name   |description                 |data|
         |deepak@appveen.com|123123123|Strings|Service with text attributes|    |
         
Scenario Outline:TS-DS-STRING-TEXT-1001-02

	Given Click on view on dataservice "Strings"
  And Verify all the attributes and properties are there as per user creation i.e. match with "<data>"
         Examples:
         |data|
         ||
         
         
Scenario Outline:TC-DS-STRING-TEXT-1001-03

		Given Login to ODP Appcenter
	  And Enter "<username>" and "<password>" in Appcenter application and login to application.
	  And Click on "Strings" data service and navigate to it
	  And Click on Add Data
	  And Without entering data in any attribute Click on Save button
	  And Again Click on "+ Add Data"
	  And Enter "<data>" in attributes and Click on Save button
	  Then Open above created record
	  Then User logs out of Appcenter
	  
	  Examples:
	   |username          |password |data|
     |deepak@appveen.com|123123123|    |
     
Scenario Outline:TC-DS-STRING-TEXT-1001-04

			Given Access ODP Author Application
      And Enter "<username>" and "<password>" in Author application and login to application.
      And Click on Edit on above created Service "Strings".
	    And Add new attibute with text data-type.
	    And Click on Save and Deploy button.
	    
	    Examples:
	   |username          |password |
     |deepak@appveen.com|123123123|
     
Scenario Outline:TC-DS-STRING-TEXT-1001-05

	Given Login to ODP Appcenter.
	 And Enter "<username>" and "<password>" in Appcenter application and login to application.
	 And Click on data service "Strings" and navigate to it
	 And Open any existing record in edit mode
	 And Enter data in attributes and Click on Save button
	 And Open above created record
	 
	  Examples:
	   |username          |password |
     |deepak@appveen.com|123123123|
     
Scenario Outline:TC-DS-STRING-TEXT-1001-06
		
		Given Access ODP Author Application
    And Enter "<username>" and "<password>" in Author application and login to application.
    And Click on options (^)icon on above created service and click on Clone icon.
    And Enter DS name "Strings-2" and select desired tabs to be cloned and click on create.
    And Click on Save and Deploy button
    
     Examples:
	   |username          |password |
     |deepak@appveen.com|123123123|
    
Scenario Outline:TC-DS-STRING-TEXT-1001-07

		Given Login to ODP Appcenter
		And Enter "<username>" and "<password>" in Appcenter application and login to application.
		And  Click on above created service (Clone) "Strings-2" and navigate to it.
		And Click on Add Data 
		And Without entering data in any attribute Click on Save button.
		And Again Click on Add Data.
		And Enter data in attributes and Click on Save button.
		And Open above created record.
		Then User logs out of Appcenter.
		
		Examples:
	   |username          |password |
     |deepak@appveen.com|123123123|
		
Scenario Outline:TC-DS-STRING-TEXT-1001-08

		Given Access ODP Author Application
    And Enter "<username>" and "<password>" in Author application and login to application.
		And Click on options (^)icon on data service "Strings" and click on Delete icon.
		And Click yes on delete modal
		
		Examples:
	   |username          |password |
     |deepak@appveen.com|123123123|
     
Scenario Outline:TC-DS-STRING-TEXT-1001-09

		Given Login to ODP Appcenter
		And Enter "<username>" and "<password>" in Appcenter application and login to application.
		And Try to find above delete service.
		
		Examples:
	   |username          |password |
     |deepak@appveen.com|123123123|
     
     


	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  