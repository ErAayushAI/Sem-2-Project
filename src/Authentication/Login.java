package Authentication;


import DataBase.*;
//import DataBase.DataBaseManager;
import DataStructure.AreaEmergencyDispatcher;
import Model.EmergencyService;
import Model.ParkingLot;
//import org.w3c.dom.xpath.XPathResult;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Login {
    private Connection connection;

    public Login() {
        try {
            connection = DataBaseManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   static Scanner sc=new Scanner(System.in);
        public void loginMenu() throws SQLException {
            int choice;
            System.out.println("Welcome to the Login System");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Customer Registration");
            System.out.print("Select an option: ");
            while (true) {
                try {
                    System.out.print("Enter your choice (integer only): ");
                    choice = sc.nextInt();   // may throw exception
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("❌ Invalid input! Please enter a number.");
                    sc.nextLine();
                }
            }
                sc.nextLine();//consume new line
            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    customerLogin();
                    break;
                case 3:
                    customerRegistration();
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }

    public void adminLogin() throws SQLException {
        int attempts = 0;
        int MAX_ATTEMPTS = 3;
        int r=0;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter admin username: ");
            String username = sc.nextLine();

            System.out.print("Enter password: ");
            String password = sc.nextLine();

            String sql = "SELECT password FROM admin WHERE username = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("password");

                if (password.equals(dbPassword)) {
                    System.out.println("Admin login successful!");
                    r=1;
                    break;
                } else {
                    attempts++;
                    System.out.println("Invalid password! Attempts remaining: " + (MAX_ATTEMPTS - attempts));
                }
            } else {
                System.out.println("Admin not found!");
                attempts++;
                System.out.println("Attempts remaining: " + (MAX_ATTEMPTS - attempts));
            }
        }
        if(r==1)
        {
            adminDashboard();
        }
        else {
            System.out.println("Maximum login attempts reached. Please try again later.");
            loginMenu();
        }
    }

    public void customerLogin() throws SQLException {
        int attempts = 0;
        int MAX_ATTEMPTS = 3;
        int r=0;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter customer username: ");
            String username = sc.nextLine();

            System.out.print("Enter password: ");
            String password = sc.nextLine();

            String sql = "select Password, FullName from customer where UserName = ?";
            PreparedStatement stmt =connection.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("password");
                String fullName = rs.getString("fullName");

                if (password.equals(dbPassword)) {
                    System.out.println("Customer login successful!");
                    System.out.println("Welcome, " + fullName + "!");
                    r=1;
                    break;
                } else {
                    attempts++;
                    System.out.println("Invalid password! Attempts remaining: " + (MAX_ATTEMPTS - attempts));
                }
            } else {
                System.out.println("Customer not found!");
                attempts++;
                System.out.println("Attempts remaining: " + (MAX_ATTEMPTS - attempts));
            }
        }
        if(r==1)
        {
            customerDashboard();
        }
        else {
            System.out.println("Maximum login attempts reached. Please try again later.");
            loginMenu();
        }
    }

    public void customerRegistration() throws SQLException {
        System.out.println("Customer Registration");

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter full name: ");
        String fullName = sc.nextLine();

        // Check if username or email already exists
        String checkSql = "SELECT id FROM customer WHERE username = ? OR email = ?";
        PreparedStatement checkStmt = connection.prepareStatement(checkSql);
        checkStmt.setString(1, username);
        checkStmt.setString(2, email);

        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            System.out.println("Username or email already exists!");
            return;
        }

        // Insert new customer (password stored in plain text - not recommended for production)
        String insertSql = "INSERT INTO customer (username, password, email, fullName) VALUES (?, ?, ?, ?)";
        PreparedStatement insertStmt = connection.prepareStatement(insertSql);
        insertStmt.setString(1, username);
        insertStmt.setString(2, password);
        insertStmt.setString(3, email);
        insertStmt.setString(4, fullName);

        int rowsAffected = insertStmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Registration successful! You can now login.");
        } else {
            System.out.println("Registration failed!");
        }
    }

        void adminDashboard() throws SQLException {
            int choice;
            boolean b;int ch5;
            System.out.println("\nAdmin Dashboard");
            System.out.println("1. View all customers");
            System.out.println("2. Area");
            System.out.println("3. Bus");
            System.out.println("4. EmergencyServices");
            System.out.println("5. Metro");
            System.out.println("6. ParkingLot");
            System.out.println("7. Route");
            System.out.println("8. add Schedule");
            System.out.println("9. Station");
            System.out.println("10. Street");
            System.out.println("11. Tourist place");
            System.out.println("12. Logout");
            System.out.print("Select an option: ");

            while (true) {
                try {
                    System.out.print("Enter your choice (integer only): ");
                    choice = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("❌ Invalid input! Please enter a number.");
                    sc.nextLine();
                }
            }

            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAllCustomers();
                    adminDashboard(); // Show menu again
                    break;
                case 2:
                    DataBase.AreaDAO a=new AreaDAO();
                    System.out.println("1. To add Area");
                    System.out.println("2. To Update Area");
                    System.out.println("3. To delete Area");
                    System.out.println("4. To exit");
                    System.out.println("Enter choice: ");
                    while (true) {
                        try {
                            System.out.print("Enter your choice (integer only): ");
                            ch5 = sc.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("❌ Invalid input! Please enter a number.");
                            sc.nextLine();
                        }
                    }
                    switch(ch5) {
                        case 1:
                            System.out.println("Enter area id: ");
                            b = a.addArea(sc);
                            if (b) {
                                System.out.println("Added successfully");
                            } else {
                                System.out.println("Failed");
                            }
                            break;
                        case 2:
                            b = a.updateArea(sc);
                            if (b) {
                                System.out.println("updated successfully");
                            } else {
                                System.out.println("Failed");
                            }
                            break;
                        case 3:
                            b = a.deleteArea(sc);
                            if (b) {
                                System.out.println("Deleted successfully");
                            } else {
                                System.out.println("Failed");
                            }
                            break;
                        case 4:
                            System.out.println("Exiting.....");
                            adminDashboard();
                            break;
                        default:
                            System.out.println("Invalid option!");
                            break;
                    }
                    break;
                case 3:
                    DataBase.BusDAO a3=new BusDAO();
                    System.out.println("Bus System");
                    System.out.println("1.Add bus");
                    System.out.println("2.Update bus Location");
                    System.out.println("3.Update bus Route");
                    System.out.println("4.Exit");
                    while (true) {
                        try {
                            System.out.print("Enter your choice (integer only): ");
                            ch5 = sc.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("❌ Invalid input! Please enter a number.");
                            sc.nextLine();
                        }
                    }
                    switch (ch5)
                    {
                        case 1:
                             b = a3.addBus(sc);
                            if (b) {
                                System.out.println("Added successfully");
                            }
                            else {
                                System.out.println("Failed");
                            }
                            break;
                        case 2:
                             b=a3.updateBusLocation(sc);
                            if (b) {
                                System.out.println("updated successfully");
                            }
                            else {
                                System.out.println("Failed");
                            }
                            break;
                        case 3:
                            b= a3.updateBusRoute(sc);
                            if (b) {
                                System.out.println("Updated successfully");
                            }
                            else {
                                System.out.println("Failed");
                            }
                            break;
                        case 4:
                            System.out.println("Exiting.....");
                            adminDashboard();
                            break;
                        default:
                            System.out.println("Invalid option!");
                            break;
                    }
                    break;
                case 4:
                    DataBase.EmergencyServiceDAO a4=new EmergencyServiceDAO();
                    System.out.println("1. To add EmergencyServices");
                    System.out.println("2.display all EmergencyServices");
                    System.out.println("3. To exit");
                    System.out.println("Enter choice: ");
                    while (true) {
                        try {
                            System.out.print("Enter your choice (integer only): ");
                            ch5 = sc.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("❌ Invalid input! Please enter a number.");
                            sc.nextLine();
                        }
                    }
                    switch(ch5) {
                        case 1: //add
                             b = a4.addEmergencyService(sc);
                            if (b) {
                                System.out.println("Added successfully");
                            } else {
                                System.out.println("Failed");
                            }
                            break;
                        case 2: //display
                            ArrayList<EmergencyService> r = (ArrayList<EmergencyService>) a4.getAllEmergencyService();
                            break;
                        case 3:
                            System.out.println("Exiting.....");
                            adminDashboard();
                            break;
                        default:
                            System.out.println("Invalid option!");
                            break;
                    }
                    break;
                case 5:
                    MetroDAO m=new MetroDAO();
                    System.out.println("1. Add metro");
                    System.out.println("2. Get all metro");
                    System.out.println("3. update metro location");
                    System.out.println("4. update metro route");
                    System.out.println("5. to Exit");
                    while (true) {
                        try {
                            System.out.print("Enter your choice (integer only): ");
                            ch5 = sc.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("❌ Invalid input! Please enter a number.");
                            sc.nextLine();
                        }
                    }
                    switch(ch5)
                    {
                        case 1: m.addMetro(sc);break;
                        case 2: m.getAllMetros();break;
                        case 3: m.updateMetroLocation(sc);break;
                        case 4: m.updateMetroRoute(sc);break;
                        case 5:
                            System.out.println("Exiting.....");
                            adminDashboard();
                            break;
                        default:
                            System.out.println("Enter valid input");
                    }
                    break;

                case 6:
                    ParkingLotDAO p=new ParkingLotDAO();
                    System.out.println("1. get all parking lots");
                    System.out.println("2. to add parking lot");
                    System.out.println("3. to update parking occupancy");
                    System.out.println("4. to exit");
                    while (true) {
                        try {
                            System.out.print("Enter your choice (integer only): ");
                            ch5 = sc.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("❌ Invalid input! Please enter a number.");
                            sc.nextLine();
                        }
                    }
                    switch(ch5)
                    {
                        case 1: p.getAllParkingLots();break;
                        case 2: p.addParkingLot(sc);break;
                        case 3: p.updateParkingCapacity(sc);break;
                        case 4:
                            System.out.println("Exiting.....");
                            adminDashboard();
                            break;
                        default:
                            System.out.println("Enter valid input");
                    }
                    break;

                case 7:
                    RouteDAO r=new RouteDAO();
                    System.out.println("1. get all routes");
                    System.out.println("2. add a route");
                    System.out.println("2. to exit");
                    while (true) {
                        try {
                            System.out.print("Enter your choice (integer only): ");
                            ch5 = sc.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("❌ Invalid input! Please enter a number.");
                            sc.nextLine();
                        }
                    }
                    switch (ch5)
                    {
                        case 1: r.getAllRoutes();break;
                        case 2: r.addRoute(sc);break;
                        case 3:
                            System.out.println("Exiting.....");
                            adminDashboard();
                            break;
                        default:
                            System.out.println("Enter valid input");
                    }
                    break;

                case 8:
                    ScheduleDAO s=new ScheduleDAO();
                    s.addSchedule(sc);
                    adminDashboard();
                    break;

                case 9:
                    StationDAO st=new StationDAO();
                    System.out.println("1. get all stops");
                    System.out.println("2. add station");
                    System.out.println("3. to exit");
                    while (true) {
                        try {
                            System.out.print("Enter your choice (integer only): ");
                            ch5 = sc.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("❌ Invalid input! Please enter a number.");
                            sc.nextLine();
                        }
                    }
                    switch(ch5)
                    {
                        case 1: st.getAllStops();break;
                        case 2: st.addStation(sc); break;
                        case 3:
                            System.out.println("Exiting......");
                            adminDashboard();
                            break;
                        default:
                            System.out.println("Enter valid input");
                    }
                    break;

                case 10:
                    StreetDAO sr=new StreetDAO();
                    System.out.println("1. get all street");
                    System.out.println("2. add street");
                    System.out.println("3. update street");
                    System.out.println("4. delete street");
                    System.out.println("5. to exit");
                    while (true) {
                        try {
                            System.out.print("Enter your choice (integer only): ");
                            ch5 = sc.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("❌ Invalid input! Please enter a number.");
                            sc.nextLine();
                        }
                    }
                    switch(ch5)
                    {
                        case 1:sr.getAllStreet();break;
                        case 2:sr.addStreet(sc);break;
                        case 3:sr.updateStreet(sc);break;
                        case 4:sr.deleteStreet(sc);break;
                        case 5:
                            System.out.println("Exiting....");
                            adminDashboard();
                            break;
                        default:
                            System.out.println("Enter valid input");
                    }
                    break;

                case 11:
                    TouristPlaceDAO to=new TouristPlaceDAO();
                    System.out.println("1. get all tourist places");
                    System.out.println("2. add tourist places");
                    System.out.println("3. to exit");
                    while (true) {
                        try {
                            System.out.print("Enter your choice (integer only): ");
                            ch5 = sc.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("❌ Invalid input! Please enter a number.");
                            sc.nextLine();
                        }
                    }
                    switch(ch5)
                    {
                        case 1: to.displayAllPlaces();break;
                        case 2: to.addPlace(); break;
                        case 3:
                            System.out.println("Exiting......");
                            adminDashboard();
                            break;
                        default:
                            System.out.println("Enter valid input");
                    }
                    break;

                case 12:
                    System.out.println("logged out successfully");
                    loginMenu();
                    break;
                default:
                    System.out.println("Invalid option!");
                    adminDashboard();
        }
        }

        public void customerDashboard() throws SQLException {
            int choice;
            int ch;
            System.out.println("\nCustomer Dashboard");
            System.out.println("1. Travelling routes and schedules");
            System.out.println("2. Emergency Service");
            System.out.println("3. Book tickets");
            System.out.println("4. Tourist places");
            System.out.println("5. Parking lot");
            System.out.println("6. submit Feedback");
            System.out.println("7. file a complaint");
            System.out.println("8. Logout");
            System.out.print("Select an option: ");

            while (true) {
                try {
                    System.out.print("Enter your choice (integer only): ");
                    choice = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("❌ Invalid input! Please enter a number.");
                    sc.nextLine();
                }
            }
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    RouteDAO r=new RouteDAO();
                    ScheduleDAO s=new ScheduleDAO();
                    System.out.println("1. get all route");
                    System.out.println("2. get route by id");
                    System.out.println("3. get schedule by root id");
                    System.out.println("4. to exit");
                    while (true) {
                        try {
                            System.out.print("Enter your choice (integer only): ");
                            ch = sc.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("❌ Invalid input! Please enter a number.");
                            sc.nextLine();
                        }
                    }
                    switch (ch)
                    {
                        case 1: r.getAllRoutes(); break;
                        case 2:r.getRouteById(sc);break;
                        case 3:s.getScheduleByRouteId(sc);break;
                        case 4:
                            System.out.println("Exiting....");
                            customerDashboard();
                            break;
                        default:
                            System.out.println("Enter valid input");
                    }
                    break;
                case 2:
                    EmergencyServiceDAO e=new EmergencyServiceDAO();
                    AreaEmergencyDispatcher a=new AreaEmergencyDispatcher(e.getAllEmergencyService());
                    System.out.println("1. get all Emergency services");
                    System.out.println("2. get Emergency services by type");
                    System.out.println("3. call Emergency service");
                    System.out.println("4. to exit");
                    while (true) {
                        try {
                            System.out.print("Enter your choice (integer only): ");
                            ch = sc.nextInt();
                            break;
                        } catch (InputMismatchException er) {
                            System.out.println("❌ Invalid input! Please enter a number.");
                            sc.nextLine();
                        }
                    }
                    switch(ch)
                    {
                        case 1:
                            e.getAllEmergencyService(); break;
                        case 2:e.getEmergencyServiceByType(sc);
                        case 3:
                            a.dispatchEmergency(sc); break;
                        case 4:
                            System.out.println("Exiting....");
                            customerDashboard();
                            break;
                        default:
                            System.out.println("Enter valid input");
                    }
                    break;
                case 3:
                    TicketDAO t=new TicketDAO();
                    System.out.println("1.book ticket");
                    System.out.println("2.search ticket");
                    System.out.println("3. to exit");
                    while (true) {
                        try {
                            System.out.print("Enter your choice (integer only): ");
                            ch = sc.nextInt();
                            break;
                        } catch (InputMismatchException er) {
                            System.out.println("❌ Invalid input! Please enter a number.");
                            sc.nextLine();
                        }
                    }
                    switch(ch)
                    {
                        case 1: t.addTicket(sc); break;
                        case 2: t.searchTickets(sc);break;
                        case 3:
                            System.out.println("Exiting....");
                            customerDashboard();
                            break;
                        default:
                            System.out.println("Enter valid input");

                    }
                    break;
                case 4:
                    TouristPlaceDAO tt=new TouristPlaceDAO();
                    System.out.println("1.Display all places");
                    System.out.println("2.Display top rated places");
                    System.out.println("3.Display places by category");
                    System.out.println("4.give feedback for places");
                    System.out.println("5. to exit");
                    while (true) {
                        try {
                            System.out.print("Enter your choice (integer only): ");
                            ch = sc.nextInt();
                            break;
                        } catch (InputMismatchException er) {
                            System.out.println("❌ Invalid input! Please enter a number.");
                            sc.nextLine();
                        }
                    }
                    switch (ch)
                    {
                        case 1: tt.displayAllPlaces();break;
                        case 2: tt.displayTopRatedPlaces(sc);break;
                        case 3: tt.displayPlacesByCategory(sc);break;
                        case 4: tt.applyFeedback(sc);break;
                        case 5:
                            System.out.println("Exiting....");
                            customerDashboard();
                            break;
                        default:
                            System.out.println("Enter valid input");
                    }
                    break;
                case 5:
                    ParkingLot p = new ParkingLot();
                    ParkingLotDAO po = new ParkingLotDAO();
                    System.out.println("1. Get Available parking lots");
                    System.out.println("2. Get Parking Lot By area Id");
                    System.out.println("3. Book Parking lot");
                    while (true) {
                        try {
                            System.out.print("Enter your choice (integer only): ");
                            ch = sc.nextInt();
                            break;
                        } catch (InputMismatchException er) {
                            System.out.println("❌ Invalid input! Please enter a number.");
                            sc.nextLine();
                        }
                    }
                    switch(ch) {
                        case 1: po.getAvailableParkingLots();break;
                        case 2: po.getParkingLotByAreaId(sc);break;
                        case 3: p.bookSlot(po);break;
                        default:
                            System.out.println("Enter valid input");
                    }
                    break;
                case 6:
                    FeedbackDAO f=new FeedbackDAO();
                    f.submitFeedback(sc);
                    customerDashboard();
                    break;
                case 7:
                    ComplaintDAO c=new ComplaintDAO();
                    c.fileComplaint(sc);
                    customerDashboard();
                    break;
                case 8:
                    System.out.println("Logged out successfully.");
                    loginMenu();
                    break;
                default:
                    System.out.println("Invalid option!");
                    customerDashboard();
            }
        }

    public void viewAllCustomers() throws SQLException {
        String sql = "SELECT id, username, email, full_name, created_at FROM customers";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\nList of all customers:");
        System.out.println("ID\tUsername\tEmail\t\tFull Name\tJoined Date");

        while (rs.next()) {
            System.out.println(
                    rs.getInt("id") + "\t" +
                            rs.getString("username") + "\t" +
                            rs.getString("email") + "\t" +
                            rs.getString("full_name") + "\t" +
                            rs.getTimestamp("created_at")
            );
        }
    }
}

