package Database;

import org.testng.annotations.DataProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataProviders {

    private Connection conn;
    private Statement myStmt;
    private String query;
    ResultSet myRs;
    Object data[][];

    private void getData(String table, int columnCount){
        try{
            //get DB connection
            conn = DatabaseConn.getConnection();
            myStmt = conn.createStatement();

            //Get row count
            query = "SELECT COUNT(*) AS rowCount FROM " + table;
            ResultSet fetchCount = myStmt.executeQuery(query);
            fetchCount.next();
            int rowCount = fetchCount.getInt("rowCount");


            //Set results to response object
            myRs = myStmt.executeQuery("SELECT * FROM products");

            data = new Object[rowCount][columnCount];
        } catch (Exception e){
            System.err.println("Failed to get data from database"+e.getMessage());
        }
    }


    @DataProvider(name = "registrationData")
    public Object[][] getRegistrationData() throws SQLException {
        //Get registration data from database
        this.getData("users", 8);
        int row = 0;
        int col = 0;
        while (myRs.next()) {
            data[row][col++] = myRs.getString("scenario");
            data[row][col++] = myRs.getString("firstname");
            data[row][col++] = myRs.getString("lastname");
            data[row][col++] = myRs.getString("isNews");
            data[row][col++] = myRs.getString("email");
            data[row][col++] = myRs.getString("password");
            data[row][col++] = myRs.getString("expectedOutput");
            data[row][col] = myRs.getString("id");
            col = 0;
            row++;
        }
        return data;
    }

    @DataProvider(name = "productOptions")
    public Object[][] getProductOptions() {
        //Get product options data from database
        this.getData("products", 5);

        try{
            int row = 0;
            int col = 0;
            while (myRs.next()) {
                data[row][col++] = myRs.getString("scenario");
                data[row][col++] = myRs.getString("size");
                data[row][col++] = myRs.getString("color");
                data[row][col++] = myRs.getString("quantity");
                data[row][col] = myRs.getString("expectedOutput");
                col = 0;
                row++;
            }
            System.out.println("Data retrieved from database successfully!");
            return data;
        }catch (Exception e){
            System.out.println("Error while getting data from database!");
            e.printStackTrace();
            return  null;
        }

    }
}
