import booking.dao.RoomDAO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import booking.model.Room;


/**
 * HotelDAOTest is a test class for HotelDAO
 *
 * @author Brandon Brenes
 * @version 1.0
 * @date 11/29/2024
 *  */

public class RoomDAOTest {
    //RoomDAO instance
    RoomDAO RoomDAO = new RoomDAO();

    @Disabled("Disabled for now")
    @Test
    public void addToDatabaseTest(){
        //Create instance of room
        Room testRoom = new Room("The Suite", "An elegant corner suite for those with Presidential taste", true, 102, 5 );

        //Add to database using addMethod
        RoomDAO.add(testRoom);

        //Assert hotel has been added
        Room resultRoom = RoomDAO.get(testRoom.getTypeName());
        Assertions.assertEquals(testRoom.getTypeName(), resultRoom.getTypeName());
    }


    @Test
    public void getDatabaseTest(){
        //Get Room from Database
        Room resultRoom = RoomDAO.get("The Suite");

        //Assert correct item was retrieved
        Assertions.assertEquals(resultRoom.getTypeName(), "The Suite");
    }

    @Test
    public void getRoomIdTest(){
        //Get ID
        Object pulledID = RoomDAO.getRoomID("The Suite");
        System.out.println(pulledID);
        String stringID = pulledID.toString();

        //Assert the resulting string equals the ID
        Assertions.assertEquals(stringID, "674f5677433a264253fcc749");
    }

    @Test
    public void updateDatabaseTest(){
        //Create instance of Room
        Room updatedRoom = new Room("The Suite", "An upgraded version of The Suite", true, 220, 5 );

        //Call update method
        RoomDAO.update(updatedRoom, "description", "An even more upgraded BLAH");

        //Assert that room has been updated
        Room resultRoom = RoomDAO.get(updatedRoom.getTypeName());
        Assertions.assertEquals(updatedRoom.getTypeName(), resultRoom.getTypeName());
    }
}
