package Model;

public class Metro {
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
}
