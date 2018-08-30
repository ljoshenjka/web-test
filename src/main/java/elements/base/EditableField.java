package elements.base;

import base.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public abstract class EditableField extends BaseField {
    public EditableField(BaseField parent, By locator) {
        super(parent, locator);
    }

    public EditableField(By locator) {
        super(locator);
    }

    public void setValue(String value) {
        WebElement element = getWebElement();
        element.clear();
        element.sendKeys(value);
        WebDriverUtil.waitForPageToLoad();
    }

    public Boolean isEnabled() {
        return getWebElement().getAttribute("disabled") == null;
    }

    public void submit() {
        getWebElement().sendKeys(Keys.ENTER);
    }

    public void clearWithKeys() {
        while (getValue().length() > 0) {
            getWebElement().sendKeys(Keys.BACK_SPACE);
        }
    }
}
