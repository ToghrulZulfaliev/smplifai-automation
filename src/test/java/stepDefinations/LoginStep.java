package stepDefinations;

import config.WebDriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.LoginPage;

import java.time.Duration;

public class LoginStep {

    private final LoginPage loginPage;

    public LoginStep() {
        loginPage = new LoginPage();
    }

    @Given("İstifadəçi Smplifai giriş səhifəsindədir")
    public void istifadəçi_smplifai_giriş_səhifəsindədir() {

        WebDriverManager.getChromeDriver()
                .get("https://appstudentsecret.smplifai.com/auth/login");
    }

    @When("İstifadəçi düzgün email daxil edir")
    public void istifadəçi_düzgün_email_daxil_edir() {

        loginPage.enterEmail("toghrulzulfaliev8@gmail.com");
    }

    @And("İstifadəçi düzgün şifrə daxil edir")
    public void istifadəçi_düzgün_şifrə_daxil_edir() {

        loginPage.enterPassword("T7772288l");
    }

    @And("İstifadəçi {string} düyməsinə klik edir")
    public void istifadəçi_düyməsinə_klik_edir(String buttonName) {

        if (buttonName.equalsIgnoreCase("Log In")) {
            loginPage.clickLoginButton();
        }
    }

    @Then("İstifadəçi sistemə uğurla daxil olmalıdır")
    public void istifadəçi_sistemə_uğurla_daxil_olmalıdır() {

        WebDriverWait wait = new WebDriverWait(
                WebDriverManager.getChromeDriver(),
                Duration.ofSeconds(15)
        );

        wait.until(driver ->
                !driver.getCurrentUrl().contains("/auth/login")
        );

        String currentUrl =
                WebDriverManager.getChromeDriver().getCurrentUrl();

        Assert.assertFalse(
                currentUrl.contains("/auth/login"),
                "Login uğursuz oldu!"
        );

        System.out.println("Login uğurla tamamlandı");
    }
}