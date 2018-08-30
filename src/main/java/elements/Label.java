package elements;

import elements.base.BaseField;
import elements.base.StaticField;
import org.openqa.selenium.By;

public class Label extends StaticField {
    public Label(BaseField parent, By locator) {
        super(parent, locator);
    }

    public Label(By locator) {
        super(locator);
    }
}
