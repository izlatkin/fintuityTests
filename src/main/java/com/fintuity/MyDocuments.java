package com.fintuity;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class MyDocuments extends FintuityPage {
    private By myProfileTitle = By.className("ibox-title");
    //classname = envelope-badge
    private By notificationSign = By.xpath("//*[contains(@class, 'envelope-badge')]");
    private By notificationElement = By.xpath("//*[contains(@class, 'nav-link notifications-toggle dropdown-toggle toolbar-icon')]");
    private By followingLink = By.xpath("//a[contains(text(),'Follow')]");
    private By uploadButton = By.xpath("//button[contains(text(),'Upload')]");
    //addFile
    private By filePath = By.xpath("//input[@type='file']");


    public MyDocuments(WebDriver driver) {
        super(driver);
    }

    public void downloadOriginalDocument(String fileName){
        By tdTmp = By.xpath("//td[./span[contains(text(),'" + fileName +
                "')]]/following-sibling::node()/div[contains(@class,'dropdown')]/ancestor::*[position()=1]");
//        By tmpMenu = By.xpath("//td[./span[contains(text(),'" + fileName +
//                "')]]/following-sibling::node()/div[contains(@class,'dropdown')]");
        //ToDo find diff selector. This is a temporary one
        By tmpMenu = By.cssSelector("body > app-root > app-page-wrapper > div > " +
                "div.tab-pane.active > div > fin-client-documents > div > fin-steps > " +
                "div > div:nth-child(2) > fin-documents > " +
                "fin-wrapper-of-page-with-form-or-table > div > div > " +
                "fin-wrapper-ibox > div > div.ibox-body > app-table:nth-child(3) > " +
                "div > table > tbody > tr > td:nth-child(7) > button");
//        By Send = By.xpath("//td[./span[contains(text(),'" + fileName +
//                "')]]/following-sibling::node()/div[contains(@class,'dropdown')]/button[1]");
        //ToDo find diff selector. This is a temporary one
        By downloadOriginalDocument = By.xpath("//td[./span[contains(text(),'" + fileName + "')]]/following-sibling::node()/div[contains(@class,'dropdown')]/button[1]");
        System.out.println("click td  " + tdTmp.toString());
        driver.findElement(tdTmp).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("click dropdown menu " + tmpMenu.toString());
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(tmpMenu));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("downloadOriginalDocument " + downloadOriginalDocument.toString());
        //driver.findElement(downloadOriginalDocument).click();
        executor.executeScript("arguments[0].click();", driver.findElement(downloadOriginalDocument));
        //ToDo check Downloading
    }

    public void uploadSignedDocument(String fileName){
        By tdTmp = By.xpath("//td[./span[contains(text(),'" + fileName +
                "')]]/following-sibling::node()/div[contains(@class,'dropdown')]/ancestor::*[position()=1]");
//        By tmpMenu = By.xpath("//td[./span[contains(text(),'" + fileName +
//                "')]]/following-sibling::node()/div[contains(@class,'dropdown')]");
        //ToDo find diff selector. This is a temporary one
        By tmpMenu = By.cssSelector("body > app-root > app-page-wrapper > div > " +
                "div.tab-pane.active > div > fin-client-documents > div > fin-steps > " +
                "div > div:nth-child(2) > fin-documents > " +
                "fin-wrapper-of-page-with-form-or-table > div > div > " +
                "fin-wrapper-ibox > div > div.ibox-body > app-table:nth-child(3) > " +
                "div > table > tbody > tr > td:nth-child(7) > button");
//        By Send = By.xpath("//td[./span[contains(text(),'" + fileName +
//                "')]]/following-sibling::node()/div[contains(@class,'dropdown')]/button[1]");
        //ToDo find diff selector. This is a temporary one
        //dropdown-item
        ////td[./span[contains(text(),'Docs221')]]/following-sibling::node()/div[contains(@class,'dropdown-item')]/ancestor::*[position()=1]
        By uploadSignedDocument = By.xpath("//td[./span[contains(text(),'" + fileName + "')]]/following-sibling::node()/div[contains(@class,'dropdown')]/button[2]");
        System.out.println("click td  " + tdTmp.toString());
        driver.findElement(tdTmp).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("click dropdown menu " + tmpMenu.toString());
        driver.findElement(tmpMenu).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("uploadSignedDocument " + uploadSignedDocument.toString());
        driver.findElement(uploadSignedDocument).click();
        //ToDo check Downloading
    }

    public void downloadOriginalDocumentAndUploadSignedDocument(String fileName){
        By tdTmp = By.xpath("//td[./span[contains(text(),'" + fileName +
                "')]]/following-sibling::node()//button[contains(@class,'dropdown')]");
        //ToDo find diff selector. This is a temporary one
        By downloadOriginalDocument = By.xpath("//td[./span[contains(text(),'" + fileName + "')]]/following-sibling::node()//button[contains(text(),'Download')]");
        By uploadSignedDocument = By.xpath("//td[./span[contains(text(),'" + fileName + "')]]/following-sibling::node()//*[contains(text(),'Upload Signed Document')]");
        System.out.println("click td  " + tdTmp.toString());
        driver.findElement(tdTmp).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        System.out.println("downloadOriginalDocument " + downloadOriginalDocument.toString());
        executor.executeScript("arguments[0].click();", driver.findElement(downloadOriginalDocument));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println("uploadSignedDocument " + downloadOriginalDocument.toString());
        //driver.findElement(downloadOriginalDocument).click();
        executor.executeScript("arguments[0].click();", driver.findElement(uploadSignedDocument));
        //ToDo check Downloading
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
}
