package booking.model;

import java.time.LocalDate;


/**
 * The Reservation class represents a booking made by a user.
 * It contains details about the reservation's start date, end date,
 * associated user, hotel, and room.
 * @author Andres Feldstedt
 * @version 1.0
 */
public class Reservation {

    private LocalDate startDate; // The start date of the reservation
    private LocalDate endDate;   // The end date of the reservation
    private UserProfile user;    // The user who made the reservation
    private Hotel hotel;         // The hotel associated with the reservation
    private Room room;           // The room booked in the reservation

    /**
     * Constructor to create a Reservation object.
     *
     * @param startDate The start date of the reservation.
     * @param endDate   The end date of the reservation.
     * @param user      The user who made the reservation.
     * @param hotel     The hotel where the reservation is made.
     * @param room      The room being reserved.
     */
    public Reservation(LocalDate startDate, LocalDate endDate, UserProfile user, Hotel hotel, Room room) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.hotel = hotel;
        this.room = room;

        // Add the reservation to the user's history
        if (user.getReservationHistory() != null) {
            user.getReservationHistory().add(toString());
        }
    }

    public Reservation(){
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now();
        this.user = null;
        this.hotel = null;
        this.room = null;
    }

    /**
     * Retrieves the start date of the reservation.
     *
     * @return The start date.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the reservation.
     *
     * @param startDate The new start date.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Retrieves the end date of the reservation.
     *
     * @return The end date.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the reservation.
     *
     * @param endDate The new end date.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Retrieves the user associated with the reservation.
     *
     * @return The user.
     */
    public UserProfile getUser() {
        return user;
    }

    /**
     * Sets the user associated with the reservation.
     *
     * @param user The new user.
     */
    public void setUser(UserProfile user) {
        this.user = user;
    }

    /**
     * Retrieves the hotel associated with the reservation.
     *
     * @return The hotel.
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * Sets the hotel associated with the reservation.
     *
     * @param hotel The new hotel.
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Retrieves the room associated with the reservation.
     *
     * @return The room.
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Sets the room associated with the reservation.
     *
     * @param room The new room.
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * Returns a string representation of the reservation details.
     *
     * @return The reservation details as a string.
     */
    @Override
    public String toString() {
        return "Reservation{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", hotel=" + hotel.getName() +
                ", room=" + room.getTypeName() +
                '}';
    }
}
