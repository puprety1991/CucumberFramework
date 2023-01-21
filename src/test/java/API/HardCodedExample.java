package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

//import static org.hamcrest.CoreMatchers.equalTo;
import  static org.hamcrest.Matcher.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

//to change the order of execution we use fix method order since it is executing in
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExample {

    //one thing to remember
    // base URI -  base URL
    //end then using when keyword, we will send the end point
    String baseUrI= RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    //we need to perform CRUD operations
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NzQxMzk3ODgsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY3NDE4Mjk4OCwidXNlcklkIjoiNTAzNCJ9.9rAJRe0XCfdpr4zxhU4i0vihbwGb6qi7R19VQRa8rkc";
    static String employee_id;
    @Test
    public void bgetOneEmployee(){
       //prepare the request
       //to prepare the request, we use request specification
       RequestSpecification request = given().header("Authorization",token)
               .header("Content-Type","Application/json")
               .queryParam("employee_id",employee_id);
       //to hit the end point/ to make the request which will return response
       Response response = request.when().get("/getOneEmployee.php");
      // System.out.println(response.asString());
       response.prettyPrint();
       //verifying the status code
       response.then().assertThat().statusCode(200);
        // using jsonPath method, we are extracting the value from the response body
        String firstname = response.jsonPath().getString("employee.emp_firstname");
        System.out.println(firstname);
        //first way of assertion
        Assert.assertEquals(firstname,"Nelson");
        //second way of assertion to verify the value in response body using hamcrast method
        response.then().assertThat().body("employee.emp_firstname", equalTo("Nelson"));

    }
    @Test
    public void acreateEmployee(){
        RequestSpecification requestSpecification = given().header("Authorization", token).header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"emp_firstname\": \"Nelson\",\n" +
                        "  \"emp_lastname\": \"Sharma\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2011-01-14\",\n" +
                        "  \"emp_status\": \"confirmed\",\n" +
                        "  \"emp_job_title\": \"QA Engineer\"\n" +
                        "}");
        Response response = requestSpecification.when().post("/createEmployee.php");
        response.prettyPrint();
        //verifying the status code is 201
        response.then().assertThat().statusCode(201);
        //getting the employee id from the response and use it as static one
        employee_id = response.jsonPath().getString("Employee.employee_id");

        System.out.println(employee_id);
        //response.then().assertThat().body("Employee_emp_lastname")
        response.then().assertThat().body("Employee.emp_lastname", equalTo("Sharma"));
        response.then().assertThat().body("Employee.emp_middle_name", equalTo("ms"));
        //verify console header
        response.then().assertThat().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");

    }

    @Test
    public void cupdateEmployee(){
        RequestSpecification request = given().header("Authorization", token)
                .header("Content-Type", "application/json").
                body("{\n" +
                        "  \"employee_id\": \""+employee_id+"\",\n" +
                        "  \"emp_firstname\": \"Lionel\",\n" +
                        "  \"emp_lastname\": \"Mandela\",\n" +
                        "  \"emp_middle_name\": \"nath\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"1987-01-14\",\n" +
                        "  \"emp_status\": \"probation\",\n" +
                        "  \"emp_job_title\": \"Lead QA Engineer\"\n" +
                        "}");

        Response response = request.when().put("/updateEmployee.php");
        response.prettyPrint();
        //verification
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("Message", equalTo("Employee record Updated"));
    }

    @Test
    public void dgetUpdatedEmployee(){
        RequestSpecification request = given().header("Authorization", token)
                .header("Content-Type", "application/json")
                .queryParam("employee_id", employee_id);

        //to hit the end point/ to make the request which will return response
        Response response = request.when().get("/getOneEmployee.php");

        // System.out.println(response.asString());
        response.prettyPrint();
        //verifying the status code
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("employee.emp_job_title", equalTo("Lead QA Engineer"));
    }

    }


