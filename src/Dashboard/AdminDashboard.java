package Dashboard;

import DataBase.*;
import Model.*;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class AdminDashboard {
    private final Scanner sc;
    private final Connection connection;

    public AdminDashboard(Scanner sc, Connection connection) {
        this.sc = sc;
        this.connection = connection;
    }

    public void showMenu() throws SQLException {
        boolean running = true;

        while (running) {
            System.out.println("\n🔒 Admin Dashboard");
            System.out.println("1. View all customers");
            System.out.println("2. Manage Area");
            System.out.println("3. Bus Services");
            System.out.println("4. Emergency Services");
            System.out.println("5. Metro Services");
            System.out.println("6. Manage Parking Lot");
            System.out.println("7. Manage Route");
            System.out.println("8. Add Schedule");
            System.out.println("9. Manage Station");
            System.out.println("10. Manage Street");
            System.out.println("11. Manage Tourist Places");
            System.out.println("12. Logout");
            System.out.print("Select an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewAllCustomers();
                    break;

                case 2:
                    AreaDAO areaDAO = new AreaDAO();
                    Area area = new Area();
                    List<Area> areas;
                    boolean areaLoop = true;

                    while (areaLoop) {
                        System.out.println("\n📍 Area Management");
                        System.out.println("1. View All Areas");
                        System.out.println("2. Add Area");
                        System.out.println("3. Update Area");
                        System.out.println("4. Delete Area");
                        System.out.println("5. Get Area By ID");
                        System.out.println("6. Get Area In Area");
                        System.out.println("7. Back to Dashboard");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();

                        switch (ch) {
                            case 1:
                                areas = areaDAO.getAllArea();
                                Display.printAreas(areas);
                                break;
                            case 2:
                                if (areaDAO.addArea(sc)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 3:
                                if (areaDAO.updateArea(sc)) System.out.println("✅ Updated successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 4:
                                if (areaDAO.deleteArea(sc)) System.out.println("✅ Deleted successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 5:
                                area = areaDAO.getAreaById(sc);
                                Display.printArea(area);
                                break;
                            case 6:
                                areas = areaDAO.getAreaInArea(sc);
                                Display.printAreas(areas);
                                break;
                            case 7:
                                areaLoop = false;
                                break;
                            default:
                                System.out.println("⚠️ Invalid option!");
                        }
                    }
                    break;

                case 3:
                    BusDAO busDAO = new BusDAO();
                    Bus bus = new Bus();
                    boolean busLoop = true;

                    while (busLoop) {
                        System.out.println("\n🚌 Bus Management");
                        System.out.println("1. View All Buses");
                        System.out.println("2. Add Bus");
                        System.out.println("3. Update Bus Location");
                        System.out.println("4. Update Bus Route");
                        System.out.println("5. Get Bus By ID");
                        System.out.println("6. Back to Dashboard");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();

                        switch (ch) {
                            case 1:
                                List<Bus> buses = busDAO.getAllBuses();
                                Display.printBusList(buses);
                                break;
                            case 2:
                                if (busDAO.addBus(sc)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 3:
                                if (busDAO.updateBusLocation(sc)) System.out.println("✅ Updated successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 4:
                                if (busDAO.updateBusRoute(sc)) System.out.println("✅ Updated successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 5:
                                bus = busDAO.getBusByID(sc);
                                Display.printBus(bus);
                                break;
                            case 6:
                                busLoop = false;
                                break;
                            default:
                                System.out.println("⚠️ Invalid option!");
                        }
                    }
                    break;

                case 4:
                    EmergencyServiceDAO esDAO = new EmergencyServiceDAO();
                    EmergencyService es = new EmergencyService();
                    boolean esLoop = true;

                    while (esLoop) {
                        System.out.println("\n🚨 Emergency Services");
                        System.out.println("1. View All Emergency Services");
                        System.out.println("2. Add Emergency Service");
                        System.out.println("3. Get Emergency By ID");
                        System.out.println("4. Back to Dashboard");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();

                        switch (ch) {
                            case 1:
                                List<EmergencyService> services = esDAO.getAllEmergencyService();
                                Display.printEmergencyServices(services);
                                break;
                            case 2:
                                if (esDAO.addEmergencyService(sc)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 3:
                                es = esDAO.getEmergencyServiceByID(sc);
                                Display.printEmergencyService(es);
                                break;
                            case 4:
                                esLoop = false;
                                break;
                            default:
                                System.out.println("⚠️ Invalid option!");
                        }
                    }
                    break;

                case 5:
                    MetroDAO metroDAO = new MetroDAO();
                    Metro metro = new Metro();
                    boolean metroLoop = true;

                    while (metroLoop) {
                        System.out.println("\n🚇 Metro Management");
                        System.out.println("1. Add Metro");
                        System.out.println("2. View All Metros");
                        System.out.println("3. Update Metro Location");
                        System.out.println("4. Update Metro Route");
                        System.out.println("5. Get Metro By ID");
                        System.out.println("6. Exit to Dashboard");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();
                        switch (ch) {
                            case 1:
                                if (metroDAO.addMetro(sc)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 2:
                                List<Metro> metros = metroDAO.getAllMetros();
                                Display.printMetroList(metros);
                                break;
                            case 3:
                                if (metroDAO.updateMetroLocation(sc)) System.out.println("✅ Updated successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 4:
                                if (metroDAO.updateMetroRoute(sc)) System.out.println("✅ Updated successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 5:
                                metro = metroDAO.getMetroByID(sc);
                                Display.printMetro(metro);
                                break;
                            case 6:
                                System.out.println("🔙 Returning to Admin Dashboard...");
                                metroLoop = false;
                                break;
                            default:
                                System.out.println("⚠️ Invalid input. Please try again.");
                        }
                    }
                    break;

                case 6:
                    ParkingLotDAO parkingDAO = new ParkingLotDAO();
                    boolean ParkingLoop = true;

                    while (ParkingLoop) {
                        System.out.println("\n🅿️ Parking Lot Management");
                        System.out.println("1. View All Parking Lots");
                        System.out.println("2. Add Parking Lot");
                        System.out.println("3. Update Parking Occupancy");
                        System.out.println("4. Exit to Dashboard");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();
                        switch (ch) {
                            case 1:
                                List<ParkingLot> lots = parkingDAO.getAllParkingLots();
                                Display.printParkingLots(lots);
                                break;
                            case 2:
                                if (parkingDAO.addParkingLot(sc)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 3:
                                if (parkingDAO.updateParkingOccupancy(sc)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 4:
                                System.out.println("🔙 Returning to Admin Dashboard...");
                                ParkingLoop = false;
                                break;
                            default:
                                System.out.println("⚠️ Invalid input. Please try again.");
                        }
                    }
                    break;

                case 7:
                    RouteDAO routeDAO = new RouteDAO();
                    boolean routeLoop = true;

                    while (routeLoop) {
                        System.out.println("\n🛣️ Route Management");
                        System.out.println("1. View All Routes");
                        System.out.println("2. Add Route");
                        System.out.println("3. Exit to Dashboard");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();
                        switch (ch) {
                            case 1:
                                List<Route> routes = routeDAO.getAllRoutes();
                                Display.printRoutes(routes);
                                break;
                            case 2:
                                if (routeDAO.addRoute(sc)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 3:
                                System.out.println("🔙 Returning to Admin Dashboard...");
                                routeLoop = false;
                                break;
                            default:
                                System.out.println("⚠️ Invalid input. Please try again.");
                        }
                    }
                    break;

                case 8:
                    ScheduleDAO scheduleDAO = new ScheduleDAO();
                    scheduleDAO.addSchedule(sc);
                    break;

                case 9:
                    StationDAO stationDAO = new StationDAO();
                    Station station = new Station();
                    boolean stationLoop = true;

                    while (stationLoop) {
                        System.out.println("\n🚏 Station Management");
                        System.out.println("1. View All Stops");
                        System.out.println("2. Add Station");
                        System.out.println("3. Get Station By Id");
                        System.out.println("4. Exit to Dashboard");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();
                        switch (ch) {
                            case 1:
                                List<Station> stations = stationDAO.getAllStops();
                                Display.printStations(stations);
                                break;
                            case 2:
                                if (stationDAO.addStation(sc)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 3:
                                station = stationDAO.getStationById(sc);
                                Display.printStation(station);
                                break;
                            case 4:
                                System.out.println("🔙 Returning to Admin Dashboard...");
                                stationLoop = false;
                                break;
                            default:
                                System.out.println("⚠️ Invalid input. Please try again.");
                        }
                    }
                    break;

                case 10:
                    StreetDAO streetDAO = new StreetDAO();
                    boolean streetLoop = true;

                    while (streetLoop) {
                        System.out.println("\n🛣️ Street Management");
                        System.out.println("1. View All Streets");
                        System.out.println("2. Add Street");
                        System.out.println("3. Update Street");
                        System.out.println("4. Delete Street");
                        System.out.println("5. Exit to Dashboard");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();
                        switch (ch) {
                            case 1:
                                List<Street> streets = streetDAO.getAllStreet();
                                Display.printStreets(streets);
                                break;
                            case 2:
                                if (streetDAO.addStreet(sc)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 3:
                                if (streetDAO.updateStreet(sc)) System.out.println("✅ Updated successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 4:
                                if (streetDAO.deleteStreet(sc)) System.out.println("✅ Deleted successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 5:
                                System.out.println("🔙 Returning to Admin Dashboard...");
                                streetLoop = false;
                                break;
                            default:
                                System.out.println("⚠️ Invalid input. Please try again.");
                        }
                    }
                    break;

                case 11:
                    TouristPlaceDAO placeDAO = new TouristPlaceDAO();
                    boolean placeLoop = true;

                    while (placeLoop) {
                        System.out.println("\n🏞️ Tourist Place Management");
                        System.out.println("1. View All Tourist Places");
                        System.out.println("2. Add Tourist Place");
                        System.out.println("3. Exit to Dashboard");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();
                        switch (ch) {
                            case 1:
                                List<TouristPlace> places = placeDAO.displayAllPlaces();
                                Display.printTouristPlaces(places);
                                break;
                            case 2:
                                if (placeDAO.addPlace()) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 3:
                                System.out.println("🔙 Returning to Admin Dashboard...");
                                placeLoop = false;
                                break;
                            default:
                                System.out.println("⚠️ Invalid input. Please try again.");
                        }
                    }
                    break;

                case 12:
                    System.out.println("✅ Logged out successfully.");
                    running = false;
                    break;

                default:
                    System.out.println("⚠️ Invalid option!");
            }
        }
    }

    private void viewAllCustomers() throws SQLException {
        String sql = "SELECT id, username, email, full_name, created_at FROM customers";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n👥 List of All Customers");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("%-5s %-15s %-25s %-20s %-20s%n", "ID", "Username", "Email", "Full Name", "Joined Date");
            System.out.println("--------------------------------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String fullName = rs.getString("full_name");
                Timestamp createdAt = rs.getTimestamp("created_at");

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
}