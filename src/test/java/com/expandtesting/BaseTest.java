package com.expandtesting;

import com.expandtesting.drivers.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;


import java.time.Duration;

public abstract class BaseTest {
    public AppiumDriver driver;
    public WebDriverWait driverWait;



    @BeforeTest
    @Parameters({"platformName"})
    public void setUpAppiumDriver(@Optional("ANDROID")String platformName) throws Exception {

        driver = DriverManager.initializeDriver(platformName);
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(12));
    }

    @AfterTest
    public void tearDownAppiumDriver() throws Exception {
        DriverManager.shutdownDriver();
    }

}
