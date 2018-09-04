package hooks;

import base.WebDriverSetup;
import base.WebDriverUtil;
import com.vimalselvam.cucumber.listener.Reporter;
import constants.PropertyConfigs;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import helpers.ConfigReader;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;
import java.net.MalformedURLException;

public class SeleniumHooks {

    private final String driverType = ConfigReader.getInstance().getValue(PropertyConfigs.SELENIUM_DRIVER);

    @Before
    public void initialize(Scenario scenario) throws MalformedURLException {
        if (WebDriverUtil.getDriver() != null) {
            try {
                WebDriverUtil.getDriver().getPageSource();
            } catch (Exception wde) {
                WebDriverUtil.setDriver(null);
            }
        }

        if (WebDriverUtil.getDriver() == null) {
            WebDriverUtil.getLogger().info("Open app: " + driverType);
            WebDriverUtil.setDriver(WebDriverSetup.setupWebDriver(driverType));
        }

        WebDriverUtil.getLogger().info("Scenario '" + scenario.getName() + "' STARTED");
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.getStatus().equals("failed") || scenario.getStatus().equals("undefined")) {
            byte[] screen = WebDriverUtil.getScreenshot();
            scenario.embed(screen, "image/png");
            String screenshotName = WebDriverUtil.writeScreenshotToFile(screen, scenario.getName().replaceAll(" ", "_"), null);
            WebDriverUtil.getLogger().error("Scenario '" + scenario.getName() + "' FAILED, Screen shot: " + screenshotName);
            try {
                Reporter.addScreenCaptureFromPath(screenshotName);
            } catch (IOException e) {
                WebDriverUtil.getLogger().error("Unable to capture screenshot");
            }
        } else {
            WebDriverUtil.getLogger().info("Scenario '" + scenario.getName() + "' PASSED");
        }
        WebDriverUtil.getDriver().manage().deleteAllCookies();
        try {
            WebDriverUtil.getDriver().quit();
        } catch (WebDriverException we) {
            WebDriverUtil.getLogger().info("Ignoring WebDriverException");
        }
    }
}
