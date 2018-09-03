package pages.components;

import elements.Button;
import elements.base.BaseField;
import org.openqa.selenium.By;

public class PaymentForm extends BaseField {

    public Button btnPaymentSwed = new Button(this, By.xpath(".//input[@value='cinamon_swedbl']"));
    public Button btnPaymentLuminor = new Button(this, By.xpath(".//input[@value='mk_lv_nordea']"));
    public Button btnPaymentSeb = new Button(this, By.xpath(".//input[@value='mk_lv_seb']"));
    public Button btnPaymentCitadele = new Button(this, By.xpath(".//input[@value='mk_lv_citadele']"));
    public Button btnPaymentCreditCard = new Button(this, By.xpath(".//div[@class='control--image--creditcard']"));

    public PaymentForm(BaseField parent, By locator) {
        super(parent, locator);
    }

    public PaymentForm(By locator) {
        super(locator);
    }
}
