package Model;

import Display.Displayable;

public class Complaint implements Displayable {
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
     * Default Constructor.
     */
    public Complaint() {}

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
     * setter for set complaint id.
     *
     * @param id complaint's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter for complaint department.
     *
     * @param department name
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * setter for user who file a Complaint.
     *
     * @param userId id of user
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * setter for issue which filed in complaint.
     *
     * @param issue content
     */
    public void setIssue(String issue) {
        this.issue = issue;
    }

    /**
     * setter ton set status.
     *
     * @param status if true issue is solved
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public void displaySummary() {
        String format = "| %-4d | %-20s | %-7d | %-9s | %-45s |%n";
        String statusStr = status ? "Resolved" : "Pending";
        String preview = getIssuePreview(issue, 45);
        System.out.format(format, id, department, userId, statusStr, preview);
    }

    @Override
    public void displayDetails() {
        System.out.println("\n📝 Full Issue for Complaint ID: " + id);
        System.out.println("--------------------------------------------------");
        System.out.println(indentMultilineText(issue, "--> "));
        System.out.println("--------------------------------------------------");
    }

    private String getIssuePreview(String issue, int maxLength) {
        if (issue == null || issue.isEmpty()) return "(No issue)";
        issue = issue.replaceAll("\\r?\\n", " ");
        return issue.length() <= maxLength ? issue : issue.substring(0, maxLength - 3) + "...";
    }

    private String indentMultilineText(String text, String indent) {
        if (text == null || text.isEmpty()) return indent + "(No issue description)";
        String[] lines = text.split("\\r?\\n");
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(indent).append(line).append("\n");
        }
        return sb.toString();
    }
}
