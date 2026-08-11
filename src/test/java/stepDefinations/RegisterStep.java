package stepDefinations;

import config.WebDriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.RegisterPage;

import java.time.Duration;

public class RegisterStep {

    private final RegisterPage registerPage;

    public RegisterStep() {
        registerPage = new RegisterPage();
    }

    @When("İstifadəçi {string} linkinə klik edir")
    public void istifadəçi_linkinə_klik_edir(String linkName) {

        if (linkName.equalsIgnoreCase("Sign up here")) {
            registerPage.clickSignUpHereLink();
        }
    }

    @Then("İstifadəçi qeydiyyat səhifəsinə yönləndirilməlidir")
    public void istifadəçi_qeydiyyat_səhifəsinə_yönləndirilməlidir() {

        WebDriverWait wait = new WebDriverWait(
                WebDriverManager.getChromeDriver(),
                Duration.ofSeconds(10)
        );

        wait.until(driver ->
                driver.getCurrentUrl().contains("/auth/register")
        );

        Assert.assertTrue(
                WebDriverManager.getChromeDriver()
                        .getCurrentUrl()
                        .contains("/auth/register"),
                "Register səhifəsinə keçid edilmədi!"
        );
    }

    @When("İstifadəçi qeydiyyat üçün düzgün ad daxil edir")
    public void istifadəçi_qeydiyyat_üçün_düzgün_ad_daxil_edir() {

        registerPage.enterFirstName("Togrul");
    }

    @And("İstifadəçi qeydiyyat üçün düzgün soyad daxil edir")
    public void istifadəçi_qeydiyyat_üçün_düzgün_soyad_daxil_edir() {

        registerPage.enterLastName("Zulfaliyev");
    }

    @And("İstifadəçi qeydiyyat üçün düzgün email daxil edir")
    public void istifadəçi_qeydiyyat_üçün_düzgün_email_daxil_edir() {

        String email =
                "testautomation"
                        + System.currentTimeMillis()
                        + "@gmail.com";

        registerPage.enterEmail(email);

        System.out.println("Register email: " + email);
    }

    @And("İstifadəçi qeydiyyat üçün düzgün telefon nömrəsi daxil edir")
    public void istifadəçi_qeydiyyat_üçün_düzgün_telefon_daxil_edir() {

        registerPage.enterPhone("+994501234567");
    }

    @And("İstifadəçi qeydiyyat üçün düzgün şifrə daxil edir")
    public void istifadəçi_qeydiyyat_üçün_düzgün_şifrə_daxil_edir() {

        registerPage.enterPassword("Test123456!");
    }

    @And("İstifadəçi qeydiyyat üçün şifrəni təkrar daxil edir")
    public void istifadəçi_qeydiyyat_üçün_şifrəni_təkrar_daxil_edir() {

        registerPage.enterRepeatPassword("Test123456!");
    }

    @And("İstifadəçi Terms of Use və Privacy Policy checkbox-ını seçir")
    public void istifadəçi_checkbox_seçir() {

        registerPage.selectAllCheckboxes();
    }

    @And("İstifadəçi Create account düyməsinə klik edir")
    public void istifadəçi_create_account_düyməsinə_klik_edir() {

        registerPage.clickCreateAccountButton();
    }

    @Then("İstifadəçi sistemdə uğurla qeydiyyatdan keçməlidir")
    public void istifadəçi_sistemdə_uğurla_qeydiyyatdan_keçməlidir() {

        String currentUrl =
                WebDriverManager.getChromeDriver().getCurrentUrl();

        System.out.println(
                "Register sonrası URL: " + currentUrl
        );


    }
}