package com.expandtesting.drivers;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;


public class DriverManager {
    public static final String APPIUM = "http://127.0.0.1:4723/";

    String androidAppPath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "play-app.apk").toString();
    String iosAppPath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "play-app.zip").toString();
    private static final ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> webDriverWait = new ThreadLocal<>();


    String isCI = System.getenv("CI");

    String androidPlatformVersion = (isCI != null && isCI.equalsIgnoreCase("true")) ? "10" : "12";
    String androidDeviceId = (isCI != null && isCI.equalsIgnoreCase("true")) ? "emulator-5554" : "RZ8R21SXE6E";
    String iosDeviceId = "ADD_DEVICE_ID";

    public static AppiumDriver getDriver() {
        return appiumDriver.get();
    }

    private static void setAppiumDriver(AppiumDriver dr) {
        appiumDriver.set(dr);
    }

    public static AppiumDriver initializeDriver(String platform) {
        if (DriverManager.getDriver() == null) {
            try {
                new DriverManager(platform);
            } catch (Exception e) {
                System.out.println(e);
                throw new RuntimeException("Failed to initialize the DriverManager");
            }
        }
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return DriverManager.getDriver();
    }


    public static WebDriverWait getWait() {
        if (webDriverWait.get() == null) {
            webDriverWait.set(new WebDriverWait(getDriver(), Duration.ofSeconds(20)));
        }
        return webDriverWait.get();
    }


    /**
     * Closes the driver session
     */
    public static void shutdownDriver() {
        if (DriverManager.getDriver() != null) {
            try {
                DriverManager.getDriver().quit();
                DriverManager.appiumDriver.remove();
            } catch (Exception e) {
                throw new RuntimeException("Failed to quit the Driver from thread: " + Thread.currentThread().getId());
            }
        }
    }

    private DriverManager(String platform) throws MalformedURLException {
        AppiumDriver driver;

        //Appium Server URL and port
        URL url = new URL(APPIUM);

        switch (platform) {
            case "ANDROID" -> {

                UiAutomator2Options androidOptions = new UiAutomator2Options();
                androidOptions.setPlatformVersion(androidPlatformVersion);
                androidOptions.setUdid(androidDeviceId);
                //androidOptions.setAppPackage("TODO");
                //androidOptions.setAppActivity("TODO");
                androidOptions.setApp(androidAppPath);
                androidOptions.setAutoGrantPermissions(true);
                androidOptions.clearSystemFiles();
                //androidOptions.setAppWaitActivity(getAppiumProps().appWaitActivity());
                androidOptions.setNewCommandTimeout(Duration.ofSeconds(20));

                driver = new AndroidDriver(url, androidOptions);
            }
            case "IOS" -> {

                XCUITestOptions iOSOptions = new XCUITestOptions();

                iOSOptions.setUdid(iosDeviceId);
                //iOSOptions.setBundleId("TODO");
                iOSOptions.setApp(iosAppPath);
                iOSOptions.setAutoAcceptAlerts(true);
                iOSOptions.setNewCommandTimeout(Duration.ofSeconds(20));

                driver = new IOSDriver(url, iOSOptions);
            }
            default -> throw new RuntimeException("Unable to create session with platform: " + platform);
        }
        DriverManager.setAppiumDriver(driver);
    }
}
