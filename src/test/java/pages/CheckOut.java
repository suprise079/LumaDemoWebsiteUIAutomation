package pages;

import drivers.CurrentDriver;
import objects.HomeObjects;
import objects.ShippingObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Constants;

public class CheckOut {

    WebDriver driver;
    ShippingObjects shipObjs;
    HomeObjects homeObjs;

    public CheckOut() {
        CurrentDriver currentDriver = CurrentDriver.getInstance(Constants.BROWSER);
        this.driver = currentDriver.getDriver();
        shipObjs = new ShippingObjects();
        homeObjs = new HomeObjects();
    }

    public void openCheckOut() {

        try {
            Thread.sleep(4000);
            homeObjs.getCheckOutButton().click();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void proceedCheckOutButton(){
        homeObjs.getProccedCheckoutButton().click();
    }

    public void deleteItem(){
        homeObjs.getProccedCheckoutButton().click();
    }

    public void fillShippingForm(boolean isLogged) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //Check if the first form field "first name" is visible
        try{
            wait.until(ExpectedConditions.visibilityOf(shipObjs.getFirstNameField()));
            //inside try catch because the form is reuse where email is not there at times
            shipObjs.getEmailField().sendKeys("Tadda@gmail.com");
        }catch (Exception e) {
            // In case the is a saved password in the browser, the form will be filled with the saved password
            try{
                wait.until(ExpectedConditions.visibilityOf(shipObjs.getSavedAddressContainer()));
                this.clickContinue();
            }catch (Exception ex) {
                return;
            }

        }

        if (!isLogged){
            shipObjs.getFirstNameField().sendKeys("Tadda");
            shipObjs.getLastNameField().sendKeys("maluleke");
        }
        shipObjs.getCompanyField().sendKeys("Tadda");
        shipObjs.getStreet1Field().sendKeys("134 jobourt street");
        shipObjs.getStreet2Field().sendKeys("jobourt");
        shipObjs.getStreet3Field().sendKeys("jobourt");
        shipObjs.getCityField().sendKeys("johannesburg");


        Select country = new Select(shipObjs.getCountrySelector());
        country.selectByVisibleText("South Africa");

        try{
            Thread.sleep(3000);
            shipObjs.getProvinceSelector().sendKeys("Gauteng");
        }catch (Exception e) {
            Select province = new Select(shipObjs.getProvinceSelector());
            province.selectByIndex(3);
        }

        shipObjs.getZipField().sendKeys("2000");
        shipObjs.getTelephoneField().sendKeys("011 123 4567");


        try{
            Thread.sleep(3000);
//            shipObjs.getShippingMehod(0).click();
        }catch (InterruptedException e) {
            System.out.println("Error: " + e);
        }
    }

    public void clickContinue(){
        try{
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(shipObjs.getContinueButton()));
            shipObjs.getContinueButton().click();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }
}

















