package tests.pageTesting;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import pages.Registration;
import utilities.Constants;
import utilities.ExcelFunctions;

public class RegistrationTesting {
    static ExtentTest test;
    static ExtentReports report;

    @BeforeClass
    public static void startTest() {
        report = new ExtentReports("src/test/java/reports/RegistrationTestReport.html");
        test = report.startTest("Registration Test");
    }

    public static ExtentTest test() {
        return test;
    }

    public static ExtentReports report() {
        return report;
    }

    @Test
    public void TestSingleUserRegistration() {

        Registration regInstanstance = new Registration();
        Registration reg = regInstanstance
                .withEmail("test@gmail.com")
                .withFirstName("Tadaa")
                .withLastName("maluleke")
                .withPassword("123456!!D");

        reg.openRegistrationPage();
        reg.multiUsersFillForm();
        reg.fillForm();
        reg.submitForm();
    }

    @AfterClass
    public static void endTest() {
        report.endTest(test);
        report.flush();
    }
}
