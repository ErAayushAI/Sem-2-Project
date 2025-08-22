package Display;

public interface Displayable {

    /**
     * For List view
     * Prints data in table formate
     */
    void displaySummary();

    /**
     * For Detailed view
     * Prints data for one object of particular data
     */
    void displayDetails();
}
