package com.test.imdb;

import com.test.utils.Utilities;
import org.openqa.selenium.By;

public class ImdbMoviePage {
    public static String imdbReleaseDateOfMovie;
    public static String imdbCountryOfMovie;
    By release_date = By.xpath("//a[text()='Release date']/parent::li//following-sibling::div//a");
    By country = By.xpath("//span[text()='Country of origin']/parent::li//following-sibling::div//a");

    public ImdbMoviePage readReleaseDate() {
        imdbReleaseDateOfMovie = Utilities.getText(release_date).split(" \\(")[0];
        return this;
    }

    public ImdbMoviePage readCountryName() {
        imdbCountryOfMovie = Utilities.getText(country);
        return this;
    }
}
