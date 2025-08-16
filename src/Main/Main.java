package Main;

import DataBase.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            connection = DataBaseManager.getConnection();
            MainMenu menu = new MainMenu(connection);
            menu.start();
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        } finally {
            try {
                DataBaseManager.closeConnection(connection);
            } catch (SQLException e) {
                System.err.println("Error while closing connection: " + e.getMessage());
            }
        }
    }
}