package com.fintuity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RequestCall {
    private WebDriver driver;

    private By title = By.xpath("/html/head/title");
    private By requestCallBack = By.xpath("//button[text()=' Request Call Back ']");
    private By checkBoxes = By.className("input-span");
    private By phoneField = By.id("providePhoneNumber_field_phone");

    public RequestCall(WebDriver driver){
        this.driver = driver;
    }


    public String getTitle(){
        return driver.findElement(title).getText();
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

    public boolean isTextPresent(String text){
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
        return list.size() > 0;
    }

    public void waitForElement(String text) {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'" + text + "')]")));

    }
}
