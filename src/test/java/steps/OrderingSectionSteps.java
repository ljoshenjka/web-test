package steps;

import base.BaseStep;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.OrderPage;

public class OrderingSectionSteps extends BaseStep {

    @Then("^ordering page is shown$")
    public void paymentPageIsShown() throws Throwable {
        Assert.assertTrue(OrderPage.isOpen());
    }

    @And("^correct ticket price is shown$")
    public void correctTicketPriceIsShown() throws Throwable {
        String fullPrice = getData("ticket_price").toString();
        Assert.assertEquals("Wrong total ticket price shown", fullPrice, OrderPage.lblTotal.getValue());
    }

    @And("^correct number of tickets is shown$")
    public void correctNumberOfTicketsIsShown() throws Throwable {
        String ticketNumber = getData("ticket_number").toString();
        Assert.assertEquals("Wrong ticket number shown", ticketNumber, OrderPage.lblTicketAmount.getValue().split(" ")[0]);
    }

    @When("^user selects to change order$")
    public void userSelectsToChangeOrder() throws Throwable {
        OrderPage.btnChangeOrder.click();
    }
}
