package com.solvd.ui.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CheckoutStepOnePage extends AbstractPage {

    @FindBy(id = "first-name")
    private ExtendedWebElement firstName;

    @FindBy(id = "last-name")
    private ExtendedWebElement lastName;

    @FindBy(id = "postal-code")
    private ExtendedWebElement postalCode;

    @FindBy(id = "continue")
    private ExtendedWebElement continueBtn;

    @FindBy(css = "h3[data-test='error']")
    private ExtendedWebElement errorMessage;

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return continueBtn.isElementPresent();
    }

    public CheckoutStepOnePage fill(String fn, String ln, String zip) {
        if (fn != null) firstName.type(fn);
        if (ln != null) lastName.type(ln);
        if (zip != null) postalCode.type(zip);
        return this;
    }

    public CheckoutStepTwoPage continueCheckout() {
        continueBtn.click();
        return new CheckoutStepTwoPage(getDriver());
    }

    public CheckoutStepOnePage continueExpectingError() {
        continueBtn.click();
        return this;
    }

    public boolean isErrorPresent() {
        return errorMessage.isElementPresent();
    }

    public String getErrorText() {
        return errorMessage.getText().trim();
    }
}
