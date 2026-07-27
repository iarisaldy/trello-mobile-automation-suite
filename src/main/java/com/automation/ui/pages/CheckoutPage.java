package com.automation.ui.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class CheckoutPage extends BasePage {

    private final By firstNameField = By.id("first_name");
    private final By lastNameField = By.id("last_name");
    private final By postalCodeField = By.id("postal_code");
    private final By continueButton = By.id("continue_checkout");
    private final By finishButton = By.id("finish_checkout");
    private final By successHeader = By.id("checkout_success_header");
    private final By errorMessage = By.id("checkout_error_message");

    public CheckoutPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public void fillCustomerInformation(String firstName, String lastName, String postalCode) {
        sendKeys(firstNameField, firstName);
        sendKeys(lastNameField, lastName);
        sendKeys(postalCodeField, postalCode);
        click(continueButton);
    }

    public void finishCheckout() {
        click(finishButton);
    }

    public boolean isCheckoutSuccessDisplayed() {
        return isDisplayed(successHeader);
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }
}
