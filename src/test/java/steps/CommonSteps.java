package steps;

import base.BaseStep;
import base.WebDriverUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import elements.Button;
import org.junit.Assert;
import pages.App;

import java.util.Map;

import static testHelpers.TestProperties.*;

public class CommonSteps extends BaseStep {

    @Given("^user opens app link$")
    public void userOpensAppLink() throws Throwable {
        App.openApp();
    }

    @And("^user logs in into app with credentials$")
    public void userLogsInIntoAppWithCredentials(Map<String, String> details) throws Throwable {
        App.btnLogin.click();
        App.loginPopup.txbEmail.setValue(details.get("email"));
        App.loginPopup.txbPassword.setValue(details.get("password"));
        App.loginPopup.btnLogin.click();
        WebDriverUtil.wait.until(driver -> !App.loginPopup.isDisplayed());
    }

    @When("^user navigates to \"([^\"]*)\" section$")
    public void userNavigatesToSection(String sectionName) throws Throwable {
        Button navButton = null;
        switch (sectionName) {
            case NAV_MOVIES_EN:
                navButton = App.btnMovies;
                break;
            case NAV_SCHEDULE_EN:
                navButton = App.btnSchedule;
                break;
            case NAV_GOODIES_EN:
                navButton = App.btnGoodies;
                break;
        }
        if (navButton != null) {
            // TODO: change sleep to wait
            WebDriverUtil.sleep(1000);
            navButton.click();
        } else {
            Assert.fail("No such navigation button");
        }
        WebDriverUtil.waitForPageToLoad();
    }
}
