package booking;

/**
 * The booking.Room class represents a room in a hotel. It contains details
 * about the room's price, availability, and room type.
 * @author Elina Hossain
 * @version 1.0
 */
public class Room {
    private double price;     // Price of the room per night
    private boolean isAvailable; // Indicates if the room is available
    private RoomType roomType; // Type of the room (e.g., Single, Double)

    /**
     * Constructor to create a booking.Room object with price, availability, and room type.
     * @param price the price of the room
     * @param isAvailable whether the room is available
     * @param roomType the type of the room
     */
    public Room(double price, boolean isAvailable, RoomType roomType) {
        this.price = price;
        this.isAvailable = isAvailable;
        this.roomType = roomType;
    }

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
     * Retrieves the room type.
     * @return the room type
     */
    public RoomType getRoomType() {
        return roomType;
    }

    /**
     * Returns a string representation of the room, including price, availability, and room type.
     * @return a string describing the room
     */
    public String toString() {
        return "Price: $" + price + ", Available: " + isAvailable + ", booking.Room Type: " + roomType;
    }
}
