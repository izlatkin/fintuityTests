package org.tempmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class tempMailPage {
    private WebDriver driver;

    private By email = By.xpath("//input[@id=\\'mail\\']");
    private By lastEmail = By.xpath("//div[@class=\\'inboxWarpMain\\']");

    private By refresh = By.id("click-to-refresh");

    public tempMailPage(WebDriver driver){
        this.driver = driver;
    }

    public String getEmail(){
        return driver.findElement(email).getText();
    }

    public tempMailPage refresh(){
        driver.findElement(refresh).click();
        return new tempMailPage(driver);
    }

    public tempMain_EmailBody clickLastEmail(){
        driver.findElement(lastEmail).click();
        return new tempMain_EmailBody(this.driver);
    }

}
