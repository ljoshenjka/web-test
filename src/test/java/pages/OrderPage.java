package pages;

import elements.Button;
import elements.Label;
import org.openqa.selenium.By;
import pages.components.PaymentForm;

import static testHelpers.TestProperties.FILM_MY_ORDER_EN;

public class OrderPage {
    public static final Button btnChangeOrder = new Button(By.className("pageBasket__basket__items__item__controls--change"));
    public static final Label lblTotal = new Label(By.xpath("//div[@class='pageBasket__basket__totals']/h3"));
    public static final PaymentForm paymentForm = new PaymentForm(By.id("payment-form"));
    public static final Label lblTicketAmount = new Label(By.className("pageBasket__basket__items__item__order-data--ticket-type"));
    public static final Label lblSectionHeader = new Label(By.xpath("//section[@class='pageBasket__basket']/h3"));

    public static Boolean isOpen() {
        return lblSectionHeader.getValue().equals(FILM_MY_ORDER_EN);
    }
}
