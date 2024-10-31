import java.util.*;

/**
 * The Hotel class represents a hotel containing a collection of rooms.
 * It provides methods to add rooms, retrieve all rooms, 
 * get available rooms, and display hotel information.
 */
public class Hotel {
    private String name;    // The name of the hotel
    private String location; // The location of the hotel
    private List<Room> rooms; // List of rooms in the hotel

    /**
     * Constructor to create a Hotel object with a name and location.
     * @param name the name of the hotel
     * @param location the location of the hotel
     */
    public Hotel(String name, String location) {
        this.name = name;
        this.location = location;
        this.rooms = new ArrayList<>();
    }

    /**
     * Adds a room to the hotel's list of rooms.
     * @param room the room to be added
     */
    public void addRoom(Room room) {
        rooms.add(room);
    }

    /**
     * Retrieves the list of all rooms in the hotel.
     * @return the list of rooms
     */
    public List<Room> getRooms() {
        return rooms;  
    }

    /**
     * Retrieves a list of available rooms in the hotel.
     * @return the list of available rooms
     */
    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    /**
     * Displays the hotel's information, including the name, location, and rooms.
     */
    public void displayHotelInfo() {
        System.out.println("Hotel Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Rooms:");
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    /**
     * Retrieves the name of the hotel.
     * @return the hotel name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the location of the hotel.
     * @return the hotel location
     */
    public String getLocation() {
        return location;
    }
}
