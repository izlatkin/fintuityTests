package com.fintuity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;

    private By signInButton = By.xpath("//a[text()='SIGN IN']");
    private By getStarted = By.xpath("//a[text()='Get Started']");

    private By nameField = By.id("register_field_forename");
    private By surnameFienld = By.id("register_field_surname");
    private By emailField = By.id("register_field_email");
    private By regCheckBox = By.className("input-span");

    private By registerButton = By.xpath("//a[text()='Register']");
    private By loginButton = By.xpath("//a[text()='Login']");

    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    public LoginPage clickSignIn(){
        driver.findElement(signInButton).click();
        return new LoginPage(driver);
    }

    public CongratsPage clickRegister(){
        driver.findElement(registerButton).submit();
        return new CongratsPage(driver);
    }

    public void typeName(String name){
        driver.findElement(nameField).sendKeys(name);
    }

    public void typeSurname(String surname){
        driver.findElement(surnameFienld).sendKeys(surname);
    }

    public void typeEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    public void clickRegCheckBox(){
        driver.findElement(regCheckBox).click();
    }

    public CongratsPage register(String name, String surname, String email){
        this.typeName(name);
        this.typeSurname(surname);
        this.typeEmail(email);
        this.clickRegCheckBox();
        return this.clickRegister();
    }
}
