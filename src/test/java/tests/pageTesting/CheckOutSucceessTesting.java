package tests.pageTesting;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pages.*;

public class CheckOutSucceessTesting {
    static ExtentTest test;
    static ExtentReports report;

    @BeforeClass
    public static void startTest() {
        report = new ExtentReports("src/test/java/reports/CheckoutSuccessReport.html");
        test = report.startTest("Checkout success Shipping Test");
    }

    public static ExtentTest test() {
        return test;
    }

    public static ExtentReports report() {
        return report;
    }

    @Test
    public void TestCheckoutSuccess() throws InterruptedException {
        CheckOut checkOut = new CheckOut();
        Home home = new Home();
        Product product = new Product();
        Payments payments = new Payments();
        CheckOutSuccess checkOutSuccess = new CheckOutSuccess();

        home.openHomePage();
        home.scrollDown();
        home.viewProduct(1);
        product.addToCart("1", "2", "3");

        checkOut.openCheckOut();
        checkOut.proceedCheckOutButton();
        checkOut.fillShippingForm(true);
        checkOut.clickContinue();

        //payments.selectAddressCheckbox();
        //checkOut.fillForm();        //Made page componets to allow for a more reuse of componets like this one
        //payments.clickUpdateButton();
        home.scrollDown();
//        payments.clickPlaceOrderButton();
        Thread.sleep(5000);
//        checkOutSuccess.createNewAccount();
            checkOutSuccess.continueShopping();

    }

    @AfterClass
    public static void endTest() {
        report.endTest(test);
        report.flush();
    }
}
