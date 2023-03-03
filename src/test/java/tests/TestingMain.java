package tests.pageTesting;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import drivers.CurrentDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.OrderWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import utilities.Constants;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestingMain {
    private static ExtentTest test;
    private static ExtentReports report;

    @BeforeClass
    public static void startTest() {
        report = new ExtentReports("src/test/java/reports/TestReport.html");
        test = report.startTest("Luma Test Report");
    }

    public static ExtentTest test() {
        return test;
    }

    public static ExtentReports report() {
        return report;
    }

    @Test
    public void test1_TestRegistration() throws InterruptedException {
        Registration regInstanstance = new Registration();
        CurrentDriver currentDriver = CurrentDriver.getInstance(Constants.BROWSER);
        WebDriver driver = currentDriver.getDriver();
        Registration reg = regInstanstance
                .withEmail(Constants.DEFAULT_EMAIL)
                .withFirstName(Constants.DEFAULT_FIRST_NAME)
                .withLastName(Constants.DEFAULT_LAST_NAME)
                .withPassword(Constants.DEFAULT_PASSWORD);

        reg.openRegistrationPage();
        reg.multiUsersFillForm();
        reg.fillForm();
        reg.submitForm();
        Thread.sleep(10000);
        CurrentDriver.closeDriver();
    }

    @Test
    public void test2_TestSignIn() throws InterruptedException {
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

    @Test
    public void test3_TestAddProductTocart(){
        Home home = new Home();
        Product product = new Product();

        home.openHomePage();
        home.scrollDown();
        home.viewProduct(1);
        product.addMultipleProducts();
        product.addToCart("1", "2", "3");

    }

    @Test
    public void test4_TestTransactionFlowWhenLoggedIn() throws InterruptedException {
        CheckOut checkOut = new CheckOut();
        Home home = new Home();
        Product product = new Product();
        Payments payments = new Payments();
        CheckOutSuccess checkOutSuccess = new CheckOutSuccess();

        checkOut.openCheckOut();
        checkOut.proceedCheckOutButton();
        checkOut.fillShippingForm(true);
        checkOut.clickContinue();

        Thread.sleep(3000);
        payments.clickPlaceOrderButton();

        home.scrollDown();
        Thread.sleep(5000);
        CurrentDriver.closeDriver();
    }

    @Test
    public void test5_TestTransactionFlowWhenNotLoggedIn() throws InterruptedException {
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
        checkOut.fillShippingForm(false);
        checkOut.clickContinue();

        Thread.sleep(3000);
        payments.clickPlaceOrderButton();
        checkOutSuccess.continueShopping();
        home.scrollDown();
        Thread.sleep(5000);
        CurrentDriver.closeDriver();
    }

    @Test
    public void test6_TestSearch(){
        Search search = new Search();
        Home home = new Home();

        home.openHomePage();
        search.findMultiple();
        CurrentDriver.closeDriver();
    }

    @Test
    public void test7_TestAddToWishlist(){
        SignIn sign = new SignIn();
        Home home = new Home();
        Product product = new Product();

        sign.withEmail(Constants.DEFAULT_EMAIL).withPassword(Constants.DEFAULT_PASSWORD);
        sign.openSignInPage();
        sign.fillForm();
        sign.submitForm();

        home.openHomePage();
        home.viewProduct(0);
        product.addToWishList();
    }

    @AfterClass
    public static void endTest() {
        report.endTest(test);
        report.flush();
    }
}
