package objects;

import drivers.CurrentDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditAccountObjects {
    WebDriver driver;

    public EditAccountObjects(){
        this.driver = CurrentDriver.getDriver();
    }

    public WebElement getEditButton(){
        return driver.findElement(By.cssSelector("div.box.box-information > div.box-actions > a.action.edit"));
    }

    public WebElement getFirstName(){
        return driver.findElement(By.id("firstname"));
    }

    public WebElement getLastName(){
        return driver.findElement(By.id("lastname"));
    }

    public WebElement getChangeEmailCheckBox(){
        return driver.findElement(By.id("change-email"));
    }

    public WebElement getChangePasswordCheckBox(){
        return driver.findElement(By.id("change-password"));
    }

    public WebElement getEmail(){
        return driver.findElement(By.id("email"));
    }

    public WebElement getCurrentPassword(){
        return driver.findElement(By.id("current-password"));
    }

    public WebElement getNewPassword(){
        return driver.findElement(By.id("password"));
    }

    public WebElement getConfirmPassword(){
        return driver.findElement(By.id("password-confirmation"));
    }

    public WebElement getSaveButton(){
        return driver.findElement(By.cssSelector("#form-validate > div > div.primary > button"));
    }


}
