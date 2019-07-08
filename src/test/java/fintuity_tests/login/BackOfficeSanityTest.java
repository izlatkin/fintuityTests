package fintuity_tests.login;

import backoffice.AdminMainPage;
import backoffice.LoginPageBO;
import environment.EnvironmentManager;
import environment.RunEnvironment;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class BackOfficeSanityTest {
    static WebDriver driver;

    @BeforeMethod
    public static void startBrowser() {
        EnvironmentManager.initWebDriver();
        driver = RunEnvironment.getWebDriver();
        driver.get(EnvironmentManager.FINTUITY_BACKOFFICE_URL);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        LoginPageBO loginPageBO = new LoginPageBO(driver);
        AdminMainPage adminMainPage = loginPageBO.loginCorrect("ilya.zlatkin@gmail.com","Scaleio123");
    }

    @Ignore
    @Test
    public void checkButtons(){
        AdminMainPage adminMainPage =  new AdminMainPage(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertTrue(adminMainPage.isTextPresent("Open"),"No \"Open\" button" );
        Assert.assertTrue(adminMainPage.isTextPresent("Closed"),"No \"Closed\" button" );
        Assert.assertTrue(adminMainPage.isTextPresent("In Progress"),"No \"In Progress\" button" );
    }

    @Ignore
    @Test
    public void checkClickability(){
        AdminMainPage adminMainPage =  new AdminMainPage(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertTrue(adminMainPage.isTextPresent("Open"),"No \"Open\" button" );
        Assert.assertTrue(adminMainPage.isTextPresent("In Progress"),"No \"In Progress\" button" );
        Assert.assertTrue(adminMainPage.isTextPresent("Closed"),"No \"Closed\" button");

        Assert.assertTrue(
                adminMainPage.isOpenNotActive(),"check that default mode of 'Open' is not active");
        Assert.assertTrue(adminMainPage.isInProgressActive(),"check that default mode of 'In Progress' is active");
        Assert.assertTrue(adminMainPage.isClosedActive(),"check that default mode of 'Closed' is active");
        adminMainPage.clickInProgress();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertTrue(adminMainPage.isOpenActive(),"check that 'Open' state changed to active");
        Assert.assertTrue(adminMainPage.isInProgressnNotActive(),"check that 'In Progress' state changed to not active");
        Assert.assertTrue(adminMainPage.isClosedActive(),"check that default mode of 'Closed' is active");
        adminMainPage.clickClosed();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertTrue(adminMainPage.isClosedNotActive(),"check that 'Closed' state changed to not active");
        Assert.assertTrue(adminMainPage.isOpenActive(),"check that 'Open' state changed to active");
        Assert.assertTrue(adminMainPage.isInProgressActive(),"check that default mode of 'In Progress' is active");
        adminMainPage.clickOpen();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertTrue(adminMainPage.isClosedActive(),"check that 'Closed' state changed to active");
        Assert.assertTrue(adminMainPage.isOpenNotActive(),"check that 'Open' state changed to not active");
        Assert.assertTrue(adminMainPage.isInProgressActive(),"check that default mode of 'In Progress' is active");
    }

    @Ignore
    @Test
    public void checkCreateNewTaskButtom(){
        AdminMainPage adminMainPage =  new AdminMainPage(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertTrue(adminMainPage.isCreateNewTaskAvaible(),"Create New Task is not avaible ");
        adminMainPage.clickCreateNewTask();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertTrue(adminMainPage.isTaskCreationLableAvaible(),"Check that Lable 'Task Creation' appeared ");
        Assert.assertTrue(adminMainPage.isClientFieldAvaible(),"Client filed not available");
        Assert.assertTrue(adminMainPage.isClientFieldClickable(),"Client filed not clickable ");
        //adminMainPage.clickClientField();
        adminMainPage.typeClientField("test name");
        Assert.assertTrue(adminMainPage.isTaskFormFieldTypeAvaible(),"Task filed not avaible ");
        Assert.assertTrue(adminMainPage.isTaskFormFieldType(),"Task form not enable");
        Assert.assertTrue(adminMainPage.is_taskForm_field_assignee_avaible(),"assignee not avaible");
        Assert.assertTrue(adminMainPage.is_taskForm_field_comment(),"comment  not avaible");
        Assert.assertTrue(adminMainPage.is_taskForm_field_details_avaible(),"details not availbe");
        Assert.assertTrue(adminMainPage.is_taskForm_field_dueDate_avaible(),"due date not avaible");

    }

    @AfterMethod
    public static void tearDown() {
        EnvironmentManager.shutDownDriver();
    }
}
