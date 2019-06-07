package util;

import com.fintuity.CongratsPage;
import com.fintuity.MainPage;
import com.fintuity.RegisterPage;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class UseCases {
    static public void registrateUser(WebDriver driver, String name, String surname, String email){
        driver.navigate().to("https://fintuity.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = mainPage.clickGetStarted();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //UserProfile user = UserProfile.createUserWithRandomNameAndSurname();
        UserProfile user = new UserProfile(name,surname,email);
        CongratsPage congratsPage = registerPage.register(user);
        //congratsPage.clickSendLinkAgainButton();
    }

    static public void registrateUser(WebDriver driver,UserProfile up){
        registrateUser(driver,up.getName(),up.getSurname(),up.getEmail());
    }
}
