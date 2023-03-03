package objects;

import drivers.CurrentDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Constants;

public class PaymentsObjects {
    WebDriver driver;

    public PaymentsObjects() {
        CurrentDriver currentDriver = CurrentDriver.getInstance(Constants.BROWSER);
        this.driver = currentDriver.getDriver();
    }

    public WebElement getAddressCheckbox(){
        return driver.findElement(By.name("billing-address-same-as-shipping"));
    }

    public WebElement getUpdateButton(){
        return driver.findElement(By.cssSelector("div.payment-method-billing-address > div > fieldset > div.actions-toolbar > div > button.action.action-update"));
    }

    public WebElement getPlaceOrderButton(){
        return driver.findElement(By.xpath("//div[@id='checkout-payment-method-load']//div[@class='payment-group']/div[2]//button[@title='Place Order']/span[.='Place Order']"));
    }
}
