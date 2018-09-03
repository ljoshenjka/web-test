package pages.components;

import elements.Button;
import elements.TextBox;
import elements.base.BaseField;
import org.openqa.selenium.By;

public class Voucher extends BaseField {

    public TextBox txbCode = new TextBox(this, By.tagName("input"));
    public Button btnApply = new Button(this, By.tagName("button"));

    public Voucher(BaseField parent, By locator) {
        super(parent, locator);
    }

    public Voucher(By locator) {
        super(locator);
    }
}
