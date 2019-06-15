package fintuity_tests.login;

import com.fintuity.PasswordSetting;
import com.fintuity.SelectTopicPage;
import environment.EnvironmentManager;
import mail.tempMailPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import mail.tempMain_EmailBody;
import util.UseCases;
import util.UserProfile;

import java.util.concurrent.TimeUnit;

public class MailServices {
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
    public void getEmail(){
        //https://temp-mail.org/
        driver.navigate().to("https://temp-mail.org/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        tempMailPage mainPage = new tempMailPage(driver);
        String email = mainPage.getEmail();
        System.out.println("Email: " + email);

        //set email
        driver_mail.navigate().to("https://fintuity.com/");
        UserProfile user = UserProfile.createUserWithRandomNameAndSurname();
        user.setEmail(email);
        UseCases.registrateUser(driver_mail,user);


        System.out.println(mainPage.isEmpty());
        if (!mainPage.isEmpty()){
            registrationEmailClick(mainPage);
        }else{
            //wait N attempt for 10 sec
            if (mainPage.isReceived(60)){
                driver.navigate().refresh();
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
        environmentManager.shutDownDriver();
        environmentManager_mail.shutDownDriver();
    }


}
