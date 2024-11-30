package booking.model;

/**
 * The Room class represents a room in a hotel.
 * It contains details about the room's price, availability, type, and capacity.
 * @author Elina Hossain
 * @version 1.0
 */
public class Room {
    private String typeName ;       // The name of the room type (e.g., Single, Double, Suite)
    private double price;          // Price of the room per night
    private boolean isAvailable;   // Indicates if the room is available
    private int capacity;          // The maximum capacity of this room

    /**
     * Constructor to create a Room object with price, availability, type, and capacity.
     * @param typeName the type of the room
     * @param price the price of the room
     * @param isAvailable whether the room is available
     * @param capacity the maximum capacity of the room
     */
    public Room(double price, boolean isAvailable, String typeName, int capacity) {
        this.typeName = typeName;
        this.price = price;
        this.isAvailable = isAvailable;
        this.capacity = capacity;
    }

    /**
     * No Argument Constructor
     */
    public Room(){}

    /**
     * Retrieves the price of the room.
     * @return the room price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Checks if the room is available.
     * @return true if available, false otherwise
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Retrieves the name of the room type.
     * @return the room type name
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Retrieves the capacity of the room.
     * @return the room capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns a string representation of the room, including price, availability, type, and capacity.
     * @return a string describing the room
     */
    @Override
    public String toString() {
        return "Price: $" + price + ", Available: " + isAvailable + ", Room Type: " + typeName + ", Capacity: " + getCapacity();
}

}
