package Model;


public class TouristPlace {
    int id;
    String name;
    String category;
    int areaId;
    double ratings;

    public TouristPlace(int id, String name, String category, int areaId, double ratings) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.areaId = areaId;
        this.ratings = ratings;
    }

    public TouristPlace() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getAreaId() {
        return areaId;
    }

    public double getRatings() {
        return ratings;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }
}

