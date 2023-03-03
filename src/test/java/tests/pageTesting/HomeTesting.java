package tests.pageTesting;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pages.Home;
import pages.SignIn;

public class HomeTesting {
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
    public void AddProductTocart() {
        Home home = new Home();

        home.openHomePage();
        // scrool down
        home.scrollDown();
        home.addToCart(0);

    }

    @AfterClass
    public static void endTest() {
        report.endTest(test);
        report.flush();
    }
}
