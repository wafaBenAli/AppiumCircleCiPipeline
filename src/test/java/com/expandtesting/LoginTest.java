package com.expandtesting;

import com.expandtesting.screens.HomeScreen;
import com.expandtesting.screens.LoginScreen;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    private LoginScreen loginScreen;
    private HomeScreen homeScreen;

    @BeforeClass
    public void setupTest() {
        loginScreen = new LoginScreen(driver);
        homeScreen = new HomeScreen(driver);
    }
    @Test
    public void userShouldNotLoginWithNotValidCredentials() {
        homeScreen.logout();
        //loginScreen.open();
        loginScreen.login("practice", "test");
        Boolean isNotLogged = loginScreen.checkAlertError();
        Assert.assertTrue(isNotLogged);
        loginScreen.closeAlert();
    }

    @Test
    @Description("Login test Scenario")
    @Epic("EPIC-001")
    @Feature("Feature ID: FEA-01")
    @Story("Story ID: EB-003")
    public void userShouldLoginWithValidCredentials() {
        loginScreen.open();
        loginScreen.login("practice", "practice");
        Boolean isLoggedIn = homeScreen.isLoggedIn();
        Assert.assertTrue(isLoggedIn);
    }


}
