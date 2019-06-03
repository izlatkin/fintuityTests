package com.fintuity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By signInButton = By.xpath("//a[text()='SIGN IN']");
    private By getStarted = By.xpath("//a[text()='Get Started']");

    //btn btn-warning btn-block font-bold
    private By loginYellow = By.className("btn btn-warning btn-block font-bold");

    private By emailField = By.id("login_field_email");
    private By password = By.id("login_field_password");
    private By loginButton = By.xpath("//a[text()='Login']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickSignIn(){
        driver.findElement(signInButton).click();
    }

    public MyProfilePage clickLogin(){
        driver.findElement(loginYellow).submit();
        return new MyProfilePage(driver);
    }

    public LoginPage typeEmail(){
        driver.findElement(emailField).sendKeys("zlatkin_ilya@mail.ru");
        return this;
    }

    public LoginPage typePassword(){
        driver.findElement(password).sendKeys("Scaleio123");
        return this;
    }

    public MyProfilePage clickRegister(){
        driver.findElement(loginButton).click();
        return new MyProfilePage(driver);
    }
}
