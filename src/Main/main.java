package Main;

import DataBase.DataBaseManager;
import Authentication.Login;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws SQLException {
        Login l = new Login();
        DataBase.DataBaseManager db = new DataBaseManager();
        try {
            db.getInstance();
            db.getConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("-----------------------------------------------------------------");
            System.out.println("                Welcome to Smart city HUB                        ");
            System.out.println("-----------------------------------------------------------------");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    boolean b = l.adminLogin();
                    if (b) {
                        while (true) {
                            System.out.println("\nAdmin Dashboard");
                            System.out.println("1. View all customers");
                            System.out.println("2. Area");
                            System.out.println("3. Bus");
                            System.out.println("4. EmergencyServices");
                            System.out.println("5. Metro");
                            System.out.println("6. ParkingLot");
                            System.out.println("7. Route");
                            System.out.println("8. Schedule");
                            System.out.println("9. Station");
                            System.out.println("10. Street");
                            System.out.println("11. Logout");
                            System.out.print("Select an option: ");

                            int ch = sc.nextInt();
                            sc.nextLine(); // Consume newline

                            switch (ch) {
                                case 1:
                                    l.viewAllCustomers();
                                    break;
                                case 2:
                                    while (true) {
                                        DataBase.AreaDAO a = new DataBase.AreaDAO();
                                        System.out.println("1. To add Area");
                                        System.out.println("2. To Update Area");
                                        System.out.println("3. To delete Area");
                                        System.out.println("4. To exit");
                                        System.out.println("Enter choice: ");
                                        int ch1 = sc.nextInt();
                                        switch (ch1) {
                                            case 1:
                                                boolean b1 = a.addArea();
                                                if (b1) {
                                                    System.out.println("Added successfully");
                                                } else {
                                                    System.out.println("Failed");
                                                }
                                                break;

                                            case 2:

                                                boolean b2 = a.updateArea();
                                                if (b2) {
                                                    System.out.println("updated successfully");
                                                } else {
                                                    System.out.println("Failed");
                                                }
                                                break;

                                            case 3:

                                                boolean b3 = a.deleteArea();
                                                if (b3) {
                                                    System.out.println("Deleted successfully");
                                                } else {
                                                    System.out.println("Failed");
                                                }
                                                break;

                                            case 4:
                                                System.out.println("Exiting.....");
                                                break;
                                            default:
                                                System.out.println("Invalid option!");
                                                break;
                                        }
                                        break;
                                    }
                                case 3:

                                    DataBase.BusDAO a = new DataBase.BusDAO();
                                    while (true) {
                                        System.out.println("Bus System");
                                        System.out.println("1.Add bus");
                                        System.out.println("2.Update bus Location");
                                        System.out.println("3.Update bus Route");
                                        System.out.println("4.Exit");
                                        int ch2 = sc.nextInt();
                                        switch (ch2) {
                                            case 1:
                                                boolean b2 = a.addBus();
                                                if (b2) {
                                                    System.out.println("Added successfully");
                                                } else {
                                                    System.out.println("Failed");
                                                }
                                                break;

                                            case 2:

                                                boolean b3 = a.updateBusLocation();
                                                if (b3) {
                                                    System.out.println("updated successfully");
                                                } else {
                                                    System.out.println("Failed");
                                                }
                                                break;

                                            case 3:

                                                boolean b4 = a.updateBusRoute();
                                                if (b4) {
                                                    System.out.println("Updated successfully");
                                                } else {
                                                    System.out.println("Failed");
                                                }
                                                break;

                                            case 4:
                                                System.out.println("Exiting.....");
                                                break;
                                            default:
                                                System.out.println("Invalid option!");
                                                break;
                                        }
                                        break;
                                    }

                                case 4:
                                    DataBase.EmergencyServiceDAO ab = new DataBase.EmergencyServiceDAO();
                                    while (true) {
                                        System.out.println("1. To add EmergencyServices");
                                        System.out.println("2.display all EmergencyServices");
                                        System.out.println("3. To exit");
                                        System.out.println("Enter choice: ");
                                        int ch3 = sc.nextInt();
                                        switch (ch3) {
                                            case 1: //add

                                                boolean b2 = ab.addEmergencyService();
                                                if (b2) {
                                                    System.out.println("Added successfully");
                                                } else {
                                                    System.out.println("Failed");
                                                }
                                                break;

                                            case 2: //display

//                                              Array[] c=a.getAllEmergencyService();
                                                break;

                                            case 3:
                                                System.out.println("Exiting.....");
                                                break;
                                            default:
                                                System.out.println("Invalid option!");
                                                break;
                                        }
                                        break;
                                    }
                                default:
                                    System.out.println("Invalid option!");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("login failed");
                    }
            }
        }
    }
}

