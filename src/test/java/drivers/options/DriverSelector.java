package drivers.options;

public class DriverSelector {

    public static DriverLayout getDriver(String driverName) {
        switch (driverName) {
            case "chrome":
                return new Chrome();
            case "firefox":
                return new Firefox();
            case "phatomjs":
                return new PhantomJs();
            default:
                System.out.println("No driver found");
                return null;
        }
    }

}

