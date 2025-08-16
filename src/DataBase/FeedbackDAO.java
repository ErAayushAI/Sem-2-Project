package DataBase;

import DataStructure.FeedbackLinkedList;
import Model.Feedback;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Validation.InputValidator.getValidInt;

public class FeedbackDAO {
    private Connection connection;

    public FeedbackDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            System.out.println("❌ No database connection provided to Feedback.");
        }
    }

    /**
     * To Submit Feedback on database.
     *
     * @param scanner object for user input
     * @return true if feedback is added
     */
    public boolean submitFeedback(Scanner scanner) {
        System.out.println("\n========== ADD FEEDBACK ==========\n");

        String query = "INSERT INTO feedback (UserId, PlaceId, Comments, Rating) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int uid = getValidInt(scanner, "Enter user id: ");
            stmt.setInt(1, uid);

            int pid = getValidInt(scanner, "Enter Place Id: ");
            stmt.setInt(2, pid);

            System.out.print("Enter Comments: ");
            scanner.nextLine();
            stmt.setString(3, scanner.nextLine().trim());

            int rat = getValidInt(scanner, "Enter Rating Between 1 and 5: ");
            stmt.setInt(4, rat);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to load feedback data: " + e.getMessage());
            return false;
        }
    }

    /**
     * For Reviewing Latest 10 Feedbacks
     *
     * @return list of feedbacks
     */
    public List<Feedback> reviewLatestFeedback() {
        System.out.println("\n========== REVIEW FEEDBACK ==========\n");

        List<Feedback> feedbacks = new ArrayList<>();
        String query = "SELECT * FROM Feedback LIMIT 10";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Feedback fb = new Feedback();
                fb.setId(rs.getInt(1));
                fb.setUserId(rs.getInt(2));
                fb.setPlaceId(rs.getInt(3));
                fb.setComments(rs.getString(4));
                fb.setRating(rs.getInt(5));
                feedbacks.add(fb);
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load feedback data: " + e.getMessage());
        }
        return feedbacks;
    }

    /**
     * To find average of ratings.
     *
     * @param placeId for find avg of ratings given by the user
     * @return average of rating given by user on particular place
     */
    public double getAverageRating(int placeId) {
        double avg = 0.0;
        String query = "SELECT AVG(Rating) FROM Feedback WHERE placeId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, placeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                avg = rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load feedback data: " + e.getMessage());
        }
        return avg;
    }

    /**
     * To Assign Feedbacks into Linked List of Feedback.
     * To search, display feedbacks using DS.
     */
    public FeedbackLinkedList loadFeedbacksIntoLinkedList() {
        List<Feedback> feedbacks = new ArrayList<>();
        String query = "SELECT * FROM Feedback";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Feedback fb = new Feedback();
                fb.setId(rs.getInt(1));
                fb.setUserId(rs.getInt(2));
                fb.setPlaceId(rs.getInt(3));
                fb.setComments(rs.getString(4));
                fb.setRating(rs.getInt(5));
                feedbacks.add(fb);
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load feedback data: " + e.getMessage());
        }

        FeedbackLinkedList list = new FeedbackLinkedList();
        for (Feedback fb : feedbacks) {
            list.addFeedback(fb);
        }

        return list;
    }
}