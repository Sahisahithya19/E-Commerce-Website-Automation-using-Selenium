package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsPage extends BasePage {

    private By pageTitle =
            By.cssSelector(".title");

    private By productNames =
            By.cssSelector(".inventory_item_name");

    private By addToCartButtons =
            By.cssSelector(".btn_inventory");

    private By cartIcon =
            By.className("shopping_cart_link");

    private By sortDropdown =
            By.className("product_sort_container");

    private By menuButton =
            By.id("react-burger-menu-btn");

    private By logoutButton =
            By.id("logout_sidebar_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {

        return getText(pageTitle);
    }

    public int getProductCount() {

        List<WebElement> products =
                driver.findElements(productNames);

        return products.size();
    }

    public void addFirstProductToCart() {

        List<WebElement> buttons =
                driver.findElements(addToCartButtons);

        buttons.get(0).click();
    }

    public void addProductByName(String productName) {

        List<WebElement> products =
                driver.findElements(productNames);

        for (WebElement product : products) {

            if (product.getText().equalsIgnoreCase(productName)) {

                WebElement parent =
                        product.findElement(
                                By.xpath(
                                        "./ancestor::div[contains(@class,'inventory_item')]"
                                )
                        );

                parent.findElement(
                        By.cssSelector(".btn_inventory")
                ).click();

                break;
            }
        }
    }

    public void openCart() {

        click(cartIcon);
    }

    public void sortProducts(String option) {

        Select select =
                new Select(driver.findElement(sortDropdown));

        select.selectByValue(option);
    }

    public void logout() {

        click(menuButton);
        click(logoutButton);
    }
}
