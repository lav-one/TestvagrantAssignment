package com.test.utils;

import com.test.Exceptions.InvalidUrlException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Utilities {
    private static WebDriver driver;

    public static void setDriver(WebDriver idriver) {
        driver = idriver;
    }

    public static void launchURL(String url) throws InvalidUrlException {
        if (url.length() > 0) {
            driver.get(url);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } else {
            throw new InvalidUrlException("URL empty");
        }
    }

    public static void click(By element) {
        driver.findElement(element).click();
    }

    public static String getText(By element) {
        return driver.findElement(element).getText();
    }

    public static void sendText(By element, String input) {
        driver.findElement(element).sendKeys(input);
    }

    public static void closeBrower() {
        driver.close();
    }

    public static void quitDriver() {
        driver.quit();
    }

    public static Date parseDate(String expectedFormat, String actualData) throws ParseException {
        return new SimpleDateFormat(expectedFormat)
                .parse(actualData);
    }

}
