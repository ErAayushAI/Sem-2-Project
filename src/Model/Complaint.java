package Model;

public class Complaint {
    int id = 1;
    String department;
    int userId;
    String issue;
    Boolean status;

    /**
     * Constructor for Complaint class.
     *
     * @param department for which department complaint is registered
     * @param userId     who is registered this complaint
     * @param issue      what issue is in this complaint
     */
    public Complaint(String department, int userId, String issue) {
        id++;
        this.department = department;
        this.userId = userId;
        this.issue = issue;
        this.status = false;
    }

    /**
     * getter for complaint id.
     *
     * @return complaint id
     */
    public int getId() {
        return id;
    }

    /**
     * getter for department on which complaint is registered.
     *
     * @return department id
     */
    public String getDepartment() {
        return department;
    }

    /**
     * getter for user who registered complaint.
     *
     * @return user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * getter for issue registered by user
     *
     * @return issue in department
     */
    public String getIssue() {
        return issue;
    }

    /**
     * getter for status (false = pending)
     *
     * @return status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * setter ton set status.
     *
     * @param status if true issue is solved
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }
}
