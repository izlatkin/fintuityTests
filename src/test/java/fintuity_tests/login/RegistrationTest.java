package fintuity_tests.login;

import backoffice.AdminMainPage;
import backoffice.LoginPageBO;
import com.fintuity.*;
import environment.EnvironmentManager;
import environment.RunEnvironment;
import mail.Dropmail;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.UserProfile;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class RegistrationTest {
    static WebDriver driver;

    @BeforeMethod
    public void startBrowser() {
        EnvironmentManager.initWebDriver();
        driver = RunEnvironment.getWebDriver();
    }

    @Test
    public void reg_new_user(){
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
        // driver.get("https://getnada.com");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //EmailGetnada emailGetnada = new (driver);
        Dropmail tempMailPage = new Dropmail(driver);
        String email =  tempMailPage.getEmail();
        //create new user
        UserProfile user = UserProfile.createUserWithRandomNameAndSurname();
        user.setEmail(email);

        //reg new user
        driver.switchTo().window(fintuityTab);
        CongratsPage congratsPage = registerPage.register(user);
        //click "send again and content"
        Assert.assertTrue(congratsPage.isTextPresent("Congrats!"),"Check that page contains \"Congrats!\" ");
        Assert.assertTrue(congratsPage.isTextPresent("You have successfully created account in the system."),
                "Check that page contains " +
                        "\"You have successfully created account in the system.\" ");
        Assert.assertTrue(congratsPage.isTextPresent("Send Link Again"),
                "Check that page contains " +
                        "\" Send Link Again \" ");
    }

    @Test
    public void reg_new_user_and_activate() {
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
        // driver.get("https://getnada.com");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //EmailGetnada emailGetnada = new (driver);
        Dropmail tempMailPage = new Dropmail(driver);
        String email =  tempMailPage.getEmail();
        //create new user
        UserProfile user = UserProfile.createUserWithRandomNameAndSurname();
        user.setEmail(email);

        //reg new user
        driver.switchTo().window(fintuityTab);
        CongratsPage congratsPage = registerPage.register(user);
        //click "send again and content"
        Assert.assertTrue(congratsPage.isTextPresent("Congrats!"),
                "Check that page contains \"Congrats!\" ");
        Assert.assertTrue(congratsPage.isTextPresent("You have successfully created account in the system."),
                "Check that page contains " +
                        "\"You have successfully created account in the system.\" ");
        Assert.assertTrue(congratsPage.isTextPresent("Send Link Again"),
                "Check that page contains " +
                        "\" Send Link Again \" ");

        driver.switchTo().window(mailTab);
        String confirmation = "Fintuity email confir";
        tempMailPage.waitForElement(confirmation);
        //EmailPage emailPage = emailGetnada.openEmail("activation message");
        Assert.assertTrue(tempMailPage.isTextPresent(confirmation),
                "Fintuity email confirmation was not received ");

        //click on email and do some checks for email
        tempMailPage.clickOnText(confirmation);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        String confirmYourEmail = "Confirm your email";
        //emailGetnada.waitForElement(confirmYourEmail);

        tempMailPage.waitForElement(confirmYourEmail);
        String activationLink = tempMailPage.findConfirmLink();
        //Assert.assertTrue(emailBody.isTextPresent(confirmYourEmail));

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
        Assert.assertTrue(bookConsultation.isTextPresent(successMessage),
                "No \"" + successMessage + "\"");
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
        Assert.assertTrue(requestCall.isTextPresent(callMessage),
                "check: " + callMessage);
    }

    @Test
    public void reg_new_user_activate_backoffice_confirm() {
        driver.get(EnvironmentManager.FINTUITY_FRONT_URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MainPage mainPage = new MainPage(driver);
        driver.manage().window().maximize();
        RegisterPage registerPage = mainPage.clickGetStarted();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //create new mail account
        String fintuityTab = driver.getWindowHandle();
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String mailTab = "";
        for (String tab : tabs) {
            if (!tab.equals(fintuityTab))
                mailTab = tab;
        }
        driver.switchTo().window(mailTab);
        driver.get("https://dropmail.me/en/");
        // driver.get("https://getnada.com");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //EmailGetnada emailGetnada = new (driver);
        Dropmail tempMailPage = new Dropmail(driver);
        String email = tempMailPage.getEmail();
        //create new user
        UserProfile user = UserProfile.createUserWithRandomNameAndSurname();
        user.setEmail(email);

        //reg new user
        driver.switchTo().window(fintuityTab);
        CongratsPage congratsPage = registerPage.register(user);
        //click "send again and content"
        Assert.assertTrue(congratsPage.isTextPresent("Congrats!"),
                "Check that page contains \"Congrats!\" ");
        Assert.assertTrue(congratsPage.isTextPresent("You have successfully created account in the system."),
                "Check that page contains " +
                        "\"You have successfully created account in the system.\" ");
        Assert.assertTrue(congratsPage.isTextPresent("Send Link Again"),
                "Check that page contains " +
                        "\" Send Link Again \" ");

        driver.switchTo().window(mailTab);
        String confirmation = "Fintuity email confir";
        tempMailPage.waitForElement(confirmation);
        //EmailPage emailPage = emailGetnada.openEmail("activation message");
        Assert.assertTrue(tempMailPage.isTextPresent(confirmation),
                "Fintuity email confirmation was not received ");

        //click on email and do some checks for email
        tempMailPage.clickOnText(confirmation);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        String confirmYourEmail = "Confirm your email";
        //emailGetnada.waitForElement(confirmYourEmail);

        tempMailPage.waitForElement(confirmYourEmail);
        String activationLink = tempMailPage.findConfirmLink();
        //Assert.assertTrue(emailBody.isTextPresent(confirmYourEmail));

        //click on ac activation link
        Assert.assertFalse(activationLink.isEmpty(),
                "Activation link was not found ");
        ((JavascriptExecutor) driver).executeScript("window.open()");
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
        Assert.assertTrue(bookConsultation.isTextPresent(successMessage),
                "No \"" + successMessage + "\"");
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

        ((JavascriptExecutor) driver).executeScript("window.open()");
        tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.switchTo().window(tabs.get(3));
        driver.get(EnvironmentManager.FINTUITY_BACKOFFICE_URL);

        LoginPageBO loginPageBO = new LoginPageBO(driver);
        AdminMainPage adminMainPage =
                loginPageBO.loginCorrect("ilya.zlatkin@gmail.com","Scaleio123");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertTrue(adminMainPage.isTextPresent(user.getName()),"Check user Name: ");
        Assert.assertTrue(adminMainPage.isTextPresent(user.getSurname()),"Check user Surname: ");
        Assert.assertTrue(adminMainPage.isTextPresent(user.getPhone()),"Check Phone: ");
    }

    @AfterMethod
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }
}
