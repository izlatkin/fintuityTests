package fintuity_tests.login;

import com.fintuity.*;
import environment.EnvironmentManager;
import mail.Dropmail;
import mail.EmailGetnada;
import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import util.UserProfile;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Registration {
    static WebDriver driver;
    static EnvironmentManager environmentManager;
    static String FINTUITY_FRONT_URL;

    @Before
    public void startBrowser() {
        environmentManager = new EnvironmentManager();
        environmentManager.initWebDriver();
        FINTUITY_FRONT_URL = environmentManager.fintuity_front_url;
        driver = environmentManager.re.getWebDriver();
    }

    @Test
    public void reg_new_user(){
        driver.get(FINTUITY_FRONT_URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MainPage mainPage = new MainPage(driver);
        driver.manage().window().maximize();
        RegisterPage registerPage = mainPage.clickGetStarted();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //create new mail account
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        for(int i = 0; i < tabs.size(); i++) {
            System.out.println(tabs.get(i));
        }
        driver.switchTo().window(tabs.get(1));
        driver.get("https://getnada.com");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        EmailGetnada emailGetnada = new EmailGetnada(driver);
        //EmailGetnada emailGetnada = PageFactory.initElements(driver, EmailGetnada.class);
        String email =  emailGetnada.getEmail();
        //create new user
        UserProfile user = UserProfile.createUserWithRandomNameAndSurname();
        user.setEmail(email);

        //reg new user
        driver.switchTo().window(tabs.get(0));
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
    }

    @Test
    public void reg_new_user_and_activate() {
        driver.get(FINTUITY_FRONT_URL);
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
        Assert.assertTrue("Check that page contains \"Congrats!\" ",
                congratsPage.isTextPresent("Congrats!"));
        Assert.assertTrue("Check that page contains " +
                        "\"You have successfully created account in the system.\" ",
                congratsPage.isTextPresent("You have successfully created account in the system."));
        Assert.assertTrue("Check that page contains " +
                        "\" Send Link Again \" ",
                congratsPage.isTextPresent("Send Link Again"));

        driver.switchTo().window(tabs.get(1));
        String confirmation = "Fintuity email confir";
        tempMailPage.waitForElement(confirmation);
        //EmailPage emailPage = emailGetnada.openEmail("activation message");
        Assert.assertTrue("Fintuity email confirmation was not received ",
                tempMailPage.isTextPresent(confirmation));

        //click on email and do some checks for email
        tempMailPage.clickOnText(confirmation);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        String confirmYourEmail = "Confirm your email";
        //emailGetnada.waitForElement(confirmYourEmail);

        tempMailPage.waitForElement(confirmYourEmail);
        String activationLink = tempMailPage.findConfirmLink();
        //Assert.assertTrue(emailBody.isTextPresent(confirmYourEmail));

        //click on ac activation link
        Assert.assertFalse("Activation link was not found ", activationLink.isEmpty());
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs_n = new ArrayList<String>(driver.getWindowHandles());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.switchTo().window(tabs_n.get(2));
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
        Assert.assertTrue("No \"" + successMessage + "\"",
                bookConsultation.isTextPresent(successMessage));
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
        Assert.assertTrue("check: " + callMessage,requestCall.isTextPresent(callMessage));
    }

    @After
    public void tearDown() {
        environmentManager.shutDownDriver();
    }
}
