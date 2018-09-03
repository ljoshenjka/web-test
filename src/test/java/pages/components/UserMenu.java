package pages.components;

import base.WebDriverUtil;
import elements.Button;
import elements.base.BaseField;
import elements.base.ClickableField;
import org.openqa.selenium.By;

public class UserMenu extends ClickableField {

    private Button btnProfile = new Button(this, By.xpath(".//li[./span[@class='ico ico__profile']]"));
    private Button btnPersonalData = new Button(this, By.xpath(".//li[./span[@class='ico ico__profile-data']]"));
    private Button btnLogout = new Button(this, By.xpath(".//li[./span[@class='ico ico__profile-logout']]"));

    public UserMenu(BaseField parent, By locator) {
        super(parent, locator);
    }

    public UserMenu(By locator) {
        super(locator);
    }

    public void openMyProfile() {
        click();
        btnProfile.click();
    }

    public void openMyPersonalData() {
        click();
        btnPersonalData.click();
    }

    public void logout() {
        click();
        btnLogout.click();
    }

    public boolean isMenuOpen() {
        return WebDriverUtil.getElements(getWebElement(), By.cssSelector(".select--open")).size() > 0;
    }

    public void click() {
        if (!isMenuOpen()) {
            super.click();
        }
    }
}
