package testrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"@target/failedCases.txt"},
        glue = {"stepdefs", "hooks"},
        plugin = {
                "pretty",
                "rerun:target/failedCases.txt"
        },
        monochrome = true
)

public class TestNgFailedRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
