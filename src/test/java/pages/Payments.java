package pages;

import drivers.CurrentDriver;
import objects.PaymentsObjects;
import org.openqa.selenium.WebDriver;
import utilities.Constants;
import utilities.Helpers;

public class Payments {
    WebDriver driver;
    PaymentsObjects objs;
    
    public Payments() {
        CurrentDriver currentDriver = CurrentDriver.getInstance(Constants.BROWSER);
        this.driver = currentDriver.getDriver();
        objs = new PaymentsObjects();
    }

    public void selectAddressCheckbox(){
        objs.getAddressCheckbox().click();
    }

    public void clickUpdateButton(){
        objs.getUpdateButton().click();
    }

    public void clickPlaceOrderButton(){
        try{
//            WebDriverWait wait = new WebDriverWait(driver, 10);
//            wait.until(ExpectedConditions.elementToBeClickable(objs.getPlaceOrderButton()));

            objs.getPlaceOrderButton().click();
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

    }
}
