package docuSign;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DocuSign {
    //TestLink
    //https://demo.docusign.net/Member/EmailStart.aspx?a=7b8235e5-4c8d-490a-b494-d0f9d343ecc0&acct=1615617e-01c0-4718-aff9-07255fdc6336&er=5726b452-50ad-4646-b898-e12f539f55c5&espei=7a5e3a47-6d1d-4203-9a58-681f0856e235
    private WebDriver driver;
    private By continueButton  = By.xpath("//button[contains(@id,'action-bar-btn-continue')]");
    private By continueDialogButton  = By.xpath("//button[contains(@track-ds,'signingCompleteContinue')]");
    private By okButton  = By.xpath("//button[contains(@id,'comments-tooltip-btn-ok')]");
    private By signElement =  By.xpath("//*[contains(@class,'tab-image tab-image-for-signature')]");
    private By adoptAndSign =  By.xpath("//button[contains(text(),'Adopt and Sign')]");
    private By finishButton =  By.xpath("//button[contains(@id,'action-bar-btn-finish')]");
    private By checkBox =  By.xpath("//input[contains(@class,'cb_input')]");

    public DocuSign(WebDriver driver){
        this.driver = driver;
    }

    public void clickContinue(){
        WebElement nextElement = driver.findElement(continueButton);
        nextElement.click();
    }

    public void waitForElement(String text) {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'" + text + "')]")));

    }

    public void waitForText(String text) {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'" + text + "')]")));

    }

    public boolean isTextPresent(String text){
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
        return list.size() > 0;
    }

    public boolean isTextPresentAndDisplayed(String text){
        By el = By.xpath("//*[contains(text(),'" + text + "')]");
        List<WebElement> list = driver.findElements(el);
        if (list.size() > 0)
            return driver.findElement(el).isDisplayed();
        else
            return false;
    }

    public void clickOnText(String text){
        System.out.println("click on email with: " + text);
        driver.findElement(By.xpath("//span[contains(@class,'bog')]/*[contains(text(),'" + text + "')]")).click();
    }

    public void clickOk() {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(okButton));
    }

    public void clickSign() {
        WebElement signEl = driver.findElement(signElement);
        signEl.click();
    }

    public void clickAdoptAndSign() {
        WebElement element = driver.findElement(adoptAndSign);
        element.click();
    }

    public void clickFinish() {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(finishButton));
    }

    public void clickDialogCuntinue() {
        WebElement element = driver.findElement(continueDialogButton);
        element.click();
    }

    public void clickCheckBox() {
        WebElement element = driver.findElement(checkBox);
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkBox));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(checkBox));
    }
}
