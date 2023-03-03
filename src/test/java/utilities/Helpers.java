package utilities;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import tests.pageTesting.TestingMain;

import java.io.File;
import java.io.IOException;

public class Helpers {




    public static void select(WebElement element, String option) {
        Select select = new Select(element);
        select.selectByIndex(0);
        try {
            //use this once you have created the excel sheet for flights
            select.selectByVisibleText(option);
        } catch (Exception e) {
            //Random number between 1 and 3
            int index = (int) (Math.random() * 3) + 1;
            select.selectByIndex(index);

        }
        select = null;
    }

    //Take screenshot of the current browser page
    public static String capture(WebDriver driver) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File("src\\test\\java\\reports\\images" + System.currentTimeMillis() + ".png");
        String errflpath = Dest.getAbsolutePath();
        try {
            Files.copy(scrFile, Dest);
        } catch (IOException e) {
            System.out.println("Screenshot was not captured");
//            RegistrationTesting.test().log(LogStatus.FAIL, "Screenshot was not captured");
        }
        return errflpath;
    }

    public static void scrollDown(WebDriver driver, int value){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "window.scrollBy(0,"+value+")";
        js.executeScript(script);
    }

    public static void passed(String caseName){
        TestingMain.test().log(LogStatus.PASS, caseName + " - Test passed");
    }

    public static void failed(String caseName, WebDriver driver){
        String output = caseName + " - Test failed"+ TestingMain.test().addScreenCapture(Helpers.capture(driver));
        TestingMain.test().log(LogStatus.FAIL, output);
    }

}
