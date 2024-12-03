import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import booking.model.Hotel;
import booking.model.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * HotelTests is a test class for the Hotel class
 */
public class HotelTests {

    @Test
    public void addRoomTest() {
        // Arrange: Create a hotel with no initial rooms
        Hotel testHotel = new Hotel("Test Hotel", "New York", "NY");

        // Act: Add a new room with the adjusted constructor
        Room testRoom = new Room("GuestRoom", "testdesc", true, 95, 5);
        testHotel.addRoom(testRoom);

        // Assert: The room list should now contain the added room
        Assertions.assertEquals(1, testHotel.getRooms().size());
        Assertions.assertEquals(testRoom, testHotel.getRooms().get(0));
    }

    @Test
    public void getRoomsTest() {
        // Arrange: Create a hotel with a list of rooms
        List<Room> testRooms = new ArrayList<>();
        Room room1 = new Room("Standard", "Room 1", true, 101, 2);
        Room room2 = new Room("Deluxe", "Room 2", false, 102, 4);
        testRooms.add(room1);
        testRooms.add(room2);
        Hotel testHotel = new Hotel("Test Hotel", "New York", "NY", 2, testRooms);

        // Act: Retrieve all rooms from the hotel
        List<Room> resultRooms = testHotel.getRooms();

        // Assert: The retrieved list should match the expected list
        Assertions.assertEquals(2, resultRooms.size());
        Assertions.assertTrue(resultRooms.contains(room1));
        Assertions.assertTrue(resultRooms.contains(room2));
    }

    @Test
    public void getAvailableRoomsTest() {
        // Arrange: Create a hotel with both available and unavailable rooms
        List<Room> testRooms = new ArrayList<>();
        Room room1 = new Room("Standard", "Room 1", true,101 , 2);
        Room room2 = new Room("Deluxe", "Room 2", false, 101 , 4);
        testRooms.add(room1);
        testRooms.add(room2);
        Hotel testHotel = new Hotel("Test Hotel", "New York", "NY", 1, testRooms);

        // Act: Retrieve the available rooms
        List<Room> availableRooms = testHotel.getAvailableRooms();

        // Assert: Only the available room should be returned
        Assertions.assertEquals(1, availableRooms.size());
        Assertions.assertTrue(availableRooms.contains(room1));
        Assertions.assertFalse(availableRooms.contains(room2));
    }

    @Test
    public void displayHotelInfoTest() {
        // Arrange: Create a hotel with one room
        Room testRoom = new Room("Standard", "Room 1", true,101 , 2);
        Hotel testHotel = new Hotel("Test Hotel", "New York", "NY");
        testHotel.addRoom(testRoom);

        // Act & Assert: Call displayHotelInfo() and check if no exception is thrown
        Assertions.assertDoesNotThrow(testHotel::displayHotelInfo);
    }
}
