package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/failed.txt",
        glue = "steps",
        monochrome = true,
        plugin = {"pretty"}

)
// this runner class is responsible to run only failed test cases
public class FailedRunner{

}
