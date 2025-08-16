package Model;

public class Station {
    int id;
    String name;
    int AreaId;
    boolean isBusStation;
    boolean isMetroStation;

    /**
     * Default Constructor.
     */
    public Station() {
    }

    /**
     * getter for station id.
     *
     * @return Station's id
     */
    public int getId() {
        return id;
    }

    /**
     * getter for station name.
     *
     * @return Station's name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for station location.
     *
     * @return area id of station
     */
    public int getAreaId() {
        return AreaId;
    }

    /**
     * getter for Bus station.
     *
     * @return true if it's Bus Station
     */
    public boolean isBusStation() {
        return isBusStation;
    }

    /**
     * getter for Metro station.
     *
     * @return true if it's Metro Station
     */
    public boolean isMetroStation() {
        return isMetroStation;
    }

    /**
     * setter for station id.
     *
     * @param id station id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter for station name.
     *
     * @param name station name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter for station location.
     *
     * @param areaId station area id
     */
    public void setAreaId(int areaId) {
        AreaId = areaId;
    }

    /**
     * setter for Bus station.
     *
     * @param busStation true if it's Bus Station
     */
    public void setBusStation(boolean busStation) {
        isBusStation = busStation;
    }

    /**
     * setter for Metro station.
     *
     * @param metroStation true if it's Metro Station
     */
    public void setMetroStation(boolean metroStation) {
        isMetroStation = metroStation;
    }
}
