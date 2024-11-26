package booking;

/**
 * The booking.RoomType class represents the type of a room in a hotel.
 * It includes the room's name (e.g., Single, Double, Suite) 
 * and the capacity of that room type.
 * @author Elina Hossain
 * @version 1.0
 */
public class RoomType {
    private String typeName;   // The name of the room type (e.g., Single, Double, Suite)
    private int capacity;      // The maximum capacity of this room type

    /**
     * Constructor to create a booking.RoomType object with a name and capacity.
     * @param typeName the name of the room type
     * @param capacity the capacity of the room
     */
    public RoomType(String typeName, int capacity) {
        this.typeName = typeName;
        this.capacity = capacity;
    }

    /**
     * Retrieves the name of the room type.
     * @return the room type name
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Retrieves the capacity of the room type.
     * @return the room capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns a string representation of the room type, including name and capacity.
     * @return a string describing the room type
     */
    public String toString() {
        return typeName + ", Capacity: " + capacity + " ";
    }
}
