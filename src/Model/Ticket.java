package Model;

public class Ticket {
    int id;

    /**
     * Default Constructor.
     */
    public Ticket() {}

    /**
     * To calculate bill as per traveled distance.
     *
     * @param distance travel distance
     * @return total amount
     */
    public double calculateBill(double distance){
        return distance * 5;
    }

    /**
     * getter for bill id.
     * @return Ticket's id
     */
    public int getId() {
        return id;
    }

}
