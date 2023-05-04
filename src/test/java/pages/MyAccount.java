package pages;

import drivers.CurrentDriver;
import objects.MyAccountObjects;
import org.openqa.selenium.WebDriver;
import utilities.Helpers;
import utilities.LoggedUser;

public class MyAccount {
    WebDriver driver;
    MyAccountObjects objs;
    LoggedUser user;
    public MyAccount() {
        this.driver = CurrentDriver.getDriver();
        objs = new MyAccountObjects();
        user = LoggedUser.getInstance();
    }

    public void verifyAccount(){
        String fullName = objs.getFullName().getText();
        String email = objs.getEmail().getText();

        if ( fullName.contains(user.getFirstName()) && fullName.contains(user.getLastName())) {
            System.out.println("Full name: '" + fullName + "' is correct");
//            Helpers.passed("Full name: '" + fullName + "' is correct");
        }
        else {
            System.out.println("Full name: '" + fullName + "' is incorrect");
//            Helpers.failed("Full name: '" + fullName + "' is incorrect");
        }

        if ( email.contains(user.getEmail())) {
            System.out.println("Email: '" + email + "' is correct");
//            Helpers.passed("Email: '" + email + "' is correct");
        }
        else {
            System.out.println("Email: '" + email + "' is incorrect");
//            Helpers.failed("Email: '" + email + "' is incorrect");
        }


    }
}
