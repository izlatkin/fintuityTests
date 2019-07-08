package com.fintuity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyProfilePage extends FintuityPage{
    private By myProfileTitle = By.className("ibox-title");

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
}
