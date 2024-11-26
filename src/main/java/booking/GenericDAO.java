package booking;

import java.util.List;

/**
 * Generic DAO (Data Access Object) is an abstract interface to the database.
 *
 * @author Brandon Brenes
 * @version 1.0
 *
 */

public interface GenericDAO<Thing> {


    //CRUD Methods

    //Add method
    void add(Thing object);

    //Get method
    Thing get(String name);

    //Get all method
   // List<Thing> getAll();

    //Update
    void update(Thing thing);

    //Delete
    void delete(String name);
}
