package Model;

import DataBase.ParkingLotDAO;

import java.util.Random;
import java.util.Scanner;

public class ParkingLot {
    int id;
    String name;
    int areaId;
    int capacity;
    int currentOccupancy;

    /**
     * Constructor for ParkingLot class.
     *
     * @param id               for lot id
     * @param name             for lot name
     * @param areaId           for location of lot
     * @param capacity         for number of spots
     * @param currentOccupancy number spots currently taken
     */
    public ParkingLot(int id, String name, int areaId, int capacity, int currentOccupancy) {
        this.id = id;
        this.name = name;
        this.areaId = areaId;
        this.capacity = capacity;
        this.currentOccupancy = currentOccupancy;
    }

    /**
     * Default Constructor.
     */
    public ParkingLot() {
    }

    /**
     * getter for lot id.
     *
     * @return lot's id
     */
    public int getId() {
        return id;
    }

    /**
     * getter for lot name.
     *
     * @return lot's name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for location of lot.
     *
     * @return lot area id
     */
    public int getAreaId() {
        return areaId;
    }

    /**
     * getter for number of slots.
     *
     * @return slots in lot
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * getter for slots taken inside lot.
     *
     * @return taken slots in lot
     */
    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    /**
     * setter for lot id.
     *
     * @param id lot id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter for lot name.
     *
     * @param name lot name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter for location of lot.
     *
     * @param areaId area id of lot
     */
    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    /**
     * setter for capacity in lot.
     *
     * @param capacity lot capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * setter for slots taken in lot.
     *
     * @param currentOccupancy taken slots
     */
    public void setCurrentOccupancy(int currentOccupancy) {
        this.currentOccupancy = currentOccupancy;
    }

    /**
     * To book a new slot.
     *
     * @param dao object to update database and occupancy
     */
    public void bookSlot(ParkingLotDAO dao) {
        if (currentOccupancy < capacity) {
            currentOccupancy++;
            dao.updateOccupancyById(id, currentOccupancy); // Update DB
            System.out.println("Slot booked at " + name + " (Area " + areaId + ")");

            // Simulate release after random time
            Thread releaseThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int delay = new Random().nextInt(10) + 1; // 1 to 20 seconds
                        Thread.sleep(delay * 10000);
                        currentOccupancy--;
                        dao.updateOccupancyById(id, currentOccupancy); // Update DB again
                        System.out.println("Slot released at " + name + " after " + delay + " sec");
                    } catch (InterruptedException e) {
                        System.out.println("Booking interrupted at " + name);
                    }
                }
            });

            releaseThread.start();
        } else {
            System.out.println("No slots available at " + name);
        }
    }

}
