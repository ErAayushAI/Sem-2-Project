package Main;

import Authentication.LoginManager;
import Dashboard.AdminDashboard;
import Dashboard.CustomerDashboard;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final Connection connection;

    public MainMenu(Connection connection) {
        this.connection = connection;
    }

    static {
        System.out.println("-------------------------------------------------");
        System.out.println("          Welcome to Smart City Hub              ");
        System.out.println("-------------------------------------------------");
    }

    public void start() throws SQLException {
        LoginManager loginManager = new LoginManager(connection, scanner);

        while (true) {
            System.out.println("-----------------------------");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Customer Registration");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    if (loginManager.adminLogin()) {
                        new AdminDashboard(scanner, connection).showMenu();
                    }
                    break;
                case 2:
                    if (loginManager.customerLogin()) {
                        new CustomerDashboard(scanner).showMenu();
                    }
                    break;
                case 3:
                    loginManager.customerRegistration();
                    break;
                case 0:
                    System.out.println("Thank you for using Smart City Hub. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
