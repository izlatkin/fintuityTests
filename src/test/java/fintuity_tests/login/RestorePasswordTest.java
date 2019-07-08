package fintuity_tests.login;

import com.fintuity.*;
import environment.EnvironmentManager;
import environment.RunEnvironment;
import mail.Dropmail;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import util.UserProfile;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class RestorePasswordTest{
    static WebDriver driver;
    static UserProfile user;

    @BeforeMethod
    public void startBrowser_and_createNewUser() {
        EnvironmentManager.initWebDriver();
        driver = RunEnvironment.getWebDriver();
    }

    @Test
    public void createNewUser_and_restorePassword(){
        driver.get(EnvironmentManager.FINTUITY_FRONT_URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MainPage mainPage = new MainPage(driver);
        driver.manage().window().maximize();
        RegisterPage registerPage = mainPage.clickGetStarted();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //create new mail account
        String fintuityTab = driver.getWindowHandle();
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String mailTab ="";
        for (String tab:tabs) {
            if (!tab.equals(fintuityTab))
                mailTab = tab;
        }
        driver.switchTo().window(mailTab);
        driver.get("https://dropmail.me/en/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        Dropmail tempMailPage = new Dropmail(driver);
        String email =  tempMailPage.getEmail();
        //create new user
        UserProfile user = UserProfile.createUserWithRandomNameAndSurname();
        user.setEmail(email);

        //reg new user
        driver.switchTo().window(fintuityTab);
        CongratsPage congratsPage = registerPage.register(user);

        driver.switchTo().window(mailTab);
        String confirmation = "Fintuity email confir";
        tempMailPage.waitForElement(confirmation);

        //click on email and do some checks for email
        tempMailPage.clickOnText(confirmation);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        String confirmYourEmail = "Confirm your email";

        tempMailPage.waitForElement(confirmYourEmail);
        String activationLink = tempMailPage.findConfirmLink();

        //click on ac activation link
        Assert.assertFalse(activationLink.isEmpty(),
                "Activation link was not found ");
        ((JavascriptExecutor)driver).executeScript("window.open()");
        tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.switchTo().window(tabs.get(2));
        driver.get(activationLink);

        //login with activated email
        user.generatePassword();
        String password = user.getPassword();
        PasswordSetting passwordSetting = new PasswordSetting(driver);
        passwordSetting.setPasswordField(password);
        passwordSetting.setPasswordConfirmField(password);
        passwordSetting.clickSetNewPasswordButton();

        //book consultation
        BookConsultation bookConsultation = new BookConsultation(driver);
        String successMessage = "You have successfully activated your account";
        bookConsultation.printCheckBoxes();
        bookConsultation.clickNext();

        //Request Call
        RequestCall requestCall = new RequestCall(driver);
        user.generatePhone();
        requestCall.setPhone(user.getPhone());
        requestCall.clickRequestCallBack();

        // wait for "Consultation has been successfully booked. .."
        String callMessage = "Consultation has been successfully booked. Your IFA will contact you shortly";
        requestCall.waitForElement(callMessage);

        //press SignIn
        driver.switchTo().window(fintuityTab);
        LoginPage loginPage = registerPage.clickSignIn();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        RestorePage restorePage = loginPage.clickRestore();
        Assert.assertTrue(registerPage.isTextPresent("Password Restore"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        restorePage.typeEmail(user.getEmail());
        CongratsPage congratsPage2 = restorePage.clickRestore();
        Assert.assertTrue(congratsPage2.isTextPresent("Success!"));
        Assert.assertTrue(congratsPage2.isTextPresent("A link has been sent to your email"));

        //check email for restore links
        //press SignIn
        driver.switchTo().window(mailTab);
        String restoreEmailSubject = "Fintuity restore password";
        tempMailPage.waitForElement(restoreEmailSubject, 600);
        tempMailPage.clickOnText(restoreEmailSubject);

        String restoreLink = tempMailPage.findRestoreLink();

        //click on ac activation link
        Assert.assertFalse(restoreLink.isEmpty(),
                "Restore link was not found ");
        ((JavascriptExecutor)driver).executeScript("window.open()");
        tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.switchTo().window(tabs.get(3));
        driver.get(restoreLink);

        user.generatePassword();
        String password_new = user.getPassword();
        passwordSetting = new PasswordSetting(driver);
        passwordSetting.setPasswordField(password_new);
        passwordSetting.setPasswordConfirmField(password_new);
        passwordSetting.clickSetNewPasswordButton();

        passwordSetting.waitForElement("Success!");
        Assert.assertTrue(passwordSetting.isTextPresent("Your password has been successfully changed."));
    }


//    @AfterMethod
//    public void tearDown() {
//        EnvironmentManager.shutDownDriver();
//    }
}
