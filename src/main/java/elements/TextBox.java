package elements;

import elements.base.BaseField;
import elements.base.EditableField;
import org.openqa.selenium.By;

public class TextBox extends EditableField {
    public TextBox(BaseField parent, By locator) {
        super(parent, locator);
    }

    public TextBox(By locator) {
        super(locator);
    }
}
