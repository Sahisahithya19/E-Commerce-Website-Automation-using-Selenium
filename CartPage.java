package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private By cartTitle =
            By.cssSelector(".title");

    private By cartItems =
            By.cssSelector(".cart_item");

    private By checkoutButton =
            By.id("checkout");

    private By continueShoppingButton =
            By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getCartTitle() {

        return getText(cartTitle);
    }

    public int getCartItemCount() {

        return driver.findElements(cartItems).size();
    }

    public void clickCheckout() {

        click(checkoutButton);
    }

    public void continueShopping() {

        click(continueShoppingButton);
    }
}
