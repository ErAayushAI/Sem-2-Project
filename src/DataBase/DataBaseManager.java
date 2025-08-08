package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {
    private static String URL = "jdbc:mysql://localhost:3306/smartcityhub";
    private static String USER = "root";
    private static String PASSWORD = "";
    public static Connection connection;

    //    Static Block for Driver Registered Only once.
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL Driver Registered.");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver registration failed: " + e.getMessage());
        }
    }

    /**
     * Return Connection to the database.
     *
     * @return Connection Object
     * @throws SQLException If connection Fails
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to SmartCityHub Database.");
        }
        return connection;
    }

    /**
     * to close connection to the database.
     *
     * @param con object of connection
     * @throws SQLException if connection not found
     */
    public void closeConnectin(Connection con) throws SQLException {
        if (con != null) {
            try {
                con.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.err.println("Failed to close connection: " + e.getMessage());
            }
        }

    }
}
