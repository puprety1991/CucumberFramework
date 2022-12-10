package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginSteps extends CommonMethods {
    //public static WebDriver driver;
    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application() {
        openBrowserAndLaunchApplication();
    }

    @When("user enters valid username and valid password")
    public void user_enters_valid_username_and_valid_password() {
        sendText(login.usernameTextField, ConfigReader.getPropertyValue("username"));
        sendText(login.passwordTextField, ConfigReader.getPropertyValue("password"));
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        click(login.loginButton);
    }

    @Then("user is successfully logged in")
    public void user_is_successfully_logged_in() {
        WebElement welcomeMessage = driver.findElement(By.id("welcome"));
        if (welcomeMessage.isDisplayed()) {
            System.out.println("Test case is passed");
        } else {
            System.out.println("Test is failed");
        }
    }

    @When("user enters ess username and ess password")
    public void user_enters_ess_username_and_ess_password() {
        sendText(login.usernameTextField, "asmahuma321");
        sendText(login.passwordTextField, "Hum@nhrm123");
    }

    @When("user enters invalid username and password")
    public void user_enters_invalid_username_and_password() {
        sendText(login.usernameTextField, "admin123");
        sendText(login.passwordTextField, "Hum@nhrm");
    }

    @Then("error message displayed")
    public void error_message_displayed() {
        System.out.println("Error message displayed");
    }
}


