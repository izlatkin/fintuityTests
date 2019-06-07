package com.fintuity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.UserProfile;

public class RegisterPage {
    private WebDriver driver;

    private By signInButton = By.xpath("//a[text()='SIGN IN']");
    private By getStarted = By.xpath("//a[text()='Get Started']");

    private By nameField = By.id("register_field_forename");
    private By surnameFienld = By.id("register_field_surname");
    private By emailField = By.id("register_field_email");
    private By regCheckBox = By.className("input-span");

    private By registerButton = By.xpath("//button[contains(text(),\'Register\')]");
    private By loginButton = By.xpath("//a[text()='Login']");
    private By invalidEmail = By.xpath("//p[text()='Invalid email']");
    private By loginExist = By.xpath("//div[text()='Login already registered']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage clickLogin(){
        System.out.println("login: ");
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }

    public LoginPage clickSignIn() {
        System.out.println("clickSignIn: ");
        driver.findElement(signInButton).click();
        return new LoginPage(driver);
    }

    public CongratsPage clickRegister() {
        System.out.println("clickRegister");
        driver.findElement(registerButton).click();
        return new CongratsPage(driver);
    }

    public void typeName(String name) {
        System.out.println("type Name:" + name);
        driver.findElement(nameField).sendKeys(name);
    }

    public void typeSurname(String surname) {
        System.out.println("type surname:" + surname);
        driver.findElement(surnameFienld).sendKeys(surname);
    }

    public void typeEmail(String email) {
        System.out.println("type email:" + email);
        driver.findElement(emailField).sendKeys(email);
    }

    public void clickRegCheckBox() {
        System.out.println("clickRegCheckBox");
        driver.findElement(regCheckBox).click();
    }

    public CongratsPage register(UserProfile up){
        return register(up.getName(),up.getSurname(),up.getEmail());
    }

    public CongratsPage register(String name, String surname, String email) {
        System.out.println("register: ");
        this.typeName(name);
        this.typeSurname(surname);
        this.typeEmail(email);
        this.clickRegCheckBox();
        return this.clickRegister();
    }
}