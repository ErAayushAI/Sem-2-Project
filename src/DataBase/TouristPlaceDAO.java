package DataBase;

import Model.Feedback;
import Model.TouristPlace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TouristPlaceDAO {

    private Connection connection;
    public Scanner scanner = new Scanner(System.in);

    public TouristPlaceDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean addPlace(String name, String location, String category) {
        System.out.println("---------- ADD TOURIST PLACE ----------");
        System.out.println();
        String query = "INSERT INTO TouristPlace (Name, Location, Category, Ratings) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Name of Place: ");
            stmt.setString(1, scanner.nextLine().trim());

            System.out.print("Enter Area Id: ");
            stmt.setInt(2, scanner.nextInt());

            System.out.print("Enter Category: ");
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

    public void applyFeedback(Feedback fb) {
        TouristPlace tp = places.get(fb.placeName);
        if (tp != null) {
            tp.addRating(fb.rating);
            double newAvg = tp.getAverageRating();

            PreparedStatement pstmt = connection.prepareStatement("UPDATE tourist_place SET average_rating = ? WHERE name = ?");
            pstmt.setDouble(1, newAvg);
            pstmt.setString(2, fb.placeName);
            pstmt.executeUpdate();

            System.out.println("Updated rating for " + fb.placeName + ": " + newAvg + " stars");
        } else {
            System.out.println("⚠Place not found: " + fb.placeName);
        }
    }

    public List<TouristPlace> displayPlacesByCategory(Scanner scanner) {
        System.out.println("---------- TOURIST PLACE BY CATEGORY ----------");
        System.out.println();
        String query = "SELECT * FROM TouristPlace WHERE Category = ?";
        List<TouristPlace> places = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Category: ");
            stmt.setString(1, scanner.nextLine().trim());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                TouristPlace place = new TouristPlace();
                place.setId(rs.getInt(1));
                place.setName(rs.getString(2));
                place.setCategory(rs.getString(3));
                place.setAreaId(rs.getInt(4));
                place.setRatings(rs.getDouble(5));
                places.add(place);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return places;
    }

    public void displayTopRatedPlaces(double threshold) {
        System.out.println("Top-Rated Places (≥ " + threshold + " stars):");
        places.values().stream()
                .filter(tp -> tp.getAverageRating() >= threshold)
                .forEach(TouristPlace::showPlaceInfo);
    }

    public void displayAllPlaces() {
        places.values().forEach(TouristPlace::showPlaceInfo);
    }

}
