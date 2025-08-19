package DataBase;

import Model.Metro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Validation.InputValidator.getValidInt;

public class MetroDAO {
    private Connection connection;

    public MetroDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            System.out.println("❌ No database connection provided to Metro.");
        }
    }

    /**
     * Add a new Metro.
     *
     * @param scanner object for user input
     * @return true if Metro is added
     */
    public boolean addMetro(Scanner scanner) {
        System.out.println("\n========== ADD METRO ==========\n");

        String query = "INSERT INTO Metro (TrainName, Capacity, CurrentRouteID, CurrentAreaID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Train Name: ");
            scanner.nextLine();
            stmt.setString(1, scanner.nextLine().trim());

            int capacity = getValidInt(scanner, "Enter Capacity: ");
            stmt.setInt(2, capacity);

            int rid = getValidInt(scanner, "Enter Current Route Id: ");
            stmt.setInt(3, rid);

            int aid = getValidInt(scanner, "Enter Current Area Id: ");
            stmt.setInt(4, aid);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to load metro data: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get Metro by id.
     *
     * @param scanner object for user input
     * @return Object of Metro
     */
    public Metro getMetroByID(Scanner scanner) {
        System.out.println("\n========== METRO BY ID ==========\n");

        String query = "SELECT * FROM Metro WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int mid = getValidInt(scanner, "Enter Metro Id: ");
            stmt.setInt(1, mid);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Metro metro = new Metro();
                metro.setId(rs.getInt(1));
                metro.setTrainName(rs.getString(2));
                metro.setCapacity(rs.getInt(3));
                metro.setCurrentRouteID(rs.getInt(4));
                metro.setCurrentAreaID(rs.getInt(5));
                return metro;
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load metro data: " + e.getMessage());
        }
        return null;
    }

    /**
     * Get all Metros.
     *
     * @return list of Metros
     */
    public List<Metro> getAllMetros() {
        List<Metro> metros = new ArrayList<>();
        String query = "SELECT * FROM Metro";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Metro metro = new Metro();
                metro.setId(rs.getInt(1));
                metro.setTrainName(rs.getString(2));
                metro.setCapacity(rs.getInt(3));
                metro.setCurrentRouteID(rs.getInt(4));
                metro.setCurrentAreaID(rs.getInt(5));
                metros.add(metro);
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load metro data: " + e.getMessage());
        }
        return metros;
    }

    /**
     * Update Metro current Location.
     *
     * @param scanner object for user input
     * @return true if Metro is Updated
     */
    public boolean updateMetroLocation(Scanner scanner) {
        System.out.println("\n========== UPDATE METRO AREA ==========\n");

        String query = "UPDATE Metro SET CurrentAreaID = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int aid = getValidInt(scanner, "Enter new Area PinCode: ");
            stmt.setInt(1, aid);

            int mid = getValidInt(scanner, "Enter Metro Id to Update: ");
            stmt.setInt(2, mid);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to load metro data: " + e.getMessage());
        }
        return false;
    }

    /**
     * Update Metro current Route.
     *
     * @param scanner object for user input
     * @return true if Metro is Updated
     */
    public boolean updateMetroRoute(Scanner scanner) {
        System.out.println("\n========== UPDATE METRO ROUTE ==========");

        String query = "UPDATE Bus SET CurrentRouteId = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int rid = getValidInt(scanner, "Enter new Route Id: ");
            stmt.setInt(1, rid);

            int mid = getValidInt(scanner, "Enter Metro Id to Update: ");
            stmt.setInt(2, mid);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to load metro data: " + e.getMessage());
        }
        return false;
    }
}
