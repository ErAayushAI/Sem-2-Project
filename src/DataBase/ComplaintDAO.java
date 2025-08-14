package DataBase;

import DataStructure.CustomQueue;
import Model.Complaint;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComplaintDAO {
    private CustomQueue<Complaint> complaintQueue = new CustomQueue<>();
    private Connection connection;

    public ComplaintDAO() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * To Register Complaint.
     *
     * @param scanner object for user input
     * @return true if complaint is filed
     */
    public boolean fileComplaint(Scanner scanner) {
        System.out.println("---------- ADD COMPLAINT ----------");
        System.out.println();
        String query = "INSERT INTO complaint (Department, UserId, Issue, Status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter Department Name: ");
            String deptName = scanner.next().trim();
            stmt.setString(1, deptName);

            System.out.print("Enter User Id: ");
            int userId = scanner.nextInt();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return complaints;
    }

    /**
     * To solve complaint.
     */
    public void resolveNextComplaint() {
        System.out.println("---------- RESOLVE PENDING COMPLAINT ----------");
        System.out.println();
        if (!complaintQueue.isEmpty()) {
            Complaint c = complaintQueue.dequeue();
            System.out.println("Resolving complaint:");
            System.out.println("Department: " + c.getDepartment());
            System.out.println("User: " + c.getUserId());
            System.out.println("Issue: " + c.getIssue());
            c.setStatus(true);
        } else {
            System.out.println("No complaints to resolve.");
        }
    }
}
