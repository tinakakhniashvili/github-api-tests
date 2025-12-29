package com.solvd.ui.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends AbstractPage {

    @FindBy(css = "h2.complete-header")
    private ExtendedWebElement completeHeader;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return completeHeader.isElementPresent();
    }

    public String getHeaderText() {
        return completeHeader.getText().trim();
    }
}
