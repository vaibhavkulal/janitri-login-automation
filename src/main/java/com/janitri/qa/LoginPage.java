package com.janitri.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By userIdInput = By.id("formEmail");
    private By passwordInput = By.id("formPassword");
    private By loginButton = By.cssSelector("button.login-button");
    private By passwordToggle = By.cssSelector("img.passowrd-visible"); // Note: typo in class "passowrd-visible"
    private By errorMessage = By.cssSelector(".error-message"); // update based on actual error message selector

    // Methods
    public void enterUserId(String userId) {
        driver.findElement(userIdInput).clear();
        driver.findElement(userIdInput).sendKeys(userId);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isLoginButtonEnabled() {
        return driver.findElement(loginButton).isEnabled();
    }

    public void togglePasswordVisibility() {
        driver.findElement(passwordToggle).click();
    }

    public boolean isPasswordMasked() {
        String type = driver.findElement(passwordInput).getAttribute("type");
        return type.equals("password");
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
