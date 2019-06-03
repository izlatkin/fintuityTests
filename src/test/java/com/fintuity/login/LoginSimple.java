package com.fintuity.login;

import com.fintuity.environment.EnvironmentManager;
import com.fintuity.environment.RunEnvironment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class LoginSimple {
    static WebDriver driver;
    @Before
    public void startBrowser() {
        EnvironmentManager.initWebDriver();
        driver = RunEnvironment.getWebDriver();
    }


    @Test
    public void availabilityFintuity(){
        driver.navigate().to("https://fintuity.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        System.out.println("Titels: " + driver.getTitle());
        Assert.assertTrue("Online Financial Adviser",
                driver.getTitle().startsWith("Online Financial Adviser" ));
        //driver.findElement(By.xpath("/html/body/div[1]/header/div/div[1]/div/nav/ul/li[5]/a"));
        driver.findElement(By.xpath("//nav[@class=\"sign in \"]")).click();
    }


    @After
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }
}
