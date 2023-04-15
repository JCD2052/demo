package bddtests.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import steps.TestSteps;

@CucumberOptions(
        features = {"src/test/java/bddtests/features"},
        glue = {
                "bddtests/stepdefenitions"
        },
        plugin = {
                "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm",
                "aquality.tracking.integrations.cucumber5jvm.AqualityTrackingCucumber5Jvm"
        },
        strict = true)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeSuite
    protected void beforeSuite(ITestContext context) {
        TestSteps.setSuiteSystemPropertyFroTracking(context.getSuite().getName());
    }
}