package bddtests.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/java/bddtests/features"},
        glue = {
                "bddtests/stepdefenitions"
        },
        plugin = {"pretty",
                "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"})
public class TestRunner extends AbstractTestNGCucumberTests {

        @Override
        @DataProvider
        public Object[][] scenarios() {
                return super.scenarios();
        }

}