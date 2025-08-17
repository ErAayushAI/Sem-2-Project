package DataBase;

import Model.EmergencyService;
import Validation.InputValidator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Validation.InputValidator.getValidInt;

public class EmergencyServiceDAO {
    private Connection connection;

    public EmergencyServiceDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            System.out.println("❌ No database connection provided to Emergency Service.");
        }
    }

    /**
     * Add a new Emergency Service.
     *
     * @param scanner object for user input
     * @return true if Service is added
     */
    public boolean addEmergencyService(Scanner scanner) {
        System.out.println("\n========== ADD EMERGENCY SERVICE ==========\n");

        String query = "INSERT INTO EmergencyService (Name, Type, AreaId, ContactNumber, AvailableVehicles) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            scanner.nextLine();
            System.out.print("Enter Name: ");
            stmt.setString(1, scanner.nextLine().trim());

            System.out.print("Enter Type: ");
            System.out.println("Like Hospital, Police Station, Fire Station");
            stmt.setString(2, scanner.nextLine().trim().toUpperCase());

            int pinCode = getValidInt(scanner, "Enter Area PinCode: ");
            stmt.setInt(3, pinCode);

            long number;
            do {
                System.out.print("Enter Contact Number: ");
                number = scanner.nextLong();
                stmt.setLong(4, number);
            } while (InputValidator.isValidContactNumber(number));

            int count = getValidInt(scanner, "Enter Vehicle Count: ");
            stmt.setInt(5, count);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to load emergency service data: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get Emergency service by id.
     *
     * @return Object of EmergencyService
     */
    public EmergencyService getEmergencyServiceByID(Scanner scanner) {
        System.out.println("\n========== EMERGENCY SERVICE BY ID ==========\n");

        String query = "SELECT * FROM EmergencyService WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int id = getValidInt(scanner, "Enter Id: ");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                EmergencyService service = new EmergencyService();
                service.setId(rs.getInt(1));
                service.setName(rs.getString(2));
                service.setType(rs.getString(3));
                service.setAreaId(rs.getInt(4));
                service.setContactNumber(rs.getLong(5));
                service.setAvailableVehicles(rs.getInt(6));
                return service;
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load emergency service data: " + e.getMessage());
        }
        return null;
    }

    /**
     * Get Emergency Service By Type (For Example = Medical, Fire, Police)
     *
     * @param scanner object for user input
     * @return Object of EmergencyService
     */
    public List<EmergencyService> getEmergencyServiceByType(Scanner scanner) {
        System.out.println("\n========== EMERGENCY SERVICE BY TYPE ==========\n");

        List<EmergencyService> services = new ArrayList<>();
        String query = "SELECT * FROM EmergencyService WHERE Type = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            scanner.nextLine();
            System.out.print("Enter Type: ");
            System.out.println("Like Hospital, Police Station, Fire Station");
            stmt.setString(1, scanner.nextLine().trim().toUpperCase());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                EmergencyService service = new EmergencyService();
                service.setId(rs.getInt(1));
                service.setName(rs.getString(2));
                service.setType(rs.getString(3));
                service.setAreaId(rs.getInt(4));
                service.setContactNumber(rs.getLong(5));
                service.setAvailableVehicles(rs.getInt(6));
                services.add(service);
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load emergency service data: " + e.getMessage());
        }
        return services;
    }

    /**
     * Get all the Emergency Services.
     *
     * @return list of Emergency Services
     */
    public List<EmergencyService> getAllEmergencyService() {
        List<EmergencyService> services = new ArrayList<>();
        String query = "SELECT * FROM EmergencyService";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                EmergencyService service = new EmergencyService();
                service.setId(rs.getInt(1));
                service.setName(rs.getString(2));
                service.setType(rs.getString(3));
                service.setAreaId(rs.getInt(4));
                service.setContactNumber(rs.getLong(5));
                service.setAvailableVehicles(rs.getInt(6));
                services.add(service);
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load emergency service data: " + e.getMessage());
        }
        return services;
    }

    /**
     * To update Service Vehicle and contact number.
     *
     * @param scanner Object for User inputs
     * @return true if services is Updated
     */
    public boolean updateEmergencyService(Scanner scanner) {
        System.out.println("\n========== UPDATE EMERGENCY SERVICE ==========\n");

        String query = "UPDATE EmergencyService SET ContactNumber = ?, AvailableVehicles = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            long number;
            do {
                System.out.print("Enter Contact Number: ");
                number = scanner.nextLong();
                stmt.setLong(1, number);
            } while (InputValidator.isValidContactNumber(number));

            int count = getValidInt(scanner, "Enter Vehicle Count: ");
            stmt.setInt(2, count);

            int id = getValidInt(scanner, "Enter Service Id: ");
            stmt.setInt(3, id);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to load emergency service data: " + e.getMessage());
            return false;
        }
    }

    /**
     * To Update Emergency Service Vehicles Count.
     *
     * @param serviceId for service id
     * @param newCount  of Service Vehicles
     */
    public void updateVehicleCount(int serviceId, int newCount) {
        String query = "UPDATE EmergencyService SET AvailableVehicles = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, newCount);
            stmt.setInt(2, serviceId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Failed to load emergency service data: " + e.getMessage());
        }
    }

}
