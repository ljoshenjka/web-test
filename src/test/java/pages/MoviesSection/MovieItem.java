package pages.MoviesSection;

import elements.Label;
import elements.base.BaseField;
import elements.base.ClickableField;
import org.openqa.selenium.By;

public class MovieItem extends ClickableField {

    public Label lblMovieName = new Label(this, By.xpath(".//div[@class[contains(.,'pageMovies__card__title')]]/a"));
    public Label lblRating = new Label(this, By.xpath("//div[@class[contains(.,'pageMovies__card__title')]]/div"));
    public Label lblGenre = new Label(this, By.className("pageMovies__card__genre"));

    public MovieItem(BaseField parent, By locator) {
        super(parent, locator);
    }

    public MovieItem(By locator) {
        super(locator);
    }

}
