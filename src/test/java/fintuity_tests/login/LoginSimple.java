package fintuity_tests.login;

import com.fintuity.LoginPage;
import com.fintuity.MainPage;
import com.fintuity.MyProfilePage;
import com.fintuity.RegisterPage;
import environment.EnvironmentManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginSimple {
    static WebDriver driver;
    static EnvironmentManager environmentManager;
    @Before
    public void startBrowser() {
        environmentManager = new EnvironmentManager();
        environmentManager.initWebDriver();
        driver = environmentManager.re.getWebDriver();
    }


    @Test
    public void loginWithExistingCredentials(){
        driver.navigate().to("https://fintuity.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickSignIn();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MyProfilePage myProfilePage = loginPage.loginCorrect("zlatkin_ilya@mail.ru","Scaleio123");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(myProfilePage.getMyProfileTitle());
        Assert.assertEquals("Check Title of the page", myProfilePage.getMyProfileTitle(),"My Profile");
    }

    @Test
    public void loginWithExistingCredentials_second_attempt(){
        driver.navigate().to("https://fintuity.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickSignIn();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LoginPage loginPage_2 = loginPage.loginWithIncorrectCreds("zlatkin_ilya@mail.ru","incorrect");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals("Get Error Text: ",
                loginPage_2.getErrorText(),"Sorry, your email and password are incorrect - please try again");

        loginPage_2.clearAllFields();
        MyProfilePage myProfilePage = loginPage_2.loginCorrect("zlatkin_ilya@mail.ru","Scaleio123");
        System.out.println(myProfilePage.getMyProfileTitle());
        Assert.assertEquals("Check Title of the page", myProfilePage.getMyProfileTitle(),"My Profile");
    }

    @Test
    public void loginWithExistingCredentials_from_RegisterPage(){
        driver.navigate().to("https://fintuity.com/");
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = mainPage.clickGetStarted();
        LoginPage loginPage = registerPage.clickLogin();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MyProfilePage myProfilePage = loginPage.loginCorrect("zlatkin_ilya@mail.ru","Scaleio123");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(myProfilePage.getMyProfileTitle());
        Assert.assertEquals("Check Title of the page", myProfilePage.getMyProfileTitle(),"My Profile");
    }



        @After
    public void tearDown() {
        environmentManager.shutDownDriver();
    }
}
