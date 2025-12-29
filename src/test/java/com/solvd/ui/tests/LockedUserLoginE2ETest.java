package com.solvd.ui.tests;

import com.solvd.ui.pages.LoginPage;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

public class LockedUserLoginE2ETest implements IAbstractTest {

    @Test
    public void testLockedUserCannotLogin() {
        ResourceBundle td = ResourceBundle.getBundle("_testdata");
        String locked = td.getString("sauce.user.locked");
        String pass = td.getString("sauce.password");

        LoginPage login = new LoginPage(getDriver());
        login.open();

        login.loginFail(locked, pass);
        Assert.assertTrue(login.isErrorPresent(), "Error should be shown for locked user");
        Assert.assertTrue(login.getErrorText().toLowerCase().contains("locked"), "Expected locked message");
    }
}
