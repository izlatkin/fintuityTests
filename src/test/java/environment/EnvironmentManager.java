package environment;

import config.PropertiesFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class EnvironmentManager {
    public RunEnvironment re;
    public String fintuity_front_url;
    public String fintuity_backoffice_url;

    public void initWebDriver() {
        String browser = PropertiesFile.getBrowser();
        fintuity_front_url = PropertiesFile.getFintuity_front();
        fintuity_backoffice_url = PropertiesFile.getFintuity_backoffice();
        WebDriver driver;
        switch (browser){
            case ("firefox"):
            case ("Firefox"):
                System.setProperty("webdriver.gecko.driver",
                        PropertiesFile.getDriverPath("webdriver.gecko.driver"));
                driver = new FirefoxDriver();
                break;
            case ("safary"):
            case ("Safary"):
                System.setProperty("webdriver.safari.driver",
                        PropertiesFile.getDriverPath("webdriver.safari.driver"));
                driver = new SafariDriver();
                break;
            case ("chrome"):
            case ("Chrome"):
                //System.setProperty("webdriver.chrome.driver",
                // "C:\\Users\\zlatki\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
                System.setProperty("webdriver.chrome.driver",
                        PropertiesFile.getDriverPath("webdriver.chrome.driver"));
                driver = new ChromeDriver();
                break;
            case ("edge"):
            case ("Edge"):
                System.setProperty("webdriver.edge.driver",
                        PropertiesFile.getDriverPath("webdriver.edge.driver"));
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("Check Property File file for browser settings");
                return;

        }
        //http://phantomjs.org/download.html
        //System.setProperty("phantomjs.binary.path",
        // "C:\\Users\\zlatki\\AppData\\Local\\Google\\Chrome\\Application\\phantomjs-2.1.1-windows");



        re = new RunEnvironment();
        re.setWebDriver(driver);
    }

    public void shutDownDriver() {
        re.getWebDriver().quit();
    }
}
