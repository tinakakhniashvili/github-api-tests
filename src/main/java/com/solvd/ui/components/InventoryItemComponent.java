package com.solvd.ui.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class InventoryItemComponent extends AbstractUIObject {

    @FindBy(css = ".inventory_item_name")
    private ExtendedWebElement name;

    @FindBy(css = "button.btn_inventory")
    private ExtendedWebElement addRemoveBtn;

    public InventoryItemComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getName() {
        return name.getText().trim();
    }

    public boolean isInCart() {
        return addRemoveBtn.getText().toLowerCase().contains("remove");
    }

    public void addToCart() {
        if (!isInCart()) addRemoveBtn.click();
    }

    public void removeFromCart() {
        if (isInCart()) addRemoveBtn.click();
    }
}
