package objects;

import drivers.CurrentDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Constants;

public class CheckOutSuccessObjects {

    WebDriver driver;

    public CheckOutSuccessObjects(){
        CurrentDriver currentDriver = CurrentDriver.getInstance(Constants.BROWSER);
        this.driver =  currentDriver.getDriver();
    }

    public WebElement getContinueShoppingLink(){
        return driver.findElement(By.linkText("Continue Shopping"));
    }

    public WebElement getCreateAccountLink(){
        return driver.findElement(By.linkText("Create an Account"));
    }
}
