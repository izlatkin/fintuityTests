package com.fintuity.environment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class EnvironmentManager {
    public static void initWebDriver() {
        //http://phantomjs.org/download.html
        System.setProperty("phantomjs.binary.path", "C:\\Users\\zlatki\\AppData\\Local\\Google\\Chrome\\Application\\phantomjs-2.1.1-windows");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\zlatki\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
        //System.setProperty("webdriver.chrome.driver", "/Users/ilyazlatkin/IdeaProjects/chromedriver");
        WebDriver driver = new ChromeDriver();
        //System.setProperty("webdriver.edge.driver", "C:\\Users\\zlatki\\AppData\\Local\\Google\\Chrome\\Application\\MicrosoftWebDriver.exe");
        //WebDriver driver = new EdgeDriver();
        System.setProperty("webdriver.gecko.driver", "/Users/ilyazlatkin/IdeaProjects/geckodriver");
        System.setProperty("webdriver.safari.driver","/usr/bin/safaridriver");
        //WebDriver driver = new FirefoxDriver();
        //WebDriver driver = new SafariDriver();
        RunEnvironment.setWebDriver(driver);
    }

    public static void shutDownDriver() {
        RunEnvironment.getWebDriver().quit();
    }
}
