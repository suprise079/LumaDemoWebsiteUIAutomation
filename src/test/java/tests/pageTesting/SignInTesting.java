package tests.pageTesting;

import drivers.CurrentDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import pages.Registration;
import pages.SignIn;
import utilities.Constants;

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
    public void TestSingleUserSignIn() throws InterruptedException {
        SignIn signIn = new SignIn();
        signIn.openSignInPage();
        signIn.MultiUserFillForm();
        CurrentDriver.closeDriver();

        SignIn sign = new SignIn();
        sign.withEmail(Constants.DEFAULT_EMAIL).withPassword(Constants.DEFAULT_PASSWORD);
        sign.openSignInPage();
        sign.fillForm();
        sign.submitForm();
        Thread.sleep(5000);
    }

    @AfterClass
    public static void endTest() {
        report.endTest(test);
        report.flush();
    }
}
