package com.solvd.ui.pages;

import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @FindBy(xpath = "//input[@id='user-name']")
    private ExtendedWebElement usernameInput;

    @FindBy(id = "password")
    private ExtendedWebElement passwordInput;

    @FindBy(id = "login-button")
    private ExtendedWebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    private ExtendedWebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageURL() {
        return Configuration.getRequired("url");
    }

    @Override
    public boolean isPageOpened() {
        return loginButton.isElementPresent();
    }

    public InventoryPage login(String username, String password) {
        usernameInput.type(username);
        passwordInput.type(password);
        loginButton.click();
        return new InventoryPage(getDriver());
    }

    public boolean isErrorPresent() {
        return errorMessage.isElementPresent();
    }

    public String getErrorText() {
        return errorMessage.getText().trim();
    }
}
