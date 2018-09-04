package pages.components;

import base.WebDriverUtil;
import elements.base.BaseField;
import org.openqa.selenium.By;

import static testHelpers.TestProperties.*;

public class MovieFilter extends BaseField {

    private String locMovieStatus = ".//div[@class='pageMovies__filter__main']/ul/li";

    public MovieFilter(BaseField parent, By locator) {
        super(parent, locator);
    }

    public MovieFilter(By locator) {
        super(locator);
    }

    public void selectStatus(String movieStatus) {
        int i = 0;
        switch (movieStatus) {
            case ALL_MOVIES_EN:
                i = 1;
                break;
            case IN_THEATRE_EN:
                i = 2;
                break;
            case COMING_SOON_EN:
                i = 3;
                break;
            case LAST_CHANCE_EN:
                i = 4;
                break;
        }
        WebDriverUtil.getElement(getWebElement(), By.xpath(String.format("%s[%d]", locMovieStatus, i))).click();
        // TODO: change this to normal wait
        WebDriverUtil.sleep(3000);
    }

    public void selectScreen(String movieScreen) {

    }

    public void selectFormat(String movieFormat) {

    }

    public void selectGenre(String movieGenre) {

    }

    public void selectLanguage(String movieLanguage) {

    }
}
