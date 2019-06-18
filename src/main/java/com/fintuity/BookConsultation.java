package com.fintuity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BookConsultation {
    private WebDriver driver;

    private By title = By.xpath("/html/head/title");
    private By nextButton = By.xpath("//button[text()='Next']");
    private By checkBoxes = By.className("input-span");

    public BookConsultation(WebDriver driver){
        this.driver = driver;
    }


    public String getTitle(){
        return driver.findElement(title).getText();
    }

    public void clickNext(){
        System.out.println("click button Set New Password");
        driver.findElement(nextButton).click();
    }

    public void printCheckBoxes(){
        List<WebElement> checkBoxesList =  driver.findElements(checkBoxes);
        for (WebElement cb:checkBoxesList) {
            System.out.println(cb.getText());
            cb.click();
        }
    }

    public boolean isTextPresent(String text){
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
        return list.size() > 0;
    }
}
