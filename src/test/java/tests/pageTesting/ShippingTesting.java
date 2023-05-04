package tests.pageTesting;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pages.CheckOut;
import pages.Home;
import pages.Product;

public class ShippingTesting {
    static ExtentTest test;
    static ExtentReports report;

    @BeforeClass
    public static void startTest() {
        report = new ExtentReports("src/test/java/reports/ShippingReport.html");
        test = report.startTest("Checkout Shipping Test");
    }

    public static ExtentTest test() {
        return test;
    }

    public static ExtentReports report() {
        return report;
    }

    @Test
    public void TestShippingForm() {
        CheckOut checkOut = new CheckOut();
        Home home = new Home();
        Product product = new Product();

        home.openHomePage();
        home.scrollDown();
        home.viewProduct(1);
        product.addToCart("1", "2", "3");

        checkOut.openCheckOut();
        checkOut.proceedCheckOutButton();
        checkOut.fillShippingForm(false);
        checkOut.clickContinue();
    }

    @AfterClass
    public static void endTest() {
        report.endTest(test);
        report.flush();
    }
}
