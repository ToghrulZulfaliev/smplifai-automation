package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/Register.feature",
        glue = {"stepDefinations", "hooks"},
        plugin = {
                "pretty",
                "html:target/Register-report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class RegisterRunner extends AbstractTestNGCucumberTests {
}