import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import booking.dao.*;
import booking.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * HotelDAOTest is a test class for HotelDAO
 *
 * @author Brandon Brenes
 * @version 1.0
 * @date 11/29/2024
 *  */

public class HotelDAOTest {
    //HotelDAO instance
    HotelDAO TestDAO = new HotelDAO();

    //@Disabled("Disabled to test the delete method thoroughly")
    @Test
    public void addToDatabaseTest(){
        //Create instance of Hotel
        List<Room> testRooms = new ArrayList<Room>();
        testRooms.add(new Room("Double Queen", "Corner Room with two queen beds", true, 50, 5));
        Hotel testHotel = new Hotel("COOLHOTEL", "PARK CITY", "UT", 5, testRooms);

        //Execute the addMethod
        TestDAO.add(testHotel);

        //Assert that hotel was added
        Hotel resultHotel = TestDAO.get(testHotel.getName());
        Assertions.assertEquals(testHotel.getName(), resultHotel.getName());

    }


    @Test
    public void getDatabaseTest(){
        //Get hotel from database
        Hotel testHotel = TestDAO.get("The Plaza Hotel");

        //Assert that resulting hotel equals searched hotel
        Assertions.assertEquals(testHotel.getName(), "The Plaza Hotel");
        System.out.println(testHotel);
    }

    @Test
    public void getIDDatabaseTest(){
        //Get ID for specified document
        Object pulledID = TestDAO.getID("The Plaza Hotel");
        String stringID = pulledID.toString();
        System.out.println(stringID);

        //Assert the resulting string equals the ID
        Assertions.assertEquals(stringID, "672c31bbad59ab497e2f39d9");

    }

    @Test
    public void updateDatabaseTest(){
        //Instantiate a Hotel Object
        Hotel updatedHotel = new Hotel("randoHotel", "Pasadena", "CA");

        //Update hotel
        TestDAO.update(updatedHotel);

        //Assert hotel was updated
        Hotel resultHotel = TestDAO.get(updatedHotel.getName());
        Assertions.assertEquals(updatedHotel.getName(), resultHotel.getName());

    }

    @Test
    public void deleteDatabaseTest(){
        //Delete hotel
        TestDAO.delete("newTestHotel");

        //Assert hotel has been deleted
        Hotel resultHotel = TestDAO.get("newTestHotel");
        Assertions.assertNull(resultHotel);
    }

}
