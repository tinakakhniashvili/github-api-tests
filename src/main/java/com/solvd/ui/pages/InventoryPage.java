package com.solvd.ui.pages;

import com.solvd.ui.components.HeaderComponent;
import com.solvd.ui.components.InventoryItemComponent;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InventoryPage extends AbstractPage {

    @FindBy(css = "span.title")
    private ExtendedWebElement title;

    @FindBy(css = "div.primary_header")
    private HeaderComponent header;

    @FindBy(css = "div.inventory_item")
    private List<InventoryItemComponent> items;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return title.isElementPresent() && "Products".equalsIgnoreCase(title.getText().trim());
    }

    public HeaderComponent header() {
        return header;
    }

    public List<InventoryItemComponent> getItems() {
        return items;
    }

    public InventoryItemComponent findItemByName(String productName) {
        for (InventoryItemComponent item : items) {
            if (productName.equalsIgnoreCase(item.getName())) {
                return item;
            }
        }
        throw new IllegalStateException("Product not found: " + productName);
    }

    public void addToCart(String productName) {
        findItemByName(productName).addToCart();
    }

    public void removeFromCart(String productName) {
        findItemByName(productName).removeFromCart();
    }

    public CartPage openCart() {
        header.openCart();
        return new CartPage(getDriver());
    }

    public int getCartBadgeCount() {
        return header.getCartBadgeValueOrZero();
    }
}
