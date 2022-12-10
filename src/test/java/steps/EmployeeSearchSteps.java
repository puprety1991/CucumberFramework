package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.ConfigReader;

public class EmployeeSearchSteps extends CommonMethods {
    @When("clicks on EmployeeList option")
    public void clicks_on_employee_list_option() {
        click(dashboard.empListOption);
    }
    @When("user enter valid employee id")
    public void user_enter_valid_employee_id() {
        sendText(empList.empIdField, ConfigReader.getPropertyValue("empId"));
    }
    @When("user clicks on search button")
    public void user_clicks_on_search_button() {
        click((empList.searchButton));
    }
    @Then("user see employee information is displayed")
    public void user_see_employee_information_is_displayed() {
        System.out.println("Employee information can be seen");
    }
    @When("user enters valid employee name")
    public void user_enters_valid_employee_name() {
        sendText(empList.searchNameField, "dawggy");
    }
}
