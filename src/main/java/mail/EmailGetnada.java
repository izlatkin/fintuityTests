package mail;

import com.fintuity.PasswordSetting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EmailGetnada {
    private WebDriver driver;

    //@FindBy(xpath = "//span[@class=\"address what_to_copy\"")
    //@FindBy(xpath = "//h1/span[2]")
    @FindBy(xpath = "//span[contains(@class, \"address what_to_copy\")]")
    private WebElement email;

    @FindBy(xpath = "//*[text()='Inbox empty ... '")

    private WebElement isEmpty;
    @FindBy(xpath = "//ul[@class=\"msg_list\"]")
    private List<WebElement> messageList;

    public EmailGetnada(WebDriver driver){
        this.driver = driver;
    }

    public String getEmail(){
        return email.getText();
    }

    public boolean isTextPresent(String text){
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
        return list.size() > 0;
    }

    public boolean isEmpty(){
        return isTextPresent("Inbox empty ... messages");
    }


}
