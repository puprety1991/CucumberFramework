package APISteps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIConstants;
import utils.APIPayloadConstant;

import static io.restassured.RestAssured.given;


public class GenerateTokenSteps {
  //to use my token in the project i am using static variable
    public static String token;
    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        RequestSpecification request = given().header(APIConstants.Header_Key_Content_Type,APIConstants.Header_Value_Content_Type)
                .body(APIPayloadConstant.adminPayload());
        Response response = request.when().post(APIConstants.GENERATE_TOKEN_URI);
        //printing the response body
        //response.prettyPrint();
       token = "Bearer "+response.jsonPath().getString("token");
      // System.out.println(token);
    }
}
