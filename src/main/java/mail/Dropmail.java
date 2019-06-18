package mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Dropmail {
    private WebDriver driver;
    private By email = By.xpath("//span[contains(@class, \"email\")]");
    private By confirmYourEmail = By.xpath("//*[contains(text(),'Confirm your email')]");
    private By confirmation = By.xpath("//*[contains(text(),'Fintuity email confirm')]");


    public Dropmail(WebDriver driver) {
        this.driver = driver;
    }

    public String getEmail(){
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


    public boolean isTextPresent(String text) {
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
        return list.size() > 0;
    }

    public void waitForElement(String text) {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'" + text + "')]")));

    }

    public boolean isEmpty() {
        return isTextPresent("Nothing received yet");
    }

    public void openConfirmeEmail() {
        System.out.println("Click on : " + confirmation.toString());
        driver.findElement(confirmation).click();
    }

    public void clickOnEmail() {
        System.out.println("Click on : " + confirmation.toString());
        driver.findElement(confirmation).click();
    }

    public void confirmEmail() {
        System.out.println("Click on : " + confirmYourEmail.toString());
        driver.findElement(confirmYourEmail).click();
    }

    public void clickOnText(String text){
        System.out.println("click on email with: " + text);
        driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]")).click();
    }

    public String findConfirmLink(){
        String confirmLink = "";
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        for(WebElement link:allLinks){
            String tmp = link.getAttribute("href");
            System.out.println(link.getText() + " - " );
            if (tmp != null && tmp.indexOf("confirm-account") > 0)
                confirmLink = tmp;
        }
        return confirmLink;
    }
}