package fintuity_tests.demo;

import environment.EnvironmentManager;
import environment.RunEnvironment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class fintuityFirst {
    static WebDriver driver;


    @BeforeMethod
    public void startBrowser() {
        EnvironmentManager.initWebDriver();
        driver = RunEnvironment.getWebDriver();
    }


    @Test
    public void availabilityFintuity(){
        driver.navigate().to("https://fintuity.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Assert.assertTrue("Your personal financial adviser",driver.getTitle().startsWith("Your personal\n" ));
        System.out.println("Titels: " + driver.getTitle());
        Assert.assertTrue(driver.getTitle().startsWith("Online Financial Adviser" ),"Online Financial Adviser");
        //driver.close();
        //driver.quit();
    }


    @Test
    public void loginValidTest(){
        driver.get("https://fintuity.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //check main page content
        WebElement signIn = driver.findElement(By.linkText("SIGN IN"));
        driver.findElement(By.className("signin"));
    }

    @Test
    public void findBButtons(){
        driver.get("https://fintuity.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //check main page content
        WebElement yellowButton = driver.findElement(By.linkText("Book Free Consultation"));
        WebElement LiveChatXPath = driver.findElement(By.xpath("/html/body/div[1]/main/section[4]/div/div[3]/a/button"));
        LiveChatXPath.click();
    }

    @AfterMethod
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }
}
