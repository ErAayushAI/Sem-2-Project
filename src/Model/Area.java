package Model;

import Display.Displayable;

public class Area implements Displayable {
    int id;
    String name;
    double latitude;
    double longitude;
    boolean isEmergencyPoint;

    /**
     * Constructor for Area class.
     *
     * @param id               for Area pin code
     * @param name             for Area name
     * @param latitude         for location of Area on North or South of the Equator
     * @param longitude        for location of Area on East or West of the Prime Meridian
     * @param isEmergencyPoint for Area has an Emergency Point
     */
    public Area(int id, String name, double latitude, double longitude, boolean isEmergencyPoint) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isEmergencyPoint = isEmergencyPoint;
    }

    /**
     * Default Constructor.
     */
    public Area() {
    }

    /**
     * getter for Area pin code.
     *
     * @return Area's pin code
     */
    public int getAreaId() {
        return id;
    }

    /**
     * getter for Area Name.
     *
     * @return Area's name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for Area location in parallels to the Equator.
     *
     * @return Area's latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * getter for Area location vertically from the NorthPole to SouthPole.
     *
     * @return Area's longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * getter for Area has an Emergency Point.
     *
     * @return true if it has an Emergency Point
     */
    public boolean isEmergencyPoint() {
        return isEmergencyPoint;
    }

    /**
     * setter for Area pin code.
     *
     * @param id for pin code of Area
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter for Area name.
     *
     * @param name for name of Area
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter for Area latitude.
     *
     * @param latitude for latitude of Area
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * setter for Area longitude.
     *
     * @param longitude for longitude of Area
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * setter for Area has Emergency Point.
     *
     * @param emergencyPoint for emergency point in Area
     */
    public void setEmergencyPoint(boolean emergencyPoint) {
        isEmergencyPoint = emergencyPoint;
    }

    @Override
    public void displaySummary() {
        String emergencyStatus = isEmergencyPoint ? "✅ Yes" : "❌ No";
        System.out.printf("%-7d %-20s %-12.6f %-12.6f %-18s%n",
                id, name, latitude, longitude, emergencyStatus);
    }

    @Override
    public void displayDetails() {
        System.out.println("\n🌐 Area Details");
        System.out.println("--------------------------------------------------");
        System.out.printf("ID: %d%n", id);
        System.out.printf("Name: %s%n", name);
        System.out.printf("Latitude: %.6f%n", latitude);
        System.out.printf("Longitude: %.6f%n", longitude);
        System.out.printf("Emergency Point: %s%n", isEmergencyPoint ? "✅ Yes" : "❌ No");
        System.out.println("--------------------------------------------------");
    }

}
