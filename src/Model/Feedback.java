package Model;

public class Feedback {
    int id;
    int userId;
    String placeName;
    String comments;
    int rating;

    /**
     * Constructor for Feedback Class.
     *
     * @param id        for Feedback id
     * @param userId    for user who Rates
     * @param placeName for which place user is rating
     * @param comments  for comments user wants to add
     * @param rating    for rating between 1 and 5 stars
     */
    public Feedback(int id, int userId, String placeName, String comments, int rating) {
        this.id = id;
        this.userId = userId;
        this.placeName = placeName;
        this.comments = comments;
        this.rating = rating;
    }

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
     * @return place name
     */
    public String getPlaceName() {
        return placeName;
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
     * getter for ratings
     *
     * @return ratings in between 1 and 5
     */
    public int getRating() {
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
     * @param placeName place which is rated by user
     */
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
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
