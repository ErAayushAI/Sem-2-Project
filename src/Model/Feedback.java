package Model;

public class Feedback {
    int id;
    int userId;
    int placeId;
    String comments;
    int rating;

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
    public int getPlaceName() {
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
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * For get ratings in Stars
     *
     * @return stars
     */
    public String getStarRating() {
        return "★".repeat(rating) + "☆".repeat(5 - rating);
    }
}
