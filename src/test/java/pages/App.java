package pages;

import base.WebDriverUtil;
import constants.PropertyConfigs;
import elements.Button;
import helpers.ConfigReader;
import org.openqa.selenium.By;
import pages.components.LoginPopup;
import pages.components.Notification;
import pages.components.PersonalDataPopup;
import pages.components.UserMenu;

import static base.WebDriverUtil.getDriver;

/**
 * Represent application core page
 */
public class App {

    public static final Button btnLogin = new Button(By.id("login_button"));
    public static final LoginPopup loginPopup = new LoginPopup(By.id("login-form"));
    public static final UserMenu userMenu = new UserMenu(By.className("header__user"));
    public static final PersonalDataPopup personalDataPopup = new PersonalDataPopup(By.id("update-form"));
    public static final Notification sucessNotification = new Notification(By.cssSelector(".notification--success"));
    public static final Notification errorNotification = new Notification(By.cssSelector(".notification--error"));
    public static final Notification infoNotification = new Notification(By.cssSelector(".notification--info"));

    // navigation
    public static final Button btnSchedule = new Button(By.xpath("//nav/ul/li[1]"));
    public static final Button btnMovies = new Button(By.xpath("//nav/ul/li[2]"));
    public static final Button btnGoodies = new Button(By.xpath("//nav/ul/li[3]"));

    public static final Button btnLatvianCinamon = new Button(By.xpath("//span[@class='flag-lv']"));


    public static void openApp() {
        getDriver().navigate().to(ConfigReader.getInstance().getValue(PropertyConfigs.APP_URL));
        btnLatvianCinamon.click();
        if (infoNotification.isDisplayed()) {
            infoNotification.btnClose.click();
            WebDriverUtil.wait.until(driver -> !infoNotification.isDisplayed());
        }
    }
}
