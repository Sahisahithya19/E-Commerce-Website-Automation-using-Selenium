package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.DriverManager;

public class ProductTest {

    private ProductsPage productsPage;

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
    public void verifyProductCount() {

        int productCount =
                productsPage.getProductCount();

        Assert.assertEquals(
                productCount,
                6
        );
    }

    @Test
    public void addProductToCartTest() {

        productsPage.addProductByName(
                "Sauce Labs Backpack"
        );

        productsPage.openCart();

        Assert.assertEquals(
                DriverManager.getDriver()
                        .findElements(
                                org.openqa.selenium.By.cssSelector(".cart_item")
                        ).size(),
                1
        );
    }

    @Test
    public void sortProductsTest() {

        productsPage.sortProducts("lohi");

        Assert.assertEquals(
                productsPage.getPageTitle(),
                "Products"
        );
    }

    @AfterMethod
    public void tearDown() {

        DriverManager.quitDriver();
    }
}
