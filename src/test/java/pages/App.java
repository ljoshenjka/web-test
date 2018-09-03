package pages;

import elements.Button;
import org.openqa.selenium.By;
import pages.components.LoginPopup;
import pages.components.PersonalDataPopup;
import pages.components.UserMenu;

/**
 * Represent application core page
 */
public class App {

    public static final Button btnLogin = new Button(By.id("login_button"));
    public static final LoginPopup loginPopup = new LoginPopup(By.id("login-form"));
    public static final UserMenu userMenu = new UserMenu(By.className("header__user"));
    public static final PersonalDataPopup personalDataPopup = new PersonalDataPopup(By.id("update-form"));

    // navigation
    public static final Button btnSchedule = new Button(By.xpath("//nav/ul/li[1]"));
    public static final Button btnMovies = new Button(By.xpath("//nav/ul/li[2]"));
    public static final Button btnGoodies = new Button(By.xpath("//nav/ul/li[3]"));
}
