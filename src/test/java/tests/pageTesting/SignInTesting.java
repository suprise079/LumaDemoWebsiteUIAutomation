package tests.pageTesting;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import pages.Registration;
import pages.SignIn;

public class SignInTesting {
    static ExtentTest test;
    static ExtentReports report;

    @BeforeClass
    public static void startTest() {
        report = new ExtentReports("src/test/java/reports/SignInTestReport.html");
        test = report.startTest("SignIn Test");
    }

    public static ExtentTest test() {
        return test;
    }

    public static ExtentReports report() {
        return report;
    }

    @Test
    public void TestSingleUserSignIn() {

    }

    @AfterClass
    public static void endTest() {
        report.endTest(test);
        report.flush();
    }
}
