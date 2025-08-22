package DataBase;

import Model.Schedule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Validation.InputValidator.getValidBoolean;
import static Validation.InputValidator.getValidInt;

public class ScheduleDAO {
    private Connection connection;

    public ScheduleDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            System.out.println("❌ No database connection provided to Schedule.");
        }
    }

    /**
     * Add a new Schedule.
     *
     * @param scanner object for user input
     * @return true if schedule is added
     */
    public boolean addSchedule(Scanner scanner) {
        System.out.println("\n========== ADD SCHEDULE ==========\n");

        String query = "INSERT INTO Schedule (RouteId, DepartureTime, isBusSchedule, isMetroSchedule) VALUES (?, ?, ?, ?)";
        String sql = "SELECT isBusRoute, isMetroRoute FROM Route WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query);
        PreparedStatement stmt1 = connection.prepareStatement(sql)) {

            int rid = getValidInt(scanner, "Enter Route Id: ");
            stmt.setInt(1, rid);
            stmt1.setInt(1,rid);

            System.out.println("Departure Time:");
            int hour = getValidInt(scanner, "Enter Hour: ");
            int minute = getValidInt(scanner, "Enter Minute: ");
            int second = getValidInt(scanner, "Enter Second: ");
            Time t = new Time(hour, minute, second);
            stmt.setTime(2, t);

            ResultSet rs = stmt1.executeQuery();

            if(rs.next()) {
                boolean busSchedule = rs.getBoolean(1);
                stmt.setBoolean(3, busSchedule);
                boolean metroSchedule = rs.getBoolean(2);
                stmt.setBoolean(4, metroSchedule);
            }

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to load schedule data: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get Schedule by route.
     *
     * @param scanner object for user input
     * @return Object of Schedule
     */
    public Schedule getScheduleByRouteId(Scanner scanner) {
        System.out.println("\n========== SCHEDULE BY ROUTE ==========\n");

        String query = "SELECT * FROM Schedule WHERE RouteId = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int rid = getValidInt(scanner, "Enter Route Id: ");
            stmt.setInt(1, rid);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(rs.getInt(1));
                schedule.setRouteID(rs.getInt(2));
                schedule.setDepartureTime(rs.getTime(3));
                schedule.setBusSchedule(rs.getBoolean(4));
                schedule.setMetroSchedule(rs.getBoolean(5));
                return schedule;
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load schedule data: " + e.getMessage());
        }
        return null;
    }

    /**
     * To view all Schedule of services
     *
     * @return list of schedule
     */
    public List<Schedule> getAllSchedule() {
        List<Schedule> schedules = new ArrayList<>();
        String query = "SELECT * FROM Schedule";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(rs.getInt(1));
                schedule.setRouteID(rs.getInt(2));
                schedule.setDepartureTime(rs.getTime(3));
                schedule.setBusSchedule(rs.getBoolean(4));
                schedule.setMetroSchedule(rs.getBoolean(5));
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load schedule data: " + e.getMessage());
        }
        return schedules;
    }

    /**
     * Update an existing Schedule.
     *
     * @param scanner object for user input
     * @return true if update is successful
     */
    public boolean updateSchedule(Scanner scanner) {
        System.out.println("\n========== UPDATE SCHEDULE ==========\n");

        String query = "UPDATE Schedule SET RouteId = ?, DepartureTime = ?, isBusSchedule = ?, isMetroSchedule = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int id = getValidInt(scanner, "Enter Schedule ID to update: ");
            int routeId = getValidInt(scanner, "Enter new Route ID: ");

            System.out.println("Enter new Departure Time:");
            int hour = getValidInt(scanner, "Hour: ");
            int minute = getValidInt(scanner, "Minute: ");
            int second = getValidInt(scanner, "Second: ");
            Time departureTime = new Time(hour, minute, second);

            stmt.setInt(1, routeId);
            stmt.setTime(2, departureTime);
            stmt.setInt(3, id);

            boolean metroTransport = false;
            boolean BusTransport = getValidBoolean(scanner, "Enter 'true' if it is Bus Schedule: ");
            stmt.setBoolean(2, BusTransport);
            if(BusTransport){
                stmt.setBoolean(3, false);
            } else {
                metroTransport = getValidBoolean(scanner, "Enter 'true' if it is Metro Schedule: ");
                stmt.setBoolean(3, metroTransport);
            }

            if(!BusTransport && !metroTransport){
                System.out.println("You should choose any of the above transport update Schedule.");
                return false;
            }
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to update schedule: " + e.getMessage());
            return false;
        }
    }

    /**
     * Delete a Schedule by ID.
     *
     * @param scanner object for user input
     * @return true if deletion is successful
     */
    public boolean deleteSchedule(Scanner scanner) {
        System.out.println("\n========== DELETE SCHEDULE ==========\n");

        String query = "DELETE FROM Schedule WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int id = getValidInt(scanner, "Enter Schedule ID to delete: ");
            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to delete schedule: " + e.getMessage());
            return false;
        }
    }
}
