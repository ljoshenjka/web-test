package elements.base;

import base.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class ClickableField extends BaseField {
    public ClickableField(BaseField parent, By locator) {
        super(parent, locator);
    }

    public ClickableField(By locator) {
        super(locator);
    }

    public Boolean isEnabled() {
        return getWebElement().getAttribute("disabled") == null;
    }

    public void click() {
        try {
            getWebElement().click();
        } catch (WebDriverException e) {
            WebDriverUtil.scrollTo(getWebElement());
            WebDriverUtil.wait.until(ExpectedConditions.elementToBeClickable(locator));
            getWebElement().click();
        }
    }

    public void click(int times) {
        for (int i = 0; i <= times; i++) {
            click();
            WebDriverUtil.sleep(1000);
        }
    }
}
