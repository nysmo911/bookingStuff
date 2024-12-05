package booking.dao;

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

    //Update
    <OtherThing> void update(Thing thing, String fieldName, OtherThing fieldValue) throws IllegalArgumentException;

    //Delete
    void delete(String name);
}
