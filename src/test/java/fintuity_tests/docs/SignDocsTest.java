package fintuity_tests.docs;

import environment.EnvironmentManager;
import environment.RunEnvironment;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.UserProfile;

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
    public void upload_sign_comfirm(){
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
        EnvironmentManager.shutDownDriver();
    }
}
