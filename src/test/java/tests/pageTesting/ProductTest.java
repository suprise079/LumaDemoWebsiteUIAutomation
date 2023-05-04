package tests.pageTesting;

import Database.DataProviders;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Home;
import pages.Product;
import utilities.Reporter;

public class ProductTest {

    @BeforeClass
    public static void startTest() {
        Reporter.startTest("Add Product to cart", "Testing the add to cart functionality with different product options, checking if the product is added to cart and if the product is added to cart with the correct options");
    }

    @Test(dataProvider = "productOptions", dataProviderClass = DataProviders.class)
    public void AddProductTocart(String scenario, String size, String color, String quantity, String ExpectedResult) throws InterruptedException {
        Home home = new Home();
        Product product = new Product();

        home.openHomePage();
        home.scrollDown();
        home.viewProduct(1);
        System.out.println("size: "+ size + " color: " + color + " quantity: " + quantity);

        product.addToCart(size, color, quantity);

        product.callTests(scenario);

    }

    @AfterClass
    public static void endTest() {
        Reporter.endTest();
    }
}
