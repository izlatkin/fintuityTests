package backoffice;

import com.fintuity.FintuityPage;
import com.fintuity.LoginPage;
import com.fintuity.MyProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminMainPage extends FintuityPage {
    private By signInButton = By.xpath("//a[text()='SIGN IN']");

    //btn btn-warning btn-block font-bold
    private By open = By.xpath("//*[@class='nav-link active nav__button_active']");
    private By inProgress = By.xpath("//*[@class='nav-link active'][text()=' In Progress ']");
    private By closed = By.xpath("//*[@class='nav-link active'][text()=' Closed ']");
    private By createdNewTask = By.xpath("//*[@class='btn btn-warning text-white']");

    private By emailField = By.id("login_field_email");
    private By password = By.id("login_field_password");
    private By loginButton = By.xpath("//a[text()='Login']");
    private By loginLable = By.xpath("//a[text()='Log In']");
    //div[contains(@class,'alert alert-danger')]
    private By error = By.xpath("//div[contains(@class,'alert alert-danger')]");

    public AdminMainPage(WebDriver driver){
        super(driver);
    }

    public void clickLogin(){
        System.out.println("click Open: " + open.toString());
        driver.findElement(open).click();
    }

    public void clickInProgress(){
        System.out.println("click InProgress: " + inProgress.toString());
        driver.findElement(inProgress).click();
    }

    public void clickClosed(){
        System.out.println("click Closed: " + closed.toString());
        driver.findElement(closed).click();
    }

    public void clickCreateNewTask(){
        System.out.println("click Create new task: " + createdNewTask.toString());
        driver.findElement(createdNewTask).click();
    }



}
