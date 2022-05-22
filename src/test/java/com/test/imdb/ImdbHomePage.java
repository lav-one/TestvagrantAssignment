package com.test.imdb;

import com.test.utils.Utilities;
import org.openqa.selenium.By;

public class ImdbHomePage {
    By search_box = By.id("suggestion-search");
    By search_icon = By.id("suggestion-search-button");
    By movie_link = By.xpath("//h3[text()='Titles']/following-sibling::table/tbody/tr[1]/td[2]/a");

    public ImdbHomePage searchMovie(String movieName) {
        Utilities.sendText(search_box, movieName);
        Utilities.click(search_icon);
        return this;
    }

    public ImdbHomePage clickMovieLink() {
        Utilities.click(movie_link);
        return this;
    }
}
