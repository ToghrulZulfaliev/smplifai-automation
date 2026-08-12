package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SettingsPage extends BasePage {

    @FindBy(id = "nav-settings")
    private WebElement settingsButton;

    @FindBy(xpath = "//button[contains(@class,'selector-btn') and contains(.,'Dark')]")
    private WebElement darkButton;


    public void clickSettingButton() {

        waitAndClick(settingsButton);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/settings"));

        System.out.println("✓ Settings düyməsinə klik edildi");
        System.out.println("Current URL: " + driver.getCurrentUrl());
    }


    public void scrollToDarkButton() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement dark =
                wait.until(ExpectedConditions.visibilityOf(darkButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
                dark
        );

        System.out.println("✓ Dark theme bölməsinə scroll edildi");
    }


    public void clickDarkButton() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement dark =
                wait.until(ExpectedConditions.elementToBeClickable(darkButton));

        dark.click();

        System.out.println("✓ Dark düyməsinə klik edildi");
    }


    public boolean isDarkThemeActive() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(driver ->
                darkButton.getAttribute("class").contains("is-active")
        );

        String classValue = darkButton.getAttribute("class");

        System.out.println("Dark button class: " + classValue);

        return classValue.contains("is-active");
    }
}