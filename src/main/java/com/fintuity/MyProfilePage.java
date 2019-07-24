package com.fintuity;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyProfilePage extends FintuityPage{
    private By myProfileTitle = By.className("ibox-title");
    //classname = envelope-badge
    private By notificationSign = By.xpath("//*[contains(@class, 'envelope-badge')]");
    private By notificationElement = By.xpath("//*[contains(@class, 'nav-link notifications-toggle dropdown-toggle toolbar-icon')]");
    private By followingLink = By.xpath("//a[contains(text(),'Follow')]");

    public MyProfilePage(WebDriver driver){
        super(driver);
    }


    public String getMyProfileTitle(){
        return driver.findElement(myProfileTitle).getText();
    }

    public boolean isTextPresent(String text){
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
        return list.size() > 0;
    }

    public boolean isNotificationAvaible(){
        List<WebElement> list = driver.findElements(notificationSign);
        return list.size() > 0;
    }

    public void clickNotificationElement(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(notificationElement));
    }

    public void clickFollow(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(followingLink));
    }
}
