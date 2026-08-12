package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class ApplicationPage {

    private final WebDriver driver;
    private final WebDriverWait wait;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ApplicationPage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(30)
                );
    }


    // =========================================================
    // LOCATORS
    // =========================================================

    private final By newApplicationButton =
            By.id("btn-new-application");


    private final By phoneInput =
            By.cssSelector(
                    "input[type='tel']"
            );


    private final By chooseButton =
            By.xpath(
                    "//button[@type='submit' and @form='step1-form']"
            );


    private final By fileInputs =
            By.cssSelector(
                    "input[type='file']"
            );


    private final By passportFileInput =
            By.xpath(
                    "(//input[@type='file'])[1]"
            );


    private final By visaFileInput =
            By.xpath(
                    "(//input[@type='file'])[2]"
            );


    private final By i20FileInput =
            By.xpath(
                    "(//input[@type='file'])[3]"
            );


    private final By submitButton =
            By.xpath(
                    "//button[contains(@class,'btn-submit') " +
                            "and contains(normalize-space(),'Submit')]"
            );


    private final By applicationRows =
            By.cssSelector(
                    "tbody tr"
            );


    // REAL HTML:
    // <a class="btn-view" href="/application/.../documents">
    private final By viewButtons =
            By.xpath(
                    "//a[contains(@class,'btn-view') " +
                            "and contains(@href,'/documents')]"
            );


    // REAL HTML:
    // <a class="btn-download btn-download--active"
    // href="/application/.../downloads">
    private final By downloadButtons =
            By.xpath(
                    "//a[contains(@class,'btn-download') " +
                            "and contains(@href,'/downloads')]"
            );


    // Application delete button
    private final By deleteButtons =
            By.cssSelector(
                    "button.btn-delete"
            );


    // Modal daxilində confirm Delete
    private final By confirmDeleteButton =
            By.xpath(
                    "//button[normalize-space()='Delete' " +
                            "and not(contains(@class,'btn-delete'))]"
            );


    private final By passportDocument =
            By.xpath(
                    "//*[contains(" +
                            "translate(normalize-space(.)," +
                            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                            "'abcdefghijklmnopqrstuvwxyz')," +
                            "'passport'" +
                            ")]"
            );


    private final By visaDocument =
            By.xpath(
                    "//*[contains(" +
                            "translate(normalize-space(.)," +
                            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                            "'abcdefghijklmnopqrstuvwxyz')," +
                            "'visa'" +
                            ")]"
            );


    private final By i20Document =
            By.xpath(
                    "//*[contains(normalize-space(.),'I-20') " +
                            "or contains(normalize-space(.),'I20')]"
            );


    // =========================================================
    // DASHBOARD CHECK
    // =========================================================

    public boolean isDashboardDisplayed() {

        try {

            System.out.println(
                    "Dashboard yoxlanılır..."
            );

            System.out.println(
                    "Current URL: "
                            + driver.getCurrentUrl()
            );


            WebElement newApplication =
                    wait.until(
                            ExpectedConditions
                                    .visibilityOfElementLocated(
                                            newApplicationButton
                                    )
                    );


            boolean displayed =
                    newApplication.isDisplayed();


            if (displayed) {

                System.out.println(
                        "✓ Dashboard New Application button tapıldı"
                );

                System.out.println(
                        "✓ Dashboard uğurla açılıb"
                );
            }


            return displayed;


        } catch (Exception e) {

            System.out.println(
                    "✘ Dashboard element tapılmadı"
            );

            System.out.println(
                    "Current URL: "
                            + driver.getCurrentUrl()
            );

            System.out.println(
                    "Xəta: "
                            + e.getMessage()
            );

            return false;
        }
    }


    // =========================================================
    // CLICK NEW APPLICATION
    // =========================================================

    public void clickNewApplication() {

        WebElement button =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                newApplicationButton
                        )
                );


        scrollToElement(button);


        try {

            button.click();

        } catch (Exception e) {

            jsClick(button);
        }


        System.out.println(
                "✓ New Application düyməsinə klik edildi"
        );
    }


    // =========================================================
    // NEW APPLICATION OPENED
    // =========================================================

    public boolean isNewApplicationOpened() {

        try {

            WebElement phone =
                    wait.until(
                            ExpectedConditions
                                    .visibilityOfElementLocated(
                                            phoneInput
                                    )
                    );


            boolean displayed =
                    phone.isDisplayed();


            if (displayed) {

                System.out.println(
                        "✓ New Application pəncərəsi açıldı"
                );
            }


            return displayed;


        } catch (Exception e) {

            System.out.println(
                    "✘ New Application pəncərəsi açılmadı"
            );

            return false;
        }
    }


    // =========================================================
    // ENTER PHONE NUMBER
    // =========================================================

    public void enterPhoneNumber(String phoneNumber) {

        WebElement phone =
                wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(
                                        phoneInput
                                )
                );


        phone.clear();

        phone.sendKeys(
                phoneNumber
        );


        System.out.println(
                "✓ Telefon nömrəsi daxil edildi: "
                        + phoneNumber
        );
    }


    // =========================================================
    // CLICK CHOOSE
    // =========================================================

    public void clickChoose() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement choose =
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(.,'Choose')]")
                ));

        choose.click();

        System.out.println("✓ Choose düyməsinə klik edildi");

        wait.until(driver ->
                driver.findElements(
                        By.cssSelector("input[type='file']")
                ).size() >= 3
        );
    }
    // =========================================================
    // ENROLLMENT AGREEMENT CHECK
    // =========================================================

    public boolean isEnrollmentAgreementOpened() {

        try {

            wait.until(
                    ExpectedConditions.numberOfElementsToBeMoreThan(
                            fileInputs,
                            2
                    )
            );


            List<WebElement> inputs =
                    driver.findElements(
                            fileInputs
                    );


            System.out.println(
                    "✓ Enrollment səhifəsində file input sayı: "
                            + inputs.size()
            );


            return inputs.size() >= 3;


        } catch (Exception e) {

            System.out.println(
                    "✘ Application Enrollment Agreement açılmadı"
            );

            System.out.println(
                    "Current URL: "
                            + driver.getCurrentUrl()
            );

            return false;
        }
    }


    // =========================================================
    // TEST DOCUMENT PATH
    // =========================================================

    private String getTestDocumentPath() {

        String path =
                System.getProperty("user.dir")
                        + File.separator
                        + "src"
                        + File.separator
                        + "test"
                        + File.separator
                        + "resources"
                        + File.separator
                        + "testdata"
                        + File.separator
                        + "test-document.png";


        File file =
                new File(path);


        if (!file.exists()) {

            throw new RuntimeException(
                    "Test sənədi tapılmadı: "
                            + path
            );
        }


        return file.getAbsolutePath();
    }


    // =========================================================
    // UPLOAD PASSPORT
    // =========================================================

    public void uploadPassport() {

        WebElement input =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                passportFileInput
                        )
                );


        input.sendKeys(
                getTestDocumentPath()
        );


        System.out.println(
                "✓ Passport yükləndi"
        );
    }


    // =========================================================
    // UPLOAD VISA
    // =========================================================

    public void uploadVisa() {

        WebElement input =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                visaFileInput
                        )
                );


        input.sendKeys(
                getTestDocumentPath()
        );


        System.out.println(
                "✓ Visa yükləndi"
        );
    }


    // =========================================================
    // UPLOAD I-20
    // =========================================================

    public void uploadI20() {

        WebElement input =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                i20FileInput
                        )
                );


        input.sendKeys(
                getTestDocumentPath()
        );


        System.out.println(
                "✓ I-20 yükləndi"
        );
    }


    // =========================================================
    // SUBMIT DOCUMENTS
    // =========================================================

    public void clickSubmitDocuments() {

        WebElement button =
                wait.until(driver -> {

                    WebElement submit =
                            driver.findElement(
                                    submitButton
                            );


                    if (submit.isDisplayed()
                            && submit.isEnabled()) {

                        return submit;
                    }


                    return null;
                });


        scrollToElement(button);


        try {

            button.click();

        } catch (Exception e) {

            jsClick(button);
        }


        System.out.println(
                "✓ Submit Documents klik edildi"
        );


        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        newApplicationButton
                )
        );
    }


    // =========================================================
    // APPLICATION VISIBLE
    // =========================================================

    public boolean isApplicationVisible() {

        try {

            List<WebElement> views =
                    wait.until(
                            ExpectedConditions
                                    .presenceOfAllElementsLocatedBy(
                                            viewButtons
                                    )
                    );


            System.out.println(
                    "Tapılan application sayı: "
                            + views.size()
            );


            return !views.isEmpty();


        } catch (Exception e) {

            System.out.println(
                    "✘ Application list-də application tapılmadı"
            );

            return false;
        }
    }


    // =========================================================
    // CLICK VIEW
    // =========================================================

    public void clickView() {

        List<WebElement> buttons =
                wait.until(
                        ExpectedConditions
                                .presenceOfAllElementsLocatedBy(
                                        viewButtons
                                )
                );


        System.out.println(
                "Tapılan View düymələrinin sayı: "
                        + buttons.size()
        );


        if (buttons.isEmpty()) {

            throw new RuntimeException(
                    "View düyməsi tapılmadı!"
            );
        }


        WebElement newestView =
                buttons.get(
                        buttons.size() - 1
                );


        scrollToElement(
                newestView
        );


        jsClick(
                newestView
        );


        wait.until(
                ExpectedConditions.urlContains(
                        "/documents"
                )
        );


        System.out.println(
                "✓ View klik edildi"
        );

        System.out.println(
                "Açılan URL: "
                        + driver.getCurrentUrl()
        );
    }


    // =========================================================
    // DOCUMENTS PAGE OPENED
    // =========================================================

    public boolean isDocumentsPageOpened() {

        try {

            wait.until(
                    ExpectedConditions.urlContains(
                            "/documents"
                    )
            );


            boolean opened =
                    driver
                            .getCurrentUrl()
                            .contains("/documents");


            if (opened) {

                System.out.println(
                        "✓ Documents səhifəsi açıldı"
                );
            }


            return opened;


        } catch (Exception e) {

            System.out.println(
                    "✘ Documents səhifəsi açılmadı"
            );

            System.out.println(
                    "Current URL: "
                            + driver.getCurrentUrl()
            );

            return false;
        }
    }


    // =========================================================
    // PASSPORT CHECK
    // =========================================================

    public boolean isPassportVisible() {

        return isElementVisible(
                passportDocument,
                "Passport"
        );
    }


    // =========================================================
    // VISA CHECK
    // =========================================================

    public boolean isVisaVisible() {

        return isElementVisible(
                visaDocument,
                "Visa"
        );
    }


    // =========================================================
    // I-20 CHECK
    // =========================================================

    public boolean isI20Visible() {

        return isElementVisible(
                i20Document,
                "I-20"
        );
    }


    // =========================================================
    // CLICK DOWNLOAD
    // =========================================================

    public void clickDownload() {

        List<WebElement> buttons =
                wait.until(
                        ExpectedConditions
                                .presenceOfAllElementsLocatedBy(
                                        downloadButtons
                                )
                );


        System.out.println(
                "Tapılan Download düymələrinin sayı: "
                        + buttons.size()
        );


        if (buttons.isEmpty()) {

            throw new RuntimeException(
                    "Download düyməsi tapılmadı!"
            );
        }


        WebElement newestDownload =
                buttons.get(
                        buttons.size() - 1
                );


        scrollToElement(
                newestDownload
        );


        try {

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            newestDownload
                    )
            ).click();

        } catch (Exception e) {

            jsClick(
                    newestDownload
            );
        }


        wait.until(
                ExpectedConditions.urlContains(
                        "/downloads"
                )
        );


        System.out.println(
                "✓ Download düyməsinə klik edildi"
        );

        System.out.println(
                "Downloads URL: "
                        + driver.getCurrentUrl()
        );
    }


    // =========================================================
    // DOWNLOAD PAGE CHECK
    // =========================================================

    public boolean isDownloadsPageOpened() {

        try {

            wait.until(
                    ExpectedConditions.urlContains(
                            "/downloads"
                    )
            );


            boolean opened =
                    driver
                            .getCurrentUrl()
                            .contains("/downloads");


            if (opened) {

                System.out.println(
                        "✓ Downloads səhifəsi açıldı"
                );
            }


            return opened;


        } catch (Exception e) {

            System.out.println(
                    "✘ Downloads səhifəsi açılmadı"
            );

            System.out.println(
                    "Current URL: "
                            + driver.getCurrentUrl()
            );

            return false;
        }
    }


    // =========================================================
    // CLICK DELETE
    // =========================================================

    public void clickDelete() {

        List<WebElement> buttons =
                wait.until(
                        ExpectedConditions
                                .presenceOfAllElementsLocatedBy(
                                        deleteButtons
                                )
                );


        System.out.println(
                "Tapılan Delete düymələrinin sayı: "
                        + buttons.size()
        );


        if (buttons.isEmpty()) {

            throw new RuntimeException(
                    "Application Delete düyməsi tapılmadı!"
            );
        }


        WebElement newestDelete =
                buttons.get(
                        buttons.size() - 1
                );


        scrollToElement(
                newestDelete
        );


        try {

            newestDelete.click();

        } catch (Exception e) {

            jsClick(
                    newestDelete
            );
        }


        System.out.println(
                "✓ Application Delete düyməsinə klik edildi"
        );
    }


    // =========================================================
    // DELETE CONFIRMATION OPEN
    // =========================================================

    public boolean isDeleteConfirmationOpened() {

        try {

            WebElement confirm =
                    wait.until(
                            ExpectedConditions
                                    .visibilityOfElementLocated(
                                            confirmDeleteButton
                                    )
                    );


            boolean displayed =
                    confirm.isDisplayed();


            if (displayed) {

                System.out.println(
                        "✓ Delete confirmation pəncərəsi açıldı"
                );
            }


            return displayed;


        } catch (Exception e) {

            System.out.println(
                    "✘ Delete confirmation pəncərəsi açılmadı"
            );

            return false;
        }
    }


    // =========================================================
    // CONFIRM DELETE
    // =========================================================

    public void confirmDelete() {

        WebElement button =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                confirmDeleteButton
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
                "✓ Application silinməsi təsdiqləndi"
        );
    }


    // =========================================================
    // DELETE CONFIRMATION CLOSED
    // =========================================================

    public boolean isDeleteConfirmationClosed() {

        try {

            /*
             * Generic "Delete" elementinin yox olmasını gözləmirik.
             *
             * Delete bitəndən sonra Dashboard-un
             * yenidən hazır olmasını gözləyirik.
             */

            WebElement newApplication =
                    wait.until(
                            ExpectedConditions
                                    .visibilityOfElementLocated(
                                            newApplicationButton
                                    )
                    );


            boolean dashboardVisible =
                    newApplication.isDisplayed();


            if (dashboardVisible) {

                System.out.println(
                        "✓ Delete confirmation bağlandı"
                );

                System.out.println(
                        "✓ Delete sonrası Dashboard hazırdır"
                );
            }


            return dashboardVisible;


        } catch (Exception e) {

            System.out.println(
                    "✘ Delete confirmation sonrası Dashboard açılmadı"
            );

            System.out.println(
                    "Current URL: "
                            + driver.getCurrentUrl()
            );

            return false;
        }
    }


    // =========================================================
    // COMMON ELEMENT VISIBILITY
    // =========================================================

    private boolean isElementVisible(
            By locator,
            String elementName
    ) {

        try {

            WebElement element =
                    wait.until(
                            ExpectedConditions
                                    .visibilityOfElementLocated(
                                            locator
                                    )
                    );


            boolean displayed =
                    element.isDisplayed();


            if (displayed) {

                System.out.println(
                        "✓ "
                                + elementName
                                + " görünür"
                );
            }


            return displayed;


        } catch (Exception e) {

            System.out.println(
                    "✘ "
                            + elementName
                            + " görünmür"
            );

            return false;
        }
    }


    // =========================================================
    // SCROLL
    // =========================================================

    private void scrollToElement(
            WebElement element
    ) {

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView(" +
                                "{block:'center', inline:'center'}" +
                                ");",
                        element
                );
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