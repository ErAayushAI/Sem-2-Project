package Main;

import DataBase.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DataBaseManager.getConnection();
            MainMenu menu = new MainMenu(connection);
            menu.start();
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }
}