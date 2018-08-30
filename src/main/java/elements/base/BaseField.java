package elements.base;

import base.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class BaseField {
    protected final By locator;
    private final BaseField parent;

    public BaseField(By locator) {
        this(null, locator);
    }

    public BaseField(BaseField parent, By locator) {
        this.parent = parent;
        this.locator = locator;
    }

    public BaseField getParent() {
        return parent;
    }

    public WebElement getWebElement() {
        if (getParent() != null) {
            return WebDriverUtil.getElement(getParent().getWebElement(), locator);
        } else {
            return WebDriverUtil.getElement(locator);
        }
    }

    public List<WebElement> getWebElements() {
        if (getParent() != null) {
            return WebDriverUtil.getElements(getParent().getWebElement(), locator);
        } else {
            return WebDriverUtil.getElements(locator);
        }
    }

    public Boolean isDisplayedWithWait() {
        if (getParent() != null) {
            return WebDriverUtil.getElements(getParent().getWebElement(), locator).size() > 0;
        } else {
            return WebDriverUtil.getElements(locator).size() > 0;
        }
    }

    public Boolean isDisplayed() {
        if (getParent() != null) {
            return WebDriverUtil.getElementsOrEmpty(getParent().getWebElement(), locator).size() > 0;
        } else {
            return WebDriverUtil.getElementsOrEmpty(locator).size() > 0;
        }
    }

    public void highlight() {
        WebDriverUtil.getJsExecutor().executeScript("arguments[0].style.border='3px solid red'", getWebElement());
    }

    public String getValue() {
        return getWebElement().getText();
    }

    public String getAttribute(String attribute) {
        return getWebElement().getAttribute(attribute);
    }
}
