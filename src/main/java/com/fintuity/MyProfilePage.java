package com.fintuity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyProfilePage {

    private WebDriver driver;

    private By title = By.xpath("/html/head/title");
    private By myProfileTitle = By.className("ibox-title");

    public MyProfilePage(WebDriver driver){
        this.driver = driver;
    }


    public String getTitle(){
        return driver.findElement(title).getText();
    }

    public String getMyProfileTitle(){
        return driver.findElement(myProfileTitle).getText();
    }
}
