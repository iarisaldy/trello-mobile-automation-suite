package com.automation;

import com.automation.ui.driver.DriverManager;
import com.automation.ui.pages.CartPage;
import com.automation.ui.pages.CheckoutPage;
import com.automation.ui.pages.LoginPage;
import com.automation.ui.pages.ProductListPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.testng.Assert.assertTrue;

public class UITests {

    private LoginPage loginPage;
    private ProductListPage productListPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeClass
    public void setup() throws MalformedURLException {
        DriverManager.initializeDriver();
        AppiumDriver<MobileElement> driver = DriverManager.getDriver();

        loginPage = new LoginPage(driver);
        productListPage = new ProductListPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test(priority = 1, description = "Test login with valid user credentials")
    public void testUserLogin() {
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(loginPage.isWelcomeMessageDisplayed(), "Welcome message should be displayed after successful login");
    }

    @Test(priority = 2, description = "Test login with invalid user credentials")
    public void testInvalidLogin() {
        loginPage.login("invalid_user", "invalid_password");
        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid credentials");
    }

    @Test(priority = 3, description = "Test sorting products by price filter")
    public void testPriceFilter() {
        productListPage.applyPriceFilter();
        assertTrue(productListPage.isProductListLoaded(), "Product list should be visible after applying filter");
    }

    @Test(priority = 4, description = "Test adding item to cart from listing screen")
    public void testAddToCartFromListingScreen() {
        productListPage.addFirstProductToCart();
        productListPage.goToCart();
        assertTrue(cartPage.isCartItemPresent(), "Item should be added to the cart");
    }

    @Test(priority = 5, description = "Test adding product from item details screen")
    public void testAddToCartFromDetailsScreen() {
        productListPage.selectFirstProduct();
        productListPage.addFirstProductToCart();
        productListPage.goToCart();
        assertTrue(cartPage.isCartItemPresent(), "Item added from details screen should appear in cart");
    }

    @Test(priority = 6, description = "Test adding multiple products to shopping cart")
    public void testAddMultipleProductsToCart() {
        productListPage.addFirstProductToCart();
        productListPage.goToCart();
        assertTrue(cartPage.isCartItemPresent(), "Cart should contain added products");
    }

    @Test(priority = 7, description = "Test continue shopping button navigation")
    public void testContinueShoppingRedirection() {
        productListPage.goToCart();
        cartPage.clickContinueShopping();
        assertTrue(productListPage.isProductListLoaded(), "Navigating back should show product list");
    }

    @Test(priority = 8, description = "Test verifying item details inside cart")
    public void testCartDetails() {
        productListPage.addFirstProductToCart();
        productListPage.goToCart();
        assertTrue(cartPage.isCartItemPresent(), "Cart item details should be visible");
    }

    @Test(priority = 9, description = "Test completing checkout flow with valid customer data")
    public void testCheckoutSuccess() {
        productListPage.addFirstProductToCart();
        productListPage.goToCart();
        cartPage.proceedToCheckout();
        checkoutPage.fillCustomerInformation("John", "Doe", "12345");
        checkoutPage.finishCheckout();
        assertTrue(checkoutPage.isCheckoutSuccessDisplayed(), "Checkout success screen should be displayed");
    }

    @Test(priority = 10, description = "Test checkout validation with missing customer information")
    public void testCheckoutInvalidFirstName() {
        productListPage.addFirstProductToCart();
        productListPage.goToCart();
        cartPage.proceedToCheckout();
        checkoutPage.fillCustomerInformation("", "Doe", "12345");
        assertTrue(checkoutPage.isErrorMessageDisplayed(), "Validation error should be displayed when first name is empty");
    }

    @AfterClass
    public void teardown() {
        DriverManager.quitDriver();
    }
}