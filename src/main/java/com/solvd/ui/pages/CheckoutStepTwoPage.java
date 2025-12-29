package com.solvd.ui.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CheckoutStepTwoPage extends AbstractPage {

    @FindBy(id = "finish")
    private ExtendedWebElement finishBtn;

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return finishBtn.isElementPresent();
    }

    public CheckoutCompletePage finish() {
        finishBtn.click();
        return new CheckoutCompletePage(getDriver());
    }
}
