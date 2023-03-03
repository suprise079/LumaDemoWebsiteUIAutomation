package objects;

import drivers.CurrentDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Constants;

public class SignInObjects {

    WebDriver driver;

    public SignInObjects() {
        CurrentDriver currentDriver = CurrentDriver.getInstance(Constants.BROWSER);
        this.driver = currentDriver.getDriver();
    }

    public WebElement getSignInLink() {
        return driver.findElement(By.ByLinkText.linkText("Sign In"));
    }

    public WebElement getEmailField() {
        return driver.findElement(By.id("email"));
    }

    public WebElement getPasswordField() {
        return driver.findElement(By.id("pass"));
    }

    public WebElement getSignInButton() {
        return driver.findElement(By.id("send2"));
    }

    public WebElement getEmailError() {
        return driver.findElement(By.id("email-error"));
    }

    public WebElement getPasswordError() {
        return driver.findElement(By.id("pass-error"));
    }

    public WebElement getErrorNotification(){
        return driver.findElement(By.cssSelector("#maincontent > div.page.messages > div:nth-child(2) > div > div > div"));
    }

    public WebElement getCaptureContainer(){
        return driver.findElement(By.cssSelector("#captcha-container-user_login > div"));
    }


}
