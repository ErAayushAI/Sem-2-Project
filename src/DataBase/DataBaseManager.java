package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {
    private static String URL = "jdbc:mysql://localhost:3306/smartcityhub";
    private static String USER = "root";
    private static String PASSWORD = "";
    public static Connection connection;

    /**
     * to Register Driver.
     *
     * @throws ClassNotFoundException if Driver class Not Found
     */
    public static void getInstance() throws ClassNotFoundException {
        String driverManager = "com.mysql.cj.jdbc.Driver";
        Class.forName(driverManager);
        System.out.println("Driver Registered.");
    }

    /**
     * to get Connection to database.
     *
     * @throws SQLException if Connection to database not found
     */
    public static void getConnection() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        if (connection != null) {
            System.out.println("Connected");
        } else {
            System.out.println("Failed.");
        }
    }

    /**
     * to close connection to the database.
     *
     * @param con object of connection
     * @throws SQLException if connection not found
     */
    public void closeConnectin(Connection con) throws SQLException {
        con.close();
    }

}
