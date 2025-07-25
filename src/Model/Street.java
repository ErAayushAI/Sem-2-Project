package Model;

public class Street {
    int id;
    int startAreaId;
    int endAreaId;
    double distance;
    boolean isOneWay;

    /**
     * Constructor for Street Class.
     *
     * @param id          for Street id
     * @param startAreaId for Street Starts from this Area
     * @param endAreaId   for Street Ends at this Area
     * @param distance    for length of the Street
     * @param isOneWay    for Street has One way or not
     */
    public Street(int id, int startAreaId, int endAreaId, double distance, boolean isOneWay) {
        this.id = id;
        this.startAreaId = startAreaId;
        this.endAreaId = endAreaId;
        this.distance = distance;
        this.isOneWay = isOneWay;
    }

    /**
     * Default Constructor.
     */
    public Street() {
    }

    /**
     * getter for Street id.
     *
     * @return Street's id
     */
    public int getId() {
        return id;
    }

    /**
     * getter for Street starts from the Area.
     *
     * @return Street's start Area id
     */
    public int getStartAreaId() {
        return startAreaId;
    }

    /**
     * getter for Street ends at the Area.
     *
     * @return Street's end Area id
     */
    public int getEndAreaId() {
        return endAreaId;
    }

    /**
     * getter for Street Length from start to end Area.
     *
     * @return Street's Length
     */
    public double getDistance() {
        return distance;
    }

    /**
     * getter for Street has a One way.
     *
     * @return true if traffic flows only StartAreaID to EndAreaID
     */
    public boolean isOneWay() {
        return isOneWay;
    }

    /**
     * setter for Street id.
     *
     * @param id for id of Street
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter for Street starts Area id.
     *
     * @param startAreaId for start area of the Street
     */
    public void setStartAreaId(int startAreaId) {
        this.startAreaId = startAreaId;
    }

    /**
     * setter for Street ends Area id.
     *
     * @param endAreaId for end area of the Street
     */
    public void setEndAreaId(int endAreaId) {
        this.endAreaId = endAreaId;
    }

    /**
     * setter for Street traffic flows only StartAreaID to EndAreaID.
     *
     * @param oneWay for One way in Street
     */
    public void setOneWay(boolean oneWay) {
        isOneWay = oneWay;
    }

    /**
     * setter for Street Distance between StartAreaID and EndAreaID.
     *
     * @param distance for length of the Street
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }
}
