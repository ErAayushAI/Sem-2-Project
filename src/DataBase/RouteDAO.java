package DataBase;

import Model.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RouteDAO {
    private Connection connection;

    public RouteDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a new Route.
     *
     * @param scanner object for user input
     * @return true if Route is added
     */
    public boolean addRoute(Scanner scanner) {
        System.out.println("---------- ADD ROUTE ----------");
        System.out.println();
        String query = "INSERT INTO Route (Name, Length, IsBusRoute, IsMetroRoute) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Name: ");
            scanner.nextLine();
            stmt.setString(1, scanner.nextLine().trim());

            System.out.print("Enter Length: ");
            stmt.setDouble(2, scanner.nextDouble());

            System.out.print("Enter 'true' if it is Bus Route: ");
            stmt.setBoolean(3, scanner.nextBoolean());

            System.out.print("Enter 'true' if it is Metro Route: ");
            stmt.setBoolean(4, scanner.nextBoolean());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get Route by id.
     *
     * @param scanner object for user input
     * @return Object of Route
     */
    public Route getRouteById(Scanner scanner) {
        System.out.println("---------- ROUTE BY ID ----------");
        System.out.println();
        String query = "SELECT * FROM Route WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Id: ");
            stmt.setInt(1, scanner.nextInt());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Route route = new Route();
                route.setId(rs.getInt(1));
                route.setName(rs.getString(2));
                route.setLength(rs.getDouble(3));
                route.setBusRoute(rs.getBoolean(4));
                route.setMetroRoute(rs.getBoolean(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get All the Route.
     *
     * @return list of all the Routes
     */
    public List<Route> getAllRoutes() {
        List<Route> routes = new ArrayList<>();
        String query = "SELECT * FROM Route";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Route route = new Route();
                route.setId(rs.getInt(1));
                route.setName(rs.getString(2));
                route.setLength(rs.getDouble(3));
                route.setBusRoute(rs.getBoolean(4));
                route.setMetroRoute(rs.getBoolean(5));
                routes.add(route);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return routes;
    }
}
