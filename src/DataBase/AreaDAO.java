package DataBase;

import Model.Area;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AreaDAO {
    private Connection connection = DataBase.DataBaseManager.connection;
    public Scanner scanner = new Scanner(System.in);

    public AreaDAO() {
        try {
            DataBase.DataBaseManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a new Area.
     *
     * @return true if area is added
     */
    public boolean addArea() {
        System.out.println("---------- ADD AREA ----------");
        System.out.println();
        String query = "INSERT INTO Area (Id, Name, Latitude, Longitude, IsEmergencyPoint) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter PinCode: ");
            stmt.setInt(1, scanner.nextInt());

            System.out.print("Enter Name: ");
            scanner.nextLine();
            stmt.setString(2, scanner.nextLine().trim());

            System.out.print("Enter Latitude: ");
            stmt.setDouble(3, scanner.nextDouble());

            System.out.print("Enter Longitude: ");
            stmt.setDouble(4, scanner.nextDouble());

            System.out.print("Enter 'true' if Area has Emergency Point: ");
            stmt.setBoolean(5, scanner.nextBoolean());

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
     * @return Object of Area
     */
    public Area getAreaById() {
        System.out.println("---------- AREA BY ID ----------");
        System.out.println();
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
        System.out.println("---------- ALL AREA ----------");
        System.out.println();
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
     * @return true if Area is updated
     */
    public boolean updateArea() {
        System.out.println("---------- UPDATE AREA ----------");
        System.out.println();
        String query = "UPDATE Area SET name = ?, isEmergencyPoint = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter new Name: ");
            stmt.setString(1, scanner.nextLine().trim());

            System.out.print("Enter 'true' if Area has Emergency Point: ");
            stmt.setBoolean(2, scanner.nextBoolean());

            System.out.print("Enter Area PinCode to Update: ");
            stmt.setInt(3, scanner.nextInt());

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
     * @return true if Area is Deleted
     */
    public boolean deleteArea() {
        System.out.println("---------- DELETE AREA ----------");
        System.out.println();
        String query = "DELETE FROM Area WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Area PinCode to Delete: ");
            stmt.setInt(1, scanner.nextInt());

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
     * @return list of Area inside the area
     */
    public List<Area> getAreaInArea() {
        System.out.println("---------- UPDATE AREA BY ID ----------");
        System.out.println();
        List<Area> areas = new ArrayList<>();
        System.out.print("Enter Minimum Latitude: ");
        double minLat = scanner.nextDouble();

        System.out.print("Enter Maximum Latitude: ");
        double maxLat = scanner.nextDouble();

        System.out.print("Enter Minimum Longitude: ");
        double minLon = scanner.nextDouble();

        System.out.print("Enter Maximum Longitude: ");
        double maxLon = scanner.nextDouble();

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
