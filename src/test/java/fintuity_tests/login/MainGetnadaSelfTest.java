package fintuity_tests.login;

import environment.EnvironmentManager;
import environment.RunEnvironment;
import mail.EmailGetnada;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MainGetnadaSelfTest {

    static WebDriver driver;
    static EnvironmentManager environmentManager;

    @BeforeMethod
    public void startBrowser() {
        EnvironmentManager.initWebDriver();
        driver = RunEnvironment.getWebDriver();
    }

    @Test
    public void initMail(){
        driver.navigate().to("https://getnada.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        EmailGetnada emailGetnadaPage = PageFactory.initElements(driver, EmailGetnada.class);
        System.out.println(emailGetnadaPage.getEmail());
    }


    @AfterMethod
    public void tearDown() {
        environmentManager.shutDownDriver();
    }
}
