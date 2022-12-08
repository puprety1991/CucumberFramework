package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //features we use to provide the path of all the feature files
        features = "src/test/resources/features/",
        glue = "steps",

        //when you set dry run to true, it stops actual execution
        // it will quickly scan all the gherkin steps whether they are implemented or not
        dryRun = false,
        //when we set dry run to false, it starts execution again
        tags = "@sprint3",//if we want to run two then "@sprint3 or @sprint2"

        //to remove irrelevant information from console, you need to set monochrome to true
        monochrome = true,

        //pretty keywords prints the steps in the console to increase readability
        plugin = {"pretty"}

)

public class SmokeRunner {
}