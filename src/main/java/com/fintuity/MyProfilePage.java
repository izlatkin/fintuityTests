package com.fintuity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyProfilePage extends FintuityPage{
    private By myProfileTitle = By.className("ibox-title");

    public MyProfilePage(WebDriver driver){
        super(driver);
    }


    public String getMyProfileTitle(){
        return driver.findElement(myProfileTitle).getText();
    }
}
