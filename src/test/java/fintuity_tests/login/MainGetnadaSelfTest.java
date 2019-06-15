package fintuity_tests.login;

import environment.EnvironmentManager;
import mail.EmailGetnada;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class MainGetnadaSelfTest {

    static WebDriver driver;
    static EnvironmentManager environmentManager;

    @Before
    public void startBrowser() {
        environmentManager = new EnvironmentManager();
        environmentManager.initWebDriver();
        driver = environmentManager.re.getWebDriver();
    }

    @Test
    public void initMail(){
        driver.navigate().to("https://getnada.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        EmailGetnada emailGetnadaPage = PageFactory.initElements(driver, EmailGetnada.class);
        System.out.println(emailGetnadaPage.getEmail());
    }


    @After
    public void tearDown() {
        environmentManager.shutDownDriver();
    }
}
