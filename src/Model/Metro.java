package Model;

import Display.Displayable;

public class Metro implements Displayable {
    int id;
    String trainName;
    int capacity;
    Integer currentRouteID;
    Integer currentAreaID;

    /**
     * Default Constructor.
     */
    public Metro() {
    }

    /**
     * getter for Metro id.
     *
     * @return Metro's id
     */
    public int getId() {
        return id;
    }

    /**
     * getter for Train name.
     *
     * @return Metro's name
     */
    public String getTrainName() {
        return trainName;
    }

    /**
     * getter for Metro capacity.
     *
     * @return Metro's capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * getter for Metro current route is.
     *
     * @return Metro's current route id
     */
    public Integer getCurrentRouteID() {
        return currentRouteID;
    }

    /**
     * getter for Metro current Location is.
     *
     * @return Metro's current area id
     */
    public Integer getCurrentAreaID() {
        return currentAreaID;
    }

    /**
     * setter for Metro id.
     *
     * @param id for id of Metro
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter for train name.
     *
     * @param trainName for name of Metro
     */
    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    /**
     * setter for Metro capacity.
     *
     * @param capacity for capacity of metro
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * setter for current Route.
     *
     * @param currentRouteID for current route id
     */
    public void setCurrentRouteID(Integer currentRouteID) {
        this.currentRouteID = currentRouteID;
    }

    /**
     * setter for current Location.
     *
     * @param currentAreaID for current area id
     */
    public void setCurrentAreaID(Integer currentAreaID) {
        this.currentAreaID = currentAreaID;
    }

    @Override
    public void displaySummary() {
        System.out.printf("%-5d %-20s %-10d %-15s %-15s%n",
                id,
                trainName,
                capacity,
                currentRouteID != null ? currentRouteID : "—",
                currentAreaID != null ? currentAreaID : "—");
    }

    @Override
    public void displayDetails() {
        System.out.println("\n🚇 Metro Details");
        System.out.println("--------------------------------------------------");
        System.out.printf("ID: %d%n", id);
        System.out.printf("Train Name: %s%n", trainName);
        System.out.printf("Capacity: %d%n", capacity);
        System.out.printf("Current Route ID: %s%n", currentRouteID != null ? currentRouteID : "—");
        System.out.printf("Current Area ID: %s%n", currentAreaID != null ? currentAreaID : "—");
        System.out.println("--------------------------------------------------");
    }

}
