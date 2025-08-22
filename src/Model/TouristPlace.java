package Model;


import Display.Displayable;

import java.util.List;

public class TouristPlace implements Displayable {
    int id;
    String name;
    String category;
    int areaId;
    double ratings;

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

    @Override
    public void displaySummary() {
        System.out.printf("%-5s %-30s %-25s %-10s %-20s%n",
                id, name, category, areaId, ratings+" "+getStarRating());
    }

    @Override
    public void displayDetails() {
        System.out.println("\n🗺️ Tourist Details");
        System.out.println("--------------------------------------------------");
        System.out.printf("ID: %d%n", id);
        System.out.printf("Name: %s%n", name);
        System.out.printf("Category: %s%n", category);
        System.out.printf("Area ID: %s%n", areaId);
        System.out.printf("Ratings: %s%n", ratings+" "+getStarRating());
        System.out.println("--------------------------------------------------");
    }

    public String getStarRating() {
        int fullStars = (int) ratings;
        boolean hasHalfStar = ratings - fullStars >= 0.25 && ratings - fullStars < 0.75;
        int emptyStars = 5 - fullStars - (hasHalfStar ? 1 : 0);

        StringBuilder stars = new StringBuilder();
        stars.append("★".repeat(Math.max(0, fullStars)));
        if (hasHalfStar) stars.append("⯨");
        stars.append("☆".repeat(Math.max(0, emptyStars)));

        return stars.toString();
    }

    //Display List of Categories
    public static void printCategories(List<String> categories) {
        if (categories == null || categories.isEmpty()) {
            System.out.println("⚠️ No categories found.");
            return;
        }

        String line = "+---------------------------+";
        String format = "| %-25s |\n";

        System.out.println("\n---------- AVAILABLE CATEGORIES ----------\n");
        System.out.println(line);
        System.out.printf(format, "Category");
        System.out.println(line);

        for (String category : categories) {
            System.out.printf(format, category);
        }

        System.out.println(line);
    }
}

