package backoffice;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class UserInfoPage extends AdminMainPage{

    private By documentsTab = By.xpath("//*[contains(@data-toggle,'tab') and contains(text(),'Documents')]");
    private By generateUploadNewDocumentsButton = By.xpath("//button[contains(text(),'Generate')]");
    private By nameDocumentOperation = By.id("uploadDocumentDialogForm_field_name");
    //private By typeDocumentOperation = By.id("uploadDocumentDialogForm_field_type");
    //private By downloadUploadRadioButton = By.xpath("//label[contains(text(),'Download Upload')]/input/following::span");
    private By downloadUploadRadioButton = By.xpath("//*[contains(text(),'Download Upload')]");
//    private By downloadUploadRadioButton = By.cssSelector("body > fin-modal > div > div > div > " +
//            "fin-upload-document-dialog > fin-wrapper-ibox > div > div.ibox-body > " +
//            "div > fin-dynamic-form > form > fin-radiobutton-field > div > div > div:nth-child(3) > label");
    private By docuSignRadioButton = By.xpath("//label[contains(text(),'Docu Sign')]/input");
    private By uploadDocumentDialogFormButton = By.id("uploadDocumentDialogForm_field_file");
    private By filePath = By.xpath("//input[@type='file']");
    private By uploadButton = By.xpath("//button[text()=' Upload ']");
    private By sendButton = By.xpath("//*[contains(text(),'Send')]");
    private By yesButton = By.xpath("//*[contains(text(),'Yes')]");

    public UserInfoPage(WebDriver driver) {
        super(driver);
    }

    public void clickTab(String tab){
        System.out.println("click Tab: " + tab);
        By tmpTab = By.xpath("//*[contains(@data-toggle,'tab') and contains(text(),'" + tab + "')]");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(tmpTab)).click().build().perform();
    }

    public void clickGenerateUploadNewDocumentsButton(){
        System.out.println("click generate Upload New Documents Button: " + generateUploadNewDocumentsButton.toString());
//        Actions actions = new Actions(driver);
//        actions.moveToElement(driver.findElement(generateUploadNewDocumentsButton)).click().build().perform();
        WebDriverWait wait2 = new WebDriverWait(driver, 10);
        wait2.until(ExpectedConditions.elementToBeClickable(generateUploadNewDocumentsButton));
        driver.findElement(generateUploadNewDocumentsButton).click();
    }

    public void typeDocumentName(String name){
        System.out.println("type name: " + name);
        driver.findElement(nameDocumentOperation).sendKeys(name);
    }

    public void selectDocumentType(String type){
        System.out.println("select name: " + type);
        By tmpType = By.xpath("//*[contains(@id,'uploadDocumentDialogForm_field_type')]/option[contains(text(),'"
                + type + "')]");
        driver.findElement(tmpType).click();
    }

    public void selectRadioButton(String type){
        if (type.equals("Download Upload"))
        {
            System.out.println("select Download Upload Radio Button" + downloadUploadRadioButton.toString());
            JavascriptExecutor jse2 = (JavascriptExecutor)driver;
            jse2.executeScript("arguments[0].scrollIntoView()", driver.findElement(downloadUploadRadioButton));
            //driver.findElement(downloadUploadRadioButton).click();
            //Actions actions = new Actions(driver);
            //actions.moveToElement(driver.findElement(downloadUploadRadioButton)).click().build().perform();
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", driver.findElement(downloadUploadRadioButton));
        }else if(type.equals("Docu Sign"))
        {
            System.out.println("select Docu Sign Radio Button");
            //Actions actions = new Actions(driver);
            //actions.moveToElement(driver.findElement(docuSignRadioButton)).click().build().perform();
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", driver.findElement(docuSignRadioButton));
        }else{
            System.out.println("Incorrect RadioButton value");
        }
    }

    public void clickUploadDocumentDialogFormButton(){
        System.out.println("click Upload Document Dialog Form Button");
        driver.findElement(uploadDocumentDialogFormButton).click();
    }

    public void setFilePath(String path){
        WebElement element = driver.findElement(filePath);
        element.sendKeys(path);
    }

    public void clickUpload(){
        System.out.println("click upload" );
        //driver.findElement(uploadButton).click();
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(uploadButton));
    }

    public void sentFile(String fileName){
        By tdTmp = By.xpath("//td[./span[contains(text(),'" + fileName +
                        "')]]/following-sibling::node()//button[contains(@class,'dropdown')]");
        ///div[contains(@class,'dropdown')]/ancestor::*[position()=1]
//        By tmpMenu = By.xpath("//td[./span[contains(text(),'" + fileName +
//                "')]]/following-sibling::node()/div[contains(@class,'dropdown')]");
        //ToDo find diff selector. This is a temporary one
        By tmpMenu = By.cssSelector("body > app-root > fin-admin > div.content-wrapper " +
                "> fin-main > fin-main-steps-component > fin-steps > div > " +
                "div:nth-child(2) > fin-client-documents > div > fin-steps > div >" +
                " div.flex-grow-1 > fin-documents > fin-wrapper-of-page-with-form-or-table > " +
                "div > div > fin-wrapper-ibox > div > div.ibox-body > app-table:nth-child(3) >" +
                " div > table > tbody > tr > td:nth-child(7) > fin-table-actions-button > " +
                "div > button.btn.btn-outline-primary.dropdown-toggle.dropdown-arrow.p-0.pl-1.pr-1.ng-star-inserted");
//        By Send = By.xpath("//td[./span[contains(text(),'" + fileName +
//                "')]]/following-sibling::node()/div[contains(@class,'dropdown')]/button[1]");
        //ToDo find diff selector. This is a temporary one
        By Send = By.cssSelector("body > app-root > fin-admin > div.content-wrapper > " +
                "fin-main > fin-main-steps-component > fin-steps > div > div:nth-child(2) >" +
                " fin-client-documents > div > fin-steps > div > div.flex-grow-1 >" +
                " fin-documents > fin-wrapper-of-page-with-form-or-table > div > div > " +
                "fin-wrapper-ibox > div > div.ibox-body > app-table:nth-child(3) > div > " +
                "table > tbody > tr > td:nth-child(7) > fin-table-actions-button >" +
                " div > div > button:nth-child(1)");
        System.out.println("click td  " + tdTmp.toString());
        try {
            driver.findElement(tdTmp).click();
        }catch (org.openqa.selenium.StaleElementReferenceException ex){
            driver.findElement(tdTmp).click();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("click dropdown menu " + tmpMenu.toString());
        //driver.findElement(tmpMenu).click();
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(tmpMenu));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("click send " + Send.toString());
        //driver.findElement(Send).click();
        executor.executeScript("arguments[0].click();", driver.findElement(Send));
        waitForElement("Do you want to send selected document?");
        clickYes();
    }

    public void clickSend(){
        ////td[./span[contains(text(),'Docs840')]]/following-sibling::node()/div[contains(@class,'dropdown')]
        System.out.println("click send " + sendButton.toString());
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(sendButton)).click().build().perform();
    }

    public void clickYes(){
        ////td[./span[contains(text(),'Docs840')]]/following-sibling::node()/div[contains(@class,'dropdown')]
        System.out.println("click Yes " + yesButton.toString());
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(yesButton)).click().build().perform();
    }
}
