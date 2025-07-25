package DataBase;

import Model.Feedback;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FeedbackDAO {
    private Connection connection = DataBaseManager.connection;
    public Scanner scanner = new Scanner(System.in);

    public FeedbackDAO() {
        try {
            DataBaseManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * To Submit Feedback on database.
     *
     * @return true if feedback is added
     */
    public boolean submitFeedback() {
        System.out.println("---------- ADD FEEDBACK ----------");
        System.out.println();
        String query = "INSERT INTO feedback (UserId, PlaceName, Comments, Rating) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter User ID: ");
            stmt.setInt(1, scanner.nextInt());

            System.out.print("Enter Place Name: ");
            scanner.nextLine();
            stmt.setString(2, scanner.nextLine().trim());

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
                fb.setPlaceName(rs.getString(3));
                fb.setComments(rs.getString(4));
                fb.setRating(rs.getInt(5));
                feedbacks.add(fb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbacks;
    }
}
