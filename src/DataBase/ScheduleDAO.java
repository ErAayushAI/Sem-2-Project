package DataBase;

import Model.Schedule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        System.out.println("---------- ADD SCHEDULE ----------");
        System.out.println();
        String query = "INSERT INTO Schedule (RouteId, DepartureTime) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Route Id: ");
            stmt.setInt(1, scanner.nextInt());

            System.out.println("Departure Time:");
            System.out.print("Enter Hour: ");
            int hour = scanner.nextInt();
            System.out.print("Enter Minute: ");
            int minute = scanner.nextInt();
            System.out.print("Enter Second: ");
            int second = scanner.nextInt();
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
        System.out.println("---------- SCHEDULE BY ROUTE ----------");
        System.out.println();
        String query = "SELECT * FROM Schedule WHERE RouteId = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Route Id: ");
            stmt.setInt(1, scanner.nextInt());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(rs.getInt(1));
                schedule.setRouteID(rs.getInt(2));
                schedule.setDepartureTime(rs.getTime(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
