package steps;

import base.BaseStep;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.MoviesSection.MoviesPage;

import java.util.concurrent.ThreadLocalRandom;

public class MoviesSectionSteps extends BaseStep {

    @And("^select status \"([^\"]*)\" from movie filter$")
    public void selectFromMovieFilter(String statusName) throws Throwable {
        MoviesPage.movieFilter.selectStatus(statusName);
    }

    @Then("^movie list header is \"([^\"]*)\"$")
    public void movieListHeaderIs(String header) throws Throwable {
        Assert.assertEquals("Wrong movie list header", header, MoviesPage.lblSectionTitle.getValue());
    }

    @When("^user select random movie from movie list$")
    public void userSelectRandomMovieFromMovieList() throws Throwable {
        int randomMovieNumber = ThreadLocalRandom.current().nextInt(1, MoviesPage.movieList.getMovieCount());
        MoviesPage.movieList.getMovieByIndex(randomMovieNumber).click();
    }
}
