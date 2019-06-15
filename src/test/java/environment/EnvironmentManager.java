package environment;

import config.PropertiesFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class EnvironmentManager {
    public RunEnvironment re;

    public void initWebDriver() {
        String browser = PropertiesFile.getBrowser();
        WebDriver driver;
        switch (browser){
            case ("firefox"):
            case ("Firefox"):
                System.setProperty("webdriver.gecko.driver", "/Users/ilyazlatkin/IdeaProjects/geckodriver");
                driver = new FirefoxDriver();
                break;
            case ("safary"):
            case ("Safary"):
                System.setProperty("webdriver.safari.driver","/usr/bin/safaridriver");
                driver = new SafariDriver();
                break;
            case ("chrome"):
            case ("Chrome"):
                //System.setProperty("webdriver.chrome.driver", "C:\\Users\\zlatki\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", "/Users/ilyazlatkin/IdeaProjects/chromedriver");
                driver = new ChromeDriver();
                break;
            case ("edge"):
            case ("Edge"):
                System.setProperty("webdriver.edge.driver", "C:\\Users\\zlatki\\AppData\\Local\\Google\\Chrome\\Application\\MicrosoftWebDriver.exe");
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("Check Property File file for browser settings");
                return;



        }
        //http://phantomjs.org/download.html
        //System.setProperty("phantomjs.binary.path", "C:\\Users\\zlatki\\AppData\\Local\\Google\\Chrome\\Application\\phantomjs-2.1.1-windows");



        re = new RunEnvironment();
        re.setWebDriver(driver);
    }

    public void shutDownDriver() {
        re.getWebDriver().quit();
    }
}
