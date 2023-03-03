package objects;

import drivers.CurrentDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Constants;

public class HomeObjects {

    WebDriver driver;

    public HomeObjects() {

        CurrentDriver currentDriver = CurrentDriver.getInstance(Constants.BROWSER);
        this.driver = currentDriver.getDriver();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public WebElement getHomeLink() {
        return driver.findElement(By.cssSelector("body > div.page-wrapper > header > div.header.content > a"));
    }

    public WebElement getProduct(int index) {
        String selector = "div.block.widget.block-products-list.grid > div > div > ol > li";
        return driver.findElements(By.cssSelector(selector)).get(index);
    }

    public WebElement getProductLink(int index) {
        WebElement container = this.getProduct(index);
        return container.findElement(By.cssSelector("div > a"));
    }

    public WebElement getProductSizeButton(int productIndex, int sizeIndex) {
        WebElement container = this.getProduct(productIndex);
        return container.findElement(By.cssSelector(" div.swatch-attribute.size > div > div:nth-child(" + sizeIndex + ")"));
    }

    public WebElement getProductColorButton(int productIndex, int colorIndex) {
        WebElement container = this.getProduct(productIndex);
        return container.findElement(By.cssSelector(" div.swatch-attribute.color > div > div:nth-child(" + colorIndex + ")"));
    }

    public WebElement getAddToCartButton(int productIndex) {
        WebElement container = this.getProduct(productIndex);
        return container.findElement(By.cssSelector("form[method='post'] > button[title='Add to Cart']"));
    }

    public WebElement getCheckOutButton() {
        return driver.findElement(By.cssSelector("div.header.content > div.minicart-wrapper > a"));
    }

    public WebElement getCartCounter(){
        return driver.findElement(By.cssSelector("div.minicart-wrapper > a > span.counter.qty > span.counter-number"));
    }

    public WebElement getSuccessNotification() {
        return driver.findElement(By.cssSelector("#maincontent > div.page.messages > div:nth-child(2) > div > div"));
    }

    public WebElement getProccedCheckoutButton() {
        return driver.findElement(By.id("top-cart-btn-checkout"));
    }

    public WebElement getDeleteItemButton() {
        return driver.findElement(By.cssSelector("#mini-cart > li > div > div > div.product.actions > div.secondary > a"));
    }




}
