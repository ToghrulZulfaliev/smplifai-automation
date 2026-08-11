package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

        features = "src/test/resources/features",

        glue = {
                "stepDefinations",
                "hooks"
        },

        tags = "@e2e",

        plugin = {
                "pretty",
                "html:target/cucumber-reports/e2e-report.html",
                "json:target/cucumber-reports/e2e.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },

        monochrome = true,

        publish = false
)

public class E2ERunner
        extends AbstractTestNGCucumberTests {

}