package DataBase;

import Model.Station;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Validation.InputValidator.getValidBoolean;
import static Validation.InputValidator.getValidInt;

public class StationDAO {
    private Connection connection;

    public StationDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            System.out.println("❌ No database connection provided to Station.");
        }
    }

    /**
     * Add a new Station.
     *
     * @param scanner object for user input
     * @return true if Station is added
     */
    public boolean addStation(Scanner scanner) {
        System.out.println("\n========== ADD STATION ==========\n");

        String query = "INSERT INTO Station (Name, AreaId, IsBusStation, IsMetroStation) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Name: ");
            scanner.nextLine();
            stmt.setString(1, scanner.nextLine().trim());

            int aid = getValidInt(scanner, "Enter Area Id: ");
            stmt.setInt(2, aid);

            boolean metroTransport = false;
            boolean BusTransport = getValidBoolean(scanner, "Enter 'true' if it is Bus Station: ");
            stmt.setBoolean(3, BusTransport);
            if(BusTransport){
                stmt.setBoolean(4, false);
            } else {
                metroTransport = getValidBoolean(scanner, "Enter 'true' if it is Metro Station: ");
                stmt.setBoolean(4, metroTransport);
            }

            if(!BusTransport && !metroTransport){
                System.out.println("You should choose any of the above transport add station.");
                return false;
            }

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to load station data: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get Station by id.
     *
     * @param scanner object for user input
     * @return Object of Station
     */
    public Station getStationById(Scanner scanner) {
        System.out.println("\n========== STATION BY ID ==========\n");

        String query = "SELECT * FROM Station WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int sid = getValidInt(scanner, "Enter Station Id: ");
            stmt.setInt(1, sid);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Station station = new Station();
                station.setId(rs.getInt(1));
                station.setName(rs.getString(2));
                station.setAreaId(rs.getInt(3));
                station.setBusStation(rs.getBoolean(4));
                station.setMetroStation(rs.getBoolean(5));
                return station;
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load station data: " + e.getMessage());
        }
        return null;
    }

    /**
     * Get All the Station.
     *
     * @return list of the all Stations
     */
    public List<Station> getAllStops() {
        List<Station> stations = new ArrayList<>();
        String query = "SELECT * FROM Station";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Station station = new Station();
                station.setId(rs.getInt(1));
                station.setName(rs.getString(2));
                station.setAreaId(rs.getInt(3));
                station.setBusStation(rs.getBoolean(4));
                station.setMetroStation(rs.getBoolean(5));
                stations.add(station);
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load station data: " + e.getMessage());
        }
        return stations;
    }

    /**
     * Get Station in Location.
     *
     * @param scanner object for user input
     * @return List of Station
     */
    public List<Station> getStopsByAreaId(Scanner scanner) {
        System.out.println("\n========== STATION BY AREA ==========\n");

        List<Station> stations = new ArrayList<>();
        String query = "SELECT * FROM Station WHERE AreaId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

           int aid = getValidInt(scanner, "Enter Area Id: ");
            stmt.setInt(1, aid);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Station station = new Station();
                station.setId(rs.getInt(1));
                station.setName(rs.getString(2));
                station.setAreaId(rs.getInt(3));
                station.setBusStation(rs.getBoolean(4));
                station.setMetroStation(rs.getBoolean(5));
                stations.add(station);
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load station data: " + e.getMessage());
        }
        return stations;
    }

    public boolean updateStation (Scanner scanner) {
        System.out.println("\n========== UPDATE ROUTE ==========\n");

        String query = "UPDATE Station SET Name = ?, isBusStation = ?, isMetroStation = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter new Name: ");
            scanner.nextLine();
            stmt.setString(1, scanner.nextLine().trim());

            boolean metroTransport = false;
            boolean BusTransport = getValidBoolean(scanner, "Enter 'true' if it is Bus Station: ");
            stmt.setBoolean(2, BusTransport);
            if(BusTransport){
                stmt.setBoolean(3, false);
            } else {
                metroTransport = getValidBoolean(scanner, "Enter 'true' if it is Metro Station: ");
                stmt.setBoolean(3, metroTransport);
            }

            if(!BusTransport && !metroTransport){
                System.out.println("You should choose any of the above transport to update Station.");
                return false;
            }

            int sid = getValidInt(scanner, "Enter Station Id to Update: ");
            stmt.setInt(4, sid);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to load station data: " + e.getMessage());
            return false;
        }
    }
}
