package com.fintuity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.UserProfile;

public class LoginPage extends FintuityPage{

    private By signInButton = By.xpath("//a[text()='SIGN IN']");
    private By getStarted = By.xpath("//a[text()='Get Started']");

    //btn btn-warning btn-block font-bold
    private By loginYellow = By.xpath("//button[@class=\"btn btn-warning btn-block font-bold\"]");

    private By emailField = By.id("login_field_email");
    private By password = By.id("login_field_password");
    private By loginButton = By.xpath("//a[text()='Login']");
    private By loginLable = By.xpath("//a[text()='Log In']");
    //div[contains(@class,'alert alert-danger')]
    private By error = By.xpath("//div[contains(@class,'alert alert-danger')]");

    private By restore = By.xpath("//*[text()=' Restore it ']");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void clickSignIn(){
        driver.findElement(signInButton).click();
    }

    public MyProfilePage clickLogin(){
        System.out.println("click loginYellow: " + loginYellow.toString());
        driver.findElement(loginYellow).click();
        return new MyProfilePage(driver);
    }

    public LoginPage typeEmail(String email){
        System.out.println("type email: " + email);
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public void clearEmailField(){
        System.out.println("clear email filed ");
        driver.findElement(emailField).clear();
    }

    public LoginPage typePassword(String pwd){
        System.out.println("type password: " + pwd);
        driver.findElement(password).sendKeys(pwd);
        return this;
    }

    public void clearPasswordField(){
        System.out.println("clear password filed ");
        driver.findElement(password).clear();
    }

    public void clearAllFields(){
        System.out.println("clear all fileds ");
        clearEmailField();
        clearPasswordField();
    }

    public MyProfilePage clickRegister(){
        System.out.println("click : " + loginButton.toString());
        driver.findElement(loginButton).click();
        return new MyProfilePage(driver);
    }

    public RestorePage clickRestore(){
        System.out.println("click : " + restore.toString());
        driver.findElement(restore).click();
        return new RestorePage(driver);
    }

    public WebElement pageLable(){
        return driver.findElement(loginLable);
    }

    public String getErrorText(){
        return driver.findElement(error).getText();
    }

    public LoginPage loginWithIncorrectCreds(String username, String password){
        this.typeEmail(username);
        this.typePassword(password);
        this.clickLogin();
        return new LoginPage(driver);
    }

    public MyProfilePage login(UserProfile user){
        this.typeEmail(user.getEmail());
        this.typePassword(user.getPassword());
        return this.clickLogin();
    }



    public MyProfilePage loginCorrect(String username, String password){
        this.typeEmail(username);
        this.typePassword(password);
        return this.clickLogin();
    }
}
