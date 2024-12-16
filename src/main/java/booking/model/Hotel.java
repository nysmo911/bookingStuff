package booking.model;

import java.util.*;

/**
 * The booking.model.Hotel class represents a hotel containing a collection of rooms.
 * It provides methods to add rooms, retrieve all rooms, 
 * get available rooms, and display hotel information.
 *
 * @author Elina Hossain
 * @version 1.0
 */
public class Hotel {
    private String name;    // The name of the hotel
    private String city;    // The location (city) of the hotel
    private String state; //The location (state) of the hotel
    private int numOfAvailableRooms; //number of available rooms
    private List<Room> rooms; // List of rooms in the hotel

    public Hotel() {
        this.name = null;
        this.city = null;
        this.state = null;
        this.numOfAvailableRooms = 0;
        this.rooms = new ArrayList<>();
    }

    /**
     * Constructor to create a booking.model.Hotel object with a name and location.
     * @param name the name of the hotel
     * @param city the location(city) of the hotel
     * @param state
     * @param numOfAvailableRooms
     * @param rooms
     */
    public Hotel(String name, String city, String state, int numOfAvailableRooms, List<Room> rooms) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.numOfAvailableRooms = numOfAvailableRooms;
        this.rooms = rooms;
    }

    public Hotel(String name, String city, String state, List<Room> rooms) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.numOfAvailableRooms = 0;
        this.rooms = rooms;
    }

    public Hotel(String name, String city, String state) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.numOfAvailableRooms = 0;
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
        System.out.println("Name: " + name);
        System.out.println("Location: " + city + ", " + state);
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
     * Retrieves the location(city) of the hotel.
     * @return the hotel city
     */
    public String getCity() {
        return city;
    }

    /**
     * Retrieves the location(state) of the hotel.
     * @return the hotel city
     */
    public String getState() {return state;}

    /**
     * Retrieves the number of available rooms.
     * @return number of Available Rooms
     */
    public int getNumOfAvailableRooms() {
        int availableRooms = 0;
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms++;
            }
        }
        return availableRooms;
    }
}

