package steps;

import base.BaseStep;
import base.WebDriverUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import elements.Button;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.FilmPage;
import pages.components.SeatPicker.SeatRow;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class FilmSectionSteps extends BaseStep {

    @And("^set random available date$")
    public void setRandomAvailableDate() throws Throwable {
        WebDriverUtil.wait.until(driver -> FilmPage.btnTickets.isDisplayed());
        FilmPage.btnDatePicker.click();
        List<Button> dateList = FilmPage.getAvailableDates();
        int randomDate = ThreadLocalRandom.current().nextInt(1, dateList.size());
        dateList.get(randomDate).click();
        WebDriverUtil.sleep(500);
    }

    @And("^user start buying tickets$")
    public void userStartBuyingTickets() throws Throwable {
        FilmPage.btnTickets.click();
    }

    @Then("^seat plan page is shown$")
    public void seatPlanPageIsShown() throws Throwable {
        Assert.assertTrue("Missing seat plan", FilmPage.seatPicker.isDisplayedWithWait());
    }

    @When("^user select (\\d+) random seats from rows$")
    public void userSelectRandomSeatsFromRows(int seatsNumber, List<Integer> rows) throws Throwable {
        int rowCount = FilmPage.seatPicker.getSeatRowCount();
        for (Integer i = 1; i <= seatsNumber; i++) {
            SeatRow seatRow = null;
            for (int row : rows) {
                if (row <= rowCount) {
                    seatRow = FilmPage.seatPicker.getSeatRowByNumber(row);
                    break;
                }
            }
            if (seatRow == null) {
                WebDriverUtil.getLogger().error("Rows are not available");
                int randomRow = ThreadLocalRandom.current().nextInt(1, rowCount + 1);
                seatRow = FilmPage.seatPicker.getSeatRowByNumber(randomRow);
            }
            int randomSeat = ThreadLocalRandom.current().nextInt(1, seatRow.getAvailableSeatsCount() + 1);
            seatRow.getSeatByNumber(randomSeat).click();
            WebDriverUtil.waitForPageToLoad();
            WebDriverUtil.wait.until(ExpectedConditions.attributeToBe(FilmPage.lblPeopleGoing.getWebElement(), "textContent", i.toString()));
        }
    }

    @Then("^(.*) people are going$")
    public void peopleAreGoing(String peopleNumber) throws Throwable {
        Assert.assertEquals("Wrong number of people going", peopleNumber, FilmPage.lblPeopleGoing.getValue());
        saveData("ticket_number", FilmPage.lblPeopleGoing.getValue());
    }

    @When("^user enters voucher \"([^\"]*)\"$")
    public void userEntersVoucher(String voucherText) throws Throwable {
        if (voucherText.equals("$RANDOM")) {
            voucherText = UUID.randomUUID().toString().replaceAll("-", "");
        }
        FilmPage.voucher.txbCode.setValue(voucherText);
        FilmPage.voucher.btnApply.click();
    }

    @Then("^voucher error message is shown$")
    public void voucherErrorMessageIsShown() throws Throwable {
        Assert.assertTrue("Error notification not shown", FilmPage.errorNotification.isDisplayedWithWait());
    }

    @When("^user submit seat selection$")
    public void userSubmitSeatSelection() throws Throwable {
        FilmPage.btnPay.click();
    }

    @And("^full ticket price is shown$")
    public void fullTicketPriceIsShown() throws Throwable {
        // TODO: temporary sleep because of full price value update; change to wait
        WebDriverUtil.sleep(500);
        Assert.assertTrue("Full ticket price not shown", FilmPage.lblFullPrice.isDisplayedWithWait());
        saveData("ticket_price", FilmPage.lblFullPrice.getValue());
    }

    @When("^user deselect all seats$")
    public void userDeselectAllSeats() throws Throwable {
        FilmPage.seatPicker.deselectAllSeats();
    }
}
