package com.fintuity.login;

import com.fintuity.LoginPage;
import com.fintuity.MainPage;
import com.fintuity.MyProfilePage;
import com.fintuity.RegisterPage;
import com.fintuity.environment.EnvironmentManager;
import com.fintuity.environment.RunEnvironment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class LoginSimple {
    static WebDriver driver;
    @Before
    public void startBrowser() {
        EnvironmentManager.initWebDriver();
        driver = RunEnvironment.getWebDriver();
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
        Assert.assertTrue("Check Title of the page", myProfilePage.getMyProfileTitle().equals("My Profile"));
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
        Assert.assertTrue("Get Error Text: ",
                loginPage_2.getErrorText().equals("Sorry, your email and password are incorrect - please try again"));

        loginPage_2.clearAllFields();
        MyProfilePage myProfilePage = loginPage_2.loginCorrect("zlatkin_ilya@mail.ru","Scaleio123");
        System.out.println(myProfilePage.getMyProfileTitle());
        Assert.assertTrue("Check Title of the page", myProfilePage.getMyProfileTitle().equals("My Profile"));
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
        Assert.assertTrue("Check Title of the page", myProfilePage.getMyProfileTitle().equals("My Profile"));
    }



        @After
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }
}
