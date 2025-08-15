package Dashboard;

import DataBase.*;
import DataStructure.AreaEmergencyDispatcher;
import Model.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CustomerDashboard {
    private final Scanner sc;

    public CustomerDashboard(Scanner sc) {
        this.sc = sc;
    }
    public void showMenu() throws SQLException {
        boolean running = true;

        while (running) {
            System.out.println("\n🧑‍💼 Customer Dashboard");
            System.out.println("-------------------------------------------------");
            System.out.printf("%-30s %-30s%n", "1. Travelling Routes & Schedules", "5. Parking Lot");
            System.out.printf("%-30s %-30s%n", "2. Emergency Services", "6. Submit Feedback");
            System.out.printf("%-30s %-30s%n", "3. Book Tickets", "7. File a Complaint");
            System.out.printf("%-30s %-30s%n", "4. Tourist Places", "8. Logout");
            System.out.println("-------------------------------------------------");
            System.out.print("Select an option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    RouteDAO routeDAO = new RouteDAO();
                    Route route;
                    Schedule schedule;
                    ScheduleDAO scheduleDAO = new ScheduleDAO();
                    boolean rsLoop = true;

                    while(rsLoop) {
                        System.out.println("\n🛣️ Routes & Schedules");
                        System.out.println("1. View All Routes");
                        System.out.println("2. View Route by ID");
                        System.out.println("3. View Schedule by Route ID");
                        System.out.println("4. Back");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();
                        switch (ch) {
                            case 1:
                                List<Route> routes = routeDAO.getAllRoutes();
                                Display.printRoutes(routes);
                                break;
                            case 2:
                                route = routeDAO.getRouteById(sc);
                                Display.printRoute(route);
                                break;
                            case 3:
                                schedule = scheduleDAO.getScheduleByRouteId(sc);
                                Display.printSchedule(schedule);
                                break;
                            case 4:
                                System.out.println("🔙 Returning to Customer Dashboard...");
                                rsLoop = false;
                                break;
                            default:
                                System.out.println("⚠️ Invalid input.");
                        }
                    }
                    break;

                case 2:
                    EmergencyServiceDAO e = new EmergencyServiceDAO();
                    EmergencyService es;
                    AreaEmergencyDispatcher dispatcher;
                    boolean esLoop = true;

                    while(esLoop) {
                        System.out.println("\n🚨 Emergency Services");
                        System.out.println("1. View All Services");
                        System.out.println("2. View Services by Type");
                        System.out.println("3. Call Emergency Service");
                        System.out.println("4. Back");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();
                        switch (ch) {
                            case 1:
                                List<EmergencyService> services = e.getAllEmergencyService();
                                Display.printEmergencyServices(services);
                                break;
                            case 2:
                                es = e.getEmergencyServiceByType(sc);
                                Display.printEmergencyService(es);
                                break;
                            case 3:
                                dispatcher = new AreaEmergencyDispatcher(e.getAllEmergencyService());
                                dispatcher.dispatchEmergency(sc);
                                break;
                            case 4:
                                System.out.println("🔙 Returning to Customer Dashboard...");
                                esLoop = false;
                                break;
                            default:
                                System.out.println("⚠️ Invalid input.");
                        }
                    }
                    break;

                case 3:
                    TicketDAO t = new TicketDAO();
                    boolean ticketLoop = true;

                    while (ticketLoop) {
                        System.out.println("\n🎟️ Ticket Booking");
                        System.out.println("1. Book Ticket");
                        System.out.println("2. Search Ticket");
                        System.out.println("3. Back");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();
                        switch (ch) {
                            case 1:
                                t.addTicket(sc);
                                break;
                            case 2:
                                t.searchTickets(sc);
                                break;
                            case 3:
                                System.out.println("🔙 Returning to Customer Dashboard...");
                                ticketLoop = false;
                            default:
                                System.out.println("⚠️ Invalid input.");
                        }
                    }
                    break;

                case 4:
                    TouristPlaceDAO tp = new TouristPlaceDAO();
                    List<TouristPlace> places;
                    boolean placeLoop = true;

                    while (placeLoop) {
                        System.out.println("\n🏞️ Tourist Places");
                        System.out.println("1. View All Places");
                        System.out.println("2. View Top Rated Places");
                        System.out.println("3. View Places by Category");
                        System.out.println("4. Give Feedback");
                        System.out.println("5. Back");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();
                        switch (ch) {
                            case 1:
                                places = tp.displayAllPlaces();
                                Display.printTouristPlaces(places);
                                break;
                            case 2:
                                places = tp.displayTopRatedPlaces(sc);
                                Display.printTouristPlaces(places);
                                break;
                            case 3:
                                places = tp.displayPlacesByCategory(sc);
                                Display.printTouristPlaces(places);
                                break;
                            case 4:
                                tp.applyFeedback(sc);
                                break;
                            case 5:
                                System.out.println("🔙 Returning to Customer Dashboard...");
                                placeLoop = false;
                            default:
                                System.out.println("⚠️ Invalid input.");
                        }
                    }
                    break;

                case 5:
                    ParkingLotDAO po = new ParkingLotDAO();
                    ParkingLot p = new ParkingLot();
                    boolean parkingLoop = true;

                    while (parkingLoop) {
                        System.out.println("\n🅿️ Parking Lot");
                        System.out.println("1. View Available Lots");
                        System.out.println("2. View by Area ID");
                        System.out.println("3. Book Slot");
                        System.out.println("4. Back");

                        System.out.print("Enter choice: ");
                        int ch = sc.nextInt();
                        switch (ch) {
                            case 1:
                                List<ParkingLot> lots = po.getAvailableParkingLots();
                                Display.printParkingLots(lots);
                                break;
                            case 2:
                                p = po.getParkingLotByAreaId(sc);
                                Display.printParkingLot(p);
                                break;
                            case 3:
                                p.bookSlot(po);
                                break;
                            case 4:
                                System.out.println("🔙 Returning to Customer Dashboard...");
                                parkingLoop = false;
                            default:
                                System.out.println("⚠️ Invalid input.");
                        }
                    }
                    break;

                case 6:
                    if (new FeedbackDAO().submitFeedback(sc)) System.out.println("✅ Added successfully");
                    else System.out.println("❌ Failed");
                    break;

                case 7:
                    if (new ComplaintDAO().fileComplaint(sc)) System.out.println("✅ Added successfully");
                    else System.out.println("❌ Failed");
                    break;

                case 8:
                    System.out.println("✅ Logged out successfully.");
                    running = false;
                    break;

                default:
                    System.out.println("⚠️ Invalid option! Please try again.");
            }
        }
    }

}
