package steps;

import base.BaseStep;
import base.WebDriverUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.App;

import java.util.Map;

import static testHelpers.TestProperties.*;

public class UserMenuSteps extends BaseStep {

    @And("^submit personal name$")
    public void setPersonalName(Map<String, String> personalName) throws Throwable {
        App.personalDataPopup.txbName.setValue(personalName.get("name"));
        App.personalDataPopup.txbSurname.setValue(personalName.get("surname"));
        WebDriverUtil.sleep(1000);
        App.personalDataPopup.btnUpdateProfile.click();
        WebDriverUtil.wait.until(driver -> !App.personalDataPopup.isDisplayed());
    }

    @When("^user opens \"([^\"]*)\" from user menu$")
    public void userOpens(String menuName) throws Throwable {
        switch (menuName) {
            case USER_MENU_DATA:
                App.userMenu.openMyPersonalData();
                break;
            case USER_MENU_PROFILE:
                App.userMenu.openMyProfile();
                break;
            case USER_MENU_LOGOUT:
                App.userMenu.logout();
                break;
        }
    }

    @Then("^personal data is updated$")
    public void personalDataIsUpdated() throws Throwable {
        Assert.assertTrue("Personal data was not changed", App.sucessNotification.isDisplayedWithWait());
        App.sucessNotification.btnClose.click();
    }
}
