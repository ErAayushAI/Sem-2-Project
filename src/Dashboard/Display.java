package Dashboard;

import DataBase.DataBaseManager;
import Model.*;

import java.sql.*;
import java.util.List;

public class Display {
    private static final Connection connection = DataBaseManager.getConnection();

    // Display Admin Dashboard Menu
    public static void showAdminMenu() {
        System.out.println("\n🔒 Admin Dashboard");
        System.out.println("-------------------------------------------------");
        System.out.printf("%-30s %-25s%n", "1. View & Delete customers", "8. Manage Schedule");
        System.out.printf("%-30s %-25s%n", "2. Manage Area", "9. Manage Station");
        System.out.printf("%-30s %-25s%n", "3. Bus Services", "10. Manage Street");
        System.out.printf("%-30s %-25s%n", "4. Emergency Services", "11. Manage Tourist Places");
        System.out.printf("%-30s %-25s%n", "5. Metro Services", "12. Complaint");
        System.out.printf("%-30s %-25s%n", "6. Manage Parking Lot", "13. Feedback");
        System.out.printf("%-30s %-25s%n", "7. Manage Service Route", "0. Logout");
        System.out.println("-------------------------------------------------");
    }

    //Display Customer Dashboard Menu
    public static void showCustomerMenu() {
        System.out.println("\n🧑‍💼 Customer Dashboard");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-35s %-30s%n", "1. Travelling Routes & Schedules", "5. Parking Lot");
        System.out.printf("%-35s %-30s%n", "2. Emergency Services", "6. Submit Feedback");
        System.out.printf("%-35s %-30s%n", "3. Book Tickets & View Stations", "7. File a Complaint");
        System.out.printf("%-35s %-30s%n", "4. Tourist Places", "0. Logout");
        System.out.println("-------------------------------------------------------------");
    }

    //Display deleted customer List
    public static void printDeletedCustomerList(List<CustomerLog> logs) {
        if (logs == null || logs.isEmpty()) {
            System.out.println("⚠️ No deleted customer records found.");
            return;
        }

        System.out.println("\n🗑️ Deleted Customer List");
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-20s %-25s %-20s%n", "ID", "Username", "Email", "Deleted At", "Full Name");
        System.out.println("--------------------------------------------------------------------------------------------------");

        for (CustomerLog log : logs) {
            String username = (log.getUsername() != null) ? log.getUsername() : "—";
            String email = (log.getEmail() != null) ? log.getEmail() : "—";
            String fullName = (log.getFullName() != null) ? log.getFullName() : "—";
            String deletedAt = (log.getDeletedAt() != null) ? log.getDeletedAt().toString() : "—";

            System.out.printf("%-5d %-20s %-20s %-25s %-20s%n",
                    log.getCustomerId(),
                    username,
                    email,
                    deletedAt,
                    fullName);
        }

        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    //Display List of Areas
    public static void printAreas(List<Area> areas) {
        if (areas == null || areas.isEmpty()) {
            System.out.println("⚠️ No area data found.");
            return;
        }

        System.out.println("\n🌐 Area List");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-7s %-20s %-12s %-12s %-18s%n", "ID", "Name", "Latitude", "Longitude", "Emergency Point");
        System.out.println("--------------------------------------------------------------------------------");

        for (Area a : areas) {
            String emergencyStatus = a.isEmergencyPoint() ? "✅ Yes" : "❌ No";
            System.out.printf("%-7d %-20s %-12.6f %-12.6f %-18s%n",
                    a.getAreaId(),
                    a.getName(),
                    a.getLatitude(),
                    a.getLongitude(),
                    emergencyStatus);
        }

        System.out.println("--------------------------------------------------------------------------------");
    }

    //Display List of Buses
    public static void printBusList(List<Bus> buses) {
        if (buses == null || buses.isEmpty()) {
            System.out.println("⚠️ No bus data found.");
            return;
        }

        System.out.println("\n🚌 Bus List");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-5s %-15s %-10s %-15s %-15s%n", "ID", "License Plate", "Capacity", "Route ID", "Area ID");
        System.out.println("--------------------------------------------------------------------------------");

        for (Bus b : buses) {
            String route = (b.getCurrentRouteId() != null) ? String.valueOf(b.getCurrentRouteId()) : "—";
            String area = (b.getCurrentAreaID() != null) ? String.valueOf(b.getCurrentAreaID()) : "—";

            System.out.printf("%-5d %-15s %-10d %-15s %-15s%n",
                    b.getId(),
                    b.getLicensePlate(),
                    b.getCapacity(),
                    route,
                    area);
        }

        System.out.println("--------------------------------------------------------------------------------");
    }

    //Display List Emergency Services
    public static void printEmergencyServices(List<EmergencyService> services) {
        if (services == null || services.isEmpty()) {
            System.out.println("⚠️ No emergency services found.");
            return;
        }

        System.out.println("\n🚨 Emergency Services List");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-30s %-15s %-10s %-15s %-20s%n", "ID", "Name", "Type", "AreaID", "Contact", "Available Vehicles");
        System.out.println("--------------------------------------------------------------------------------------");

        for (EmergencyService es : services) {
            System.out.printf("%-5d %-30s %-15s %-10d %-15d %-20d%n",
                    es.getId(),
                    es.getName(),
                    es.getType(),
                    es.getAreaId(),
                    es.getContactNumber(),
                    es.getAvailableVehicles());
        }

        System.out.println("--------------------------------------------------------------------------------------");
    }

    //Display List of Metro
    public static void printMetroList(List<Metro> metros) {
        if (metros == null || metros.isEmpty()) {
            System.out.println("⚠️ No metro data found.");
            return;
        }

        System.out.println("\n🚇 Metro List");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-10s %-15s %-15s%n", "ID", "Train Name", "Capacity", "Route ID", "Area ID");
        System.out.println("--------------------------------------------------------------------------------");

        for (Metro m : metros) {
            String route = (m.getCurrentRouteID() != null) ? String.valueOf(m.getCurrentRouteID()) : "—";
            String area = (m.getCurrentAreaID() != null) ? String.valueOf(m.getCurrentAreaID()) : "—";

            System.out.printf("%-5d %-20s %-10d %-15s %-15s%n",
                    m.getId(),
                    m.getTrainName(),
                    m.getCapacity(),
                    route,
                    area);
        }

        System.out.println("--------------------------------------------------------------------------------");
    }

    //Display List of ParkingLots
    public static void printParkingLots(List<ParkingLot> parkingList) {
        if (parkingList == null || parkingList.isEmpty()) {
            System.out.println("⚠️ No parking lot data found.");
            return;
        }

        System.out.println("\n🅿️ Parking Lot List");
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-30s %-10s %-10s %-15s %-10s%n", "ID", "Name", "AreaID", "Capacity", "Occupied", "Status");
        System.out.println("------------------------------------------------------------------------------------------");

        for (ParkingLot p : parkingList) {
            int available = p.getCapacity() - p.getCurrentOccupancy();
            String status = (available > 0) ? "🟢 Available" : "🔴 Full";

            System.out.printf("%-5d %-30s %-10d %-10d %-15d %-10s%n",
                    p.getId(),
                    p.getName(),
                    p.getAreaId(),
                    p.getCapacity(),
                    p.getCurrentOccupancy(),
                    status);
        }

        System.out.println("------------------------------------------------------------------------------------------");
    }

    //Display List of Routes
    public static void printRoutes(List<Route> routes) {
        if (routes == null || routes.isEmpty()) {
            System.out.println("⚠️ No route data found.");
            return;
        }

        System.out.println("\n🛣️ Route List");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-40s %-10s %-15s %-15s%n", "ID", "Name", "Length(km)", "Bus Route", "Metro Route");
        System.out.println("----------------------------------------------------------------------------------------");

        for (Route r : routes) {
            String busStatus = r.isBusRoute() ? "✅ Yes" : "❌ No";
            String metroStatus = r.isMetroRoute() ? "✅ Yes" : "❌ No";

            System.out.printf("%-5d %-40s %-10.2f %-15s %-15s%n",
                    r.getId(),
                    r.getName(),
                    r.getLength(),
                    busStatus,
                    metroStatus);
        }

        System.out.println("----------------------------------------------------------------------------------------");
    }

    //Display List of Stations
    public static void printStations(List<Station> stations) {
        if (stations == null || stations.isEmpty()) {
            System.out.println("⚠️ No station data available.");
            return;
        }

        System.out.println("\n🚏 Station List");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-10s %-15s %-15s%n", "ID", "Name", "Area ID", "Bus Station", "Metro Station");
        System.out.println("--------------------------------------------------------------------------------");

        for (Station s : stations) {
            String busStatus = s.isBusStation() ? "✅ Yes" : "❌ No";
            String metroStatus = s.isMetroStation() ? "✅ Yes" : "❌ No";

            System.out.printf("%-5d %-20s %-10d %-15s %-15s%n",
                    s.getId(),
                    s.getName(),
                    s.getAreaId(),
                    busStatus,
                    metroStatus);
        }

        System.out.println("--------------------------------------------------------------------------------");
    }

    //Display List of Streets
    public static void printStreets(List<Street> streets) {
        if (streets == null || streets.isEmpty()) {
            System.out.println("⚠️ No street data available.");
            return;
        }

        System.out.println("\n🛤️ Street List");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-5s %-12s %-12s %-10s %-13s%n", "ID", "Start Area", "End Area", "Distance(km)", "One Way");
        System.out.println("--------------------------------------------------------------------------------");

        for (Street s : streets) {
            String oneWayStatus = s.isOneWay() ? "➡️ Yes" : "↔️ No";

            System.out.printf("%-5d %-12d %-12d %-13.2f %-10s%n",
                    s.getId(),
                    s.getStartAreaId(),
                    s.getEndAreaId(),
                    s.getDistance(),
                    oneWayStatus);
        }

        System.out.println("--------------------------------------------------------------------------------");
    }

    //Display List of TouristPlaces
    public static void printTouristPlaces(List<TouristPlace> places) {
        if (places == null || places.isEmpty()) {
            System.out.println("⚠️ No tourist place data available.");
            return;
        }

        System.out.println("\n🗺️ Tourist Places");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-5s %-30s %-25s %-10s %-10s%n", "ID", "Name", "Category", "Area ID", "Ratings");
        System.out.println("--------------------------------------------------------------------------------");

        for (TouristPlace tp : places) {
            System.out.printf("%-5d %-30s %-25s %-10d %-10.1f%n",
                    tp.getId(),
                    tp.getName(),
                    tp.getCategory(),
                    tp.getAreaId(),
                    tp.getRatings());
        }

        System.out.println("--------------------------------------------------------------------------------");
    }

    //Display List of Schedules
    public static void printScheduleTable(List<Schedule> schedules) {
        if (schedules == null || schedules.isEmpty()) {
            System.out.println("⚠️ No schedules available.");
            return;
        }

        String format = "| %-5s | %-10s | %-15s |%n";
        String separator = String.format("+%s+", "-".repeat(37));

        System.out.println("\n🕒 Schedule Table");
        System.out.println(separator);
        System.out.format(format, "ID", "Route ID", "Departure Time");
        System.out.println(separator);

        for (Schedule s : schedules) {
            System.out.format(format,
                    s.getId(),
                    s.getRouteID(),
                    s.getDepartureTime().toString());
        }

        System.out.println(separator);
    }

    //Display List of Customers
    public static void viewAllCustomers() throws SQLException {
        String sql = "SELECT id, username, email, fullName, createdAt FROM customer";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n👥 List of All Registered Customers");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("%-5s %-15s %-25s %-20s %-20s%n", "ID", "Username", "Email", "Full Name", "Joined Date");
            System.out.println("--------------------------------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String fullName = rs.getString("fullName");
                Timestamp createdAt = rs.getTimestamp("createdAt");

                System.out.printf("%-5d %-15s %-25s %-20s %-20s%n",
                        id,
                        username,
                        email,
                        fullName,
                        createdAt.toLocalDateTime().toLocalDate());
            }

            System.out.println("--------------------------------------------------------------------------------");
        }
    }

    //Display List of Categories
    public static void printCategories(List<String> categories) {
        if (categories == null || categories.isEmpty()) {
            System.out.println("⚠️ No categories found.");
            return;
        }

        String line = "+---------------------------+";
        String format = "| %-25s |\n";

        System.out.println("\n---------- AVAILABLE CATEGORIES ----------\n");
        System.out.println(line);
        System.out.printf(format, "Category");
        System.out.println(line);

        for (String category : categories) {
            System.out.printf(format, category);
        }

        System.out.println(line);
    }

    //Display Area
    public static void printArea(Area area) {
        if (area == null) {
            System.out.println("⚠️ Area not found.");
            return;
        }

        System.out.println("\n🌐 Area Details");
        System.out.println("--------------------------------------------------");
        System.out.printf("ID: %d%n", area.getAreaId());
        System.out.printf("Name: %s%n", area.getName());
        System.out.printf("Latitude: %.6f%n", area.getLatitude());
        System.out.printf("Longitude: %.6f%n", area.getLongitude());
        System.out.printf("Emergency Point: %s%n", area.isEmergencyPoint() ? "✅ Yes" : "❌ No");
        System.out.println("--------------------------------------------------");
    }

    //Display Street
    public static void printStreet(Street street) {
        if (street == null) {
            System.out.println("⚠️ Street not found.");
            return;
        }

        System.out.println("\n🛣️ Street Details");
        System.out.println("--------------------------------------------------");
        System.out.printf("ID: %d%n", street.getId());
        System.out.printf("Start Area ID: %d%n", street.getStartAreaId());
        System.out.printf("End Area ID: %d%n", street.getEndAreaId());
        System.out.printf("Distance: %.2f km%n", street.getDistance());
        System.out.printf("One Way: %s%n", street.isOneWay() ? "➡️ Yes" : "↔️ No");
        System.out.println("--------------------------------------------------");
    }

    //Display Station
    public static void printStation(Station station) {
        if (station == null) {
            System.out.println("⚠️ Station not found.");
            return;
        }

        System.out.println("\n🚏 Station Details");
        System.out.println("--------------------------------------------------");
        System.out.printf("ID: %d%n", station.getId());
        System.out.printf("Name: %s%n", station.getName());
        System.out.printf("Area ID: %d%n", station.getAreaId());
        System.out.printf("Bus Station: %s%n", station.isBusStation() ? "✅ Yes" : "❌ No");
        System.out.printf("Metro Station: %s%n", station.isMetroStation() ? "✅ Yes" : "❌ No");
        System.out.println("--------------------------------------------------");
    }

    //Display Metro
    public static void printMetro(Metro metro) {
        if (metro == null) {
            System.out.println("⚠️ Metro not found.");
            return;
        }

        System.out.println("\n🚇 Metro Details");
        System.out.println("--------------------------------------------------");
        System.out.printf("ID: %d%n", metro.getId());
        System.out.printf("Train Name: %s%n", metro.getTrainName());
        System.out.printf("Capacity: %d%n", metro.getCapacity());
        System.out.printf("Current Route ID: %s%n", metro.getCurrentRouteID() != null ? metro.getCurrentRouteID() : "—");
        System.out.printf("Current Area ID: %s%n", metro.getCurrentAreaID() != null ? metro.getCurrentAreaID() : "—");
        System.out.println("--------------------------------------------------");
    }

    //Display Emergency Service
    public static void printEmergencyService(EmergencyService es) {
        if (es == null) {
            System.out.println("⚠️ Emergency service not found.");
            return;
        }

        System.out.println("\n🚨 Emergency Service Details");
        System.out.println("--------------------------------------------------");
        System.out.printf("ID: %d%n", es.getId());
        System.out.printf("Name: %s%n", es.getName());
        System.out.printf("Type: %s%n", es.getType());
        System.out.printf("Area ID: %d%n", es.getAreaId());
        System.out.printf("Contact Number: %d%n", es.getContactNumber());
        System.out.printf("Available Vehicles: %d%n", es.getAvailableVehicles());
        System.out.println("--------------------------------------------------");
    }

    //Display Bus
    public static void printBus(Bus bus) {
        if (bus == null) {
            System.out.println("⚠️ Bus not found.");
            return;
        }

        System.out.println("\n🚌 Bus Details");
        System.out.println("--------------------------------------------------");
        System.out.printf("ID: %d%n", bus.getId());
        System.out.printf("License Plate: %s%n", bus.getLicensePlate());
        System.out.printf("Capacity: %d%n", bus.getCapacity());
        System.out.printf("Current Route ID: %s%n", bus.getCurrentRouteId() != null ? bus.getCurrentRouteId() : "—");
        System.out.printf("Current Area ID: %s%n", bus.getCurrentAreaID() != null ? bus.getCurrentAreaID() : "—");
        System.out.println("--------------------------------------------------");
    }

    //Display Route
    public static void printRoute(Route route) {
        if (route == null) {
            System.out.println("⚠️ Route not found.");
            return;
        }

        System.out.println("\n🛣️ Route Details");
        System.out.println("--------------------------------------------------");
        System.out.printf("ID: %d%n", route.getId());
        System.out.printf("Name: %s%n", route.getName());
        System.out.printf("Length: %.2f km%n", route.getLength());
        System.out.printf("Bus Route: %s%n", route.isBusRoute() ? "✅ Yes" : "❌ No");
        System.out.printf("Metro Route: %s%n", route.isMetroRoute() ? "✅ Yes" : "❌ No");
        System.out.println("--------------------------------------------------");
    }

    //Display Schedule
    public static void printSchedule(Schedule schedule) {
        if (schedule == null) {
            System.out.println("⚠️ No schedule found for this route.");
            return;
        }

        System.out.println("\n🕒 Schedule Details");
        System.out.println("--------------------------------------------------");
        System.out.printf("Schedule ID: %d%n", schedule.getId());
        System.out.printf("Route ID: %d%n", schedule.getRouteID());
        System.out.printf("Departure Time: %s%n", schedule.getDepartureTime());
        System.out.println("--------------------------------------------------");
    }

    //Display Feedbacks
    public static void printFeedbacks(List<Feedback> feedbackList) {
        System.out.println("\n----------------- FEEDBACK TABLE -----------------");
        System.out.printf("%-5s %-8s %-9s %-40s %-6s\n", "ID", "UserID", "PlaceID", "Comments", "Rating");
        System.out.println("---------------------------------------------------------------");

        for (Feedback fb : feedbackList) {
            System.out.printf("%-5d %-8d %-9d %-40s %-6s\n",
                    fb.getId(),
                    fb.getUserId(),
                    fb.getPlaceId(),
                    fb.getComments(),
                    fb.getRating());
        }

        System.out.println("---------------------------------------------------------------\n");
    }

    //Display List of Complaints
    public static void printComplaintTable(List<Complaint> complaints) {
        if (complaints == null || complaints.isEmpty()) {
            System.out.println("⚠️ No complaints to display.");
            return;
        }

        String format = "| %-4s | %-20s | %-7s | %-9s | %-45s |%n";
        String separator = String.format("+%s+", "-".repeat(86));

        System.out.println("\n📋 Complaint Summary Table");
        System.out.println(separator);
        System.out.format(format, "ID", "Department", "UserID", "Status", "Issue (Preview)");
        System.out.println(separator);

        for (Complaint c : complaints) {
            String issuePreview = getIssuePreview(c.getIssue(), 45);
            String statusStr = c.getStatus() ? "Resolved" : "Pending";
            System.out.format(format, c.getId(), c.getDepartment(), c.getUserId(), statusStr, issuePreview);
        }

        System.out.println(separator);
        System.out.println("🔍 Use viewFullIssue(id) to see complete issue text.\n");
    }

    /**
     * Helper method to Print Issue.
     *
     * @param issue     in text
     * @param maxLength to print length in console
     * @return issue with max length
     */
    private static String getIssuePreview(String issue, int maxLength) {
        if (issue == null || issue.isEmpty()) return "(No issue)";
        issue = issue.replaceAll("\\r?\\n", " "); // Flatten line breaks
        return issue.length() <= maxLength ? issue : issue.substring(0, maxLength - 3) + "...";
    }

    //Display for view Full Issue
    public static void viewFullIssue(List<Complaint> complaints, int id) {
        for (Complaint c : complaints) {
            if (c.getId() == id) {
                System.out.println("\n📝 Full Issue for Complaint ID: " + id);
                System.out.println("--------------------------------------------------");
                System.out.println(indentMultilineText(c.getIssue(), "--> "));
                System.out.println("--------------------------------------------------");
                return;
            }
        }
        System.out.println("❌ Complaint with ID " + id + " not found.");
    }

    /**
     * Helper Method to Print Full issue.
     *
     * @param text   issue full text
     * @param indent if it contains new line then arrow will be print after that new line will be printed
     * @return formated issue
     */
    private static String indentMultilineText(String text, String indent) {
        if (text == null || text.isEmpty()) return indent + "(No issue description)";

        //Matches carriage return (\r)
        //Means "optional" — so it matches with or without \r
        //Matches newline (\n)
        String[] lines = text.split("\\r?\\n");

        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(indent).append(line).append("\n");
        }
        return sb.toString();
    }
}
