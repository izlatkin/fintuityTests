package org.tempmail;

import com.fintuity.PasswordSetting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class tempMain_EmailBody {
    private WebDriver driver;

    private By confirmButton = By.xpath("//a[text()='Confirm your email']");

    public tempMain_EmailBody(WebDriver driver){
        this.driver = driver;
    }

    public PasswordSetting clickConfirm(){
        driver.findElement(confirmButton).click();
        return new PasswordSetting(driver);
    }


}
