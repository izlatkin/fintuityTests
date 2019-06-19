package backoffice;

import com.fintuity.FintuityPage;
import com.fintuity.LoginPage;
import com.fintuity.MyProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageBO extends FintuityPage {
    private By signInButton = By.xpath("//a[text()='SIGN IN']");

    //btn btn-warning btn-block font-bold
    private By loginYellow = By.xpath("//button[@class=\"btn btn-warning btn-block font-bold\"]");

    private By emailField = By.id("login_field_email");
    private By password = By.id("login_field_password");
    private By loginButton = By.xpath("//a[text()='Login']");
    private By loginLable = By.xpath("//a[text()='Log In']");
    //div[contains(@class,'alert alert-danger')]
    private By error = By.xpath("//div[contains(@class,'alert alert-danger')]");

    public LoginPageBO(WebDriver driver){
        super(driver);
    }


    public AdminMainPage clickLogin(){
        System.out.println("click loginYellow: " + loginYellow.toString());
        driver.findElement(loginYellow).click();
        return new AdminMainPage(driver);
    }

    public LoginPageBO typeEmail(String email){
        System.out.println("type email: " + email);
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public void clearEmailField(){
        System.out.println("clear email filed ");
        driver.findElement(emailField).clear();
    }

    public LoginPageBO typePassword(String pwd){
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

    public AdminMainPage loginCorrect(String username, String password){
        this.typeEmail(username);
        this.typePassword(password);
        return this.clickLogin();
    }
}
