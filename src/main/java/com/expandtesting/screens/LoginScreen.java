package com.expandtesting.screens;

import com.expandtesting.utils.AllureUtil;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginScreen extends BaseScreen {

    private static Logger logger = LoggerFactory.getLogger(LoginScreen.class);

    AppiumDriver driver;
    @AndroidFindBy(id = "com.expandtesting.playapp:id/usernameTextField")
    @iOSXCUITFindBy(accessibility = "usernameTextField")
    protected WebElement userNameField;

    @AndroidFindBy(id = "com.expandtesting.playapp:id/passwordTextField")
    @iOSXCUITFindBy(accessibility = "passwordTextField")
    protected WebElement userPasswordField;


    @AndroidFindBy(id = "com.expandtesting.playapp:id/loginButton")
    @iOSXCUITFindBy(accessibility = "Login")
    protected WebElement loginBtn;
    @AndroidFindBy(id="android:id/message")
    protected WebElement messageError;


    public LoginScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Login")
    public void login(String userName, String userPassword) {
        logger.info("Login with user name " + userName);
        userNameField.sendKeys(userName);
        userPasswordField.sendKeys(userPassword);
        loginBtn.click();// TODO: use tap
    }

    @Step("Open login screen")
    public void open() {
        driver.findElement(By.id("com.expandtesting.playapp:id/btn_bank_app")).click();
    }

    @Step("Check alert Error")
    public boolean checkAlertError() {
        return (messageError.getText().contains("Invalid"));
    }
        @Step("Close pop-up")
        public void closeAlert() {
            driver.findElement(AppiumBy.id("android:id/button3")).click();}

    @Step("is not logged ")
    public Boolean isNotLogged() {
        AllureUtil.takeScreenshot(driver);
        return loginBtn.isDisplayed();
    }
}

