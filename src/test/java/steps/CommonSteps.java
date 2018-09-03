package steps;

import base.BaseStep;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.App;

import java.util.Map;

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
    }

    @When("^user navigates to \"([^\"]*)\" section$")
    public void userNavigatesToSection(String sectionName) throws Throwable {
        
    }
}
