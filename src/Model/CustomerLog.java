package Model;

import java.sql.Timestamp;

public class CustomerLog {
    int customerId;
    String username;
    String password;
    String email;
    String fullName;
    Timestamp deletedAt;


    /**
     * Constructor for deleted Customer log.
     *
     * @param customerId for id of customer
     * @param username   for username of customer
     * @param password   for password of deleted customer
     * @param email      for email of customer
     * @param fullName   for full name of customer
     * @param deletedAt  for when this customer is deleted
     */
    public CustomerLog(int customerId, String username, String password, String email, String fullName, Timestamp deletedAt) {
        this.customerId = customerId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.deletedAt = deletedAt;
    }

    /**
     * getter for id of customer.
     *
     * @return customer id
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * getter for username of customer
     *
     * @return customer name
     */
    public String getUsername() {
        return username;
    }

    /**
     * getter for when customer is deleted.
     *
     * @return deleted customer date
     */
    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    /**
     * getter for email of customer.
     *
     * @return email id
     */
    public String getEmail() {
        return email;
    }

    /**
     * getter for full name of customer.
     *
     * @return full name
     */
    public String getFullName() {
        return fullName;
    }
}
