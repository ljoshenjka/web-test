package base;

import constants.PropertyConfigs;
import helpers.ConfigReader;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class WebDriverSetup {

    public static RemoteWebDriver setupWebDriver(String driverType) {
        RemoteWebDriver driver;
        switch (driverType) {
            case PropertyConfigs.FIREFOX:
                setupDriver("webdriver.gecko.driver", "geckodriver");
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
                driver = new FirefoxDriver(getFirefoxOptions());
                break;
            case PropertyConfigs.CHROME:
                setupDriver("webdriver.chrome.driver", "chromedriver");
                driver = new ChromeDriver(getChromeOptions());
                break;
            default:
                setupDriver("webdriver.chrome.driver", "chromedriver");
                driver = new ChromeDriver(getChromeOptions());
        }
        return driver;
    }

    private static void setupDriver(String key, String driverExeName) {
        String operatingSystem = ConfigReader.getInstance().getValue("system.name");
        String driverPath = System.getProperty("user.dir") + File.separator + "drivers" + File.separator + operatingSystem + File.separator + driverExeName;
        if (operatingSystem.equals("win")) driverPath += ".exe";
        System.setProperty(key, driverPath);
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--test-type");
        return options;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
        return firefoxOptions;
    }
}
