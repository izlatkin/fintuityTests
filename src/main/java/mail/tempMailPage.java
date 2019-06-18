package mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.List;

public class TempMailPage {
    private WebDriver driver;

    private By email = By.xpath("//input[@id='mail']");
    private By lastEmail = By.xpath("//div[@class=\\'inboxWarpMain\\']");
    private By confirmation = By.xpath("//a[contains(text(),'Fintuity email confirm')]");
    private HashSet<WebElement> emails = new HashSet<WebElement>();

    private By refresh = By.id("click-to-refresh");

    public TempMailPage(WebDriver driver){
        this.driver = driver;
    }

    public String getEmail(){
        return driver.findElement(email).getAttribute("value");
    }

    public TempMailPage refresh(){
        driver.findElement(refresh).click();
        return new TempMailPage(driver);
    }

    public EmailBody clickLastEmail(){
        driver.findElement(lastEmail).click();
        return new EmailBody(this.driver);
    }

    public boolean isEmpty(){
        return driver.findElements(By.linkText("Your inbox is empty")).size() < 1;
    }

    public boolean isTextPresent(String text){
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
        return list.size() > 0;
    }

    public void clickOnText(String text){
        System.out.println("click on email with: " + text);
        driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]")).click();
    }

    public void clickConfirm(){
        System.out.println("click on confirm email  " );
        driver.findElement(confirmation).click();
    }

    public void waitForElement(String text){
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'" + text + "')]")));

    }




}
