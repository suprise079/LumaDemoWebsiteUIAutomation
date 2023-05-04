package Database;

import com.mongodb.util.JSON;
import utilities.Constants;
import utilities.ExcelFunctions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Database {

    private Connection conn = null;
    private Statement stmt = null;
    PreparedStatement prep;

    public Database(Connection conn){
        this.conn = conn;
        try{
            stmt = this.conn.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setPrepParams(String[][] data, int paramsCount){
        int paramIndex = 1;

        System.out.println("Starting to set the params....");
       try{
           for (int i = 0; i <= data.length - 1; i++) {

               for (int j = 0; j < data[i].length; j++) {
                   prep.setString(paramIndex, data[i][j]);
                   paramIndex++;
               }

               //Still to write the expected output
               prep.setString(paramsCount, "expectedOutput");
               paramIndex = 1;

               //Commit the batch
               prep.addBatch();
               prep.executeUpdate();
           }
       } catch (Exception e){
           e.printStackTrace();
       }
    }

    public void deleteAllTables(){
        try{
            String sql = "DROP TABLE IF EXISTS users";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.executeUpdate();

            sql = "DROP TABLE IF EXISTS products";
            prep = conn.prepareStatement(sql);
            prep.executeUpdate();

            sql = "DROP TABLE IF EXISTS search_inputs";
            prep = conn.prepareStatement(sql);
            prep.executeUpdate();

            sql = "DROP TABLE IF EXISTS checkout";
            prep = conn.prepareStatement(sql);
            prep.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public void createUsersTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS users" +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " scenario VARCHAR(500), " +
                    " firstname VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " isNews VARCHAR(255), " +
                    " email VARCHAR(255), " +
                    " password VARCHAR(255), " +
                    " isRegistered bool not null DEFAULT false, " +
                    " expectedOutput VARCHAR(255), " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Created registration table in given database...");

            //Load table with excel data
            String[][] data = ExcelFunctions.getSheetData("Test registration", Constants.END_COLON, "User information");
            prep = conn.prepareStatement("INSERT INTO users (scenario, firstname, lastname, isNews, email, password, expectedOutput) VALUES (?, ?, ?, ?, ?, ?, ?)");
            setPrepParams(data, 7);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void createProductOptionsTable() {
        try {

            String sql = "CREATE TABLE IF NOT EXISTS products" +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " scenario VARCHAR(500), " +
                    " size VARCHAR(255), " +
                    " color VARCHAR(255), " +
                    " quantity VARCHAR(255), " +
                    " expectedOutput VARCHAR(255), " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Created products table in given database...");

            //Load table with excel data
            String[][] data = ExcelFunctions.getSheetData("Test add product to cart", Constants.END_COLON, "Product specifications");
            prep = conn.prepareStatement("INSERT INTO products (scenario, size, color, quantity, expectedOutput) VALUES (?, ?, ?, ?, ?)");
            setPrepParams(data, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createSearchInput() {
        try {
            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS search_inputs" +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " scenario VARCHAR(500), " +
                    " keyword VARCHAR(255), " +
                    " expectedOutput VARCHAR(255), " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Created SearchInputs table in given database...");

            //Load table with excel data
            String[][] data = ExcelFunctions.getSheetData("Test the search field", Constants.END_COLON, "Search Inputs");
            prep = conn.prepareStatement("INSERT INTO search_inputs (scenario, keyword, expectedOutput) VALUES (?, ?, ?)");
            setPrepParams(data, 3);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void createCheckoutTable(){
        try {
            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS checkout" +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " scenario VARCHAR(500), " +
                    "email VARCHAR(255), " +
                    " firstname VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " company VARCHAR(255), " +
                    " address VARCHAR(255), " +
                    " city VARCHAR(255), " +
                    " state VARCHAR(255), " +
                    " zip VARCHAR(255), " +
                    " country VARCHAR(255), " +
                    " phone VARCHAR(255), " +
                    " shippingMethod VARCHAR(255), " +
                    " expectedOutput VARCHAR(255), " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Created checkout table in given database...");

            //Load table with excel data
            String[][] data = ExcelFunctions.getSheetData("Test transaction flow", Constants.END_COLON, "Checkout information");
            prep = conn.prepareStatement("INSERT INTO checkout (scenario, email, firstname, lastname, company, address, city, state, zip, country, phone, shippingMethod, expectedOutput) " +
                    "VALUES (?,? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            setPrepParams(data, 13);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
