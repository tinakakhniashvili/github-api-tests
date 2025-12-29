package com.solvd.ui.tests;

import com.solvd.ui.pages.CartPage;
import com.solvd.ui.pages.InventoryPage;
import com.solvd.ui.pages.LoginPage;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

public class AddToCartE2ETest implements IAbstractTest {

    @Test
    public void testAddItemToCart() {
        ResourceBundle td = ResourceBundle.getBundle("_testdata");
        String user = td.getString("sauce.user");
        String pass = td.getString("sauce.password");

        String product = "Sauce Labs Backpack";

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        InventoryPage inventoryPage = loginPage.login(user, pass);
        Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory page not opened after login");

        inventoryPage.addToCart(product);
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), 1, "Cart badge should be 1 after adding one item");

        CartPage cartPage = inventoryPage.openCart();
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page is not opened");
        Assert.assertTrue(cartPage.containsItem(product), "Cart does not contain expected product: " + product);
    }
}
