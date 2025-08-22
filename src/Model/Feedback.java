package Model;

import Display.Displayable;

public class Feedback implements Displayable {
    int id;
    int userId;
    int placeId;
    String comments;
    double rating;

    /**
     * Default Constructor.
     */
    public Feedback() {
    }

    /**
     * getter for feedback id.
     *
     * @return feedback id
     */
    public int getId() {
        return id;
    }

    /**
     * getter for user who gives feedback.
     *
     * @return user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * getter for place on which user are giving feedback.
     *
     * @return place id
     */
    public int getPlaceId() {
        return placeId;
    }

    /**
     * getter for comments if user are added that.
     *
     * @return comment
     */
    public String getComments() {
        return comments;
    }

    /**
     * getter for Rating of the place.
     *
     * @return ratings
     */
    public double getRating() {
        return rating;
    }

    /**
     * setter for feedback id.
     *
     * @param id feedback's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter for user Id.
     *
     * @param userId user who feedbacks
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * setter for place name.
     *
     * @param placeId place which is rated by user
     */
    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    /**
     * setter for comments for places given by user.
     *
     * @param comments comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * setter for ratings given user.
     *
     * @param rating rating between 1 and 5
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * For get ratings in Stars
     *
     * @return stars
     */
    public String getStarRating() {
        int fullStars = (int) rating;
        boolean hasHalfStar = rating - fullStars >= 0.25 && rating - fullStars < 0.75;
        int emptyStars = 5 - fullStars - (hasHalfStar ? 1 : 0);

        StringBuilder stars = new StringBuilder();
        stars.append("★".repeat(Math.max(0, fullStars)));
        if (hasHalfStar) stars.append("⯨");
        stars.append("☆".repeat(Math.max(0, emptyStars)));

        return stars.toString();
    }

    @Override
    public void displaySummary() {
        System.out.printf("%-5s %-8s %-9s %-40s %-6s\n",
                id, userId, placeId, comments, rating);
    }

    @Override
    public void displayDetails() {
        System.out.println("\n----------------- FEEDBACK DETAILS -----------------");
        System.out.println("Feedback ID : " + id);
        System.out.println("User ID     : " + userId);
        System.out.println("Place ID    : " + placeId);
        System.out.println("Comments    : " + comments);
        System.out.println("Rating      : " + rating);
        System.out.println("----------------------------------------------------\n");

    }
}
