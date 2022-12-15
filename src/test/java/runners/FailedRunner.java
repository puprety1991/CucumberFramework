package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        //features we use to provide the path of all the feature files
        features = "@target/failed.txt",
        glue = "steps",
        //to remove irrelevant information from console, you need to set monochrome to true
        monochrome = true,
        plugin = {"pretty"}

)
// this runner class is responsible to run only failed test cases
public class FailedRunner{

}
