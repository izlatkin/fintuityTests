package com.fintuity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RequestCall extends FintuityPage{

    private By requestCallBack = By.xpath("//button[text()=' Request Call Back ']");
    private By checkBoxes = By.className("input-span");
    private By phoneField = By.id("providePhoneNumber_field_phone");

    public RequestCall(WebDriver driver) {
        super(driver);
    }


    public void clickRequestCallBack(){
        System.out.println("click requestCallBack");
        driver.findElement(requestCallBack).click();
    }

    public void printCheckBoxes(){
        List<WebElement> checkBoxesList =  driver.findElements(checkBoxes);
        for (WebElement cb:checkBoxesList) {
            System.out.println(cb.getText());
            cb.click();
        }
    }

    public void setPhone(String phone){
        System.out.println("type phone :" + phone);
        driver.findElement(phoneField).sendKeys(phone);
    }


}
