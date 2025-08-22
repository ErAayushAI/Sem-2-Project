package Model;

import Display.Displayable;

public class Route implements Displayable {
    int id;
    String name;
    double length;
    boolean isBusRoute;
    boolean isMetroRoute;

    /**
     * Constructor for Route class.
     *
     * @param id           for id of Route
     * @param name         for name of Route
     * @param length       for length of Route
     * @param isBusRoute   for Bus Route
     * @param isMetroRoute for Metro Route
     */
    public Route(int id, String name, double length, boolean isBusRoute, boolean isMetroRoute) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.isBusRoute = isBusRoute;
        this.isMetroRoute = isMetroRoute;
    }

    /**
     * Default Constructor.
     */
    public Route() {
    }

    /**
     * getter for Route id.
     *
     * @return Route's id
     */
    public int getId() {
        return id;
    }

    /**
     * getter for Route name.
     *
     * @return Route's name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for Route Length.
     *
     * @return Route's Length
     */
    public double getLength() {
        return length;
    }

    /**
     * getter for Bus Route.
     *
     * @return true if it's Bus Route
     */
    public boolean isBusRoute() {
        return isBusRoute;
    }

    /**
     * getter for Metro Route.
     *
     * @return true if it's Metro Route
     */
    public boolean isMetroRoute() {
        return isMetroRoute;
    }

    /**
     * setter for Route id.
     *
     * @param id route id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter for Route name.
     *
     * @param name route name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter for Route length.
     *
     * @param length route length
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * setter to set Route to Bus Route.
     *
     * @param busRoute true if it's bus route
     */
    public void setBusRoute(boolean busRoute) {
        isBusRoute = busRoute;
    }

    /**
     * setter to set Route to Metro Route.
     *
     * @param metroRoute true if it's metro route
     */
    public void setMetroRoute(boolean metroRoute) {
        isMetroRoute = metroRoute;
    }

    @Override
    public void displaySummary() {
        String busStatus = isBusRoute ? "✅ Yes" : "❌ No";
        String metroStatus = isMetroRoute ? "✅ Yes" : "❌ No";
        System.out.printf("%-5s %-40s %-10s %-15s %-15s%n",
                id, name, length, busStatus, metroStatus);
    }

    @Override
    public void displayDetails() {
        String busStatus = isBusRoute ? "✅ Yes" : "❌ No";
        String metroStatus = isMetroRoute ? "✅ Yes" : "❌ No";
        System.out.println("\n🛣️ Route Details");
        System.out.println("--------------------------------------------------");
        System.out.printf("ID: %d%n", id);
        System.out.printf("Name: %s%n", name);
        System.out.printf("Length: %.2f km%n", length);
        System.out.printf("Bus Route: %s%n", busStatus);
        System.out.printf("Metro Route: %s%n", metroStatus);
        System.out.println("--------------------------------------------------");
    }

}
