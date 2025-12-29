package com.solvd.ui.tests;

import com.solvd.ui.pages.*;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

public class PurchaseE2ETest implements IAbstractTest {

    @Test
    public void testPurchaseTwoItems() {
        ResourceBundle td = ResourceBundle.getBundle("_testdata");
        String user = td.getString("sauce.user");
        String pass = td.getString("sauce.password");

        String fn = td.getString("checkout.first");
        String ln = td.getString("checkout.last");
        String zip = td.getString("checkout.zip");

        LoginPage login = new LoginPage(getDriver());
        login.open();

        InventoryPage inv = login.login(user, pass);
        Assert.assertTrue(inv.isPageOpened(), "Inventory page not opened");

        inv.addToCart("Sauce Labs Backpack");
        inv.addToCart("Sauce Labs Bike Light");
        Assert.assertEquals(inv.getCartBadgeCount(), 2, "Cart badge should be 2");

        CartPage cart = inv.openCart();
        Assert.assertTrue(cart.isPageOpened(), "Cart page not opened");

        CheckoutStepOnePage s1 = cart.checkout();
        Assert.assertTrue(s1.isPageOpened(), "Checkout Step 1 not opened");

        CheckoutStepTwoPage s2 = s1.fill(fn, ln, zip).continueCheckout();
        Assert.assertTrue(s2.isPageOpened(), "Checkout Step 2 not opened");

        CheckoutCompletePage done = s2.finish();
        Assert.assertTrue(done.isPageOpened(), "Checkout Complete not opened");
        Assert.assertTrue(done.getHeaderText().toLowerCase().contains("thank you"), "No confirmation text");
    }
}
