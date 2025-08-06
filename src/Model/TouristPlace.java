package Model;

import java.util.ArrayList;
import java.util.List;

public class TouristPlace {
    String name;
    String category;
    int areaId;
    private List<Integer> ratings;

    public TouristPlace(String name, String category, int areaId, List<Integer> ratings) {
        this.name = name;
        this.category = category;
        this.areaId = areaId;
        this.ratings = ratings;
    }

    public void addRating(int rating) {
        if (rating >= 1 && rating <= 5) ratings.add(rating);
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) return 0.0;
        return Math.round(ratings.stream().mapToInt(Integer::intValue).average().orElse(0.0) * 100.0) / 100.0;
    }

    public String getCategory() { return category; }

    public String getName() { return name; }

    public void showPlaceInfo() {
        System.out.println("📍 " + name + " — " + location);
        System.out.println("Category: " + category);
        System.out.println("★ Average Rating: " + getAverageRating() + " stars");
    }
}

