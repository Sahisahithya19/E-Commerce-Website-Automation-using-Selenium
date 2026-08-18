package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.DriverManager;

public class CartTest {

    private ProductsPage productsPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setUp() {

        DriverManager.initializeDriver();

        DriverManager.getDriver().get(
                "https://www.saucedemo.com/"
        );

        LoginPage loginPage =
                new LoginPage(
                        DriverManager.getDriver()
                );

        productsPage =
                loginPage.login(
                        "standard_user",
                        "secret_sauce"
                );
    }

    @Test
    public void verifyCartTest() {

        productsPage.addProductByName(
                "Sauce Labs Backpack"
        );

        productsPage.openCart();

        cartPage =
                new CartPage(
                        DriverManager.getDriver()
                );

        Assert.assertEquals(
                cartPage.getCartTitle(),
                "Your Cart"
        );

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                1
        );
    }

    @Test
    public void addMultipleProductsTest() {

        productsPage.addProductByName(
                "Sauce Labs Backpack"
        );

        productsPage.addProductByName(
                "Sauce Labs Bike Light"
        );

        productsPage.openCart();

        cartPage =
                new CartPage(
                        DriverManager.getDriver()
                );

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                2
        );
    }

    @AfterMethod
    public void tearDown() {

        DriverManager.quitDriver();
    }
}
