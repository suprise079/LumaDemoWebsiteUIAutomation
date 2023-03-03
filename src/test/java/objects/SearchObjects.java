package objects;

import drivers.CurrentDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Constants;

import java.util.List;

public class SearchObjects {

    WebDriver driver;

    public SearchObjects() {
        CurrentDriver currentDriver = CurrentDriver.getInstance(Constants.BROWSER);
        this.driver = currentDriver.getDriver();
    }

    public WebElement getSearchField(){
        return driver.findElement(By.id("search"));
    }

    public WebElement getSearchButton(){
        return driver.findElement(By.cssSelector("button.action.search"));
    }

    public WebElement getMessageNotice(){
        return driver.findElement(By.cssSelector("#maincontent > div.columns > div.column.main > div.message.notice > div"));
    }

    public List<WebElement> getSearchResults(){
        return driver.findElements(By.cssSelector("ol.products.list.items.product-items > li"));
    }
}
