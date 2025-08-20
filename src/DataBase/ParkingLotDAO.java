package DataBase;

import Model.ParkingLot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Validation.InputValidator.getValidInt;

public class ParkingLotDAO {
    private Connection connection;

    public ParkingLotDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            System.out.println("❌ No database connection provided to Parking Lot.");
        }
    }

    /**
     * Add a new Parking Lot.
     *
     * @param scanner object for user input
     * @return true if lot is added
     */
    public boolean addParkingLot(Scanner scanner) {
        System.out.println("\n========== ADD PARKING LOT ==========\n");

        String query = "INSERT INTO ParkingLot (Name, AreaId, Capacity, CurrentOccupancy) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            System.out.print("Enter Name: ");
            scanner.nextLine();
            stmt.setString(1, scanner.nextLine().trim());

            int aid = getValidInt(scanner, "Enter Area PinCode: ");
            stmt.setInt(2, aid);

            int capacity = getValidInt(scanner, "Enter Capacity: ");
            stmt.setInt(3, capacity);

            int occupancy = getValidInt(scanner, "Enter Current Occupancy: ");
            stmt.setInt(4, occupancy);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to load parking lot data: " + e.getMessage());
            return false;
        }
    }

    public List<ParkingLot> getParkingLotByAreaIdList(Scanner scanner) {
        System.out.println("\n========== PARKING LOT BY ID ==========\n");

        String query = "SELECT * FROM ParkingLot WHERE AreaId = ?";
        List<ParkingLot> lots  = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int aid = getValidInt(scanner, "Enter Area Id: ");
            stmt.setInt(1, aid);

            ResultSet rs = stmt.executeQuery();
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
            System.out.println("❌ Failed to load parking lot data: " + e.getMessage());
        }
        return lots;
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
            System.out.println("❌ Failed to load parking lot data: " + e.getMessage());
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
        System.out.println("\n========== UPDATE PARKING LOT ==========\n");

        String query = "UPDATE ParkingLot SET Capacity = ? WHERE Id = ?";
        String sql = "SELECT CurrentOccupancy FROM ParkingLot WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            PreparedStatement stmt1 = connection.prepareStatement(sql);
            int occupancy = 0;

            int pid = getValidInt(scanner, "Enter Lot Id to Update: ");
            stmt1.setInt(1, pid);
            stmt.setInt(2, pid);

            ResultSet rs = stmt1.executeQuery();
            while(rs.next()){
                occupancy = rs.getInt(1);
            }

            int capacity = getValidInt(scanner, "Enter new Capacity: ");
            if(capacity > occupancy) {
                stmt.setInt(1, capacity);
            } else {
                return false;
            }

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to load parking lot data: " + e.getMessage());
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
            System.out.println("❌ Failed to load parking lot data: " + e.getMessage());
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
        System.out.println("\n========== DELETE ROUTE ==========\n");

        String query = "DELETE FROM ParkingLot WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int pid = getValidInt(scanner, "Enter Lot Id to delete: ");
            stmt.setInt(1, pid);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to load parking lot data: " + e.getMessage());
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
            System.out.println("❌ Failed to load parking lot data: " + e.getMessage());
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
            System.out.println("❌ Failed to load parking lot data: " + e.getMessage());
        }
        return 0;
    }
}
