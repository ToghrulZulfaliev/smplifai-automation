package pages;

import config.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {

        driver = WebDriverManager.getChromeDriver();

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );

        PageFactory.initElements(driver, this);
    }

    public void waitAndClick(WebElement element) {

        wait.until(
                ExpectedConditions.elementToBeClickable(element)
        );

        element.click();
    }

    public void waitAndSendKeys(
            WebElement element,
            String text
    ) {

        wait.until(
                ExpectedConditions.visibilityOf(element)
        );

        element.clear();

        element.sendKeys(text);
    }
}