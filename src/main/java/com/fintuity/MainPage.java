package com.fintuity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends FintuityPage{

    private By signInButton = By.xpath("//a[text()='SIGN IN']");

    //<button class="btn btn-yellow">Book Free Consultation</button>
    private By bookFreeConsultation = By.className("btn btn-yellow");
    private By cardInvestment = By.xpath("//a[text()='Investment']");
    private By cardPension = By.xpath("//a[text()='Pension']");
    private By cardProtection = By.xpath("//a[text()='Protection']");
    private By cardInheritanceTax = By.xpath("//a[text()='Inheritance Tax']");

    //should be updated
    private By viewClientBrochure = By.xpath("/html/body/div[1]/main/section[3]/div/div/div[6]/a");
    //<button class="btn btn-yellow question-btn">Live Chat</button>
    private By liveChat = By.className("btn btn-yellow question-btn");
    private By whyUs = By.xpath("//a[text()='Why Us']");
    private By ourServices = By.xpath("//a[text()='our services']");
    private By faq = By.xpath("//a[text()='FAQ']");

    //intercom-fvs20o e2ujk8f2
    private By intercom = By.className("intercom-fvs20o e2ujk8f2");

    public MainPage(WebDriver driver){
        super(driver);
    }

//    public LoginPage clickSignIn(){
//        driver.findElement(signInButton).click();
//        return new LoginPage(driver);
//    }




}
