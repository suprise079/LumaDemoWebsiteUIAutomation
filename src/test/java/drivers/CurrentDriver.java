package drivers;

import drivers.options.DriverLayout;
import drivers.options.DriverSelector;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class CurrentDriver {

    private static CurrentDriver instance = null;
    private static WebDriver driver = null;

    private CurrentDriver(String driverName){
        instantiate(driverName);
    }

    private WebDriver instantiate(String strategy){
        DriverLayout driverStrategy = DriverSelector.getDriver(strategy);
        driver = driverStrategy.setDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/");

        return driver;
    }

    public static CurrentDriver getInstance(String driverName){
        if(instance == null){
            instance = new CurrentDriver(driverName);
        }

        return instance;
    }

    public static void closeDriver() {
        instance = null;
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
