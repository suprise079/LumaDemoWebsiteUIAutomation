package tests.pageTesting;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pages.Home;
import pages.Search;

public class SearchTesting {
    static ExtentTest test;
    static ExtentReports report;

    @BeforeClass
    public static void startTest() {
        report = new ExtentReports("src/test/java/reports/SearchTestReport.html");
        test = report.startTest("Search Test");
    }

    public static ExtentTest test() {
        return test;
    }

    public static ExtentReports report() {
        return report;
    }

    @Test
    public void searchProduct() {
        Search search = new Search();
        Home home = new Home();

        home.openHomePage();
        search.findMultiple();
        search.find("shirt");
    }

    @AfterClass
    public static void endTest() {
        report.endTest(test);
        report.flush();
    }
}
