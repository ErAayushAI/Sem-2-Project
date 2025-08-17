package DataBase;

import Model.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Validation.InputValidator.*;

public class RouteDAO {
    private Connection connection;

    public RouteDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            System.out.println("❌ No database connection provided to Route.");
        }
    }

    /**
     * Add a new Route.
     *
     * @param scanner object for user input
     * @return true if Route is added
     */
    public boolean addRoute(Scanner scanner) {
        boolean che;
        System.out.println("\n========== ADD ROUTE ==========\n");

        String query = "INSERT INTO Route (Name, Length, IsBusRoute, IsMetroRoute) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Name: ");
            scanner.nextLine();
            stmt.setString(1, scanner.nextLine().trim());

            double length = getValidDouble(scanner, "Enter Length: ");
            stmt.setDouble(2, length);

            che = getValidBoolean(scanner, "Enter 'true' if it is Bus Route: ");
            stmt.setBoolean(3, che);

            che = getValidBoolean(scanner, "Enter 'true' if it is Metro Route: ");
            stmt.setBoolean(4, che);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to load route data: " + e.getMessage());
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
        System.out.println("\n========== ROUTE BY ID ==========\n");

        String query = "SELECT * FROM Route WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int che = getValidInt(scanner, "Enter Id: ");
            stmt.setInt(1, che);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Route route = new Route();
                route.setId(rs.getInt(1));
                route.setName(rs.getString(2));
                route.setLength(rs.getDouble(3));
                route.setBusRoute(rs.getBoolean(4));
                route.setMetroRoute(rs.getBoolean(5));
                return route;
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load route data: " + e.getMessage());
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
            System.out.println("❌ Failed to load route data: " + e.getMessage());
        }
        return routes;
    }

    /**
     * To update Route length and route for which service.
     *
     * @param scanner Object for user input
     * @return true if Route is Updated
     */
    public boolean updateRoute(Scanner scanner) {
        int che;
        System.out.println("\n========== UPDATE ROUTE ==========\n");

        String query = "UPDATE Route SET length = ?, isBusRoute = ?, isMetroRoute = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            che = getValidInt(scanner, "Enter new Length: ");
            stmt.setInt(1, che);

            boolean metroTransport = false;
            boolean BusTransport = getValidBoolean(scanner, "Enter 'true' if it is Bus Station: ");
            stmt.setBoolean(2, BusTransport);
            if(BusTransport){
                stmt.setBoolean(3, false);
            } else {
                metroTransport = getValidBoolean(scanner, "Enter 'true' if it is Metro Transport: ");
                stmt.setBoolean(3, metroTransport);
            }

            if(!BusTransport && !metroTransport){
                System.out.println("You should choose any of the above transport to book tickets.");
                return false;
            }

            che = getValidInt(scanner, "Enter Route Id to Update: ");
            stmt.setInt(4, che);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to load route data: " + e.getMessage());
            return false;
        }
    }

    /**
     * To delete Route.
     *
     * @param scanner Object for User inputs
     * @return true if route is deleted
     */
    public boolean deleteRoute (Scanner scanner){
        System.out.println("\n========== DELETE ROUTE ==========\n");

        String query = "DELETE FROM Route WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int che = getValidInt(scanner, "Enter Route Id to Delete: ");
            stmt.setInt(1, che);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to load route data: " + e.getMessage());
            return false;
        }
    }
}
