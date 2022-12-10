package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class AddEmployeePage extends CommonMethods {
    @FindBy(name = "firstName")
    public WebElement firstNameField;

    @FindBy(id = "lastName")
    public WebElement lastNameField;

    @FindBy(id = "btnSave")
    public WebElement saveButton;

    public AddEmployeePage(){
        //page factory is the concept of selenium which we use to implement page
        //object model design patter which is responsible to initialize all the objects
        //of the class here when we say objects we mean locators what we also call object repository
        //if someone ask this to you in the interview you have to write the same thing

        PageFactory.initElements(driver,this);
    }


}
