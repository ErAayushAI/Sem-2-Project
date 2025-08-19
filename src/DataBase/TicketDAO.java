package DataBase;

import Model.Ticket;
import Validation.InputValidator;

import java.sql.*;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import static Validation.InputValidator.*;

public class TicketDAO {
    private Connection connection;

    public TicketDAO() {
        try {
            connection = DataBaseManager.getConnection();
            connection.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println("❌ No database connection provided to Ticket.");

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
        System.out.println("\n========== ADD TICKET ==========\n");

        String query = "INSERT INTO ticket (UserID, RouteId, IsBusTransport, IsMetroTransport, Time, TotalBill, Distance) VALUES (?, ?, ?, ?, ?, ?, ?)";
        boolean status = false;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            int uid = getValidInt(scanner, "Enter User Id: ");
            stmt.setInt(1, uid);

            int rid = getValidInt(scanner, "Enter Route Id: ");
            stmt.setInt(2, rid);

            boolean metroTransport = false;
            boolean BusTransport = getValidBoolean(scanner, "Enter 'true' if it is Bus Transport: ");
            stmt.setBoolean(3, BusTransport);
            if(BusTransport){
                stmt.setBoolean(4, false);
            } else {
                metroTransport = getValidBoolean(scanner, "Enter 'true' if it is Metro Transport: ");
                stmt.setBoolean(4, metroTransport);
            }

            if(!BusTransport && !metroTransport){
                System.out.println("You should choose any of the above transport to book tickets.");
                return false;
            }

            java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
            stmt.setTimestamp(5, timestamp);

            double distance = getValidDouble(scanner, "Enter Travel Distance(km): ");
            stmt.setDouble(7, distance);
            stmt.setDouble(6, ticket.calculateBill(distance));

            int choice;
            System.out.println("Ticket pending confirmation: " + ticket.getId());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("1. Confirm Ticket\n2. Cancel Ticket");
                choice = InputValidator.getChoice(scanner);

                boolean ticketLoop = true;
                while(ticketLoop) {
                    switch (choice) {
                        case 1:
                            status = ticketDAO.commitTicket();
                            ticketLoop = false;
                            break;
                        case 2:
                            status = ticketDAO.rollbackTicket();
                            ticketLoop = false;
                            break;
                        default:
                            System.out.println("Invalid Choice>>");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load ticket data: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("❌ Failed to find file location: " + e.getMessage());
            return false;
        }
        return status;
    }

    /**
     * To confirm Ticket by User.
     *
     * @throws SQLException for connection
     * @throws IOException  for generate bill
     */
    public boolean commitTicket() throws SQLException, IOException {
        System.out.println("\n========== CONFIRM TICKET ==========\n");

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
     * @throws SQLException for connection
     */
    public boolean rollbackTicket() throws SQLException {
        System.out.println("\n========== CANCEL TICKET ==========\n");


        int ticketId = getLastInsertedTicketId();
        if (ticketId == -1) {
            System.out.println("Error: Could not fetch Ticket ID.");
            return false;
        }

        connection.rollback();
        String query = "DELETE FROM ticket WHERE Id = " + ticketId;
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
        ResultSetMetaData ticketRSMD = rs.getMetaData();

        StringBuilder bill = new StringBuilder("=== TICKET BILL ===\n");
        while (rs.next()) {
            for (int i = 1; i <= ticketRSMD.getColumnCount(); i++) {
                bill.append(ticketRSMD.getColumnName(i)).append(": ").append(rs.getString(i)).append("\n");
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
        System.out.println("\n========== SEARCH TICKET ==========\n");

        String sql = "SELECT * FROM ticket WHERE RouteId = ? OR UserId = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);

        int routeId = getValidInt(scanner, "Enter Route Id: ");
        stmt.setInt(1, routeId);

        int userId = getValidInt(scanner, "Enter User Id: ");
        stmt.setInt(2, userId);

        ResultSet rs = stmt.executeQuery();
        ResultSetMetaData ticketRSMD = rs.getMetaData();

        boolean found = false;
        while (rs.next()) {
            found = true;
            System.out.println("---- TICKET FOUND ----");
            for (int i = 1; i <= ticketRSMD.getColumnCount(); i++) {
                System.out.println(ticketRSMD.getColumnName(i) + ": " + rs.getString(i));
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
        int userAnswer = getValidInt(sc, "Captcha: What is " + num1 + " + " + num2 + "? ");

        return userAnswer == correctAnswer;
    }

}
