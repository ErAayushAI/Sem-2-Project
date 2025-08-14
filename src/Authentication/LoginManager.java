package Authentication;

import java.sql.*;
import java.util.Scanner;

public class LoginManager {
    private final Connection connection;
    private final Scanner scanner;

    public LoginManager(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public boolean adminLogin() throws SQLException {
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter admin username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            String sql = "SELECT password FROM admin WHERE username = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String dbPassword = rs.getString("password");
                    if (password.equals(dbPassword)) {
                        System.out.println("✅ Admin login successful!");
                        return true;
                    } else {
                        attempts++;
                        System.out.println("❌ Invalid password! Attempts remaining: " + (MAX_ATTEMPTS - attempts));
                    }
                } else {
                    attempts++;
                    System.out.println("❌ Admin not found! Attempts remaining: " + (MAX_ATTEMPTS - attempts));
                }
            }
        }

        System.out.println("⚠️ Maximum login attempts reached. Please try again later.");
        return false;
    }

    public boolean customerLogin() throws SQLException {
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter customer username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            String sql = "SELECT password, fullName FROM customer WHERE username = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String dbPassword = rs.getString("password");
                    String fullName = rs.getString("fullName");

                    if (password.equals(dbPassword)) {
                        System.out.println("✅ Customer login successful!");
                        System.out.println("Welcome, " + fullName + "!");
                        return true;
                    } else {
                        attempts++;
                        System.out.println("❌ Invalid password! Attempts remaining: " + (MAX_ATTEMPTS - attempts));
                    }
                } else {
                    attempts++;
                    System.out.println("❌ Customer not found! Attempts remaining: " + (MAX_ATTEMPTS - attempts));
                }
            }
        }

        System.out.println("⚠️ Maximum login attempts reached. Please try again later.");
        return false;
    }

    public void customerRegistration() throws SQLException {
        System.out.println("\n📝 Customer Registration");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine();

        String checkSql = "SELECT id FROM customer WHERE username = ? OR email = ?";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
            checkStmt.setString(1, username);
            checkStmt.setString(2, email);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println("❌ Username or email already exists!");
                return;
            }
        }

        String insertSql = "INSERT INTO customer (username, password, email, fullName) VALUES (?, ?, ?, ?)";
        try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
            insertStmt.setString(1, username);
            insertStmt.setString(2, password);
            insertStmt.setString(3, email);
            insertStmt.setString(4, fullName);

            int rowsAffected = insertStmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Registration successful! You can now login.");
            } else {
                System.out.println("❌ Registration failed!");
            }
        }
    }
}