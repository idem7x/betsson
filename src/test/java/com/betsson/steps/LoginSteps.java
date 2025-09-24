package com.betsson.steps;

import com.betsson.pages.LoginPage;
import com.betsson.utils.DriverManager;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        loginPage = new LoginPage(DriverManager.getDriver());
    }

    @When("the user attempts to login with username {string} and password {string}")
    public void theUserAttemptsToLoginWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("the user should be successfully logged in")
    public void theUserShouldBeSuccessfullyLoggedIn() {
        Assert.assertTrue(loginPage.isLoginSuccessful());
    }

    @Then("the user should see an error message")
    public void theUserShouldSeeAnErrorMessage() {
        Assert.assertTrue(loginPage.isErrorDisplayed());
    }
}