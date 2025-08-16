package Model;

import DataBase.ParkingLotDAO;

import java.util.Random;

public class ParkingLot {
    int id;
    String name;
    int areaId;
    int capacity;
    int currentOccupancy;

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
        int latestOccupancy = dao.getCurrentOccupancyById(id);

        if (latestOccupancy < capacity) {
            currentOccupancy = latestOccupancy + 1;
            dao.updateOccupancyById(id, currentOccupancy);
            System.out.println("Slot booked at " + name + " (Area " + areaId + ")");

            // Simulate release after random time
            Thread releaseThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int delay = new Random().nextInt(5) + 1; // 10 to 50 seconds
                        Thread.sleep(delay * 10000);

                        int refreshedOccupancy = dao.getCurrentOccupancyById(id);
                        dao.updateOccupancyById(id, Math.max(0, refreshedOccupancy - 1));
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
