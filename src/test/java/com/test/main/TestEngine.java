package com.test.main;

import com.test.Exceptions.InvalidBrowserException;
import com.test.Exceptions.InvalidUrlException;
import com.test.browserfactory.BrowserFactory;
import com.test.imdb.ImdbHomePage;
import com.test.imdb.ImdbMoviePage;
import com.test.utils.Utilities;
import com.test.wiki.WikiHomePage;
import com.test.wiki.WikiMoviePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

public class TestEngine {
    Properties propertyFileObj;
    ImdbHomePage imdbHomePageObj;
    ImdbMoviePage imdbMoviePageObj;
    WikiHomePage wikiHomePageObj;
    WikiMoviePage wikiMoviePageObj;

    @BeforeTest
    public void loadConfigAndLaunchBrowser() throws IOException, InvalidBrowserException {
        FileInputStream file = new FileInputStream(
                System.getProperty("user.dir") +
                        "/src/Config.properties");
        propertyFileObj = new Properties();
        propertyFileObj.load(file);
        BrowserFactory.initializeBrowser(propertyFileObj.getProperty("Browser"));
    }

    @BeforeClass
    public void getMovieDataFromIMDB() throws InvalidUrlException {
        Utilities.launchURL(propertyFileObj.getProperty("imdbUrl"));
        imdbHomePageObj = new ImdbHomePage();
        imdbHomePageObj.searchMovie(propertyFileObj.getProperty("MovieName")).clickMovieLink();
        imdbMoviePageObj = new ImdbMoviePage();
        imdbMoviePageObj.readReleaseDate().
                readCountryName();
    }

    @BeforeClass(dependsOnMethods = "getMovieDataFromIMDB")
    public void getMovieDataFromWiki() throws InvalidUrlException {
        Utilities.launchURL(propertyFileObj.getProperty("wikiUrl"));
        wikiHomePageObj = new WikiHomePage();
        wikiHomePageObj.enterMovieName(propertyFileObj.getProperty("MovieName"))
                .clickMovie();
        wikiMoviePageObj = new WikiMoviePage();
        wikiMoviePageObj.readReleaseDate()
                .readCountryName();
    }

    @Test
    public void validateCountryName() {
        Assert.assertEquals(ImdbMoviePage.imdbCountryOfMovie, WikiMoviePage.wikiCountryOfMovie,
                "Country is not matching for IMDB and Wiki..");

    }

    @Test
    public void validateReleaseDate() throws ParseException {
        Date imdbDate = Utilities.parseDate(propertyFileObj.getProperty("imdbDateFormat"), ImdbMoviePage.imdbReleaseDateOfMovie);
        Date wikiDate = Utilities.parseDate(propertyFileObj.getProperty("wikiDateFormat"), WikiMoviePage.wikiReleaseDateOfMovie);
        Assert.assertEquals(imdbDate, wikiDate,
                "Release Date is not matching for IMDB and Wiki..");
    }

    @AfterClass
    public void tearDown() {
        Utilities.quitDriver();
    }
}
