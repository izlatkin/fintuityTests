package com.fintuity.login;

import com.fintuity.environment.EnvironmentManager;
import com.fintuity.environment.RunEnvironment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class MailServices {
    static WebDriver driver;
    @Before
    public void startBrowser() {
        EnvironmentManager.initWebDriver();
        driver = RunEnvironment.getWebDriver();
    }

    @Test
    //


    @After
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }
}
