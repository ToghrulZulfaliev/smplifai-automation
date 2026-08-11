package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/Login.feature",
        glue = {"stepDefinations", "hooks"},
        plugin = {
                "pretty",
                "html:target/Login-report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class LoginRunner extends AbstractTestNGCucumberTests {
}