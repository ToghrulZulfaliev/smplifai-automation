package hooks;

import config.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

public class Hooks {

    private WebDriver driver;


    // =========================================================
    // LOGIN / REGISTER SCENARIO-LARI
    // =========================================================

    @Before(value = "@login or @register", order = 0)
    public void preparePublicScenario() {

        driver = WebDriverManager.getChromeDriver();

        System.out.println(
                "✓ Browser public scenario üçün hazırdır"
        );
    }


    // =========================================================
    // PROTECTED SCENARIO-LAR
    // Application, Notifications və s.
    // =========================================================

    @Before(
            value = "not @login and not @register",
            order = 1
    )
    public void loginBeforeProtectedScenario() {

        driver = WebDriverManager.getChromeDriver();

        System.out.println(
                "========================================"
        );

        System.out.println(
                ">>> Protected scenario üçün login başlayır"
        );


        driver.get(
                "https://appstudentsecret.smplifai.com/auth/login"
        );


        LoginPage loginPage =
                new LoginPage();


        // =====================================================
        // TEST LOGIN DATA
        // =====================================================

        String email =
                "toghrulzulfaliev8@gmail.com";

        String password =
                "T7772288l";


        if (email == null || email.isBlank()) {

            throw new RuntimeException(
                    "Login email null və ya boşdur!"
            );
        }


        if (password == null || password.isBlank()) {

            throw new RuntimeException(
                    "Login password null və ya boşdur!"
            );
        }


        System.out.println(
                "Login email: " + email
        );


        // =====================================================
        // LOGIN
        // =====================================================

        loginPage.enterEmail(
                email
        );

        loginPage.enterPassword(
                password
        );

        loginPage.clickLoginButton();


        // =====================================================
        // WAIT
        // =====================================================

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(30)
                );


        /*
         * Login səhifəsindən çıxmasını gözləyirik.
         */
        wait.until(
                ExpectedConditions.not(
                        ExpectedConditions.urlContains(
                                "/auth/login"
                        )
                )
        );


        System.out.println(
                "Login sonrası URL: "
                        + driver.getCurrentUrl()
        );


        /*
         * Dashboard elementinin gəlməsini gözləyirik.
         */
        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.id(
                                "btn-new-application"
                        )
                )
        );


        System.out.println(
                "✓ Protected scenario üçün login tamamlandı"
        );

        System.out.println(
                "✓ Dashboard hazırdır"
        );

        System.out.println(
                "========================================"
        );
    }


    // =========================================================
    // AFTER
    // =========================================================

    @After(order = 0)
    public void closeBrowser() {

        try {

            WebDriverManager.quitDriver();

            System.out.println(
                    "✓ Browser bağlandı"
            );

        } catch (Exception e) {

            System.out.println(
                    "Browser bağlanarkən xəta: "
                            + e.getMessage()
            );
        }
    }
}