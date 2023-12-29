package com.expandtesting.screens;

import com.expandtesting.utils.AllureUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HomeScreen extends BaseScreen {

    private static Logger logger = LoggerFactory.getLogger(HomeScreen.class);
    AppiumDriver driver;


    @AndroidFindBy(id = "com.expandtesting.playapp:id/logoutButton")
    @iOSXCUITFindBy(accessibility = "LOGOUT")
    protected WebElement logoutBtn;

    @AndroidFindBy(accessibility = "go-to-echo-screen")
    private WebElement echoMessageButton;

    @AndroidFindBy(id = "com.expandtesting.playapp:id/balanceWebView")
    protected WebElement balanceWebView;
    @AndroidFindBy(id = "com.expandtesting.playapp:id/makePaymentButton")
    protected WebElement makePaymentButton;
    public HomeScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("is logged in")
    public Boolean isLoggedIn() {
        AllureUtil.takeScreenshot(driver);
        return logoutBtn.isDisplayed();
    }

    @Step("logout")
    public void logout() {
        AllureUtil.takeScreenshot(driver);
        logoutBtn.click();
    }
    @Step("Check value of balance")
    public boolean getBalance() {
        balanceWebView.getText().contains("25");
        return true;
    }
    @Step("Open Make payment Screen")
    public void openMakePaymentScreen() throws InterruptedException {
        Thread.sleep(300);
        makePaymentButton.click();
    }

}
