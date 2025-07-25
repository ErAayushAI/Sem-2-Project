package Model;

public class ParkingLot {
    int id;
    String name;
    int areaId;
    int capacity;
    int currentOccupancy;

    /**
     * Constructor for ParkingLot class.
     *
     * @param id               for lot id
     * @param name             for lot name
     * @param areaId           for location of lot
     * @param capacity         for number of spots
     * @param currentOccupancy number spots currently taken
     */
    public ParkingLot(int id, String name, int areaId, int capacity, int currentOccupancy) {
        this.id = id;
        this.name = name;
        this.areaId = areaId;
        this.capacity = capacity;
        this.currentOccupancy = currentOccupancy;
    }

    /**
     * Default Constructor.
     */
    public ParkingLot() {
    }

    /**
     * getter for lot id.
     *
     * @return lot's id
     */
    public int getId() {
        return id;
    }

    /**
     * getter for lot name.
     *
     * @return lot's name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for location of lot.
     *
     * @return lot area id
     */
    public int getAreaId() {
        return areaId;
    }

    /**
     * getter for number of slots.
     *
     * @return slots in lot
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * getter for slots taken inside lot.
     *
     * @return taken slots in lot
     */
    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    /**
     * setter for lot id.
     *
     * @param id lot id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter for lot name.
     *
     * @param name lot name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter for location of lot.
     *
     * @param areaId area id of lot
     */
    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    /**
     * setter for capacity in lot.
     *
     * @param capacity lot capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * setter for slots taken in lot.
     *
     * @param currentOccupancy taken slots
     */
    public void setCurrentOccupancy(int currentOccupancy) {
        this.currentOccupancy = currentOccupancy;
    }
}
