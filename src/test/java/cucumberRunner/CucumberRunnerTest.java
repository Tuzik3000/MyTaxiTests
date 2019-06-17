package cucumberRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Basic cucumber Runner, goes with Test Name for maven test command
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "pretty",
        features = "src/test/resources/features",
        glue = {"com/mytaxi/stepsDefinitions"}
)
public class CucumberRunnerTest {
}
