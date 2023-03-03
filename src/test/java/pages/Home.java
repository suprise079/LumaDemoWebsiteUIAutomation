package pages;

import drivers.CurrentDriver;
import objects.HomeObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import utilities.Constants;

public class Home {

    WebDriver driver;
    HomeObjects objs;

    public Home(){
        CurrentDriver currentDriver = CurrentDriver.getInstance(Constants.BROWSER);
        this.driver = currentDriver.getDriver();
        objs = new HomeObjects();
    }

    public void openHomePage(){
    objs.getHomeLink().click();
    }

    public void viewProduct(int index){
        objs.getProductLink(index).click();
    }

    public void addToCart(int productIndex){
        objs.getProductSizeButton(productIndex, 1).click();
        objs.getProductColorButton(productIndex, 1).click();
        objs.getAddToCartButton(productIndex).click();
    }

    public void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,2000)");

    }

}
