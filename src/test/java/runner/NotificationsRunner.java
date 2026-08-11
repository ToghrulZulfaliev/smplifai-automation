package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

        // Feature faylının yolu
        features = "src/test/resources/features/Notifications.feature",

        // StepDefinition package
        glue = {
                "stepDefinations",
                "hooks"
        },

        // Hansı tag işləsin
        tags = "@notifications",

        // Console output
        plugin = {
                "pretty",
                "html:target/cucumber-reports/notifications-report.html",
                "json:target/cucumber-reports/notifications.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },

        monochrome = true,

        publish = false
)

public class NotificationsRunner
        extends AbstractTestNGCucumberTests {

}