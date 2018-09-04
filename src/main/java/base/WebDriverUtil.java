package base;

import constants.PropertyConfigs;
import helpers.ConfigReader;
import helpers.DateHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverUtil {
    private static final ThreadLocal<WebDriver> drivers = ThreadLocal.withInitial(() -> null);

    private static final int implicitWait = Integer.parseInt(ConfigReader.getInstance().getValue(PropertyConfigs.SELENIUM_IMPLICITLY_WAIT));

    public static Wait<WebDriver> wait = null;
    public static Actions performAction = null;

    private static final int timeout = Integer.parseInt(ConfigReader.getInstance().getValue(PropertyConfigs.APP_WAIT));
    private static final Logger logger = LoggerFactory.getLogger(BaseStep.class);


    public static WebDriver getDriver() {
        return drivers.get();
    }

    public static void setDriver(WebDriver driver) {
        drivers.set(driver);
        if (driver != null) {
            wait = new FluentWait<WebDriver>(getDriver())
                    .withTimeout(Duration.ofSeconds(timeout))
                    .pollingEvery(Duration.ofMillis(500)).ignoring(StaleElementReferenceException.class);
            performAction = new Actions(WebDriverUtil.getDriver());
        }
    }

    public static JavascriptExecutor getJsExecutor() {
        return (JavascriptExecutor) getDriver();
    }

    public static WebElement getElement(By locator) {
        try {
            return getDriver().findElement(locator);
        } catch (WebDriverException e) {
            waitForPageToLoad();
            return getDriver().findElement(locator);
        }
    }

    public static WebElement getElement(WebElement parent, By locator) {
        try {
            return parent.findElement(locator);
        } catch (WebDriverException e) {
            waitForPageToLoad();
            return parent.findElement(locator);
        }
    }

    public static List<WebElement> getElementsOrEmpty(By locator) {
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> temp = getDriver().findElements(locator);
        getDriver().manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        return temp;
    }

    public static List<WebElement> getElementsOrEmpty(WebElement parent, By locator) {
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> temp = parent.findElements(locator);
        getDriver().manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        return temp;
    }

    public static List<WebElement> getElements(By locator) {
        waitForPageToLoad();
        try {
            return getDriver().findElements(locator);
        } catch (WebDriverException e) {
            waitForPageToLoad();
            return getDriver().findElements(locator);
        }
    }

    public static List<WebElement> getElements(WebElement parent, By locator) {
        waitForPageToLoad();
        try {
            return parent.findElements(locator);
        } catch (WebDriverException e) {
            waitForPageToLoad();
            return parent.findElements(locator);
        }
    }

    public static List<WebElement> getElements(By parentLocator, By childLocator) {
        waitForPageToLoad();
        WebElement parent = getElement(parentLocator);
        return getElements(parent, childLocator);
    }

    public static void waitForPageToLoad() {
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return ((Long) (getJsExecutor()).executeScript("return jQuery.active") == 0);
            } catch (Exception e) {
                return true;
            }
        };

        ExpectedCondition<Boolean> jsLoad = driver -> (getJsExecutor()).executeScript("return document.readyState").toString().equals("complete");

        wait.until(jsLoad);
        wait.until(jQueryLoad);
    }

    public static void refreshScreen() {
        getDriver().navigate().refresh();
        waitForPageToLoad();
    }

    public static void scrollTo(WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        getJsExecutor().executeScript(scrollElementIntoMiddle, element);
    }

    public static void sleep(Integer miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (Exception e) {

        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static String writeScreenshotToFile(byte[] screen, String nameTemplate, String path) {
        try {
            if (path == null) {
                path = "./target/screenshots/" + nameTemplate + "_" + DateHelper.getTodaysDateTime() + ".png";
            }
            FileUtils.writeByteArrayToFile(new File(path), screen);

        } catch (IOException e) {
            path = "Failed to capture screenshot: " + e.getMessage();
        }

        return path;
    }

    public static byte[] getScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
