package mail;

import com.fintuity.PasswordSetting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class EmailGetnada {
    private WebDriver driver;

    //@FindBy(xpath = "//span[@class=\"address what_to_copy\"")
    //@FindBy(xpath = "//h1/span[2]")
//    @FindBy(xpath = "//*[contains(@class, \"address what_to_copy\")]")
//    private WebElement email;
//
//    @FindBy(xpath = "//*[text()='Inbox empty ... '")
//
//    private WebElement isEmpty;
//    @FindBy(xpath = "//ul[@class=\"msg_list\"]")
//    private List<WebElement> messageList;
    private By email  = By.xpath("//*[contains(@class, \"address what_to_copy\")]");
    private By confirmYourEmail  = By.xpath("//a[contains(text(),'Confirm your email')]");


    public EmailGetnada(WebDriver driver){
        this.driver = driver;
    }

    public String getEmail(){
        //return email.getText();
        //return driver.findElement(email).getText();
        //return driver.findElement(By.xpath("//span[contains(@class, \"address what_to_copy\")]")).getText();
        try {
            WebElement emailElement = driver.findElement(email);
            return emailElement.getText();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            WebElement emailElement = driver.findElement(email);
            return emailElement.getText();
        }
    }

    public boolean isTextPresent(String text){
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
        return list.size() > 0;
    }

    public void clickOnEmail(String text){
        System.out.println("click on email with: " + text);
        //driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]")).click();
        driver.findElement(By.xpath("//li//div[contains(text(),'" + text + "')]")).click();
        //li//div[contains(text(),"Fintuity email")]
        //return new EmailBody(driver);
    }

    public void waitForElement(String text){
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'" + text + "')]")));

    }

    public boolean isEmpty(){
        return isTextPresent("Inbox empty ... messages");
    }

    public void clickConfirm(){
        //WebDriverWait wait = new WebDriverWait(driver, 100);
        //wait.until(ExpectedConditions.presenceOfElementLocated(confirmYourEmail));
        System.out.println("Click on : " + confirmYourEmail.toString());
        String link = driver.findElement(confirmYourEmail).getAttribute("href");
        System.out.println(link);
    }
}
