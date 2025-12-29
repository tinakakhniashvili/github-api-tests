package com.solvd.ui.pages;

import com.solvd.ui.components.CartItemComponent;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends AbstractPage {

    @FindBy(id = "checkout")
    private ExtendedWebElement checkoutBtn;

    @FindBy(id = "continue-shopping")
    private ExtendedWebElement continueShoppingBtn;

    @FindBy(css = "div.cart_item")
    private List<CartItemComponent> items;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return checkoutBtn.isElementPresent();
    }

    public boolean containsItem(String productName) {
        return items.stream().anyMatch(i -> productName.equalsIgnoreCase(i.getName()));
    }

    public void removeItem(String productName) {
        CartItemComponent item = items.stream()
                .filter(i -> productName.equalsIgnoreCase(i.getName()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Item not found in cart: " + productName));
        item.remove();
    }

    public InventoryPage continueShopping() {
        continueShoppingBtn.click();
        return new InventoryPage(getDriver());
    }

    public CheckoutStepOnePage checkout() {
        checkoutBtn.click();
        return new CheckoutStepOnePage(getDriver());
    }
}
