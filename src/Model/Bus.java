package Model;

public class Bus {
    int id;
    String licensePlate;
    int capacity;
    Integer currentRouteId;
    Integer currentAreaID;

    /**
     * Constructor for Bus Class.
     *
     * @param id             for Bus id
     * @param licensePlate   for Bus Registration number
     * @param capacity       for Bus capacity
     * @param currentRouteId for current Route of the Bus
     * @param currentAreaID  for current Location of the Bus
     */
    public Bus(int id, String licensePlate, int capacity, Integer currentRouteId, Integer currentAreaID) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.capacity = capacity;
        this.currentRouteId = currentRouteId;
        this.currentAreaID = currentAreaID;
    }

    /**
     * Default Constructor.
     */
    public Bus() {
    }

    /**
     * getter for Bus id.
     *
     * @return Bus's id
     */
    public int getId() {
        return id;
    }

    /**
     * getter for Bus Registration number.
     *
     * @return Bus's License Plate
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * getter for Bus Capacity.
     *
     * @return Bus's capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * getter for Bus current Route is.
     *
     * @return Bus's current route id
     */
    public Integer getCurrentRouteId() {
        return currentRouteId;
    }

    /**
     * getter for Bus current Location is.
     *
     * @return Bus's current area id
     */
    public Integer getCurrentAreaID() {
        return currentAreaID;
    }

    /**
     * setter for Bus id.
     *
     * @param id for id of Bus
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter for Bus registration number.
     *
     * @param licensePlate for license plate of Bus
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    /**
     * setter for Bus Capacity.
     *
     * @param capacity for capacity of Bus
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * setter for Bus current Route.
     *
     * @param currentRouteId for current route id
     */
    public void setCurrentRouteId(Integer currentRouteId) {
        this.currentRouteId = currentRouteId;
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
