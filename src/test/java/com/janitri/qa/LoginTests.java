package com.janitri.qa;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testLoginButtonDisabledWhenFieldsAreEmpty() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertFalse(loginPage.isLoginButtonEnabled(),
                "Login button should be disabled when fields are empty.");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testLoginButtonEnabledWhenFieldsAreFilled() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserId("validUser");  // Use actual valid credentials
        loginPage.enterPassword("validPass");
        Assert.assertTrue(loginPage.isLoginButtonEnabled(),
                "Login button should be enabled when username and password are filled.");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testPasswordMaskedButton() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPasswordMasked(), "Password should be masked by default.");

        loginPage.togglePasswordVisibility();
        Assert.assertFalse(loginPage.isPasswordMasked(), "Password should be visible after clicking the eye icon.");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testInvalidLoginShowErrorMsg() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserId("randomUser");
        loginPage.enterPassword("randomPass");
        loginPage.clickLogin();

        String errorMessage = loginPage.getErrorMessage();
        System.out.println("Error Message: " + errorMessage);
        Assert.assertTrue(errorMessage.toLowerCase().contains("invalid"),
                "Error message should indicate invalid credentials.");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testSuccessfulLoginRedirectsToDashboard() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserId("validUser");  // Replace with actual valid credentials
        loginPage.enterPassword("validPass");
        loginPage.clickLogin();

        // Wait for dashboard element to appear
        boolean dashboardVisible;
        if (driver.findElements(By.cssSelector(".dashboard, .home-page")).size() > 0) dashboardVisible = true;
        else dashboardVisible = false;
        Assert.assertTrue(dashboardVisible, "User should be redirected to the dashboard after successful login.");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testForgotPasswordLinkVisible() {
        boolean isVisible = driver.findElements(By.linkText("Forgot Password?")).size() > 0;
        Assert.assertTrue(isVisible, "Forgot Password link should be visible on login page.");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testUsernameFieldRequiredValidation() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserId("");
        loginPage.enterPassword("somePass");
        loginPage.clickLogin();

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.toLowerCase().contains("username"),
                "Error message should indicate that username is required.");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testPasswordFieldRequiredValidation() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserId("someUser");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.toLowerCase().contains("password"),
                "Error message should indicate that password is required.");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testPasswordRemainsMaskedAfterFailedLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserId("randomUser");
        loginPage.enterPassword("randomPass");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isPasswordMasked(),
                "Password should remain masked after failed login.");
    }
}
