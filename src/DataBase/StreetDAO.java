package DataBase;

import Model.Street;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StreetDAO {
    private Connection connection = DataBaseManager.connection;
    public Scanner scanner = new Scanner(System.in);

    public StreetDAO() {
        try {
            DataBaseManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a New Street.
     *
     * @return true if Street is added
     */
    public boolean addStreet() {
        System.out.println("---------- ADD STREET ----------");
        System.out.println();
        String query = "INSERT INTO Street (Id, StartAreaId, EndAreaId, Distance, IsOneWay) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Id: ");
            stmt.setInt(1, scanner.nextInt());

            System.out.print("Enter Start Area Id: ");
            stmt.setDouble(2, scanner.nextInt());

            System.out.print("Enter End Area Id: ");
            stmt.setDouble(3, scanner.nextInt());

            System.out.print("Enter Distance: ");
            stmt.setDouble(4, scanner.nextDouble());

            System.out.print("Enter 'true' if it is Oneway:");
            stmt.setBoolean(5, scanner.nextBoolean());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get Street by id.
     *
     * @return Object of Street
     */
    public Street getStreetById() {
        System.out.println("---------- STREET BY ID ----------");
        System.out.println();
        String query = "SELECT * FROM Street WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Id: ");
            stmt.setInt(1, scanner.nextInt());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Street street = new Street();
                street.setId(rs.getInt(1));
                street.setStartAreaId(rs.getInt(2));
                street.setEndAreaId(rs.getInt(3));
                street.setDistance(rs.getDouble(4));
                street.setOneWay(rs.getBoolean(5));
                return street;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get Street starts from the Area.
     *
     * @return Object of street
     */
    public Street getStreetByAreaId() {
        System.out.println("---------- STREET BY AREA ----------");
        System.out.println();
        String query = "SELECT * FROM Street WHERE areaId = ? LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Area Id: ");
            stmt.setInt(1, scanner.nextInt());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Street street = new Street();
                street.setId(rs.getInt(1));
                street.setStartAreaId(rs.getInt(2));
                street.setEndAreaId(rs.getInt(3));
                street.setDistance(rs.getDouble(4));
                street.setOneWay(rs.getBoolean(5));
                return street;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get All the Streets.
     *
     * @return list of Streets
     */
    public List<Street> getAllStreet() {
        System.out.println("---------- ALL STREET ----------");
        System.out.println();
        List<Street> streets = new ArrayList<>();
        String query = "SELECT * FROM Street";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Street street = new Street();
                street.setId(rs.getInt(1));
                street.setStartAreaId(rs.getInt(2));
                street.setEndAreaId(rs.getInt(3));
                street.setDistance(rs.getDouble(4));
                street.setOneWay(rs.getBoolean(5));
                streets.add(street);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return streets;
    }

    /**
     * Update Street start area, end area ids and one way.
     *
     * @return true if Street is Updated
     */
    public boolean updateStreet() {
        System.out.println("---------- UPDATE STREET ----------");
        System.out.println();
        String query = "UPDATE Street SET StartAreaId = ?, EndAreaId = ?, IsOneWay = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter new Start Area Id: ");
            stmt.setInt(1, scanner.nextInt());

            System.out.print("Enter new End Area Id: ");
            stmt.setInt(2, scanner.nextInt());

            System.out.print("Enter 'true' if it is Oneway: ");
            stmt.setBoolean(3, scanner.nextBoolean());

            System.out.print("Enter Street Id to Update: ");
            stmt.setInt(4, scanner.nextInt());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete Street By id.
     *
     * @return true if Street is deleted
     */
    public boolean deleteStreet() {
        System.out.println("---------- DELETE STREET ----------");
        System.out.println();
        String query = "DELETE FROM Street WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Street Id to Delete: ");
            stmt.setInt(1, scanner.nextInt());

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
