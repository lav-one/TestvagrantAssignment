package com.test.browserfactory;

import com.test.Exceptions.InvalidBrowserException;
import com.test.utils.Utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {

    public static void initializeBrowser(String browser) throws InvalidBrowserException {
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            }
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            }
            case "ie": {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            }
            default: {
                throw new InvalidBrowserException(browser);
            }
        }
        Utilities.setDriver(driver);
    }
}
