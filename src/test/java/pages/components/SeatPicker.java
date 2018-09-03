package pages.components;

import base.WebDriverUtil;
import elements.Button;
import elements.base.BaseField;
import org.openqa.selenium.By;

public class SeatPicker extends BaseField {


    private String locSeatRow = ".//div[@class='seatPlanGrid__row']";

    public SeatPicker(BaseField parent, By locator) {
        super(parent, locator);
    }

    public SeatPicker(By locator) {
        super(locator);
    }

    public Integer getSeatRowCount() {
        return WebDriverUtil.getElements(getWebElement(), By.xpath(locSeatRow)).size();
    }

    public SeatRow getSeatRowByNumber(Integer rowNumber) {
        int rowCount = getSeatRowCount();
        if (rowCount > 0 && rowCount >= rowNumber) {
            // because in cinema 1st row is closer to screen
            int revertRowNumber = rowCount - rowNumber + 1;
            return new SeatRow(this, By.xpath(String.format("%s[%d]", locSeatRow, revertRowNumber)));
        } else {
            WebDriverUtil.getLogger().error("No such seat row in cinema");
            return null;
        }
    }

    public class SeatRow extends BaseField {

        private String locAvailableSeat = ".//div[@class[contains(.,'seatPlanGrid__cell--empty ')]]";

        public SeatRow(BaseField parent, By locator) {
            super(parent, locator);
        }

        public Integer getAvailableSeatsCount() {
            return WebDriverUtil.getElements(getWebElement(), By.xpath(locAvailableSeat)).size();
        }

        public Button getSeatByNumber(Integer seatNumber) {
            int availableSeatCount = getAvailableSeatsCount();
            if (availableSeatCount > 0 && availableSeatCount >= seatNumber) {
                return new Button(this, By.xpath(String.format("%s[%d]", locAvailableSeat, seatNumber)));
            } else {
                WebDriverUtil.getLogger().error("No such seat in current seat row");
                return null;
            }
        }

    }
}
