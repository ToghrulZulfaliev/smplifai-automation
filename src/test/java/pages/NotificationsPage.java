package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class NotificationsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private int notificationCountBeforeDelete;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public NotificationsPage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );
    }


    // =========================================================
    // LOCATORS
    // =========================================================

    // Sidebar Notifications
    // REAL HTML:
    // <a id="nav-notifications" href="/notifications">
    private final By notificationsMenu =
            By.id("nav-notifications");


    // Notifications page title
    private final By notificationsTitle =
            By.xpath(
                    "//*[self::h1 or self::h2]" +
                            "[normalize-space()='Notifications']"
            );


    // Mark all as read
    private final By markAllAsReadButton =
            By.cssSelector(
                    "button.mark-all-btn"
            );


    // Delete all
    private final By deleteAllButton =
            By.cssSelector(
                    "button.delete-all-btn"
            );


    // =========================================================
    // SINGLE DELETE BUTTON
    // =========================================================

    /*
     * REAL HTML:
     *
     * <button
     *      type="button"
     *      class="notification-delete-btn"
     *      aria-label="Delete notification">
     *
     * Ona görə SVG-yə görə yox,
     * birbaşa button-un class-ına görə tapırıq.
     */
    private final By singleNotificationDeleteButtons =
            By.cssSelector(
                    "button.notification-delete-btn"
            );


    // =========================================================
    // SINGLE DELETE CONFIRM BUTTON
    // =========================================================

    /*
     * Individual notification siləndən sonra
     * confirmation modal çıxarsa,
     * modal daxilindəki Delete düyməsini tapır.
     *
     * Bir neçə variant saxlayırıq:
     *
     * Delete
     * Yes, delete
     * Confirm
     */
    private final By singleDeleteConfirmButton =
            By.xpath(
                    "//button[" +
                            "normalize-space()='Delete' " +
                            "or normalize-space()='Yes, delete' " +
                            "or normalize-space()='Confirm'" +
                            "]"
            );


    // =========================================================
    // DELETE ALL CONFIRM BUTTON
    // =========================================================

    private final By confirmDeleteAllButton =
            By.xpath(
                    "//button[" +
                            "normalize-space()='Delete all' " +
                            "and not(contains(@class,'delete-all-btn'))" +
                            "]"
            );


    // =========================================================
    // NOTIFICATION TITLES
    // =========================================================

    /*
     * Hazırda sənin notification-ların:
     *
     * Application Status Updated
     */
    private final By notificationTitles =
            By.xpath(
                    "//*[normalize-space()='Application Status Updated']"
            );


    // =========================================================
    // OPEN NOTIFICATIONS PAGE
    // =========================================================

    public void openNotificationsPage() {

        System.out.println(
                ">>> Notifications səhifəsinə keçid başlayır"
        );

        System.out.println(
                "Başlanğıc URL: " + driver.getCurrentUrl()
        );


        /*
         * Notifications menu DOM-da yaranana qədər gözlə.
         */
        WebElement notifications =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                notificationsMenu
                        )
                );


        System.out.println(
                "✓ Notifications menu DOM-da tapıldı"
        );


        scrollToElement(
                notifications
        );


        /*
         * Əvvəl normal click.
         *
         * İşləməsə JS click.
         */
        try {

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            notifications
                    )
            );

            notifications.click();

            System.out.println(
                    "✓ Notifications normal click edildi"
            );

        } catch (Exception e) {

            System.out.println(
                    "Normal click alınmadı. JS click edilir..."
            );

            jsClick(
                    notifications
            );

            System.out.println(
                    "✓ Notifications JS click edildi"
            );
        }


        /*
         * URL /notifications olmalıdır.
         */
        wait.until(
                ExpectedConditions.urlContains(
                        "/notifications"
                )
        );


        System.out.println(
                "Notifications sonrası URL: "
                        + driver.getCurrentUrl()
        );


        /*
         * Notifications page elementlərindən
         * ən azı biri DOM-da yaranmalıdır.
         */
        wait.until(driver ->

                !driver.findElements(
                        markAllAsReadButton
                ).isEmpty()

                        ||

                        !driver.findElements(
                                deleteAllButton
                        ).isEmpty()

                        ||

                        !driver.findElements(
                                notificationTitles
                        ).isEmpty()
        );


        System.out.println(
                "✓ Notifications səhifəsinin elementləri yükləndi"
        );
    }


    // =========================================================
    // NOTIFICATIONS PAGE OPEN CHECK
    // =========================================================

    public boolean isNotificationsPageOpened() {

        try {

            wait.until(
                    ExpectedConditions.urlContains(
                            "/notifications"
                    )
            );


            boolean correctUrl =
                    driver
                            .getCurrentUrl()
                            .contains("/notifications");


            /*
             * Title varsa yoxlayırıq.
             *
             * Title tapılmasa belə əsas notification
             * elementlərinə görə səhifənin açıldığını
             * müəyyən edə bilərik.
             */
            boolean pageElementExists =

                    !driver.findElements(
                            notificationsTitle
                    ).isEmpty()

                            ||

                            !driver.findElements(
                                    markAllAsReadButton
                            ).isEmpty()

                            ||

                            !driver.findElements(
                                    deleteAllButton
                            ).isEmpty();


            System.out.println(
                    "Current URL: "
                            + driver.getCurrentUrl()
            );


            if (correctUrl && pageElementExists) {

                System.out.println(
                        "✓ Notifications səhifəsi açıldı"
                );

                return true;
            }


            System.out.println(
                    "✘ Notifications səhifəsi açılmadı"
            );

            return false;


        } catch (Exception e) {

            System.out.println(
                    "✘ Notifications səhifəsi açılmadı"
            );

            System.out.println(
                    "Hazırkı URL: "
                            + driver.getCurrentUrl()
            );

            return false;
        }
    }


    // =========================================================
    // MARK ALL AS READ
    // =========================================================

    public void markAllAsReadIfPossible() {

        WebElement button =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                markAllAsReadButton
                        )
                );


        /*
         * Disabled-dirsə unread yoxdur.
         */
        if (!button.isEnabled()) {

            System.out.println(
                    "✓ Mark all as read disabled-dir"
            );

            System.out.println(
                    "✓ Oxunmamış notification yoxdur"
            );

            return;
        }


        scrollToElement(
                button
        );


        try {

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            button
                    )
            );

            button.click();

        } catch (Exception e) {

            jsClick(
                    button
            );
        }


        System.out.println(
                "✓ Mark all as read klik edildi"
        );


        /*
         * Klikdən sonra button disabled olmalıdır.
         */
        wait.until(driver -> {

            try {

                WebElement markButton =
                        driver.findElement(
                                markAllAsReadButton
                        );

                return !markButton.isEnabled();

            } catch (Exception e) {

                return false;
            }
        });


        System.out.println(
                "✓ Mark all as read disabled vəziyyətinə keçdi"
        );
    }


    // =========================================================
    // ALL NOTIFICATIONS READ CHECK
    // =========================================================

    public boolean areAllNotificationsRead() {

        try {

            WebElement button =
                    wait.until(
                            ExpectedConditions.presenceOfElementLocated(
                                    markAllAsReadButton
                            )
                    );


            boolean disabled =
                    !button.isEnabled();


            if (disabled) {

                System.out.println(
                        "✓ Bütün notification-lar read vəziyyətindədir"
                );

            } else {

                System.out.println(
                        "✘ Hələ unread notification mövcuddur"
                );
            }


            return disabled;


        } catch (Exception e) {

            System.out.println(
                    "✘ Mark all as read button tapılmadı"
            );

            return false;
        }
    }


    // =========================================================
    // NOTIFICATION COUNT
    // =========================================================

    public int getNotificationCount() {

        return driver
                .findElements(
                        notificationTitles
                )
                .size();
    }


    // =========================================================
    // NOTIFICATION EXISTS
    // =========================================================

    public boolean hasNotification() {

        try {

            wait.until(driver ->
                    getNotificationCount() > 0
            );


            int count =
                    getNotificationCount();


            System.out.println(
                    "✓ Notification sayı: "
                            + count
            );


            return count > 0;


        } catch (Exception e) {

            System.out.println(
                    "✘ Notification tapılmadı"
            );

            return false;
        }
    }


    // =========================================================
    // DELETE ONE NOTIFICATION
    // =========================================================

    public void deleteOneNotification() {

        /*
         * 1. Silməzdən əvvəl notification sayı.
         */
        notificationCountBeforeDelete =
                getNotificationCount();


        System.out.println(
                "Silməzdən əvvəl notification sayı: "
                        + notificationCountBeforeDelete
        );


        if (notificationCountBeforeDelete <= 0) {

            throw new RuntimeException(
                    "Silmək üçün notification yoxdur!"
            );
        }


        /*
         * 2. Delete button-ları tap.
         */
        List<WebElement> deleteButtons =
                wait.until(
                        ExpectedConditions.presenceOfAllElementsLocatedBy(
                                singleNotificationDeleteButtons
                        )
                );


        System.out.println(
                "Tapılan individual Delete düymələrinin sayı: "
                        + deleteButtons.size()
        );


        if (deleteButtons.isEmpty()) {

            throw new RuntimeException(
                    "Individual Delete düyməsi tapılmadı!"
            );
        }


        /*
         * 3. İlk delete button.
         *
         * isDisplayed() yoxlamırıq.
         *
         * Çünki əvvəlki testdə Selenium button-u
         * DOM-da tapsa da isDisplayed() false qaytarırdı.
         */
        WebElement deleteButton =
                deleteButtons.get(0);


        /*
         * 4. Elementi viewport-a gətir.
         */
        scrollToElement(
                deleteButton
        );


        /*
         * 5. Click.
         *
         * Normal click alınmasa JS click.
         */
        try {

            deleteButton.click();

            System.out.println(
                    "✓ Individual notification normal click edildi"
            );

        } catch (Exception e) {

            System.out.println(
                    "Normal click alınmadı. JS click edilir..."
            );

            jsClick(
                    deleteButton
            );

            System.out.println(
                    "✓ Individual notification JS click edildi"
            );
        }


        /*
         * =====================================================
         * 6. BURADA ƏSAS DÜZƏLİŞ VAR
         * =====================================================
         *
         * Click-dən sonra iki mümkün variant var:
         *
         * VARIANT A:
         *
         * Notification dərhal silinir.
         *
         * 6 -> 5
         *
         *
         * VARIANT B:
         *
         * Confirmation modal açılır.
         *
         * Delete
         *
         * düyməsinə klik etmək lazımdır.
         */


        /*
         * Bir az gözləyib baxırıq:
         *
         * notification sayı artıq azalıb?
         */
        boolean deletedDirectly = false;


        try {

            WebDriverWait shortWait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(3)
                    );


            deletedDirectly =
                    shortWait.until(driver ->
                            getNotificationCount()
                                    < notificationCountBeforeDelete
                    );


        } catch (Exception ignored) {

            /*
             * Say azalmadı.
             *
             * Deməli confirmation ola bilər.
             */
        }


        /*
         * Əgər birbaşa silinibsə confirmation lazım deyil.
         */
        if (deletedDirectly) {

            System.out.println(
                    "✓ Notification confirmation olmadan silindi"
            );

            System.out.println(
                    "Sildikdən sonra notification sayı: "
                            + getNotificationCount()
            );

            return;
        }


        /*
         * 7. Confirmation button axtarırıq.
         */
        List<WebElement> confirmButtons =
                driver.findElements(
                        singleDeleteConfirmButton
                );


        if (!confirmButtons.isEmpty()) {

            System.out.println(
                    "✓ Single delete confirmation tapıldı"
            );


            WebElement confirmButton =
                    confirmButtons.get(0);


            scrollToElement(
                    confirmButton
            );


            try {

                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                confirmButton
                        )
                );

                confirmButton.click();


                System.out.println(
                        "✓ Single delete confirmation Delete klik edildi"
                );

            } catch (Exception e) {

                jsClick(
                        confirmButton
                );


                System.out.println(
                        "✓ Single delete confirmation JS click edildi"
                );
            }

        } else {

            System.out.println(
                    "ℹ Single delete confirmation button tapılmadı"
            );

            System.out.println(
                    "Notification sayının azalması gözlənilir..."
            );
        }


        /*
         * 8. İndi notification sayı mütləq azalmalıdır.
         *
         * 6 -> 5
         */
        wait.until(driver -> {

            int currentCount =
                    getNotificationCount();


            System.out.println(
                    "Hazırkı notification sayı: "
                            + currentCount
            );


            return currentCount
                    < notificationCountBeforeDelete;
        });


        System.out.println(
                "✓ Notification uğurla silindi"
        );


        System.out.println(
                "Əvvəlki say: "
                        + notificationCountBeforeDelete
        );


        System.out.println(
                "Yeni say: "
                        + getNotificationCount()
        );
    }


    // =========================================================
    // SINGLE DELETE CHECK
    // =========================================================

    public boolean isOneNotificationDeleted() {

        int currentCount =
                getNotificationCount();


        System.out.println(
                "Əvvəlki notification sayı: "
                        + notificationCountBeforeDelete
        );


        System.out.println(
                "Hazırkı notification sayı: "
                        + currentCount
        );


        boolean deleted =
                currentCount
                        == notificationCountBeforeDelete - 1;


        if (deleted) {

            System.out.println(
                    "✓ Notification sayı 1 ədəd azalıb"
            );

        } else {

            System.out.println(
                    "✘ Notification sayı düzgün azalmayıb"
            );
        }


        return deleted;
    }


    // =========================================================
    // DELETE ALL
    // =========================================================

    public void clickDeleteAll() {

        WebElement button =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                deleteAllButton
                        )
                );


        scrollToElement(
                button
        );


        try {

            button.click();

        } catch (Exception e) {

            jsClick(
                    button
            );
        }


        System.out.println(
                "✓ Delete all klik edildi"
        );
    }


    // =========================================================
    // DELETE ALL CONFIRMATION OPENED
    // =========================================================

    public boolean isDeleteAllConfirmationOpened() {

        try {

            WebElement confirmButton =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    confirmDeleteAllButton
                            )
                    );


            boolean displayed =
                    confirmButton.isDisplayed();


            if (displayed) {

                System.out.println(
                        "✓ Delete all confirmation pəncərəsi açıldı"
                );
            }


            return displayed;


        } catch (Exception e) {

            System.out.println(
                    "✘ Delete all confirmation pəncərəsi açılmadı"
            );

            return false;
        }
    }


    // =========================================================
    // CONFIRM DELETE ALL
    // =========================================================

    public void confirmDeleteAll() {

        WebElement confirmButton =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                confirmDeleteAllButton
                        )
                );


        scrollToElement(
                confirmButton
        );


        try {

            confirmButton.click();

        } catch (Exception e) {

            jsClick(
                    confirmButton
            );
        }


        System.out.println(
                "✓ Confirmation pəncərəsində Delete all klik edildi"
        );


        /*
         * Modal bağlanmalıdır.
         */
        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        confirmDeleteAllButton
                )
        );


        System.out.println(
                "✓ Delete confirmation pəncərəsi bağlandı"
        );
    }


    // =========================================================
    // ALL NOTIFICATIONS DELETED CHECK
    // =========================================================

    public boolean areAllNotificationsDeleted() {

        try {

            wait.until(driver ->
                    getNotificationCount() == 0
            );


            System.out.println(
                    "✓ Bütün notification-lar silindi"
            );


            return true;


        } catch (Exception e) {

            System.out.println(
                    "✘ Bütün notification-lar silinmədi"
            );


            System.out.println(
                    "Qalan notification sayı: "
                            + getNotificationCount()
            );


            return false;
        }
    }


    // =========================================================
    // SCROLL TO ELEMENT
    // =========================================================

    private void scrollToElement(
            WebElement element
    ) {

        try {

            ((JavascriptExecutor) driver)
                    .executeScript(
                            "arguments[0].scrollIntoView(" +
                                    "{block:'center', inline:'center'}" +
                                    ");",
                            element
                    );

        } catch (StaleElementReferenceException e) {

            System.out.println(
                    "Element DOM-da dəyişdi"
            );
        }
    }


    // =========================================================
    // JS CLICK
    // =========================================================

    private void jsClick(
            WebElement element
    ) {

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        element
                );
    }
}