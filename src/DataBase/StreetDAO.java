package DataBase;

import Model.Street;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Validation.AreaInputValidation.getValidBoolean;
import static Validation.AreaInputValidation.getValidInt;

public class StreetDAO {
    private Connection connection;

    public StreetDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a New Street.
     *
     * @return true if Street is added
     */
    public boolean addStreet(Scanner scanner) {
        int che;
        System.out.println("\n========== ADD STREET ==========\n");

        String query = "INSERT INTO Street (Id, StartAreaId, EndAreaId, Distance, IsOneWay) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            che = getValidInt(scanner, "Enter Id: ");
            stmt.setInt(1, che);

            che = getValidInt(scanner, "Enter Start area Id: ");
            stmt.setInt(2, che);

            che = getValidInt(scanner, "Enter End Area Id: ");
            stmt.setInt(3, che);

            che = getValidInt(scanner, "Enter Distance: ");
            stmt.setInt(4, che);

            boolean chek = getValidBoolean(scanner, "Enter 'true' if it is Oneway: ");
            stmt.setBoolean(5, chek);

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
    public Street getStreetById(Scanner scanner) {
        System.out.println("\n========== STREET BY ID ==========\n");

        String query = "SELECT * FROM Street WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int che = getValidInt(scanner, "Enter Id: ");
            stmt.setInt(1, che);

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
    public Street getStreetByAreaId(Scanner scanner) {
        System.out.println("\n========== STREET BY AREA ==========\n");

        String query = "SELECT * FROM Street WHERE areaId = ? LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int che = getValidInt(scanner, "Enter Area Id: ");
            stmt.setInt(1, che);

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
    public boolean updateStreet(Scanner scanner) {
        int che;
        System.out.println("\n========== UPDATE STREET ==========\n");

        String query = "UPDATE Street SET StartAreaId = ?, EndAreaId = ?, IsOneWay = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            che = getValidInt(scanner, "Enter new Start Area Id: ");
            stmt.setInt(1, che);

            che = getValidInt(scanner, "Enter new End Area Id: ");
            stmt.setInt(2, che);

            boolean chek = getValidBoolean(scanner, "Enter 'true' if it is Oneway: ");
            stmt.setBoolean(3, chek);

            che = getValidInt(scanner, "Enter Street Id to Update: ");
            stmt.setInt(4, che);

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
    public boolean deleteStreet(Scanner scanner) {
        System.out.println("\n========== DELETE STREET ==========\n");

        String query = "DELETE FROM Street WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int che = getValidInt(scanner, "Enter Street Id to Delete: ");
            stmt.setInt(1, che);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
