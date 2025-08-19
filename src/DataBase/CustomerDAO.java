package DataBase;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
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

        System.out.print("Enter customer ID to delete: ");
        int customerId = Integer.parseInt(scanner.nextLine().trim());

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
}
