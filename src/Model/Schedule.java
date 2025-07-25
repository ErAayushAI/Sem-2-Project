package Model;

import java.sql.Time;

public class Schedule {
    int id;
    int routeID;
    Time departureTime;

    /**
     * Constructor for Schedule Class.
     *
     * @param id            for schedule id
     * @param routeID       for schedule of route
     * @param departureTime for departure time of bus or metro
     */
    public Schedule(int id, int routeID, Time departureTime) {
        this.id = id;
        this.routeID = routeID;
        this.departureTime = departureTime;
    }

    /**
     * Default Constructor.
     */
    public Schedule() {
    }

    /**
     * getter for Schedule id.
     *
     * @return Schedule's id
     */
    public int getId() {
        return id;
    }

    /**
     * getter for schedule for route.
     *
     * @return schedule's route id
     */
    public int getRouteID() {
        return routeID;
    }

    /**
     * getter for departure time of bus or metro.
     *
     * @return time of departure
     */
    public Time getDepartureTime() {
        return departureTime;
    }

    /**
     * setter for schedule id.
     *
     * @param id schedule id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter for schedule for route.
     *
     * @param routeID id of route
     */
    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    /**
     * setter for departure time.
     *
     * @param departureTime Bus or metro's departure time
     */
    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }
}
