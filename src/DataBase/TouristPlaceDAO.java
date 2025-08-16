package DataBase;

import Model.TouristPlace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Validation.AreaInputValidation.getValidInt;
import static Validation.AreaInputValidation.getValidString;

public class TouristPlaceDAO {

    FeedbackDAO fb = new FeedbackDAO();
    private Connection connection;
    public Scanner scanner = new Scanner(System.in);

    public TouristPlaceDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * To add a new Tourist place.
     *
     * @return true if the place is added
     */
    public boolean addPlace() {
        System.out.println("---------- ADD TOURIST PLACE ----------");
        System.out.println();
        String query = "INSERT INTO TouristPlace (Name, AreaId, Category, Ratings) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Name of Place: ");
            stmt.setString(1, scanner.nextLine().trim());

            System.out.print("Enter Area Id: ");
           int che = getValidInt(scanner, "Enter Area Id: ");
            stmt.setInt(2, che);

            System.out.print("Enter Category: ");
            System.out.println("Like Historic, Religious, etc...");
            scanner.nextLine();
            stmt.setString(3, scanner.nextLine().trim());

            stmt.setDouble(4, 0.0);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * To submit feedback of the place.
     *
     * @param scanner object for user input
     */
    public void applyFeedback(Scanner scanner) {
        System.out.println("---------- TOURIST PLACE FEEDBACK ----------");
        System.out.println();
        int placeId = getValidInt(scanner, "Enter Place Id to submit Feedback: ");
        if (fb.submitFeedback(scanner)) {
            String query = "UPDATE TouristPlace SET Ratings = ? WHERE PlaceId = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                double avgRatings = fb.getAverageRating(placeId);
                stmt.setDouble(1, avgRatings);
                stmt.setInt(2, placeId);
                stmt.executeUpdate();
            } catch (SQLException e) {
                // silently ignore if avg is zero
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
        System.out.println("---------- TOURIST PLACE BY CATEGORY ----------");
        System.out.println();
        String query = "SELECT * FROM TouristPlace WHERE Category = ?";
        List<TouristPlace> places = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            scanner.nextLine();
            System.out.print("Enter Category: ");
            String categ = getValidString(scanner, "Enter Category: ");
            stmt.setString(1, categ);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                TouristPlace place = new TouristPlace();
                place.setId(rs.getInt(1));
                place.setName(rs.getString(2));
                place.setAreaId(rs.getInt(3));
                place.setCategory(rs.getString(4));
                place.setRatings(rs.getDouble(5));
                places.add(place);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        System.out.println("---------- TOP-RATED PLACES ----------");
        System.out.println();
        String query = "SELECT * FROM TouristPlace WHERE Ratings >= ?";
        List<TouristPlace> places = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int che = getValidInt(scanner, "Enter Ratings: ");
            stmt.setInt(1, che);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                TouristPlace place = new TouristPlace();
                place.setId(rs.getInt(1));
                place.setName(rs.getString(2));
                place.setAreaId(rs.getInt(3));
                place.setCategory(rs.getString(4));
                place.setRatings(rs.getDouble(5));
                places.add(place);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            if (rs.next()) {
                TouristPlace place = new TouristPlace();
                place.setId(rs.getInt(1));
                place.setName(rs.getString(2));
                place.setAreaId(rs.getInt(3));
                place.setCategory(rs.getString(4));
                place.setRatings(rs.getDouble(5));
                places.add(place);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return places;
    }
}
