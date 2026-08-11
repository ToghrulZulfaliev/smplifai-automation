package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

        features = "src/test/resources/features",

        glue = {
                "stepDefinations",
                "hooks"
        },

        tags = "@regression",

        plugin = {
                "pretty",
                "html:target/cucumber-reports/regression-report.html",
                "json:target/cucumber-reports/regression.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },

        monochrome = true,

        publish = false
)

public class RegressionRunner
        extends AbstractTestNGCucumberTests {

}