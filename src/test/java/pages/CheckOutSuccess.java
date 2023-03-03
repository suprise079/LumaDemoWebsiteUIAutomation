package pages;

import drivers.CurrentDriver;
import objects.CheckOutSuccessObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Constants;

public class CheckOutSuccess {

    WebDriver driver;
    CheckOutSuccessObjects objs;

    public CheckOutSuccess(){
        CurrentDriver currentDriver = CurrentDriver.getInstance(Constants.BROWSER);
        this.driver = currentDriver.getDriver();
        this.objs = new CheckOutSuccessObjects();
    }

    public void continueShopping(){
        objs.getContinueShoppingLink().click();
    }

    public void createNewAccount(){
        objs.getCreateAccountLink().click();
    }
}
