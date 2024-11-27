package booking.model;

import java.util.*;

/**
 * The booking.model.HotelSearch class provides functionality to search for hotelList based on
 * room availability and a maximum price limit.
 *
 *  @author Elina Hossain
 *  @version 1.0
 *  @date 10/31/2024
 */
class HotelSearch {

    /**
     * Searches for hotelList that have rooms matching the provided criteria: maximum price and availability.
     * 
     * @param hotelList the list of hotelList to search
     * @param priceLimit the maximum price for a room
     * @param requireAvailability if true, only consider available rooms; otherwise, consider all rooms
     * @return a list of hotelList that have rooms matching the search criteria
     */
    public static List<Hotel> findHotelsByCriteria(List<Hotel> hotelList, double priceLimit, boolean requireAvailability) {
        List<Hotel> hotelListWithMatchingRooms = new ArrayList<>(); // List to store hotelList with matching rooms

        for (Hotel hotel : hotelList) {
            List<Room> matchingRooms = new ArrayList<>(); // List to store rooms that meet the criteria

            for (Room room : hotel.getRooms()) {
                // Check if the room matches the availability and price criteria
                if ((requireAvailability && room.isAvailable()) || !requireAvailability) {
                    if (room.getPrice() <= priceLimit) {
                        matchingRooms.add(room);
                    }
                }
            }

            // If the hotel has any rooms that meet the criteria, add the hotel to the result list
            if (!matchingRooms.isEmpty()) {
                hotelListWithMatchingRooms.add(hotel);
            }
        }

        return hotelListWithMatchingRooms;
    }
}
