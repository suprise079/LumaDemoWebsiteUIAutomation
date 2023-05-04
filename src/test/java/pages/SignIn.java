package pages;

import drivers.CurrentDriver;
import objects.HomeObjects;
import objects.SignInObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.pageTesting.Main;
import utilities.Constants;
import utilities.ExcelFunctions;
import utilities.Helpers;
import utilities.Reporter;

public class SignIn {

    WebDriver driver;
    SignInObjects objs;

    private String email = "";
    private String password = "";

    public SignIn() {
        CurrentDriver currentDriver = CurrentDriver.getInstance(Constants.BROWSER);
        this.driver = currentDriver.getDriver();
        objs = new SignInObjects();
    }

    public void openSignInPage() {
        driver.navigate().to("https://magento.softwaretestingboard.com/customer/account/login/");
    }

    public SignIn withEmail(String email) {
        this.email = email;
        return this;
    }

    public SignIn withPassword(String password) {
        this.password = password;
        return this;
    }

    public void fillForm() {
        objs.getEmailField().sendKeys(email);
        objs.getPasswordField().sendKeys(password);
    }

    public void MultiUserFillForm() {
        String[][] data = ExcelFunctions.getSheetData("Test signIn", Constants.END_COLON, "User information");
        int attempt = 0;
        for (int i = 0; i <= data.length-1; i++) {
            int count = 3;
            String caseName = data[i][count];
            try {
                String email = data[i][count++];
                String password = data[i][count++];
                objs.getEmailField().sendKeys(email);
                objs.getPasswordField().sendKeys(password);


                this.submitForm();


                Thread.sleep(2000);
                callTestCases(caseName, attempt);

                objs.getEmailField().clear();
                objs.getPasswordField().clear();
                attempt++;

            } catch (Exception e) {
                Reporter.failed(caseName, driver);
            }

        }
    }

    public void submitForm() {
        objs.getSignInButton().click();
    }

    public void callTestCases(String caseName, int attempt) {
        if (caseName.contains("with unregistered email")) testUregisteredUser(caseName);
        if (caseName.contains("with incorrect password")) testUregisteredUser(caseName);
        if (attempt == 3) testCaptureVisible(caseName);
        if (caseName.contains("with valid email and password")) validLogin(caseName);
    }

    //Test cases
    private void testUregisteredUser(String caseName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(objs.getErrorNotification()));
            if (!objs.getErrorNotification().getText().contains("The account sign-in was incorrect")) {
                Reporter.passed(caseName);
            } else {
                Reporter.failed(caseName, driver);
            }
        } catch (Exception e) {
            Reporter.failed(caseName, driver);
        }
    }

    public void testCaptureVisible(String caseName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(objs.getCaptureContainer()));
            if (objs.getCaptureContainer().isDisplayed()) {
                String output = Main.test().addScreenCapture(Reporter.capture(driver)) + "\n" + caseName;
                Reporter.passed(output);

            } else {
                Reporter.failed(caseName, driver);
            }
        } catch (Exception e) {
            Reporter.failed(caseName, driver);
        }
    }

    public void validLogin(String caseName) {
        HomeObjects home = new HomeObjects();

        if (home.getTitle().contains("Home Page - Magento eCommerce")) {
            Reporter.passed(caseName);
        } else {
            Reporter.failed(caseName, driver);
        }
    }


}
