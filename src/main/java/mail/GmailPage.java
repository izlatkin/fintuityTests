package mail;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GmailPage {
    private WebDriver driver;

    private By email  = By.xpath("//input[contains(@type,'email')]");
    private By next  = By.xpath("//*[contains(@class,'RveJvd snByac')]");
    private By autorize  = By.xpath("//*[contains(@class,'RveJvd snByac')]");
    private By login = By.xpath("//a[contains(@ga-event-action,'sign in')]");
    private By passwordField = By.xpath("//input[contains(@type,'password')]");
    private By inbox =  By.xpath("//*[contains(@class,'J-Ke n0')]");
    private By checkBoxEmail = By.xpath("//span[contains(@class,'T-Jo J-J5-Ji')]");
    private By removeAll = By.xpath("//*[contains(@class,'T-I J-J5-Ji nX T-I-ax7 T-I-Js-Gs mA')]");


    public GmailPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickLogin(){
        WebElement loginElement = driver.findElement(login);
        loginElement.click();
    }

    public void clickNext(){
        WebElement nextElement = driver.findElement(next);
        nextElement.click();
    }

    public void typeEmail(String email_name){
        WebElement emailElement = driver.findElement(email);
        emailElement.sendKeys(email_name);
    }

    public void typePassword(String password){
        //WebElement passwordElement = driver.findElement(passwordField);
        new WebDriverWait(driver, 20).
                until(ExpectedConditions.elementToBeClickable(passwordField)).sendKeys(password);
        //passwordElement.sendKeys(password);
    }

    public boolean isAutorizePresented(){
        return driver.findElements(autorize).size() > 0;
    }


    public void clickAutorize(){
        if (isAutorizePresented()){
            WebElement autorizeElement = driver.findElement(autorize);
            autorizeElement.click();
        }
    }

    public boolean isInboxPresent(){
        return driver.findElements(inbox).size() > 0;
    }

    public void clickInbox(){
        if (isInboxPresent()){
            WebElement autorizeElement = driver.findElement(inbox);
            autorizeElement.click();
        }
    }

    public void login(String email, String password){
        typeEmail(email);
        clickNext();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        typePassword(password);
        clickNext();
        Assert.assertTrue(isInboxPresent());
        clickInbox();
    }

    public void waitForElement(String text) {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(@class,'bog')]/*[contains(text(),'" + text + "')]")));

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

    public void clickOnText(String text){
        System.out.println("click on email with: " + text);
        driver.findElement(By.xpath("//span[contains(@class,'bog')]/*[contains(text(),'" + text + "')]")).click();
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

    public String findDocuSignLink(){
        String docuSignLink = "";
        List<WebElement> allLinks = driver.findElements(By.xpath("//a[contains(@href,'demo.docusign')]"));
        for(WebElement link:allLinks){
            if (link.isDisplayed()) {
                String tmp = link.getAttribute("href");
                if (tmp != null && tmp.indexOf("demo.docusign.net") > 0)
                    docuSignLink = tmp;
            }
        }
        return docuSignLink;
    }

    public void removeAllEmails(){
        System.out.println("select all email " + checkBoxEmail.toString());
        List<WebElement> selectAll = driver.findElements(checkBoxEmail);
        if (selectAll.size() <= 0){
            System.out.println("This is no emails");
            return;
        }
//        Actions actions = new Actions(driver);
//        for (WebElement element:selectAll) {
//            String status = element.getAttribute("aria-checked");
//            if (status.equals("false") & element.isDisplayed())
//                actions.moveToElement(element).click().build().perform();
//        }
        WebElement select = driver.findElement(checkBoxEmail);
        select.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Error with sleep");
        }
        System.out.println("remove all email " + removeAll.toString());
        WebElement remove = driver.findElement(removeAll);
        remove.click();
    }
}
