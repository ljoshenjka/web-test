package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Cucumber test runner
 */
@RunWith(Cucumber.class) //JUnit
@CucumberOptions(
        features = {"src/test/resources/Features"},
        glue = {"steps", "hooks"},
        tags = {"@e2e"},
        plugin = {"rerun:target/rerun.txt"},
        format = {"json:target/reports/cucumber.json"}
)
public class TestRunner {
}
