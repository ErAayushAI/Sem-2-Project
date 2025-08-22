package Model;

import Display.Displayable;

import java.sql.Time;

public class Schedule implements Displayable {
    int id;
    int routeID;
    Time departureTime;
    boolean isBusSchedule;
    boolean isMetroSchedule;

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

    /**
     * setter for bus schedule
     *
     * @param busSchedule true if it is bus schedule
     */
    public void setBusSchedule(boolean busSchedule) {
        isBusSchedule = busSchedule;
    }

    /**
     * setter for metro schedule
     *
     * @param metroSchedule true if it is metro schedule
     */
    public void setMetroSchedule(boolean metroSchedule) {
        isMetroSchedule = metroSchedule;
    }

    @Override
    public void displaySummary() {
        String busStatus = isBusSchedule ? "✅ Yes" : "❌ No";
        String metroStatus = isMetroSchedule ? "✅ Yes" : "❌ No";
        System.out.printf("%-5s %-10s %-15s %-15s %-15s%n",
                id, routeID, departureTime, busStatus, metroStatus);
    }

    @Override
    public void displayDetails() {
        String busStatus = isBusSchedule ? "✅ Yes" : "❌ No";
        String metroStatus = isMetroSchedule ? "✅ Yes" : "❌ No";
        System.out.println("\n🕒 Schedule Details");
        System.out.println("--------------------------------------------------");
        System.out.printf("ID: %s%n", id);
        System.out.printf("Route ID: %s%n", routeID);
        System.out.printf("Departure Time: %s%n", departureTime);
        System.out.printf("Bus Schedule: %s%n", busStatus);
        System.out.printf("Metro Schedule: %s%n", metroStatus);
        System.out.println("--------------------------------------------------");
    }

}
