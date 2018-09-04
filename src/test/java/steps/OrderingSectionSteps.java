package steps;

import base.BaseStep;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import elements.Button;
import org.junit.Assert;
import pages.OrderPage;

import java.util.List;

import static testHelpers.TestProperties.*;

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

    @And("^correct payment methods are shown$")
    public void correctPaymentMethodsAreShown(List<String> paymentMethods) throws Throwable {
        Button paymentButton = null;
        for (String method : paymentMethods) {
            switch (method) {
                case PAYMENT_SWED:
                    paymentButton = OrderPage.paymentForm.btnPaymentSwed;
                    break;
                case PAYMENT_LUMINOR:
                    paymentButton = OrderPage.paymentForm.btnPaymentLuminor;
                    break;
                case PAYMENT_SEB:
                    paymentButton = OrderPage.paymentForm.btnPaymentSeb;
                    break;
                case PAYMENT_CITADELE:
                    paymentButton = OrderPage.paymentForm.btnPaymentCitadele;
                    break;
                case PAYMENT_CREDIT_CARD:
                    paymentButton = OrderPage.paymentForm.btnPaymentCreditCard;
                    break;
            }
            if (paymentButton == null) {
                Assert.fail("No such payment method");
            } else {
                Assert.assertTrue("Missing payment method: " + method, paymentButton.isDisplayedWithWait());
            }
        }
    }
}
