package com.fintuity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

public abstract class FintuityPage {
    public WebDriver driver;

    private By title = By.xpath("/html/head/title");
    ////*[@class='menu-btn']/img
    private By burgerMenu = By.xpath("//*[@class='menu-btn']/img");
    private By getStarted_1 = By.xpath("//*[@class='getStarted']");
    private By getStarted_2 = By.xpath("//a[text()='Get Started']");

    public FintuityPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isTextPresent(String text){
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
        return list.size() > 0;
    }

    public boolean isBurgerMenuVisible(){
        List<WebElement> list = driver.findElements(burgerMenu);
        if (list.size() == 0)
            return false;
        else
            return driver.findElement(burgerMenu).isDisplayed();
    }

    public void clickBurgerMenu(){
        driver.findElement(burgerMenu).click();
    }

    public boolean isLinkPresent(String text){
        List<WebElement> list = driver.findElements(By.xpath("//a[contains(text(),'" + text + "')]"));
        return list.size() > 0;
    }

    public void waitForElement(String text) {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'" + text + "')]")));

    }

    public RegisterPage clickGetStarted(){
        if (this.isBurgerMenuVisible())
            clickBurgerMenu();
        if (driver.findElements(getStarted_1).size() > 0)
            driver.findElement(getStarted_1).click();
        else if (driver.findElements(getStarted_2).size() > 0)
            driver.findElement(getStarted_2).click();
        return new RegisterPage(driver);
    }

    public String getTitle(){
        return driver.findElement(title).getText();
    }
}
