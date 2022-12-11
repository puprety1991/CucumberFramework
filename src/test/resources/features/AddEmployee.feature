Feature: Add Employee
  Background:
    When user enters valid username and valid password
    And user clicks on login button
    Then user is successfully logged in
    When user clicks on PIM option
    And user clicks on Add Employee button

  @sprint3 @regression
  Scenario: Adding one employee
   # Given user is navigated to HRMS application
    And user enter firstname and lastname
    And user clicks on save button
    Then employee added successfully

    @test1
    Scenario: Adding one employee using feature file
      And user enter "Nelson" and "mandela"
      And user clicks on save button
      Then employee added successfully

      @outline
      Scenario Outline: Adding multiple employees using feature file
        And user enter "<firstName>" and "<lastName>" for adding multiple employees
        And user clicks on save button
        Then employee added successfully
        Examples:
          | firstName | lastName |
          |Rajesh     |Haml      |
          |Joe        |Biden     |
          |Kamala     |Harish    |

        @datatable
        Scenario: Adding multiple employee using data table
          When user adds multiple employees and verify they are added successfully
          |firstName|middleName|lastName|
          |Leo      |MS        |Messi   |
          |Argentina|MS        |Winner  |
          |Sachin   |MS        |Tendulkar|

        @excel
        Scenario: Adding multiple employees using excel file
          When user add multiple employees from excel using "EmployeeData" and verify it





