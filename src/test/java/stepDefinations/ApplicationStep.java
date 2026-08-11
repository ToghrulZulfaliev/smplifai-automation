package stepDefinations;

import config.WebDriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.ApplicationPage;

public class ApplicationStep {

    private final ApplicationPage applicationPage;


    public ApplicationStep() {

        applicationPage =
                new ApplicationPage(
                        WebDriverManager.getChromeDriver()
                );
    }


    // =========================================================
    // DASHBOARD
    // =========================================================

    @Given("İstifadəçi sistemə login olub Dashboard səhifəsindədir")
    public void userIsOnDashboard() {

        String currentUrl =
                WebDriverManager
                        .getChromeDriver()
                        .getCurrentUrl();


        System.out.println(
                "Application scenario URL: "
                        + currentUrl
        );


        Assert.assertTrue(
                applicationPage.isDashboardDisplayed(),
                "Dashboard səhifəsi açılmayıb! URL: "
                        + currentUrl
        );


        System.out.println(
                "✓ İstifadəçi Dashboard səhifəsindədir"
        );
    }


    // =========================================================
    // NEW APPLICATION
    // =========================================================

    @When("İstifadəçi New Application düyməsinə klik edir")
    public void clickNewApplication() {

        applicationPage.clickNewApplication();

        System.out.println(
                "✓ New Application klik edildi"
        );
    }


    // =========================================================
    // NEW APPLICATION MODAL
    // =========================================================

    @Then("New Application pəncərəsi açılmalıdır")
    public void newApplicationShouldOpen() {

        Assert.assertTrue(
                applicationPage.isNewApplicationOpened(),
                "New Application pəncərəsi açılmadı!"
        );

        System.out.println(
                "✓ New Application pəncərəsi açıldı"
        );
    }


    // =========================================================
    // PHONE
    // =========================================================

    @And("İstifadəçi düzgün telefon nömrəsi daxil edir")
    public void enterPhoneNumber() {

        applicationPage.enterPhoneNumber(
                "+994501234567"
        );

        System.out.println(
                "✓ Telefon nömrəsi daxil edildi"
        );
    }


    // =========================================================
    // CHOOSE
    // =========================================================

    @And("İstifadəçi Choose düyməsinə klik edir")
    public void clickChoose() {

        applicationPage.clickChoose();

        System.out.println(
                "✓ Choose klik edildi"
        );
    }


    // =========================================================
    // ENROLLMENT
    // =========================================================

    @And("Application Enrollment Agreement pəncərəsi açılmalıdır")
    public void enrollmentAgreementShouldOpen() {

        Assert.assertTrue(
                applicationPage.isEnrollmentAgreementOpened(),
                "Application Enrollment Agreement açılmadı!"
        );

        System.out.println(
                "✓ Application Enrollment Agreement açıldı"
        );
    }


    // =========================================================
    // PASSPORT AREA
    // =========================================================

    @And("İstifadəçi Passport sənədi üçün Upload sahəsinə klik edir")
    public void passportUploadArea() {

        System.out.println(
                "✓ Passport Upload sahəsi hazırdır"
        );
    }


    // =========================================================
    // PASSPORT
    // =========================================================

    @And("İstifadəçi Passport sənədini yükləyir")
    public void uploadPassport() {

        applicationPage.uploadPassport();

        System.out.println(
                "✓ Passport yükləndi"
        );
    }


    // =========================================================
    // VISA AREA
    // =========================================================

    @And("İstifadəçi Visa sənədi üçün Upload sahəsinə klik edir")
    public void visaUploadArea() {

        System.out.println(
                "✓ Visa Upload sahəsi hazırdır"
        );
    }


    // =========================================================
    // VISA
    // =========================================================

    @And("İstifadəçi Visa sənədini yükləyir")
    public void uploadVisa() {

        applicationPage.uploadVisa();

        System.out.println(
                "✓ Visa yükləndi"
        );
    }


    // =========================================================
    // I-20 AREA
    // =========================================================

    @And("İstifadəçi I-20 sənədi üçün Upload sahəsinə klik edir")
    public void i20UploadArea() {

        System.out.println(
                "✓ I-20 Upload sahəsi hazırdır"
        );
    }


    // =========================================================
    // I-20
    // =========================================================

    @And("İstifadəçi I-20 sənədini yükləyir")
    public void uploadI20() {

        applicationPage.uploadI20();

        System.out.println(
                "✓ I-20 yükləndi"
        );
    }


    // =========================================================
    // SUBMIT
    // =========================================================

    @And("İstifadəçi Submit düyməsinə klik edir")
    public void clickSubmit() {

        applicationPage.clickSubmitDocuments();

        System.out.println(
                "✓ Submit Documents klik edildi"
        );
    }


    // =========================================================
    // APPLICATION CREATED
    // =========================================================

    @And("Application uğurla yaradılmalıdır")
    public void applicationShouldBeCreated() {

        Assert.assertTrue(
                applicationPage.isDashboardDisplayed(),
                "Application yaradıldıqdan sonra Dashboard görünmədi!"
        );

        System.out.println(
                "✓ Application uğurla yaradıldı"
        );
    }


    // =========================================================
    // RETURN DASHBOARD
    // =========================================================

    @And("İstifadəçi Dashboard səhifəsinə qaytarılmalıdır")
    public void shouldReturnToDashboard() {

        Assert.assertTrue(
                applicationPage.isDashboardDisplayed(),
                "Dashboard səhifəsinə qayıtmadı!"
        );

        System.out.println(
                "✓ Dashboard səhifəsinə qayıdıldı"
        );
    }


    // =========================================================
    // APPLICATION LIST
    // =========================================================

    @And("Application list-də yeni application görünməlidir")
    public void applicationShouldBeVisible() {

        Assert.assertTrue(
                applicationPage.isApplicationVisible(),
                "Yeni application list-də görünmür!"
        );

        System.out.println(
                "✓ Yeni Application list-də görünür"
        );
    }


    // =========================================================
    // VIEW
    // =========================================================

    @And("İstifadəçi yeni application üçün View düyməsinə klik edir")
    public void clickView() {

        applicationPage.clickView();

        Assert.assertTrue(
                applicationPage.isDocumentsPageOpened(),
                "Documents səhifəsi açılmadı!"
        );

        System.out.println(
                "✓ View klik edildi"
        );
    }


    // =========================================================
    // PASSPORT CHECK
    // =========================================================

    @And("Yüklənmiş Passport sənədi görünməlidir")
    public void passportShouldBeVisible() {

        Assert.assertTrue(
                applicationPage.isPassportVisible(),
                "Passport sənədi görünmür!"
        );

        System.out.println(
                "✓ Passport sənədi görünür"
        );
    }


    // =========================================================
    // VISA CHECK
    // =========================================================

    @When("Yüklənmiş Visa sənədi görünməlidir")
    public void visaShouldBeVisible() {

        Assert.assertTrue(
                applicationPage.isVisaVisible(),
                "Visa sənədi görünmür!"
        );

        System.out.println(
                "✓ Visa sənədi görünür"
        );
    }


    // =========================================================
    // I-20 CHECK
    // =========================================================

    @Then("Yüklənmiş I-20 sənədi görünməlidir")
    public void i20ShouldBeVisible() {

        Assert.assertTrue(
                applicationPage.isI20Visible(),
                "I-20 sənədi görünmür!"
        );

        System.out.println(
                "✓ I-20 sənədi görünür"
        );
    }


    // =========================================================
    // DOWNLOAD
    // =========================================================

    @When("İstifadəçi application üçün Download düyməsinə klik edir")
    public void clickDownload() {

        applicationPage.clickDownload();

        System.out.println(
                "✓ Download klik edildi"
        );
    }


    @Then("Downloads səhifəsi açılmalıdır")
    public void downloadsPageShouldOpen() {

        Assert.assertTrue(
                applicationPage.isDownloadsPageOpened(),
                "Downloads səhifəsi açılmadı!"
        );

        System.out.println(
                "✓ Downloads səhifəsi açıldı"
        );
    }


    // =========================================================
    // DELETE APPLICATION
    // =========================================================

    @When("İstifadəçi application üçün Delete düyməsinə klik edir")
    public void clickDelete() {

        applicationPage.clickDelete();

        System.out.println(
                "✓ Application Delete klik edildi"
        );
    }


    @Then("Delete confirmation pəncərəsi açılmalıdır")
    public void deleteConfirmationShouldOpen() {

        Assert.assertTrue(
                applicationPage.isDeleteConfirmationOpened(),
                "Delete confirmation açılmadı!"
        );

        System.out.println(
                "✓ Delete confirmation açıldı"
        );
    }


    @And("İstifadəçi silinməni təsdiqləyir")
    public void confirmDelete() {

        applicationPage.confirmDelete();

        System.out.println(
                "✓ Delete təsdiqləndi"
        );
    }


    @Then("Delete confirmation pəncərəsi bağlanmalıdır")
    public void deleteConfirmationShouldClose() {

        Assert.assertTrue(
                applicationPage.isDeleteConfirmationClosed(),
                "Delete confirmation bağlanmadı!"
        );

        System.out.println(
                "✓ Delete confirmation bağlandı"
        );
    }
}