package objects;

import drivers.CurrentDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Constants;

import java.util.List;

public class productViewObjects {

    WebDriver driver;

    public productViewObjects() {
        CurrentDriver currentDriver = CurrentDriver.getInstance(Constants.BROWSER);
        this.driver = currentDriver.getDriver();
    }

    /**
     * Set the size of the product
     * Size index as follows:
     * 0 = XS, 1 = S, 2 = M, 3 = L, 4 = XL
     *
     * @param index
     * @return WebElement
     */
    public WebElement getProductSize(int index) {
        String selector = "#product-options-wrapper > div > div > div.swatch-attribute.size > div > div";
        return driver.findElements(By.cssSelector(selector)).get(index);
    }

    /**
     * Set the color of the product
     * Color index as follows:
     * 0 = Black, 1 = Orange, 2 = Red
     *
     * @param index
     * @return WebElement
     */
    public WebElement getProductColor(int index) {
        String selector = "#product-options-wrapper > div > div > div.swatch-attribute.color > div > div";
        try {
            return driver.findElements(By.cssSelector(selector)).get(index);
        } catch (Exception e) {
            return driver.findElements(By.cssSelector(selector)).get(0);
        }
    }

    public WebElement getQuantityField() {
        return driver.findElement(By.id("qty"));
    }

    public WebElement getAddToCartButton() {
        return driver.findElement(By.id("product-addtocart-button"));
    }

    public WebElement getSuccessMessage() {
        return driver.findElement(By.cssSelector("div.message-success.success.message > div"));
    }

    public WebElement getErrorMessage() {
        return driver.findElement(By.cssSelector("div.message-notice.notice.message > div"));
    }

    public List<WebElement> getFieldErrors() {
        return driver.findElements(By.cssSelector("div.mage-error"));
    }

    public WebElement getColorError() {
        return driver.findElement(By.id("super_attribute[93]-error"));
    }

    public WebElement getWishlistButton() {
        return driver.findElement(By.cssSelector("div.product-social-links > div > a.action.towishlist"));
    }
}
