package DataBase;

import Model.Bus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Validation.InputValidator.getValidInt;

public class BusDAO {
    private Connection connection;

    public BusDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            System.out.println("❌ No database connection provided to Bus.");
        }
    }

    /**
     * Add a new Bus.
     *
     * @param scanner object for user input
     * @return true if Bus is added
     */
    public boolean addBus(Scanner scanner) {
        System.out.println("\n========== ADD BUS ==========\n");

        String query = "INSERT INTO Bus (LicensePlate, Capacity, CurrentRouteID, CurrentAreaID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Bus License Plate: ");
            stmt.setString(1, scanner.next().trim());

            int capacity = getValidInt(scanner, "Enter Capacity: ");
            stmt.setInt(2, capacity);

            int cid = getValidInt(scanner, "Enter Current Route Id: ");
            stmt.setInt(3, cid);

            int aid = getValidInt(scanner, "Enter Current Area PinCode: ");
            stmt.setInt(4, aid);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to load bus data: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get Bus by id.
     *
     * @param scanner object for user input
     * @return Object of Bus
     */
    public Bus getBusByID(Scanner scanner) {
        System.out.println("\n========== BUS BY ID ==========\n");

        String query = "SELECT * FROM Bus WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int bid = getValidInt(scanner, "Enter Bus Id: ");
            stmt.setInt(1, bid);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Bus bus = new Bus();
                bus.setId(rs.getInt(1));
                bus.setLicensePlate(rs.getString(2));
                bus.setCapacity(rs.getInt(3));
                bus.setCurrentRouteId(rs.getInt(4));
                bus.setCurrentAreaID(rs.getInt(5));
                return bus;
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load bus data: " + e.getMessage());
        }
        return null;
    }

    /**
     * Get all The Buses.
     *
     * @return list of Buses
     */
    public List<Bus> getAllBuses() {
        List<Bus> buses = new ArrayList<>();
        String query = "SELECT * FROM Bus";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Bus bus = new Bus();
                bus.setId(rs.getInt(1));
                bus.setLicensePlate(rs.getString(2));
                bus.setCapacity(rs.getInt(3));
                bus.setCurrentRouteId(rs.getInt(4));
                bus.setCurrentAreaID(rs.getInt(5));
                buses.add(bus);
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load bus data: " + e.getMessage());
        }
        return buses;
    }

    /**
     * Update Bus current Location.
     *
     * @param scanner object for user input
     * @return true if Bus is Updated
     */
    public boolean updateBusLocation(Scanner scanner) {
        System.out.println("\n========== UPDATE BUS AREA ==========\n");

        String query = "UPDATE Bus SET CurrentAreaID = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int pinCode = getValidInt(scanner, "Enter new area PinCode: ");
            stmt.setInt(1, pinCode);

            int id = getValidInt(scanner, "Enter bus id to update: ");
            stmt.setInt(2, id);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to load bus data: " + e.getMessage());
        }
        return false;
    }

    /**
     * Update Bus Current Route.
     *
     * @param scanner object for user input
     * @return true if Bus is Updated
     */
    public boolean updateBusRoute(Scanner scanner) {
        System.out.println("\n========== UPDATE BUS ROUTE ==========\n");

        String query = "UPDATE Bus SET CurrentRouteId = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int rid = getValidInt(scanner, "Enter new route id: ");
            stmt.setInt(1, rid);

            int id = getValidInt(scanner, "Enter bus id to update: ");
            stmt.setInt(2, id);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to load bus data: " + e.getMessage());
        }
        return false;
    }
}
