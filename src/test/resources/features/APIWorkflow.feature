Feature: API workflow test

  Background: For Generating the token before every request
    Given a JWT is generated

 # @api @createEmp
  #Scenario: API test case for creating the employee
   # Given a request is prepared for creating employee
    #When a POST call is made to create an employee
    #Then the status code for creating an employee is 201
    #And the response body contains key "Message" and value "Employee Created"
    #And the employee id "Employee.employee_id" is stored as global to be used for other request

  #@api
  #Scenario: API test case for creating the employee using json body
   # Given a request is prepared for creating an employee by passing json body
    #When a POST call is made to create an employee
    #Then the status code for creating an employee is 201
    #And the response body contains key "Message" and value "Employee Created"
    #And the employee id "Employee.employee_id" is stored as global to be used for other request

  @api @creatingEmployeeDynamic
  Scenario: API test case for creating the employee using highly dynamic body
    Given a request is prepared for creating an employee with dynamic data "Robin","Trump","ms","M","1981-01-14","confirmed","Politician"
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the response body contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as global to be used for other request

  @api @getOneEmployee
    Scenario: Getting the created employee
      Given a request is prepared for getting a created employee
      When a Get a call is made to get this employee
      Then the status code for this employee is 200
      And the employee id "employee.employee_id" should match with global employee id
      And the retrieved data at "employee" object should match with the data  used for creating the employee
        |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
        |Robin         |Trump         |ms             |Male   |1981-01-14 |confirmed |Politician  |


  @api @updatingEmployeeDynamic
  Scenario: API test for updating the employee using dynamic
    Given a request is prepared for updating an employee with dynamic data "Ashley","Robinson","ms","F","2001-01-01","confirmed","QA Engineer"
    When a Put call is made to update an employee
    Then a status code for updating an employee is 200
    And the response body contains key "Message" and value is "Employee record Updated"

  @api @gettingUpdateEmployee
  Scenario: Getting the updated employee
    Given a request is prepared for getting a created employee
    When a Get a call is made to get this employee
    Then the status code for this employee is 200
    And the employee id "employee.employee_id" should match with global employee id
    And the retrieved data at "employee" object should match with the data  used for creating the employee
      |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
      |Ashley         |Robinson         |ms             |Female   |2001-01-01 |confirmed |QA Engineer  |


  @api @PartialUpdate
   Scenario: API test for dynamic partial update employee info
    Given a request is prepared for partial update an employee with dynamic data "Oscar"
    When a Patch is made to partial update an employee
    Then the status code for this employee is 201
    And a response body contains key "Message" and value is "Employee record updated successfully"

   @api @getPartiallyUpdate
   Scenario: Getting the partial updated employee
     Given a request is prepared for getting a created employee
     When a Get a call is made to get this employee
     Then the status code for this employee is 200
     And the employee id "employee.employee_id" should match with global employee id
     And the retrieved data at "employee" object should match with the data  used for creating the employee
       |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
       |Oscar         |Robinson         |ms             |Female   |2001-01-01 |confirmed |QA Engineer  |





