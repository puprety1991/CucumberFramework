package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AddEmployeePage;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReader;
import utils.databaseReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {
    String id, fName,lName;

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
        fName=firstName;
        lName=lastName;
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
    @When("user add multiple employees from excel using {string} and verify it")
    public void user_add_multiple_employees_from_excel_using_and_verify_it(String sheetName) throws InterruptedException {
        List<Map<String,String>>empFromExcel = ExcelReader.excelListIntoMap(Constants.TESTDATA_FILEPATH,sheetName);

        //it returns one map from list of maps
        Iterator<Map<String,String>>iterator = empFromExcel.iterator();
        while (iterator.hasNext()){
            //it returns the key and value for employee from excel
            Map<String, String>mapNewEmp = iterator.next();
            sendText(addEmployee.firstNameField,mapNewEmp.get("firstName"));
            sendText(addEmployee.middleNameField,mapNewEmp.get("middleName"));
            sendText(addEmployee.lastNameField,mapNewEmp.get("lastName"));
            String empIdValue = addEmployee.empIdLocator.getAttribute("value");
            sendText(addEmployee.photograph, mapNewEmp.get("photograph"));
            if(!addEmployee.checkBox.isSelected()){
                addEmployee.checkBox.click();
            }
            sendText(addEmployee.createUserNameField,mapNewEmp.get("username"));
            sendText(addEmployee.createPasswordField, mapNewEmp.get("password"));
            sendText(addEmployee.confirmPasswordField, mapNewEmp.get("confirmPassword"));
            click(addEmployee.saveButton);

            //verification is in homework
            Thread.sleep(3000);
            click(dashboard.empListOption);
            Thread.sleep(2000);
            sendText(empList.empIdTextField, empIdValue);
            click(empList.searchButton);

            //verifying the employee added from the Excel file
            List<WebElement> rowData =
                    driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr"));
          /*  for(WebElement data:rowData){
                String rowText = data.getText();
                System.out.println(rowText);
            }*/
            for (int i =0; i<rowData.size(); i++){
                System.out.println("I am inside the loop and worried about josh");
                //getting the text of every element from here and storing it into string
                String rowText = rowData.get(i).getText();
                System.out.println(rowText);

                String expectedData = empIdValue + " " + mapNewEmp.get("firstName")
                        + " " + mapNewEmp.get("middleName") + " " + mapNewEmp.get("lastName");
            }

            click(dashboard.addEmployeeOption);
            Thread.sleep(2000);

        }

    }
    @When("user captures employee id")
    public void user_captures_employee_id() {
        id = addEmployee.employeeIdAdd.getAttribute("value");

    }

    @Then("added employee is displayed in database")
    public void added_employee_is_displayed_in_database() {
        String query = DatabaseSteps.getFnameLnameQuery() + id;
        System.out.println(query);
        List<Map<String, String>> dataFromDatabase = databaseReader.getListOfMapsFromResultSet(query);
        System.out.println(dataFromDatabase);
        String firstNameFromDb = dataFromDatabase.get(0).get("emp_firstname");
        String lastNameFromDb = dataFromDatabase.get(0).get("emp_lastname");
        Assert.assertEquals(fName,firstNameFromDb);
        Assert.assertEquals(lName,lastNameFromDb);

    }
}
