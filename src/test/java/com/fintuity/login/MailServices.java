package com.fintuity.login;

import com.fintuity.*;
import com.fintuity.environment.EnvironmentManager;
import com.fintuity.environment.RunEnvironment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.tempmail.tempMailPage;
import org.tempmail.tempMain_EmailBody;
import util.UseCases;
import util.UserProfile;

import java.util.concurrent.TimeUnit;

public class MailServices {
    static WebDriver driver;
    static WebDriver driver_f;
    @Before
    public void startBrowser() {
        EnvironmentManager.initWebDriver();
        driver = RunEnvironment.getWebDriver();
        EnvironmentManager.initWebDriver();
        driver_f = RunEnvironment.getWebDriver();
    }

    @Test
    public void getEmail(){
        //https://temp-mail.org/
        driver.navigate().to("https://temp-mail.org/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        tempMailPage mainPage = new tempMailPage(driver);
        String email = mainPage.getEmail();
        System.out.println("Email: " + email);

        //set email
        driver_f.navigate().to("https://fintuity.com/");
        UserProfile user = UserProfile.createUserWithRandomNameAndSurname();
        user.setEmail(email);
        UseCases.registrateUser(driver_f,user);


        System.out.println(mainPage.isEmpty());
        if (!mainPage.isEmpty()){
            registrationEmailClick(mainPage);
        }else{
            //wait N attempt for 10 sec
            if (mainPage.isReceived(30)){
                registrationEmailClick(mainPage);
            }else{
                Assert.assertTrue("No registration email was Received", false);
            }
        }

        mainPage.refresh();


    }

    private void registrationEmailClick(tempMailPage mainPage) {
        tempMain_EmailBody email_body = mainPage.clickLastEmail();
        PasswordSetting passwordSettingPage = email_body.clickConfirm();
        passwordSettingPage.setGeneratedPassword();
        SelectTopicPage selectTopicPage = passwordSettingPage.clickSetNewPasswordButton();
        Assert.assertTrue("You have successfully activated your account",selectTopicPage.isActivated());
    }


    @After
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }


}
