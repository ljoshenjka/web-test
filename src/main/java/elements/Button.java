package elements;

import elements.base.BaseField;
import elements.base.ClickableField;
import org.openqa.selenium.By;

public class Button extends ClickableField {
    public Button(BaseField parent, By locator) {
        super(parent, locator);
    }

    public Button(By locator) {
        super(locator);
    }
}
