package objects;

import drivers.CurrentDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Constants;

import java.util.List;

public class RegistrationObjects {

    WebDriver driver;

    public RegistrationObjects() {
        CurrentDriver current = CurrentDriver.getInstance(Constants.BROWSER);
        this.driver = current.getDriver();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public WebElement getRegistrationLink(){
        return driver.findElement(By.ByLinkText.linkText("Create an Account"));
    }

    public WebElement getFirstName(){
        return driver.findElement(By.id("firstname"));
    }

    public WebElement getLastName(){
        return driver.findElement(By.id("lastname"));
    }

    public WebElement getEmail(){
        return driver.findElement(By.id("email_address"));
    }

    public WebElement getPassword(){
        return driver.findElement(By.id("password"));
    }

    public WebElement getConfirmPassword(){
        return driver.findElement(By.id("password-confirmation"));
    }

    public WebElement getSubmitButton(){
        return driver.findElement(By.cssSelector("#form-validate > div > div.primary > button"));
    }

    public WebElement getFirstNameError(){
        return driver.findElement(By.id("firstname-error"));
    }

    public WebElement getLastNameError(){
        return driver.findElement(By.id("lastname-error"));
    }

    public WebElement getEmailError(){
        return driver.findElement(By.id("email_address-error"));
    }

    public WebElement getPasswordError(){
        return driver.findElement(By.id("password-error"));
    }

    public WebElement getConfirmPasswordError(){
        return driver.findElement(By.id("password-confirmation-error"));
    }
}
