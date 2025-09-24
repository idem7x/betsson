package com.betsson.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String username, String password) {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId("test-Username")));
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(AppiumBy.accessibilityId("test-Password"));
        passwordField.sendKeys(password);

        WebElement loginButton = driver.findElement(AppiumBy.accessibilityId("test-LOGIN"));
        loginButton.click();
    }

    public boolean isErrorDisplayed() {
        try {
            WebElement errorMessage = driver.findElement(AppiumBy.accessibilityId("test-Error message"));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginSuccessful() {
        try {
            Thread.sleep(2000);
            WebElement productsTitle = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='PRODUCTS']"));
            return productsTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}