package com.fintuity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RestorePage extends FintuityPage {

    private By emailField = By.id("restorePassword_field_email");
    private By restoreButton = By.xpath("//button[contains(text(),'Restore')]");

    public RestorePage(WebDriver driver){
        super(driver);
    }

    public void typeEmail(String email){
        System.out.println("type email: " + email);
        driver.findElement(emailField).sendKeys(email);
    }

    public CongratsPage clickRestore(){
        driver.findElement(restoreButton).click();
        return new CongratsPage(driver);
    }
}
