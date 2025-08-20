package DataBase;

import Model.TouristPlace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Validation.InputValidator.*;

public class TouristPlaceDAO {

    FeedbackDAO fb = new FeedbackDAO();
    private Connection connection;
    public Scanner scanner = new Scanner(System.in);

    public TouristPlaceDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            System.out.println("❌ No database connection provided to Tourist Place.");
        }
    }

    /**
     * To add a new Tourist place.
     *
     * @return true if the place is added
     */
    public boolean addPlace() {
        System.out.println("\n========== ADD TOURIST PLACE ==========\n");

        String query = "INSERT INTO TouristPlace (Name, AreaId, Category, Ratings) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Name of Place: ");
            stmt.setString(1, scanner.nextLine().trim());

            System.out.print("Enter Area Id: ");
           int aid = getValidInt(scanner, "Enter Area Id: ");
            stmt.setInt(2, aid);

            System.out.print("Enter Category: ");
            System.out.println("Like Historic, Religious, etc...");
            scanner.nextLine();
            stmt.setString(3, scanner.nextLine().trim());

            stmt.setDouble(4, 0.0);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to load tourist place data: " + e.getMessage());
            return false;
        }
    }

    /**
     * To submit feedback of the place.
     *
     * @param scanner object for user input
     */
    public void applyFeedback(Scanner scanner) {
        System.out.println("\n========== TOURIST PLACE FEEDBACK ==========\n");

        int placeId = getValidInt(scanner, "Enter Place Id to submit Feedback: ");

        if (fb.submitFeedback(scanner, placeId)) {
            double avgRatings = fb.getAverageRating(placeId);
            String query = "UPDATE TouristPlace SET Ratings = ? WHERE id = ?";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setDouble(1, avgRatings);
                stmt.setInt(2, placeId);
                stmt.executeUpdate();
                System.out.println("✅ Updated new average rating: " + avgRatings);
            } catch (SQLException e) {
                System.err.println("❌ Failed to update rating: " + e.getMessage());
            }
        }
    }

    /**
     * To find places Category wise.
     *
     * @param scanner object for user inputs
     * @return list of places
     */
    public List<TouristPlace> displayPlacesByCategory(Scanner scanner) {
        System.out.println("\n========== TOURIST PLACE BY CATEGORY ==========\n");

        String query = "SELECT * FROM TouristPlace WHERE Category = ?";
        List<TouristPlace> places = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            String category = getValidString(scanner, "Enter Category: ");
            stmt.setString(1, category);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TouristPlace place = new TouristPlace();
                place.setId(rs.getInt(1));
                place.setName(rs.getString(2));
                place.setAreaId(rs.getInt(3));
                place.setCategory(rs.getString(4));
                place.setRatings(rs.getDouble(5));
                places.add(place);
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load tourist place data: " + e.getMessage());
        }
        return places;
    }

    /**
     * To find top-rated place given by user.
     *
     * @param scanner object for user inputs
     * @return list of places
     */
    public List<TouristPlace> displayTopRatedPlaces(Scanner scanner) {
        System.out.println("\n========== TOP-RATED PLACES ==========\n");

        String query = "SELECT * FROM TouristPlace WHERE Ratings >= ?";
        List<TouristPlace> places = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            double ratings = getValidDouble(scanner, "Enter Ratings: ");
            stmt.setDouble(1, ratings);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TouristPlace place = new TouristPlace();
                place.setId(rs.getInt(1));
                place.setName(rs.getString(2));
                place.setAreaId(rs.getInt(3));
                place.setCategory(rs.getString(4));
                place.setRatings(rs.getDouble(5));
                places.add(place);
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load tourist place data: " + e.getMessage());
        }
        return places;
    }

    /**
     * Get all the Tourist Places in the city.
     *
     * @return list of places
     */
    public List<TouristPlace> displayAllPlaces() {
        String query = "SELECT * FROM TouristPlace";
        List<TouristPlace> places = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TouristPlace place = new TouristPlace();
                place.setId(rs.getInt(1));
                place.setName(rs.getString(2));
                place.setAreaId(rs.getInt(3));
                place.setCategory(rs.getString(4));
                place.setRatings(rs.getDouble(5));
                places.add(place);
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load tourist place data: " + e.getMessage());
        }
        return places;
    }

    /**
     * To find tourist places by Area ID.
     *
     * @return list of places in that area
     */
    public List<TouristPlace> getPlacesByAreaId(Scanner scanner) {
        System.out.println("\n========== TOURIST PLACES BY AREA ==========\n");

        String query = "SELECT * FROM TouristPlace WHERE AreaId = ?";
        List<TouristPlace> places = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int aid = getValidInt(scanner, "Enter Area Id: ");
            stmt.setInt(1, aid);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TouristPlace place = new TouristPlace();
                place.setId(rs.getInt("id"));
                place.setName(rs.getString("Name"));
                place.setAreaId(rs.getInt("AreaId"));
                place.setCategory(rs.getString("Category"));
                place.setRatings(rs.getDouble("Ratings"));
                places.add(place);
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load places by area: " + e.getMessage());
        }

        return places;
    }

    /**
     * Display all unique categories available in TouristPlace table.
     *
     * @return list of category names
     */
    public List<String> getAllCategories() {
        System.out.println("\n========== AVAILABLE TOURIST CATEGORIES ==========\n");

        String query = "SELECT DISTINCT Category FROM TouristPlace";
        List<String> categories = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String category = rs.getString("Category");
                categories.add(category);
                System.out.println("• " + category);
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load categories: " + e.getMessage());
        }

        return categories;
    }
}
