package pages;

import drivers.CurrentDriver;
import objects.productViewObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Constants;
import utilities.ExcelFunctions;
import utilities.Helpers;

public class Product {

    WebDriver driver;
    productViewObjects objs;

    public Product() {
        CurrentDriver currentDriver = CurrentDriver.getInstance(Constants.BROWSER);
        this.driver = currentDriver.getDriver();
        objs = new productViewObjects();
    }


    /**
     * This method adds a product to the cart
     * @sizeIndex : 0 - Small, 1 - Medium, 2 - Large, 3 - Extra Large
     * @colorIndex : 0 - Black, 1 - Orange, 2 - Red
     *
     * @param sizeIndex  The index of the size to select
     * @param colorIndex The index of the color to select
     * @param quantity   The quantity of the product to add to the cart
     */
    public void addToCart(String sizeIndex, String colorIndex, String quantity) {
         try{
             if (sizeIndex != "") objs.getProductSize(Integer.parseInt(sizeIndex)).click();
             if (colorIndex != "") objs.getProductColor(Integer.parseInt(colorIndex)).click();

             objs.getQuantityField().clear();
             objs.getQuantityField().sendKeys(String.valueOf(quantity));
             objs.getAddToCartButton().click();
         } catch (Exception e){
             System.out.println("Error: "+e.getMessage());
         }
    }

    public void addMultipleProducts() {
        String[][] products = ExcelFunctions.getSheetData("Test add product to cart", Constants.END_COLON, "Product specifications");
        for (int i = 0; i <= products.length - 1; i++) {
            int count = 0;
            String sizeIndex = products[i][count++];
            String colorIndex = products[i][count++];
            String quantity = products[i][count++];
            String caseName = products[i][count];
            this.addToCart(sizeIndex, colorIndex, quantity);
            this.callTests(caseName);
        }

    }

    public void addToWishList(){
        objs.getWishlistButton().click();
    }

    public void callTests(String caseName){
        if (caseName.contains("without choosing options")) testAddWithoutOptions(caseName);
        if (caseName.contains("without choosing color")) testAddWithoutColor(caseName);
        if (caseName.contains("without choosing size")) testAddWithoutSize(caseName);
        if (caseName.contains("increase product quantity")) testAddSuccess(caseName);
        if (caseName.contains("negative quantity")) testAddWithoutSize(caseName);
        if (caseName.contains("message on product add")) testAddSuccess(caseName);
        if (caseName.contains("product add without Color")) testAddWithoutColor(caseName);
        if (caseName.contains("product add without Size")) testAddWithoutSize(caseName);
        if (caseName.contains("product with quantity < 1")) testAddWithoutSize(caseName);
        if (caseName.contains("updated with product entered qauntity")) testAddSuccess(caseName);
    }


    public void testAddWithoutOptions(String caseName){
        try{
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(objs.getErrorMessage()));

            if (objs.getFieldErrors().size() > 2){
                Helpers.passed(caseName);
            } else {
                Helpers.failed(caseName, driver);
            }
        } catch (Exception e){
            Helpers.failed(caseName, driver);
        }
    }

    public void testAddWithoutSize(String caseName){
        try{
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.invisibilityOfAllElements(objs.getFieldErrors()));

            if (objs.getFieldErrors().size() == 1){
                Helpers.passed(caseName);
            } else {
                Helpers.failed(caseName, driver);
            }
        } catch (Exception e){
            Helpers.failed(caseName, driver);
        }
    }

    public void testAddWithoutColor(String caseName){
        try{
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.invisibilityOfAllElements(objs.getFieldErrors()));

            if (objs.getFieldErrors().size() == 1){
                Helpers.passed(caseName);
            } else {
                Helpers.failed(caseName, driver);
            }
        } catch (Exception e){
            Helpers.failed(caseName, driver);
        }
    }

    public void testAddSuccess(String caseName){
        try{
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(objs.getSuccessMessage()));
            String successMessage = objs.getSuccessMessage().getText();
            System.out.println("Success message: "+successMessage);
            if (successMessage.contains("You added")){
                Helpers.passed(caseName);
            } else {
                Helpers.failed(caseName, driver);
            }
        } catch (Exception e){
            Helpers.failed(caseName, driver);
        }
    }

    //Write a bunch of confirmation methods here

}
