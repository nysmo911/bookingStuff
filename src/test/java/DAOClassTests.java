import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import booking.dao.*;
import booking.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAOClassTests is a test class for HotelDAO
 *  */

public class DAOClassTests {
    //HotelDAO instance
    HotelDAO testDAO = new HotelDAO();

    //@Disabled("Disabled to test the delete method thoroughly")
    @Test
    public void addToDatabaseTest(){
        //Create instance of Hotel
        List<Room> testRooms = new ArrayList<Room>();
        testRooms.add(new Room(125, "Corner Room with two queen beds", true, "Double Queen", 5));
        Hotel testHotel = new Hotel("newTestHotel", "Salt Lake City", "UT", 5, testRooms);

        //Execute the addMethod
        testDAO.add(testHotel);

        //Assert that hotel was added
        Hotel resultHotel = testDAO.get(testHotel.getName());
        Assertions.assertEquals(testHotel.getName(), resultHotel.getName());

    }


    @Test
    public void getDatabaseTest(){
        //Get hotel from database
        Hotel testHotel = testDAO.get("The Plaza Hotel");

        //Assert that resulting hotel equals searched hotel
        Assertions.assertEquals(testHotel.getName(), "The Plaza Hotel");
        System.out.println(testHotel);
    }

    @Test
    public void getIDDatabaseTest(){
        //Get ID for specified document
        String pulledID = testDAO.getID("The Plaza Hotel");
        System.out.println(pulledID);

        //Assert the resulting string equals the ID
        Assertions.assertEquals(pulledID, "672c31bbad59ab497e2f39d9");

    }

    @Test
    public void updateDatabaseTest(){
        //Instantiate a Hotel Object
        Hotel updatedHotel = new Hotel("randoHotel", "Pasadena", "UT");

        //Update hotel
        testDAO.update(updatedHotel);

        //Assert hotel was updated
        Hotel resultHotel = testDAO.get(updatedHotel.getName());
        Assertions.assertEquals(updatedHotel.getName(), resultHotel.getName());

    }

    @Test
    public void deleteDatabaseTest(){
        //Delete hotel
        testDAO.delete("myTestHotel");

        //Assert hotel has been deleted
        Hotel resultHotel = testDAO.get("myTestHotel");
        Assertions.assertNull(resultHotel);
    }

}
