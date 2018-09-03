package pages.components;

import elements.Button;
import elements.Label;
import elements.base.BaseField;
import org.openqa.selenium.By;

public class Notification extends BaseField {

    public Button btnClose = new Button(this, By.className("notification__close"));
    public Label lblErrorMsg = new Label(this, By.tagName("p"));

    public Notification(By locator) {
        super(locator);
    }
}
