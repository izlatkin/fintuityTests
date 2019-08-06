package fintuity_tests.mail;

import docuSign.DocuSign;
import environment.EnvironmentManager;
import environment.RunEnvironment;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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
    public void signTest() throws InterruptedException {
        driver.navigate().to("https://demo.docusign.net/Member/EmailStart.aspx?a=237bdfef-6144-497a-8fea-050bd0f494b3&acct=1615617e-01c0-4718-aff9-07255fdc6336&er=fd12931c-86c9-4b2f-96b4-08515b9d7187&espei=e68407e4-37dc-4b3d-b962-03b59b6d5ce5");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        DocuSign docuSign = new DocuSign(driver);
        Thread.sleep(15000);
        if (docuSign.isTextPresentAndDisplayed("I agree to use electronic records and signatures"))
            docuSign.clickCheckBox();
        docuSign.clickContinue();
        Thread.sleep(15000);
        if (docuSign.isTextPresent("HAVE QUESTIONS ABOUT THIS DOCUMENT?"))
            docuSign.clickOk();
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        docuSign.clickSign();
        docuSign.waitForElement("Adopt Your Signature");
        docuSign.clickAdoptAndSign();
        Thread.sleep(15000);
        docuSign.clickFinish();
        Thread.sleep(30000);
        docuSign.waitForElement("Done Signing");
        docuSign.clickDialogCuntinue();
    }

    @AfterMethod
    public void tearDown() {
        //environmentManager.shutDownDriver();
    }
}
