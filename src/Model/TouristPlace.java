package Model;


public class TouristPlace {
    int id;
    String name;
    String category;
    int areaId;
    double ratings;

    /**
     * Constructor for Tourist Place class.
     *
     * @param id       for place id
     * @param name     for place name
     * @param category for which type of place it is
     * @param areaId   for area in which place is exists
     * @param ratings  for rating of that place
     */
    public TouristPlace(int id, String name, String category, int areaId, double ratings) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.areaId = areaId;
        this.ratings = ratings;
    }

    /**
     * Default Constructor.
     */
    public TouristPlace() {
    }

    /**
     * Getter for placeId.
     *
     * @return place's Id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for place name.
     *
     * @return place's name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for place category.
     *
     * @return place's category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Getter for Area where place is located.
     *
     * @return area id
     */
    public int getAreaId() {
        return areaId;
    }

    /**
     * Getter for ratings of the place.
     *
     * @return place's ratings
     */
    public double getRatings() {
        return ratings;
    }

    /**
     * Setter for place id.
     *
     * @param id for id of the place
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter for place name.
     *
     * @param name for name of place
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for category of the place.
     *
     * @param category for category of place
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Setter for Area id where it is located.
     *
     * @param areaId for area id
     */
    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    /**
     * Setter for place ratings.
     *
     * @param ratings for ratings of the place
     */
    public void setRatings(double ratings) {
        this.ratings = ratings;
    }
}

