package booking.dao;

import booking.model.Reservation;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static booking.util.DbConnection.getInstance;


/*
public class ReservationDAO implements GenericDAO<Reservation> {


    private MongoDatabase db = getInstance().getDatabase();
    final private MongoCollection<Document> collection = db.getCollection("hotels");


    @Override
    public void add(Reservation resy){
        //Create ID
        //long objectIDCount = cole
    }

    @Override
    public Reservation get(String reservationID) {
        Reservation resy = new Reservation();
        return resy;
    }

    @Override
    public <Thing> void update(String reservationID, String fieldName, Thing fieldValue){
        return (Thing)Object;
    }

    @Override
    public void delete(String reservationID) {

    }




}
 */
