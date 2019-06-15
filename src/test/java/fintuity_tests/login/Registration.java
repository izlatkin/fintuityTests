package fintuity_tests.login;

import com.fintuity.CongratsPage;
import com.fintuity.MainPage;
import com.fintuity.RegisterPage;
import environment.EnvironmentManager;
import mail.EmailGetnada;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import util.UserProfile;

import java.util.concurrent.TimeUnit;

public class Registration {
    static WebDriver driver;
    static WebDriver driver_mail;
    static EnvironmentManager environmentManager;
    static EnvironmentManager environmentManager_mail;

    @Before
    public void startBrowser() {
        environmentManager = new EnvironmentManager();
        environmentManager.initWebDriver();
        environmentManager_mail = new EnvironmentManager();
        environmentManager_mail.initWebDriver();
        driver = environmentManager.re.getWebDriver();
        driver_mail = environmentManager_mail.re.getWebDriver();
    }

    @Test
    public void reg_new_user(){
        driver.get("https://fintuity.hiendsys.ru/portal");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = mainPage.clickGetStarted();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //create new mail account
        //driver.findElement(By.cssSelector("body")).sendKeys(Keys.COMMAND +"t");
        driver_mail.navigate().to("https://getnada.com");
        driver_mail.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //EmailGetnada emailGetnada = new EmailGetnada(driver);
        EmailGetnada emailGetnada = PageFactory.initElements(driver_mail, EmailGetnada.class);
        String email =  emailGetnada.getEmail();
        //create new user
        UserProfile user = UserProfile.createUserWithRandomNameAndSurname();
        user.setEmail(email);

        //reg new user
        CongratsPage congratsPage = registerPage.register(user);
        //click "send again and content"
        Assert.assertTrue("Check that page contains \"Congrats!\" ",
                congratsPage.isTextPresent("Congrats!"));
        Assert.assertTrue("Check that page contains " +
                        "\"You have successfully created account in the system.\" ",
                congratsPage.isTextPresent("You have successfully created account in the system."));
        Assert.assertTrue("Check that page contains " +
                        "\" Send Link Again \" ",
                congratsPage.isTextPresent("Send Link Again"));
        //congratsPage.clickSendLinkAgainButton();

    }

    @Test
    public void reg_new_user_and_activate(){
        driver.get("https://fintuity.hiendsys.ru/portal");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = mainPage.clickGetStarted();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //create new mail account
        //driver.findElement(By.cssSelector("body")).sendKeys(Keys.COMMAND +"t");
        driver_mail.navigate().to("https://getnada.com");
        driver_mail.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //EmailGetnada emailGetnada = new EmailGetnada(driver);
        EmailGetnada emailGetnada = PageFactory.initElements(driver_mail, EmailGetnada.class);
        String email =  emailGetnada.getEmail();
        //create new user
        UserProfile user = UserProfile.createUserWithRandomNameAndSurname();
        user.setEmail(email);

        //reg new user
        CongratsPage congratsPage = registerPage.register(user);
        //click "send again and content"
        Assert.assertTrue("Check that page contains \"Congrats!\" ",
                congratsPage.isTextPresent("Congrats!"));
        Assert.assertTrue("Check that page contains " +
                        "\"You have successfully created account in the system.\" ",
                congratsPage.isTextPresent("You have successfully created account in the system."));
        Assert.assertTrue("Check that page contains " +
                        "\" Send Link Again \" ",
                congratsPage.isTextPresent("Send Link Again"));
        //congratsPage.clickSendLinkAgainButton();

        //wait n minutes for new email

        //click on email and do some checks for email

        //click on ac activation link

        //login with activated email
    }


    @After
    public void tearDown() {
        environmentManager.shutDownDriver();
        environmentManager_mail.shutDownDriver();
    }
}
