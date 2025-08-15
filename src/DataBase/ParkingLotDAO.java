package DataBase;

import Model.ParkingLot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingLotDAO {
    private Connection connection;

    public ParkingLotDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a new Parking Lot.
     *
     * @param scanner object for user input
     * @return true if lot is added
     */
    public boolean addParkingLot(Scanner scanner) {
        System.out.println("---------- ADD PARKING LOT ----------");
        System.out.println();
        String query = "INSERT INTO ParkingLot (Name, AreaId, Capacity, CurrentOccupancy) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            System.out.print("Enter Name: ");
            scanner.nextLine();
            stmt.setString(1, scanner.nextLine().trim());

            System.out.print("Enter Area PinCode: ");
            stmt.setInt(2, scanner.nextInt());

            System.out.print("Enter Capacity: ");
            stmt.setInt(3, scanner.nextInt());

            System.out.print("Enter Current Occupancy: ");
            stmt.setInt(4, scanner.nextInt());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get Parking Lot by id.
     *
     * @param scanner object for user input
     * @return Object of Parking lot
     */
    public ParkingLot getParkingLotByAreaId(Scanner scanner) {
        System.out.println("---------- PARKING LOT BY ID ----------");
        System.out.println();
        String query = "SELECT * FROM ParkingLot WHERE AreaId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Area Id: ");
            stmt.setInt(1, scanner.nextInt());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ParkingLot lot = new ParkingLot();
                lot.setId(rs.getInt(1));
                lot.setName(rs.getString(2));
                lot.setCapacity(rs.getInt(3));
                lot.setCurrentOccupancy(rs.getInt(4));
                lot.setAreaId(rs.getInt(5));
                return lot;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all the Parking Lots.
     *
     * @return list of Parking lots
     */
    public List<ParkingLot> getAllParkingLots() {
        List<ParkingLot> lots = new ArrayList<>();
        String query = "SELECT * FROM ParkingLot";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ParkingLot lot = new ParkingLot();
                lot.setId(rs.getInt(1));
                lot.setName(rs.getString(2));
                lot.setAreaId(rs.getInt(3));
                lot.setCapacity(rs.getInt(4));
                lot.setCurrentOccupancy(rs.getInt(5));
                lots.add(lot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lots;
    }

    /**
     * Update Parking occupancy by id.
     *
     * @param scanner object for user input
     * @return true if Parking Lot is Updated
     */
    public boolean updateParkingCapacity(Scanner scanner) {
        System.out.println("---------- UPDATE PARKING LOT ----------");
        System.out.println();
        String query = "UPDATE ParkingLot SET Capacity = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter new Capacity: ");
            stmt.setInt(1, scanner.nextInt());

            System.out.print("Enter Lot Id to Update: ");
            stmt.setInt(2, scanner.nextInt());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get all the Parking Lots which have slots to Park.
     *
     * @return list of Parking Lots
     */
    public List<ParkingLot> getAvailableParkingLots() {
        List<ParkingLot> availableLots = new ArrayList<>();
        String query = "SELECT * FROM ParkingLot WHERE CurrentOccupancy < Capacity";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ParkingLot lot = new ParkingLot();
                lot.setId(rs.getInt(1));
                lot.setName(rs.getString(2));
                lot.setCapacity(rs.getInt(3));
                lot.setCurrentOccupancy(rs.getInt(4));
                lot.setAreaId(rs.getInt(5));
                availableLots.add(lot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableLots;
    }

    /**
     * To Update Parking Occupancy.
     *
     * @param lotId        for parking lot to book parking
     * @param newOccupancy change occupancy of parking lot
     */
    public void updateOccupancyById(int lotId, int newOccupancy) {
        String query = "UPDATE ParkingLot SET CurrentOccupancy = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, newOccupancy);
            stmt.setInt(2, lotId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
