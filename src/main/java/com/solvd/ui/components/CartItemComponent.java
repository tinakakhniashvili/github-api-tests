package com.solvd.ui.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CartItemComponent extends AbstractUIObject {

    @FindBy(css = ".inventory_item_name")
    private ExtendedWebElement name;

    @FindBy(css = "button.cart_button")
    private ExtendedWebElement removeBtn;

    public CartItemComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getName() {
        return name.getText().trim();
    }

    public void remove() {
        removeBtn.click();
    }
}
