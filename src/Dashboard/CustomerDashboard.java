package Dashboard;

import DataBase.*;
import DataStructure.AreaEmergencyDispatcher;
import Model.*;
import Validation.InputValidator;
import Display.DisplayUtil;

import java.sql.SQLException;

import java.util.List;
import java.util.Scanner;

@SuppressWarnings("ClassCanBeRecord")
public class CustomerDashboard {
    private final Scanner scanner;

    public CustomerDashboard(Scanner scanner) {
        this.scanner = scanner;
    }
    public void showMenu() throws SQLException {
        boolean running = true;
        int Choice;
        int choice;

        while (running) {

            showCustomerMenu();
            Choice = InputValidator.getChoice(scanner);

            switch (Choice) {
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

                        choice = InputValidator.getChoice(scanner);

                        switch (choice) {
                            case 1:
                                List<Route> routes = routeDAO.getAllRoutes();
                                DisplayUtil.printList(routes, "Route");
                                break;
                            case 2:
                                route = routeDAO.getRouteById(scanner);
                                DisplayUtil.printDetails(route, "Route");
                                break;
                            case 3:
                                schedule = scheduleDAO.getScheduleByRouteId(scanner);
                                DisplayUtil.printDetails(schedule, "Schedule");
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

                        choice = InputValidator.getChoice(scanner);

                        switch (choice) {
                            case 1:
                                services = e.getAllEmergencyService();
                                DisplayUtil.printList(services, "Emergency");
                                break;
                            case 2:
                                services = e.getEmergencyServiceByType(scanner);
                                DisplayUtil.printList(services, "Emergency");
                                break;
                            case 3:
                                dispatcher = new AreaEmergencyDispatcher(e.getAllEmergencyService());
                                if(dispatcher.dispatchEmergency(scanner)) System.out.println("✅ Vehicle Allot Successfully.");
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

                        choice = InputValidator.getChoice(scanner);

                        switch (choice) {
                            case 1:
                                List<Station> stations = stationDAO.getStopsByAreaId(scanner);
                                DisplayUtil.printList(stations, "Station");
                                break;
                            case 2:
                                if (ticketDAO.addTicket(scanner)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 3:
                                ticketDAO.searchTickets(scanner);
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
                    TouristPlaceDAO tpDAO = new TouristPlaceDAO();
                    List<TouristPlace> places;
                    boolean placeLoop = true;

                    while (placeLoop) {
                        System.out.println("\n🏞️ Tourist Places");
                        System.out.println("-------------------------------------------------");
                        System.out.printf("%-30s %-30s%n", "1. View All Places", "3. View Places by Category");
                        System.out.printf("%-30s %-30s%n", "2. View Top Rated Places", "4. Give Feedback");
                        System.out.println("0. Return to Dashboard");
                        System.out.println("-------------------------------------------------");

                        choice = InputValidator.getChoice(scanner);

                        switch (choice) {
                            case 1:
                                places = tpDAO.displayAllPlaces();
                                DisplayUtil.printList(places, "Place");
                                break;
                            case 2:
                                places = tpDAO.displayTopRatedPlaces(scanner);
                                DisplayUtil.printList(places, "Place");
                                break;
                            case 3:
                                List<String> categories = tpDAO.getAllCategories();
                                TouristPlace.printCategories(categories);
                                places = tpDAO.displayPlacesByCategory(scanner);
                                DisplayUtil.printList(places, "Place");
                                break;
                            case 4:
                                tpDAO.applyFeedback(scanner);
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
                    List<ParkingLot> lots;
                    boolean parkingLoop = true;

                    while (parkingLoop) {
                        System.out.println("\n🅿️ Parking Lot");
                        System.out.println("-------------------------------------------------");
                        System.out.println("1. View Available Lots");
                        System.out.println("2. View by Area ID");
                        System.out.println("3. Book Slot");
                        System.out.println("0. Return to Dashboard");
                        System.out.println("-------------------------------------------------");

                        choice = InputValidator.getChoice(scanner);

                        switch (choice) {
                            case 1:
                                lots = lotDAO.getAvailableParkingLots();
                                DisplayUtil.printList(lots, "Parking");
                                break;
                            case 2:
                                lots = lotDAO.getParkingLotByAreaIdList(scanner);
                                DisplayUtil.printList(lots, "Parking");
                                break;
                            case 3:
                                List<ParkingLot> areaLots = lotDAO.getParkingLotByAreaIdList(scanner);

                                if (areaLots.isEmpty()) {
                                    System.out.println("❌ No parking lots found for the given Area ID.");
                                } else if (areaLots.size() == 1) {
                                    ParkingLot singleLot = areaLots.getFirst();
                                    singleLot.bookSlot();
                                } else {
                                    System.out.println("\nMultiple parking lots found in this area:");
                                    DisplayUtil.printList(areaLots, "Parking");

                                    int selectedId = InputValidator.getValidInt(scanner, "Enter Parking Lot ID to book: ");
                                    ParkingLot selectedLot = null;

                                    for (ParkingLot lot : areaLots) {
                                        if (lot.getId() == selectedId) {
                                            selectedLot = lot;
                                            break;
                                        }
                                    }

                                    if (selectedLot != null) {
                                        selectedLot.bookSlot();
                                    } else {
                                        System.out.println("❌ Invalid Parking Lot ID.");
                                    }
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
                    int pid = InputValidator.getValidInt(scanner, "Enter Place Id: ");
                    TouristPlaceDAO placeDAO = new TouristPlaceDAO();
                    if (!placeDAO.doesPlaceExist(pid)) {
                        System.out.println("❌ Invalid Place ID. No tourist place found with ID: " + pid);
                        break;
                    }
                    if (new FeedbackDAO().submitFeedback(scanner, pid)) System.out.println("✅ Added successfully");
                    else System.out.println("❌ Failed");
                    break;

                case 7:
                    if (new ComplaintDAO().fileComplaint(scanner)) System.out.println("✅ Added successfully");
                    else System.out.println("❌ Failed");
                    break;

                case 0:
                    System.out.println("✅ Logged out successfully.");
                    Session.clear();
                    running = false;
                    break;

                default:
                    System.out.println("⚠️ Invalid option! Please try again.");
            }
        }
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
}
