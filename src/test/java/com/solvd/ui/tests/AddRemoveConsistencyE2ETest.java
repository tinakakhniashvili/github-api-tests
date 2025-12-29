package com.solvd.ui.tests;

import com.solvd.ui.pages.CartPage;
import com.solvd.ui.pages.InventoryPage;
import com.solvd.ui.pages.LoginPage;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

public class AddRemoveConsistencyE2ETest implements IAbstractTest {

    @Test
    public void testAddRemoveConsistency() {
        ResourceBundle td = ResourceBundle.getBundle("_testdata");
        String user = td.getString("sauce.user");
        String pass = td.getString("sauce.password");

        String product = "Sauce Labs Backpack";

        LoginPage login = new LoginPage(getDriver());
        login.open();

        InventoryPage inv = login.login(user, pass);
        inv.addToCart(product);
        Assert.assertEquals(inv.getCartBadgeCount(), 1, "Badge should be 1");

        CartPage cart = inv.openCart();
        cart.removeItem(product);
        Assert.assertFalse(cart.containsItem(product), "Item should be removed from cart");

        InventoryPage inv2 = cart.continueShopping();
        Assert.assertEquals(inv2.getCartBadgeCount(), 0, "Badge should be 0 after removal");
        Assert.assertFalse(inv2.findItemByName(product).isInCart(), "Item should not be marked as in cart");
    }
}
