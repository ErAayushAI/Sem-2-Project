package DataBase;

import Model.EmergencyService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmergencyServiceDAO {
    private Connection connection = DataBaseManager.connection;
    public Scanner scanner = new Scanner(System.in);

    public EmergencyServiceDAO() {
        try {
            DataBaseManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a new Emergency Service.
     *
     * @return true if Service is added
     */
    public boolean addEmergencyService() {
        System.out.println("---------- ADD EMERGENCY SERVICE ----------");
        System.out.println();
        String query = "INSERT INTO EmergencyService (Name, Type, AreaId, ContactNumber) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Name: ");
            stmt.setString(1, scanner.nextLine().trim());

            System.out.println("Like Hospital, Police Station, Fire Station");
            System.out.print("Enter Type: ");
            stmt.setString(2, scanner.nextLine().trim().toUpperCase());

            System.out.print("Enter Area PinCode: ");
            stmt.setInt(3, scanner.nextInt());

            long number;
            do {
                System.out.print("Enter Contact Number: ");
                number = scanner.nextLong();
                stmt.setLong(4, number);
            } while (isValidContactNumber(number));

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get Emergency service by id.
     *
     * @return Object of EmergencyService
     */
    public EmergencyService getEmergencyServiceByID() {
        System.out.println("---------- EMERGENCY SERVICE BY ID ----------");
        System.out.println();
        String query = "SELECT * FROM EmergencyService WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Id: ");
            stmt.setInt(1, scanner.nextInt());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                EmergencyService service = new EmergencyService();
                service.setId(rs.getInt(1));
                service.setName(rs.getString(2));
                service.setType(rs.getString(3));
                service.setAreaId(rs.getInt(4));
                service.setContactNumber(rs.getLong(5));
                return service;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get Emergency Service By Type (For Example = Medical, Fire, Police)
     *
     * @param type Service type (For Example = Hospital, Fire, Police)
     * @return Object of EmergencyService
     */
    public EmergencyService getEmergencyServiceByType(String type) {
        System.out.println("---------- EMERGENCY SERVICE BY TYPE ----------");
        System.out.println();
        String query = "SELECT * FROM EmergencyService WHERE Type = ? LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.println("Like Hospital, Police Station, Fire Station");
            System.out.print("Enter Type: ");
            stmt.setString(1, scanner.nextLine().trim().toUpperCase());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                EmergencyService service = new EmergencyService();
                service.setId(rs.getInt(1));
                service.setName(rs.getString(2));
                service.setType(rs.getString(3));
                service.setAreaId(rs.getInt(4));
                service.setContactNumber(rs.getLong(5));
                return service;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all the Emergency Services.
     *
     * @return list of Emergency Services
     */
    public List<EmergencyService> getAllEmergencyService() {
        System.out.println("---------- ALL EMERGENCY SERVICE ----------");
        System.out.println();
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
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    /**
     * Validate Contact Number of Service.
     *
     * @param number contact number
     * @return true if it's valid
     */
    public static boolean isValidContactNumber(long number) {
        // Convert to String to check length and digits
        String numStr = Long.toString(number);

        // Check if the number has exactly 10 digits
        if (numStr.length() != 10) {
            return true;
        }

        if (numStr.equals("0000000000")) {
            return true;
        }

        return false;
    }

}
