package com.fintuity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class PasswordSetting {
    static WebDriver driver;

    //setPassword_field_password
    private By PasswordField = By.id("setPassword_field_password");
    //setPassword_field_confirmPassword
    private By PasswordConfirmField = By.id("setPassword_field_confirmPassword");
    //Password Requirements
    private By passwordRequirements = By.xpath("//div[text()='Password Requirements']");


    public PasswordSetting(WebDriver driver) {
        this.driver = driver;
    }

    public void setPasswordField(String password){
        driver.findElement(PasswordField).sendKeys(password);
    }

    public void setPasswordConfirmField(String password){
        driver.findElement(PasswordConfirmField).sendKeys(password);
    }

    public void lickPasswordRequirements(){
        driver.findElement(passwordRequirements).click();
    }

    public String PasswordRequirementsText(){
        //By passwordRequirements_1 = By.xpath("//div[text()='Your password may be any']");
        ////div[@class=\'p-4\']
        //By passwordRequirements_a1 = By.xpath("//span[text()='It is case sensitive']");
        //By passwordRequirements_a2 = By.xpath("//span[text()='It can't contain special characters (?&%$#@+=!'~, etc.)']");
        //By passwordRequirements_a3 = By.xpath("//span[text()=' It can't contain \"Fintuity\" or \"Password\"");
        By passwordRequirements_1 = By.xpath("//div[@class=\\'p-4\\']");
        String pr_text_1 = driver.findElement(passwordRequirements_1).getText();
        System.out.println("pr_text_1: " + pr_text_1);
        By passwordRequirements_add = By.xpath("//span");
        List<WebElement> elementName = driver.findElements(passwordRequirements_add);
        String pr_text_2 = "";
        for (WebElement element: elementName){
            pr_text_2 +=element.getText();
        }
        System.out.println("pr_text_2: " + pr_text_2);
        return pr_text_1+pr_text_2;
    }


    //generate password
    static char[] geek_Password(int len)
    {
        System.out.println("Generating password using random() : ");
        System.out.print("Your new password is : ");

        // A strong password has Cap_chars, Lower_chars,
        // numeric value and symbols. So we are using all of
        // them to generate our password
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "";
        //String symbols = "!@#$%^&*_=+-/.?<>)";


        String values = Capital_chars + Small_chars +
                numbers + symbols;

        // Using random method
        Random rndm_method = new Random();

        char[] password = new char[len];

        for (int i = 0; i < len; i++)
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            password[i] =
                    values.charAt(rndm_method.nextInt(values.length()));

        }
        return password;
    }
}
