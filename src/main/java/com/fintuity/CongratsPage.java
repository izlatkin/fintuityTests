package com.fintuity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CongratsPage extends FintuityPage{

    //Eaxample:https://portal.fintuity.com/send-link/resend-account-confirmation/congrats?login=test_email@mail.ru

    //Congrats!
    //You have successfully created account in the system.
    // A link has been sent to your email address.
    // To activate your account, please follow the link in the letter

    private WebDriver driver;
    private By sendLinkAgainButton = By.xpath("//*[@class=\"btn btn-warning text-white\"]");
            //By.xpath("//a[text()='Send Link Again']");
    public CongratsPage(WebDriver driver){
        super(driver);
    }

    public ResendPage clickSendLinkAgainButton(){
        driver.findElement(sendLinkAgainButton).click();
        return new ResendPage(driver);
    }



}
