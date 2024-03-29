package utils;

import io.cucumber.java.it.Data;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.DOMConfiguration;
import steps.PageInitializer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonMethods extends PageInitializer {
    public static WebDriver driver;
    public static void openBrowserAndLaunchApplication(){
        ConfigReader.readProperties(Constants.CONFIGURATION_FILEPATH);
        switch (ConfigReader.getPropertyValue("browser")){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Invalid browser name");

        }
        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        initializePageObjects();
        //to configure the file and pattern of it, we need to call the file
        DOMConfigurator.configure("log4j.xml");
        Log.startTestCase("My first test case is Login test");
        Log.info("my login test is going on");
        Log.warning("My test is might be failed");
    }
    public static void closeBrowser(){
        Log.info("My test case about to complete");
        Log.endTestCase("This is my login test again");
        driver.quit();
    }
    // we use this method instead of send keys method throughout the framework
    public static void sendText(WebElement element,String textToSend){
        element.clear();
        element.sendKeys(textToSend);
    }
    // to get webDriver wait (explicit wait)
    public static WebDriverWait getWait(){
        WebDriverWait wait = new WebDriverWait(driver,Constants.EXPLICIT_WAIT);
        return wait;
    }
    public static void waitForClickAbility(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }
    public static void click(WebElement element){
        waitForClickAbility(element);
        element.click();
    }
    //this method will return JavaScriptExecutor Object
    public static JavascriptExecutor getJSExecutor(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }
    // this function will perform click on element using javascript executor
    public static void jsClick(WebElement element){
        getJSExecutor().executeScript("arguments[0].click();",element);
    }
    //selecting the dropdown using text
    public static void selectDropDown(WebElement element, String text){
        Select select = new Select(element);
        select.selectByVisibleText(text);

    }
    public static byte[] takeScreenShot(String fileName){
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);
        File sourceFile =ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile,new File(Constants.SCREENSHOT_FILEPATH+ fileName+" "+
                    getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picBytes;
    }
    public static String getTimeStamp(String pattern){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    public static void getAttribute(WebElement element,String key){
        element.getAttribute(key);

    }
    public static String insideSingleQuotation(String stringToBeInserted){
        int index =1;
        String singleQuotation = "' '";
        String newString = new String();
        for(int i=0; i<singleQuotation.length(); i++){
            newString+=singleQuotation.charAt(i);
            if(i == index){
                newString+=stringToBeInserted;
            }
        }
        return newString;
    }


}
