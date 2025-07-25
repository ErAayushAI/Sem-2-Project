package Authentication;


import DataBase.AreaDAO;
import DataBase.BusDAO;
//import DataBase.DataBaseManager;
import DataBase.EmergencyServiceDAO;
import DataBase.DataBaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Login {
    Connection connection = DataBaseManager.connection;

    public Login() {
        try {
            DataBaseManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    Scanner sc=new Scanner(System.in);
//        void loginMenu() {
//            System.out.println("Welcome to the Login System");
//            System.out.println("1. Admin Login");
//            System.out.println("2. Customer Login");
//            System.out.println("3. Customer Registration");
//            System.out.print("Select an option: ");
//
//            int choice = sc.nextInt();
//            sc.nextLine(); // Consume newline
//
//            switch (choice) {
//                case 1:
//                    adminLogin();
//                    break;
//                case 2:
//                    customerLogin();
//                    break;
//                case 3:
//                    customerRegistration();
//                    break;
//                default:
//                    System.out.println("Invalid option!");
//            }
//        }

    public boolean adminLogin() throws SQLException {
        int attempts = 0;
        int MAX_ATTEMPTS = 3;
        int r=0;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter admin username: ");
            String username = sc.nextLine();

            System.out.print("Enter password: ");
            String password = sc.nextLine();

            String sql = "SELECT password FROM admin WHERE username = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("password");

                if (password.equals(dbPassword)) {
                    System.out.println("Admin login successful!");
                    r=1;
                    break;
                } else {
                    attempts++;
                    System.out.println("Invalid password! Attempts remaining: " + (MAX_ATTEMPTS - attempts));
                }
            } else {
                System.out.println("Admin not found!");
                attempts++;
                System.out.println("Attempts remaining: " + (MAX_ATTEMPTS - attempts));
            }
        }
        if(r==1)
        {
            return true;
        }
        else {
            System.out.println("Maximum login attempts reached. Please try again later.");
            return false;
        }
    }

    public boolean customerLogin() throws SQLException {
        int attempts = 0;
        int MAX_ATTEMPTS = 3;
        int r=0;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter customer username: ");
            String username = sc.nextLine();

            System.out.print("Enter password: ");
            String password = sc.nextLine();

            String sql = "select Password, FullName from customer where UserName = ?";
            PreparedStatement stmt =connection.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("password");
                String fullName = rs.getString("full_name");

                if (password.equals(dbPassword)) {
                    System.out.println("Customer login successful!");
                    System.out.println("Welcome, " + fullName + "!");
                    r=1;
                    break;
                } else {
                    attempts++;
                    System.out.println("Invalid password! Attempts remaining: " + (MAX_ATTEMPTS - attempts));
                }
            } else {
                System.out.println("Customer not found!");
                attempts++;
                System.out.println("Attempts remaining: " + (MAX_ATTEMPTS - attempts));
            }
        }
        if(r==1)
        {
            return true;
        }
        else {
            System.out.println("Maximum login attempts reached. Please try again later.");
            return false;
        }
    }

    public void customerRegistration() throws SQLException {
        System.out.println("Customer Registration");

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter full name: ");
        String fullName = sc.nextLine();

        // Check if username or email already exists
        String checkSql = "SELECT id FROM customers WHERE username = ? OR email = ?";
        PreparedStatement checkStmt = connection.prepareStatement(checkSql);
        checkStmt.setString(1, username);
        checkStmt.setString(2, email);

        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            System.out.println("Username or email already exists!");
            return;
        }

        // Insert new customer (password stored in plain text - not recommended for production)
        String insertSql = "INSERT INTO customers (username, password, email, full_name) VALUES (?, ?, ?, ?)";
        PreparedStatement insertStmt = connection.prepareStatement(insertSql);
        insertStmt.setString(1, username);
        insertStmt.setString(2, password);
        insertStmt.setString(3, email);
        insertStmt.setString(4, fullName);

        int rowsAffected = insertStmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Registration successful! You can now login.");
        } else {
            System.out.println("Registration failed!");
        }
    }

//        void adminDashboard() {
//            System.out.println("\nAdmin Dashboard");
//            System.out.println("1. View all customers");
//            System.out.println("2. Area");
//            System.out.println("3. Bus");
//            System.out.println("4. EmergencyServices");
//            System.out.println("5. Metro");
//            System.out.println("6. ParkingLot");
//            System.out.println("6. Route");
//            System.out.println("6. Schedule");
//            System.out.println("6. Station");
//            System.out.println("6. Street");
//            System.out.println("2. Logout");
//            System.out.print("Select an option: ");
//
//            int choice = sc.nextInt();
//            sc.nextLine(); // Consume newline
//
//            switch (choice) {
//                case 1: {
//                    viewAllCustomers();
//                    adminDashboard(); // Show menu again
//                    break;
//                }
//                case 2: {
//                    DataBase.AreaDAO a=new AreaDAO();
//                    System.out.println("1. To add Area");
//                    System.out.println("2. To Update Area");
//                    System.out.println("3. To delete Area");
//                    System.out.println("4. To exit");
//                    System.out.println("Enter choice: ");
//                    int ch=sc.nextInt();
//                    switch(ch) {
//                        case 1: {
//                            System.out.println("Enter area id: ");
//                            boolean b = a.addArea(sc.nextInt(););
//                            if (b) {
//                                System.out.println("Added successfully");
//                            }
//                            else {
//                                System.out.println("Failed");
//                            }
//                            break;
//                        }
//                        case 2:
//                        {
//                            boolean b=a.updateArea();
//                            if (b) {
//                                System.out.println("updated successfully");
//                            }
//                            else {
//                                System.out.println("Failed");
//                            }
//                            break;
//                        }
//                        case 3:
//                        {
//                            boolean b= a.deleteArea();
//                            if (b) {
//                                System.out.println("Deleted successfully");
//                            }
//                            else {
//                                System.out.println("Failed");
//                            }
//                            break;
//                        }
//                        case 4:
//                            System.out.println("Exiting.....");
//                            break;
//                        default:
//                            System.out.println("Invalid option!");
//                            break;
//                    }
//                    break;
//                }
//                case 3:
//                {
//                    DataBase.BusDAO a=new BusDAO();
//                    System.out.println("Bus System");
//                    System.out.println("1.Add bus");
//                    System.out.println("2.Update bus Location");
//                    System.out.println("3.Update bus Route");
//                    System.out.println("4.Exit");
//                    int ch=sc.nextInt();
//                    switch (ch)
//                    {
//                        case 1: {
//                            boolean b = a.addBus();
//                            if (b) {
//                                System.out.println("Added successfully");
//                            }
//                            else {
//                                System.out.println("Failed");
//                            }
//                            break;
//                        }
//                        case 2:
//                        {
//                            boolean b=a.updateBusLocation();
//                            if (b) {
//                                System.out.println("updated successfully");
//                            }
//                            else {
//                                System.out.println("Failed");
//                            }
//                            break;
//                        }
//                        case 3:
//                        {
//                            boolean b= a.updateBusRoute();
//                            if (b) {
//                                System.out.println("Updated successfully");
//                            }
//                            else {
//                                System.out.println("Failed");
//                            }
//                            break;
//                        }
//                        case 4:
//                            System.out.println("Exiting.....");
//                            break;
//                        default:
//                            System.out.println("Invalid option!");
//                            break;
//                    }
//                    break;
//                }
//                case 4: {
//                    DataBase.EmergencyServiceDAO a=new EmergencyServiceDAO();
//                    System.out.println("1. To add EmergencyServices");
//                    System.out.println("2.display all EmergencyServices");
//                    System.out.println("3. To exit");
//                    System.out.println("Enter choice: ");
//                    int ch=sc.nextInt();
//                    switch(ch) {
//                        case 1: //add
//                        {
//                            boolean b = a.addEmergencyService();
//                            if (b) {
//                                System.out.println("Added successfully");
//                            }
//                            else {
//                                System.out.println("Failed");
//                            }
//                            break;
//                        }
//                        case 2: //display
//                        {
//                            ArrayList b=a.getAllEmergencyService();
//                            break;
//                        }
//                        case 3:
//                            System.out.println("Exiting.....");
//                            break;
//                        default:
//                            System.out.println("Invalid option!");
//                            break;
//                    }
//                    break;
//                }
//                default:
//                    System.out.println("Invalid option!");
//                    adminDashboard();
//            }
//        }

//        void customerDashboard() {
//            System.out.println("\nCustomer Dashboard");
//            System.out.println("1. View profile");
//            System.out.println("2. Logout");
//            System.out.print("Select an option: ");
//
//            int choice = sc.nextInt();
//            sc.nextLine(); // Consume newline
//
//            switch (choice) {
//                case 1:
//                    System.out.println("Profile information would be displayed here.");
//                    customerDashboard(); // Show menu again
//                    break;
//                case 2:
//                    System.out.println("Logged out successfully.");
//                    break;
//                default:
//                    System.out.println("Invalid option!");
//                    customerDashboard();
//            }
//        }

    public void viewAllCustomers() throws SQLException {
        String sql = "SELECT id, username, email, full_name, created_at FROM customers";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\nList of all customers:");
        System.out.println("ID\tUsername\tEmail\t\tFull Name\tJoined Date");

        while (rs.next()) {
            System.out.println(
                    rs.getInt("id") + "\t" +
                            rs.getString("username") + "\t" +
                            rs.getString("email") + "\t" +
                            rs.getString("full_name") + "\t" +
                            rs.getTimestamp("created_at")
            );
        }
    }
}

// for database
//-- Create database
//CREATE DATABASE IF NOT EXISTS login_system;
//USE login_system;
//
//-- Create admin table
//CREATE TABLE IF NOT EXISTS admins (
//        id INT AUTO_INCREMENT PRIMARY KEY,
//        username VARCHAR(50) NOT NULL UNIQUE,
//password VARCHAR(50) NOT NULL, -- Storing plain text password (not recommended)
//email VARCHAR(100) NOT NULL UNIQUE,
//created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
//);
//
//        -- Create customer table
//CREATE TABLE IF NOT EXISTS customers (
//        id INT AUTO_INCREMENT PRIMARY KEY,
//        username VARCHAR(50) NOT NULL UNIQUE,
//password VARCHAR(50) NOT NULL, -- Storing plain text password (not recommended)
//email VARCHAR(100) NOT NULL UNIQUE,
//full_name VARCHAR(100),
//created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
//);
//
//        -- Insert sample admin (password is 'admin123')
//INSERT INTO admins (username, password, email)
//VALUES ('admin', 'admin123', 'admin@example.com');
//
//-- Insert sample customer (password is 'customer123')
//INSERT INTO customers (username, password, email, full_name)
//VALUES ('customer1', 'customer123', 'customer@example.com', 'John Doe');

