package environment;

import org.openqa.selenium.WebDriver;

public class RunEnvironment {

    private WebDriver webDriver;

    public  WebDriver getWebDriver() {
        return webDriver;
    }

    void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
