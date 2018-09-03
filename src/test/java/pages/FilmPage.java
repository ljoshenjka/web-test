package pages;

import base.WebDriverUtil;
import elements.Button;
import elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.MoviesSection.MovieItem;
import pages.components.SeatPicker;

import java.util.ArrayList;
import java.util.List;

public class FilmPage {
    public static final Label lblFilmName = new Label(By.tagName("h1"));
    public static final Button btnBuy = new Button(By.className("filmSchedule__buy"));
    public static final SeatPicker seatPicker = new SeatPicker(By.className("seatPlanGrid"));

    // TODO: change this to DatePicker element
    public static final Button btnDatePicker = new Button(By.cssSelector(".flatpickr-input"));

    private String locAvailableDates = "//span[not(@class[contains(.,'disabled')]) and @class[contains(.,'flatpickr-day')]]";

    private List<Button> getAvailableDates() {
        int dayCount = WebDriverUtil.getElements(By.xpath(locAvailableDates)).size();
        List<Button> itemList = new ArrayList<>();
        for (int i = 1; i <= dayCount; i++) {
            itemList.add(new Button(By.xpath(String.format("%s[%d]", locAvailableDates, i))));
        }
        return itemList;
    }

}
