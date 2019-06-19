package com.fintuity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResendPage extends FintuityPage{
    //Example https://portal.fintuity.com/send-link/resend-account-confirmation/congrats?login=test_email@mail.ru
    //Success!
    //A link has been sent to your email address.
    // To activate your account, please follow the link in the letter.

    private By sendLinkAgainButton = By.xpath("//a[text()='Send Link Again']");
    public ResendPage(WebDriver driver){
        super(driver);
    }

    public void clickSendLinkAgain(){
        driver.findElement(sendLinkAgainButton).click();
    }
}
