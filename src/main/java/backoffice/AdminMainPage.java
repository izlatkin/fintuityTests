package backoffice;

import com.fintuity.FintuityPage;
import com.fintuity.LoginPage;
import com.fintuity.MyProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AdminMainPage {
    public WebDriver driver;
    private By signInButton = By.xpath("//a[text()='SIGN IN']");
    private By logoImg = By.xpath("//*[@class='logo__img']");
    private By clientsIcon = By.xpath("//*[@class='ng-star-inserted active']");

    private By searchFieldIcon = By.xpath("//*[contains(text(),'Search here')]");
    private By searchField = By.xpath("//input[ @placeholder='Search...' and @type='text']");
    //search-results__item ng-star-inserted
    private By searchResult = By.xpath("//*[contains(text(),'test one') and contains(@class,'search')]");
    //btn btn-warning btn-block font-bold
    private By openNotActive = By.xpath("//*[@class='nav-link active nav__button_active'][text()=' Open ']");
    private By openActive = By.xpath("//*[@class='nav-link active'][text()=' Open ']");

    private By inProgressActive = By.xpath("//*[@class='nav-link active'][text()=' In Progress ']");
    private By inProgressNotActive = By.xpath("//*[@class='nav-link active nav__button_active'][text()=' In Progress ']");
    private By closedActive = By.xpath("//*[@class='nav-link active'][text()=' Closed ']");
    private By closedNotActive = By.xpath("//*[@class='nav-link active nav__button_active'][text()=' Closed ']");
    private By createdNewTask = By.xpath("//*[@class='btn btn-warning text-white']");

    private By taskCreationLable = By.xpath("//*[@class='ibox-title color-blue'][text()='Task creation']");

    private By taskForm_field_client = By.xpath("//*[@id='taskForm_field_client']");
    private By taskForm_field_type = By.xpath("//*[@id='taskForm_field_type']");

    //taskForm_field_details
    private By taskForm_field_details = By.xpath("//label[text()=' Task details ']");
    //taskForm_field_dueDate
    private By taskForm_field_dueDate = By.xpath("//label[text()=' Due date ']");
    //taskForm_field_assignee
    private By taskForm_field_assignee = By.xpath("//label[text()=' Assignee ']");
    //taskForm_field_comment
    private By taskForm_field_comment = By.xpath("//label[text()=' Comment ']");

    private By emailField = By.id("login_field_email");
    private By password = By.id("login_field_password");
    private By loginButton = By.xpath("//a[text()='Login']");
    private By loginLable = By.xpath("//a[text()='Log In']");
    //div[contains(@class,'alert alert-danger')]
    private By error = By.xpath("//div[contains(@class,'alert alert-danger')]");

    public AdminMainPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isTextPresent(String text){
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
        return list.size() > 0;
    }

    public void clickOpen(){
        System.out.println("click Open: " + openActive.toString());
        driver.findElement(openActive).click();
    }

    public void clickSearch(){
        System.out.println("click search: " + searchFieldIcon.toString());
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(searchFieldIcon)).click().build().perform();
    }

    public void searchUser(String userFullName){
        System.out.println("search for user: " + userFullName);
        clickSearch();
        waitForElement(searchField);
        WebElement search = driver.findElement(searchField);
        search.sendKeys(userFullName);
        By userSearchResult = By.xpath("//*[contains(text(),'" + userFullName + "') and contains(@class,'search')]");
        waitForElement(userSearchResult);
//        Actions actions = new Actions(driver);
//        actions.moveToElement(driver.findElement(userSearchResult)).click().build().perform();
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(userSearchResult));
    }



    public boolean isOpenActive(){
        List<WebElement> list = driver.findElements(openActive);
        return list.size() > 0;
    }

    public boolean isOpenNotActive(){
        List<WebElement> list = driver.findElements(openNotActive);
        return list.size() > 0;

    }

    public void clickInProgress(){
        System.out.println("click InProgress: " + inProgressActive.toString());
        driver.findElement(inProgressActive).click();
    }

    public boolean isInProgressActive(){
        List<WebElement> list = driver.findElements(inProgressActive);
        return list.size() > 0;
    }

    public boolean isInProgressnNotActive(){
        List<WebElement> list = driver.findElements(inProgressNotActive);
        return list.size() > 0;

    }

    public void clickClosed(){
        System.out.println("click Closed: " + closedActive.toString());
        driver.findElement(closedActive).click();
    }

    public boolean isClosedActive(){
        List<WebElement> list = driver.findElements(closedActive);
        return list.size() > 0;
    }

    public boolean isClosedNotActive(){
        List<WebElement> list = driver.findElements(closedNotActive);
        return list.size() > 0;

    }

    public void clickCreateNewTask(){
        System.out.println("click Create new task: " + createdNewTask.toString());
        driver.findElement(createdNewTask).click();
    }

    public boolean isCreateNewTaskAvaible(){
        List<WebElement> list = driver.findElements(createdNewTask);
        return list.size() > 0;
    }

    public boolean isTaskCreationLableAvaible(){
        List<WebElement> list = driver.findElements(taskCreationLable);
        return list.size() > 0;
    }

    public boolean isClientFieldAvaible(){
        List<WebElement> list = driver.findElements(taskForm_field_client);
        return list.size() > 0;
    }

    public void typeClientField(String clientName){
        driver.findElement(taskForm_field_client).sendKeys(clientName);
    }

    public void clickClientField(){
        driver.findElement(taskForm_field_client).click();
    }

    public boolean isClientFieldClickable(){
        return driver.findElement(taskForm_field_client).isEnabled();
    }

    public boolean isTaskFormFieldTypeAvaible(){
        List<WebElement> list = driver.findElements(taskForm_field_type);
        return list.size() > 0;
    }

    public void typeTaskFormFieldType(String clientName){
        driver.findElement(taskForm_field_type).sendKeys(clientName);
    }

    public void clickTaskFormFieldType(){
        driver.findElement(taskForm_field_type).click();
    }

    public boolean isTaskFormFieldType(){
        return driver.findElement(taskForm_field_type).isEnabled();
    }

    public boolean is_taskForm_field_details_avaible(){
        List<WebElement> list = driver.findElements(taskForm_field_details);
        return list.size() > 0;
    }


    public boolean is_taskForm_field_dueDate_avaible(){
        List<WebElement> list = driver.findElements(taskForm_field_dueDate);
        return list.size() > 0;
    }

    public boolean is_taskForm_field_assignee_avaible(){
        List<WebElement> list = driver.findElements(taskForm_field_assignee);
        return list.size() > 0;
    }

    public boolean is_taskForm_field_comment(){
        List<WebElement> list = driver.findElements(taskForm_field_comment);
        return list.size() > 0;
    }

    public void clickClientsIcon(){
        System.out.println("click client icon: " + clientsIcon.toString());
        driver.findElement(clientsIcon).click();
    }

    public void waitForElement(String text) {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'" + text + "')]")));

    }

    public void waitForElement(By e) {
        System.out.println("wait for element: " + e.toString());
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(e));

    }

    public void moveToClient(){
        Actions action = new Actions(driver);
        WebElement client = driver.findElement(clientsIcon);
        action.moveToElement(client);
    }

    public void waitForElement(String text, int Seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Seconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'" + text + "')]")));

    }




}
