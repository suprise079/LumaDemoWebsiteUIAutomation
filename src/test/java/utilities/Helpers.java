package utilities;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Helpers {

    public static void select(WebElement element, String option) {
        Select select = new Select(element);
        select.selectByIndex(0);
        try {
            //use this once you have created the excel sheet for flights
            select.selectByVisibleText(option);
        } catch (Exception e) {
            //Random number between 1 and 3
            int index = (int) (Math.random() * 3) + 1;
            select.selectByIndex(index);

        }
        select = null;
    }

    //Take screenshot of the current browser page


    public static void scrollDown(WebDriver driver, int value){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "window.scrollBy(0,"+value+")";
        js.executeScript(script);
    }



    public static String getProperty(String key){
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream(Constants.PROPERTIES_PATH));
            return properties.getProperty(key);
        } catch (Exception e){
            System.err.println("Error reading properties file"+e.getMessage());
            return null;
        }
    }

}
