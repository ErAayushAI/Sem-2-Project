package Model;

public class EmergencyService {
    int id;
    String name;
    String type;
    int areaId;
    long contactNumber;

    /**
     * Constructor for EmergencyService Class.
     *
     * @param id            for service id
     * @param name          for service name
     * @param type          for service type (for example =  Hospital, Police, Fire)
     * @param areaId        for location of service
     * @param contactNumber for service contact number
     */
    public EmergencyService(int id, String name, String type, int areaId, long contactNumber) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.areaId = areaId;
        this.contactNumber = contactNumber;
    }

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
}
