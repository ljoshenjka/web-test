package steps;

import base.BaseStep;
import base.WebDriverUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import elements.Button;
import org.junit.Assert;
import pages.FilmPage;
import pages.components.SeatPicker.SeatRow;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FilmSectionSteps extends BaseStep {

    @And("^set random available date$")
    public void setRandomAvailableDate() throws Throwable {
        FilmPage.btnDatePicker.click();
        List<Button> dateList = FilmPage.getAvailableDates();
        int randomDate = ThreadLocalRandom.current().nextInt(1, dateList.size() + 1);
        dateList.get(randomDate).click();
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
        for (int i = 0; i < seatsNumber; i++) {
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
        }
    }
}
