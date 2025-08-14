package DataStructure;

import DataBase.EmergencyServiceDAO;
import Model.EmergencyService;

import java.util.List;
import java.util.Scanner;

//To simulate emergency response based on user location and vehicle availability
//This demonstrates understanding of data structures like lists, object state management, and traversal algorithms.
public class AreaEmergencyDispatcher {

    List<EmergencyService> allServices;

    /**
     * Constructor for Area Dispatcher.
     *
     * @param services list of all services
     */
    public AreaEmergencyDispatcher(List<EmergencyService> services) {
        this.allServices = services;
    }

    /**
     * To Allot an Emergency Vehicle to nearest area.
     *
     * @return true if service is available
     */
    public boolean dispatchEmergency(Scanner scanner) {
        System.out.println("---------- EMERGENCY SERVICE DISPATCHER ----------");
        System.out.println();
        System.out.print("Enter Service Type: ");
        scanner.nextLine();
        String type = scanner.nextLine().trim().toUpperCase();
        System.out.print("Enter Area ID: ");
        int userAreaId = scanner.nextInt();
        System.out.println("Emergency Request: " + type + " in Area " + userAreaId);
        EmergencyServiceDAO dao = new EmergencyServiceDAO();

        for (EmergencyService service : allServices) {
            if (service.getType().equalsIgnoreCase(type) && service.getAreaId() == userAreaId) {
                if (service.getAvailableVehicles() > 0) {
                    service.dispatchWithDelay(dao);
                    return true;
                } else {
                    System.out.println("No vehicles left at " + service.getName());
                }
            }
        }

        for (EmergencyService service : allServices) {
            if (service.getType().equalsIgnoreCase(type) && service.getAreaId() != userAreaId) {
                if (service.getAvailableVehicles() > 0) {
                    service.dispatchWithDelay(dao);
                    return true;
                }
            }
        }

        System.out.println("No available emergency vehicles for type: " + type);
        return false;
    }

}