package com.solvd.ui.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent extends AbstractUIObject {

    @FindBy(css = "a.shopping_cart_link")
    private ExtendedWebElement cartLink;

    @FindBy(css = "span.shopping_cart_badge")
    private ExtendedWebElement cartBadge;

    public HeaderComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void openCart() {
        cartLink.click();
    }

    public int getCartBadgeValueOrZero() {
        if (!cartBadge.isElementPresent()) return 0;
        return Integer.parseInt(cartBadge.getText().trim());
    }
}
