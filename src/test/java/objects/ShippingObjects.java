package objects;

import drivers.CurrentDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Constants;

public class ShippingObjects {

    WebDriver driver;

    public ShippingObjects() {
        CurrentDriver currentDriver = CurrentDriver.getInstance(Constants.BROWSER);
        this.driver = currentDriver.getDriver();
    }

    public WebElement getEmailField() {
        return driver.findElement(By.id("customer-email"));
    }

    public WebElement getFirstNameField() {
        return driver.findElement(By.name("firstname"));
    }

    public WebElement getLastNameField() {
        return driver.findElement(By.name("lastname"));
    }

    public WebElement getCompanyField() {
        return driver.findElement(By.name("company"));
    }

    public WebElement getStreet1Field() {
        return driver.findElement(By.name("street[0]"));
    }

    public WebElement getStreet2Field() {
        return driver.findElement(By.name("street[1]"));
    }

    public WebElement getStreet3Field() {
        return driver.findElement(By.name("street[2]"));
    }

    public WebElement getCityField() {
        return driver.findElement(By.name("city"));
    }

    public WebElement getCountrySelector() {
        return driver.findElement(By.name("country_id"));
    }

    public WebElement getProvinceSelector() {
        return driver.findElement(By.name("region"));
    }

    public WebElement getZipField() {
        return driver.findElement(By.name("postcode"));
    }

    public WebElement getTelephoneField() {
        return driver.findElement(By.name("telephone"));
    }

    public WebElement getShippingMehod(int methodIndex){
        if (methodIndex == 0){
            return driver.findElement(By.cssSelector("#checkout-shipping-method-load > table > tbody > tr:nth-child(1) > td:nth-child(1) > input"));
        }
        else{
            return driver.findElement(By.cssSelector("#checkout-shipping-method-load > table > tbody > tr:nth-child(2) > td:nth-child(1) > input"));
        }
    }

    public WebElement getContinueButton() {

        return driver.findElement(By.cssSelector("#shipping-method-buttons-container > div > button"));
    }

    public WebElement getSavedAddressContainer(){
        return driver.findElement(By.cssSelector("#checkout-step-shipping > div.field.addresses > div > div > div"));
    }
}


















