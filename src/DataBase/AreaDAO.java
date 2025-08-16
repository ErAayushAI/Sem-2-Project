package DataBase;

import Model.Area;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Validation.AreaInputValidation.*;

public class AreaDAO {
    private Connection connection;

    public AreaDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add new Area.
     *
     * @param scanner object for user input
     * @return true if area is added
     */
    public boolean addArea(Scanner scanner) {
        System.out.println("\n========== ADD AREA ==========\n");

        String query = "INSERT INTO Area (Id, Name, Latitude, Longitude, IsEmergencyPoint) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int pinCode = getValidInt(scanner, "Enter PinCode: ");
            stmt.setInt(1, pinCode);

            String name = getValidString(scanner, "Enter Name: ");
            stmt.setString(2, name);

            double latitude = getValidDouble(scanner, "Enter Latitude: ");
            stmt.setDouble(3, latitude);

            double longitude = getValidDouble(scanner, "Enter Longitude: ");
            stmt.setDouble(4, longitude);

            boolean hasEmergency = getValidBoolean(scanner, "Enter 'true' if Area has Emergency Point: ");
            stmt.setBoolean(5, hasEmergency);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get Area by id.
     *
     * @param scanner object for user input
     * @return Object of Area
     */
    public Area getAreaById(Scanner scanner) {
        System.out.println("\n========== AREA BY ID ==========\n");

        String query = "SELECT * FROM Area WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Pincode: ");
            stmt.setInt(1, scanner.nextInt());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Area area = new Area();
                area.setId(rs.getInt(1));
                area.setName(rs.getString(2));
                area.setLatitude(rs.getDouble(3));
                area.setLongitude(rs.getDouble(4));
                area.setEmergencyPoint(rs.getBoolean(5));
                return area;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get All the Areas in the city.
     *
     * @return list of all the area
     */
    public List<Area> getAllArea() {
        List<Area> areas = new ArrayList<>();
        String query = "SELECT * FROM Area";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Area area = new Area();
                area.setId(rs.getInt(1));
                area.setName(rs.getString(2));
                area.setLatitude(rs.getDouble(3));
                area.setLongitude(rs.getDouble(4));
                area.setEmergencyPoint(rs.getBoolean(5));
                areas.add(area);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return areas;
    }

    /**
     * Update area name and Emergency point.
     *
     * @param scanner object for user input
     * @return true if Area is updated
     */
    public boolean updateArea(Scanner scanner) {
        System.out.println("\n========== UPDATE AREA ==========\n");

        String query = "UPDATE Area SET name = ?, isEmergencyPoint = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            String name = getValidString(scanner, "Enter new Area Name: ");
            stmt.setString(1, name);

            boolean hasEmergency = getValidBoolean(scanner, "Enter 'true' if Area has Emergency Point: ");
            stmt.setBoolean(2, hasEmergency);


            int pinCode = getValidInt(scanner, "Enter PinCode to update: ");
            stmt.setInt(3, pinCode);


            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * To Delete Area by id.
     *
     * @param scanner object for user input
     * @return true if Area is Deleted
     */
    public boolean deleteArea(Scanner scanner) {
        System.out.println("\n========== DELETE AREA ==========\n");

        String query = "DELETE FROM Area WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int pinCode = getValidInt(scanner, "Enter Area PinCode to delete: ");
            stmt.setInt(1, pinCode);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get list of Areas inside Area given by user in the city.
     *
     * @param scanner object for user input
     * @return list of Area inside the area
     */
    public List<Area> getAreaInArea(Scanner scanner) {
        System.out.println("\n========== UPDATE AREA BY ID ==========\n");

        List<Area> areas = new ArrayList<>();
        System.out.print("Enter Minimum Latitude: ");
        double minLat = getValidDouble(scanner, "Enter minimum Latitude: ");

        System.out.print("Enter Maximum Latitude: ");
        double maxLat = getValidDouble(scanner, "Enter maximum Latitude: ");

        System.out.print("Enter Minimum Longitude: ");
        double minLon = getValidDouble(scanner, "Enter minimum Longitude: ");

        System.out.print("Enter Maximum Longitude: ");
        double maxLon = getValidDouble(scanner, "Enter maximum Longitude: ");

        String query = "SELECT * FROM Area WHERE Latitude between " + minLat + " and " + maxLat + " AND Longitude between " + minLon + " and " + maxLon + " ORDER BY Id";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Area area = new Area();
                area.setId(rs.getInt(1));
                area.setName(rs.getString(2));
                area.setLatitude(rs.getDouble(3));
                area.setLongitude(rs.getDouble(4));
                area.setEmergencyPoint(rs.getBoolean(5));
                areas.add(area);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return areas;
    }
}
