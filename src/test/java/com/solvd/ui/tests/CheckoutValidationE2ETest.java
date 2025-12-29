package com.solvd.ui.tests;

import com.solvd.ui.pages.*;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

public class CheckoutValidationE2ETest implements IAbstractTest {

    @Test
    public void testCheckoutMissingZipShowsError() {
        ResourceBundle td = ResourceBundle.getBundle("_testdata");
        String user = td.getString("sauce.user");
        String pass = td.getString("sauce.password");

        LoginPage login = new LoginPage(getDriver());
        login.open();

        InventoryPage inv = login.login(user, pass);
        inv.addToCart("Sauce Labs Backpack");

        CartPage cart = inv.openCart();
        CheckoutStepOnePage s1 = cart.checkout();

        s1.fill(td.getString("checkout.first"), td.getString("checkout.last"), null)
          .continueExpectingError();

        Assert.assertTrue(s1.isErrorPresent(), "Error should be shown for missing ZIP");
        Assert.assertTrue(s1.getErrorText().toLowerCase().contains("postal"), "Expected postal code error");
    }
}
