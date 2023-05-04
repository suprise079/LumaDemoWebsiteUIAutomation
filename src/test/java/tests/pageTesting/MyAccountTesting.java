package tests.pageTesting;

import drivers.CurrentDriver;
import org.junit.Test;
import pages.MyAccount;
import pages.Registration;
import utilities.Constants;
import utilities.LoggedUser;

public class MyAccountTesting {

    @Test
    public void verifyAccountDetails() throws InterruptedException {
        CurrentDriver currentDriver = CurrentDriver.getInstance("chrome");
        LoggedUser user = LoggedUser.getInstance();
        MyAccount myAccount = new MyAccount();
        Registration reg = new Registration();

    reg.registerDefaultUser();
        user.setLoggedUser(Constants.DEFAULT_FIRST_NAME, Constants.DEFAULT_LAST_NAME, Constants.DEFAULT_EMAIL, Constants.DEFAULT_PASSWORD);
        myAccount.verifyAccount();

    }


}
