package Model;

import java.sql.Time;

public class Ticket {
    int id;
    int userId;
    int routeId;
    boolean isBusTransport;
    boolean isMetroTransport;
    Time time;
    double totalBill;
    double distance;

    /**
     * Constructor for Ticket Class.
     *
     * @param id for ticket id
     * @param userId for user id
     * @param routeId for route id
     * @param isBusTransport for bus transport
     * @param isMetroTransport for metro transport
     * @param time for current time
     * @param totalBill for price per kilo meter
     * @param distance for distance to travel
     */
    public Ticket(int id, int userId, int routeId, boolean isBusTransport, boolean isMetroTransport, Time time, double totalBill, double distance) {
        this.id = id;
        this.userId = userId;
        this.routeId = routeId;
        this.isBusTransport = isBusTransport;
        this.isMetroTransport = isMetroTransport;
        this.time = time;
        this.distance = distance;
        this.totalBill = calculateBill(distance);
    }

    /**
     * Default Constructor.
     */
    public Ticket() {}

    /**
     * To calculate bill as per traveled distance.
     *
     * @param distance travel distance
     * @return total amount
     */
    public double calculateBill(double distance){
        return distance * 5;
    }

    /**
     * getter for bill id.
     * @return Ticket's id
     */
    public int getId() {
        return id;
    }

    /**
     * getter for user id who is booking ticket.
     * @return user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * getter for route in which user wants to travel.
     * @return route id
     */
    public int getRouteId() {
        return routeId;
    }

    /**
     * getter user is traveling from Bus.
     * @return true if it's Bus
     */
    public boolean isBusTransport() {
        return isBusTransport;
    }

    /**
     * getter for user traveling from Metro.
     * @return true if it's Metro
     */
    public boolean isMetroTransport() {
        return isMetroTransport;
    }

    /**
     * getter for Booking time.
     * @return time
     */
    public Time getTime() {
        return time;
    }

    /**
     * getter for user wants to travel distance.
     * @return distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * getter for total bill.
     * @return total amount to be paid by user
     */
    public double getTotalBill() {
        return totalBill;
    }
}
