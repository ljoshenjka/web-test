package pages.MoviesSection;

import base.WebDriverUtil;
import elements.base.BaseField;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class MovieList extends BaseField {

    private static final String movieItemLocator = ".//div[@class='pageMovies__card']";

    public MovieList(BaseField parent, By locator) {
        super(parent, locator);
    }

    public MovieList(By locator) {
        super(locator);
    }

    public List<MovieItem> getMovies() {
        List<MovieItem> itemList = new ArrayList<>();
        int itemCount = getMovieCount();
        for (int i = 1; i <= itemCount; i++) {
            itemList.add(getMovieByIndex(i));
        }
        return itemList;

    }

    public Integer getMovieCount() {
        return WebDriverUtil.getElements(locator, By.xpath(movieItemLocator)).size();
    }

    public MovieItem getMovieByIndex(int index) {
        return new MovieItem(this, By.xpath(String.format("(%s)[%d]", movieItemLocator, index)));
    }
}
