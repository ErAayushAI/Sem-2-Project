package DataBase;

import Model.Ticket;

import java.sql.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class TicketDAO {
    private Connection connection;

    public TicketDAO() {
        try {
            connection = DataBaseManager.getConnection();
            connection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a new Ticket.
     *
     * @param scanner object for user input
     * @return true if new ticket is added
     */
    public boolean addTicket(Scanner scanner) {
        Ticket ticket = new Ticket();
        TicketDAO ticketDAO = new TicketDAO();
        System.out.println("---------- ADD TICKET ----------");
        System.out.println();
        String query = "INSERT INTO ticket (UserID, RouteId, IsBusTransport, IsMetroTransport, Time, TotalBill, Distance) VALUES (?, ?, ?, ?, ?, ?, ?)";
        boolean status = false;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            System.out.print("Enter User Id: ");
            stmt.setInt(1, scanner.nextInt());

            System.out.println("Enter Route Id: ");
            stmt.setInt(2, scanner.nextInt());

            System.out.print("Enter 'true' if it is Bus Transport: ");
            stmt.setBoolean(3, scanner.nextBoolean());

            System.out.print("Enter 'true' if it is Metro Transport: ");
            stmt.setBoolean(4, scanner.nextBoolean());

            System.out.println("Enter Time: ");
            System.out.print("Enter Hour: ");
            int hour = scanner.nextInt();
            System.out.print("Enter Minute: ");
            int minute = scanner.nextInt();
            System.out.print("Enter Second: ");
            int second = scanner.nextInt();
            Time t = new Time(hour, minute, second);
            stmt.setTime(5, t);

            System.out.print("Enter Travel Distance: ");
            double distance = scanner.nextDouble();
            stmt.setDouble(7, distance);
            stmt.setDouble(6, ticket.calculateBill(distance));

            System.out.println("Ticket pending confirmation: " + ticket.getId());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("1. Confirm Ticket\n2. Cancel Ticket");
                System.out.print("Enter Choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        status = ticketDAO.commitTicket(scanner);
                        break;
                    case 2:
                        status = ticketDAO.rollbackTicket(scanner);
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return status;
    }

    /**
     * To confirm Ticket by User.
     *
     * @param scanner object for user input
     * @throws SQLException for connection
     * @throws IOException  for generate bill
     */
    public boolean commitTicket(Scanner scanner) throws SQLException, IOException {
        System.out.println("---------- CONFIRM TICKET ----------");
        System.out.println();
        connection.commit();

        int ticketId = getLastInsertedTicketId();
        if (ticketId == -1) {
            System.out.println("Error: Could not fetch Ticket ID.");
            return false;
        }

        // Generate captcha
        if (!verifyCaptcha()) {
            System.out.println("Captcha failed! Ticket not confirmed.");
            return false;
        }

        generateBill(ticketId);
        System.out.println("Ticket confirmed and saved: " + ticketId);
        return true;
    }

    /**
     * To cancel Ticket by User.
     *
     * @param scanner object for user input
     * @throws SQLException for connection
     */
    public boolean rollbackTicket(Scanner scanner) throws SQLException {
        System.out.println("---------- CANCEL TICKET ----------");
        System.out.println();

        int ticketId = getLastInsertedTicketId();
        if (ticketId == -1) {
            System.out.println("Error: Could not fetch Ticket ID.");
            return false;
        }

        connection.rollback();
        String query = "DELETE FROM ticket WHERE ticketId = " + ticketId;
        try(Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
            System.out.println("Transaction rolled back, ticket cancelled: " + ticketId);
            return false;
        }
    }

    /**
     * To generate bill in text file
     *
     * @param ticketId Ticket id
     * @throws SQLException for connection
     * @throws IOException  for Buffered Writer
     */
    public void generateBill(int ticketId) throws SQLException, IOException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ticket WHERE Id = ?");
        stmt.setInt(1, ticketId);
        ResultSet rs = stmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();

        StringBuilder bill = new StringBuilder("=== TICKET BILL ===\n");
        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                bill.append(rsmd.getColumnName(i)).append(": ").append(rs.getString(i)).append("\n");
            }
        }

        String filename = "bill_" + ticketId + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(bill.toString());
        }

        System.out.println("Bill generated: " + filename);
    }

    /**
     * To search Ticket by route and user.
     *
     * @param scanner object for user input
     * @throws SQLException for connection
     */
    public void searchTickets(Scanner scanner) throws SQLException {
        System.out.println("---------- SEARCH TICKET ----------");
        System.out.println();
        String sql = "SELECT * FROM ticket WHERE RouteId = ? OR UserId = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);

        System.out.print("Enter Route Id: ");
        int routeId = scanner.nextInt();
        stmt.setInt(1, routeId);

        System.out.print("Enter User Id: ");
        int userId = scanner.nextInt();
        stmt.setInt(2, userId);

        ResultSet rs = stmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();

        boolean found = false;
        while (rs.next()) {
            found = true;
            System.out.println("---- TICKET FOUND ----");
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.println(rsmd.getColumnName(i) + ": " + rs.getString(i));
            }
        }

        if (!found) {
            System.out.println("No tickets found for route '" + routeId + "' or user '" + userId + "'.");
        }
    }

    public int getLastInsertedTicketId() throws SQLException {
        String sql = "SELECT Id FROM ticket ORDER BY Id DESC LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("Id");
            }
        }
        return -1;
    }

    public  boolean verifyCaptcha() {
        int num1 = (int)(Math.random()*10);
        int num2 = (int)(Math.random()*10);
        int correctAnswer = num1 + num2;

        Scanner sc = new Scanner(System.in);
        System.out.print("Captcha: What is " + num1 + " + " + num2 + "? ");
        int userAnswer = sc.nextInt();

        return userAnswer == correctAnswer;
    }
}
