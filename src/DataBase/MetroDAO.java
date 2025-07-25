package DataBase;

import Model.Metro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MetroDAO {
    private Connection connection = DataBaseManager.connection;
    public Scanner scanner = new Scanner(System.in);

    public MetroDAO() {
        try {
            DataBaseManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a new Metro.
     *
     * @return true if Metro is added
     */
    public boolean addMetro() {
        System.out.println("---------- ADD METRO ----------");
        System.out.println();
        String query = "INSERT INTO Metro (TrainName, Capacity, CurrentRouteID, CurrentAreaID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Train Name: ");
            stmt.setString(1, scanner.nextLine().trim());

            System.out.print("Enter Capacity: ");
            stmt.setInt(2, scanner.nextInt());

            System.out.print("Enter Current Route: ");
            stmt.setInt(3, scanner.nextInt());

            System.out.print("Enter Current Area: ");
            stmt.setInt(4, scanner.nextInt());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get Metro by id.
     *
     * @return Object of Metro
     */
    public Metro getMetroByID() {
        System.out.println("---------- METRO BY ID ----------");
        System.out.println();
        String query = "SELECT * FROM Metro WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Id: ");
            stmt.setInt(1, scanner.nextInt());

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
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all Metros.
     *
     * @return list of Metros
     */
    public List<Metro> getAllMetros() {
        System.out.println("---------- ALL METRO ----------");
        System.out.println();
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
            e.printStackTrace();
        }
        return metros;
    }

    /**
     * Update Metro current Location.
     *
     * @return true if Metro is Updated
     */
    public boolean updateMetroLocation() {
        System.out.println("---------- UPDATE METRO AREA ----------");
        System.out.println();
        String query = "UPDATE Metro SET CurrentAreaID = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Area PinCode: ");
            stmt.setInt(1, scanner.nextInt());

            System.out.print("Enter Metro Id to Update: ");
            stmt.setInt(2, scanner.nextInt());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Update Metro current Route.
     *
     * @return true if Metro is Updated
     */
    public boolean updateMetroRoute() {
        System.out.println("---------- UPDATE METRO ROUTE ----------");
        System.out.println();
        String query = "UPDATE Bus SET CurrentRouteId = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Route Id: ");
            stmt.setInt(1, scanner.nextInt());

            System.out.print("Enter Metro Id to Update: ");
            stmt.setInt(2, scanner.nextInt());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
