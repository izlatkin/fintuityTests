package fintuity_tests.demo;

import config.PropertiesFile;
import environment.EnvironmentManager;
import environment.RunEnvironment;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class fintuityAndMainSimpleTest {
    static WebDriver driver;
    static String fintuityUrl;

    @BeforeMethod
    public void startBrowser() {
        EnvironmentManager.initWebDriver();
        driver = RunEnvironment.getWebDriver();
        fintuityUrl = PropertiesFile.getFintuity_front();
    }

    @Test
    public void OpenTwoWindows(){
        driver.get("http://yahoo.com");
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("http://google.com");
    }

    @AfterMethod
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }
}
