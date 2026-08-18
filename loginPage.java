package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By username =
            By.id("user-name");

    private By password =
            By.id("password");

    private By loginButton =
            By.id("login-button");

    private By errorMessage =
            By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String usernameText) {

        enterText(username, usernameText);
    }

    public void enterPassword(String passwordText) {

        enterText(password, passwordText);
    }

    public void clickLogin() {

        click(loginButton);
    }

    public ProductsPage login(
            String usernameText,
            String passwordText) {

        enterUsername(usernameText);
        enterPassword(passwordText);
        clickLogin();

        return new ProductsPage(driver);
    }

    public String getErrorMessage() {

        return getText(errorMessage);
    }
}
