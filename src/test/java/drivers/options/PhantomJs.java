package drivers.options;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class PhantomJs implements DriverLayout {
    @Override
    public WebDriver setDriver() {
        System.setProperty("phantomjs.binary.path", "src/main/resources/phantomjs.exe");
        WebDriver driver = new PhantomJSDriver();

        return driver;
    }
}
