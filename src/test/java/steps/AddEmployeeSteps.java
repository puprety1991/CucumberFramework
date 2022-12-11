package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AddEmployeePage;
import utils.CommonMethods;

import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        AddEmployeePage addEmployee = new AddEmployeePage();
        click(dashboard.pimOption);
    }

    @When("user clicks on Add Employee button")
    public void user_clicks_on_add_employee_button() {
        click(dashboard.addEmployeeOption);

    }
    @When("user enter firstname and lastname")
    public void user_enter_firstname_and_lastname() {
        /*WebElement firstName = driver.findElement(By.name("firstName"));
        firstName.sendKeys("Salman");
        WebElement lastName = driver.findElement(By.name("lastName"));
        lastName.sendKeys("Khan");*/
        sendText(addEmployee.firstNameField, "Oscar");
        sendText(addEmployee.lastNameField, "Patel");
    }
    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        /*WebElement saveButton = driver.findElement(By.id("btnSave"));
        saveButton.click();*/
        click(addEmployee.saveButton);

    }
    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee Added");
    }
    @When("user enter {string} and {string}")
    public void user_enter_and(String firstName, String lastName) {
        sendText(addEmployee.firstNameField, firstName);
        sendText(addEmployee.lastNameField, lastName);
    }
    @When("user enter {string} and {string} for adding multiple employees")
    public void user_enter_and_for_adding_multiple_employees(String firstNameValue, String lastNameValue) {
        sendText(addEmployee.firstNameField, firstNameValue);
        sendText(addEmployee.lastNameField, lastNameValue);
    }
    @When("user adds multiple employees and verify they are added successfully")
    public void user_adds_multiple_employees_and_verify_they_are_added_successfully(DataTable dataTable) throws InterruptedException {
        List<Map<String,String>> employeeNames = dataTable.asMaps();
        for(Map<String,String> employee:employeeNames){
            String firstNameValue = employee.get("firstName");
            String middleNameValue = employee.get("middleName");
            String lastNameValue = employee.get("lastName");

            sendText(addEmployee.firstNameField, firstNameValue);
            sendText(addEmployee.middleNameField, middleNameValue);
            sendText(addEmployee.lastNameField, lastNameValue);

            click(addEmployee.saveButton);
            Thread.sleep(2000);
            //till this point one employee has been added

            // verifying the employee is homework

            click(dashboard.addEmployeeOption);
            Thread.sleep(2000);


        }


    }
}
