package pages.MoviesSection;

import elements.Label;
import org.openqa.selenium.By;
import pages.components.MovieFilter;

public class MoviesPage {

    public static final MovieFilter movieFilter = new MovieFilter(By.cssSelector(".pageMovies__filter"));
    public static final Label lblSectionTitle = new Label(By.cssSelector(".sectionTitle"));
    public static final MovieList movieList = new MovieList(By.className("pageMovies__cards"));
}
