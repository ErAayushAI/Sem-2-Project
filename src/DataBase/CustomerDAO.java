package DataBase;

import Model.CustomerLog;
import Validation.InputValidator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerDAO {
    private Connection connection;

    public CustomerDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            System.out.println("❌ No database connection provided to Customer.");
        }
    }

    /**
     * To delete customer using procedure
     *
     * @param scanner Object for user input
     */
    public void deleteCustomerById(Scanner scanner) throws SQLException {
        System.out.println("\n🗑️ Delete Customer by ID (Stored Procedure)");

        int customerId = InputValidator.getValidInt(scanner, "Enter customer ID to delete: ");

        String callSql = "{CALL DeleteCustomerById(?)}";
        try (CallableStatement stmt = connection.prepareCall(callSql)) {
            stmt.setInt(1, customerId);
            stmt.execute();
            System.out.println("✅ Customer deleted successfully.");
        } catch (SQLException e) {
            if ("45000".equals(e.getSQLState())) {
                System.out.println("❌ " + e.getMessage());
            } else {
                throw e;
            }
        }
    }

    public List<CustomerLog> getDeletedCustomers() {
        List<CustomerLog> logs = new ArrayList<>();
        String sql = "{CALL get_deleted_customers()}";

        try (CallableStatement stmt = connection.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("customerId");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String fullName = rs.getString("fullName");
                Timestamp deletedAt = rs.getTimestamp("deletedAt");

                CustomerLog log = new CustomerLog(id, username, password, email, fullName, deletedAt);
                logs.add(log);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error while calling get_deleted_customers: " + e.getMessage());
        }

        return logs;
    }

    //Display List of Customers
    public void viewAllCustomers() throws SQLException {
        String sql = "SELECT id, username, email, fullName, createdAt FROM customer";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n👥 List of All Registered Customers");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("%-5s %-15s %-25s %-20s %-20s%n", "ID", "Username", "Email", "Full Name", "Joined Date");
            System.out.println("--------------------------------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String fullName = rs.getString("fullName");
                Timestamp createdAt = rs.getTimestamp("createdAt");

                System.out.printf("%-5d %-15s %-25s %-20s %-20s%n",
                        id,
                        username,
                        email,
                        fullName,
                        createdAt.toLocalDateTime().toLocalDate());
            }

            System.out.println("--------------------------------------------------------------------------------");
        }
    }

    //Display deleted customer List
    public void printDeletedCustomer(List<CustomerLog> logs) {
        if (logs == null || logs.isEmpty()) {
            System.out.println("⚠️ No deleted customer records found.");
            return;
        }

        System.out.println("\n🗑️ Deleted Customer List");
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-20s %-25s %-20s%n", "ID", "Username", "Email", "Deleted At", "Full Name");
        System.out.println("--------------------------------------------------------------------------------------------------");

        for (CustomerLog log : logs) {
            String username = (log.getUsername() != null) ? log.getUsername() : "—";
            String email = (log.getEmail() != null) ? log.getEmail() : "—";
            String fullName = (log.getFullName() != null) ? log.getFullName() : "—";
            String deletedAt = (log.getDeletedAt() != null) ? log.getDeletedAt().toString() : "—";

            System.out.printf("%-5d %-20s %-20s %-25s %-20s%n",
                    log.getCustomerId(),
                    username,
                    email,
                    deletedAt,
                    fullName);
        }

        System.out.println("--------------------------------------------------------------------------------------------------");
    }
}
