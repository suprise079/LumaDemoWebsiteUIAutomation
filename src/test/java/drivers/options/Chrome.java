package drivers.options;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chrome implements DriverLayout {
    @Override
    public WebDriver setDriver() {
        WebDriver driver = new ChromeDriver();

        return driver;
    }
}
