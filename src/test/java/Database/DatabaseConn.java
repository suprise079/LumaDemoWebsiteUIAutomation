package Database;

import utilities.Helpers;

import java.io.IOException;
import java.sql.*;

public class DatabaseConn {

    private Connection conn = null;
    private String hostname = "";
    private String username = "";
    private String password = "";
    private static DatabaseConn instance = null;

    private DatabaseConn(){

        try{
            this.getConnectionString();
            System.out.println("Connecting to database: " + hostname);
            System.out.println("User: " + username);
            System.out.println("Password: " + password);

            conn = DriverManager.getConnection(hostname, username, password);
            System.out.println("Connection successful");
//            Database database = new Database(conn);
//            database.deleteAllTables();
//            database.createUsersTable();
//            database.createProductOptionsTable();
//            database.createSearchInput();
//            database.createCheckoutTable();

        } catch (Exception e){
            e.printStackTrace();
        }
    }



    private void getConnectionString() throws IOException {
        hostname = Helpers.getProperty("hostname");
        username = Helpers.getProperty("username");
        password = Helpers.getProperty("password");
    }

    public static Connection getConnection(){
        if (instance == null){
            instance = new DatabaseConn();
        }
        return instance.conn;
    }



    protected void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            System.err.println("Database connection closed.");
        }
    }



}
