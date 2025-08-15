package DataBase;

import Model.Station;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StationDAO {
    private Connection connection;

    public StationDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a new Station.
     *
     * @param scanner object for user input
     * @return true if Station is added
     */
    public boolean addStation(Scanner scanner) {
        System.out.println("---------- ADD STATION ----------");
        System.out.println();
        String query = "INSERT INTO Station (Name, AreaId, IsBusStation, IsMetroStation) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Name: ");
            scanner.nextLine();
            stmt.setString(1, scanner.nextLine().trim());

            System.out.print("Enter Area Id: ");
            stmt.setInt(2, scanner.nextInt());

            System.out.print("Enter 'true' if it is Bus Station: ");
            boolean BusTransport = scanner.nextBoolean();
            boolean metroTransport = false;
            stmt.setBoolean(3, BusTransport);
            if(BusTransport){
                stmt.setBoolean(4, false);
            } else {
                System.out.print("Enter 'true' if it is Metro Transport: ");
                metroTransport = scanner.nextBoolean();
                stmt.setBoolean(4, metroTransport);
            }

            if(BusTransport == false && metroTransport == false){
                System.out.println("You should choose any of the above transport to book tickets.");
                return false;
            }

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
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
        System.out.println("---------- STATION BY ID ----------");
        System.out.println();
        String query = "SELECT * FROM Station WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Id: ");
            stmt.setInt(1, scanner.nextInt());

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
            e.printStackTrace();
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
            e.printStackTrace();
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
        System.out.println("---------- STATION BY AREA ----------");
        System.out.println();
        List<Station> stations = new ArrayList<>();
        String query = "SELECT * FROM Station WHERE AreaId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Area Id: ");
            stmt.setInt(1, scanner.nextInt());

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
            e.printStackTrace();
        }
        return stations;
    }

    public boolean updateStation (Scanner scanner) {
        System.out.println("---------- UPDATE ROUTE ----------");
        System.out.println();
        String query = "UPDATE Station SET Name = ?, isBusStation = ?, isMetroStation = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter new Name: ");
            scanner.nextLine();
            stmt.setString(1, scanner.nextLine().trim());

            System.out.print("Enter 'true' if it is Bus Station: ");
            boolean BusTransport = scanner.nextBoolean();
            boolean metroTransport = false;
            stmt.setBoolean(2, BusTransport);
            if(BusTransport){
                stmt.setBoolean(3, false);
            } else {
                System.out.print("Enter 'true' if it is Metro Transport: ");
                metroTransport = scanner.nextBoolean();
                stmt.setBoolean(3, metroTransport);
            }

            if(BusTransport == false && metroTransport == false){
                System.out.println("You should choose any of the above transport to book tickets.");
                return false;
            }

            System.out.print("Enter Station Id to Update: ");
            stmt.setInt(4, scanner.nextInt());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
