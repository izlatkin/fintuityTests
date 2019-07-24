package fintuity_tests.docs;

import backoffice.AdminMainPage;
import backoffice.LoginPageBO;
import backoffice.UserInfoPage;
import com.fintuity.LoginPage;
import com.fintuity.MainPage;
import com.fintuity.MyDocuments;
import com.fintuity.MyProfilePage;
import environment.EnvironmentManager;
import environment.RunEnvironment;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.UserProfile;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SignDocsTest {
    static WebDriver driver;
    static UserProfile user;

    @BeforeMethod
    public void startBrowser_and_createNewUser() {
        EnvironmentManager.initWebDriver();
        driver = RunEnvironment.getWebDriver();
        //ToDo login with exist user
    }

    @Test
    public void upload_signManual_comfirm(){
        //login to backoffice
        driver.get(EnvironmentManager.FINTUITY_BACKOFFICE_URL);
        driver.manage().window().maximize();
        LoginPageBO loginPageBO = new LoginPageBO(driver);
        AdminMainPage adminMainPage =
                loginPageBO.loginCorrect("testIFA1@fintuity.com","Fintuity-test1");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // go to top search field -> type test user name and click on it
        UserProfile user = new UserProfile("ilya","zlatkin");
        user.setEmail("ilya.zlatkin@gmail.com");
        user.setPassword("fintuity123");
        adminMainPage.searchUser(user.getName()+" "+user.getSurname());
        Assert.assertTrue(adminMainPage.isTextPresent(user.getName()),"Check user Name: ");
        Assert.assertTrue(adminMainPage.isTextPresent(user.getSurname()),"Check user Surname: ");

        // go to Documents tab and click "generate / upload new documents"
        // from resources/agreement/Fintuity_Client_Agreement.pdf
        // select document type "Appilcation form"
        UserInfoPage userInfoPage = new UserInfoPage(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        userInfoPage.clickTab("Documents");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        userInfoPage.clickGenerateUploadNewDocumentsButton();
        userInfoPage.waitForElement("Document Operation");
        Random rand = new Random();
        String randomDocName = "Docs" + rand.nextInt(1000);
        userInfoPage.typeDocumentName(randomDocName);
        userInfoPage.selectDocumentType("Application Form");
        userInfoPage.waitForElement("Download Upload");
        userInfoPage.selectRadioButton("Download Upload");
        //userInfoPage.clickUploadDocumentDialogFormButton();
        ClassLoader classLoader = getClass().getClassLoader();
        File resourcesDirectory = new File("src/test/resources");
        System.out.println(resourcesDirectory.getAbsolutePath());
        String filePath = resourcesDirectory.getAbsolutePath() +
                "/fintuity/agreement/Fintuity_Client_Agreement.pdf";
        System.out.println(filePath);
        userInfoPage.setFilePath(filePath);
        userInfoPage.clickUpload();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        userInfoPage.sentFile(randomDocName);
        userInfoPage.waitForElement("Waiting for Signature");
        //press send to user -> stutus must be "waiting for signature"


        //go to User mail or internal mail and get email with DocsSign
        String backOfficeTab = driver.getWindowHandle();
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String fintuityTab = "";
        for (String tab : tabs) {
            if (!tab.equals(backOfficeTab))
                fintuityTab = tab;
        }
        driver.switchTo().window(fintuityTab);
        driver.get(EnvironmentManager.FINTUITY_FRONT_URL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickSingIn();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MyProfilePage myProfilePage = loginPage.login(user);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Notification avaible: " + myProfilePage.isNotificationAvaible());
        myProfilePage.clickNotificationElement();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(myProfilePage.isTextPresent("you have new document for signing"));
        myProfilePage.clickFollow();
        MyDocuments myDocuments = new MyDocuments(driver);
        Assert.assertTrue(myDocuments.isTextPresent(randomDocName));
        Assert.assertTrue(myDocuments.isTextPresent("Waiting for Signature"));
        //sign docs
        myDocuments.downloadOriginalDocumentAndUploadSignedDocument(randomDocName);
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        myDocuments.uploadSignedDocument(randomDocName);
        String filePathSigned = resourcesDirectory.getAbsolutePath() +
                "/fintuity/agreement/Fintuity_Client_Agreement_Signed.pdf";
        System.out.println(filePath);
        userInfoPage.setFilePath(filePath);
        myDocuments.setFilePath(filePathSigned);
        myDocuments.clickUpload();
        myDocuments.waitForElement("IFA Review Required");


        //goto back office and find this doc signed in correspoding table
        //check that it is downloadbale

        //? compare with reference doc



    }

    @Test
    public void upload_signDocu_comfirm(){
        //login to backoffice

        // go to top search field -> type test user name and click on it

        // go to Documents tab and click "generate / upload new documents"
        // from resources/agreement/Fintuity_Client_Agreement.pdf
        // select document type "Appilcation form"

        //press send to user -> stutus must be "waiting for signature"


        //go to User mail or internal mail and get email with DocsSign
        //sign docs


        //goto back office and find this doc signed in correspoding table
        //check that it is downloadbale

        //? compare with reference doc
    }

    @AfterMethod
    public void tearDown() {
        //EnvironmentManager.shutDownDriver();
    }
}
