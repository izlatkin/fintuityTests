package com.fintuity.environment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class EnvironmentManager {
    public static void initWebDriver() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\zlatki\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "/Users/ilyazlatkin/IdeaProjects/chromedriver");
        //WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.gecko.driver", "/Users/ilyazlatkin/IdeaProjects/geckodriver");
        System.setProperty("webdriver.safari.driver","/usr/bin/safaridriver");
        WebDriver driver = new FirefoxDriver();
        //WebDriver driver = new SafariDriver();
        RunEnvironment.setWebDriver(driver);
    }

    public static void shutDownDriver() {
        RunEnvironment.getWebDriver().quit();
    }
}
