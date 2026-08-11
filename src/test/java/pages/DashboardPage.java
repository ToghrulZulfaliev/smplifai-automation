package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//button[contains(@class,'header-logout-btn')]")
    private WebElement logoutButton;

    @FindBy(xpath = "//button[@type='button' and normalize-space()='Sign out']")
    private WebElement signOutButton;

    public void logout() {

        waitAndClick(logoutButton);

        waitAndClick(signOutButton);
    }
}