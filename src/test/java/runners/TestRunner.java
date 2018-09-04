package runners;

import com.vimalselvam.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;

/**
 * Cucumber test runner
 */
@RunWith(Cucumber.class) //JUnit
@CucumberOptions(
        features = {"src/test/resources/Features"},
        glue = {"steps", "hooks"},
        tags = {"@e2e"},
        plugin = {"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:output/report.html"},
        format = {"json:target/reports/cucumber.json"}
)
public class TestRunner {
    @AfterClass
    public static void teardown() {
        Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setTestRunnerOutput("sample test");
    }
}
