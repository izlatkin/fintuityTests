package com.fintuity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectTopicPage extends FintuityPage{


    private By congratulationMessage = By.xpath("//div[text()='You have successfully activated your account']");
    public SelectTopicPage(WebDriver driver) {
        super(driver);
    }


    public boolean isActivated(){
        return driver.findElements(congratulationMessage).size() < 1;
    }
}

