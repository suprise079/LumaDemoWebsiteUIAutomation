package pages;

import com.relevantcodes.extentreports.LogStatus;
import drivers.CurrentDriver;
import objects.RegistrationObjects;
import org.openqa.selenium.WebDriver;
import tests.pageTesting.Main;
import utilities.Constants;
import utilities.ExcelFunctions;
import utilities.Helpers;
import utilities.Reporter;

public class Registration {

    WebDriver driver;
    RegistrationObjects regObj;

// initialise form value with default values
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String password = "";

    public Registration() {
        CurrentDriver current = CurrentDriver.getInstance(Constants.BROWSER);
        this.driver = current.getDriver();

        regObj = new RegistrationObjects();
    }

    public void openRegistrationPage() {
        driver.navigate().to("https://magento.softwaretestingboard.com/customer/account/create/");
    }

    public Registration withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Registration withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Registration withEmail(String email) {
        this.email = email;
        return this;
    }

    public Registration withPassword(String password) {
        this.password = password;
        return this;
    }

    public void fillForm() {
        regObj.getFirstName().sendKeys(firstName);
        regObj.getLastName().sendKeys(lastName);
        regObj.getEmail().sendKeys(email);
        regObj.getPassword().sendKeys(password);
        regObj.getConfirmPassword().sendKeys(password);
    }

    public void multiUsersFillForm(){
        String[][] data = ExcelFunctions.getSheetData( "Test registration", Constants.END_COLON, "User information");
        int count = 0;
        for (int i = 1; i <= data.length-1; i++) {

            String firstName = data[i][count++];
            String lastName = data[i][count++];
            String isNews = data[i][count++];
            String email = data[i][count++];
            String password = data[i][count++];
            String confirmPassword = password;
            String caseName = data[i][count];

            regObj.getFirstName().sendKeys(firstName);
            regObj.getLastName().sendKeys(lastName);
            regObj.getEmail().sendKeys(email);
            regObj.getPassword().sendKeys(password);
            regObj.getConfirmPassword().sendKeys(confirmPassword);

            try{
                count = 0;
                this.submitForm();
                Thread.sleep(1000);
                this.callTest(caseName);
            } catch (Exception e){
                this.failed(caseName);
            }

            regObj.getFirstName().clear();
            regObj.getLastName().clear();
            regObj.getEmail().clear();
            regObj.getPassword().clear();
            regObj.getConfirmPassword().clear();

        }
    }

    public void submitForm() {
        regObj.getSubmitButton().click();
    }

    public void registerDefaultUser(){
       this.withEmail(Constants.DEFAULT_EMAIL)
                .withFirstName(Constants.DEFAULT_FIRST_NAME)
                .withLastName(Constants.DEFAULT_LAST_NAME)
                .withPassword(Constants.DEFAULT_PASSWORD);
        this.openRegistrationPage();
        this.fillForm();
        this.submitForm();
    }

    public void callTest(String caseName){
        if (caseName.contains("without required fields")) this.testEmptyFields(caseName);
        if (caseName.contains("without first name")) this.testNoFirstName(caseName);
        if (caseName.contains("without last name")) this.testNoLastName(caseName);
        if (caseName.contains("without email")) this.testNoEmail(caseName);
        if (caseName.contains("wrongly formated email")) this.testWrongEmailFormat(caseName);
        if (caseName.contains("without password")) this.testNoPassword(caseName);
        if (caseName.contains("weak password")) this.testWeakPassword(caseName);
        if (caseName.contains("without password confirmation")) this.testNoPasswordConfirmation(caseName);
        if (caseName.contains("with all fields and valid email")) this.testValidRegistration(caseName);
        if (caseName.contains("automatically directed to homepage")) this.testValidRegistration(caseName);
    }

    private void testEmptyFields(String caseName) {
        String title = driver.getTitle();
        if (title.equals("Create New Customer Account")) {
            this.passed(caseName);;
        } else {
            this.failed(caseName);

        }
    }

    private void passed(String caseName){
        Main.test().log(LogStatus.PASS, caseName + " - Test passed");
    }
    
    private void failed(String caseName){
        String output = caseName + " - Test failed"+ Main.test().addScreenCapture(Reporter.capture(driver));
        Main.test().log(LogStatus.FAIL, output);
    }

    private void testNoFirstName(String caseName) {
        try {
            regObj.getFirstNameError().isDisplayed();
            this.passed(caseName);

        } catch (Exception e) {
            this.failed(caseName);
        }
    }

    private void testNoLastName(String caseName) {
        try {
            regObj.getLastNameError().isDisplayed();
            this.passed(caseName);
        } catch (Exception e) {
            this.failed(caseName);
        }
    }

    private void testNoEmail(String caseName) {
        try {
            regObj.getEmailError().isDisplayed();
            this.passed(caseName);;

        } catch (Exception e) {
            this.failed(caseName);
        }
    }

    private void testWrongEmailFormat(String caseName) {
        try {
            regObj.getEmailError().isDisplayed();
            this.passed(caseName);
        } catch (Exception e) {
            this.failed(caseName);
        }
    }

    private void testNoPassword(String caseName) {
        try {
            regObj.getPasswordError().isDisplayed();
            this.passed(caseName);;

        } catch (Exception e) {
            this.failed(caseName);
        }
    }

    private void testWeakPassword(String caseName) {
        try {
            regObj.getConfirmPasswordError().isDisplayed();
            this.passed(caseName);;

        } catch (Exception e) {
            this.failed(caseName);
        }
    }

    private void testNoPasswordConfirmation(String caseName) {
        try {
            regObj.getPasswordError().isDisplayed();
            this.passed(caseName);;

        } catch (Exception e) {
            this.failed(caseName);
        }
    }

    private void testValidRegistration(String caseName) {
        String title = driver.getTitle();
        if (title.equals("My Account")) {
            this.passed(caseName);
        } else {
            this.failed(caseName);
        }
    }




}
