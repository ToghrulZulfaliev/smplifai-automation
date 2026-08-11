package stepDefinations;

import config.WebDriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.NotificationsPage;

public class NotificationsStep {

    private final NotificationsPage notificationsPage;


    public NotificationsStep() {

        notificationsPage =
                new NotificationsPage(
                        WebDriverManager.getChromeDriver()
                );
    }


    // =========================================================
    // OPEN NOTIFICATIONS PAGE
    // =========================================================

    @Given("İstifadəçi Notifications səhifəsindədir")
    public void userIsOnNotificationsPage() {

        System.out.println(
                ">>> Notifications səhifəsinə keçid başlayır"
        );


        // ƏSAS HİSSƏ BUDUR
        notificationsPage.openNotificationsPage();


        Assert.assertTrue(
                notificationsPage.isNotificationsPageOpened(),
                "Notifications səhifəsi açılmayıb!"
        );


        System.out.println(
                "✓ İstifadəçi Notifications səhifəsindədir"
        );
    }


    // =========================================================
    // MARK ALL AS READ
    // =========================================================

    @When("İstifadəçi Mark all as read düyməsinin vəziyyətini yoxlayır")
    public void checkMarkAllAsRead() {

        notificationsPage.markAllAsReadIfPossible();


        System.out.println(
                "✓ Mark all as read vəziyyəti yoxlanıldı"
        );
    }


    // =========================================================
    // ALL READ CHECK
    // =========================================================

    @Then("Bütün notification-lar oxunmuş olmalıdır")
    public void allNotificationsShouldBeRead() {

        Assert.assertTrue(
                notificationsPage.areAllNotificationsRead(),
                "Bütün notification-lar read vəziyyətində deyil!"
        );


        System.out.println(
                "✓ Bütün notification-lar oxunmuşdur"
        );
    }


    // =========================================================
    // NOTIFICATION EXISTS
    // =========================================================

    @And("Notification listində ən azı bir notification mövcuddur")
    public void notificationShouldExist() {

        Assert.assertTrue(
                notificationsPage.hasNotification(),
                "Notification listində notification yoxdur!"
        );


        System.out.println(
                "✓ Notification listində notification mövcuddur"
        );
    }


    // =========================================================
    // DELETE SINGLE
    // =========================================================

    @When("İstifadəçi bir notification-un Delete düyməsinə klik edir")
    public void deleteSingleNotification() {

        notificationsPage.deleteOneNotification();


        System.out.println(
                "✓ Bir notification üçün Delete klik edildi"
        );
    }


    // =========================================================
    // SINGLE DELETE CHECK
    // =========================================================

    @Then("Notification sayı bir ədəd azalmalıdır")
    public void notificationCountShouldDecrease() {

        Assert.assertTrue(
                notificationsPage.isOneNotificationDeleted(),
                "Notification sayı bir ədəd azalmadı!"
        );


        System.out.println(
                "✓ Notification sayı 1 ədəd azaldı"
        );
    }


    // =========================================================
    // DELETE ALL
    // =========================================================

    @When("İstifadəçi Delete all düyməsinə klik edir")
    public void clickDeleteAll() {

        notificationsPage.clickDeleteAll();


        System.out.println(
                "✓ Delete all klik edildi"
        );
    }


    // =========================================================
    // DELETE ALL CONFIRMATION
    // =========================================================

    @Then("Delete all confirmation pəncərəsi açılmalıdır")
    public void deleteAllConfirmationShouldOpen() {

        Assert.assertTrue(
                notificationsPage.isDeleteAllConfirmationOpened(),
                "Delete all confirmation pəncərəsi açılmadı!"
        );


        System.out.println(
                "✓ Delete all confirmation pəncərəsi açıldı"
        );
    }


    // =========================================================
    // CONFIRM DELETE ALL
    // =========================================================

    @When("İstifadəçi confirmation pəncərəsində Delete all düyməsinə klik edir")
    public void confirmDeleteAll() {

        notificationsPage.confirmDeleteAll();


        System.out.println(
                "✓ Delete all təsdiqləndi"
        );
    }


    // =========================================================
    // DELETE ALL CHECK
    // =========================================================

    @Then("Bütün notification-lar silinməlidir")
    public void allNotificationsShouldBeDeleted() {

        Assert.assertTrue(
                notificationsPage.areAllNotificationsDeleted(),
                "Bütün notification-lar silinmədi!"
        );


        System.out.println(
                "✓ Bütün notification-lar uğurla silindi"
        );
    }
}