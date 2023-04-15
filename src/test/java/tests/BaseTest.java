package tests;

import aquality.tracking.integrations.core.AqualityTrackingLifecycle;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import steps.TestSteps;

public abstract class BaseTest {
    private final AqualityTrackingLifecycle lifecycle = new AqualityTrackingLifecycle();

    @BeforeSuite
    protected void beforeSuite(ITestContext context) {
        TestSteps.setSuiteSystemPropertyFroTracking(context.getSuite().getName());
        lifecycle.startTestRun();
    }

    @BeforeMethod
    protected void beforeMethod(ITestResult result) {
        lifecycle.startTestExecution(result.getMethod().getDescription());
    }


    @AfterSuite
    protected void afterSuite() {
        lifecycle.finishTestRun();
    }

    @AfterMethod
    protected void afterMethod(ITestResult result) {
        Throwable fail = result.getThrowable();
        lifecycle.finishTestExecution(setResultId(result.getStatus()),
                fail == null ? null : fail.getMessage());
    }

    private int setResultId(int testNgResultId) {
        int resultId = 0;
        switch (testNgResultId) {
            case 1:
                resultId = 2;
                break;
            case 2:
                resultId = 1;
                break;
            case 16:
                resultId = 5;
                break;
            default:
                return resultId;
        }
        return resultId;
    }
}
