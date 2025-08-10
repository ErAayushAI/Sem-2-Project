package Main;

import Authentication.Login;

import java.sql.SQLException;


public class main {
    public static void main(String[] args) throws SQLException {
        Login l = new Login();
        do {
            System.out.println("-------------------------------------------------");
            System.out.println("          Welcome to Smart city hub              ");
            System.out.println("-------------------------------------------------");
            System.out.println();
            l.loginMenu();
        } while (true);
    }
}

