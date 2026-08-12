package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.SettingsPage;

public class SettingsStep {

    private SettingsPage settingsPage;

    public SettingsStep() {
        settingsPage = new SettingsPage();
    }

    @Given("İstifadəçi Settings səhifəsindədir")
    public void istifadəçi_settings_səhifəsindədir() {

        settingsPage.clickSettingButton();

        System.out.println("✓ İstifadəçi Settings səhifəsinə keçdi");
    }

    @Given("İstifadəçi theme bölməsinə scroll edir")
    public void istifadəçi_theme_bölməsinə_scroll_edir() {

        settingsPage.scrollToDarkButton();

        System.out.println("✓ Theme bölməsinə scroll edildi");
    }

    @When("İstifadəçi Dark theme seçir")
    public void istifadəçi_dark_theme_seçir() {

        settingsPage.clickDarkButton();

        System.out.println("✓ Dark theme seçildi");
    }

    @Then("Səhifə Dark theme rejiminə keçməlidir")
    public void səhifə_dark_theme_rejiminə_keçməlidir() {

        boolean darkThemeActive = settingsPage.isDarkThemeActive();

        Assert.assertTrue(
                darkThemeActive,
                "Dark theme aktiv olmadı!"
        );

        System.out.println("✓ Dark theme uğurla aktiv oldu");
    }
}