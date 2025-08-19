package Dashboard;

import DataBase.*;
import DataStructure.FeedbackLinkedList;
import Model.*;
import Validation.InputValidator;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("ClassCanBeRecord")
public class AdminDashboard {
    private final Scanner scanner;

    public AdminDashboard(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMenu() throws SQLException {
        boolean running = true;
        int Choice;
        int choice;

        while (running) {

            Display.showAdminMenu();
            Choice = InputValidator.getChoice(scanner);

            switch (Choice) {
                case 1:
                    Display.viewAllCustomers();
                    break;

                case 2:
                    AreaDAO areaDAO = new AreaDAO();
                    Area area;
                    List<Area> areas;
                    boolean areaLoop = true;

                    while (areaLoop) {
                        System.out.println("\n📍 Area Management");
                        System.out.println("-------------------------------------------------");
                        System.out.printf("%-30s %-30s%n", "1. View All Areas", "4. Delete Area");
                        System.out.printf("%-30s %-30s%n", "2. Add Area", "5. Get Area By ID");
                        System.out.printf("%-30s %-30s%n", "3. Update Area", "6. Get Area In Area");
                        System.out.println("0. Return to Dashboard");
                        System.out.println("-------------------------------------------------");

                        choice = InputValidator.getChoice(scanner);

                        switch (choice) {
                            case 1:
                                areas = areaDAO.getAllArea();
                                Display.printAreas(areas);
                                break;
                            case 2:
                                if (areaDAO.addArea(scanner)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 3:
                                if (areaDAO.updateArea(scanner)) System.out.println("✅ Updated successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 4:
                                if (areaDAO.deleteArea(scanner)) System.out.println("✅ Deleted successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 5:
                                area = areaDAO.getAreaById(scanner);
                                Display.printArea(area);
                                break;
                            case 6:
                                areas = areaDAO.getAreaInArea(scanner);
                                Display.printAreas(areas);
                                break;
                            case 0:
                                System.out.println("🔙 Returning to Admin Dashboard...");
                                areaLoop = false;
                                break;
                            default:
                                System.out.println("⚠️ Invalid option!");
                        }
                    }
                    break;

                case 3:
                    BusDAO busDAO = new BusDAO();
                    Bus bus;
                    boolean busLoop = true;

                    while (busLoop) {
                        System.out.println("\n🚍 Bus Management");
                        System.out.println("-------------------------------------------------");
                        System.out.printf("%-30s %-30s%n", "1. View All Buses", "4. Update Bus Route");
                        System.out.printf("%-30s %-30s%n", "2. Add Bus", "5. Get Bus By ID");
                        System.out.printf("%-30s %-30s%n", "3. Update Bus Location", "");
                        System.out.println("0. Return to Dashboard");
                        System.out.println("-------------------------------------------------");

                        choice = InputValidator.getChoice(scanner);

                        switch (choice) {
                            case 1:
                                List<Bus> buses = busDAO.getAllBuses();
                                Display.printBusList(buses);
                                break;
                            case 2:
                                if (busDAO.addBus(scanner)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 3:
                                if (busDAO.updateBusLocation(scanner)) System.out.println("✅ Updated successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 4:
                                if (busDAO.updateBusRoute(scanner)) System.out.println("✅ Updated successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 5:
                                bus = busDAO.getBusByID(scanner);
                                Display.printBus(bus);
                                break;
                            case 0:
                                System.out.println("🔙 Returning to Admin Dashboard...");
                                busLoop = false;
                                break;
                            default:
                                System.out.println("⚠️ Invalid option!");
                        }
                    }
                    break;

                case 4:
                    EmergencyServiceDAO esDAO = new EmergencyServiceDAO();
                    EmergencyService es;
                    boolean esLoop = true;

                    while (esLoop) {
                        System.out.println("\n🚨 Emergency Service Management");
                        System.out.println("-------------------------------------------------");
                        System.out.printf("%-30s %-30s%n", "1. View All Services", "3. Update Service");
                        System.out.printf("%-30s %-30s%n", "2. Add Service", "4. Get Service By ID");
                        System.out.println("0. Return to Dashboard");
                        System.out.println("-------------------------------------------------");

                        choice = InputValidator.getChoice(scanner);

                        switch (choice) {
                            case 1:
                                List<EmergencyService> services = esDAO.getAllEmergencyService();
                                Display.printEmergencyServices(services);
                                break;
                            case 2:
                                if (esDAO.addEmergencyService(scanner)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 3:
                                if (esDAO.updateEmergencyService(scanner)) System.out.println("✅ Updated successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 4:
                                es = esDAO.getEmergencyServiceByID(scanner);
                                Display.printEmergencyService(es);
                                break;
                            case 0:
                                System.out.println("🔙 Returning to Admin Dashboard...");
                                esLoop = false;
                                break;
                            default:
                                System.out.println("⚠️ Invalid option!");
                        }
                    }
                    break;

                case 5:
                    MetroDAO metroDAO = new MetroDAO();
                    Metro metro;
                    boolean metroLoop = true;

                    while (metroLoop) {
                        System.out.println("\n🚇 Metro Management");
                        System.out.println("-------------------------------------------------");
                        System.out.printf("%-30s %-30s%n", "1. View All Metros", "4. Update Metro Route");
                        System.out.printf("%-30s %-30s%n", "2. Add Metro", "5. Get Metro By ID");
                        System.out.printf("%-30s %-30s%n", "3. Update Metro Location", "");
                        System.out.println("0. Return to Dashboard");
                        System.out.println("-------------------------------------------------");

                        choice = InputValidator.getChoice(scanner);

                        switch (choice) {
                            case 1:
                                List<Metro> metros = metroDAO.getAllMetros();
                                Display.printMetroList(metros);
                                break;
                            case 2:
                                if (metroDAO.addMetro(scanner)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 3:
                                if (metroDAO.updateMetroLocation(scanner)) System.out.println("✅ Updated successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 4:
                                if (metroDAO.updateMetroRoute(scanner)) System.out.println("✅ Updated successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 5:
                                metro = metroDAO.getMetroByID(scanner);
                                Display.printMetro(metro);
                                break;
                            case 0:
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
                        System.out.println("-------------------------------------------------");
                        System.out.printf("%-30s %-30s%n", "1. View All Lots", "3. Update Lot Capacity");
                        System.out.printf("%-30s %-30s%n", "2. Add Lot", "4. Delete Lot");
                        System.out.println("0. Return to Dashboard");
                        System.out.println("-------------------------------------------------");

                        choice = InputValidator.getChoice(scanner);

                        switch (choice) {
                            case 1:
                                List<ParkingLot> lots = parkingDAO.getAllParkingLots();
                                Display.printParkingLots(lots);
                                break;
                            case 2:
                                if (parkingDAO.addParkingLot(scanner)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 3:
                                if (parkingDAO.updateParkingCapacity(scanner)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 4:
                                if (parkingDAO.deleteParkingLot(scanner)) System.out.println("✅ Deleted successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 0:
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
                        System.out.println("-------------------------------------------------");
                        System.out.printf("%-30s %-30s%n", "1. View All Routes", "3. Update Route");
                        System.out.printf("%-30s %-30s%n", "2. Add Route", "4. Delete Route");
                        System.out.println("0. Return to Dashboard");
                        System.out.println("-------------------------------------------------");

                        choice = InputValidator.getChoice(scanner);

                        switch (choice) {
                            case 1:
                                List<Route> routes = routeDAO.getAllRoutes();
                                Display.printRoutes(routes);
                                break;
                            case 2:
                                if (routeDAO.addRoute(scanner)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 3:
                                if (routeDAO.updateRoute(scanner)) System.out.println("✅ Updated successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 4:
                                if (routeDAO.deleteRoute(scanner)) System.out.println("✅ Deleted successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 0:
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
                    boolean scheduleLoop = true;

                    while (scheduleLoop) {
                        System.out.println("\n📅 Schedule Management");
                        System.out.println("-------------------------------------------------");
                        System.out.printf("%-30s %-30s%n", "1. View All Schedules", "3. Update Schedule");
                        System.out.printf("%-30s %-30s%n", "2. Add Schedule", "4. Delete Schedule");
                        System.out.println("0. Return to Dashboard");
                        System.out.println("-------------------------------------------------");


                        choice = InputValidator.getChoice(scanner);

                        switch (choice) {
                            case 1:
                                List<Schedule> schedules = scheduleDAO.getAllSchedule();
                                Display.printScheduleTable(schedules);
                                break;
                            case 2:
                                if (scheduleDAO.addSchedule(scanner)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 3:
                                if (scheduleDAO.updateSchedule(scanner)) System.out.println("✅ Updated successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 4:
                                if (scheduleDAO.deleteSchedule(scanner)) System.out.println("✅ Deleted successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 0:
                                System.out.println("🔙 Returning to Admin Dashboard...");
                                scheduleLoop = false;
                                break;
                        }
                    }
                    break;

                case 9:
                    StationDAO stationDAO = new StationDAO();
                    Station station;
                    boolean stationLoop = true;

                    while (stationLoop) {
                        System.out.println("\n🚉 Station Management");
                        System.out.println("-------------------------------------------------");
                        System.out.printf("%-30s %-30s%n", "1. View All Stations", "3. Update Station");
                        System.out.printf("%-30s %-30s%n", "2. Add Station", "4. Get Station By ID");
                        System.out.println("0. Return to Dashboard");
                        System.out.println("-------------------------------------------------");

                        choice = InputValidator.getChoice(scanner);

                        switch (choice) {
                            case 1:
                                List<Station> stations = stationDAO.getAllStops();
                                Display.printStations(stations);
                                break;
                            case 2:
                                if (stationDAO.addStation(scanner)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 3:
                                if (stationDAO.updateStation(scanner)) System.out.println("✅ Updated successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 4:
                                station = stationDAO.getStationById(scanner);
                                Display.printStation(station);
                                break;
                            case 0:
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
                    Street street;
                    boolean streetLoop = true;

                    while (streetLoop) {
                        System.out.println("\n🛣️ Street Management");
                        System.out.println("-------------------------------------------------");
                        System.out.printf("%-30s %-30s%n", "1. View All Streets", "4. Delete Street");
                        System.out.printf("%-30s %-30s%n", "2. Add Street", "5. Get Street By ID");
                        System.out.printf("%-30s %-30s%n", "3. Update Street", "6. Get Streets By Area");
                        System.out.println("0. Return to Dashboard");
                        System.out.println("-------------------------------------------------");

                        choice = InputValidator.getChoice(scanner);

                        switch (choice) {
                            case 1:
                                List<Street> streets = streetDAO.getAllStreet();
                                Display.printStreets(streets);
                                break;
                            case 2:
                                if (streetDAO.addStreet(scanner)) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 3:
                                if (streetDAO.updateStreet(scanner)) System.out.println("✅ Updated successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 4:
                                if (streetDAO.deleteStreet(scanner)) System.out.println("✅ Deleted successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 5:
                                street = streetDAO.getStreetById(scanner);
                                Display.printStreet(street);
                                break;
                            case 6:
                                street = streetDAO.getStreetByAreaId(scanner);
                                Display.printStreet(street);
                                break;
                            case 0:
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
                    List<TouristPlace> places;
                    boolean placeLoop = true;

                    while (placeLoop) {
                        System.out.println("\n🗺️ Tourist Place Management");
                        System.out.println("-------------------------------------------------");
                        System.out.println("1. View All Places");
                        System.out.println("2. Add Place");
                        System.out.println("3. Get Places By Area");
                        System.out.println("0. Return to Dashboard");
                        System.out.println("-------------------------------------------------");

                        choice = InputValidator.getChoice(scanner);

                        switch (choice) {
                            case 1:
                                places = placeDAO.displayAllPlaces();
                                Display.printTouristPlaces(places);
                                break;
                            case 2:
                                if (placeDAO.addPlace()) System.out.println("✅ Added successfully");
                                else System.out.println("❌ Failed");
                                break;
                            case 3:
                                places = placeDAO.getPlacesByAreaId(scanner);
                                Display.printTouristPlaces(places);
                            case 0:
                                System.out.println("🔙 Returning to Admin Dashboard...");
                                placeLoop = false;
                                break;
                            default:
                                System.out.println("⚠️ Invalid input. Please try again.");
                        }
                    }
                    break;

                case 12:
                    ComplaintDAO complaintDAO = new ComplaintDAO();
                    List<Complaint> complaints;
                    boolean complaintLoop = true;

                    while (complaintLoop) {
                        System.out.println("\n📝 Complaint Management");
                        System.out.println("-------------------------------------------------");
                        System.out.println("1. View All Complaint ");
                        System.out.println("2. View Full Issue (If Not Displayed In Table Properly)");
                        System.out.println("3. Resolve Next Complaint");
                        System.out.println("0. Return to Dashboard");
                        System.out.println("-------------------------------------------------");

                        choice = InputValidator.getChoice(scanner);

                        switch (choice) {
                            case 1:
                                complaints = complaintDAO.getAllComplaint();
                                Display.printComplaintTable(complaints);
                                break;
                            case 2:
                                complaints = complaintDAO.getAllComplaint();
                                System.out.print("Enter Complaint Id: ");
                                Display.viewFullIssue(complaints, scanner.nextInt());
                                break;
                            case 3:
                                complaintDAO.resolveNextComplaint();
                                break;
                            case 0:
                                System.out.println("🔙 Returning to Admin Dashboard...");
                                complaintLoop = false;
                                break;
                            default:
                                System.out.println("⚠️ Invalid input. Please try again.");
                        }
                    }
                    break;

                case 13:
                    FeedbackDAO fbDAO = new FeedbackDAO();
                    boolean fbLoop = true;

                    while (fbLoop) {
                        FeedbackLinkedList fbList  = fbDAO.loadFeedbacksIntoLinkedList();

                        System.out.println("\n💬 Feedback Management");
                        System.out.println("-------------------------------------------------");
                        System.out.println("1. View Feedback");
                        System.out.println("2. View Feedback By User ID");
                        System.out.println("3. Review Latest Feedbacks");
                        System.out.println("0. Return to Dashboard");
                        System.out.println("-------------------------------------------------");

                        choice = InputValidator.getChoice(scanner);

                        switch (choice) {
                            case 1:
                                fbList.displayFeedbacks();
                                break;
                            case 2:
                                System.out.print("Enter User Id: ");
                                fbList.searchByUserId(scanner.nextInt());
                                break;
                            case 3:
                                List<Feedback> feedbacks = fbDAO.reviewLatestFeedback();
                                Display.printFeedbacks(feedbacks);
                            case 0:
                                System.out.println("🔙 Returning to Admin Dashboard...");
                                fbLoop = false;
                                break;
                            default:
                                System.out.println("⚠️ Invalid input. Please try again.");
                        }
                    }
                    break;

                case 0:
                    System.out.println("✅ Logged out successfully.");
                    running = false;
                    break;

                default:
                    System.out.println("⚠️ Invalid option!");
            }
        }
    }
}