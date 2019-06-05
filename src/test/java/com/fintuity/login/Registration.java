package com.fintuity.login;

import com.fintuity.*;
import com.fintuity.environment.EnvironmentManager;
import com.fintuity.environment.RunEnvironment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Registration {
    static WebDriver driver;
    @Before
    public void startBrowser() {
        EnvironmentManager.initWebDriver();
        driver = RunEnvironment.getWebDriver();
    }

    @Test
    public void loginWithExistingCredentials_second_attempt(){
        driver.navigate().to("https://fintuity.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = mainPage.clickGetStarted();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        CongratsPage congratsPage = registerPage.register("ilya","zlatkin","siwolur@eaglemail.top");
        congratsPage.clickSendLinkAgainButton();

    }


    @After
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }
}
