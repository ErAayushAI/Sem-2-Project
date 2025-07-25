package DataBase;

import Model.Bus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BusDAO {
    private Connection connection = DataBaseManager.connection;
    public Scanner scanner = new Scanner(System.in);

    public BusDAO() {
        try {
            DataBaseManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a new Bus.
     *
     * @return true if Bus is added
     */
    public boolean addBus() {
        System.out.println("---------- ADD BUS ----------");
        System.out.println();
        String query = "INSERT INTO Bus (LicensePlate, Capacity, CurrentRouteID, CurrentAreaID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Bus License Plate: ");
            stmt.setString(1, scanner.next().trim());

            System.out.print("Enter Capacity: ");
            stmt.setInt(2, scanner.nextInt());

            System.out.print("Enter Current Route Id: ");
            stmt.setInt(3, scanner.nextInt());

            System.out.print("Enter Current Area PinCode: ");
            stmt.setInt(4, scanner.nextInt());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get Bus by id.
     *
     * @return Object of Bus
     */
    public Bus getBusByID() {
        System.out.println("---------- BUS BY ID ----------");
        System.out.println();
        String query = "SELECT * FROM Bus WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Bus Id: ");
            stmt.setInt(1, scanner.nextInt());

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
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all The Buses.
     *
     * @return list of Buses
     */
    public List<Bus> getAllBuses() {
        System.out.println("---------- ALL BUS ----------");
        System.out.println();
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
            e.printStackTrace();
        }
        return buses;
    }

    /**
     * Update Bus current Location.
     *
     * @return true if Bus is Updated
     */
    public boolean updateBusLocation() {
        System.out.println("---------- UPDATE BUS AREA ----------");
        System.out.println();
        String query = "UPDATE Bus SET CurrentAreaID = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Area PinCode: ");
            stmt.setInt(1, scanner.nextInt());

            System.out.print("Enter Bus Id to Update: ");
            stmt.setInt(2, scanner.nextInt());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Update Bus Current Route.
     *
     * @return true if Bus is Updated
     */
    public boolean updateBusRoute() {
        System.out.println("---------- UPDATE BUS ROUTE ----------");
        System.out.println();
        String query = "UPDATE Bus SET CurrentRouteId = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Route Id: ");
            stmt.setInt(1, scanner.nextInt());

            System.out.print("Enter Bus Id to Update: ");
            stmt.setInt(2, scanner.nextInt());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
