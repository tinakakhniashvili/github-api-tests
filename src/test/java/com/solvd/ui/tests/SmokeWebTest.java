package com.solvd.ui.tests;

import com.solvd.ui.pages.LoginPage;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeWebTest implements IAbstractTest {

    @Test
    public void testOpenLoginPage() {
        LoginPage page = new LoginPage(getDriver());
        page.open();
        Assert.assertTrue(page.isPageOpened(), "Login page is not opened");
    }
}
