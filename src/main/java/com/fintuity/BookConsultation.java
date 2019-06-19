package com.fintuity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BookConsultation extends FintuityPage{

    private By nextButton = By.xpath("//button[text()='Next']");
    private By checkBoxes = By.className("input-span");

    public BookConsultation(WebDriver driver) {
        super(driver);
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

}
