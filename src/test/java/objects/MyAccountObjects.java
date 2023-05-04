package objects;

import drivers.CurrentDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccountObjects {

    WebDriver driver;

    public MyAccountObjects(){
        this.driver = CurrentDriver.getDriver();
    }

    public WebElement getFullName(){
        return driver.findElement(By.cssSelector("#maincontent > div.columns > div.column.main > div.block.block-dashboard-info > div.block-content > div.box.box-information > div.box-content > p"));
    }

    public WebElement getEmail(){
        return driver.findElement(By.cssSelector("#maincontent > div.columns > div.column.main > div.block.block-dashboard-info > div.block-content > div.box.box-information > div.box-content > p"));
    }

    public WebElement getNameInHeader(){
        return driver.findElement(By.cssSelector("body > div.page-wrapper > header > div.panel.wrapper > div > ul > li.greet.welcome > span"));
    }
}
