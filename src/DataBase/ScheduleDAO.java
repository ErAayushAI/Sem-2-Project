package DataBase;

import Model.Schedule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Validation.AreaInputValidation.getValidInt;

public class ScheduleDAO {
    private Connection connection;

    public ScheduleDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
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

        String query = "INSERT INTO Schedule (RouteId, DepartureTime) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int che = getValidInt(scanner, "Enter Route Id: ");
            stmt.setInt(1, che);

            System.out.println("Departure Time:");
            int hour = getValidInt(scanner, "Enter Hour: ");
            int minute = getValidInt(scanner, "Enter Minute: ");
            int second = getValidInt(scanner, "Enter Second: ");
            Time t = new Time(hour, minute, second);
            stmt.setTime(2, t);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
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

            int che = getValidInt(scanner, "Enter Route Id: ");
            stmt.setInt(1, che);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(rs.getInt(1));
                schedule.setRouteID(rs.getInt(2));
                schedule.setDepartureTime(rs.getTime(3));
                return schedule;
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            if (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(rs.getInt(1));
                schedule.setRouteID(rs.getInt(2));
                schedule.setDepartureTime(rs.getTime(3));
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }
}
