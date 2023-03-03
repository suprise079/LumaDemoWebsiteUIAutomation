package pages;

import drivers.CurrentDriver;
import objects.SearchObjects;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Constants;
import utilities.ExcelFunctions;
import utilities.Helpers;

import java.util.List;

public class Search {
    WebDriver driver;
    SearchObjects objs;

    public Search(){
        CurrentDriver currentDriver = CurrentDriver.getInstance(Constants.BROWSER);
        this.driver = currentDriver.getDriver();
        this.objs = new SearchObjects();

    }

    public void find(String search){
        objs.getSearchField().sendKeys(search);
        objs.getSearchButton().click();

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).perform();
        objs.getSearchField().clear();
    }

    public void findMultiple(){
        String[][] words = ExcelFunctions.getSheetData("Test the search field", Constants.END_COLON, "Search Inputs");
        for (int i = 0; i < words.length; i++) {
            int count = 0;
            try{
                this.find(words[i][count++]);
                count++;    //skip keys
                callTest(words[i][count]);
                Thread.sleep(1000);
            } catch (Exception e){
                Helpers.failed(words[i][2], driver);
            }
        }
    }

    public void callTest(String caseName){
        if (caseName.contains("keyword with length > 3")) testValidLength(caseName);
        if (caseName.contains("keyword with length < 3")) testShortLength(caseName);
        if (caseName.contains("Notification for no results")) testNoResults(caseName);
        if (caseName.contains("products return for valid  search")) testValidLength(caseName);
        if (caseName.contains("search by clicking enter")) testValidLength(caseName);
    }

    //test cases
    public void testValidLength(String caseName){
        if (this.getSearchResults() > 0){
            Helpers.passed(caseName);
        } else {
            Helpers.failed(caseName, driver);
        }
    }

    public void testShortLength(String caseName){
        try{
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(objs.getMessageNotice()));
            String message = this.getMessageNotice();
            if (message.contains("Minimum Search query length is 3")){
                Helpers.passed(caseName);
            } else {
                Helpers.failed(caseName, driver);
            }
        } catch (Exception e){
            Helpers.failed(caseName, driver);
        }
    }

    public void testNoResults(String caseName){
        try{
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(objs.getMessageNotice()));
            String message = this.getMessageNotice();
            if (message.contains("Your search returned no results.")){
                Helpers.passed(caseName);
            } else {
                Helpers.failed(caseName, driver);
            }
        } catch (Exception e){
            Helpers.failed(caseName, driver);
        }
    }

    public String getMessageNotice(){
        return objs.getMessageNotice().getText();
    }

    public int getSearchResults(){
        return objs.getSearchResults().size();
    }
}
