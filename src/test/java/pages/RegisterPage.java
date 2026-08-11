package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RegisterPage extends BasePage {

    @FindBy(xpath = "//a[@href='/auth/register']")
    private WebElement signUpHereLink;

    @FindBy(xpath = "//input[@name='first_name']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@name='last_name']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@name='phone']")
    private WebElement phoneInput;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@name='repeat_password']")
    private WebElement repeatPasswordInput;

    @FindBy(xpath = "//input[@type='checkbox' and contains(@class,'custom-checkbox')]")
    private List<WebElement> checkboxes;

    @FindBy(xpath = "//button[@type='submit' and normalize-space()='Create account']")
    private WebElement createAccountButton;


    public void clickSignUpHereLink() {

        waitAndClick(signUpHereLink);
    }

    public void enterFirstName(String firstName) {

        waitAndSendKeys(
                firstNameInput,
                firstName
        );
    }

    public void enterLastName(String lastName) {

        waitAndSendKeys(
                lastNameInput,
                lastName
        );
    }

    public void enterEmail(String email) {

        waitAndSendKeys(
                emailInput,
                email
        );
    }

    public void enterPhone(String phone) {

        waitAndSendKeys(
                phoneInput,
                phone
        );
    }

    public void enterPassword(String password) {

        waitAndSendKeys(
                passwordInput,
                password
        );
    }

    public void enterRepeatPassword(String password) {

        waitAndSendKeys(
                repeatPasswordInput,
                password
        );
    }

    public void selectAllCheckboxes() {

        for (WebElement checkbox : checkboxes) {

            if (!checkbox.isSelected()) {

                waitAndClick(checkbox);
            }
        }
    }

    public void clickCreateAccountButton() {

        waitAndClick(createAccountButton);
    }
}