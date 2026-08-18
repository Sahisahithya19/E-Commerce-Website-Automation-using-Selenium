package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.DriverManager;

public class LoginTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {

        DriverManager.initializeDriver();

        DriverManager.getDriver().get(
                "https://www.saucedemo.com/"
        );

        loginPage =
                new LoginPage(
                        DriverManager.getDriver()
                );
    }

    @Test
    public void validLoginTest() {

        ProductsPage productsPage =
                loginPage.login(
                        "standard_user",
                        "secret_sauce"
                );

        Assert.assertEquals(
                productsPage.getPageTitle(),
                "Products"
        );
    }

    @Test
    public void invalidLoginTest() {

        loginPage.enterUsername("wrong_user");

        loginPage.enterPassword("wrong_password");

        loginPage.clickLogin();

        Assert.assertTrue(
                loginPage.getErrorMessage()
                        .contains("Username and password do not match")
        );
    }

    @AfterMethod
    public void tearDown() {

        DriverManager.quitDriver();
    }
}
