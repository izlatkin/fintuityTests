package fintuity_tests.mail;

import docuSign.DocuSign;
import environment.EnvironmentManager;
import environment.RunEnvironment;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class DocuSignTest {
    static WebDriver driver;
    static EnvironmentManager environmentManager;

    @BeforeMethod
    public void startBrowser() {
        EnvironmentManager.initWebDriver();
        driver = RunEnvironment.getWebDriver();
    }

    @Test
    public void signTest(){
        driver.navigate().to("https://demo.docusign.net/Member/EmailStart.aspx?a=90877c48-7eb9-4838-aa9b-7abc65324162&acct=1615617e-01c0-4718-aff9-07255fdc6336&er=7059465e-f07a-45f2-9fbd-7f84a700bf2e&espei=c9f64681-cb3c-49a3-8fe5-aa73b4a92017");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        DocuSign docuSign = new DocuSign(driver);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        if (docuSign.isTextPresentAndDisplayed("I agree to use electronic records and signatures"))
            docuSign.clickCheckBox();
        docuSign.clickContinue();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if (docuSign.isTextPresent("HAVE QUESTIONS ABOUT THIS DOCUMENT?"))
            docuSign.clickOk();
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        docuSign.clickSign();
        docuSign.waitForElement("Adopt Your Signature");
        docuSign.clickAdoptAndSign();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        docuSign.clickFinish();
        docuSign.waitForElement("You're Done Signing");
        docuSign.clickDialogCuntinue();


    }

    @AfterMethod
    public void tearDown() {
        //environmentManager.shutDownDriver();
    }
}
