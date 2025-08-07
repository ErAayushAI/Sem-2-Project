package DataBase;

import Model.Feedback;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FeedbackDAO {
    private Connection connection;

    public FeedbackDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * To Submit Feedback on database.
     *
     * @param scanner object for user input
     * @return true if feedback is added
     */
    public boolean submitFeedback(Scanner scanner) {
        System.out.println("---------- ADD FEEDBACK ----------");
        System.out.println();
        String query = "INSERT INTO feedback (UserId, PlaceId, Comments, Rating) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter User ID: ");
            stmt.setInt(1, scanner.nextInt());

            System.out.print("Enter Place Id: ");
            stmt.setInt(2, scanner.nextInt());

            System.out.print("Enter Comments: ");
            scanner.nextLine();
            stmt.setString(3, scanner.nextLine().trim());

            System.out.print("Enter Rating Between 1 and 5: ");
            stmt.setInt(4, scanner.nextInt());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * For Reviewing Latest 10 Feedbacks
     *
     * @return list of feedbacks
     */
    public List<Feedback> reviewLatestFeedback() {
        System.out.println("---------- REVIEW FEEDBACK ----------");
        System.out.println();
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
            e.printStackTrace();
        }
        return feedbacks;
    }

    /**
     * To find average of ratings.
     *
     * @param scanner object for user input
     * @return average of rating given by user on particular place
     */
    public double getAverageRating(Scanner scanner) {
        double avg = 0.0;
        String query = "SELECT AVG(Rating) FROM Feedback WHERE placeId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            System.out.print("Enter Place Id: ");
            stmt.setInt(1, scanner.nextInt());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                avg = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avg;
    }
}
