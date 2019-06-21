package fintuity_tests.login;

import backoffice.AdminMainPage;
import backoffice.LoginPageBO;
import environment.EnvironmentManager;
import environment.RunEnvironment;
import org.junit.*;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BackOfficeSanityTest {
    static WebDriver driver;

    @BeforeClass
    public static void startBrowser() {
        EnvironmentManager.initWebDriver();
        driver = RunEnvironment.getWebDriver();
        driver.get(EnvironmentManager.FINTUITY_BACKOFFICE_URL);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        LoginPageBO loginPageBO = new LoginPageBO(driver);
        AdminMainPage adminMainPage = loginPageBO.loginCorrect("ilya.zlatkin@gmail.com","Scaleio123");
    }

    @Test
    public void checkButtons(){
        AdminMainPage adminMainPage =  new AdminMainPage(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertTrue("No \"Open\" button" ,adminMainPage.isTextPresent("Open"));
        Assert.assertTrue("No \"Closed\" button" ,adminMainPage.isTextPresent("Closed"));
        Assert.assertTrue("No \"In Progress\" button" ,adminMainPage.isTextPresent("In Progress"));
    }

    @Test
    public void checkClickability(){
        AdminMainPage adminMainPage =  new AdminMainPage(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertTrue("No \"Open\" button" ,adminMainPage.isTextPresent("Open"));
        Assert.assertTrue("No \"In Progress\" button" ,adminMainPage.isTextPresent("In Progress"));
        Assert.assertTrue("No \"Closed\" button" ,adminMainPage.isTextPresent("Closed"));

        Assert.assertTrue("check that default mode of 'Open' is not active",
                adminMainPage.isOpenNotActive());
        Assert.assertTrue("check that default mode of 'In Progress' is active",
                adminMainPage.isInProgressActive());
        Assert.assertTrue("check that default mode of 'Closed' is active",
                adminMainPage.isClosedActive());
        adminMainPage.clickInProgress();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertTrue("check that 'Open' state changed to active",
                adminMainPage.isOpenActive());
        Assert.assertTrue("check that 'In Progress' state changed to not active",
                adminMainPage.isInProgressnNotActive());
        Assert.assertTrue("check that default mode of 'Closed' is active",
                adminMainPage.isClosedActive());
        adminMainPage.clickClosed();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertTrue("check that 'Closed' state changed to not active",
                adminMainPage.isClosedNotActive());
        Assert.assertTrue("check that 'Open' state changed to active",
                adminMainPage.isOpenActive());
        Assert.assertTrue("check that default mode of 'In Progress' is active",
                adminMainPage.isInProgressActive());
        adminMainPage.clickOpen();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertTrue("check that 'Closed' state changed to active",
                adminMainPage.isClosedActive());
        Assert.assertTrue("check that 'Open' state changed to not active",
                adminMainPage.isOpenNotActive());
        Assert.assertTrue("check that default mode of 'In Progress' is active",
                adminMainPage.isInProgressActive());
    }

    @Test
    public void checkCreateNewTaskButtom(){
        AdminMainPage adminMainPage =  new AdminMainPage(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertTrue("Create New Task is not avaible ",
                adminMainPage.isCreateNewTaskAvaible());
        adminMainPage.clickCreateNewTask();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertTrue("Check that Lable 'Task Creation' appeared ",
                adminMainPage.isTaskCreationLableAvaible());
        Assert.assertTrue("Client filed not available",
                adminMainPage.isClientFieldAvaible());
        Assert.assertTrue("Client filed not clickable ",
                adminMainPage.isClientFieldClickable());
        //adminMainPage.clickClientField();
        adminMainPage.typeClientField("test name");
        Assert.assertTrue("Task filed not avaible ",
                adminMainPage.isTaskFormFieldTypeAvaible());
        Assert.assertTrue("Task form not enable",
                adminMainPage.isTaskFormFieldType());
        Assert.assertTrue("assignee not avaible"
                ,adminMainPage.is_taskForm_field_assignee_avaible());
        Assert.assertTrue("comment  not avaible",adminMainPage.is_taskForm_field_comment());
        Assert.assertTrue("details not availbe",adminMainPage.is_taskForm_field_details_avaible());
        Assert.assertTrue("due date not avaible",adminMainPage.is_taskForm_field_dueDate_avaible());

    }

    @AfterClass
    public static void tearDown() {
        EnvironmentManager.shutDownDriver();
    }
}
