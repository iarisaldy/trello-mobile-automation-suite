package com.automation.ui.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class CartPage extends BasePage {

    private final By cartItemTitle = By.id("cart_item_title");
    private final By cartItemPrice = By.id("cart_item_price");
    private final By continueShoppingButton = By.id("continue_shopping");
    private final By checkoutButton = By.id("checkout_button");

    public CartPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public boolean isCartItemPresent() {
        return isDisplayed(cartItemTitle);
    }

    public String getCartItemTitle() {
        return getText(cartItemTitle);
    }

    public String getCartItemPrice() {
        return getText(cartItemPrice);
    }

    public void clickContinueShopping() {
        click(continueShoppingButton);
    }

    public void proceedToCheckout() {
        click(checkoutButton);
    }
}
