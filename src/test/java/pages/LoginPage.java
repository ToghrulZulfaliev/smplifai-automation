package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit' and normalize-space()='Log In']")
    private WebElement loginButton;

    public void enterEmail(String email) {

        waitAndSendKeys(
                emailInput,
                email
        );
    }

    public void enterPassword(String password) {

        waitAndSendKeys(
                passwordInput,
                password
        );
    }

    public void clickLoginButton() {

        waitAndClick(loginButton);
    }
}