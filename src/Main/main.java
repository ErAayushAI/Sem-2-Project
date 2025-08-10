package Main;

import Authentication.Login;
import DataBase.DataBaseManager;

import java.sql.SQLException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws SQLException {
        Login l = new Login();
        Scanner sc = new Scanner(System.in);
        DataBaseManager con1= (DataBaseManager) DataBaseManager.getConnection();
        do{
            System.out.println("-------------------------------------------------");
            System.out.println("          Welcome to Smart city hub              ");
            System.out.println("-------------------------------------------------");
            System.out.println();
            l.loginMenu();
        }while(true);
    }
}

