package mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class tempMailPage {
    private WebDriver driver;

    private By email = By.xpath("//input[@id='mail']");
    private By lastEmail = By.xpath("//div[@class=\\'inboxWarpMain\\']");
    private HashSet<WebElement> emails = new HashSet<WebElement>();

    private By refresh = By.id("click-to-refresh");

    public tempMailPage(WebDriver driver){
        this.driver = driver;
    }

    public String getEmail(){
        return driver.findElement(email).getAttribute("value");
    }

    public tempMailPage refresh(){
        driver.findElement(refresh).click();
        return new tempMailPage(driver);
    }

    public tempMain_EmailBody clickLastEmail(){
        driver.findElement(lastEmail).click();
        return new tempMain_EmailBody(this.driver);
    }

    public boolean isEmpty(){
        return driver.findElements(By.linkText("Your inbox is empty")).size() < 1;
    }

    public boolean isReceived(int secondsToWait){
        try {
            Thread.sleep(secondsToWait * 1000);
            refresh();
            return driver.findElements(By.linkText("Your inbox is empty")).size() < 1;
        } catch (InterruptedException e) {
            return false;
        }

    }



}
