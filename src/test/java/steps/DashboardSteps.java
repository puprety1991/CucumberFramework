package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DashboardSteps extends CommonMethods {
    @Then("user verify dashboard page")
    public void user_verify_dashboard_page() {
        System.out.println("Hello Syntax");
    }
    @Then("user verify all the dashboard tabs")
    public void user_verify_all_the_dashboard_tabs(DataTable dataTable) {
        List<String>expectedTabs = dataTable.asList();
        List<String>actualTabs = new ArrayList<>();
        for(WebElement element :dashboard.dashboardTabs){
            actualTabs.add(element.getText());
        }
        //printing the text for both feature file and from application
        /*System.out.println(expectedTabs);
        System.out.println(actualTabs);*/

        //.equals is the method we use to compare two lists together
        //assertTrue is a boolean condition which returns true if condition is satisfy
        Assert.assertTrue(expectedTabs.equals(actualTabs));

    }

}
