package DataBase;

import DataStructure.CustomQueue;
import Model.Complaint;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Validation.InputValidator.getValidInt;
import static Validation.InputValidator.getValidString;

public class ComplaintDAO {
    CustomQueue<Complaint> complaintQueue = new CustomQueue<>();
    private Connection connection;

    public ComplaintDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            System.out.println("❌ No database connection provided to Complaint.");
        }
    }

    /**
     * To Register Complaint.
     *
     * @param scanner object for user input
     * @return true if complaint is filed
     */
    public boolean fileComplaint(Scanner scanner) {
        System.out.println("\n========== ADD COMPLAINT ==========\n");

        String query = "INSERT INTO complaint (Department, UserId, Issue, Status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            String deptName = getValidString(scanner, "Enter Department Name: ");
            stmt.setString(1, deptName);
            
            int userId = getValidInt(scanner, "Enter User Id: ");
            stmt.setInt(2, userId);

            System.out.print("Enter Issue: ");
            scanner.nextLine();
            String issue = scanner.nextLine().trim();
            stmt.setString(3, issue);

            stmt.setBoolean(4, false);
            int rowsInserted = stmt.executeUpdate();

            Complaint c = new Complaint(deptName, userId, issue);
            complaintQueue.enqueue(c);

            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to load complaint data: " + e.getMessage());
            return false;
        }
    }

    /**
     * To get all the complaint.
     *
     * @return list of complaint
     */
    public List<Complaint> getAllComplaint() {
        List<Complaint> complaints = new ArrayList<>();
        String query = "SELECT * FROM complaint";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Complaint complaint = new Complaint();
                complaint.setId(rs.getInt(1));
                complaint.setDepartment(rs.getString(2));
                complaint.setUserId(rs.getInt(3));
                complaint.setIssue(rs.getString(4));
                complaint.setStatus(rs.getBoolean(5));
                complaints.add(complaint);
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load complaint data: " + e.getMessage());
        }
        return complaints;
    }

    /**
     * To solve complaint.
     */
    public void resolveNextComplaint() {
        System.out.println("\n========== RESOLVE PENDING COMPLAINT ==========\n");
        ComplaintDAO complaintDAO = new ComplaintDAO();
        List<Complaint> pendingComplaints = complaintDAO.getPendingComplaints();

        for (Complaint c : pendingComplaints) {
            complaintQueue.enqueue(c);
        }

        if (!complaintQueue.isEmpty()) {
            Complaint c = complaintQueue.dequeue();
            System.out.println("Resolving complaint:");
            System.out.println("Department: " + c.getDepartment());
            System.out.println("User: " + c.getUserId());
            System.out.println("Issue: " + c.getIssue());
            c.setStatus(true);
            String query = "UPDATE complaint SET status = true WHERE UserId = "+c.getUserId();
            try(Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                System.out.println("❌ Failed to load complaint data: " + e.getMessage());
            }
        } else {
            System.out.println("No complaints to resolve.");
        }
    }

    public List<Complaint> getPendingComplaints () {
        List<Complaint> complaints = new ArrayList<>();
        String query = "SELECT * FROM complaint WHERE status = false";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Complaint complaint = new Complaint();
                complaint.setId(rs.getInt(1));
                complaint.setDepartment(rs.getString(2));
                complaint.setUserId(rs.getInt(3));
                complaint.setIssue(rs.getString(4));
                complaint.setStatus(rs.getBoolean(5));
                complaints.add(complaint);
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load complaint data: " + e.getMessage());
        }
        return complaints;
    }
}
