package fintuity_tests.demo;

import environment.EnvironmentManager;
import environment.RunEnvironment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class fintuityFirst {
    static WebDriver driver;
    static EnvironmentManager environmentManager;

    @Before
    public void startBrowser() {
        environmentManager = new EnvironmentManager();
        environmentManager.initWebDriver();
        driver = environmentManager.re.getWebDriver();
    }


    @Test
    public void availabilityFintuity(){
        driver.navigate().to("https://fintuity.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Assert.assertTrue("Your personal financial adviser",driver.getTitle().startsWith("Your personal\n" ));
        System.out.println("Titels: " + driver.getTitle());
        Assert.assertTrue("Online Financial Adviser",driver.getTitle().startsWith("Online Financial Adviser" ));
        //driver.close();
        //driver.quit();
    }

    @Test
    public void loginValidTest(){
        driver.get("https://fintuity.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //check main page content
        WebElement signIn = driver.findElement(By.linkText("SIGN IN"));
        //WebElement getStarted = driver.findElement(By.linkText("GET STARTED"));
        driver.findElement(By.className("signin"));

        //driver.close();
        //driver.quit();
    }

    @After
    public void tearDown() {
        environmentManager.shutDownDriver();
    }

    @Test
    public void findBButtons(){
        driver.get("https://fintuity.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //check main page content
        WebElement yellowButton = driver.findElement(By.linkText("Book Free Consultation"));
        //WebElement LiveChat = driver.findElement(By.)
        WebElement LiveChatXPath = driver.findElement(By.xpath("/html/body/div[1]/main/section[4]/div/div[3]/a/button"));
        LiveChatXPath.click();
        //driver.findElement(By.className("signin"));

        //driver.close();
        //driver.quit();
    }
}
