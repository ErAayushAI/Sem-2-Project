package Model;

import Display.Displayable;

public class Street implements Displayable {
    int id;
    int startAreaId;
    int endAreaId;
    double distance;
    boolean isOneWay;

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

    @Override
    public void displaySummary() {
        String oneWayStatus = isOneWay ? "➡️ Yes" : "↔️ No";
        System.out.printf("%-5d %-12d %-12d %-13.2f %-10s%n",
                id, startAreaId, endAreaId, distance, oneWayStatus);
    }

    @Override
    public void displayDetails() {
        System.out.println("\n🛣️ Street Details");
        System.out.println("--------------------------------------------------");
        System.out.printf("ID: %d%n", id);
        System.out.printf("Start Area ID: %d%n", startAreaId);
        System.out.printf("End Area ID: %d%n", endAreaId);
        System.out.printf("Distance: %.2f km%n", distance);
        System.out.printf("One Way: %s%n", isOneWay ? "➡️ Yes" : "↔️ No");
        System.out.println("--------------------------------------------------");
    }

}
