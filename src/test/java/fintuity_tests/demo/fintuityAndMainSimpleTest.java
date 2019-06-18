package fintuity_tests.demo;

import config.PropertiesFile;
import environment.EnvironmentManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class fintuityAndMainSimpleTest {
    static WebDriver driver;
    static EnvironmentManager environmentManager;
    static String fintuityUrl;

    @Before
    public void startBrowser() {
        environmentManager = new EnvironmentManager();
        environmentManager.initWebDriver();
        driver = environmentManager.re.getWebDriver();
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

    @After
    public void tearDown() {
        environmentManager.shutDownDriver();
    }
}
