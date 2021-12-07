Feature: Add user

  @Author
  Scenario Outline: Log into Author using Super Admin
    Given User navigate to Author login page
    And User enters "<username>" and "<password>" in Author login page
    And Verify User has Logged in successfully in Author Url
    Then Select "vTest" app
   Examples: 
      | username          | password  |
       | admin | u?5k167v13w5 |
      
Scenario Outline: Add users
 Given Add "<username>" and "<password>" if not present
 When Make App Admin
 
 Examples: 
      | username                            |password|
      | test_ui_appadmin@appveen.com		    |Veen@99%win|
      | test_ui_ac_ds_manage@appveen.com   |Veen@99%win|
   
  Scenario: Log out of Author
    Given User logged into Author
    Then User logs out of Author
    Then Close Browser