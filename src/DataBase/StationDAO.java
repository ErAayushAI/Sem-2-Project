package DataBase;

import Model.Station;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StationDAO {
    private Connection connection = DataBaseManager.connection;
    public Scanner scanner = new Scanner(System.in);

    public StationDAO() {
        try {
            DataBaseManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a new Station.
     *
     * @return true if Station is added
     */
    public boolean addStation() {
        System.out.println("---------- ADD STATION ----------");
        System.out.println();
        String query = "INSERT INTO Station (Name, AreaId, IsBusStation, IsMetroStation) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.println("Enter Name: ");
            stmt.setString(1, scanner.nextLine().trim());

            System.out.print("Enter Area Id: ");
            stmt.setInt(2, scanner.nextInt());

            System.out.print("Enter 'true' if it is Bus Station: ");
            stmt.setBoolean(3, scanner.nextBoolean());

            System.out.print("Enter 'true' if it is Metro Station: ");
            stmt.setBoolean(4, scanner.nextBoolean());

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
     * @return Object of Station
     */
    public Station getStationById() {
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
        System.out.println("---------- ALL STATION ----------");
        System.out.println();
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
     * @return List of Station
     */
    public List<Station> getStopsByAreaId() {
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
}
