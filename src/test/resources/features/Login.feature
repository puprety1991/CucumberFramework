Feature: Login functionality

  @sprint1 @regression @tc1101 @login
  Scenario: Valid admin login
    #Given user is navigated to HRMS application --> it is replaced by hooks
    When user enters valid username and valid password
    And user clicks on login button
    Then user is successfully logged in

  @regression @tc1102 @login
  Scenario: Valid ess login
   # Given user is navigated to HRMS application
    When user enters ess username and ess password
    And user clicks on login button
    Then user is successfully logged in

  @tc1103 @login
  Scenario: Invalid admin login
   # Given user is navigated to HRMS application
    When user enters invalid username and password
    And user clicks on login button
    Then error message displayed

   @tc1104
  Scenario Outline: Invalid login Functionality
    When user enters different "<username>" and "<password>" amd verify the "<error>" for it
    Examples:
      | username | password | error |
      |admin     |messi     |Invalid credentials|
      |leo       |Hum@nhrm123|Invalid credentials|
      |admin     |           |Password cannot be empty|
      |          |Hum@nhrm123|Username cannot be empty|

