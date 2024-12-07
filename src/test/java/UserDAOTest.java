import booking.dao.HotelDAO;
import booking.model.*;
import booking.dao.UserDAO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import booking.model.Room;

import java.util.ArrayList;
import java.util.List;

public class UserDAOTest {
    UserDAO userDAO = new UserDAO();

    @Test
    public void addToDatabaseTest(){
       //Initialize UserProfile object
        List<String>res_history = new ArrayList<>();
        res_history.add("4/12-4/15:room1");
        res_history.add("5/30-6/1: room2");
        UserProfile testUser = new UserProfile("Elina", "Hossain","ellyH@gmail.com", "8187613935", "elina", "passy123", res_history);

        //Add to database
        userDAO.add(testUser);

        //Assert that testUser was entered
        UserProfile newUser = userDAO.get("elina");
        System.out.println(newUser);
        Assertions.assertEquals("elina", newUser.getUserName());

    }

    @Test
    public void getFromDatabaseTest(){
        //Test overloaded methods
        //Username
        UserProfile assertUsername = userDAO.get("brandyboy");
        Assertions.assertEquals("brandyboy", assertUsername.getUserName());

        //First & Last
        UserProfile assertFirstAndLast = userDAO.get("Donald", "Obama");
        String fullName = assertFirstAndLast.getFName() + " " + assertFirstAndLast.getLName();
        Assertions.assertEquals("Donald Obama", fullName);
    }

    @Disabled("Will do later")
    @Test
    public void updateDatabaseTest(){

    }

    @Disabled("Will do later")
    @Test
    public void deleteDatabaseTest(){

    }

}
