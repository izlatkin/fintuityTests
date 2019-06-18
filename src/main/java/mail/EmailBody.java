package mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class EmailBody extends EmailGetnada{

    private WebDriver driver;
    private By confirmYourEmail  = By.xpath("//a[contains(text(),'Confirm your email')]");


    public EmailBody(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public boolean isTextPresent(String text){
        List<WebElement> list = driver.findElements(By.xpath("//a[contains(text(),'" + text + "')]"));
        return list.size() > 0;
    }

    public void clickConfirm(){
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.presenceOfElementLocated(confirmYourEmail));
        System.out.println("Click on : " + confirmYourEmail.toString());
        String link = driver.findElement(confirmYourEmail).getAttribute("href");
        System.out.println(link);
    }
}
