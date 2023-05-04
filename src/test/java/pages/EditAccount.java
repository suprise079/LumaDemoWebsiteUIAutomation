package pages;

import drivers.CurrentDriver;
import objects.EditAccountObjects;
import org.openqa.selenium.WebDriver;
import utilities.LoggedUser;

public class EditAccount {

    WebDriver driver;
    EditAccountObjects objs;
    LoggedUser user;

    public EditAccount() {
        this.driver = CurrentDriver.getDriver();
        objs = new EditAccountObjects();
        user = LoggedUser.getInstance();
    }

    public void openEditAccountPage() {
        objs.getEditButton().click();
    }

    public void editAccountInfo(String firstName, String lastName){
        objs.getFirstName().sendKeys(firstName);
        objs.getLastName().sendKeys(lastName);
        objs.getSaveButton().click();

    }

    public void changeEmail(String email){
        objs.getChangeEmailCheckBox().click();
        objs.getEmail().sendKeys(email);
        objs.getCurrentPassword().sendKeys(user.getPassword());
    }

    public void changePassword(String newPassword){
        objs.getChangePasswordCheckBox().click();
        objs.getCurrentPassword().sendKeys(user.getPassword());
        objs.getNewPassword().sendKeys(newPassword);
        objs.getConfirmPassword().sendKeys(newPassword);
    }

    public void saveChanges(){
        objs.getSaveButton().click();
    }


}
