package Model;

import DataBase.EmergencyServiceDAO;

public class EmergencyService {
    int id;
    String name;
    String type;
    int areaId;
    long contactNumber;
    int availableVehicles;

    /**
     * Default Constructor
     */
    public EmergencyService() {
    }

    /**
     * getter for service id.
     *
     * @return service id
     */
    public int getId() {
        return id;
    }

    /**
     * getter for service name.
     *
     * @return service name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for service type.
     *
     * @return service type (for example =  Hospital, Police, Fire)
     */
    public String getType() {
        return type;
    }

    /**
     * getter for service location.
     *
     * @return service area id
     */
    public int getAreaId() {
        return areaId;
    }

    /**
     * getter for service contact number.
     *
     * @return service number
     */
    public long getContactNumber() {
        return contactNumber;
    }

    /**
     * getter for available service vehicle.
     *
     * @return count of vehicle
     */
    public int getAvailableVehicles() {
        return availableVehicles;
    }

    /**
     * setter for service id.
     *
     * @param id for id of service
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter for service name.
     *
     * @param name for name of service
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter for service type.
     *
     * @param type for type of service (for example =  Hospital, Police, Fire)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * setter for service location.
     *
     * @param areaId for area id of service
     */
    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    /**
     * setter for contact number.
     *
     * @param contactNumber for contact number of service
     */
    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * setter for count of service vehicle.
     *
     * @param availableVehicles number of vehicles
     */
    public void setAvailableVehicles(int availableVehicles) {
        this.availableVehicles = availableVehicles;
    }

    /**
     * This Method Allot the service and add again into available service after 10sec.
     */
    public void dispatchWithDelay(EmergencyServiceDAO dao) {
        if (availableVehicles > 0) {
            availableVehicles--;
            dao.updateVehicleCount(id, availableVehicles); // Update DB
            System.out.println("Vehicle dispatched from " + name + " (" + type + ")");

            Thread returnThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(20000); // Wait 20 seconds
                        availableVehicles++;
                        dao.updateVehicleCount(id, availableVehicles); // Update DB again
                        System.out.println("Vehicle returned to " + name + " (" + type + ")");
                    } catch (InterruptedException e) {
                        System.out.println("Dispatch interrupted for " + name);
                    }
                }
            });

            returnThread.start();
        } else {
            System.out.println("No vehicles available at " + name);
        }
    }
}
