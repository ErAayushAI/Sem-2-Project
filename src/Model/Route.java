package Model;

public class Route {
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
}
