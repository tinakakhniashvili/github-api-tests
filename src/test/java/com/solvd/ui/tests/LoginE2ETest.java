package com.solvd.ui.tests;

import com.solvd.ui.pages.InventoryPage;
import com.solvd.ui.pages.LoginPage;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

public class LoginE2ETest implements IAbstractTest {

    @Test
    public void verifySuccessfulLoginTest() {
        ResourceBundle td = ResourceBundle.getBundle("_testdata");
        String user = td.getString("sauce.user");
        String pass = td.getString("sauce.password");

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        InventoryPage inventoryPage = loginPage.login(user, pass);
        Assert.assertTrue(inventoryPage.isPageOpened());
    }
}
