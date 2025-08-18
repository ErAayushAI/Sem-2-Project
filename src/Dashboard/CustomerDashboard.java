package Dashboard;

import DataBase.*;
import DataStructure.AreaEmergencyDispatcher;
import Model.*;
import Validation.InputValidator;

import java.sql.SQLException;

import java.util.List;
import java.util.Scanner;

@SuppressWarnings("ClassCanBeRecord")
public class CustomerDashboard {
    private final Scanner sc;

    public CustomerDashboard(Scanner sc) {
        this.sc = sc;
    }
    public void showMenu() throws SQLException {
        boolean running = true;
        int choice;int ch;

        while (running) {

            Display.showCustomerMenu();
            choice = InputValidator.getChoice(sc);

            switch (choice) {
                case 1:
                    RouteDAO routeDAO = new RouteDAO();
                    Route route;
                    Schedule schedule;
                    ScheduleDAO scheduleDAO = new ScheduleDAO();
                    boolean rsLoop = true;

                    while(rsLoop) {
                        System.out.println("\n🛣️ Routes & Schedules");
                        System.out.println("-------------------------------------------------");
                        System.out.println("1. View All Routes");
                        System.out.println("2. View Route by ID");
                        System.out.println("3. View Schedule by Route ID");
                        System.out.println("0. Return to Dashboard");
                        System.out.println("-------------------------------------------------");

                        ch = InputValidator.getChoice(sc);

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
                            case 0:
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
                    List<EmergencyService> services;
                    AreaEmergencyDispatcher dispatcher;
                    boolean esLoop = true;

                    while(esLoop) {
                        System.out.println("\n🚨 Emergency Services");
                        System.out.println("-------------------------------------------------");
                        System.out.println("1. View All Services");
                        System.out.println("2. View Services by Type");
                        System.out.println("3. Call Emergency Service");
                        System.out.println("0. Return to Dashboard");
                        System.out.println("-------------------------------------------------");

                        ch = InputValidator.getChoice(sc);

                        switch (ch) {
                            case 1:
                                services = e.getAllEmergencyService();
                                Display.printEmergencyServices(services);
                                break;
                            case 2:
                                services = e.getEmergencyServiceByType(sc);
                                Display.printEmergencyServices(services);
                                break;
                            case 3:
                                dispatcher = new AreaEmergencyDispatcher(e.getAllEmergencyService());
                                if(dispatcher.dispatchEmergency(sc)) System.out.println("✅ Vehicle Allot Successfully.");
                                else System.out.println("❌ Failed");
                                break;
                            case 0:
                                System.out.println("🔙 Returning to Customer Dashboard...");
                                esLoop = false;
                                break;
                            default:
                                System.out.println("⚠️ Invalid input.");
                        }
                    }
                    break;

                case 3:
                    StationDAO stationDAO = new StationDAO();
                    TicketDAO ticketDAO = new TicketDAO();
                    boolean ticketLoop = true;

                    while (ticketLoop) {
                        System.out.println("\n🎟️ Ticket Booking & View Stations");
                        System.out.println("-------------------------------------------------");
                        System.out.println("1. View Stations By Area Id");
                        System.out.println("2. Book Ticket");
                        System.out.println("3. Search Ticket");
                        System.out.println("0. Return to Dashboard");
                        System.out.println("-------------------------------------------------");

                        ch = InputValidator.getChoice(sc);

                        switch (ch) {
                            case 1:
                                List<Station> station = stationDAO.getStopsByAreaId(sc);
                                Display.printStations(station);
                                break;
                            case 2:
                                if (ticketDAO.addTicket(sc)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 3:
                                ticketDAO.searchTickets(sc);
                                break;
                            case 0:
                                System.out.println("🔙 Returning to Customer Dashboard...");
                                ticketLoop = false;
                                break;
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
                        System.out.println("-------------------------------------------------");
                        System.out.printf("%-30s %-30s%n", "1. View All Places", "3. View Places by Category");
                        System.out.printf("%-30s %-30s%n", "2. View Top Rated Places", "4. Give Feedback");
                        System.out.println("0. Return to Dashboard");
                        System.out.println("-------------------------------------------------");

                        ch = InputValidator.getChoice(sc);

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
                            case 0:
                                System.out.println("🔙 Returning to Customer Dashboard...");
                                placeLoop = false;
                                break;
                            default:
                                System.out.println("⚠️ Invalid input.");
                        }
                    }
                    break;

                case 5:
                    ParkingLotDAO lotDAO = new ParkingLotDAO();
                    ParkingLot lot;
                    boolean parkingLoop = true;

                    while (parkingLoop) {
                        System.out.println("\n🅿️ Parking Lot");
                        System.out.println("-------------------------------------------------");
                        System.out.println("1. View Available Lots");
                        System.out.println("2. View by Area ID");
                        System.out.println("3. Book Slot");
                        System.out.println("0. Return to Dashboard");
                        System.out.println("-------------------------------------------------");

                        ch = InputValidator.getChoice(sc);

                        switch (ch) {
                            case 1:
                                List<ParkingLot> lots = lotDAO.getAvailableParkingLots();
                                Display.printParkingLots(lots);
                                break;
                            case 2:
                                lot = lotDAO.getParkingLotByAreaId(sc);
                                Display.printParkingLot(lot);
                                break;
                            case 3:
                                ParkingLot selectedLot = lotDAO.getParkingLotByAreaId(sc);
                                if (selectedLot != null) {
                                    selectedLot.bookSlot(lotDAO);
                                } else {
                                    System.out.println("❌ No parking lot found for given Area ID.");
                                }
                                break;
                            case 0:
                                System.out.println("🔙 Returning to Customer Dashboard...");
                                parkingLoop = false;
                                break;
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

                case 0:
                    System.out.println("✅ Logged out successfully.");
                    running = false;
                    break;

                default:
                    System.out.println("⚠️ Invalid option! Please try again.");
            }
        }
    }
}
