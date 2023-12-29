package com.expandtesting.utils;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;

public class AllureUtil {

    @Attachment(value="screenshot", type="image/png")
    public static byte[] takeScreenshot(AppiumDriver driver){
        return driver.getScreenshotAs(OutputType.BYTES);
    }

}
