package pages.components;

import elements.Button;
import elements.Label;
import elements.base.BaseField;
import org.openqa.selenium.By;

public class ErrorNotification extends BaseField {

    public Button btnClose = new Button(this, By.className("notification__close"));
    public Label lblErrorMsg = new Label(this, By.tagName("p"));

    public ErrorNotification(By locator) {
        super(locator);
    }
}
