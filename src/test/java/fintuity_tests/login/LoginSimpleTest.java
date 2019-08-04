package fintuity_tests.login;

import com.fintuity.LoginPage;
import com.fintuity.MainPage;
import com.fintuity.MyProfilePage;
import com.fintuity.RegisterPage;
import environment.EnvironmentManager;
import environment.RunEnvironment;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginSimpleTest {

    static WebDriver driver;

    @BeforeMethod
    public void startBrowser() {
        EnvironmentManager.initWebDriver();
        driver = RunEnvironment.getWebDriver();
    }

    @Test
    public void loginWithExistingCredentials(){
        driver.get(EnvironmentManager.FINTUITY_FRONT_URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickSingIn();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MyProfilePage myProfilePage = loginPage.loginCorrect("zlatkin_ilya@mail.ru","Scaleio123");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(myProfilePage.getMyProfileTitle());
        Assert.assertTrue(myProfilePage.isTextPresent("My Profile"),"Check Title of the page");
    }

    @Test
    public void loginWithExistingCredentials_second_attempt(){
        driver.get(EnvironmentManager.FINTUITY_FRONT_URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickSingIn();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LoginPage loginPage_2 = loginPage.loginWithIncorrectCreds("zlatkin_ilya@mail.ru","incorrect");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals(loginPage_2.getErrorText(),"Sorry, your email and password are incorrect - please try again");

        loginPage_2.clearAllFields();
        MyProfilePage myProfilePage = loginPage_2.loginCorrect("zlatkin_ilya@mail.ru","Scaleio123");
        System.out.println(myProfilePage.getMyProfileTitle());
        Assert.assertTrue(myProfilePage.isTextPresent("My Profile"),"Check Title of the page");
    }

    @Test
    public void loginWithExistingCredentials_from_RegisterPage(){
        driver.get(EnvironmentManager.FINTUITY_FRONT_URL);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MainPage mainPage = new MainPage(driver);
        driver.manage().window().maximize();
        RegisterPage registerPage = mainPage.clickGetStarted();
        LoginPage loginPage = registerPage.clickLogin();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MyProfilePage myProfilePage = loginPage.loginCorrect("zlatkin_ilya@mail.ru","Scaleio123");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(myProfilePage.getMyProfileTitle());
        Assert.assertTrue(myProfilePage.isTextPresent("My Profile"),"Check Title of the page");
    }

    @AfterMethod
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }
}
