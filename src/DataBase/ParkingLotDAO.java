package DataBase;

import Model.ParkingLot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Validation.AreaInputValidation.getValidInt;

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
        int che;
        System.out.println("---------- ADD PARKING LOT ----------");
        System.out.println();
        String query = "INSERT INTO ParkingLot (Name, AreaId, Capacity, CurrentOccupancy) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            System.out.print("Enter Name: ");
            scanner.nextLine();
            stmt.setString(1, scanner.nextLine().trim());

            che = getValidInt(scanner, "Enter Area PinCode: ");
            stmt.setInt(2, che);

            che = getValidInt(scanner, "Enter Capacity: ");
            stmt.setInt(3, che);

            che = getValidInt(scanner, "Enter Current Occupancy: ");
            stmt.setInt(4, che);

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
            int che = getValidInt(scanner, "Enter Area Id: ");
            stmt.setInt(1, che);

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
        int che;
        System.out.println("---------- UPDATE PARKING LOT ----------");
        System.out.println();
        String query = "UPDATE ParkingLot SET Capacity = ? WHERE Id = ?";
        String sql = "SELECT CurrentOccupancy FROM ParkingLot WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            PreparedStatement stmt1 = connection.prepareStatement(sql);
            int occupancy = 0;

            che = getValidInt(scanner, "Enter Lot Id to Update: ");
            stmt1.setInt(1, che);
            stmt.setInt(2, che);

            ResultSet rs = stmt1.executeQuery();
            while(rs.next()){
                occupancy = rs.getInt(1);
            }

            che = getValidInt(scanner, "Enter new Capacity: ");
            if(che > occupancy) {
                stmt.setInt(1, che);
            } else {
                return false;
            }

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
                lot.setAreaId(rs.getInt(3));
                lot.setCapacity(rs.getInt(4));
                lot.setCurrentOccupancy(rs.getInt(5));
                availableLots.add(lot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableLots;
    }

    /**
     * To delete Parking Lot.
     *
     * @param scanner Object for user inputs
     * @return true if Parking lot is deleted
     */
    public boolean deleteParkingLot (Scanner scanner) {
        System.out.println("---------- DELETE ROUTE ----------");
        System.out.println();
        String query = "DELETE FROM ParkingLot WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int che = getValidInt(scanner, "Enter Lot Id to delete: ");
            stmt.setInt(1, che);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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

    /**
     * To get Occupancy of Parking lot
     *
     * @param lotId Parking lot id
     * @return current occupancy
     */
    public int getCurrentOccupancyById(int lotId) {
        String query = "SELECT CurrentOccupancy FROM ParkingLot WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, lotId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // fallback
    }
}
