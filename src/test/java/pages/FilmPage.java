package pages;

import base.WebDriverUtil;
import elements.Button;
import elements.Label;
import org.openqa.selenium.By;
import pages.components.Notification;
import pages.components.SeatPicker;
import pages.components.Voucher;

import java.util.ArrayList;
import java.util.List;

public class FilmPage {
    public static final Label lblFilmName = new Label(By.tagName("h1"));
    public static final Button btnTickets = new Button(By.className("filmSchedule__buy"));
    public static final SeatPicker seatPicker = new SeatPicker(By.className("seatPlanGrid"));
    public static final Label lblPeopleGoing = new Label(By.xpath("//div[@class='pageSeatPlan__peopleGoing']/span"));
    public static final Voucher voucher = new Voucher(By.xpath("//div[@class='pageSeatPlan__discounts__giftCard__body']"));
    public static final Button btnPay = new Button(By.xpath("//div[@class='pageSeatPlan__addToBasket']//button"));
    public static final Label lblFullPrice = new Label(By.cssSelector(".pageSeatPlan__order__totalPrice"));
    // TODO: change this to DatePicker element
    public static final Button btnDatePicker = new Button(By.cssSelector(".flatpickr-input"));
    public static final Notification errorNotification = new Notification(By.cssSelector(".notification--error"));
    public static final Label lblTotalPrice = new Label(By.xpath("//div[@class[contains(.,'pageSeatPlan__order__totalPrice')]]//strong"));

    private static final String locAvailableDates = "//span[not(@class[contains(.,'disabled')]) and @class[contains(.,'flatpickr-day')]]";

    public static List<Button> getAvailableDates() {
        int dayCount = WebDriverUtil.getElements(By.xpath(locAvailableDates)).size();
        List<Button> itemList = new ArrayList<>();
        for (int i = 1; i <= dayCount; i++) {
            itemList.add(new Button(By.xpath(String.format("%s[%d]", locAvailableDates, i))));
        }
        return itemList;
    }
}
