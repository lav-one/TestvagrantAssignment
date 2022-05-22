package com.test.wiki;

import com.test.utils.Utilities;
import org.openqa.selenium.By;

public class WikiMoviePage {
    public static String wikiReleaseDateOfMovie;
    public static String wikiCountryOfMovie;
    By release_date = By.xpath("//div[text()='Release date']//parent::th//following-sibling::td//li");
    By country = By.xpath("//th[text()='Country']//following-sibling::td");

    public WikiMoviePage readReleaseDate() {
        wikiReleaseDateOfMovie = Utilities.getText(release_date);
        return this;
    }

    public WikiMoviePage readCountryName() {
        wikiCountryOfMovie = Utilities.getText(country);
        return this;
    }
}
