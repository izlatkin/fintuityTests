package com.fintuity;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class BookConsultation extends FintuityPage{

    //private By nextButton = By.xpath("//button[text()='Next']");
    private By nextButton = By.xpath("//button/span[contains(text(),'Next')]");
    //next Date day-calendar-container_next
    private By nextDate = By.xpath("//div[starts-with(@class,'day-calendar-container_next')]");
    private By submitButton = By.xpath("//button/span[contains(text(),'Submit')]");
    //private By checkBoxes = By.className("input-span");
    //private By checkBoxes = By.xpath("//*[contains(@class, 'topic_input-span')]");
    //private By checkBoxes = By.xpath("//input[@type='checkbox']");    //ratio-box_content day_number active
    private By checkBoxes = By.xpath("//label[contains(.//input[@type='checkbox'], '')]");
    private By selectDates = By.xpath("//div[starts-with(@class,'ratio-box_content')]");
    private By selectTime = By.xpath("//div[starts-with(@class,'time_item')]");
    private By phoneField = By.id("clientPhoneForm_field_phone");


    public void selectTopics() {
        Actions actions = new Actions(driver);
        List<WebElement> checkBoxesList =  driver.findElements(checkBoxes);
        for (WebElement cb:checkBoxesList) {
            System.out.println(cb.getText());
            //cb.click();
            actions.moveToElement(cb).click().build().perform();
        }
    }

    public void clickNextDate(){
        Actions actions = new Actions(driver);
        System.out.println("click next Date");
        //driver.findElement(nextDate).click();
        actions.moveToElement(driver.findElement(nextDate)).click().build().perform();
    }

    public BookConsultation(WebDriver driver) {
        super(driver);
    }

    public void clickNext(){
        System.out.println("click button Set New Password");
        driver.findElement(nextButton).click();
    }

    public void clickSubmit(){
        System.out.println("click Submit");
        driver.findElement(submitButton).click();
    }

    public void printCheckBoxes(){
        List<WebElement> checkBoxesList =  driver.findElements(checkBoxes);
        for (WebElement cb:checkBoxesList) {
            System.out.println(cb.getText());
            cb.click();
        }
    }

    public void clickRandomDate(){
        List<WebElement> dates =  driver.findElements(selectDates);
        double randomDouble = Math.random();
        randomDouble = randomDouble * (dates.size() - 1);
        int randomDate = (int) randomDouble;
        dates.get(randomDate).click();
        if (isTextPresent("No available time in the day")){
            clickNextDate();
            clickRandomDate();
        }
    }

    public void clickRandomTime(){
        List<WebElement> times =  driver.findElements(selectTime);
        double randomDouble = Math.random();
        randomDouble = randomDouble * (times.size() - 1);
        int randomDate = (int) randomDouble;
        times.get(randomDate).click();
    }

    public void setPhone(String phone){
        System.out.println("type phone :" + phone);
        driver.findElement(phoneField).sendKeys(phone);
    }

}
