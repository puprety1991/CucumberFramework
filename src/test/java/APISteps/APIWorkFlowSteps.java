package APISteps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstant;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class APIWorkFlowSteps {
    RequestSpecification request;
    Response response;
    public static String employee_id;

  /*  @Given("a request is prepared for creating employee")
    public void a_request_is_prepared_for_creating_employee() {
         request = given().header(APIConstants.Header_Key_Content_Type,APIConstants.Header_Value_Content_Type)
                .header(APIConstants.Header_Key_Authorization,GenerateTokenSteps.token)
                .body(APIPayloadConstant.createEmployeePayload());
    }
    @Given("a request is prepared for creating an employee by passing json body")
    public void a_request_is_prepared_for_creating_an_employee_by_passing_json_body() {
        request = given().
                header(APIConstants.Header_Key_Content_Type, APIConstants.Header_Value_Content_Type).
                header(APIConstants.Header_Key_Authorization, GenerateTokenSteps.token).
                body(APIPayloadConstant.createEmployeeJsonBody());
    }*/
//-----------------------------------------Creating Employee----------------------------------------------------------------------------------
    @Given("a request is prepared for creating an employee with dynamic data {string},{string},{string},{string},{string},{string},{string}")
    public void a_request_is_prepared_for_creating_an_employee_with_dynamic_data
    (String firstName, String lastName, String middleName,
     String gender, String dob, String empStatus, String jobTitle) {
        request = given().
                header(APIConstants.Header_Key_Content_Type, APIConstants.Header_Value_Content_Type).
                header(APIConstants.Header_Key_Authorization, GenerateTokenSteps.token).
                body(APIPayloadConstant.createEmployeePayloadDynamic(firstName, lastName, middleName, gender, dob, empStatus, jobTitle));
    }

    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
        response = request.when().post(APIConstants.CREATE_EMPLOYEE_URI);
        response.prettyPrint();
    }

    @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(Integer int1) {
        response.then().assertThat().statusCode(int1);
    }

    @Then("the response body contains key {string} and value {string}")
    public void the_response_body_contains_key_and_value(String key, String value) {
        //arguments for key and value is coming from our feature file
        response.then().assertThat().body(key, equalTo(value));
    }

    @Then("the employee id {string} is stored as global to be used for other request")
    public void the_employee_id_is_stored_as_global_to_be_used_for_other_request(String empId) {
        employee_id = response.jsonPath().getString(empId);
        System.out.println("global id: " + employee_id);
    }

    //---------------------------------------Getting One Employee------------------------------------------------------------------------------------
    @Given("a request is prepared for getting a created employee")
    public void a_request_is_prepared_for_getting_a_created_employee() {
        request = given().header(APIConstants.Header_Key_Authorization, GenerateTokenSteps.token)
                .header(APIConstants.Header_Key_Content_Type, APIConstants.Header_Value_Content_Type)
                .queryParam("employee_id", employee_id);
    }

    @When("a Get a call is made to get this employee")
    public void a_get_a_call_is_made_to_get_this_employee() {
        response = request.when().get(APIConstants.ONE_EMPLOYEE_URI);
        response.prettyPrint();
    }

    @Then("the status code for this employee is {int}")
    public void the_status_code_for_this_employee_is(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the employee id {string} should match with global employee id")
    public void the_employee_id_should_match_with_global_employee_id(String employeeIdkey) {
        String tempEmpId = response.jsonPath().getString(employeeIdkey);
        Assert.assertEquals(tempEmpId, employee_id);
    }

    @Then("the retrieved data at {string} object should match with the data  used for creating the employee")
    public void the_retrieved_data_at_object_should_match_with_the_data_used_for_creating_the_employee
            (String employeeObject, DataTable dataTable) {
        //employeeObject will give the data from responseBody
        //data table provides the data in the form of key and value pair
        List<Map<String, String>> expectedData = dataTable.asMaps();
        // to get all the keys and values of employee object, we use jsonPath.get method
        Map<String, String> actualData = response.body().jsonPath().get(employeeObject);
        for (Map<String, String> map : expectedData) {
            // it returns all the keys
            Set<String> keys = map.keySet();
            for (String key : keys) {
                String expectedValue = map.get(key);
                String actualValue = actualData.get(key);
                Assert.assertEquals(expectedValue, actualValue);
            }
        }
    }
    //---------------------------------------Updating  Employee in details--------------------------------------------------------------------

    @Given("a request is prepared for updating an employee with dynamic data {string},{string},{string},{string},{string},{string},{string}")
    public void a_request_is_prepared_for_updating_an_employee_with_dynamic_data(String firstName, String lastName, String middleName,
                                                                                 String gender, String dob, String empStatus, String jobTitle) {

        request = given().header(APIConstants.Header_Key_Authorization, GenerateTokenSteps.token)
                .header(APIConstants.Header_Key_Content_Type, APIConstants.Header_Value_Content_Type)
                .body(APIPayloadConstant.updateEmployeePayLoadDynamic(employee_id,firstName, lastName, middleName, gender, dob, empStatus, jobTitle));

    }

    @When("a Put call is made to update an employee")
    public void a_put_call_is_made_to_update_an_employee() {
        response = request.when().put(APIConstants.UPDATE_EMPLOYEE_URI);
        response.prettyPrint();
    }

    @Then("a status code for updating an employee is {int}")
    public void a_status_code_for_updating_an_employee_is(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the response body contains key {string} and value is {string}")
    public void the_response_body_contains_key_and_value_is(String key, String value) {
        response.then().assertThat().body(key, equalTo(value));
    }
//-------------------------------------------Partially Update------------------------------------------------------------------------------------
 @Given("a request is prepared for partial update an employee with dynamic data {string}")
    public void a_request_is_prepared_for_partial_update_an_employee_with_dynamic_data(String firstname) {
        request = given().header(APIConstants.Header_Key_Authorization,GenerateTokenSteps.token)
                .header(APIConstants.Header_Key_Content_Type,APIConstants.Header_Value_Content_Type)
                .body(APIPayloadConstant.partialUpdateEmployeePayloadDynamic(employee_id,firstname));

 }
    @When("a Patch is made to partial update an employee")
    public void a_patch_is_made_to_partial_update_an_employee() {
        response = request.when().patch(APIConstants.PARTIALLY_UPDATE_EMPLOYEES_URI);
        response.prettyPrint();
    }

    @Then("a response body contains key {string} and value is {string}")
    public void a_response_body_contains_key_and_value_is(String key, String value) {
       response.then().assertThat().body(key,equalTo(value));
    }

}
