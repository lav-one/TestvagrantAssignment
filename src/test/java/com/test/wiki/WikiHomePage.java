package com.test.wiki;

import com.test.utils.Utilities;
import org.openqa.selenium.By;

public class WikiHomePage {
    By search_box = By.id("searchInput");
    By search_icon = By.id("searchButton");

    public WikiHomePage enterMovieName(String movieName) {
        Utilities.sendText(search_box, movieName);
        return this;
    }

    public WikiHomePage clickMovie() {
        Utilities.click(search_icon);
        return this;
    }
}
