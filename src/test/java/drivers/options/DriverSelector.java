package drivers.options;

import utilities.Helpers;

public class DriverSelector {

    public static DriverLayout getDriver(String driverName) {
        switch (driverName) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", Helpers.getProperty("chromeDriverPath"));
                return new Chrome();
            case "firefox":
                System.setProperty("webdriver.gecko.driver", Helpers.getProperty("firefoxDriverPath"));
                return new Firefox();
            case "phatomjs":
                System.setProperty("phantomjs.binary.path", Helpers.getProperty("phantomjsDriverPath"));
                return new PhantomJs();
            default:
                System.out.println("No driver found");
                return null;
        }
    }

}

