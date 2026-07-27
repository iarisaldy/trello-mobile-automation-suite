package com.automation.ui.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class ProductListPage extends BasePage {

    private final By filterButton = By.id("filter_button");
    private final By priceFilterOption = By.id("filter_price_low_high");
    private final By firstProductItem = By.id("product_item_1");
    private final By addToCartButton = By.id("add_to_cart_button");
    private final By cartIcon = By.id("cart_icon");

    public ProductListPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public void applyPriceFilter() {
        click(filterButton);
        click(priceFilterOption);
    }

    public void selectFirstProduct() {
        click(firstProductItem);
    }

    public void addFirstProductToCart() {
        click(addToCartButton);
    }

    public void goToCart() {
        click(cartIcon);
    }

    public boolean isProductListLoaded() {
        return isDisplayed(firstProductItem);
    }
}
